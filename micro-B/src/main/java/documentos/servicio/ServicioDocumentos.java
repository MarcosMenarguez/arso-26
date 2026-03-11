
package documentos.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import documentos.modelo.Documento;
import documentos.modelo.Usuario;
import documentos.repositorio.RepositorioDocumentos;
import documentos.repositorio.RepositorioUsuarios;
import repositorio.EntidadNoEncontrada;

@Service
@Transactional
public class ServicioDocumentos implements IServicioDocumentos {

	private RepositorioDocumentos repositorioDocumentos;
	private RepositorioUsuarios repositorioUsuarios;

	@Autowired
	public ServicioDocumentos(RepositorioDocumentos repositorio, RepositorioUsuarios repositorioUsuarios) {
		this.repositorioDocumentos = repositorio;
		this.repositorioUsuarios = repositorioUsuarios;
		
		// crea un usuario de pruebas
		
		Usuario usuario = new Usuario("jose@um.es", "Jose");
		this.repositorioUsuarios.save(usuario);
	}

	@Override
	public Long crear(String titulo, String email) throws EntidadNoEncontrada  {

		// Control de integridad de los datos

		if (titulo == null || titulo.isEmpty())
			throw new IllegalArgumentException("titulo: no debe ser nulo ni vacio");
		
		if (email == null || email.isEmpty())
			throw new IllegalArgumentException("email: no debe ser nulo ni vacio");
		
		Optional<Usuario> resultado = repositorioUsuarios.findById(email);
		if (resultado.isPresent() == false)
			throw new EntidadNoEncontrada("No existe usuario con id: " + email);
		
		Usuario usuario = resultado.get();
		
		Documento encuesta = new Documento(titulo, usuario);

		Long id = repositorioDocumentos.save(encuesta).getId();

		return id;
	}

	@Override
	public Documento getDocumento(Long id) throws EntidadNoEncontrada {
		if (id == null )
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");

		Optional<Documento> resultado = repositorioDocumentos.findById(id);
		if (resultado.isPresent() == false)
			throw new EntidadNoEncontrada("No existe documento con id: " + id);
		else
			return resultado.get();
	}

}
