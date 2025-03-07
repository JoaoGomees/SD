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
    		
			System.out.println("To create an account write -> create account:<username>:<password>");
			System.out.println("To log in with your account write -> log in:<username>:<password>");
			
    		String input = buffer.readLine();
    		String parser [] = input.trim().split(":");
    		this.out.println(input);
    		this.out.flush();
    		
    		
    		if ("quit".equals(parser[0])) break;
    		
    		if ("create account".equals(parser[0]));
    		
    		if ("log in".equals(parser[0])) {
    			String answer = in.readLine();
    			System.out.println (answer);
    			
				if (answer.equals("Sucesso"))
					System.out.println ("Para fazer um upload escreva -> upload:<file_path>:<nome_musica>:<nome_autor>:<ano>:<etiquetaVarias>");
				else ;
    		}
    		
    		if ("upload".equals(parser[0])) {
    			
    			System.out.println("Here");
				File file = new File(parser[1]);
				long length = file.length();
				byte[] bytes = new byte[1000000];
				InputStream inS = new FileInputStream(file);
				OutputStream outS = this.clientSocket.getOutputStream();
				int count;
	
				System.out.println("Sending");
				while ((count = inS.read(bytes)) > 0) {
					outS.write(bytes, 0, count);
					outS.flush();
				}
				
				
				
    		}
    		
    		
    	}
    }
    
    
    public static void main (String args []) throws UnknownHostException, IOException {
		Client client = new Client ("127.0.0.1", 6666);
		client.start();
		
	}
    
}
