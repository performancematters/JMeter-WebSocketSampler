/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JMeter.plugins.functional.samplers.websocket;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.jmeter.config.Argument;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.http.control.CookieManager;
import org.apache.jmeter.protocol.http.util.EncoderCache;
import org.apache.jmeter.protocol.http.util.HTTPArgument;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestStateListener;
import org.apache.jmeter.testelement.property.PropertyIterator;
import org.apache.jmeter.testelement.property.StringProperty;
import org.apache.jmeter.testelement.property.TestElementProperty;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.jorphan.util.JOrphanUtils;
import org.apache.log.Logger;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;

/**
 *
 * @author Marc Parmet
 */
public class WebSocketSampler extends AbstractSampler implements TestStateListener {
	private static final long serialVersionUID = 1L;

	static final Logger log = LoggingManager.getLoggerForClass();

	private static final String ARG_VAL_SEP = "="; // $NON-NLS-1$
	private static final String QRY_SEP = "&"; // $NON-NLS-1$
	private static final String WS_PREFIX = "ws://"; // $NON-NLS-1$
	private static final String WSS_PREFIX = "wss://"; // $NON-NLS-1$
	private static final String DEFAULT_PROTOCOL = "ws";

	private static Map<String, SocketSession> activeConnections;
	private CookieManager cookieManager;

	public WebSocketSampler() {
		super();
		setName("WebSocket sampler");
	}

	private void openWebSocket(String connectionId) throws Exception {
		if (activeConnections.containsKey(connectionId))
			return;

		List<String> cookieList = Lists.newArrayList();
		if (cookieManager != null)
			for (int i = 0; i < cookieManager.getCookieCount(); i++)
				cookieList.add(String.format("%s=%s", cookieManager.get(i).getName(), cookieManager.get(i).getValue()));

		SocketSession socket = new SocketSession(connectionId);
		int connectionTimeout = Integer.parseInt(getOpenTimeout());
		URI uri = getUri();
		socket.connect(uri, cookieList, connectionTimeout);
		activeConnections.put(connectionId, socket);

		//        if (cookieManager != null && cookieHandler != null) {
		//            String setCookieHeader = socket.getSession().getUpgradeResponse().getHeader("set-cookie");
		//            if (setCookieHeader != null) {
		//                cookieHandler.addCookieFromHeader(cookieManager, true, setCookieHeader, new URL(
		//                        uri.getScheme() == null || uri.getScheme().equalsIgnoreCase("ws") ? "HTTP" : "HTTPS",
		//                        uri.getHost(),
		//                        uri.getPort(),
		//                        uri.getQuery() != null ? uri.getPath() + "?" + uri.getQuery() : uri.getPath()
		//                ));
		//            }
		//        }
	}

	@Override
	public SampleResult sample(Entry entry) {
		SampleResult sampleResult = new SampleResult();
		sampleResult.setSampleLabel(getName());
		sampleResult.setDataEncoding(getContentEncoding());

		sampleResult.sampleStart();

		try {
			String connectionId = getThreadName() + getConnectionId();

			if (getOpenWebSocket())
				openWebSocket(connectionId);

			if (getWriteWebSocket()) {
				SocketSession socketToWrite = checkNotNull(activeConnections.get(connectionId));
				String payloadMessage = getDataToWrite();
				sampleResult.setSamplerData(payloadMessage);
				socketToWrite.write(payloadMessage);
			}

			if (getReadWebSocket()) {
				SocketSession socketToRead = checkNotNull(activeConnections.get(connectionId));
				int readTimeout = Integer.parseInt(getReadTimeout());
				String response = socketToRead.read(readTimeout);
				if (response == null)
					throw new TimeoutException();
				sampleResult.setResponseData(response, getContentEncoding());
			}

			if (getCloseWebSocket()) {
				SocketSession socketToClose = activeConnections.remove(connectionId);
				if (socketToClose != null) {
					try {
						socketToClose.close();
					} catch (Exception ex) {
					}
				}
			}

			sampleResult.setResponseOK();
		} catch (Exception ex) {
			sampleResult.setSuccessful(false);
			sampleResult.setResponseCode(ex instanceof TimeoutException ? "204" : "500");
			sampleResult.setResponseMessage(ex.getMessage() + "\n" + ex.getClass().getName() + "\n" + Joiner.on('\n').join(ex.getStackTrace()));
		}

		sampleResult.sampleEnd();

		return sampleResult;
	}

