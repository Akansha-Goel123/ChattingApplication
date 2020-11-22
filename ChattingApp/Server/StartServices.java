import java.awt.event.*;
import java.net.*;
class StartServices implements ActionListener
{
	Server serverUI;
	ServerSocket server;
	StartServices(Server serverUI)
	{
		this.serverUI = serverUI;
	}
	@Override
	public void actionPerformed(ActionEvent ev)
	{
		if(serverUI.start.getText().equals("Start"))
		{
			try
			{
				server = new ServerSocket(Integer.parseInt(serverUI.port.getText()));
				serverUI.history.append("* * * * * * * * * * * * * * * * * * * * * * * * * *\n");
				serverUI.history.append("  Server Started at PORT["+serverUI.port.getText()+"]\n");
				serverUI.history.append("* * * * * * * * * * * * * * * * * * * * * * * * * *\n");
				serverUI.start.setText("Stop");
				serverUI.port.setEnabled(false);
				new AcceptClientThread(this,"AcceptClientThread");
			}
			catch(Exception e)
			{
				serverUI.history.append("* * * * * * * * * * * * * * * * * * * * * * * * * *\n");
				serverUI.history.append("Port["+serverUI.port.getText()+"] already in use.\n");
				serverUI.history.append("* * * * * * * * * * * * * * * * * * * * * * * * * *\n");
			}
		}
		else if(serverUI.start.getText().equals("Stop"))
		{
			if(server != null)
			{
				try
				{
					server.close();
					serverUI.history.append("* * * * * * * * * * * * * * * * * * * * * * * * * *\n");
					serverUI.history.append("Server Closed.\n");
					serverUI.history.append("* * * * * * * * * * * * * * * * * * * * * * * * * *\n");
					serverUI.start.setText("Start");
					serverUI.port.setEnabled(true);
				}
				catch(Exception ex)
				{
					serverUI.history.append("* * * * * * * * * * * * * * * * * * * * * * * * * *\n");
					serverUI.history.append("Error While closing Server.[Please restart Applicatoin]\n");
					serverUI.history.append("* * * * * * * * * * * * * * * * * * * * * * * * * *\n");
				}
			}
		}
	}
}