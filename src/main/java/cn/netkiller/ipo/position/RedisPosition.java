package cn.netkiller.ipo.position;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisPosition implements PositionInterface {
	private final static Logger logger = LoggerFactory.getLogger(RedisPosition.class);
	private StringRedisTemplate stringRedisTemplate;
	private String cacheKey;
	private String hashKey;

	public RedisPosition(StringRedisTemplate stringRedisTemplate, String cacheKey, String hashKey) {
		this.stringRedisTemplate = stringRedisTemplate;
		this.cacheKey = String.format("%s:%s", this.getClass().getName(), cacheKey);
		this.hashKey = hashKey;
		logger.debug("Cache key {}", this.cacheKey);
	}

	@Override
	public boolean set(Object data) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) data;

		if (map.containsKey(this.hashKey)) {
			String current = String.valueOf(map.get(this.hashKey));
			stringRedisTemplate.opsForValue().set(this.cacheKey, (String) current);
			logger.debug("Current position {} {} => {}", this.cacheKey, this.hashKey, current);
		} else {
			// throw new Exception("The " + this.key + " isn't exist!");
			return false;
		}

		return true;

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
	public boolean reset() {
		stringRedisTemplate.delete(this.cacheKey);
		return true;
	}

	@Override
	public Object get(String hashKey) {
		return null;
	}

	public String getSqlWhere() {
		if (this.get() == null) {
			return "";
		} else {
			return String.format(" WHERE %s > %s", this.hashKey, this.get());
		}
	}
}
