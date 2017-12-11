package cn.netkiller.ipo.test;

import cn.netkiller.ipo.Input;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.Output;
import cn.netkiller.ipo.Process;
import cn.netkiller.ipo.input.FileInput;
import cn.netkiller.ipo.output.StdoutOutput;
import cn.netkiller.ipo.process.json.JsonValueLength;

public class JsonTest {

	public static void main(String[] args) {

		Input input = new Input();
		input.add(new FileInput("D:\\workspace\\ipo\\target\\project\\test.json"));

		Process process = new Process();
		process.add(new JsonValueLength(100));

		Output output = new Output();
		output.add(new StdoutOutput());

		InputProcessOutput ipo = new InputProcessOutput();
		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setBatch(2);
		ipo.launch();

	}

}
