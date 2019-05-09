package cn.netkiller.ipo.process.map;

import java.util.Map;

import cn.netkiller.ipo.process.ProcessInterface;

public class MapReplace implements ProcessInterface {
	private final String oldString;
	private final String newString;
	private final String key;

	public MapReplace(String key, String oldString, String newString) {
		this.key = key;
		this.oldString = oldString;
		this.newString = newString;
	}

	@Override
	public Object run(Object data) {
		@SuppressWarnings("unchecked")
		Map<String, Object> row = (Map<String, Object>) data;
		if (row == null) {
			return null;
		}
		if (this.oldString == null) {
			if (row.get(this.key) == null) {
				row.replace(this.key, this.newString);
			}
		} else if (row.get(this.key) instanceof String) {
			String tmp = (String) row.get(this.key);
			if (tmp.contains(this.oldString)) {
				tmp = tmp.replace(this.oldString, this.newString);
				row.replace(this.key, tmp);
			}
		}

		return row;
	}
}
