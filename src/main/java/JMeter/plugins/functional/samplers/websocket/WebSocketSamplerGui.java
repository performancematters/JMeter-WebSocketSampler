/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JMeter.plugins.functional.samplers.websocket;

import java.awt.BorderLayout;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.config.gui.ArgumentsPanel;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;

/**
 *
 * @author Marc Parmet
 */
public class WebSocketSamplerGui extends AbstractSamplerGui {
	private static final long serialVersionUID = 1L;
	private WebSocketSamplerPanel panel;

    public WebSocketSamplerGui() {
        super();
        panel = new WebSocketSamplerPanel();
        panel.initFields();

        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());

        add(makeTitlePanel(), BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    @Override
    public String getStaticLabel() {
        return "WebSocket Sampler";
    }

    @Override
    public String getLabelResource() {
    	throw new UnsupportedOperationException();
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
        if (element instanceof WebSocketSampler) {
            WebSocketSampler sampler = (WebSocketSampler) element;
            panel.setServerAddress(sampler.getServerAddress());
            panel.setServerPort(sampler.getServerPort());
            panel.setProtocol(sampler.getProtocol());
            panel.setContextPath(sampler.getContextPath());
            panel.setContentEncoding(sampler.getContentEncoding());
            panel.setDataToWrite(sampler.getDataToWrite());
            panel.setConnectionTimeout(sampler.getOpenTimeout());
            panel.setConnectionId(sampler.getConnectionId());
            panel.setOpenWebSocket(sampler.getOpenWebSocket());
            panel.setWriteWebSocket(sampler.getWriteWebSocket());
            panel.setReadWebSocket(sampler.getReadWebSocket());
            panel.setReadTimeout(sampler.getReadTimeout());
            panel.setCloseWebSocket(sampler.getCloseWebSocket());

            Arguments queryStringParameters = sampler.getQueryStringParameters();
            if (queryStringParameters != null) {
                panel.getAttributePanel().configure(queryStringParameters);
            }
        }
    }

    @Override
    public TestElement createTestElement() {
        WebSocketSampler sampler = new WebSocketSampler();
        configureTestElement(sampler);
        return sampler;
    }

    @Override
    public void modifyTestElement(TestElement te) {
        configureTestElement(te);
        if (te instanceof WebSocketSampler) {
            WebSocketSampler sampler = (WebSocketSampler) te;
            sampler.setServerAddress(panel.getServerAddress());
            sampler.setServerPort(panel.getServerPort());
            sampler.setProtocol(panel.getProtocol());
            sampler.setContextPath(panel.getContextPath());
            sampler.setContentEncoding(panel.getContentEncoding());
            sampler.setDataToWrite(panel.getDataToWrite());
            sampler.setOpenTimeout(panel.getConnectionTimeout());
            sampler.setOpenWebSocket(panel.getOpenWebSocket());
            sampler.setWriteWebSocket(panel.getWriteWebSocket());
            sampler.setReadWebSocket(panel.getReadWebSocket());
            sampler.setReadTimeout(panel.getReadTimeout());
            sampler.setConnectionId(panel.getConnectionId());
            sampler.setCloseWebSocket(panel.getCloseWebSocket());

            ArgumentsPanel queryStringParameters = panel.getAttributePanel();
            if (queryStringParameters != null) {
                sampler.setQueryStringParameters((Arguments)queryStringParameters.createTestElement());
            }
        }
    }

    @Override
    public void clearGui() {
        super.clearGui();
        panel.initFields();
    }

}
