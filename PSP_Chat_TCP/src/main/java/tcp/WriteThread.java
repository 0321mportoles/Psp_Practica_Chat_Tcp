package tcp;

import java.io.*;
import java.net.*;
 
/**
 * This thread read user message to send to the server
 * This thread is executed infinitely until user types '*'.
 */
public class WriteThread extends Thread {
    private PrintWriter writer;
    private Socket socket;
    private AppCliente appCliente;
 
    public WriteThread(Socket socket, AppCliente appCliente) {
        this.socket = socket;
        this.appCliente = appCliente;

        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    public void run() {
         Console console = System.console();
 
        String name = console.readLine("\nEnter your name: ");
        appCliente.setUserName(name);
        writer.println(name);
 
        String message;
 
        do {
        	message = console.readLine("[" + name + "]: ");
            writer.println(message);
        } while (!message.equals("*") && !appCliente.closed);
        
        appCliente.closed = true;
 
        try {
            socket.close();
        } catch (IOException ex) {
//            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }
}