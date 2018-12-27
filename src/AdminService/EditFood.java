package AdminService;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

import connect.FoodData;

public class EditFood implements ActionListener {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel label;
	private JButton btnOk;
	private String tableSelected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditFood window = new EditFood("");
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
	public EditFood(String tableSelected) {
		initialize();
		this.tableSelected = tableSelected;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 331);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblS = new JLabel("S\u1EEDa th\u00F4ng tin s\u1EA3n ph\u1EA9m");
		lblS.setFont(new Font("Times New Roman", Font.ITALIC, 22));
		lblS.setBounds(49, 11, 259, 37);
		frame.getContentPane().add(lblS);
		
		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(44, 69, 46, 14);
		frame.getContentPane().add(lblId);
		
		label = new JLabel("");
		label.setBounds(178, 69, 118, 14);
		frame.getContentPane().add(label);
		
		JLabel lblTn = new JLabel("T\u00EAn ");
		lblTn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTn.setBounds(44, 108, 46, 14);
		frame.getContentPane().add(lblTn);
		
		textField = new JTextField();
		textField.setBounds(178, 105, 150, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblGi = new JLabel("Gi\u00E1");
		lblGi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGi.setBounds(44, 153, 46, 14);
		frame.getContentPane().add(lblGi);
		
		textField_1 = new JTextField();
		textField_1.setBounds(178, 150, 150, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(178, 202, 150, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblCnLi = new JLabel("C\u00F2n l\u1EA1i");
		lblCnLi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCnLi.setBounds(44, 205, 46, 17);
		frame.getContentPane().add(lblCnLi);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(154, 248, 89, 23);
		frame.getContentPane().add(btnOk);
		btnOk.addActionListener(this);
	}
	
	public void setDefault(String id, String name, String price, String num){
		label.setText(id);
		textField.setText(name);
		textField_1.setText(price);
		textField_2.setText(num);
	}
	
	public void update(){
		FoodData foodData = new FoodData();
		String id = label.getText();
		String name = textField.getText();
		String price = textField_1.getText();
		String num = textField_2.getText();
		foodData.update(id, name, price, num, tableSelected);
		JOptionPane.showMessageDialog(null, "Thay đổi thành công");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnOk){
			update();
			frame.dispose();
			FoodManage foodManage = new FoodManage(tableSelected);
			foodManage.loadTable();
			foodManage.frame.setVisible(true);
		}
	}
	
	
}
