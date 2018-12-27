package ClientService;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Client.Client;
import connect.FoodData;


public class ClientChoose implements ActionListener {

	public JFrame frame;
	private int cbb;
	private JComboBox comboBox = new JComboBox();
	private JButton btnOk;
	private JButton btnExit;
	private JButton btnMk;
	private JTable table;
	private JTextField textField;
	private String tableSelected = "nuocdongchai";
	private int idSelected = 0;
	private String name;
	private Client client;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientChoose window = new ClientChoose("Duong988",null);
					window.loadTable();
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
	public ClientChoose(String str, Client client) {
		this.client = client;
		this.name = str;
		initialize();
		try {
			loadTable();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.setBounds(100, 100, 447, 570);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 431, 47);
		comboBox.setBounds(23, 75, 153, 20);
		comboBox.addActionListener(this);
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u0110\u1ED3 u\u1ED1ng \u0111\u00F3ng chai", "M\u00EC, ph\u1EDF"}));
		
		btnOk = new JButton("Đặt hàng");
		btnOk.setBounds(85, 471, 91, 23);
		btnOk.addActionListener(this);
		btnExit = new JButton("Thoát");
		btnExit.setBounds(265, 471, 84, 23);
		btnExit.addActionListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 113, 330, 313);
		
		JTextArea txtrQuKhchVui = new JTextArea();
		txtrQuKhchVui.setBounds(75, 437, 210, 21);
		txtrQuKhchVui.setBackground(Color.WHITE);
		txtrQuKhchVui.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		txtrQuKhchVui.setText("Quý khách vui lòng nhập ID để đặt hàng");
		
		table = new JTable();
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		table.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblNewLabel);
		frame.getContentPane().add(btnOk);
		frame.getContentPane().add(btnExit);
		frame.getContentPane().add(comboBox);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().add(txtrQuKhchVui);
		
		textField = new JTextField();
		textField.setBackground(SystemColor.textHighlight);
		textField.setForeground(SystemColor.activeCaptionText);
		textField.setBounds(283, 437, 49, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnMk = new JButton("Đổi mật khẩu");
		btnMk.setBounds(293, 24, 128, 23);
		frame.getContentPane().add(btnMk);
		btnMk.addActionListener(this);
		
	}
	
	public void loadTable() throws IOException{
		client.sendMSG("loadTable");
		client.sendMSG(tableSelected);
		DefaultTableModel tableModel = new DefaultTableModel();
		String arr[] = new String[4];
		String a[] = {"ID","Tên","Giá","Số lượng"}; 
		tableModel.setColumnIdentifiers(a);
		String reply = client.getMSG();
			while (!reply.equals("hethang")){
				for (int i=0;i<4;i++){
					arr[i]=reply;
					reply = client.getMSG();
				}
				tableModel.addRow(arr);
			}
		table.setModel(tableModel);
	}
	
	public boolean checkId(int id){
		boolean check = false;
		try {
			client.sendMSG("checkId");
			client.sendMSG(Integer.toString(id));
			client.sendMSG(tableSelected);
			if (client.getMSG().equals("Idhople")){
			check = true;
			}
			else {
				check = false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == comboBox){
			cbb = comboBox.getSelectedIndex();
			switch (cbb) {
				case 0:tableSelected = "nuocdongchai";break;
				case 1:tableSelected = "mi";break;
			}
			try {
				loadTable();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == btnOk){
			idSelected =Integer.parseInt(textField.getText());
			if (checkId(idSelected)){
				Confirm fr = new Confirm(idSelected, tableSelected, client, name);
				fr.frame.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "ID lựa chọn không phù hợp. Xin hãy thử lại!");
			}
		}
		
		if (e.getSource() == btnExit){
			LogIn logIn = new LogIn(client);
			logIn.frame.setVisible(true);
			try {
				client.sendMSG("exit");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			frame.dispose();
		}
		if (e.getSource() == btnMk ){
			ChangePw changePw = new ChangePw(name,client);
			changePw.frame.setVisible(true);
		}
	}
}
