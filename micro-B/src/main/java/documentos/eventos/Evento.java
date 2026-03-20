package documentos.eventos;

import java.time.LocalDateTime;

public abstract class Evento {

	private String timestamp; // marca de tiempo
	private String id; // de la entidad
	private String tipo; // del evento
	
	public Evento(String id, String tipo) {
		this.timestamp = LocalDateTime.now().toString();
		this.id = id;
		this.tipo = tipo;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
}
