package cn.netkiller.ipo.output;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class RedisMessagePublisher implements OutputInterface {
	private final static Logger logger = LoggerFactory.getLogger(RedisMessagePublisher.class);

	private StringRedisTemplate stringRedisTemplate;
	private ChannelTopic topic;

	public RedisMessagePublisher(StringRedisTemplate stringRedisTemplate, String channel) {
		this.stringRedisTemplate = stringRedisTemplate;
		this.topic = new ChannelTopic(channel);
	}

	@Override
	public void open() {
		logger.debug(topic.getTopic());
	}

	@Override
	public void write(Object message) {
		logger.debug(message.toString());
		stringRedisTemplate.opsForValue().set("name", "Neo");
		logger.debug("REDIS: " + stringRedisTemplate.opsForValue().get("name"));

		stringRedisTemplate.convertAndSend(topic.getTopic(), message.toString());

	}

	@Override
	public void close() {

	}
}
