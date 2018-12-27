package connect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetDataById {
	private ConnectDB connectDB = new ConnectDB();
	
	public GetDataById(){
		connectDB.connect();
	}
	
	public int maxId(String table){
		ResultSet rs = null;
		int id = 0;
		String sqlCommand = "select id from " + table;
		Statement st = null;
		int max = 1;
		try {
			st = connectDB.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()){
				id = rs.getInt(1);
				if (id>max) max = id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return max;
	}
	
	public String getName(int id, String table){
		ResultSet rs = null;
		String name = null;
		String sqlCommand = "select name from " + table +" where id = "+ Integer.toString(id);
		Statement st = null;
		try {
			st = connectDB.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()){
				name = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	public int getPrice(int id, String table){
		ResultSet rs = null;
		int price = 0;
		String sqlCommand = "select price from " + table +" where id = "+ Integer.toString(id);
		Statement st = null;
		try {
			st = connectDB.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()){
				price = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return price;
	}
	
	public int getNum(int id, String table){
		ResultSet rs = null;
		int num = 0;
		String sqlCommand = "select num from " + table +" where id = "+ Integer.toString(id);
		Statement st = null;
		try {
			st = connectDB.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()){
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static void main(String args[]){
		GetDataById a = new GetDataById();
		System.out.printf("%d", a.maxId("mi"));
	}
}
