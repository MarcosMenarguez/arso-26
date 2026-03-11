package documentos;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import documentos.servicio.IServicioDocumentos;

public class ProgramaServicio {

	public static void main(String[] args) throws Exception {

		ConfigurableApplicationContext contexto = SpringApplication.run(DocumentosSpringApplication.class, args);

		IServicioDocumentos servicio = contexto.getBean(IServicioDocumentos.class);
		
		Long id = servicio.crear("doc1", "jose@um.es");
		
		System.out.println(servicio.getDocumento(id));
		
		contexto.close();
		
		System.out.println("fin.");

	}
}
