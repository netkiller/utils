/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo;

import java.util.List;

import cn.netkiller.ipo.process.ExcludeProcess;
import cn.netkiller.ipo.process.IncludeProcess;
import cn.netkiller.ipo.process.ProcessInterface;
import cn.netkiller.ipo.process.Replace;
import cn.netkiller.ipo.process.nginx.NginxAccessGetParameterProcess;
import cn.netkiller.ipo.process.nginx.NginxAccessProcess;

import java.util.ArrayList;

/**
 *
 * @author neoch
 */
public class Process implements ProcessInterface {
	private final List<ProcessInterface> process = new ArrayList<ProcessInterface>();

	public Process() {
	}

	public Process add(Replace process) {
		this.process.add(process);
		return this;
	}

	public String run(String tmp) {
		String line = tmp;
		// System.out.println(line);
		for (ProcessInterface proc : this.process) {
			line = proc.run(line);
			if (line == null) {
				break;
			}
		}
		// this.process.forEach((proc) -> {
		// System.out.println(proc.toString());
		// line = proc.run(line);
		// });
		return line;
	}

	public Process add(NginxAccessProcess nginxAccessProcess) {
		this.process.add(nginxAccessProcess);
		return this;
	}

	public Process add(ExcludeProcess excludeProcess) {
		this.process.add(excludeProcess);
		return this;
	}

	public Process add(IncludeProcess includeProcess) {
		this.process.add(includeProcess);
		return this;
	}

	public Process add(NginxAccessGetParameterProcess nginxAccessGetParameterProcess) {
		this.process.add(nginxAccessGetParameterProcess);
		return this;

	}

	public Process add(Object object) {
		this.process.add((ProcessInterface) object);
		return this;
	}
}
