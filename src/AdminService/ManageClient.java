package AdminService;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import Server.Admin;
import connect.ClientData;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ManageClient implements ActionListener {

	public JFrame frame;
	private JTable table;
	private JTextField textField;
	private JButton btnTmKim; 
	private JButton btnXaTiKhon;
	private JButton btnExit;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageClient window = new ManageClient();
					window.searchUser();;
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
	public ManageClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.YELLOW);
		frame.setBounds(100, 100, 715, 453);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 103, 644, 301);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setBounds(82, 72, 216, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnTmKim = new JButton("T\u00ECm ki\u1EBFm");
		btnTmKim.addActionListener(this); 
		btnTmKim.setBounds(325, 69, 89, 23);
		frame.getContentPane().add(btnTmKim);
		
		btnXaTiKhon = new JButton("X\u00F3a t\u00E0i kho\u1EA3n");
		btnXaTiKhon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnXaTiKhon.setBounds(448, 69, 120, 23);
		frame.getContentPane().add(btnXaTiKhon);
		btnXaTiKhon.addActionListener(this);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(578, 11, 89, 23);
		frame.getContentPane().add(btnExit);
		btnExit.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("Qu\u1EA3n l\u00FD kh\u00E1ch h\u00E0ng");
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 26));
		lblNewLabel.setBounds(73, 11, 255, 50);
		frame.getContentPane().add(lblNewLabel);
	}
		
	public void searchUser(){
		String str = textField.getText();
		ClientData clientData = new ClientData();
		clientData.searchAcc(str);
		DefaultTableModel tableModel = new DefaultTableModel();
		try {
			int colNumber = 4;
			String arr[] = new String[colNumber];
			String a[] = {"Tên tài khoản","Họ tên","Sđt","Địa chỉ"}; 
			tableModel.setColumnIdentifiers(a);
			while (clientData.rs.next()){
				for (int i=0;i<colNumber;i++){
					arr[i]=clientData.rs.getString(i+1);
				}
				tableModel.addRow(arr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.setModel(tableModel);
	}
	
	public void deleteUser(){
		int row = table.getSelectedRow();
		if (row<0){
			JOptionPane.showMessageDialog(null, "Chon hang de xoa!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int select = JOptionPane.showOptionDialog(null, "Are you sure?", null, 0, 
													JOptionPane.YES_NO_OPTION, null, null, 1);
		if (select==0){
			ClientData clientData = new ClientData();
			clientData.deleteData((String) table.getValueAt(row, 0));
			searchUser();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnTmKim){
			searchUser();
		}
		if (e.getSource() == btnXaTiKhon){
			deleteUser();
		}
		if (e.getSource() == btnExit){
			frame.dispose();
			Admin server =new Admin();
			server.frame.setVisible(true);
		}
	}

}
