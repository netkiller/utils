package cn.netkiller.ipo.position;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

public class NowPosition implements PositionInterface {
	private final static Logger logger = LoggerFactory.getLogger(NowPosition.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private StringRedisTemplate stringRedisTemplate;
	private String cacheKey;

	public NowPosition(StringRedisTemplate stringRedisTemplate, String cacheKey) {
		this.stringRedisTemplate = stringRedisTemplate;
		this.cacheKey = String.format("%s:%s", this.getClass().getName(), cacheKey);
	}

	@Override
	public boolean set(Object data) {

		stringRedisTemplate.opsForValue().set(this.cacheKey, dateFormat.format(new Date()));
		if (stringRedisTemplate.hasKey(this.cacheKey)) {
			return true;
		}
		return false;
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

		return true;
	}

	@Override
	public Object get(String hashKey) {
		return null;
	}

}
