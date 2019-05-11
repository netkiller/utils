package cn.netkiller.ipo.process.string;

import cn.netkiller.ipo.process.ProcessInterface;

public class StringExclude implements ProcessInterface {

	private String string;

	public StringExclude(String string) {
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
