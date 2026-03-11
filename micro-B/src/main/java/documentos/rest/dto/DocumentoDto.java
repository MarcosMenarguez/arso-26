package documentos.rest.dto;

import documentos.modelo.Documento;

public class DocumentoDto {

    private Long id;
    private String titulo;
    private String nombreUsuario;
    private String emailUsuario;
    

    public DocumentoDto() {

    }

    public DocumentoDto(Long id, String titulo, String nombreUsuario, String emailUsuario) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.nombreUsuario = nombreUsuario;
		this.emailUsuario = emailUsuario;
	}

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public static DocumentoDto fromEntity(Documento entity) {
        return new DocumentoDto(entity.getId(), entity.getTitulo(), entity.getUsuario().getNombre(), entity.getUsuario().getEmail());
    }
}
