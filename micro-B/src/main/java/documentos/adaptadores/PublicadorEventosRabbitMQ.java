package documentos.adaptadores;

import java.io.IOException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import documentos.eventos.Evento;
import documentos.puertos.PublicadorEventos;
import documentos.rabbitmq.RabbitMQConfig;

// adaptador puerto de salida Publicador de Eventos

@Component
public class PublicadorEventosRabbitMQ implements PublicadorEventos {

	@Autowired
    private RabbitTemplate rabbitTemplate;

	@Override
	public void publicarEvento(Evento evento)  {
		rabbitTemplate.convertAndSend(
		          RabbitMQConfig.EXCHANGE_NAME, 
		          RabbitMQConfig.ROUTING_KEY + evento.getTipo(), 
		          evento);
		
	}
}
