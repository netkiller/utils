package cn.netkiller.ipo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.netkiller.ipo.position.PositionInterface;

public class Position implements PositionInterface {
	private final static Logger logger = LoggerFactory.getLogger(Position.class);
	// private final Map<String, PositionInterface> positions = new LinkedHashMap<String, PositionInterface>();
	private PositionInterface position;

	public Position() {

	}

	public Position(PositionInterface positionInterface) {
		this.position = positionInterface;
	}

	@Override
	public boolean set(Object data) {

		// for (Map.Entry<String, PositionInterface> entry : positions.entrySet()) {
		// System.out.println(String.format("%s:%d", entry.getKey(), entry.getValue()));

		// String key = entry.getKey();
		// PositionInterface position = entry.getValue();

		// }

		return position.set(data);

	}

	public String get() {
		return this.position.get();
	}

	public String getSqlWhere(String field) {
		if (this.get() == null) {
			return "";
		} else {
			return String.format(" WHERE %s > %s", field, this.get());
		}
	}

	@Override
	public boolean reset() {
		return this.position.reset();
	}

	@Override
	public Object get(String hashKey) {
		return this.position.get(hashKey);
	}

}
