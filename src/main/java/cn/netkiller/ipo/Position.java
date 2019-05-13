package cn.netkiller.ipo;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.netkiller.ipo.position.FilePosition;
import cn.netkiller.ipo.position.PositionInterface;
import cn.netkiller.ipo.position.RedisPosition;

public class Position implements PositionInterface {
	private final static Logger logger = LoggerFactory.getLogger(Position.class);
	private PositionInterface position;
	private String key;

	public Position(FilePosition filePosition, String key) {
		this.position = filePosition;
		this.key = key;
	}

	public Position(RedisPosition redisPosition, String key) {
		this.position = redisPosition;
		this.key = key;
	}

	@Override
	public boolean set(Object data) {

		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) data;

		if (map.containsKey(this.key)) {
			String current = String.valueOf(map.get(this.key));
			this.position.set(current);
			logger.debug("Current position {} => {}", this.key, current);
			return true;
		} else {
			// throw new Exception("The " + this.key + " isn't exist!");
			return false;
		}

	}

	public String get() {
		return this.position.get();
	}

	public void reset() {
		this.position.reset();
	}

}
