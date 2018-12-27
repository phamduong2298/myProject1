package connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;


public class FoodData {
	public ResultSet rs;
	public ConnectDB connectDB = new ConnectDB();
	
	public FoodData(){
		connectDB.connect();
	}
	
	public DefaultTableModel tableModel(){
		DefaultTableModel model = new DefaultTableModel();
		try {
			ResultSetMetaData rsMD = rs.getMetaData();
			int colNumber = rsMD.getColumnCount();
			String arr[] = new String[colNumber];
			String a[] = {"ID","Tên","Giá","Số lượng"}; 
			model.setColumnIdentifiers(a);
			while (rs.next()){
				for (int i=0;i<colNumber;i++){
					arr[i]=rs.getString(i+1);
				}
				model.addRow(arr);
			}
		} catch (Exception e) {
				e.printStackTrace();
				}
		return model;
	}
	
	public void getData(String table){
		String sqlCommand="select * from " + table;
		Statement st = null;
		try {
			st=connectDB.connection.createStatement();
			rs=st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void delete(String id, String table){
		String sqlCommand = "delete from "+ table +" where id = ?";
		PreparedStatement pst = null;
		try {
			pst = connectDB.connection.prepareStatement(sqlCommand);
			pst.setString(1, id);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addFood(String id,String name,String price,String num,String table){
		String sqlCommand = "Insert into "+table+" value (?,?,?,?) ";
		PreparedStatement pst = null;
		try {
			pst = connectDB.connection.prepareStatement(sqlCommand);
			pst.setString(1, id);
			pst.setString(2, name);
			pst.setString(3, price);
			pst.setString(4, num);
			pst.executeUpdate();
			System.out.println("added");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(String id,String name,String price,String num,String table){
		String sqlCommand = "update " + table + " set name = ? , price = ? , num = ?  where id = ? ";
		PreparedStatement pst = null;
		try {
			pst = connectDB.connection.prepareStatement(sqlCommand);
			pst.setString(1, name);
			pst.setString(2, price);
			pst.setString(3, num);
			pst.setString(4, id);
			pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public boolean checkFood(int id, String table){
		boolean check = false;
		ResultSet rs = null;
		String sqlCommand = "select * from " + table + " where id = ?";
		PreparedStatement pst = null;
		try {
			pst = connectDB.connection.prepareStatement(sqlCommand);
			pst.setString(1, String.valueOf(id));
			rs = pst.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (rs.next()){
				check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
	
	public int getNum(String id, String table){
		String sqlCommand = "select num from "+ table +" where id = " + id;
		Statement st = null;
		int num=0;
		try {
			st = connectDB.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
			rs.next();
			num = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return num;
	}
	
	public void updateNum(String id, String table, String num){
		String sqlCommand = "update "+table+" set num =? where id=?";
		PreparedStatement pst =null;
		try {
			pst=connectDB.connection.prepareStatement(sqlCommand);
			pst.setString(1, num);
			pst.setString(2, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void showData(ResultSet rs){
		try {
			while (rs.next()){
				System.out.printf("%-5s %-20s %-6d %-6d \n" , rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
	}
	public static void main(String args[]){
		FoodData getData = new FoodData();
//		getData.getData("nuocdongchai");
//		getData.showData(getData.rs);
		getData.updateNum("3", "mi", "15");
		System.out.println(getData.getNum("3", "mi"));
	} 
}
