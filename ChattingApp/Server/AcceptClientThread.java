import java.net.*;
import java.io.*;
import java.util.*;
class AcceptClientThread extends Thread
{
	StartServices services;
	ObjectInputStream obis;
	ObjectOutputStream obos;
	List<User> activeUsers;
	AcceptClientThread(StartServices services,String threadName)
	{
		super(threadName);
		this.services = services;
		activeUsers = new LinkedList<User>();
		start();
	}
	@Override
	public void run()
	{
		while(true)
		{
			try
			{
				Socket socket = services.server.accept();
				obis = new ObjectInputStream(socket.getInputStream());
				String username = (String)obis.readObject();
				String password = (String)obis.readObject();
				services.serverUI.history.append("* * * * * * * * * * * * * * * * * * * * * * * * * *\n");
				services.serverUI.history.append("One Client Requested.["+username+"]\n");
				services.serverUI.history.append("* * * * * * * * * * * * * * * * * * * * * * * * * *\n");
				DAO dao = new DAO();
				User user = dao.validateUser(username,password);
				obos = new ObjectOutputStream(socket.getOutputStream());
				obos.writeObject(user);
				if(user != null)
				{
					services.serverUI.history.append("* * * * * * * * * * * * * * * * * * * * * * * * * *\n");
					services.serverUI.history.append("Client Connected.["+username+"]\n");
					services.serverUI.history.append("* * * * * * * * * * * * * * * * * * * * * * * * * *\n");
					activeUsers.add(user);
					obos.writeObject(dao.getUsers());
					
				}
				else
				{
					services.serverUI.history.append("* * * * * * * * * * * * * * * * * * * * * * * * * *\n");
					services.serverUI.history.append("Client can't Connected.["+username+"]\n");
					services.serverUI.history.append("* * * * * * * * * * * * * * * * * * * * * * * * * *\n");
				}
				
			}
			catch(Exception ex)
			{
				services.serverUI.history.append("* * * * * * * * * * * * * * * * * * * * * * * * * *\n");
				services.serverUI.history.append("Exception while Connected\n");
				services.serverUI.history.append("* * * * * * * * * * * * * * * * * * * * * * * * * *\n");
				ex.printStackTrace();
			}
		}
	}
}