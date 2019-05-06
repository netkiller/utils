/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
	private Position position;

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
					if (!this.execute()) {
						Thread.sleep(1000);
					}
					if (exit) {
						this.pipeline = false;
					}
					// logger.debug("shutdown() => {}", exit);
				} while (this.pipeline);
			} catch (InterruptedException e) {
				logger.debug(e.getMessage());
			}
		} else {
			do {
				this.execute();
				logger.debug("==================== " + this.name + " ====================");
				if (exit) {
					break;
				}
			} while (this.input.hasNext());
		}
		this.input.close();
		this.output.close();
		logger.debug("==================== End {} ====================", this.name);
	}

	private boolean execute() {
		Object dataType = input.getDataType();
		// logger.debug(input.getDataType().getClass().getTypeName());
		if (dataType instanceof String) {
			this.string();
		}

		if (dataType instanceof HashMap || dataType instanceof LinkedHashMap) {
			this.map();
		}

		return false;

	}

	private void string() {

		String line = (String) input.readLine();
		if (line != null) {
			String tmp = this.process.run(line);
			if (tmp != null) {
				this.output.write(tmp);
			}
		}

	}

	private void map() {

		@SuppressWarnings("unchecked")
		Map<String, Object> output = (Map<String, Object>) input.readLine();
		if (output != null) {
			if (this.process != null) {
				output = this.process.run(output);
			}
			if (output != null) {
				this.output.write(output);
				if (this.position != null) {
					this.position.set(output);
				}
			}
		}

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

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
