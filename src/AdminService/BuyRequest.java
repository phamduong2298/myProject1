package AdminService;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.net.Socket;
import java.security.interfaces.RSAKey;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import Server.ClientThread;
import connect.BuyData;
import connect.ClientData;
import connect.FoodData;
import connect.GetDataById;

public class BuyRequest {

	public JFrame frame;
	private String user;
	private String id;
	private String table;
	private String sl;
	private String tien;
	private String mota;
	private String nameSp;
	private String nameUser;
	private String sdt;
	private String diachi;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyRequest window = new BuyRequest("Duong988","1","mi","2","20","no");
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
	public BuyRequest() {
		initialize();
	}
	
	public BuyRequest(String user, String id, String table, String sl, String tien, String mota){
		this.user = user;
		this.id = id;
		this.table = table;
		this.sl = sl;
		this.tien = tien;
		this.mota = mota;
		GetDataById getDataById = new GetDataById();
		this.nameSp = getDataById.getName(Integer.parseInt(id), table);
		getInforUser(user);
		initialize();
	}
	
	public void getInforUser(String user){
		ClientData clientData = new ClientData();
		clientData.inforUser(user);	
		try {
			clientData.rs.next();
			nameUser = clientData.rs.getString(3);
			sdt = clientData.rs.getString(4);
			diachi = clientData.rs.getString(5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 572, 478);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblKh = new JLabel("Kh\u00E1ch h\u00E0ng:");
		lblKh.setBounds(30, 25, 85, 21);
		frame.getContentPane().add(lblKh);
		
		JLabel lblNewLabel_1 = new JLabel("S\u1EA3n ph\u1EA9m:\r\n");
		lblNewLabel_1.setBounds(30, 57, 85, 21);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel label = new JLabel(nameUser);
		label.setBounds(125, 28, 301, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel(nameSp);
		label_1.setBounds(125, 60, 347, 14);
		frame.getContentPane().add(label_1);
		
		JLabel lblSLng = new JLabel("S\u1ED1 l\u01B0\u1EE3ng:");
		lblSLng.setBounds(30, 89, 85, 21);
		frame.getContentPane().add(lblSLng);
		
		JLabel lblGhiCh = new JLabel("Ghi ch\u00FA:");
		lblGhiCh.setBounds(30, 125, 85, 21);
		frame.getContentPane().add(lblGhiCh);
		
		JLabel label_2 = new JLabel(sl);
		label_2.setBounds(125, 92, 347, 14);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel(mota);
		label_3.setBounds(123, 128, 349, 99);
		frame.getContentPane().add(label_3);
		
		JLabel lblThnhTin = new JLabel("Th\u00E0nh ti\u1EC1n:");
		lblThnhTin.setBounds(30, 254, 85, 31);
		frame.getContentPane().add(lblThnhTin);
		
		JLabel lblNewLabel = new JLabel(tien);
		lblNewLabel.setBounds(125, 262, 301, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblSt = new JLabel("S\u0111t:");
		lblSt.setBounds(30, 296, 46, 14);
		frame.getContentPane().add(lblSt);
		
		JLabel label_4 = new JLabel(sdt);
		label_4.setBounds(125, 296, 226, 14);
		frame.getContentPane().add(label_4);
		
		JLabel lblaCh = new JLabel("\u0110\u1ECBa ch\u1EC9:");
		lblaCh.setBounds(30, 336, 46, 14);
		frame.getContentPane().add(lblaCh);
		
		JLabel label_5 = new JLabel(diachi);
		label_5.setBounds(125, 336, 301, 14);
		frame.getContentPane().add(label_5);
		
		JButton btnXcNhn = new JButton("X\u00E1c nh\u1EADn");
		btnXcNhn.setBounds(218, 393, 115, 23);
		frame.getContentPane().add(btnXcNhn);
		btnXcNhn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BuyData buyData = new BuyData();
				Date date1 = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(date1);
				buyData.addData(user, id, table, Integer.parseInt(sl) , date);
				FoodData foodData = new FoodData();
				int sln = foodData.getNum(id, table) - Integer.parseInt(sl);
				foodData.updateNum(id, table, Integer.toString(sln));
				frame.dispose();
			}
		});
	}
}
