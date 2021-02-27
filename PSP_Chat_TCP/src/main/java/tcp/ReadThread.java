package tcp;

import java.io.*;
import java.net.*;
 
/**
 * Thread to read server messages
 * This thread is executed infinitely
 */
public class ReadThread extends Thread {
    private BufferedReader reader;
    private Socket socket;
    private AppCliente appCliente;
    
    /* Print and flush automatically to System.out */
    static PrintStream stream = new PrintStream(System.out, true);
 
    public ReadThread(Socket socket, AppCliente appCliente) {
        this.socket = socket;
        this.appCliente = appCliente;
 
        try {
            InputStream input = this.socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException e) {
            System.out.println("Error I/O: " + e.getMessage());
        }
    }
 
    public void run() {
        while (!appCliente.closed) {
            try {
                String response = reader.readLine();
                if (response.equals("CONNECTION_REJECTED_MAX_CONNECTIONS_REACHED")) {
                	throw new MaxConnections();
                } else if (response.equals("SERVER_CONNECTION_ACCEPTED")) {
                	continue;
                } else {
                	stream.println('\n' + response);
                	// prints the user name after displaying the server's message
                    if (appCliente.getUserName() != null) {
                    	stream.print("[" + appCliente.getUserName() + "]: ");
                    }	
                }
            } catch (IOException e) {
            	if (!appCliente.closed) {
            		stream.println("Error reading server: " + e.getMessage());
            	}

            	appCliente.closed = true;
                break;
            } catch (MaxConnections e) {
				try {
					stream.println("El servidor está lleno y no puedes entrar: " + e.getMessage());
					appCliente.closed = true;
					socket.close();
				} catch (IOException e1) {
					break;
				}
			}
        }
    }
}