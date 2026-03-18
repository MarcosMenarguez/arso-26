package usuarios.servicio;

import java.io.IOException;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import usuarios.modelo.Usuario;

public interface IServicioUsuarios {

	void crear(String email, String nombre) throws RepositorioException, IOException;
	
	void modificarNombre(String email, String nombre) throws RepositorioException, EntidadNoEncontrada;
	
	Usuario obtenerUsuario(String email) throws RepositorioException, EntidadNoEncontrada;
}
