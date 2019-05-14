package cn.netkiller.ipo.position;

public interface PositionInterface {
	public boolean set(Object data);

	public String get();

	public boolean reset();

	public Object get(String hashKey);

}
