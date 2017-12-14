package cn.netkiller.ipo.output;

import java.util.Collection;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaOutput implements OutputInterface {
	private final Logger logger = LoggerFactory.getLogger(KafkaOutput.class);
	private Properties properties = new Properties();
	private Producer<String, String> producer = null;
	private Collection<String> topics;

	public KafkaOutput(String servers, Collection<String> topics) {
		this.properties.put("bootstrap.servers", servers);
		this.properties.put("acks", "all");
		this.properties.put("retries", 0);
		this.properties.put("batch.size", 16384);
		this.properties.put("linger.ms", 1);
		this.properties.put("buffer.memory", 33554432);
		this.properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		this.properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		this.topics = topics;
	}

	@Override
	public void open() {
		this.producer = new KafkaProducer<String, String>(this.properties);
		this.logger.debug("Kafka Output open()");
	}

	@Override
	public void write(String output) {
		for (String topic : this.topics) {
			this.producer.send(new ProducerRecord<String, String>(topic, output));
			this.logger.debug("Kafka Output Topic: {}, Value: {}", topic, output);
		}
	}

	@Override
	public void close() {
		producer.close();
		this.logger.debug("Kafka Output close()");
	}

}
