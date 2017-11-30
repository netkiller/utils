package cn.netkiller.ipo.test;

import java.util.Map;
import java.util.LinkedHashMap;

import cn.netkiller.ipo.Config;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.input.FileInput;
import cn.netkiller.ipo.input.Input;
import cn.netkiller.ipo.output.Output;
import cn.netkiller.ipo.output.OutputJdbc;
import cn.netkiller.ipo.output.OutputStdout;
import cn.netkiller.ipo.process.DropProcess;
import cn.netkiller.ipo.process.NginxAccessProcess;
import cn.netkiller.ipo.process.Process;

public class NginxToJdbc {
	public static void main(String[] args) {


		Config config = new Config();
		System.out.println(config.get("output.jdbc.url"));

		
		
		FileInput fi = new FileInput(String.format("%s/%s%s%s", config.get("input.file.path"), config.get("input.file.prefix"), config.get("input.file.filename"), config.get("input.file.suffix")));

		Input input = new Input();
		input.add(fi);

		Process process = new Process();
		process.add(new NginxAccessProcess());
		process.add(new DropProcess("\"logType\":\"1\""));

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

		output.add(jdbc);

		InputProcessOutput ipo = new InputProcessOutput();
		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setBatch(10);
		ipo.launch();
	}
}
