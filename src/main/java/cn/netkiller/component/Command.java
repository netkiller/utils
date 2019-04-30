package cn.netkiller.component;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

//
//import cn.netkiller.Application;
//import cn.netkiller.ipo.Input;
//import cn.netkiller.ipo.InputProcessOutput;
//import cn.netkiller.ipo.Output;
//import cn.netkiller.ipo.Process;
//import cn.netkiller.ipo.input.FileInput;
//import cn.netkiller.ipo.output.StdoutOutput;
//import cn.netkiller.ipo.process.Replace;
//
@Component
@Order(1)
public class Command implements ApplicationRunner {
	private final static Logger logger = LoggerFactory.getLogger(Command.class);

	//
	// @Value("classpath:input.txt")
	// private Resource file;
	//
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("==ApplicationRunner=====" + Arrays.asList(args.getSourceArgs()));
		// System.out.println("==getOptionNames========" + args.getOptionNames());
		System.out.println("==getOptionValues=======" + args.getOptionValues("table"));
		// System.out.println("==getOptionValues=======" + args.getOptionValues("developer.name"));
		// System.exit(0);
	}
	// System.out.println("初始化业务");
	//
	// logger.debug("begin...");
	//
	// logger.warn("this.getClass().getResource(\"\").getPath() = {}", this.getClass().getResource("").getPath());
	// // logger.warn(new ClassPathResource("logback.xml").getURI().getPath());
	// logger.warn("new ClassPathResource(\"\").getURI().getPath() = {}", new ClassPathResource("").getURI().getPath());
	// // logger.warn(this.getClass().getResource("input.txt").getPath());
	// // logger.warn(this.getClass().getClassLoader().getResource("classpath:input.txt").getPath());
	// logger.warn(file.getURI().getPath());
	// // this.getClass().getResourceAsStream("/SomeTextFile.txt");
	//
	// Input input = new Input();
	// // StdinInput stdin = new StdinInput();
	// // input.add(new StdinInput());
	//
	// input.add(new FileInput(file.getURI().getPath()));
	//
	// Output output = new Output();
	// output.add(new StdoutOutput());
	//
	// Process process = new Process();
	// process.add(new Replace("Hello", "Netkiller "));
	// process.add(new Replace("Neo", "<Neo>"));
	// process.add(new Replace("Tom", "[Tom]"));
	// //
	// InputProcessOutput ipo = new InputProcessOutput();
	//
	//// Thread exit = new Thread(new Runnable() {
	//// @Override
	//// public void run() {
	//// try {
	//// Thread.sleep(10000);
	//// ipo.shutdown();
	//// } catch (InterruptedException e) {
	//// // TODO Auto-generated catch block
	//// e.printStackTrace();
	//// }
	////
	//// }
	//// });
	//// exit.setName("shutdown");
	//// exit.start();
	//
	// ipo.setInput(input);
	// ipo.setProcess(process);
	// ipo.setOutput(output);
	// ipo.setBatch(5);
	// ipo.setPipeline(true);
	// ipo.launch();
	//
	// logger.debug("end...");
	//
	// }
	//
}
