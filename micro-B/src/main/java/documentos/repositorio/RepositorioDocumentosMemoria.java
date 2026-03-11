package documentos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import documentos.modelo.Documento;

// Utiliza base de datos en memoria H2
public interface RepositorioDocumentosMemoria 
	extends RepositorioDocumentos, JpaRepository<Documento, Long> {

}