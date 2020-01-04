import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	
	
	public static void main (String args []) throws IOException {
		ServerSocket socket = new ServerSocket (6666); 
		
		Biblioteca biblioteca = new Biblioteca(); //biblioteca comum a todas as threads
		
		while (true) {
			
			Socket clientSocket = socket.accept();
			Thread cliente = new Thread (new Service (clientSocket, biblioteca));
			cliente.start();
		}
		
	}
	
}



