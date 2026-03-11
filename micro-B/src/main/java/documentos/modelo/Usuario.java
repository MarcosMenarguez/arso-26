package documentos.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario {
	
	@Id
	private String email;
	
	private String nombre;
	
	
	public Usuario() { // POJO
		
	}
	
	public Usuario(String email, String nombre) {
		
		this.email = email;
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + ", nombre=" + nombre + "]";
	}
	
	
	
}
