package server;

public class ServerMain {
	public static void main(String[] args)
    {
        ServerFrame s = new ServerFrame();
        s.setVisible(true);
        s.listenSocket();
    }

}
