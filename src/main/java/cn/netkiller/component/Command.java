package cn.netkiller.component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cn.netkiller.ipo.Input;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.InputProcessOutputGroup;
import cn.netkiller.ipo.Output;
import cn.netkiller.ipo.input.MapInput;
import cn.netkiller.ipo.input.StringInput;
import cn.netkiller.ipo.output.RedisMessagePublisher;
import cn.netkiller.ipo.Process;

@Component
@Order(1)
public class Command implements ApplicationRunner {
	private final static Logger logger = LoggerFactory.getLogger(Command.class);

	//
	// @Value("classpath:input.txt")
	// private Resource file;
	//

	@Qualifier("inputJdbcTemplate")
	@Autowired
	private JdbcTemplate inputJdbcTemplate;

	@Qualifier("outputJdbcTemplate")
	@Autowired
	private JdbcTemplate outputJdbcTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	// @Autowired
	// private RedisTemplate<String, Object> redisTemplate;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// System.out.println("==ApplicationRunner=====" + Arrays.asList(args.getSourceArgs()));
		// System.out.println("==getOptionNames========" + args.getOptionNames());
		// System.out.println("==getOptionValues=======" + args.getOptionValues("table"));
		// System.out.println("==getOptionValues=======" + args.getOptionValues("developer.name"));

		// InputProcessOutputGroup group = new InputProcessOutputGroup();
		// group.add(this.first());
		// group.launch();

		this.first();

//		System.exit(0);
	}

	public InputProcessOutput first() {

		Map<String, Object> data = new HashMap<String, Object>() {
			{
				put("name", "neo");
			}
		};

		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();

		// Input input = new Input(new MapInput(data));
		Input input = new Input(new StringInput("Helloworld!!!"));
		Output output = new Output(new RedisMessagePublisher(stringRedisTemplate, "test"));
		// // StdinInput stdin = new StdinInput();
		// // input.add(new StdinInput());
		// input.add(new FileInput(file.getURI().getPath()));

		// output.add(new StdoutOutput());
		//
		Process process = new Process();
		// process.add(new Replace("Hello", "Netkiller "));
		// process.add(new Replace("Neo", "<Neo>"));
		// process.add(new Replace("Tom", "[Tom]"));
		// //
		InputProcessOutput ipo = new InputProcessOutput();

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.launch();
		return ipo;
	}

	public void s22s() {

		// Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
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
