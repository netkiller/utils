package cn.netkiller.ipo.input;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapInput implements InputInterface {

	private List<Map<String, Object>> data;

	private Iterator<Map<String, Object>> iterator = null;

	public MapInput(Map<String, Object> map) {
		this.data.add(map);
	}

	public MapInput(List<Map<String, Object>> list) {
		this.data = list;
	}

	@Override
	public boolean open() {
		this.iterator = data.iterator();
		return true;
	}

	@Override
	public Object readLine() {
		if (this.iterator.hasNext()) {
			Map<String, Object> line = this.iterator.next();
			return line;
		}
		return null;
	}

	@Override
	public boolean close() {
		return true;
	}

	@Override
	public Object getDataType() {
		return new LinkedHashMap<String, Object>();
	}

	@Override
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

}