	@Override
	public void setName(String name) {
		if (name != null) {
			setProperty(TestElement.NAME, name);
		}
	}

	@Override
	public String getName() {
		return getPropertyAsString(TestElement.NAME);
	}

	@Override
	public void setComment(String comment) {
		setProperty(new StringProperty(TestElement.COMMENTS, comment));
	}

	@Override
	public String getComment() {
		return getProperty(TestElement.COMMENTS).getStringValue();
	}

	public URI getUri() throws URISyntaxException {
		String path = this.getContextPath();
		// Hack to allow entire URL to be provided in host field
		if (path.startsWith(WS_PREFIX) || path.startsWith(WSS_PREFIX))
			return new URI(path);

		String domain = getServerAddress();
		String protocol = getEffectiveProtocol();
		// HTTP URLs must be absolute, allow file to be relative
		if (!path.startsWith("/")) { // $NON-NLS-1$
			path = "/" + path; // $NON-NLS-1$
		}

		String queryString = getQueryString(getContentEncoding());
		return new URI(protocol, null, domain, isProtocolDefaultPort() ? -1 : getEffectiveServerPort(), path, queryString, null);
	}

	/**
	 * Tell whether the default port for the specified protocol is used
	 *
	 * @return true if the default port number for the protocol is used, false
	 * otherwise
	 */
	public boolean isProtocolDefaultPort() {
		final int port = getEffectiveServerPort();
		final String protocol = getEffectiveProtocol();
		return ("ws".equalsIgnoreCase(protocol) && port == HTTPConstants.DEFAULT_HTTP_PORT)
				|| ("wss".equalsIgnoreCase(protocol) && port == HTTPConstants.DEFAULT_HTTPS_PORT);
	}

	public String getServerPort() {
		return getPropertyAsString("serverPort");
	}

	public int getEffectiveServerPort() {
		int port;

		try {
			port = Integer.parseInt(getServerPort());
		} catch (Exception ex) {
			port = 0;
		}

		if (port == 0) {
			String protocol = getEffectiveProtocol();
			if ("wss".equalsIgnoreCase(protocol))
				return HTTPConstants.DEFAULT_HTTPS_PORT;
			else if ("ws".equalsIgnoreCase(protocol))
				return HTTPConstants.DEFAULT_HTTP_PORT;
		}

		return port;
	}

	public void setServerPort(String port) {
		setProperty("serverPort", port);
	}    

	public String getReadTimeout() {
		return getPropertyAsString("responseTimeout", "20000");
	}    

	public void setReadTimeout(String readTimeout) {
		setProperty("responseTimeout", readTimeout);
	}    

	public String getOpenTimeout() {
		return getPropertyAsString("connectionTimeout", "5000");
	}    

	public void setOpenTimeout(String openTimeout) {
		setProperty("connectionTimeout", openTimeout);
	}    

	public void setProtocol(String protocol) {
		setProperty("protocol", protocol);
	}

	public String getProtocol() {
		return getPropertyAsString("protocol");
	}

	public String getEffectiveProtocol() {
		String protocol = getProtocol();
		return Strings.isNullOrEmpty(protocol) ? DEFAULT_PROTOCOL : protocol;
	}

	public void setServerAddress(String serverAddress) {
		setProperty("serverAddress", serverAddress);
	}

	public String getServerAddress() {
		return getPropertyAsString("serverAddress");
	}

	public void setContextPath(String contextPath) {
		setProperty("contextPath", contextPath);
	}

