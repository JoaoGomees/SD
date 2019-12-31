import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
	private Scanner sc;
	
    public Client (String host, int port) throws UnknownHostException, IOException {
    	this.clientSocket = new Socket (host,port);
    	this.sc = new Scanner (System.in);
    }
    
    public void start () throws IOException {
    	this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
    	this.out = new PrintWriter (this.clientSocket.getOutputStream());
    	
    	while (true) {
    		String input = sc.nextLine();
    		
    		if (!input.equals("Quit")) {
    			out.println(input);
        		out.flush();
        		System.out.println (in.readLine());
    		}
    		
    		else this.stop();
    	}
    }
    
    public void stop () throws IOException {
    	this.clientSocket.close();
    	this.in.close();
    	this.out.close();
    	this.sc.close();
    }
    
    public static void main (String args []) throws UnknownHostException, IOException {
		Client client = new Client ("127.0.0.1", 6666);
		client.start();
		
	}
    
}

	