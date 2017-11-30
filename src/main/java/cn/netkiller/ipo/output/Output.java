/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo.output;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author neoch
 */
public class Output {

	private final List<OutputInterface> outputs = new ArrayList<OutputInterface>();

	public void open() {
		for (OutputInterface output : this.outputs) {
			output.open();
		}
	}

	public void write(String line) {
		if (line != null) {
			for (OutputInterface output : this.outputs) {
				output.write(line);
			}
		}

	}

	public void close() {
		for (OutputInterface output : this.outputs) {
			output.close();
		}
	}

	public Output add(OutputStdout outputStdout) {
		this.outputs.add(outputStdout);
		return this;
	}

	public Output add(OutputJson outputJson) {
		this.outputs.add(outputJson);
		return this;

	}

	public Output add(OutputJdbc outputJdbc) {
		this.outputs.add(outputJdbc);
		return this;
	}

}