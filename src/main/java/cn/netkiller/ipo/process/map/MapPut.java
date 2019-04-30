package cn.netkiller.ipo.process.map;

import java.util.Map;

import cn.netkiller.ipo.process.ProcessMapInterface;

public class MapPut implements ProcessMapInterface {
	private String key;
	private Object value = null;

	public MapPut(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public Map<String, Object> run(Map<String, Object> row) {
		row.put(this.key, this.value);
		return row;
	}

}
