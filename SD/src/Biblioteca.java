import java.util.ArrayList;

public class Biblioteca {

	private int id_musica;
	private int downloads_ocorrer;
	private ArrayList <User> lista_users;
	private ArrayList <Music> lista_musicas;
	
	public Biblioteca () {
		this.lista_users = new ArrayList <User> ();
		this.lista_musicas = new ArrayList <Music> ();
		this.id_musica = 0;
		this.downloads_ocorrer = 0;
	}
	
	public int get_id () {
		return this.id_musica;
	}
	
	public synchronized int get_downloadsOcorrer () {
		return this.downloads_ocorrer;
	}
	
	public synchronized void inc_downloads () {
		this.downloads_ocorrer++;
	}
	
	public synchronized void dec_downloads () {
		this.downloads_ocorrer--;
	}
	
	
	public void inc_id () {
		this.id_musica++;
	}
	
	public synchronized void adicionaMusica (Music music) {
		this.lista_musicas.add(music);
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
	
	public synchronized void inc_downloads (int id) {
		for (Music m: this.lista_musicas) {
			if (m.get_id() == id)
				m.inc_numeroDownloads();
		}
	}
	
	public String get_music (int id) {
		String answer = null;
		
		for (Music m: this.lista_musicas)
			if (m.get_id() == id)
				answer = m.get_nome();
		
		return answer;
	}
	
	
	
	
	public String devolveMusica (String etiqueta) {
		StringBuilder sb = new StringBuilder (10000);
		
		for (Music m: this.lista_musicas) {
			for (int i = 0; i < m.get_categorias().length; i++) {
				if (etiqueta.equals(m.get_categorias()[i])) {
					sb.append(m.to_String());
				}
			}
		}
		
		return sb.toString();
		
	}
}
