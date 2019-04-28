package cn.netkiller.ipo.process.map;

import java.util.Map;

import cn.netkiller.ipo.process.ProcessMapInterface;

public class MapRemove implements ProcessMapInterface {

	private Object key;
	private Object value = null;

	public MapRemove(Object key, Object value) {
		this.key = key;
		this.value = value;
	}

	public MapRemove(String key) {
		this.key = key;
	}

	@Override
	public Map<String, Object> run(Map<String, Object> row) {
		if (this.value == null) {
			row.remove(this.key);
		} else {
			row.remove(this.key, this.value);
		}
		return row;
	}

}
