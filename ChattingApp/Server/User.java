import java.util.Date;
import java.io.Serializable;
import java.net.*;
public class User implements Serializable
{
	private int uid;
	private String fname;
	private String lname;
	private String email;
	private String phone;
	private String username;
	private String password;
	private String usertype;
	private String designation;
	private Date dateOfRegistration;
	private int active;
	private transient Socket socket;
	public void setDesignation(String designation)
	{
		this.designation = designation;
	}
	public String getDesignation()
	{
		return designation;
	}
	public Socket getSocket()
	{
		return socket;
	}
	public void setSocket(Socket socket)
	{
		this.socket = socket;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}
	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
}