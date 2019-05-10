package cn.netkiller.ipo.process;

/**
 *
 * @author netkiller
 */
public interface ProcessInterface {

	public boolean open();

	public Object run(Object data);

	public boolean close();

}
