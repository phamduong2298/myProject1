package ClientService;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import md5.MD5Library;
import Client.Client;
import connect.ClientData;
import connect.ConnectDB;
import connect.User;

public class SignUp implements ActionListener {

	private ConnectDB connectDB;
	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnDK;
	private JButton btnH;
	private JButton btnCheck;
	private JLabel lblTest;
	private Client client;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp(null);
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
	public SignUp(Client client) {
		this.client = client;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.PINK);
		frame.setBounds(100, 100, 425, 459);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Qu\u00FD kh\u00E1ch vui l\u00F2ng nh\u1EADp \u0111\u1EA7y \u0111\u1EE7 th\u00F4ng tin!");
		lblNewLabel.setBounds(10, 19, 300, 24);
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		
		JLabel lblTnngNhp = new JLabel("T\u00EAn \u0111\u0103ng nh\u1EADp");
		lblTnngNhp.setBounds(31, 57, 106, 14);
		
		textField = new JTextField();
		textField.setBounds(149, 54, 180, 20);
		textField.setColumns(10);
		
		JLabel lblMtKhu = new JLabel("M\u1EADt kh\u1EA9u");
		lblMtKhu.setBounds(31, 116, 61, 14);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(149, 113, 180, 20);
		
		JLabel lblXcNhnMt = new JLabel("X\u00E1c nh\u1EADn m\u1EADt kh\u1EA9u");
		lblXcNhnMt.setBounds(31, 161, 131, 14);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(149, 158, 180, 21);
		
		JLabel lblHTn = new JLabel("H\u1ECD t\u00EAn");
		lblHTn.setBounds(31, 207, 61, 14);
		
		JLabel lblSinThoi = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i");
		lblSinThoi.setBounds(31, 255, 95, 14);
		
		JLabel lblaCh = new JLabel("\u0110\u1ECBa ch\u1EC9");
		lblaCh.setBounds(31, 301, 61, 14);
		
		textField_1 = new JTextField();
		textField_1.setBounds(149, 204, 180, 20);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(149, 252, 180, 20);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(149, 298, 180, 20);
		textField_3.setColumns(10);
		
		btnCheck = new JButton("Check");
		btnCheck.setBounds(335, 56, 61, 19);
		btnCheck.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnCheck.addActionListener(this);
		
		btnDK = new JButton("\u0110\u0103ng k\u00FD");
		btnDK.setBounds(64, 351, 98, 23);
		btnDK.addActionListener(this);
		
		btnH = new JButton("Trở lại");
		btnH.addActionListener(this);
		btnH.setBounds(276, 351, 95, 23);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnDK);
		frame.getContentPane().add(btnH);
		frame.getContentPane().add(lblNewLabel);
		frame.getContentPane().add(lblTnngNhp);
		frame.getContentPane().add(lblMtKhu);
		frame.getContentPane().add(lblXcNhnMt);
		frame.getContentPane().add(lblHTn);
		frame.getContentPane().add(lblaCh);
		frame.getContentPane().add(lblSinThoi);
		frame.getContentPane().add(textField_3);
		frame.getContentPane().add(textField_2);
		frame.getContentPane().add(textField_1);
		frame.getContentPane().add(passwordField_1);
		frame.getContentPane().add(passwordField);
		frame.getContentPane().add(textField);
		frame.getContentPane().add(btnCheck);
		
		lblTest = new JLabel("");
		lblTest.setForeground(Color.BLACK);
		lblTest.setBounds(149, 82, 233, 14);
		frame.getContentPane().add(lblTest);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCheck){
			if (textField.getText().equals("")){
				lblTest.setText("Vui lòng nhập tên đăng nhập!");
			}
			else {
				try {
					client.sendMSG("CheckUser");
					client.sendMSG(textField.getText());
					String check = client.getMSG();
					if (check.equals("Usertontai")){
						lblTest.setText("Tên đăng nhập đã tồn tại. Vui lòng thử lại!");
					}
					else {
						lblTest.setText("Tên đăng nhập hợp lệ");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource() == btnDK){
			if (textField.getText().equals("")||passwordField.getText().equals("")||passwordField_1.getText().equals("")
					||textField_1.getText().equals("")
					||textField_2.getText().equals("")||textField_3.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Nhập đây đủ thông tin!");
			}
			else
			try {
				client.sendMSG("SignUp");
				if (passwordField.getText().equals(passwordField_1.getText())){
					client.sendUser(textField.getText(), passwordField.getText(), textField_1.getText(),
							textField_2.getText(), textField_3.getText());
					JOptionPane.showMessageDialog(null, "Đăng ký thành công");
				}
				else {
					JOptionPane.showMessageDialog(null, "Xác nhận mk sai");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}
		
		if (e.getSource() == btnH){
			LogIn logIn = new LogIn(client);
			logIn.frame.setVisible(true);
			frame.setVisible(false);
		}
	}
}
