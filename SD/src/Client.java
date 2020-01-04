import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Client {
	
	private Socket clientSocket;
	private Scanner buffer;
	private BufferedReader in;
	private PrintWriter out;
	private boolean login;
	
    public Client (String host, int port) throws UnknownHostException, IOException {
    	this.clientSocket = new Socket (host,port);
    	this.buffer = new Scanner (System.in);
    	this.login = false;

    }
    
    public void start () throws IOException {
    	
    	this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
    	this.out = new PrintWriter (this.clientSocket.getOutputStream());
    	
    	while (true ) {
    		
    		if (!this.login) {
    			System.out.println("To create an account write -> create account:<username>:<password>");
    			System.out.println("To log in with your account write -> log in:<username>:<password>");
    		} else {
    			;
    		}
			
			String input = buffer.nextLine();
    		String parser [] = input.trim().split(":");
    		this.out.println(input);
    		this.out.flush();
    		
    		
    		if ("quit".equals(parser[0])) break;
    		
    		if ("create account".equals(parser[0]));
    		
    		if ("log in".equals(parser[0]))  {
    			String answer = in.readLine();
     			if (answer.equals("Sucesso")) {
     				System.out.println ("Sucesso");
     				this.login = true;
     			} else System.out.println(answer);
     			
    		}
    			
			if ("upload".equals(parser[0])) {
    			
				File file = new File(parser[1]);
				long length = file.length();
				
				
				byte[] bytes = new byte[(int)length];
				InputStream inS = new FileInputStream(file);
				OutputStream outS = this.clientSocket.getOutputStream();
				int count;
	
				System.out.println("Sending");
				while (((count = inS.read(bytes)) != -1) && length != 0) {
					outS.write(bytes, 0, count);
					length -= count;
					outS.flush();
					System.out.println(length);
				}
				
				System.out.println("Sent");
			}
			
			if ("download".equals(parser[0])) {
				
				File file = new File("Music/" + parser[1]);
				InputStream inS = this.clientSocket.getInputStream();
				File f = new File("/Users/Jota/Desktop/musicas/" + parser[1] + ".mp3");
				OutputStream outS = new FileOutputStream(f);
				byte[] bytes = new byte[(int)file.length()];
			
				int count;
				System.out.println ("Receiving");
			       while ((count = inS.read(bytes)) > 1) {
			           outS.write(bytes, 0, count);
			           
			       }
			        
			    System.out.println("Received");
				
			}
			
    		if ("ver musicas".equals(parser[0])) {
    			String answer = in.readLine();
    			System.out.println(answer);
    		}
    		
    		
    	}
    }
    
    
    public static void main (String args []) throws UnknownHostException, IOException {
		Client client = new Client ("127.0.0.1", 6666);
		client.start();
		
	}
    
}
