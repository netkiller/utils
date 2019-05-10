package cn.netkiller.ipo.output;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQOutput implements OutputInterface {
	private final Logger logger = LoggerFactory.getLogger(KafkaOutput.class);
	private Connection connection;
	private Channel channel;

	private String uri;
	private String queue;
	private String username;
	private String password;

	public RabbitMQOutput(String uri, String queue) {
		this.uri = uri;
		this.queue = queue;
	}

	public void authentication(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public boolean open() {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			// factory.setHost( );

			factory.setUri(this.uri);

			if (this.username != null) {
				factory.setUsername(this.username);
				factory.setPassword(this.password);
			}
			this.connection = factory.newConnection();
			this.channel = this.connection.createChannel();
			channel.queueDeclare(this.queue, false, false, false, null);
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean write(Object tmp) {
		String output = (String) tmp;
		try {
			channel.basicPublish("", this.queue, null, output.getBytes());
		} catch (IOException e) {
			logger.warn(e.getMessage());
		}
		return false;

	}

	@Override
	public boolean close() {
		try {
			channel.close();
			connection.close();
		} catch (IOException | TimeoutException e) {
			logger.warn(e.getMessage());
		} catch (Exception e) {
			logger.warn(e.getMessage());

		}
		return false;

	}

}
