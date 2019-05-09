/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.netkiller.ipo.process.ProcessInterface;

import java.util.ArrayList;

/**
 *
 * @author netkiller
 */
public class Process implements ProcessInterface {
	private final static Logger logger = LoggerFactory.getLogger(Process.class);
	private final List<ProcessInterface> processes = new ArrayList<ProcessInterface>();
	// private final List<ProcessMapInterface> processes = new ArrayList<ProcessMapInterface>();

	public Process() {
	}

	public Process(ProcessInterface processInterface) {
		this.processes.add((ProcessInterface) processInterface);
	}

	public Process add(ProcessInterface processInterface) {
		this.processes.add(processInterface);
		return this;
	}

	public Object run(Object row) {
		// String line = tmp;
		logger.debug("Process source: {}", row.toString());
		for (ProcessInterface process : this.processes) {
			row = process.run(row);
			if (row == null) {
				break;
			}
		}
		logger.debug("Process target: {}", row.toString());
		// this.processes.forEach((proc) -> {
		// System.out.println(proc.toString());
		// line = proc.run(line);
		// });
		return row;
	}

}
