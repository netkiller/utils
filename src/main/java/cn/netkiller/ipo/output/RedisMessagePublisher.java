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
	public boolean open() {
		logger.debug("Channel {}", topic.getTopic());
		// stringRedisTemplate.opsForValue().set("name", "Neo");
		// stringRedisTemplate.convertAndSend(topic.getTopic(), "Channel OK");
		return true;
	}

	@Override
	public boolean write(Object message) {
		logger.debug(message.toString());
		stringRedisTemplate.convertAndSend(topic.getTopic(), message);
		return true;

	}

	@Override
	public boolean close() {
		return true;
		// logger.debug("Done");
		// logger.debug("REDIS: " + stringRedisTemplate.opsForValue().get("name"));
		// stringRedisTemplate.delete("name");

	}
}
