package cn.netkiller.ipo.test;

public class MyThread extends Thread {

	private String name;

	public MyThread(String name) {
		super();
		this.name = name;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Thread:" + this.name + ",i=" + i);
		}
	}

	public static void main(String[] args) {
		MyThread mt1 = new MyThread("A");
		MyThread mt2 = new MyThread("B");
		mt1.start();
		mt2.start();
	}
}