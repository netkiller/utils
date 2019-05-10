package cn.netkiller.ipo.output;

public class ElasticsearchOutput implements OutputInterface {

	private String index;
	private String type;

	public ElasticsearchOutput() {
	}

	public ElasticsearchOutput(String index, String type) {
		this.index = index;
		this.type = type;
	}

	@Override
	public boolean open() {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	@Override
	public boolean write(Object output) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	@Override
	public boolean close() {
		throw new UnsupportedOperationException("Not supported yet.");

	}

}
