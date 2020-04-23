package client;
 
import java.net.*;
import java.io.*;
 
public class ClientFrame extends javax.swing.JFrame
{
 
    public ClientFrame()
    {
        initComponents();
        line = "";
    }
 
    Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String line = null;
 
    public void listenSocket()
    {
        //Create socket connection
        String serverAddress = "localhost";
 
        try
        {
            socket = new Socket(serverAddress, 4444);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (UnknownHostException e)
        {
            System.out.println("Unknown host: " + serverAddress);
            System.exit(1);
        }
        catch  (IOException e)
        {
            System.out.println("No I/O");
            System.exit(1);
        }
}
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
 
        jLabel1 = new javax.swing.JLabel();
        sendText = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
 
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client Side");
 
        jLabel1.setText("text to send over socket");
 
        okButton.setText("send message");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
 
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sendText, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
 
        pack();
    }// </editor-fold>
 
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String text = sendText.getText();
        out.println(text);
        sendText.setText(new String(""));
        out.println(text);
 
        //Receive text from server
        try
        {
            String line2 = in.readLine();
            System.out.println("Text received: " + line2);
        }
        catch (IOException e)
        {
            System.out.println("Read failed");
            System.exit(1);
        }
 
}                                        
 
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientFrame().setVisible(true);
            }
        });
    }
 
    // Variables declaration - do not modify
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField sendText;
    // End of variables declaration
 
}