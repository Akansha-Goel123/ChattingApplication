import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.*;
class LoginService implements ActionListener
{
	private ClientLogin client;
	private ObjectInputStream obis;
	private ObjectOutputStream obos;
	Socket socket;
	
	LoginService(ClientLogin client)
	{
		this.client = client;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == client.login)
		{
			try
			{
				obis = new ObjectInputStream(new FileInputStream("credentials.txt"));
				obis.close();
			}
			catch(Exception ex)
			{
				try
				{
					obos = new ObjectOutputStream(new FileOutputStream("credentials.txt"));
					obos.writeObject(client.ip.getText());
					obos.writeObject(client.port.getText());
					obos.close();
				}
				catch(Exception exc)
				{
					exc.printStackTrace();
				}
			}
			try
			{
				socket = new Socket(client.ip.getText(),Integer.parseInt(client.port.getText()));
				obos = new ObjectOutputStream(socket.getOutputStream());
				obos.writeObject(client.userName.getText());
				obos.writeObject(client.password.getText());
				obis = new ObjectInputStream(socket.getInputStream());
				User user = (User)obis.readObject();
				if(user != null)
				{
					List<User> users = (List<User>)obis.readObject();
					new UserPanel(user,users,socket,obos,obis);
					client.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Invalid Username/Password!!!"," ",JOptionPane.PLAIN_MESSAGE);
				}
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null,"Server is not online!!!","",JOptionPane.PLAIN_MESSAGE);
				ex.printStackTrace();
			}
		}
	}
}