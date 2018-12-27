package ClientService;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import Client.Client;
import connect.FoodData;
import connect.GetDataById;

import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Confirm extends JFrame{

	public JFrame frame;
	JButton btnCancel = new JButton("Cancel");
	GetDataById getData = new GetDataById();
	private int id;
	private String table;
	private JTextField txtng;
	private Client client;
	private String name;
	private String nameSp;
	private JSpinner spinner;
	private JTextPane textPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Confirm window = new Confirm();
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
	public Confirm(){
		initialize();
	}
	
	public Confirm(int idSelected, String tableSelected, Client client, String name) {
		this.id=idSelected;
		this.table = tableSelected;
		this.client = client;
		this.name = name;
		try {
			this.nameSp = client.getMSG();
		} catch (IOException e) {
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 14));
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 630, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		btnCancel.setForeground(Color.BLACK);
	
		btnCancel.setBounds(364, 212, 89, 23);
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnCancel);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(136, 212, 89, 23);
		frame.getContentPane().add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					client.sendMSG("Muahang");
					client.sendMSG(name);
					client.sendMSG(Integer.toString(id));
					client.sendMSG(table);
					client.sendMSG(Integer.toString((int) spinner.getValue()));
					client.sendMSG(txtng.getText());
					client.sendMSG(textPane.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				frame.dispose();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 109, 508, 92);
		frame.getContentPane().add(scrollPane);
		
		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		String str = "Bạn đã chọn " + nameSp;
		JLabel lblNewLabel = new JLabel(str);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setBounds(38, 11, 415, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblSo = new JLabel("S\u1ED1 l\u01B0\u1EE3ng");
		lblSo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSo.setBounds(88, 44, 121, 23);
		frame.getContentPane().add(lblSo);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 50, 1));
		spinner.setBounds(154, 46, 29, 20);
		spinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int x = (int) spinner.getValue();
				x = x * getData.getPrice(id,table)*1000;
				txtng.setText(String.valueOf(x) + " đồng");
			}
		});;	
		frame.getContentPane().add(spinner);
		
		JLabel lblNewLabel_1 = new JLabel("Ghi chú :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(39, 78, 97, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblGiTin = new JLabel("Giá tiền");
		lblGiTin.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblGiTin.setBounds(288, 47, 62, 23);
		frame.getContentPane().add(lblGiTin);
		
		txtng = new JTextField();
		txtng.setText("0 đồng\r\n");
		txtng.setBounds(342, 47, 109, 20);
		frame.getContentPane().add(txtng);
		txtng.setColumns(10);
	}
}
