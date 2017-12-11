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
import cn.netkiller.ipo.output.OutputInterface;
import cn.netkiller.ipo.process.ProcessInterface;

/**
 *
 * @author netkiller
 */
public class InputProcessOutput implements Runnable {
	private final static Logger logger = LoggerFactory.getLogger(InputProcessOutput.class);

	private Input input;
	private Output output;
	private Process process;

	private int batchNumber = 0;
	private boolean exit = false;
	private boolean pipeline = false;
	private String name = "";

	public InputProcessOutput() {
		this.name = this.getClass().getName();
	}

	public InputProcessOutput(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBatch(int batchNumber) {
		this.batchNumber = batchNumber;
	}

	public int getBatch() {
		return this.batchNumber;
	}

	public void setPipeline(boolean pipeline) {
		this.pipeline = pipeline;
	}

	public boolean getPipeline() {
		return this.pipeline;
	}

	public void launch() {
		this.run();
	}

	@Override
	public void run() {
		logger.debug("==================== Begin {} ====================", this.name);
		this.input.open();
		this.output.open();
		if (this.pipeline) {
			try {
				do {
					if (!this.execute(this.batchNumber)) {
						Thread.sleep(1000);
					}
					if (exit) {
						this.pipeline = false;
					}
					logger.debug("shutdown() => {}", exit);
				} while (this.pipeline);
			} catch (InterruptedException e) {
				logger.debug(e.getMessage());
			}
		} else {
			do {
				this.execute(this.batchNumber);
				// logger.info("==================== Batch Done ====================");
				if (exit) {
					break;
				}
			} while (this.input.hasNextLine());
		}
		this.input.close();
		this.output.close();
		logger.debug("==================== End {} ====================", this.name);
	}

	private boolean execute(int batchNumber) {
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

	public void setInput(InputInterface input) {
		this.input = (Input) input;
	}

	public void setProcess(ProcessInterface process) {
		this.process = (Process) process;
	}

	public void setOutput(OutputInterface output) {
		this.output = (Output) output;
	}

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public Output getOutput() {
		return output;
	}

	public void setOutput(Output output) {
		this.output = output;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

}
