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

import cn.netkiller.ipo.input.Input;
import cn.netkiller.ipo.output.Output;
import cn.netkiller.ipo.process.Process;

/**
 *
 * @author neoch
 */
public class InputProcessOutput {
	private final static Logger logger = LoggerFactory.getLogger(InputProcessOutput.class);

	private Input input;
	private Output output;
	private Process process;

	private int batchNumber = 0;

	public void setInput(Input input) {
		this.input = input;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public void setOutput(Output output) {
		this.output = output;
	}

	public void debug() {

	}

	public void setBatch(int batchNumber) {
		this.batchNumber = batchNumber;
	}

	private void run() {
		logger.info("String input ...");
		String line = null;

		while ((line = input.read()) != null) {
			// System.out.println(line);
			String tmp = this.process.run(line);
			if (tmp != null) {
				this.output.open();
				this.output.write(tmp);
				this.output.close();
			}
		}
	}

	public void launch() {
		if (this.batchNumber == 1) {
			this.run();
		} else {
			this.run(this.batchNumber);
		}

		// this.output.write("Helloword");
	}

	private void run(int batchNumber) {
		List<String> inputLines = new ArrayList<String>();
		for (int i = 0; i < this.batchNumber; i++) {
			String line = input.read();
			if (line != null) {
				inputLines.add(line);
			}
		}

		List<String> processLines = new ArrayList<String>();

		for (String proc : inputLines) {
			String line = this.process.run(proc);
			if (line != null) {
				processLines.add(line);
			}

		}
		this.output.open();
		for (String out : processLines) {
			this.output.write(out);
		}
		this.output.close();
	}
}
