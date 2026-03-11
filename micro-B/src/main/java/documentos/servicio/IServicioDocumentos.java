package documentos.servicio;

import documentos.modelo.Documento;
import repositorio.EntidadNoEncontrada;

public interface IServicioDocumentos {

	Long crear(String titulo, String emailUsuario) throws EntidadNoEncontrada;
	
	Documento getDocumento(Long id) throws EntidadNoEncontrada;
	
}
