package cn.netkiller.ipo.position;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisPosition implements PositionInterface {
	private final static Logger logger = LoggerFactory.getLogger(RedisPosition.class);
	private StringRedisTemplate stringRedisTemplate;
	private String cacheKey;

	public RedisPosition(StringRedisTemplate stringRedisTemplate, String cacheKey) {
		this.stringRedisTemplate = stringRedisTemplate;
		this.cacheKey = String.format("%s:%s", this.getClass().getName(), cacheKey);
		logger.debug("Cache key {}", this.cacheKey);
	}

	@Override
	public void set(String pos) {

		stringRedisTemplate.opsForValue().set(this.cacheKey, pos);

	}

	@Override
	public String get() {
		if (stringRedisTemplate.hasKey(this.cacheKey)) {
			String cacheValue = stringRedisTemplate.opsForValue().get(this.cacheKey);
			return cacheValue;
		}
		return null;
	}

	@Override
	public void reset() {
		stringRedisTemplate.delete(this.cacheKey);

	}

}
