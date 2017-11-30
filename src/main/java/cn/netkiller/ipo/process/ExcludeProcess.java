package cn.netkiller.ipo.process;

public class ExcludeProcess implements ProcessInterface {

	private String string;

	public ExcludeProcess(String string) {
		this.string = string;
	}

	@Override
	public String run(String line) {
		if (line.contains(this.string)) {
			return null;
		} else {
			return line;
		}
	}

}
