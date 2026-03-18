package usuarios.servicio;

import java.io.IOException;

import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import usuarios.eventos.EventoUsuarioCreado;
import usuarios.modelo.Usuario;
import usuarios.puertos.PublicadorEventos;

public class ServicioUsuarios implements IServicioUsuarios {

	private Repositorio<Usuario, String> repositorio = FactoriaRepositorios.getRepositorio(Usuario.class);

	private PublicadorEventos publicador = FactoriaServicios.getServicio(PublicadorEventos.class);
	
	@Override
	public void crear(String email, String nombre) throws RepositorioException, IOException {

		if (email == null || email.isEmpty())
			throw new IllegalArgumentException("email: no se acepta nulo o vacío");
		
		if (nombre == null || nombre.isEmpty())
			throw new IllegalArgumentException("nombre: no se acepta nulo o vacío");
		
		Usuario usuario = new Usuario(email, nombre);
		
		repositorio.add(usuario);
		
		// notificar evento
		EventoUsuarioCreado evento = new EventoUsuarioCreado(email, nombre);
		this.publicador.publicarEvento(evento);
		
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
