package AdminService;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import connect.FoodData;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFood {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnOk;
	private String tableSelected;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddFood window = new AddFood("nuocdongchai");
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
	public AddFood(String tableSelected) {
		this.tableSelected = tableSelected;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.CYAN);
		frame.setBounds(100, 100, 470, 365);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nh\u1EADp th\u00F4ng tin s\u1EA3n ph\u1EA9m");
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblNewLabel.setBounds(41, 11, 230, 39);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblId.setBounds(66, 72, 46, 14);
		frame.getContentPane().add(lblId);
		
		JLabel lblTn = new JLabel("T\u00EAn");
		lblTn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTn.setBounds(66, 123, 46, 14);
		frame.getContentPane().add(lblTn);
		
		JLabel lblGi = new JLabel("Gi\u00E1");
		lblGi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGi.setBounds(66, 178, 46, 14);
		frame.getContentPane().add(lblGi);
		
		JLabel lblCnLi = new JLabel("C\u00F2n l\u1EA1i");
		lblCnLi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCnLi.setBounds(66, 229, 64, 19);
		frame.getContentPane().add(lblCnLi);
		
		textField = new JTextField();
		textField.setBounds(183, 71, 189, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(183, 122, 189, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(183, 177, 189, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(183, 230, 189, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(78, 293, 89, 23);
		frame.getContentPane().add(btnOk);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FoodManage foodManage = new FoodManage(tableSelected);
				foodManage.loadTable();
				frame.dispose();
				foodManage.frame.setVisible(true);
			}
		});
		btnCancel.setBounds(259, 293, 89, 23);
		frame.getContentPane().add(btnCancel);
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = textField.getText();
				String name = textField_1.getText();
				String price = textField_2.getText();
				String num = textField_3.getText();
				if (id.equals("")||name.equals("")||price.equals("")||num.equals("")){
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
				}
				else {
					FoodData foodData = new FoodData();
					if (foodData.checkFood(Integer.parseInt(id), tableSelected)==false){
						foodData.addFood(id, name, price, num, tableSelected);
						FoodManage foodManage = new FoodManage(tableSelected);
						foodManage.loadTable();
						frame.dispose();
						foodManage.frame.setVisible(true);
					}
					else
						JOptionPane.showMessageDialog(null, "ID đã tồn tại!");
				}
			}
		});
	}
}
