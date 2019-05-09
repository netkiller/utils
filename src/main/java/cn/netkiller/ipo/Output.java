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

import cn.netkiller.ipo.output.OutputInterface;

/**
 *
 * @author netkiller
 */
public class Output implements OutputInterface {

	private final static Logger logger = LoggerFactory.getLogger(Output.class);

	private final List<OutputInterface> outputs = new ArrayList<OutputInterface>();

	public Output() {
		super();
	}

	public Output(OutputInterface outputInterface) {
		this.outputs.add(outputInterface);
	}

	public Output add(OutputInterface outputInterface) {
		this.outputs.add(outputInterface);
		return this;
	}

	public void open() {
		for (OutputInterface output : this.outputs) {
			output.open();
		}
	}

	public void write(Object tmp) {
		if (tmp != null) {
			for (OutputInterface output : this.outputs) {
				logger.debug("Output: {}", output.getClass().getName());
				output.write(tmp);
			}
		}

	}

	public void close() {
		for (OutputInterface output : this.outputs) {
			output.close();
		}
	}

}