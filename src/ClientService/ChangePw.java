package ClientService;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JButton;

import Client.Client;
import connect.ClientData;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ChangePw {

	public JFrame frame;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private String name;
	private Client client;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePw window = new ChangePw("",null);
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
	public ChangePw(String name, Client client) {
		this.client = client;
		this.name = name;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 387, 205);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMkC = new JLabel("Mk c\u0169");
		lblMkC.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMkC.setBounds(23, 28, 61, 23);
		frame.getContentPane().add(lblMkC);
		
		JLabel lblMkMi = new JLabel("Mk m\u1EDBi");
		lblMkMi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMkMi.setBounds(21, 62, 63, 18);
		frame.getContentPane().add(lblMkMi);
		
		JLabel lblXcNhnMk = new JLabel("X\u00E1c nh\u1EADn mk m\u1EDBi");
		lblXcNhnMk.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblXcNhnMk.setBounds(23, 91, 105, 23);
		frame.getContentPane().add(lblXcNhnMk);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(145, 30, 184, 20);
		frame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(145, 62, 184, 20);
		frame.getContentPane().add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(145, 93, 184, 20);
		frame.getContentPane().add(passwordField_2);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String pw1 = passwordField.getText();
					String pw2 = passwordField_1.getText();
					String pw3 = passwordField_2.getText();
					if (pw1.equals("")||pw2.equals("")||pw1.equals("")){
						JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin!");
					}
					else {
						client.sendMSG("ChangePw");
						client.requestChangePw(name, pw1, pw2,pw3);
						String reply = client.getMSG();
						if (reply.equals("pwok")){
								frame.dispose();
							}
							else
							if (reply.equals("pwxacnhansai"))
							{
								JOptionPane.showMessageDialog(null, "Xác nhận mk chưa đúng");
								passwordField_1.setText("");
								passwordField_2.setText("");
							}
							else
							{
							JOptionPane.showMessageDialog(null, "Mk cũ chưa đúng");
							}
					}				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnOk.setBounds(155, 133, 89, 23);
		frame.getContentPane().add(btnOk);
	}
}
