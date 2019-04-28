package cn.netkiller.ipo.input;

import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaInput implements InputInterface {
	private final static Logger logger = LoggerFactory.getLogger(KafkaInput.class);
	private Properties properties = new Properties();
	private Collection<String> topics;
	private KafkaConsumer<String, String> consumer;

	public KafkaInput(String servers, String groupId, Collection<String> topics) {
		this.properties.put("bootstrap.servers", servers);
		this.properties.put("group.id", groupId);
		this.properties.put("enable.auto.commit", "true");
		this.properties.put("auto.commit.interval.ms", "1000");
		this.properties.put("session.timeout.ms", "30000");
		this.properties.put("log4j.logger.kafka", "INFO, kafkaAppender");
		this.properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		this.properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		this.topics = topics;
	}

	@Override
	public boolean open() {

		consumer = new KafkaConsumer<String, String>(this.properties);
		consumer.subscribe(this.topics);

		return true;
	}

	@Override
	public String readLine() {
		String line = "";
		ConsumerRecords<String, String> records = this.consumer.poll(1000);
		Iterator<ConsumerRecord<String, String>> iterator = records.iterator();
		if (iterator.hasNext()) {
			ConsumerRecord<String, String> record = iterator.next();
			logger.info(String.format("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value()));
			line = record.value();
		}
		return line;
	}

	@Override
	public boolean close() {
		this.consumer.close();
		return true;
	}

	@Override
	public Object getDataType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

}
