import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	
	public static void main (String args []) throws IOException {
		ArrayList <User> lista_users = new ArrayList <User> ();
		ArrayList <Music> lista_musicas = new ArrayList <Music> ();
		ServerSocket socket = new ServerSocket (6666);
		
		while (true) {
			Socket clientSocket = socket.accept();
			
			Thread cliente = new Thread (new Service (clientSocket, lista_users, lista_musicas));
			
			cliente.start();
		}
		
	}
	
}



