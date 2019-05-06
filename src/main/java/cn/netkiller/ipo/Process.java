/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.netkiller.ipo.process.ProcessInterface;
import cn.netkiller.ipo.process.ProcessMapInterface;
import cn.netkiller.ipo.process.json.JsonValueLength;
import cn.netkiller.ipo.process.map.MapLeft;
import cn.netkiller.ipo.process.map.MapPut;
import cn.netkiller.ipo.process.map.MapRemove;
import cn.netkiller.ipo.process.map.MapReplace;
import cn.netkiller.ipo.process.nginx.NginxAccessGetParameterProcess;
import cn.netkiller.ipo.process.nginx.NginxAccessProcess;
import cn.netkiller.ipo.process.string.Exclude;
import cn.netkiller.ipo.process.string.Include;
import cn.netkiller.ipo.process.string.Replace;

import java.util.ArrayList;

/**
 *
 * @author neoch
 */
public class Process implements ProcessInterface {
	private final static Logger logger = LoggerFactory.getLogger(Process.class);
	private final List<ProcessInterface> processes = new ArrayList<ProcessInterface>();
	private final List<ProcessMapInterface> processesMap = new ArrayList<ProcessMapInterface>();

	public Process() {
	}

	public Process add(Replace process) {
		this.processes.add(process);
		return this;
	}

	public String run(String tmp) {
		String line = tmp;
		// System.out.println(line);
		for (ProcessInterface process : this.processes) {
			line = process.run(line);
			if (line == null) {
				break;
			}
		}
		// this.processes.forEach((proc) -> {
		// System.out.println(proc.toString());
		// line = proc.run(line);
		// });
		return line;
	}

	public Map<String, Object> run(Map<String, Object> tmp) {
		Map<String, Object> line = tmp;
		// System.out.println(line);
		logger.debug("Process source: {}", tmp.toString());
		for (ProcessMapInterface process : this.processesMap) {
			line = process.run(tmp);
			if (line == null) {
				break;
			}
		}
		logger.debug("Process target: {}", line.toString());
		return line;
	}

	public Process add(NginxAccessProcess nginxAccessProcess) {
		this.processes.add(nginxAccessProcess);
		return this;
	}

	public Process add(Exclude excludeProcess) {
		this.processes.add(excludeProcess);
		return this;
	}

	public Process add(Include includeProcess) {
		this.processes.add(includeProcess);
		return this;
	}

	public Process add(NginxAccessGetParameterProcess nginxAccessGetParameterProcess) {
		this.processes.add(nginxAccessGetParameterProcess);
		return this;

	}

	public Process add(MapReplace mapReplace) {
		this.processesMap.add(mapReplace);
		return this;

	}

	public Process add(MapRemove mapRemove) {
		this.processesMap.add(mapRemove);
		return this;

	}

	public Process add(JsonValueLength jsonValueLength) {
		this.processes.add(jsonValueLength);
		return this;

	}

	public Process add(MapPut mapPut) {
		this.processesMap.add(mapPut);
		return this;

	}

	public Process add(MapLeft mapLeft) {
		this.processesMap.add(mapLeft);
		return this;

	}

	// public Process add(Object object) {
	// this.processes.add((ProcessInterface) object);
	// return this;
	// }
}
