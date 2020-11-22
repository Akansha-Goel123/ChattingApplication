import java.sql.*;
import java.util.*;
class DAO
{
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private static String URL = "jdbc:mysql://localhost:3306/hashchat";
	private static String USER = "root";
	private static String PASSWORD = "mysql";
	DAO()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public void registerUser(User user)
	{
		
	}
	public List<User> getUsers()
	{
		List<User> users = new LinkedList<User>();
		try
		{
			String SQL = "select * from user where active = 1";
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				User user = new User();
				user.setUid(rs.getInt("uid"));
				user.setFname(rs.getString("fname"));
				user.setLname(rs.getString("lname"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setUsertype(rs.getString("usertype"));
				user.setDesignation(rs.getString("designation"));
				user.setDateOfRegistration(rs.getDate("dateOfRegistration"));
				user.setActive(rs.getInt("active"));
				users.add(user);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return users;
	}
	public User validateUser(String username,String password)
	{
		User user = null;
		try
		{
			String SQL = "select * from user where username = ? and password = ?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				user = new User();
				user.setUid(rs.getInt("uid"));
				user.setFname(rs.getString("fname"));
				user.setLname(rs.getString("lname"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setUsertype(rs.getString("usertype"));
				user.setDesignation(rs.getString("designation"));
				user.setDateOfRegistration(rs.getDate("dateOfRegistration"));
				user.setActive(rs.getInt("active"));
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return user;
	}
}