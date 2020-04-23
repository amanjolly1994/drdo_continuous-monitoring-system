package client;

public class ClientMain {
	public static void main(String[] args)
    {
        ClientFrame c = new ClientFrame();
        c.setVisible(true);
        c.listenSocket();
    }
 

}
