package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket myServer;

	public void startServer() throws IOException{
		myServer = new ServerSocket(2222);
		System.out.println("Server is running");
		while (true){
			Socket socketOfServer = myServer.accept();			
			ClientThread ct = new ClientThread(socketOfServer);
			ct.start();
		}		
	}
	
	public static void main(String[] args) throws IOException{
		new Server().startServer();
	}
}
