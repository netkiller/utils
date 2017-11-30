package cn.netkiller.ipo.test;

import java.util.Map;
import java.io.File;
import java.util.LinkedHashMap;

import cn.netkiller.ipo.Config;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.input.FileInput;
import cn.netkiller.ipo.input.Input;
import cn.netkiller.ipo.output.Output;
import cn.netkiller.ipo.output.OutputJdbc;
import cn.netkiller.ipo.output.OutputStdout;
import cn.netkiller.ipo.process.ExcludeProcess;
import cn.netkiller.ipo.process.Process;
import cn.netkiller.ipo.process.nginx.NginxAccessGetParameterProcess;
import cn.netkiller.ipo.process.nginx.NginxAccessProcess;

public class NginxToJdbc {
	public static void main(String[] args) {

		Config config = new Config();

		System.out.println(config.get("output.jdbc.url"));
		String filename = String.format("%s%s%s%s%s", config.get("input.file.path"), File.separator, config.get("input.file.prefix"), config.get("input.file.filename"), config.get("input.file.suffix"));
		System.out.println(filename);
		FileInput fi = new FileInput(filename);

		Input input = new Input();
		input.add(fi);

		Process process = new Process();
//		process.add(new NginxAccessProcess());
		process.add(new NginxAccessGetParameterProcess());
//		process.add(new ExcludeProcess("\"logType\":\"1\""));

		Output output = new Output();
		output.add(new OutputStdout());

		Map<String, String> map = new LinkedHashMap<String, String>();

		map.put("logType", "logType");
		map.put("deviceId", "deviceId");

		OutputJdbc jdbc = new OutputJdbc("stat_app_log", map);
		jdbc.setDriver(config.get("output.jdbc.driver"));
		jdbc.setUrl(config.get("output.jdbc.url"));
		jdbc.setUsername(config.get("output.jdbc.username"));
		jdbc.setPassword(config.get("output.jdbc.password"));

//		output.add(jdbc);

		InputProcessOutput ipo = new InputProcessOutput();
		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setBatch(10);
		ipo.launch();
	}
}
