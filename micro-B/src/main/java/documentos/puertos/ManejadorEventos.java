package documentos.puertos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import documentos.modelo.Usuario;
import documentos.repositorio.RepositorioUsuarios;

// Puerto de entrada
// Adaptador en el paquete "adaptadores"

@Component
public class ManejadorEventos {
	
	@Autowired
	private RepositorioUsuarios repositorioUsuarios;
	
	public void usuarioCreado(String email, String nombre) {
		
		Usuario usuario = new Usuario(email, nombre);
		
		this.repositorioUsuarios.save(usuario);
		
		System.out.println("usuario creado");
	}
	
}
