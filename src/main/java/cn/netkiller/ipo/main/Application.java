package cn.netkiller.ipo.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.netkiller.ipo.Input;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.Output;
import cn.netkiller.ipo.Process;
import cn.netkiller.ipo.input.FileInput;
import cn.netkiller.ipo.output.StdoutOutput;
import cn.netkiller.ipo.process.Replace;

public class Application {

	// private final static Logger logger = LoggerFactory.getLogger(Application.class);

	public Application() {
	}

	public static void main(String[] args) {

		// logger.debug("begin...");

		Input input = new Input();
		// StdinInput stdin = new StdinInput();
		// input.add(new StdinInput());

		input.add(new FileInput("D:\\workspace\\ipo\\target\\project\\input.txt"));

		Output output = new Output();
		output.add(new StdoutOutput());

		Process process = new Process();
		process.add(new Replace("Hello", "Netkiller "));
		// process.add(new Replace("Neo", "<Neo>"));
		// process.add(new Replace("Tom", "[Tom]"));
		//
		InputProcessOutput ipo = new InputProcessOutput();

		Thread exit = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
					ipo.shutdown();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		exit.setName("shutdown");
		exit.start();

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setBatch(5);
		ipo.setPipeline(true);
		ipo.launch();

		// logger.debug("end...");
	}

}
