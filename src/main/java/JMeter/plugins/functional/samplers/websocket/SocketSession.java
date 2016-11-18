package JMeter.plugins.functional.samplers.websocket;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import javax.websocket.ClientEndpoint;
import javax.websocket.ClientEndpointConfig;
import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.glassfish.grizzly.threadpool.ThreadPoolConfig;
import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.client.ClientProperties;
import org.glassfish.tyrus.container.grizzly.client.GrizzlyClientProperties;

import com.google.common.collect.Lists;

class SocketSession {
	
	String connectionId;
	Session session;
	BlockingDeque<String> incomingMessages;
	
	private static ClientManager clientManager;

	private static synchronized void init() {
    	if (clientManager == null) {
    		clientManager = ClientManager.createClient();
    		// See https://grizzly.java.net
    		// See https://blogs.oracle.com/PavelBucek/entry/tyrus_client_shared_container
    		clientManager.getProperties().put(ClientProperties.SHARED_CONTAINER, true);
    		// The timeout seems to make no difference in application behavior.
    		// clientManager.getProperties().put(ClientProperties.SHARED_CONTAINER_IDLE_TIMEOUT, 600); // seconds

    		ThreadPoolConfig workerThreadPoolConfig = ThreadPoolConfig.defaultConfig().setPoolName("wsWorkerPool").setCorePoolSize(50);
    		clientManager.getProperties().put(GrizzlyClientProperties.WORKER_THREAD_POOL_CONFIG, workerThreadPoolConfig);
    		ThreadPoolConfig selectorThreadPoolConfig = ThreadPoolConfig.defaultConfig().setPoolName("wsSelectorPool").setCorePoolSize(50);
    		clientManager.getProperties().put(GrizzlyClientProperties.SELECTOR_THREAD_POOL_CONFIG, selectorThreadPoolConfig);
    	}
    }

	SocketSession(String connectionId) {
		this.connectionId = connectionId;
		session = null;
		incomingMessages = new LinkedBlockingDeque<>();
	}
	
	void connect(URI uri, final Collection<String> cookies, long timeoutInMilliseconds) throws Exception {
		init();
		
		ClientEndpointConfig cec = ClientEndpointConfig.Builder.create()
				.configurator(new ClientEndpointConfig.Configurator() {
					@Override
					public void beforeRequest(Map<String, List<String>> headers) {
						if (cookies != null) {
							List<String> cookieList = headers.get("Cookie");
							if (cookieList == null) {
								cookieList = Lists.newArrayList();
								headers.put("Cookie", cookieList);
							}
							cookieList.addAll(cookies);
						}
					}
				}).build();

		Future<Session> futureSession = clientManager.asyncConnectToServer(new SocketEndpoint(), cec, uri);
		try {
			session = futureSession.get(timeoutInMilliseconds, TimeUnit.MILLISECONDS);
		} catch (Exception ex) {
			futureSession.cancel(true);
			throw ex;
		}

		session.addMessageHandler(new MessageHandler.Whole<String>() {
			@Override
			public void onMessage(String message) {
				WebSocketSampler.log.debug(String.format("[%s] message!\n%s\n", connectionId, message));
				incomingMessages.addLast(message);
			}
		});
	}
	
	void write(String message) {
		session.getAsyncRemote().sendText(message);
	}
	
	String read(long timeoutInMilliseconds) throws InterruptedException {
		return incomingMessages.pollFirst(timeoutInMilliseconds, TimeUnit.MILLISECONDS);
	}
	
	void close() throws IOException {
		if (session != null)
			session.close();
	}

	@ClientEndpoint
	class SocketEndpoint extends Endpoint {

		@Override
		public void onOpen(Session session, EndpointConfig config) {
			WebSocketSampler.log.debug(String.format("[%s] open!\n", connectionId));
		}
		
		@Override
		public void onClose(Session session, CloseReason closeReason) {
			WebSocketSampler.log.debug(String.format("[%s] close! %s\n", connectionId, closeReason));
		}
		
		@Override
		public void onError(Session session, Throwable t) {
			WebSocketSampler.log.debug(String.format("[%s] error!\n", connectionId), t);
		}
	}

}
