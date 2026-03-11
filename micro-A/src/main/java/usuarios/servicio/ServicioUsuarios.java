package usuarios.servicio;

import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;
import usuarios.modelo.Usuario;

public class ServicioUsuarios implements IServicioUsuarios {

	private Repositorio<Usuario, String> repositorio = FactoriaRepositorios.getRepositorio(Usuario.class);

	@Override
	public void crear(String email, String nombre) throws RepositorioException {

		if (email == null || email.isEmpty())
			throw new IllegalArgumentException("email: no se acepta nulo o vacío");
		
		if (nombre == null || nombre.isEmpty())
			throw new IllegalArgumentException("nombre: no se acepta nulo o vacío");
		
		Usuario usuario = new Usuario(email, nombre);
		
		repositorio.add(usuario);
		
	}

	@Override
	public void modificarNombre(String email, String nombre) throws RepositorioException, EntidadNoEncontrada {

		if (email == null || email.isEmpty())
			throw new IllegalArgumentException("email: no se acepta nulo o vacío");
		
		if (nombre == null || nombre.isEmpty())
			throw new IllegalArgumentException("nombre: no se acepta nulo o vacío");
		
		Usuario usuario = repositorio.getById(email);
		
		usuario.setNombre(nombre);
		
		repositorio.update(usuario);	
	}
	
	@Override
	public Usuario obtenerUsuario(String email) throws RepositorioException, EntidadNoEncontrada {
		
		if (email == null || email.isEmpty())
			throw new IllegalArgumentException("email: no se acepta nulo o vacío");
		
		return repositorio.getById(email);
	}
	
}
