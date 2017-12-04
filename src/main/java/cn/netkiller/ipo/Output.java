/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo;

import java.util.ArrayList;
import java.util.List;

import cn.netkiller.ipo.output.OutputInterface;
import cn.netkiller.ipo.output.OutputJdbc;
import cn.netkiller.ipo.output.OutputJson;
import cn.netkiller.ipo.output.OutputStdout;

/**
 *
 * @author neoch
 */
public class Output implements OutputInterface {

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