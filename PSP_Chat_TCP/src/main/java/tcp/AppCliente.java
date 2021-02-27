package tcp;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * AppServer should be launched first always, and then AppClient
 * Send message to the server and wait for the response
 */
public class AppCliente {
	public static final int DEFAULT_PORT = 4444;
	public static final String DEFAULT_IP = "localhost";
	
	String ip = "localhost";
	int port;
	private String userName;
	public boolean closed = true;

	public AppCliente (String ip, int port)
	{
		this.ip = ip;
		this.port = port;
	}
	
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public String getUserName() {
        return this.userName;
    }
  
	private void launchClient()
	{
		try {
			Socket socketTcp = new Socket(ip, port);
			System.out.println("Connecting to server: " + socketTcp);
			closed = false;
 
			/* One thread to read */
			new ReadThread(socketTcp, this).start();
			/* One thread to send, avoiding blocks waiting for server messages */
			new WriteThread(socketTcp, this).start();
		} catch (UnknownHostException e) {
			System.out.println("Server error: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O [Error]: " + e.getMessage());
		}
	}
  
	public static void main(String[] args) throws IOException 
	{
		/* Initialize AppCliente class with default IP/PORT */
		/* Here we can prompt user to ask for a particular IR/PORT */
		AppCliente app = new AppCliente(DEFAULT_IP, DEFAULT_PORT);
		app.launchClient();
  }
}
