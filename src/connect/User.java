package connect;

public class User {
	private String acc;
	private String pw;
	private String name;
	private String phone;
	private String address;
	public User(String acc, String pw, String name, String phone, String address) {
		this.acc = acc;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}
	public String getAcc() {
		return acc;
	}
	public void setAcc(String acc) {
		this.acc = acc;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
