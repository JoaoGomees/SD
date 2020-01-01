import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Service implements Runnable {

	private Socket clientSocket;
	private Biblioteca biblioteca;
	
	public Service (Socket clientSocket, Biblioteca biblioteca) throws IOException {
		
		this.clientSocket = clientSocket;
		this.biblioteca = biblioteca;
	}
	
	public void run () {
		String answer;
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream());
			String s;
			while ((s = in.readLine()) != null) {
				System.out.println("lido: " + s);
				String p = s.trim();
				String parser [] = p.split(":");
				
				if ("create account".equals(parser[0])) {
					this.biblioteca.createAccount(parser[1], parser[2]);
					out.println("Conta com email: " + parser[1] + " criada\n");
					out.flush();
				}
				
				else if ("upload".equals(parser[0])){
					 InputStream inS = this.clientSocket.getInputStream();
					 OutputStream outS = new FileOutputStream(new File ("/home/sofia/Downloads/SD-master/test2.xml"));
					 byte[] bytes = new byte[1000000];
				
					 int count;
					 System.out.println ("Receiving");
				        while ((count = inS.read(bytes)) > 0) {
				            outS.write(bytes, 0, count);
				        }
				        
				     System.out.println("Received");
				        
				     outS.close();
				     inS.close();
				}
				
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
