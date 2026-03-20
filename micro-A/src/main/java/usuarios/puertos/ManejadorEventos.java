package usuarios.puertos;

// Puerto de entrada
public interface ManejadorEventos {

	void documentoCreado(String id, String titulo, String email);
}
