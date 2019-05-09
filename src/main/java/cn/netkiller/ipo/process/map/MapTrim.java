package cn.netkiller.ipo.process.map;

import java.util.Map;

import cn.netkiller.ipo.process.ProcessMapInterface;

public class MapTrim implements ProcessMapInterface {

	private String key;

	public MapTrim(String key) {
		this.key = key;

	}

	@Override
	public Map<String, Object> run(Map<String, Object> row) {
		if (row.containsKey(this.key)) {
			if (row.get(this.key) instanceof String) {
				row.put(this.key, ((String) row.get(this.key)).trim());
			}
		}

		return row;
	}

}
