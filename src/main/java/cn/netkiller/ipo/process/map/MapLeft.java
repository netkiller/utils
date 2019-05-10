package cn.netkiller.ipo.process.map;

import java.util.Map;

import cn.netkiller.ipo.process.ProcessInterface;

public class MapLeft implements ProcessInterface {

	private String key;
	private int length;

	public MapLeft(String key, int length) {
		this.key = key;
		this.length = length;
	}

	@Override
	public Object run(Object data) {

		@SuppressWarnings("unchecked")
		Map<String, Object> row = (Map<String, Object>) data;
		if (row.get(this.key) instanceof String) {

			String tmp = (String) row.get(this.key);
			if (tmp.length() > this.length) {
				row.put(this.key, tmp.substring(0, this.length));
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
