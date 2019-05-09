package cn.netkiller.ipo.process.string;

import cn.netkiller.ipo.process.ProcessInterface;

public class Exclude implements ProcessInterface {

	private String string;

	public Exclude(String string) {
		this.string = string;
	}

	@Override
	public Object run(Object data) {
		String line = (String) data;
		if (line.contains(this.string)) {
			return null;
		} else {
			return line;
		}
	}

}
