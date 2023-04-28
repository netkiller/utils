package cn.netkiller.thread;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.*;


public class TestThread {

//    private static final Logger logger = LoggerFactory.getLogger(TestThread.class);

    public TestThread() {

    }

    public static void setThreadName1() {
        new Thread("thread-name-1") {
            public void run() {
                try {
                    Thread.sleep(1000 * 15);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("threadName1: " + this.getName());

            }
        }.start();
    }

    public static void setThreadName2() {
        new Thread() {
            @SneakyThrows
            public void run() {
                this.setName("thread-name-2");
                Thread.sleep(1000 * 15);
                System.out.println("threadName2: " + this.getName());

            }
        }.start();
    }

    public static void setThreadName3() {
        Thread thread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000 * 15);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("threadName3: " + this.getName());

            }
        };

        thread.setName("thread-name-3");
        thread.start();
    }

    public static void setThreadName4() {
        new Thread("测试线程-1") {
            public void run() {
                try {
                    Thread.sleep(1000 * 15);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("threadName1: " + this.getName());

            }
        }.start();
    }

    public static void threadLocal() {
        ThreadLocal<String> local = new ThreadLocal<>();

        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
            local.set(Thread.currentThread().getName() + ":" + i);
            System.out.println("线程：" + Thread.currentThread().getName() + ",local:" + local.get());
        }).start());
    }

    public static void inheritableThreadLocal() {
        InheritableThreadLocal threadLocal = new InheritableThreadLocal();
        IntStream.range(0, 10).forEach(i -> {
            //每个线程的序列号，希望在子线程中能够拿到
            threadLocal.set(i);
            //这里来了一个子线程，我们希望可以访问上面的threadLocal
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
            }).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {

        ThreadManager ntm = new ThreadManager();
//        System.out.println(Arrays.stream(ntm.listThreads()).toList());


        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000 * 30);
                } catch (InterruptedException e) {
                    System.out.println("中断Thread#==" + finalI);
//                        e.printStackTrace();
                }

            });
        }
        threadPool.shutdown();

        TestThread.threadLocal();
        TestThread.inheritableThreadLocal();
//        System.out.println(ntm.activeCount());
        TestThread.setThreadName1();
        TestThread.setThreadName2();
        TestThread.setThreadName3();
        TestThread.setThreadName4();

        System.out.println(ntm.show());
        ntm.stop("pool-1-thread-1");
        ntm.stop("pool-1-thread-2");
        ntm.interrupt("pool-1-thread-3");
        //ThreadExplorer.kill("pool-1-thread-*", truer, "");//stop所有线程名符合pool-1-thread-*的线程
//        System.out.println(ntm.fetchThread("pool-1-thread-8").toString());
//        System.out.println(ntm.fetchThread(25).toString());
        ntm.stop(28L);
        ntm.interrupt(30L);
//        ntm.wait(30L);
        Thread.sleep(1000);// 休眠只是为了后面能够打印的时候准确显示

        System.out.println(ntm.show());

        Thread.sleep(30000);// 休眠只是为了后面能够打印的时候准确显示
        System.out.println(ntm.show());

        // final UUID uuid = UUID();
        // static final SecureRandom secureRandom = new SecureRandom();
        // secureRandom.

        // System.out.println(uuid.toString());

//		System.out.println(id);
        // Marker finance = MarkerFactory.getMarker(LogMarker.finance.toString());
        // Marker customer = MarkerFactory.getMarker(LogMarker.customer.toString());
        // Marker market = MarkerFactory.getMarker(LogMarker.market.toString());
        // logger.info("AAAAAAAAA");
        // logger.info(finance, "BBBBBBBBB");
        // logger.error(finance, "This is a serious an error requiring the admin's attention", new Exception("Just testing"));
        // logger.info(finance, "BBBBBBBBB");
        // logger.info(customer, "BBBBBBBBB");
        // logger.info(market, "BBBBBBBBB");

        // MDC.put("userId","0001");
        // logger.info("0001用户");
        // MDC.clear(); 

        // MDC.put("userId","0002");
        // logger.info("0002用户");
        // MDC.clear();

    }

}
