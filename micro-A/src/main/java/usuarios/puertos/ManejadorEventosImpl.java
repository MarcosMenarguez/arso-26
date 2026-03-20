package usuarios.puertos;

// Implementación puerto de entrada (se realiza en la capa del dominio)
public class ManejadorEventosImpl implements ManejadorEventos {

	@Override
	public void documentoCreado(String id, String titulo, String email) {
		
		// TODO
		
		System.out.println("evento documento creado proceado");
		
	}

}
