package Server;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JButton;

import AdminService.BillManage;
import AdminService.FoodManage;
import AdminService.ManageClient;

public class Admin implements ActionListener {
	
	public JFrame frame;
	private JButton btnNewButton;
	private JButton btnQunLKhch;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin();
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
	public Admin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 414, 245);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnNewButton = new JButton("Qu\u1EA3n l\u00FD s\u1EA3n ph\u1EA9m");
		btnNewButton.setBounds(79, 28, 232, 23);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		btnQunLKhch = new JButton("Qu\u1EA3n l\u00FD kh\u00E1ch h\u00E0ng");
		btnQunLKhch.setBounds(79, 73, 232, 23);
		frame.getContentPane().add(btnQunLKhch);
		
		btnNewButton_1 = new JButton("Qu\u1EA3n l\u00FD \u0111\u01A1n h\u00E0ng");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(79, 118, 232, 23);
		frame.getContentPane().add(btnNewButton_1);
		btnQunLKhch.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnNewButton){
			FoodManage foodManage =new FoodManage("nuocdongchai");
			foodManage.loadTable();
			foodManage.frame.setVisible(true);
		}
		if (e.getSource() == btnQunLKhch){
			ManageClient manageClient =new ManageClient();
			manageClient.searchUser();
			manageClient.frame.setVisible(true);
		}
		if (e.getSource()==btnNewButton_1){
			BillManage billManage = new BillManage();
			billManage.loadTable("", 3);
			billManage.frame.setVisible(true);
		}
	}

}
