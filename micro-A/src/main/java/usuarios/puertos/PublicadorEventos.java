package usuarios.puertos;

import java.io.IOException;

import usuarios.eventos.Evento;

// puerto de salida
// su implementación está en el paquete "adaptadores"
public interface PublicadorEventos {

	void publicarEvento(Evento evento) throws IOException;
}
