package documentos.eventos;

public class EventoDocumentoCreado extends Evento {

	private String titulo;
	private String email;
	
	public EventoDocumentoCreado(String id, String titulo, String email) {
		super(id, "documento-creado");
		
		this.titulo = titulo;
		this.email = email;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public String getEmail() {
		return email;
	}
}
