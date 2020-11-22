import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

class ClientLogin extends JFrame
{
	JPanel p1;
	JButton login;
	JTextField userName,ip,port;
	JPasswordField password;
	JLabel userNameLabel,passwordLabel,ipLabel,portLabel,picLabel1;
	ClientLogin()
	{
		p1=new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(7,94,84));
		p1.setBounds(0,0,450,70);
		add(p1);
		
		login = new JButton("Login");
		
		userNameLabel = new JLabel("Username");
		userNameLabel.setFont(new Font("SAN_SERIF",Font.BOLD,16));
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("SAN_SERIF",Font.BOLD,16));
		
		userName = new JTextField();
		password = new JPasswordField();
		ipLabel = new JLabel("IP Address");
		ipLabel.setFont(new Font("SAN_SERIF",Font.BOLD,16));
		
		portLabel = new JLabel("Port no.");
		portLabel.setFont(new Font("SAN_SERIF",Font.BOLD,16));
		
		try{
			BufferedImage img=ImageIO.read(new File("C:/Users/Akansha/Desktop/ChattingApp/Images/close.png"));
			picLabel1 = new JLabel(new ImageIcon(img));
			picLabel1.setBounds(390,17,30,30);
			p1.add(picLabel1);
			picLabel1.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent ae){
				
				System.exit(0);
			}
			
		});
		}
		catch(Exception e)
		{}
		
		try
		{
			ObjectInputStream obis = new ObjectInputStream(new FileInputStream("credentials.txt"));
			ip = new JTextField((String)obis.readObject());
			port = new JTextField((String)obis.readObject());
			ip.setEnabled(false);
			port.setEnabled(false);
			obis.close();
		}
		catch(Exception ex)
		{
			ip = new JTextField();
			port = new JTextField();
		}
		login.addActionListener(new LoginService(this));
		userNameLabel.setBounds(40,130,150,30);
		userName.setBounds(200,130,170,30);
		passwordLabel.setBounds(40,190,150,30);
		password.setBounds(200,190,170,30);
		ipLabel.setBounds(95,280,220,30);
		portLabel.setBounds(290,280,80,30);
		ip.setBounds(30,320,220,30);
		port.setBounds(280,320,80,30);
		login.setBounds(150,450,130,40);
		add(userName);
		add(userNameLabel);
		add(password);
		add(passwordLabel);
		add(ip);
		add(port);
		add(login);
		add(ipLabel);
		add(portLabel);
		setUndecorated(true);
		setLocation(800,100);
		setSize(450,600);
		setLayout(null);
		setVisible(true);
	}
	public static void main(String...s)
	{
		new ClientLogin().setVisible(true);
	}
}