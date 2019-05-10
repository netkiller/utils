package cn.netkiller.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import cn.netkiller.ipo.Input;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.Output;
import cn.netkiller.ipo.Process;
import cn.netkiller.ipo.input.MapInput;
import cn.netkiller.ipo.input.StringInput;
import cn.netkiller.ipo.output.RedisMessagePublisher;
import cn.netkiller.ipo.process.string.Replace;

@Service
public class AsyncService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public AsyncService() {
		// TODO Auto-generated constructor stub
	}

	// @Async
	public InputProcessOutput first() {

		// RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();

		//
		Input input = new Input(new StringInput(Arrays.asList("Helloworld!!!", "Java", "Linux")));
		Output output = new Output(new RedisMessagePublisher(stringRedisTemplate, "test"));
		// StdinInput stdin = new StdinInput();
		// input.add(new StdinInput());
		// input.add(new FileInput(file.getURI().getPath()));

		// output.add(new StdoutOutput());
		Process process = new Process();
		// process.add(new Replace("Hello", "Netkiller "));
		// process.add(new Replace("Neo", "<Neo>"));
		process.add(new Replace("Linux", "FreeBSD"));

		InputProcessOutput ipo = new InputProcessOutput();

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.launch();
		return ipo;
	}

	@Async
	public void second() {
		Map<String, Object> data = new HashMap<String, Object>() {
			{
				put("name", "neo");
			}
		};
		Input input = new Input(new MapInput(data));
		Output output = new Output(new RedisMessagePublisher(stringRedisTemplate, "test"));
		InputProcessOutput ipo = new InputProcessOutput();

		ipo.setInput(input);
		// ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.launch();
	}
}
