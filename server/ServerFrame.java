package server;
 
import java.net.*;
import java.io.*;
 
public class ServerFrame extends javax.swing.JFrame
{
    ServerSocket server = null;
    Socket client = null;
    BufferedReader in = null;
    PrintWriter out = null;
    String line;
 
    public ServerFrame()
    {
        initComponents();
        line = "";
    }    
 
    public void listenSocket()
    {
        int portNumber = 4444;
 
        try
        {
            server = new ServerSocket(portNumber);
        }
        catch (IOException e)
        {
            System.out.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
 
        try
        {
            client = server.accept();
        }
        catch (IOException e)
        {
            System.out.println("Accept failed :" + portNumber);
            System.exit(-1);
        }
 
        try
        {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e)
        {
            System.out.println("Read failed");
            System.exit(-1);
        }
 
        while(true)
        {
            try
            {
                line = in.readLine();
                //Send data back to client
                out.println(line);
            }
            catch (IOException e)
            {
                System.out.println("Read failed");
                System.exit(-1);
            }
        }
 
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
 
        okButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        receivedText = new javax.swing.JTextField();
 
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server Side");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
 
        okButton.setText("receive message");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
 
        jLabel1.setText("text received over socket");
 
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(receivedText, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addComponent(okButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(receivedText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
 
        pack();
    }// </editor-fold>
 
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
 
        receivedText.setText(line);
    }                                        
 
    private void formWindowClosed(java.awt.event.WindowEvent evt) {
        try
        {
            in.close();
            out.close();
            server.close();
        }
        catch (IOException e)
        {
            System.out.println("Could not close.");
            System.exit(-1);
        }
    }                                 
 
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new ServerFrame().setVisible(true);
            }
        });
    }
 
    // Variables declaration - do not modify
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField receivedText;
    // End of variables declaration
 
}