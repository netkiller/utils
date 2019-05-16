package cn.netkiller.ipo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.netkiller.ipo.position.PositionInterface;

public class Position implements PositionInterface {
	@SuppressWarnings("unused")
	private final static Logger logger = LoggerFactory.getLogger(Position.class);

	private PositionInterface position;

	public Position() {

	}

	public Position(PositionInterface positionInterface) {
		this.position = positionInterface;
	}

	public boolean set(Object data) {

		return position.set(data);

	}

	public String get() {
		return this.position.get();
	}

	public boolean reset() {
		return this.position.reset();
	}

	public Object get(String hashKey) {
		return this.position.get(hashKey);
	}

}
