package cn.netkiller.ipo.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.netkiller.ipo.Input;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.Output;
import cn.netkiller.ipo.Process;
import cn.netkiller.ipo.input.InputInterface;
import cn.netkiller.ipo.input.StdinInput;
import cn.netkiller.ipo.output.OutputStdout;
import cn.netkiller.ipo.process.Replace;

public class Application {

	// private final static Logger logger = LoggerFactory.getLogger(Application.class);

	public Application() {
	}

	public static void main(String[] args) {

		// logger.debug("begin...");

		
		
		
//		 StdinInput stdin = new StdinInput();

		// stdin.readLine();

		// String line;
		// while ((line = stdin.readLine()) != null) {
		// System.out.println(line);
		// }

		Input input = new Input();
		input.add(new StdinInput());

		// for (String line : stdin.readLines()) {
		//
		// System.out.println(line);

		// }

		Output output = new Output();
		output.add(new OutputStdout());

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
		ipo.launch(true);

		// logger.debug("end...");
	}

}
