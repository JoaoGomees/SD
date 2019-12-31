import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Service implements Runnable {

	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
	private ArrayList <User> lista_users;
	private ArrayList <Music> lista_musicas;
	
	public Service (Socket clientSocket, ArrayList <User> lista_users, ArrayList <Music> lista_musicas) throws IOException {
		
		this.clientSocket = clientSocket;
		
		this.in = new BufferedReader (new InputStreamReader (this.clientSocket.getInputStream()));
		this.out = new PrintWriter (this.clientSocket.getOutputStream());
		
		this.lista_users = lista_users;
		this.lista_musicas = lista_musicas;
	}
	
	public boolean logIn (String email, String password) {
		
		boolean resultado = false;
		
		for (User user: this.lista_users)
			if (user.get_email().equals(email) && user.get_password().equals(password))
				resultado = true;
		
		return resultado;
		
	}
	
	public void run () {
		
		 while (true) {
			 String input = null;
			try {
				input = in.readLine();
				
				if (!input.equals("Quit")) {
					out.println(input);
					out.flush();
				} else this.stop();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println (input);
		 }
	}
	
	
	public void stop () throws IOException {
		this.in.close();
		this.out.close();
		this.clientSocket.close();
	}
}
