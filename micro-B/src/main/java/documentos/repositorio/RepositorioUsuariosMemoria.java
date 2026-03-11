package documentos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import documentos.modelo.Usuario;

// Utiliza base de datos en memoria H2
public interface RepositorioUsuariosMemoria 
	extends RepositorioUsuarios, JpaRepository<Usuario, String> {

}