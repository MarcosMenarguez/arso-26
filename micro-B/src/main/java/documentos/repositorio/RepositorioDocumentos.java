package documentos.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import documentos.modelo.Documento;

@NoRepositoryBean
public interface RepositorioDocumentos extends CrudRepository<Documento, Long> {
	
}
