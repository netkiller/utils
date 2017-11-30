package cn.netkiller.ipo.process;

public class IncludeProcess implements ProcessInterface {

	private String string;

	public IncludeProcess(String string) {
		this.string = string;
	}

	@Override
	public String run(String line) {
//		System.out.println(line);
		if (line.contains(this.string)) {
			return line;
		} else {
			return null;
		}
	}

}
