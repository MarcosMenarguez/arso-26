package documentos.puertos;

import documentos.eventos.Evento;

// Puerto de salida
// Adaptador en el paquete "adaptadores"
public interface PublicadorEventos {

	void publicarEvento(Evento evento) ;
}
