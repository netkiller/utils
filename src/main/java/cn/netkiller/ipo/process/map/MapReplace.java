package cn.netkiller.ipo.process.map;

import java.util.Map;

import cn.netkiller.ipo.process.ProcessMapInterface;

public class MapReplace implements ProcessMapInterface {
	private final String oldString;
	private final String newString;
	private final String key;

	public MapReplace(String key, String oldString, String newString) {
		this.key = key;
		this.oldString = oldString;
		this.newString = newString;
	}

	@Override
	public Map<String, Object> run(Map<String, Object> row) {
		if (row == null) {
			return null;
		}
		if (row.get(this.key) instanceof String) {
			String tmp = (String) row.get(this.key);
			if (tmp.contains(this.oldString)) {
				tmp = tmp.replace(this.oldString, this.newString);
				row.replace(this.key, tmp);
			}
		}

		return row;
	}
}
