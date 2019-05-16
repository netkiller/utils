package cn.netkiller.ipo.input;

import java.util.Queue;

public class QueueInput implements InputInterface {

	private Queue<Object> queue;

	public QueueInput(Queue<Object> queue) {
		this.queue = queue;
	}

	public boolean open() {
		return true;
	}

	public Object readLine() {
		Object item = this.queue.poll();
		return item;
	}

	public boolean close() {
		return true;
	}

	public boolean hasNext() {
		if (!this.queue.isEmpty()) {
			return true;
		}
		return false;
	}

}
