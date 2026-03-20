package usuarios.adaptadores;

import java.io.IOException;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import servicio.FactoriaServicios;
import usuarios.puertos.ManejadorEventos;

// adaptador del puerto de entrada para el manejador de eventos

@WebListener
public class ConsumidorRabbitMQ implements ServletContextListener {

	// inyección del puerto de entrada
	private final ManejadorEventos manejadorEventos = FactoriaServicios.getServicio(ManejadorEventos.class);
	
	private Connection connection;
	private Channel channel; 
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		String uri = "amqp://guest:guest@localhost:5672";
		
		try {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUri(uri);

		connection = factory.newConnection();
		channel = connection.createChannel();
		
		String exchangeName = "bus";
		boolean durable = true;
		channel.exchangeDeclare(exchangeName,"topic", durable);
		
		final String queueName = "usuarios";
		final String bindingKey = "bus.documentos.#"; // todos los eventos de "documentos"
		durable = true;
		boolean exclusive = false;
		boolean autodelete = false;
		Map<String, Object> properties = null; // sin propiedades
		channel.queueDeclare(queueName, durable, exclusive, autodelete, properties);
		channel.queueBind(queueName, exchangeName, bindingKey);
		
	
		boolean autoAck = false;
		channel.basicConsume(queueName, autoAck, "usuarios-consumer", 
		  new DefaultConsumer(channel) {
		    @Override
		    public void handleDelivery(String consumerTag, Envelope envelope, 		
		    AMQP.BasicProperties properties,byte[] body) throws IOException {
		        
		       long deliveryTag = envelope.getDeliveryTag();

		        String contenido = new String(body);
		        System.out.println(contenido);
		        
		        JsonObject objeto = JsonParser.parseString(contenido).getAsJsonObject();
		        
		        if (objeto.get("tipo").getAsString().equals("documento-creado")) {
		        	
		        	// ejecutar operación del puerto ...
		        	String id = objeto.get("id").getAsString();
		        	String titulo = objeto.get("titulo").getAsString();
		        	String email = objeto.get("email").getAsString();
		        	
		        	manejadorEventos.documentoCreado(id, titulo, email);
		        }
		        
		        // Confirma el procesamiento
		        channel.basicAck(deliveryTag, false);
		    }
		});
		
		
		System.out.println("consumidor esperando ...");
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		try {
		if (this.channel != null)
			this.channel.close();
		
		if (this.connection != null)
			this.connection.close();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
