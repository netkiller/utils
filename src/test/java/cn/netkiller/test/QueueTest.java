package cn.netkiller.test;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QueueTest {
	/**
	 * 定义装苹果的篮子
	 */
	public static class Basket {
		// 篮子，能够容纳10个苹果
		BlockingQueue<String> basket = new ArrayBlockingQueue<String>(10);

		// 生产苹果，放入篮子
		public void produce() throws InterruptedException {
			// put方法放入一个苹果，若basket满了，等到basket有位置
			basket.put("An apple");
		}

		// 消费苹果，从篮子中取走
		public String consume() throws InterruptedException {
			// get方法取出一个苹果，若basket为空，等到basket有苹果为止
			return basket.take();
		}

		public int size() {
			return basket.size();
		}

	}

	// 测试方法
	public static void testBasket() throws InterruptedException {
		// 建立一个装苹果的篮子
		final Basket basket = new Basket();
		// 定义苹果生产者
		class Producer implements Runnable {
			public void run() {
				try {
					while (true) {
						int n = random(1, 5);
						for (int i = 0; i < n; i++) {
							basket.produce();
						}
						System.out.println(System.currentTimeMillis() + " 放入" + n + "个，当前总数：" + basket.size() + "个");
						Thread.sleep(random(450, 1000));
					}
				} catch (InterruptedException ex) {
				}
			}
		}
		// 定义苹果消费者
		class Consumer implements Runnable {
			public void run() {
				try {
					while (true) {
						// 消费苹果
						int n = random(1, 5);
						for (int i = 0; i < n; i++) {
							basket.consume();
						}
						System.out.println(System.currentTimeMillis() + " 取出" + n + "个，剩余数量：" + basket.size() + "个");
						Thread.sleep(random(400, 1000));
					}
				} catch (InterruptedException ex) {
				}
			}
		}

		ExecutorService service = Executors.newCachedThreadPool();
		Producer producer = new Producer();
		Consumer consumer = new Consumer();
		service.submit(producer);
		// 延迟消费
		Thread.sleep(5000);
		service.submit(consumer);
		// 程序运行10s后，所有任务停止
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
		}
		service.shutdownNow();
	}

	public static int random(int min, int max) {
		var value = new Random().ints(min, (max + 1)).limit(1).findFirst().getAsInt();
		return value;
	}

	public static void main(String[] args) throws InterruptedException {
		// new Random().ints(10, 33, 38).forEach(System.out::println);

		System.out.println(random(10, 15));
		QueueTest.testBasket();
	}
}
