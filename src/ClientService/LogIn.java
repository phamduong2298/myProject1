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

import Client.Client;

public class LogIn {

	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private static Client client;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn window = new LogIn(null);
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
	public LogIn(Client client) {
		this.client = client;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.PINK);
		frame.setBounds(100, 100, 386, 363);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTnngNhp = new JLabel("T\u00EAn \u0111\u0103ng nh\u1EADp");
		lblTnngNhp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTnngNhp.setBounds(33, 123, 106, 22);
		frame.getContentPane().add(lblTnngNhp);
		
		textField = new JTextField();
		textField.setBounds(149, 125, 189, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblMtKhu = new JLabel("M\u1EADt kh\u1EA9u");
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMtKhu.setBounds(33, 175, 106, 22);
		frame.getContentPane().add(lblMtKhu);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(148, 177, 190, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btn1 = new JButton("\u0110\u0103ng nh\u1EADp");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = textField.getText();
				String pw = passwordField.getText();
				if (name.equals("")||pw.equals("")){
					tryagain();
				}
				else {
					try {
						client.sendMSG("login");
						client.requestLogin(name, pw);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
					try {
						if (client.getMSG().equals("LogInSucc")){
							ClientChoose clientChoose = new ClientChoose(name,client);
							clientChoose.frame.setVisible(true);
							frame.setVisible(false);
						}
						else {
							tryagain();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btn1.setBounds(60, 232, 97, 23);
		frame.getContentPane().add(btn1);
		
		JButton btn2 = new JButton("\u0110\u0103ng k\u00FD");
		btn2.setBounds(225, 232, 97, 23);
		frame.getContentPane().add(btn2);
		btn2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				SignUp signUp = new SignUp(client);
				signUp.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		JLabel lblChnngK = new JLabel("Ch\u1ECDn \u0111\u0103ng k\u00FD n\u1EBFu b\u1EA1n ch\u01B0a c\u00F3 t\u00E0i kho\u1EA3n");
		lblChnngK.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblChnngK.setBounds(58, 300, 256, 14);
		frame.getContentPane().add(lblChnngK);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				try {
//					client.sendMSG("exit");
//					client.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				frame.dispose();
			}
		});
		btnExit.setBounds(286, 296, 61, 23);
		frame.getContentPane().add(btnExit);
	}
	
	public void tryagain(){
		JOptionPane.showMessageDialog(null, "Tên hoặc mật khẩu chưa chính xác");
		textField.setText("");
		passwordField.setText("");
	}
}
