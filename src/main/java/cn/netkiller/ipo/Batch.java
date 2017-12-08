package cn.netkiller.ipo;

import java.util.ArrayList;
import java.util.List;

import cn.netkiller.ipo.test.MyRunnable;

public class Batch extends Thread {
	private List<InputProcessOutput> tasks = new ArrayList<InputProcessOutput>();
	public Batch() {
		
	}
	private void add(InputProcessOutput ipo) {
		this.tasks.add(ipo);
	}
	
	public void run() {
		for(InputProcessOutput task : tasks) {
			new Thread(new MyRunnable(task.getName())).start();
		}
	}
}
