
public class Music {

	private String nome;
	private String autor;
	private int duration; //em segundos
	private int ano;
	private String [] categorias;
	private int numero_downloads;
	
	public Music (String nome, String autor, int duration, int ano, String [] categorias) {
		this.nome = nome;
		this.autor = autor;
		this.duration = duration;
		this.ano = ano;
		this.numero_downloads = 0;
		
		for (int i = 0; i < categorias.length; i++) 
			this.categorias [i] = categorias [i];
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
	
	public void inc_numeroDownloads () {
		this.numero_downloads++;
	}
}
