package cn.netkiller.ipo.position.map;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import cn.netkiller.ipo.position.PositionInterface;
import cn.netkiller.ipo.position.RedisPosition;

public class MapRedisPosition implements PositionInterface {

	private final static Logger logger = LoggerFactory.getLogger(RedisPosition.class);
	private StringRedisTemplate stringRedisTemplate;
	private String cacheKey;

	public MapRedisPosition(StringRedisTemplate stringRedisTemplate, String cacheKey) {
		this.stringRedisTemplate = stringRedisTemplate;
		this.cacheKey = String.format("%s:%s", this.getClass().getName(), cacheKey);
		logger.debug("Cache key {}", this.cacheKey);
	}

	@Override
	public boolean set(Object data) {

		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) data;

		// stringRedisTemplate.opsForValue().set(this.cacheKey, map);
		stringRedisTemplate.opsForHash().putAll(this.cacheKey, map);
		return true;

	}

	@Override
	public Object get(String hashKey) {
		if (stringRedisTemplate.opsForHash().hasKey(this.cacheKey, hashKey)) {
			Object cacheValue = (String) stringRedisTemplate.opsForHash().get(this.cacheKey, hashKey);
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
	public String get() {
		// TODO Auto-generated method stub
		return null;
	}

}
