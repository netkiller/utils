package cn.netkiller.ipo.process.map;

import java.util.Map;

import cn.netkiller.ipo.process.ProcessInterface;

public class MapKeyReplace implements ProcessInterface {

	private final String newKey;
	private final String key;

	public MapKeyReplace(String key, String newKey) {
		this.key = key;
		this.newKey = newKey;
	}

	@Override
	public boolean open() {
		return true;
	}

	@Override
	public Object run(Object data) {
		@SuppressWarnings("unchecked")
		Map<String, Object> row = (Map<String, Object>) data;
		if (row.containsKey(this.key)) {
			row.put(this.newKey, row.get(this.key));
			row.remove(this.key);
		}
		return row;
	}

	@Override
	public boolean close() {
		return true;
	}

}
