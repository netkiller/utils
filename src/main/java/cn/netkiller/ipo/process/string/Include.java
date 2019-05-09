package cn.netkiller.ipo.process.string;

import cn.netkiller.ipo.process.ProcessInterface;

public class Include implements ProcessInterface {

	private String string;

	public Include(String string) {
		this.string = string;
	}

	@Override
	public Object run(Object data) {
		String line = (String) data;
		// System.out.println(line);
		if (line.contains(this.string)) {
			return line;
		} else {
			return null;
		}
	}

}
