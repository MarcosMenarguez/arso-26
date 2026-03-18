package usuarios.eventos;

public class EventoUsuarioCreado extends Evento {

	private String nombre;
	
	public EventoUsuarioCreado(String email, String nombre ) {
		super(email, "usuario-creado"); // id - tipo
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
