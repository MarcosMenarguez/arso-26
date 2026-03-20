package documentos.adaptadores;

import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import documentos.puertos.ManejadorEventos;
import documentos.rabbitmq.RabbitMQConfig;

// Adaptador del puerto Manejador de Eventos

@Component
public class ConsumidorEventos {

	@Autowired
	private ManejadorEventos manejadorEventos;
	
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handleEvent(Map<String, String> mensaje) {
    	
    	// Importante: el parámetro debe declararse según la estructura de los eventos
    	
        System.out.println("Mensaje recibido: " + mensaje);
        
        if (mensaje.get("tipo").equals("usuario-creado")) {
        	
        	this.manejadorEventos.usuarioCreado(mensaje.get("id"), 
        			mensaje.get("nombre"));
        }
        
        
    }
}
