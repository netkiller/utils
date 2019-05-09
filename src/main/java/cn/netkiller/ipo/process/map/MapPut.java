package cn.netkiller.ipo.process.map;

import java.util.Map;

import cn.netkiller.ipo.process.ProcessInterface;

public class MapPut implements ProcessInterface {
	private String key;
	private Object value = null;

	public MapPut(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public Object run(Object data) {
		@SuppressWarnings("unchecked")
		Map<String, Object> row = (Map<String, Object>) data;
		row.put(this.key, this.value);
		return row;
	}

}
