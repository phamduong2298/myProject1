package AdminService;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connect.BuyData;
import connect.FoodData;
import connect.GetDataById;

public class BillManage implements ActionListener {

	public JFrame frame;
	private JTextField textField;
	private String month; 
	private JTable table;
	private JButton btnTmNgiDng;
	private JTextField textField_1;
	private JButton btnTim;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillManage window = new BillManage();
					window.loadTable("duong988",3);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BillManage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(189, 183, 107));
		frame.setBounds(100, 100, 659, 434);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblQunLn = new JLabel("Qu\u1EA3n l\u00FD \u0111\u01A1n h\u00E0ng");
		lblQunLn.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblQunLn.setBounds(34, 11, 177, 44);
		frame.getContentPane().add(lblQunLn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 93, 576, 292);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setBounds(443, 62, 157, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnTmNgiDng = new JButton("T\u00ECm ng\u01B0\u1EDDi d\u00F9ng");
		btnTmNgiDng.setBounds(294, 59, 125, 23);
		frame.getContentPane().add(btnTmNgiDng);
		
		textField_1 = new JTextField();
		textField_1.setBounds(175, 62, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		btnTim = new JButton("Tìm tháng");
		btnTim.addActionListener(this);
		btnTim.setBounds(58, 61, 97, 23);
		frame.getContentPane().add(btnTim);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		btnBack.setBounds(547, 11, 74, 23);
		frame.getContentPane().add(btnBack);
		btnTmNgiDng.addActionListener(this);
	}
	
	public void loadTable(String str, int x){
		int tong=0;
		BuyData buyData = new BuyData();
		switch (x) {
		case 1:buyData.getDataMonth(str);	
			break;
		case 2:buyData.getDataUser(str);
			break;
		case 3:buyData.getData();
		}
		GetDataById getDataById = new GetDataById();
		DefaultTableModel tableModel = new DefaultTableModel();
		try {
			int colNumber = 5;
			String arr[] = new String[colNumber];
			String a[] = {"Tên tài khoản","Tên sản phẩm","Số lượng","Thành tiền","Ngày"}; 
			tableModel.setColumnIdentifiers(a);
			while (buyData.rs.next()){
				arr[0]=buyData.rs.getString(2);
				arr[2]=Integer.toString(buyData.rs.getInt(5));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(buyData.rs.getDate(6));
				arr[4]=date;
				arr[1]=getDataById.getName(Integer.parseInt(buyData.rs.getString(3)), buyData.rs.getString(4));
				int tien = getDataById.getPrice(Integer.parseInt(buyData.rs.getString(3)),buyData.rs.getString(4))
						*buyData.rs.getInt(5);
				arr[3]=Integer.toString(getDataById.getPrice(Integer.parseInt(buyData.rs.getString(3)),buyData.rs.getString(4))
						*buyData.rs.getInt(5));
				tong = tong + tien;
				tableModel.addRow(arr);
			}
			arr[0]="";
			arr[1]="";
			arr[2]="Tổng tiền";
			arr[3]=Integer.toString(tong)+"000 đồng";
			arr[4]="";
			tableModel.addRow(arr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.setModel(tableModel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnTmNgiDng){
			loadTable(textField.getText(), 2);
			textField_1.setText("");
		}
		if (e.getSource()==btnTim){
			textField.setText("");
			month = textField_1.getText();
			int m = Integer.parseInt(month);
			if (m>=1&&m<=12) {
				loadTable(month, 1);
			}
			else {
				JOptionPane.showMessageDialog(null, "Tháng không hợp lệ");
			}
		}
		if (e.getSource() == btnBack){
			frame.dispose();
		}
	}
	
}
