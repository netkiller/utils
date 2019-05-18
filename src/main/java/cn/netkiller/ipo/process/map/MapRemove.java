package cn.netkiller.ipo.process.map;

import java.util.Map;

import cn.netkiller.ipo.process.ProcessInterface;

public class MapRemove implements ProcessInterface {

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
	public Object run(Object data) {
		@SuppressWarnings("unchecked")
		Map<String, Object> row = (Map<String, Object>) data;
		if (this.value == null) {
			row.remove(this.key);
		} else {
			row.remove(this.key, this.value);
		}
		return row;
	}

	@Override
	public boolean open() {
		return true;
	}

	@Override
	public boolean close() {
		return true;
	}

}
