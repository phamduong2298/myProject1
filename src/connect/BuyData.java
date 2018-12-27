package connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class BuyData {
	private ConnectDB connectDB;
	public ResultSet rs;
	
	public BuyData() {
		connectDB = new ConnectDB();
		connectDB.connect();
	}
	
	public void getData(){
		String sqlCommand = "select * from mua";
		Statement st = null;
		try {
			st = connectDB.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getDataMonth(String m){
		String sqlCommand = "select * from mua where date like \"%-"+m+"-%\"";
		PreparedStatement pst = null;
		try {
			pst = connectDB.connection.prepareStatement(sqlCommand);
			rs = pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addData(String nameUser, String idSp, String table, int sl, String date){
		String sqlCommand = "insert into mua (`nameUser`,`idSp`,`table`,`sl`,`date`) value (?,?,?,?,?)";
		PreparedStatement pst = null;
		try {
			pst = connectDB.connection.prepareStatement(sqlCommand);
			pst.setString(1, nameUser);
			pst.setString(2,idSp);
			pst.setString(3, table);
			pst.setString(4, Integer.toString(sl));
			pst.setString(5, date);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getDataUser(String username){
		String sqlCommand = "select * from mua where nameUser = ? ";
		PreparedStatement pst = null;
		try {
			pst = connectDB.connection.prepareStatement(sqlCommand);
			pst.setString(1, username);
			rs = pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void showData(ResultSet rs){
		try {
			while (rs.next()){
				System.out.printf("%-5d %-20s %-5s %-20s %-6d %-10s\n" , rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
	}
	
	public static void main(String[] args) {
		BuyData buyData = new BuyData();
//		Date date1 = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String date = sdf.format(date1);
//		buyData.addData("duong988", "3", "mi", 1, date);
//		buyData.getData();
		buyData.getDataMonth("10");
		buyData.showData(buyData.rs);
	}
}
