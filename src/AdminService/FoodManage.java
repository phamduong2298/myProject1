package AdminService;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import AdminService.AddFood;
import Server.Admin;
import connect.FoodData;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.ImageIcon;

public class FoodManage implements ActionListener {

	public JFrame frame;
	private JTable table;
	private JComboBox comboBox;
	private int cbb =0;
	private String tableSelected = "nuocdongchai" ;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnAdd;
	private boolean checkUpdate;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoodManage window = new FoodManage("nuocdongchai");
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
	public FoodManage(String tableSelected) {
		initialize();
		this.tableSelected = tableSelected;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(50, 205, 50));
		frame.setBounds(100, 100, 663, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 60, 354, 361);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		comboBox = new JComboBox();
		comboBox.setForeground(Color.BLACK);
		comboBox.setBackground(Color.WHITE);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u0110\u1ED3 u\u1ED1ng \u0111\u00F3ng chai", "M\u00EC, ph\u1EDF"}));
		comboBox.setBounds(451, 75, 138, 27);
		frame.getContentPane().add(comboBox);
		comboBox.addActionListener(this);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(451, 148, 89, 23);
		frame.getContentPane().add(btnDelete);
		btnDelete.addActionListener(this);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(451, 221, 89, 23);
		frame.getContentPane().add(btnEdit);
		btnEdit.addActionListener(this);
		
		JLabel lblQunLHng = new JLabel("Qu\u1EA3n l\u00FD h\u00E0ng");
		lblQunLHng.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		lblQunLHng.setBounds(26, 11, 280, 38);
		frame.getContentPane().add(lblQunLHng);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(this);
		btnAdd.setBounds(451, 292, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(451, 359, 89, 23);
		frame.getContentPane().add(btnBack);
		btnBack.addActionListener(this);
	}
	
	public void loadTable(){
		FoodData getData = new FoodData();
		getData.getData(tableSelected);
		table.setModel(getData.tableModel());
		switch (tableSelected) {
		case "nuocdongchai":comboBox.setSelectedIndex(0);break;
		case "mi":comboBox.setSelectedIndex(1);break;
		}
	}
	
	public void delete(){
		int row = table.getSelectedRow();
		if (row<0){
			JOptionPane.showMessageDialog(null, "Ch�?n hàng để xóa!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int select = JOptionPane.showOptionDialog(null, "Are you sure?", null, 0, 
													JOptionPane.YES_NO_OPTION, null, null, 1);
		if (select==0){
			FoodData getData= new FoodData();
			getData.delete((String) table.getValueAt(row, 0), tableSelected);
			loadTable();
		}
	}
	
	public void edit(){
		int row = table.getSelectedRow();
		EditFood editFood = new EditFood(tableSelected);
		editFood.setDefault((String) table.getValueAt(row, 0),(String) table.getValueAt(row, 1),
				(String) table.getValueAt(row, 2),(String) table.getValueAt(row, 3));
		editFood.frame.setVisible(true);
		return;
	}

	public void add(){
		AddFood addFood = new AddFood(tableSelected);
		addFood.frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == comboBox){
			cbb = comboBox.getSelectedIndex();
			switch (cbb) {
				case 0:tableSelected = "nuocdongchai";break;
				case 1:tableSelected = "mi";break;
			}
			loadTable();
		}
		if (e.getSource() == btnDelete){
			delete();
		}
		if (e.getSource() == btnEdit){
			frame.setVisible(false);
			edit();
		}
		if (e.getSource() == btnAdd){
			frame.setVisible(false);
			add();
		}
		if (e.getSource() == btnBack){
			frame.dispose();
			Admin server =new Admin();
			server.frame.setVisible(true);
		}
	}
}
