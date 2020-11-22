import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.net.*;
import java.io.*;
import java.awt.image.*;

import javax.swing.tree.*;
class UserPanel extends JFrame
{
	User user;
	JLabel picLabel;
	java.util.List<User> users;
	Socket socket;
	ObjectOutputStream obos;
	ObjectInputStream obis;
	UserPanel(User user,java.util.List<User> users,Socket socket,ObjectOutputStream obos,ObjectInputStream obis)
	{
		super("Welcome "+user.getFname());
		this.user = user;
		this.users = users;
		this.socket = socket;
		this.obos = obos;
		this.obis = obis;
		//Information of Login User
		JPanel userInfo = new JPanel();
		userInfo.setLayout(new GridLayout(1,3));
		try{
			BufferedImage img=ImageIO.read(new File("C:/Users/Akansha/Desktop/ChattingApp/Images/close.png"));
			picLabel = new JLabel(new ImageIcon(img));
			//picLabel.setBounds(390,17,30,30);
			userInfo.add(picLabel);

		}
		catch(Exception e){}
		
		JPanel userInfoPanel1 = new JPanel();
		userInfoPanel1.setLayout(new GridLayout(3,1));
		userInfoPanel1.add(new Label(user.getFname()));
		userInfoPanel1.add(new Label(user.getLname()));
		userInfoPanel1.add(new Label(user.getPhone()));
		userInfo.add(userInfoPanel1);
		JPanel userInfoPanel2 = new JPanel();
		userInfoPanel2.setLayout(new GridLayout(3,1));
		//userInfoPanel2.add(new Label(user.getLname()));
		userInfoPanel2.add(new Label(user.getEmail()));
		userInfoPanel2.add(new Label(user.getDesignation()));
		userInfo.add(userInfoPanel2);
		add(BorderLayout.NORTH,userInfo);
		
		//Information of other Users
		JPanel usersInfo = new JPanel();
		usersInfo.setLayout(new BorderLayout());
		JScrollPane jsp = new JScrollPane(usersInfo);
		JTree userTree = new JTree(addUsers());
		usersInfo.add(userTree);
		add(BorderLayout.CENTER,usersInfo);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,850);
		setVisible(true);
	}
	private DefaultMutableTreeNode addUsers()
	{
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		java.util.Iterator itr = users.iterator();
		while(itr.hasNext())
		{
			User user = (User)itr.next();
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(user.getFname()+"["+user.getDesignation()+"]");
			root.add(node);
		}
		return root;
	}
}