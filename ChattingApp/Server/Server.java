import java.awt.*;
import javax.swing.*;
class Server extends JFrame
{
	JTabbedPane tabs;
	JLabel portLabel;
	JTextField port;
	JTextArea history;
	JButton start;
	Server(String title)
	{
		super(title);
		tabs = new JTabbedPane(4);
		tabs.add("DashBoard",addDashBoard());
		tabs.add("Manage User",addManageUser());
		tabs.add("Display Chats",addDisplayChat());
		tabs.add("Settings",addSettings());
		add(tabs);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,400);
		setVisible(true);
	}
	
	private JPanel addManageUser()
	{
		JPanel panel = new JPanel();
		return panel;
	}
	private JPanel addDisplayChat()
	{
		JPanel panel = new JPanel();
		return panel;
	}
	private JPanel addSettings()
	{
		JPanel panel = new JPanel();
		return panel;
	}
	private JPanel addDashBoard()
	{
		JPanel panel = new JPanel();
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout());
		panel.setLayout(new BorderLayout());
		
		portLabel = new JLabel("PORT NO.");
		port = new JTextField("");
		start = new JButton("Start");
		portLabel.setBounds(20,10,100,30);
		port.setBounds(140,10,100,30);
		start.setBounds(260,10,100,30);
		northPanel.add(portLabel);
		northPanel.add(port);
		northPanel.add(start);
		history = new JTextArea();
		JScrollPane jsp = new JScrollPane(history);
		panel.add(BorderLayout.NORTH,northPanel);
		panel.add(BorderLayout.CENTER,jsp);
		start.addActionListener(new StartServices(this));
		
		return panel;
	}
	public static void main(String...s)
	{
		new Server("Hashchat - Server");
	}
}