package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.interfaces.RSAKey;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import AdminService.BuyRequest;
import md5.MD5Library;
import connect.ClientData;
import connect.FoodData;
import connect.GetDataById;
import connect.User;

public class ClientThread extends Thread{
	private Socket socketOfServer;
	private DataInputStream is;
	private DataOutputStream os;
	private boolean active;
	
	public ClientThread(Socket socket){
		this.socketOfServer = socket;
	}
	
	public void run(){
		String request = null;
		try {
			is = new DataInputStream(socketOfServer.getInputStream());
			os = new DataOutputStream(socketOfServer.getOutputStream());
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		while (!active){
			try {
				request = is.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (request.equals("login")){
				try {
					String name = is.readUTF();
					String pw = is.readUTF();
					ClientData clientData = new ClientData();
					if (clientData.checkLogIn(name, pw)){
						active = true;
						sendMSG("LogInSucc");
					}
					else {
						sendMSG("LogInFail");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (request.equals("CheckUser")){
				try {
					String name = is.readUTF();
					ClientData clientData = new ClientData();
					if (clientData.checkUser(name)){
						sendMSG("Usertontai");
					}
					else {
						sendMSG("Userhople");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			if (request.equals("SignUp")){
				try {
					String user = is.readUTF();
					String pw = is.readUTF();
					String name = is.readUTF();
					String phone = is.readUTF();
					String address = is.readUTF();
					ClientData clientData = new ClientData();
					MD5Library x = new MD5Library();
					String pwMD5 = x.md5(pw);
					clientData.addUser(new User(user, pwMD5, name, phone, address));
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
			
			while (active) {
				try {
					request = is.readUTF();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				if (request.equals("ChangePw")){
					try {
						String name = is.readUTF();
						String pw1 = is.readUTF();
						String pw2 = is.readUTF();
						String pw3 = is.readUTF();
						ClientData clientData = new ClientData();
						if (clientData.checkLogIn(name, pw1)){
							if (pw2.equals(pw3)){
								clientData.updatePw(name, pw2);
								sendMSG("pwok");
							}
							else{
								sendMSG("pwxacnhansai");
							}							
						}
					} catch (IOException e) {
						e.printStackTrace();
					}	
				}
				if (request.equals("loadTable")){
					try {
						String table = is.readUTF();
						FoodData getData = new FoodData();
						getData.getData(table);
						try {
							while (getData.rs.next()){
								sendMSG(getData.rs.getString(1));
								sendMSG(getData.rs.getString(2));
								sendMSG(getData.rs.getString(3));
								sendMSG(getData.rs.getString(4));
							}
							sendMSG("hethang");
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if (request.equals("checkId")){
					String id;
					try {
						id = is.readUTF();
						String table = is.readUTF();
						FoodData foodData = new FoodData();
						if (foodData.checkFood(Integer.parseInt(id), table)){
							sendMSG("Idhople");
							GetDataById getDataById = new GetDataById();
							sendMSG(getDataById.getName(Integer.parseInt(id), table));
						}
						else {
							sendMSG("Idsai");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}				
				}
				
				if (request.equals("Muahang")){
					try {
						String user = is.readUTF();
						String id = is.readUTF();
						String table = is.readUTF();
						String sl = is.readUTF();
						String tien = is.readUTF();
						String mota = is.readUTF();
						BuyRequest buyRequest = new BuyRequest(user, id, table, sl, tien, mota);
						buyRequest.frame.setVisible(true);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (request.equals("exit")){
					active = false;
//					try {
//						close();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				}
			}			
			active = false;
		}
	}
	
	public void sendMSG(String data) throws IOException{
		os.writeUTF(data);
	}
	
	public void close() throws IOException{
		is.close();
		os.close();
		socketOfServer.close();
	}
}
