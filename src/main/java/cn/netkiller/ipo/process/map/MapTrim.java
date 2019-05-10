package cn.netkiller.ipo.process.map;

import java.util.Map;

import cn.netkiller.ipo.process.ProcessInterface;

public class MapTrim implements ProcessInterface {

	private String key;

	public MapTrim(String key) {
		this.key = key;

	}

	@Override
	public Object run(Object data) {
		@SuppressWarnings("unchecked")
		Map<String, Object> row = (Map<String, Object>) data;
		if (row.containsKey(this.key)) {
			if (row.get(this.key) instanceof String) {
				row.put(this.key, ((String) row.get(this.key)).trim());
			}
		}

		return row;
	}

	@Override
	public boolean open() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		return false;
	}
}
