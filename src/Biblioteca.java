import java.util.ArrayList;
import java.util.HashMap;

public class Biblioteca {

	private int id_musica;
	private ArrayList <User> lista_users;
	private HashMap <Integer, Music> lista_musicas;
	
	public Biblioteca () {
		this.lista_users = new ArrayList <User> ();
		this.lista_musicas = new HashMap <Integer,Music> ();
		this.id_musica = 0;
	}
	
	public int get_id () {
		return this.id_musica;
	}
	
	public void inc_id () {
		this.id_musica++;
	}
	
	public void adicionaMusica (Music music) {
		this.lista_musicas.put(music.get_id(), music);
	}
	
	public synchronized void createAccount (String email, String password) {
		User user = new User (email, password);
		this.lista_users.add(user);
	}
	
	public boolean logIn (String email, String password) {
		boolean resultado = false;
		
		for (User user: this.lista_users) {
			if (user.get_email().equals(email) && user.get_password().equals(password))
				resultado = true;
		}
		
		return resultado;
	}
	
	
	public String devolveMusica (String etiqueta) {
		StringBuilder sb = null;
		sb.append("");
		
		this.lista_musicas.forEach((key, value) -> {
		    for (int i = 0; i < value.get_categorias().length; i++) {
		    	if (value.get_categorias()[i].equals(etiqueta)) {
		    		sb.append(value.to_String());
		    	}
		    }
		    
		});
		
		return sb.toString();
	
	}
}
