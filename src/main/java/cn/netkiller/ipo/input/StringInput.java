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

	public boolean open() {
		this.iterator = data.iterator();
		return false;
	}

	public Object readLine() {
		if (this.iterator.hasNext()) {
			return this.iterator.next();
		}
		return null;
	}

	public boolean close() {
		return false;
	}

	public boolean hasNext() {
		return this.iterator.hasNext();
	}

}
