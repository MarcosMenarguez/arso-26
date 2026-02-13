package arso.cliente;

import java.io.IOException;

import bookle.modelo.Actividad;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;

public class Programa {

	public static void main(String[] args) throws IOException {
		
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://localhost:8080/api/")
				.addConverterFactory(JaxbConverterFactory.create()).build();
		
		BookleRestClient service = retrofit.create(BookleRestClient.class);
		
		// Recuperación
		Actividad actividad = service.getActividad("1").execute().body();
		System.out.println("Actividad: "
		+ actividad.getTitulo() + " - " + actividad.getDescripcion());
	}
}
