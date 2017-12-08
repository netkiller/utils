package cn.netkiller.ipo.test;

public class MyRunnable implements Runnable {

	private String name;

	public MyRunnable(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Thread:" + this.name + ",i=" + i);
		}

	}

	public static void main(String[] args) {

		MyRunnable mr1 = new MyRunnable("A");
		MyRunnable mr2 = new MyRunnable("B");

		new Thread(mr1).start();
		new Thread(mr2).start();
		new Thread(new MyRunnable("C")).start();
	}
}
