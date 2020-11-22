import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;
import java.net.*;

public class ServerMain extends JFrame implements ActionListener {
	
	JPanel p1;
	JTextField t1;
	JButton send;
	JTextArea a1;

	ServerSocket server;
	Socket socket;
	ObjectInputStream din;
	ObjectOutputStream dout;
	Server serverUI;
	
	boolean typing;
	
	ServerMain(Server serverUI) {
		
		JLabel picLabel1,picLabel2,picLabel3,picLabel4,picLabel5;
		this.serverUI = serverUI;
		p1=new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(7,94,84));
		p1.setBounds(0,0,450,70);
		add(p1);
		
		JLabel name=new JLabel("Server");
		name.setFont(new Font("SAN_SERIF",Font.BOLD,18));
		name.setForeground(Color.WHITE);
		name.setBounds(110,15,100,18);
		p1.add(name);
		
		JLabel status=new JLabel("Active now");
		status.setFont(new Font("SAN_SERIF",Font.PLAIN,14));
		status.setForeground(Color.WHITE);
		status.setBounds(110,35,100,20);
		p1.add(status);
		
		Timer t=new Timer(1,new ActionListener(){
			
			public void actionPerformed(ActionEvent ae){
				if(!typing){
					status.setText("Active now");
				}
			}
		});
		
		t.setInitialDelay(2000);
		
		
		try{
			BufferedImage img=ImageIO.read(new File("C:/Users/Akansha/Desktop/ChattingApp/Images/arrow.jpg"));
			picLabel1 = new JLabel(new ImageIcon(img));
			picLabel1.setBounds(5,17,30,30);
			p1.add(picLabel1);
			picLabel1.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent ae){
				
				System.exit(0);
			}
			
		});
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		try{
			BufferedImage img=ImageIO.read(new File("C:/Users/Akansha/Desktop/ChattingApp/Images/6.jpg"));
			picLabel2 = new JLabel(new ImageIcon(img));
			picLabel2.setBounds(40,5,60,60);
			p1.add(picLabel2);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try{
			BufferedImage img=ImageIO.read(new File("C:/Users/Akansha/Desktop/ChattingApp/Images/video.png"));
			picLabel3 = new JLabel(new ImageIcon(img));
			picLabel3.setBounds(290,20,30,30);
			p1.add(picLabel3);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try{
			BufferedImage img=ImageIO.read(new File("C:/Users/Akansha/Desktop/ChattingApp/Images/phone.png"));
			picLabel4 = new JLabel(new ImageIcon(img));
			picLabel4.setBounds(350,20,30,30);
			p1.add(picLabel4);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try{
			BufferedImage img=ImageIO.read(new File("C:/Users/Akansha/Desktop/ChattingApp/Images/3icon.png"));
			picLabel5 = new JLabel(new ImageIcon(img));
			picLabel5.setBounds(400,20,30,30);
			p1.add(picLabel5);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		a1=new JTextArea();
		a1.setBounds(5,75,440,570);
		a1.setFont(new Font("SANS_SERIF",Font.PLAIN,16));
		a1.setEditable(false);
		a1.setLineWrap(true);
		a1.setWrapStyleWord(true);
		add(a1);
		
		t1=new JTextField();
		t1.setBounds(5,655,310,40);
		t1.setFont(new Font("SANS_SERIF",Font.PLAIN,16));	
		add(t1);
		
		t1.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				status.setText("typing...");
				t.stop();
				typing=true;
			}
			public void keyReleased(KeyEvent ke){
				typing=false;
				if(!t.isRunning()){
					t.start();
				}
			}
			
		});
		
		send=new JButton("Send");
		send.setBounds(320,655,123,40);
		send.setBackground(new Color(7,94,84));
		send.setForeground(Color.WHITE);
		send.addActionListener(this);
		add(send);
		
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		setSize(450,700);
		setLocation(100,100);
		setUndecorated(true);
		setVisible(true);
		send.setFont(new Font("SANS_SERIF",Font.PLAIN,16));
		
		try{
			
			server = new ServerSocket(Integer.parseInt(serverUI.port.getText()));			
			socket=server.accept();
			//a1.append("\n Client Connected\n");
			
			while(true)
			{
				din=new ObjectInputStream(socket.getInputStream());
				String msg=(String)din.readObject();
				a1.append(msg);

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		try{
			dout=new ObjectOutputStream(socket.getOutputStream());
			String out=t1.getText();
			
			a1.setText(a1.getText()+"\n\t\t\t"+out+"\n");
			
			FileOutputStream fout=new FileOutputStream("chat.txt");
			ObjectOutputStream oos=new ObjectOutputStream(fout);
			oos.writeObject(out);
			FileInputStream fin=new FileInputStream("chat.txt");
			ObjectInputStream ois=new ObjectInputStream(fin);
			System.out.println("Server : "+ois.readObject());
					
			dout.writeObject(out);
			dout.flush();
			t1.setText("");
		}
		catch(Exception e){}
	}	
	
	
	
}
