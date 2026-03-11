package documentos.rest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import documentos.modelo.Documento;
import documentos.rest.dto.DocumentoDto;
import documentos.rest.dto.NuevoDocumentoDto;
import documentos.servicio.IServicioDocumentos;

@RestController
@RequestMapping("/documentos")
public class DocumentosController {

	private IServicioDocumentos servicio;
	
	@Autowired
	public DocumentosController(IServicioDocumentos servicio) {
		this.servicio = servicio;		
	}

	// curl -i -X POST -H "Content-Type: application/json" -d "{\"titulo\": \"Doc Jose\", \"emailUsuario\": \"jose@um.es\"}" http://localhost:8081/documentos
	
	@PostMapping
	public ResponseEntity<Void> createDocumento(
			 @RequestBody NuevoDocumentoDto dto) throws Exception {
	 	
		Long id = this.servicio.crear(dto.getTitulo(),
				dto.getEmailUsuario());
	 
		 // Construye la URL completa del nuevo recurso
		 URI nuevaURL = ServletUriComponentsBuilder.fromCurrentRequest()
				 			.path("/{id}").buildAndExpand(id).toUri();
		 
		 return ResponseEntity.created(nuevaURL).build();
	}
	
	// curl http://localhost:8081/documentos/1
	
	@GetMapping("/{id}")
	public DocumentoDto getDocumentoById(@PathVariable Long id) throws Exception {
	 
		Documento encuesta = this.servicio.getDocumento(id);
	 
		 // retorna el DTO
		 return DocumentoDto.fromEntity(encuesta);	 
	}
	
 
	
}