package usuarios.modelo;

import repositorio.Identificable;

public class Usuario implements Identificable {

	private String email; // el correo es el identificador
	private String nombre;
	
	public Usuario(String email, String nombre) {
		super();
		this.email = email;
		this.nombre = nombre;
	}

	@Override
	public String getId() {		
		return email;
	}
	
	@Override
	public void setId(String id) {		
		this.email = id;
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
