/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo.process;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author neoch
 */
public class Process {
	private final List<ProcessInterface> process = new ArrayList<ProcessInterface>();

	public void add(Replace process) {
		this.process.add(process);
	}

	public String run(String tmp) {
		String line = tmp;
		for (ProcessInterface proc : this.process) {
			line = proc.run(line);
		}
		// this.process.forEach((proc) -> {
		// System.out.println(proc.toString());
		// line = proc.run(line);
		// });
		return line;
	}

	public void add(NginxAccessProcess nginxAccessProcess) {
		this.process.add(nginxAccessProcess);

	}

	public void add(DropProcess dropProcess) {
		this.process.add(dropProcess);

	}
}
