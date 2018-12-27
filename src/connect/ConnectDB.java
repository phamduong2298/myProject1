package connect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	private final String url="jdbc:mysql://127.0.0.1:3306/food";
	private final String className="com.mysql.jdbc.Driver";
	private final String user = "root";
	private final String password = "2121998";
	public Connection connection;
	
	public void connect(){
		try {
			Class.forName(className);
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("ok");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Class not found");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connect Error");
		}
	}
	
	public static void main(String args[]){
		ConnectDB con = new ConnectDB();
		con.connect();
	}
}
