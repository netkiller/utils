package cn.netkiller.ipo.test;

import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.input.FileInput;
import cn.netkiller.ipo.input.Input;
import cn.netkiller.ipo.output.Output;
import cn.netkiller.ipo.output.OutputStdout;
import cn.netkiller.ipo.process.NginxAccessProcess;
import cn.netkiller.ipo.process.Process;
import cn.netkiller.ipo.process.Replace;

public class NginxToJdbc {
	public static void main(String[] args) {
		// TODO code application logic here
		System.out.println("Starting...");

		FileInput fi = new FileInput("D:\\workspace\\ipo\\src\\main\\resources\\access.log");
		// System.out.print(fi.readLine());

		Input input = new Input();
		input.add(fi);
		// input.read();

		Output output = new Output();
		output.add(new OutputStdout());

		Process process = new Process();
		process.add(new NginxAccessProcess());

		InputProcessOutput ipo = new InputProcessOutput();
		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.launch();
	}
}
