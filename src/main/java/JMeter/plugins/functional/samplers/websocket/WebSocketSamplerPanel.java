/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JMeter.plugins.functional.samplers.websocket;

import org.apache.jmeter.config.gui.ArgumentsPanel;
import org.apache.jmeter.protocol.http.gui.HTTPArgumentsPanel;

/**
 *
 * @author Marc Parmet
 */
public class WebSocketSamplerPanel extends javax.swing.JPanel {
	private static final long serialVersionUID = 1L;
	private HTTPArgumentsPanel attributePanel;

    /**
     * Creates new form WebSocketSamplerPanel
     */
    public WebSocketSamplerPanel() {
        initComponents();

        attributePanel = new HTTPArgumentsPanel();
        querystringAttributesPanel.add(attributePanel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        contextPathTextField = new javax.swing.JTextField();
        protocolTextField = new javax.swing.JTextField();
        contentEncodingTextField = new javax.swing.JTextField();
        querystringAttributesPanel = new javax.swing.JPanel();
        openWebsocketCheckbox = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        serverAddressTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        serverPortTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        connectionTimeoutTextField = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        readWebsocketCheckbox = new javax.swing.JCheckBox();
        jLabel17 = new javax.swing.JLabel();
        readTimeoutTextField = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        closeWebsocketCheckbox = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        writeWebsocketCheckbox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataToWriteEditorPanel = new javax.swing.JEditorPane();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        connectionIdTextField = new javax.swing.JTextField();

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Open"));

        jLabel4.setText("Protocol [ws/wss]:");

        jLabel5.setText("Path:");

        jLabel6.setText("Content encoding:");

        protocolTextField.setToolTipText("");

        querystringAttributesPanel.setLayout(new javax.swing.BoxLayout(querystringAttributesPanel, javax.swing.BoxLayout.LINE_AXIS));

        openWebsocketCheckbox.setText("Open WebSocket");

        jLabel1.setText("Server Name or IP:");

        jLabel2.setText("Port Number:");

        jLabel3.setText("Timeout (ms):");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(querystringAttributesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contextPathTextField))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(serverAddressTextField)
                        .addGap(152, 152, 152))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(openWebsocketCheckbox)
                                .addGap(44, 44, 44)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(connectionTimeoutTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(protocolTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addGap(3, 3, 3)
                                .addComponent(serverPortTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(contentEncodingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 198, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(connectionTimeoutTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(openWebsocketCheckbox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(serverAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(contentEncodingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(serverPortTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(protocolTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contextPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(querystringAttributesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Read"));

        readWebsocketCheckbox.setText("Read WebSocket");

        jLabel17.setText("Timeout (ms):");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(readWebsocketCheckbox)
                .addGap(40, 40, 40)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(readTimeoutTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(readTimeoutTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readWebsocketCheckbox))
                .addGap(2, 2, 2))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Close"));

        closeWebsocketCheckbox.setText("Close WebSocket");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(closeWebsocketCheckbox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(closeWebsocketCheckbox, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Write"));

        writeWebsocketCheckbox.setText("Write WebSocket");

        jScrollPane1.setViewportView(dataToWriteEditorPanel);

        jLabel14.setText("Data to write:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(writeWebsocketCheckbox)
                            .addComponent(jLabel14))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(writeWebsocketCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel8.setText("Connection Id:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(connectionIdTextField)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(connectionIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox closeWebsocketCheckbox;
    private javax.swing.JTextField connectionIdTextField;
    private javax.swing.JTextField connectionTimeoutTextField;
    private javax.swing.JTextField contentEncodingTextField;
    private javax.swing.JTextField contextPathTextField;
    private javax.swing.JEditorPane dataToWriteEditorPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox openWebsocketCheckbox;
    private javax.swing.JTextField protocolTextField;
    private javax.swing.JPanel querystringAttributesPanel;
    private javax.swing.JTextField readTimeoutTextField;
    private javax.swing.JCheckBox readWebsocketCheckbox;
    private javax.swing.JTextField serverAddressTextField;
    private javax.swing.JTextField serverPortTextField;
    private javax.swing.JCheckBox writeWebsocketCheckbox;
    // End of variables declaration//GEN-END:variables

    public void initFields() {
    }

    public void setConnectionId(String connectionId) {
        connectionIdTextField.setText(connectionId);
    }

    public String getConnectionId() {
        return connectionIdTextField.getText();
    }

    public void setContentEncoding(String contentEncoding) {
        contentEncodingTextField.setText(contentEncoding);
    }

    public String getContentEncoding() {
        return contentEncodingTextField.getText();
    }

    public void setContextPath(String contextPath) {
        contextPathTextField.setText(contextPath);
    }

    public String getContextPath() {
        return contextPathTextField.getText();
    }

    public void setProtocol(String protocol) {
        protocolTextField.setText(protocol);
    }

    public String getProtocol() {
        return protocolTextField.getText();
    }

    public void setReadTimeout(String readTimeout) {
        readTimeoutTextField.setText(readTimeout);
    }

    public String getReadTimeout() {
        return readTimeoutTextField.getText();
    }

    public void setConnectionTimeout(String connectionTimeout) {
        connectionTimeoutTextField.setText(connectionTimeout);
    }

    public String getConnectionTimeout() {
        return connectionTimeoutTextField.getText();
    }

    public void setServerAddress(String serverAddress) {
        serverAddressTextField.setText(serverAddress);
    }

    public String getServerAddress() {
        return serverAddressTextField.getText();
    }

    public void setServerPort(String serverPort) {
        serverPortTextField.setText(serverPort);
    }

    public String getServerPort() {
        return serverPortTextField.getText();
    }

    public void setDataToWrite(String dataToWrite) {
        dataToWriteEditorPanel.setText(dataToWrite);
    }

    public String getDataToWrite() {
        return dataToWriteEditorPanel.getText();
    }
    
	public boolean getOpenWebSocket() { return openWebsocketCheckbox.isSelected(); }
	public boolean getWriteWebSocket() { return writeWebsocketCheckbox.isSelected(); }
	public boolean getReadWebSocket() { return readWebsocketCheckbox.isSelected(); }
    public boolean getCloseWebSocket() { return closeWebsocketCheckbox.isSelected(); }

    public void setOpenWebSocket(boolean openWebSocket) { openWebsocketCheckbox.setSelected(openWebSocket); }
    public void setWriteWebSocket(boolean writeWebSocket) { writeWebsocketCheckbox.setSelected(writeWebSocket); }
    public void setReadWebSocket(boolean readWebSocket) { readWebsocketCheckbox.setSelected(readWebSocket); }
    public void setCloseWebSocket(boolean closeWebSocket) { closeWebsocketCheckbox.setSelected(closeWebSocket); }

    /**
     * @return the attributePanel
     */
    public ArgumentsPanel getAttributePanel() {
        return attributePanel;
    }

}
