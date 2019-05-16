package cn.netkiller.ipo.input;

/**
 *
 * @author netkiller
 */
public interface InputInterface {
	public boolean open();

	public Object readLine();

	public boolean close();

	public boolean hasNext();
}