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

public class Service implements Runnable {

	private Socket clientSocket;
	private Biblioteca biblioteca;
	private static final int MAXDOWN = 10;
	
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
					out.println("Conta: " + parser[1] + " criada com sucesso");
					out.flush();
				}
				
				if ("log in".equals(parser[0])) {
					if (this.biblioteca.logIn(parser[1], parser[2])) {
						System.out.println("Sucesso");
						out.println("Sucesso");
						out.flush();
					}
						
					else {
						out.println("Falhou! Tente again!\n");
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
					
					File f = new File(parser[1]);
					long length = f.length();
					
					if (length > 7000000) {
						out.println("Size too big!");
						out.flush();
					}
					else {
						InputStream inS = this.clientSocket.getInputStream();
						OutputStream outS = new FileOutputStream(new File ("music/" + id));
						byte[] bytes = new byte[(int)f.length()];
					
						int count;
						System.out.println ("Receiving");
							while (((count = inS.read(bytes)) != -1)) {
					           outS.write(bytes, 0, count);
					           outS.flush();
					           length -= count;
					           if (length == 0) break;
					       }
					        
					    System.out.println("Received");
					}
					 
					
				
				}
				
				if ("download".equals(parser[0])) {
					
					if (this.biblioteca.get_downloadsOcorrer() == this.MAXDOWN)
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					else {
						this.biblioteca.inc_downloads();
						File file = new File("Music/" + parser[1]);
						long length = file.length();
						
						if (length > 7000000) {
							out.println("Size too big!");
							out.flush();
						}
						
						else {
							this.biblioteca.inc_downloads(Integer.parseInt(parser[1]));
							
							out.println(this.biblioteca.get_music(Integer.parseInt(parser[1])));
							out.flush();
							
							byte[] bytes = new byte[(int)length];
							InputStream inS = new FileInputStream(file);
							System.out.println(file.length());
							OutputStream outS = this.clientSocket.getOutputStream();
							int count;
				
							System.out.println("Sending");
							while (((count = inS.read(bytes)) > 1)) {
								outS.write(bytes, 0, count);
								length -= count;
								System.out.println(length);
								if (length == 0) break;
							}
							
							System.out.println("Sent");
						
						}
						
					if (this.biblioteca.get_downloadsOcorrer() == this.MAXDOWN) {
						this.biblioteca.dec_downloads();
						this.notify();
					} else {
						this.biblioteca.dec_downloads();
					}
					
					}
					
					
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
