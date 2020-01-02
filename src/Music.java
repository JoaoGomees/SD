
public class Music {

	private int identificador;
	private String nome;
	private String autor;
	private String ano;
	private String [] categorias;
	private int numero_downloads;
	
	public Music (int id, String nome, String autor, String ano, String [] etiquetas) {
		this.identificador = id;
		this.nome = nome;
		this.autor = autor;
		this.ano = ano;
		this.numero_downloads = 0;
		this.categorias = new String [100];
		
		for (int i = 0; i < etiquetas.length; i++)
			this.categorias[i] = etiquetas[i];
	}
	
	public int get_id () {
		return this.identificador;
	}
	
	public String get_nome () {
		return this.nome;
	}
	
	public String get_autor () {
		return this.autor;
	}
	
	
	public String get_ano () {
		return this.ano;
	}
	
	public String [] get_categorias () {
		return this.categorias;
	}
	
	public int get_numeroDownloads () {
		return this.numero_downloads;
	}
	
	public void inc_numeroDownloads () {
		this.numero_downloads++;
	}
	
	public String to_String () {
		StringBuilder sb = null;
		sb.append("musica:" + this.get_id() + ":" + this.get_nome() + ":" + this.get_autor());
		sb.append("\n");
		return sb.toString();
	}
}
