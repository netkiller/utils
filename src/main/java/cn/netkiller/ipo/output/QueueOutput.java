package cn.netkiller.ipo.output;

import java.util.Queue;

public class QueueOutput implements OutputInterface {

	private Queue<Object> queue;

	public QueueOutput(Queue<Object> queue) {
		this.queue = queue;
	}

	public boolean open() {
		return true;
	}

	public boolean write(Object output) {
		boolean status = this.queue.offer(output);
		return status;
	}

	public boolean close() {
		return true;
	}

}
