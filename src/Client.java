import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	private Socket clientSocket;
	private BufferedReader buffer;
	private BufferedReader in;
	private PrintWriter out;
	
    public Client (String host, int port) throws UnknownHostException, IOException {
    	this.clientSocket = new Socket (host,port);
    	this.buffer = new BufferedReader(new InputStreamReader (System.in));

    }
    
    public void start () throws IOException {
    	this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
    	this.out = new PrintWriter (this.clientSocket.getOutputStream());
    	
    	while (true ) {
			System.out.println("To create an account write: create account:<username>:<password>");
			System.out.println("To upload a file write: upload:<file path>");
			System.out.println("To leave write: quit");
    		String input = buffer.readLine();
    		String parser [] = input.trim().split(":");
    		this.out.println(input);
    		this.out.flush();
    		
    		if ("quit".equals(parser[0]))
    			break;
    		
    		else if ("create account".equals(parser[0])) 
    			System.out.println(in.readLine());
    		
    		else if ("upload".equals(parser[0])) {
    			System.out.println("Here");
				File file = new File(parser[1]);
				long length = file.length();
				byte[] bytes = new byte[1000000];
				InputStream in = new FileInputStream(file);
				OutputStream out = this.clientSocket.getOutputStream();
				int count;
	
				System.out.println("Sending");
				while ((count = in.read(bytes)) > 0) {
					out.write(bytes, 0, count);
				}
				out.close();
				in.close();
    		}
    	}
    }
    
    
    public static void main (String args []) throws UnknownHostException, IOException {
		Client client = new Client ("127.0.0.1", 6666);
		client.start();
		
	}
    
}
