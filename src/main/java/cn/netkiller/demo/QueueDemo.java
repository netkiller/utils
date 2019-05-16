package cn.netkiller.demo;

import java.util.LinkedList;
import java.util.Queue;

import cn.netkiller.ipo.Input;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.Output;
import cn.netkiller.ipo.Process;
import cn.netkiller.ipo.input.QueueInput;
import cn.netkiller.ipo.output.QueueOutput;
import cn.netkiller.ipo.process.string.StringReplace;

public class QueueDemo {

	public QueueDemo() {
	}

	public static void main(String[] args) {

		Queue<Object> iQueue = new LinkedList<Object>();
		Queue<Object> oQueue = new LinkedList<Object>();
		iQueue.offer("Neo");
		iQueue.offer("Jam");
		Input input = new Input(new QueueInput(iQueue));
		Output output = new Output(new QueueOutput(oQueue));
		Process process = new Process();
		process.add(new StringReplace("Neo", "Netkiller "));

		InputProcessOutput ipo = new InputProcessOutput("Queue");
		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.launch();

		System.out.println(oQueue.toString());
	}
}
