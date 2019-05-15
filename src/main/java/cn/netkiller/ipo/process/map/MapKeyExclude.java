package cn.netkiller.ipo.process.map;

import java.util.List;
import java.util.Map;

import cn.netkiller.ipo.process.ProcessInterface;

public class MapKeyExclude implements ProcessInterface {

	private List<String> hashKeys;

	public MapKeyExclude(List<String> hashKeys) {
		this.hashKeys = hashKeys;
	}

	@Override
	public boolean open() {
		return true;
	}

	@Override
	public Object run(Object data) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) data;
		this.hashKeys.forEach(item -> {
			if (map.containsKey(item)) {
				map.remove(item);
			}
		});

		return map;
	}

	@Override
	public boolean close() {
		return true;
	}
}
