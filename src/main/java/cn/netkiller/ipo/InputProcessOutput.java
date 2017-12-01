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
 * @author netkiller
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

	public void launch() {

		logger.info("==================== Begin ====================");
		this.input.open();
		this.output.open();
		do {
			this.run(this.batchNumber);
//			logger.info("==================== Batch Done ====================");
		} while (this.input.hasNextLine());
		this.input.close();
		this.output.close();
		logger.info("==================== End ====================");

	}

	private void run(int batchNumber) {

		List<String> inputLines = new ArrayList<String>();
		
		for (int i = 0; i < this.batchNumber; i++) {
			List<String> lines = input.reads();
			for (String line : lines) {
				if (line != null) {
					inputLines.add(line);
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

	}

}
