package cn.netkiller.ipo.process.map;

import java.util.Map;

import com.google.common.base.CharMatcher;

import cn.netkiller.ipo.process.ProcessInterface;

public class MapPrintable implements ProcessInterface {

	private String key;

	public MapPrintable(String key) {
		this.key = key;
	}

	@Override
	public boolean open() {
		return true;
	}

	@Override
	public Object run(Object data) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) data;
		@SuppressWarnings("deprecation")
		String printable = CharMatcher.invisible().removeFrom((CharSequence) map.get(this.key));
		if (!printable.isEmpty()) {
			map.put(this.key, printable);
		}
		return map;
	}

	@Override
	public boolean close() {
		return true;
	}

}
