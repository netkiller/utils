package cn.netkiller.ipo.input;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StringInput implements InputInterface {
	private List<String> data = new ArrayList<String>();
	private Iterator<String> iterator = null;

	public StringInput(String line) {
		this.data.add(line);
	}

	public StringInput(List<String> lines) {
		this.data.addAll(lines);
	}

	@Override
	public boolean open() {
		this.iterator = data.iterator();
		return false;
	}

	@Override
	public Object readLine() {
		if (this.iterator.hasNext()) {
			return this.iterator.next();
		}
		return null;
	}

	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getDataType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

}
