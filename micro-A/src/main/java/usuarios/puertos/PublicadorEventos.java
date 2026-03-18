package usuarios.puertos;

import java.io.IOException;

import usuarios.eventos.Evento;

public interface PublicadorEventos {

	void publicarEvento(Evento evento) throws IOException;
}