	public String getContextPath() {
		return getPropertyAsString("contextPath");
	}

	public void setContentEncoding(String contentEncoding) {
		setProperty("contentEncoding", contentEncoding);
	}

	public String getContentEncoding() {
		return getPropertyAsString("contentEncoding", "UTF-8");
	}

	public void setDataToWrite(String dataToWrite) {
		setProperty("requestPayload", dataToWrite);
	}

	public String getDataToWrite() {
		return getPropertyAsString("requestPayload");
	}

	public void setConnectionId(String connectionId) {
		setProperty("connectionId", connectionId);
	}

	public String getConnectionId() {
		return getPropertyAsString("connectionId");
	}

	public String getQueryString(String contentEncoding) {
		// Check if the sampler has a specified content encoding
		if (JOrphanUtils.isBlank(contentEncoding)) {
			// We use the encoding which should be used according to the HTTP spec, which is UTF-8
			contentEncoding = EncoderCache.URL_ARGUMENT_ENCODING;
		}
		StringBuilder buf = new StringBuilder();
		PropertyIterator iter = getQueryStringParameters().iterator();
		boolean first = true;
		while (iter.hasNext()) {
			HTTPArgument item;
			Object objectValue = iter.next().getObjectValue();
			try {
				item = (HTTPArgument) objectValue;
			} catch (ClassCastException e) {
				item = new HTTPArgument((Argument) objectValue);
			}
			final String encodedName = item.getEncodedName();
			if (encodedName.length() == 0) {
				continue; // Skip parameters with a blank name (allows use of optional variables in parameter lists)
			}
			if (!first) {
				buf.append(QRY_SEP);
			} else {
				first = false;
			}
			buf.append(encodedName);
			if (item.getMetaData() == null) {
				buf.append(ARG_VAL_SEP);
			} else {
				buf.append(item.getMetaData());
			}

			// Encode the parameter value in the specified content encoding
			try {
				buf.append(item.getEncodedValue(contentEncoding));
			} catch (UnsupportedEncodingException e) {
				log.warn("Unable to encode parameter in encoding " + contentEncoding + ", parameter value not included in query string");
			}
		}
		return buf.toString();
	}

	public void setQueryStringParameters(Arguments queryStringParameters) {
		setProperty(new TestElementProperty("queryStringParameters", queryStringParameters));
	}

	public Arguments getQueryStringParameters() {
		return (Arguments) getProperty("queryStringParameters").getObjectValue();
	}


	@Override
	public void testStarted() {
		testStarted(null);
	}

	@Override
	public void testStarted(String host) {
		activeConnections = new MapMaker().concurrencyLevel(100).makeMap();
	}

	@Override
	public void testEnded() {
		testEnded(null);
	}

	@Override
	public void testEnded(String host) {
		for (SocketSession socket : activeConnections.values()) {
			try {
				socket.close();
			} catch (IOException ex) {
			}
		}
	}

	@Override
	public void addTestElement(TestElement el) {
		super.addTestElement(el);
		if (el instanceof CookieManager)
			cookieManager = (CookieManager) el;
	}

	public boolean getOpenWebSocket() { return getPropertyAsBoolean("openWebSocket"); }
	public boolean getWriteWebSocket() { return getPropertyAsBoolean("writeWebSocket"); }
	public boolean getReadWebSocket() { return getPropertyAsBoolean("readWebSocket"); }
	public boolean getCloseWebSocket() { return getPropertyAsBoolean("closeWebSocket"); }

	public void setOpenWebSocket(boolean openWebSocket) { setProperty("openWebSocket", openWebSocket); }
	public void setWriteWebSocket(boolean writeWebSocket) { setProperty("writeWebSocket", writeWebSocket); }
	public void setReadWebSocket(boolean readWebSocket) { setProperty("readWebSocket", readWebSocket); }
	public void setCloseWebSocket(boolean closeWebSocket) { setProperty("closeWebSocket", closeWebSocket); }

}
