package documentos.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import documentos.modelo.Usuario;

@NoRepositoryBean
public interface RepositorioUsuarios extends CrudRepository<Usuario, String> {
	
}
