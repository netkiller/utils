package cn.netkiller.ipo.process.map;

import java.util.Map;

import cn.netkiller.ipo.process.ProcessInterface;

public class MapCopy implements ProcessInterface {

	private String fromKey;
	private String toKey;

	public MapCopy(String fromKey, String toKey) {
		this.fromKey = fromKey;
		this.toKey = toKey;
	}

	@Override
	public boolean open() {
		return true;
	}

	@Override
	public Object run(Object data) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) data;
		map.put(this.toKey, map.get(this.fromKey));
		return map;
	}

	@Override
	public boolean close() {
		return true;
	}

}
