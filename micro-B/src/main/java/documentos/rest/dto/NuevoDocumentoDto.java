package documentos.rest.dto;

public class NuevoDocumentoDto {

	
    private String titulo;
    private String emailUsuario;
   
    public NuevoDocumentoDto() { // POJO
    
    }

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
    
}

