import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Service implements Runnable {

	private Socket clientSocket;
	private Biblioteca biblioteca;
	
	public Service (Socket clientSocket, Biblioteca biblioteca) throws IOException {
		
		this.clientSocket = clientSocket;
		this.biblioteca = biblioteca;
	}
	
	public void run () {
		
		try {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream());
			String s;
			
			while ((s = in.readLine()) != null) {
				
				System.out.println("lido: " + s);
				String p = s.trim();
				String parser [] = p.split(":");
				int parser_size = parser.length;
				
				
				if ("create account".equals(parser[0])) {
					this.biblioteca.createAccount(parser[1], parser[2]);
				}
				
				if ("log in".equals(parser[0])) {
					if (this.biblioteca.logIn(parser[1], parser[2])) {
						System.out.println("Sucesso");
						out.println("Sucesso");
						out.flush();
					}
						
					else {
						out.println("Falhou! Tente again!");
						out.flush();
					}
				}
				
				if ("upload".equals(parser[0])) {
					String categorias [] = new String [parser.length - 5];
					
					for (int i = 0; i < categorias.length; i++) {
						categorias [i] = parser[i+5];
					}
					
					Music nova = new Music (this.biblioteca.get_id(), parser[2], parser[3], parser[4], categorias);
					int id = this.biblioteca.get_id();
					this.biblioteca.adicionaMusica(nova);
					this.biblioteca.inc_id();
					
					
					 InputStream inS = this.clientSocket.getInputStream();
					 File f = new File(parser[1]);
					 OutputStream outS = new FileOutputStream(new File ("music/" + id ));
					 byte[] bytes = new byte[(int)f.length()];
				
					 int count;
					 System.out.println ("Receiving");
				        while ((count = inS.read(bytes)) != -1) {
				            outS.write(bytes, 0, count);
				        }
				        
				     System.out.println("Received");
				
				}
				
				if ("download".equals(parser[0])) {
					File file = new File ("/Users/Jota/eclipse-workspace/SD/Music/" + parser[1]);
					byte[] mybytearray = new byte[(int) file.length()];
					BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
					bis.read(mybytearray, 0, mybytearray.length);
					OutputStream os = this.clientSocket.getOutputStream();
					os.write(mybytearray, 0, mybytearray.length);
				    os.flush();
				
				}    
				
				if ("ver musicas".equals(parser[0])) {
					String answer = this.biblioteca.devolveMusica(parser[1]);
					out.println(answer);
					out.flush();
				}
				
				
				
			
			
		} 
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
