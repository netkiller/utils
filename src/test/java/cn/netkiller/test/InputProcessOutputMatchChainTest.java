package cn.netkiller.test;

import cn.netkiller.ipo.Input;
import cn.netkiller.ipo.InputProcessOutputMatchChain;
import cn.netkiller.ipo.Output;
import cn.netkiller.ipo.Process;
import cn.netkiller.ipo.input.FileInput;
import cn.netkiller.ipo.output.StdoutOutput;
import cn.netkiller.ipo.process.string.StringInclude;
import cn.netkiller.ipo.process.string.StringReplace;

public class InputProcessOutputMatchChainTest {

	public static void main(String[] args) {

		Input input = new Input();
		input.add(new FileInput("D:\\workspace\\ipo\\target\\project\\input.txt"));

		Process process = new Process();
		process.add(new StringInclude("Neo"));
		process.add(new StringReplace("Neo", "Netkiller "));

		Process process1 = new Process();
		process1.add(new StringInclude("Hello"));
		process1.add(new StringReplace("Hello", "Helloworld!!!"));

		Output output = new Output();
		output.add(new StdoutOutput());

		InputProcessOutputMatchChain mc = new InputProcessOutputMatchChain();
		mc.setInput(input);
		mc.match(process, output);
		mc.match(process1, output);
		mc.launch();
		// mc.match(new JsonPrcess(), new JsonOutput());

	}

}
