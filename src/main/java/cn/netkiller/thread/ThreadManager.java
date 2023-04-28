package cn.netkiller.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/*
 * @author neo<netkiller@msn.com>
 */
@Slf4j
@Service
public class ThreadManager {

    public Thread[] listThreads() {
        int count = Thread.activeCount();
        Thread[] threads = new Thread[count];
        Thread.enumerate(threads);
        return threads;
    }

    public Thread fetchThread(long threadId) {
        int count = Thread.activeCount();
        Thread[] threads = new Thread[count];
        Thread.enumerate(threads);
        for (Thread thread : threads) {
            if (thread.threadId() == threadId) {
                return thread;
            }
        }
        return null;
    }

    public Thread fetchThread(String name) {
        return this.fetchThread(name, false);
    }

    public Thread fetchThread(String name, boolean ignoreCase) {
        Thread[] threadArray = listThreads();
        for (Thread thread : threadArray) {
            if (ignoreCase) {
                if (thread.getName().equalsIgnoreCase(name)) {
                    return thread;
                }
            } else {
                if (thread.getName().equals(name)) {
                    return thread;
                }
            }
        }
        return null;
    }


    public int activeCount() {
        return Thread.activeCount();
    }


    public void stop(Long threadId) {
        Thread thread = this.fetchThread(threadId);
        System.out.println(thread.toString());
//        thread.interrupt();
        thread.stop();
    }

    public void stop(String name) {
        Thread thread = this.fetchThread(name);
        thread.stop();
    }

    public void interrupt(Long threadId) {
        Thread thread = this.fetchThread(threadId);
        System.out.println(thread.toString());
        thread.interrupt();
    }

    public void interrupt(String name) {
        Thread thread = this.fetchThread(name);
        thread.interrupt();
    }

    public void wait(Long threadId) {
        Thread thread = this.fetchThread(threadId);
        try {
            thread.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void wait(String name) {
        Thread thread = this.fetchThread(name);
        try {
            thread.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void start(Long threadId) {
        Thread thread = this.fetchThread(threadId);
        thread.start();
    }

    public void start(String name) {
        Thread thread = this.fetchThread(name);
        thread.start();
    }

    public String show() {
        StringBuilder out = new StringBuilder();
        Thread[] threads = this.listThreads();
        int idLength = 4;
        int nameLength = 4;
        int groupLength = 5;
        for (Thread thread : threads) {

            if (Long.toString(thread.threadId()).length() > idLength) {
                idLength = Long.toString(thread.threadId()).length();
            }
            if (thread.getName().length() > nameLength) {
                nameLength = thread.getName().length();
            }
            if (thread.getThreadGroup().getName().length() > groupLength) {
                groupLength = thread.getThreadGroup().getName().length();
            }
        }

        String format = "| %" + idLength + "s | %" + nameLength + "s | %" + groupLength + "s | %6s | %13s | %8s |\n";
        String line = String.format("%0" + (idLength + nameLength + groupLength + 30 + 16) + "d\n", 0).replace("0", "=");
        out.append(line);
        out.append(String.format(format, "ID", "Name", "Group", "Daemon", "State", "Priority"));
        out.append(line.replace("=", "-"));
        for (Thread thread : threads) {
            if (thread != null) {
                out.append(String.format(format, thread.threadId(), thread.getName(), thread.getThreadGroup().getName(), thread.isDaemon(), thread.getState().name(), thread.getPriority()));
            }
        }
        out.append(line);
        return out.toString();
    }
}
