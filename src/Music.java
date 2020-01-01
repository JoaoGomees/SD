
public class Music {

	private int identificador;
	private String nome;
	private String autor;
	private int duration; //em segundos
	private int ano;
	private String [] categorias;
	private int numero_downloads;
	private String file_path;
	
	public Music (int id, String nome, String autor, int duration, int ano, String [] categorias, String file_path) {
		this.identificador = id;
		this.nome = nome;
		this.autor = autor;
		this.duration = duration;
		this.ano = ano;
		this.numero_downloads = 0;
		this.file_path = file_path;
		
		for (int i = 0; i < categorias.length; i++) 
			this.categorias [i] = categorias [i];
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
	
	public int get_duration () {
		return this.duration;
	}
	
	public int get_ano () {
		return this.ano;
	}
	
	public String [] get_categorias () {
		return this.categorias;
	}
	
	public int get_numeroDownloads () {
		return this.numero_downloads;
	}
	
	public String get_filePath () {
		return this.file_path;
	}
	
	public void inc_numeroDownloads () {
		this.numero_downloads++;
	}
	
	public String to_String () {
		StringBuilder sb = null;
		sb.append("musica:" + this.get_id() + ":" + this.get_nome() + ":" + this.get_autor());
		
		return sb.toString();
	}
}
