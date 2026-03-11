package documentos.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Documento {
	
	 @Id @GeneratedValue
	private Long id;
	
	private String titulo;
	
	@ManyToOne
	private Usuario usuario;
	
	public Documento() { // POJO
		
	}
	
	public Documento(String titulo, Usuario usuario) {
		
		this.titulo = titulo;
		this.usuario = usuario;
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
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Documento [id=" + id + ", titulo=" + titulo + ", usuario=" + usuario + "]";
	}
	
	
}
