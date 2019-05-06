package cn.netkiller.ipo.process.map;

import java.util.Map;

import cn.netkiller.ipo.process.ProcessMapInterface;

public class MapLeft implements ProcessMapInterface {

	private String key;
	private int length;

	public MapLeft(String key, int length) {
		this.key = key;
		this.length = length;
	}

	@Override
	public Map<String, Object> run(Map<String, Object> row) {

		if (row.get(this.key) instanceof String) {

			String tmp = (String) row.get(this.key);
			if (tmp.length() > this.length) {
				row.put(this.key, tmp.substring(0, this.length));
			}
		}

		return row;
	}

}
