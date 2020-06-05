package tw.doughnut.login;

import java.util.Date;

public class User {
	private int mem_id;
	private String mem_name;
	private String mem_password;
	private String mem_email;
	private String mem_phone;
	private Date mem_joindate;
	private Date mem_suspendtime;
	private int mem_state;
	private String mem_tax;
	private String mem_address;
	private double latitude;
	private double longitude;

	//全部資料，上傳使用
	public User(int mem_id, String mem_name, String mem_password, String mem_email, String mem_phone, Date mem_joindate,
			Date mem_suspendtime, int mem_state, String mem_tax, String mem_address, double latitude,
			double longitude) {
		super();
		this.mem_id = mem_id;
		this.mem_name = mem_name;
		this.mem_password = mem_password;
		this.mem_email = mem_email;
		this.mem_phone = mem_phone;
		this.mem_joindate = mem_joindate;
		this.mem_suspendtime = mem_suspendtime;
		this.mem_state = mem_state;
		this.mem_tax = mem_tax;
		this.mem_address = mem_address;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	//註冊使用
	public User(int mem_id, String mem_name, String mem_password, String mem_email, String mem_phone, Date mem_joindate,
			int mem_state, String mem_tax) {
		super();
		this.mem_id = mem_id;
		this.mem_name = mem_name;
		this.mem_password = mem_password;
		this.mem_email = mem_email;
		this.mem_phone = mem_phone;
		this.mem_joindate = mem_joindate;
		this.mem_state = mem_state;
		this.mem_tax = mem_tax;
	}

	// 登入使用
	public User(String mem_email, String mem_password) {
		super();

		this.mem_email = mem_email;
		this.mem_password = mem_password;
	}

	public int getMemId() {
		return mem_id;
	}

	public void setMemId(int mem_id) {
		this.mem_id = mem_id;
	}

	public String getMemName() {
		return mem_name;
	}

	public void setMemName(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMemPassword() {
		return mem_password;
	}

	public void setMemPassword(String mem_password) {
		this.mem_password = mem_password;
	}

	public String getMemEmail() {
		return mem_email;
	}

	public void setMemEmail(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getMemPhone() {
		return mem_phone;
	}

	public void setMemPhone(String mem_phone) {
		this.mem_phone = mem_phone;
	}

	public Date getMemJoindate() {
		return mem_joindate;
	}

	public void setMemJoindate(Date mem_joindate) {
		this.mem_joindate = mem_joindate;
	}

	public Date getMemSuspendtime() {
		return mem_suspendtime;
	}

	public void setMemSuspendtime(Date mem_suspendtime) {
		this.mem_suspendtime = mem_suspendtime;
	}

	public int getMemState() {
		return mem_state;
	}

	public void setMemState(int mem_state) {
		this.mem_state = mem_state;
	}

	public String getMemTax() {
		return mem_tax;
	}

	public void setMemTax(String mem_tax) {
		this.mem_tax = mem_tax;
	}

	public String getMemAddress() {
		return mem_address;
	}

	public void setMemAddress(String mem_address) {
		this.mem_address = mem_address;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
