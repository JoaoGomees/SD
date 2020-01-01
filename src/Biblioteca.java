import java.util.ArrayList;

public class Biblioteca {

	private ArrayList <User> lista_users;
	private ArrayList <Music> lista_musicas;
	
	public Biblioteca () {
		this.lista_users = new ArrayList <User> ();
		this.lista_musicas = new ArrayList <Music> ();
	}
	
	public void adicionaMusica (Music music) {
		this.lista_musicas.add(music);
	}
	
	public void createAccount (String email, String password) {
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
	
	//funcao que dada uma etiqueta devolve todas as musicas que correspondam a essa tiqueta
	//devolve num formato especifico:
	//"musica1:<id_musica>:<nome_musica>:<autor_musica>//musica2:<id_musica2>:<nome_musica2>:<autor_musica2>//...."
	//tendo um padrao pre-definido ajuda a filtrar as mensagens já que nao se podem usar bibliotecas de serializaçao.
	
	public String devolveMusica (String etiqueta) {
		String resultado = null;
		StringBuilder sb = null;
		
		for (Music m: this.lista_musicas) {
			for (int i = 0; i < m.get_categorias().length; i++) {
				if (m.get_categorias()[i].equals(etiqueta))
					sb.append(m.to_String());
					sb.append("//");
			}
		}
		
		resultado = sb.toString();
		return resultado;
	}
}
