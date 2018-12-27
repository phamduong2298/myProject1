package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import ClientService.LogIn;

public class Client {
	private Socket socketOfClient;
	private final String ip = "localhost";
	private DataInputStream is;
	private DataOutputStream os;
	public String userName;
	
	public void start() throws UnknownHostException, IOException{
		socketOfClient = new Socket(ip,2222);
		is =new DataInputStream(socketOfClient.getInputStream());
		os = new DataOutputStream(socketOfClient.getOutputStream());
	}
	
	public void close() throws IOException{
		is.close();
		os.close();
		socketOfClient.close();
	}
	
	public void sendMSG(String data) throws IOException{
		os.writeUTF(data);
		os.flush();
	}
	
	public void sendUser(String username, String pw, String name, String phone, String address) throws IOException{
		os.writeUTF(username);
		os.flush();
		os.writeUTF(pw);
		os.flush();
		os.writeUTF(name);
		os.flush();
		os.writeUTF(phone);
		os.flush();
		os.writeUTF(address);
		os.flush();
	}
	
	public void requestLogin(String name, String pw) throws IOException{
		os.writeUTF(name);
		os.flush();
		os.writeUTF(pw);
		os.flush();
	}
	
	public void requestChangePw(String name,String pw1, String pw2, String pw3) throws IOException{
		os.writeUTF(name);
		os.flush();
		os.writeUTF(pw1);
		os.flush();
		os.writeUTF(pw2);
		os.flush();
		os.writeUTF(pw3);
		os.flush();
	}
	
	public String getMSG() throws IOException{
		String msg = is.readUTF();
		return msg;
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client = new Client();
		client.start();
		LogIn logIn = new LogIn(client);
		logIn.frame.setVisible(true);
	}

}
