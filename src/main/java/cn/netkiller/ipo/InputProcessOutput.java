/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.netkiller.ipo.input.InputInterface;
import cn.netkiller.ipo.input.StdinInput;
import cn.netkiller.ipo.output.OutputInterface;
import cn.netkiller.ipo.process.ProcessInterface;

/**
 *
 * @author netkiller
 */
public class InputProcessOutput {
	private final static Logger logger = LoggerFactory.getLogger(InputProcessOutput.class);

	private InputInterface input;
	private OutputInterface output;
	private ProcessInterface process;

	private int batchNumber = 0;
	private boolean exit = false;

	public void debug() {

	}

	public void setBatch(int batchNumber) {
		this.batchNumber = batchNumber;
	}

	public void launch() {

		logger.info("==================== Begin ====================");
		this.input.open();
		this.output.open();
		do {
			this.run(this.batchNumber);
			// logger.info("==================== Batch Done ====================");
		} while (this.input.hasNextLine());
		this.input.close();
		this.output.close();
		logger.info("==================== End ====================");

	}

	public void launch(boolean loop) {
		this.input.open();
		this.output.open();
		try {
			do {
				if (!this.run(this.batchNumber)) {
					Thread.sleep(1000);
				}
				if(exit) {
					break;
				}
			} while (loop);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.input.close();
		this.output.close();
	}

	private boolean run(int batchNumber) {
		boolean isNextBatch = false;
		List<String> inputLines = new ArrayList<String>();

		for (int i = 0; i < this.batchNumber; i++) {
			List<String> lines = input.readLines();
			for (String line : lines) {
				if (line != null) {
					inputLines.add(line);
					isNextBatch = true;
				}
			}
		}

		List<String> processLines = new ArrayList<String>();

		for (String proc : inputLines) {
			String line = this.process.run(proc);
			if (line != null) {
				processLines.add(line);
			}
		}

		for (String out : processLines) {
			this.output.write(out);
		}

		return isNextBatch;

	}
	public void shutdown() {
		this.exit = true;
	}
	
//	public void shutdown(Shutdown shutdown) {
//		
//		Thread exit = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					Thread.sleep(10000);
//					ipo.shutdown();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//		});
//		exit.setName("sendmail");
//		exit.start();
//	}
	
	public void setInput(InputInterface input) {
		this.input = input;
	}

	public void setProcess(ProcessInterface process) {
		this.process = process;
	}

	public void setOutput(OutputInterface output) {
		this.output = output;
	}

	public void setInput(StdinInput stdin) {
		this.input = stdin;
	}

	public void setProcess(Process process) {
		this.process = process;

	}

	public void setOutput(Output output) {
		this.output = output;

	}
}
