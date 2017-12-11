package cn.netkiller.ipo.test;

import java.util.Arrays;

import cn.netkiller.ipo.Input;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.Output;
import cn.netkiller.ipo.Process;
import cn.netkiller.ipo.input.KafkaInput;
import cn.netkiller.ipo.output.StdoutOutput;
import cn.netkiller.ipo.process.Replace;

public class KafkaConsumer {

	public static void main(String[] args) {

		KafkaInput kafka = new KafkaInput("123.207.61.225:9092", "test-consumer-group", Arrays.asList("test"));
		// kafka.open();
		// while (true) {
		// System.out.println(kafka.readLine());
		// System.out.println("=====================================");
		// }
		// kafka.close();
		Input input = new Input();
		input.add(kafka);
		// input.open();
		// while (true) {
		// for (String line : input.readLines()) {
		// System.out.println(line);
		// System.out.println("=====================================");
		// }
		// }

		Process process = new Process();
		process.add(new Replace("Hello", "Helloworld!!!"));

		Output output = new Output();
		output.add(new StdoutOutput());

		InputProcessOutput ipo = new InputProcessOutput();
		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setBatch(1);
		ipo.setPipeline(true);
		ipo.launch();

	}

}
