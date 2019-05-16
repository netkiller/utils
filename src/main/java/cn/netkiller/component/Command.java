package cn.netkiller.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cn.netkiller.service.AsyncService;
import cn.netkiller.ipo.Process;
import cn.netkiller.ipo.service.AliyunOssService;

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
	private AsyncService asyncService;

	@Autowired
	private AliyunOssService aliyunOssService;

	// @Autowired
	// private RedisTemplate<String, Object> redisTemplate;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// System.out.println("==ApplicationRunner=====" + Arrays.asList(args.getSourceArgs()));
		// System.out.println("==getOptionNames========" + args.getOptionNames());
		// System.out.println("==getOptionValues=======" + args.getOptionValues("table"));
		// System.out.println("==getOptionValues=======" + args.getOptionValues("developer.name"));

		// InputProcessOutputGroup group = new InputProcessOutputGroup();
		// group.add(asyncService.first());
		// group.launch();

		// asyncService.first();
		// asyncService.second();

		// String objectName = "test/aabbcc.png";
		// this.aliyunOssService.open();
		// this.aliyunOssService.uploadFromUrl(objectName, "https://www.baidu.com/img/bd_logo1.png");
		// this.aliyunOssService.close();
//		logger.debug("http://lz-omcloud-test.oss-cn-shenzhen.aliyuncs.com/" + objectName);
//
//		System.exit(0);
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

	//
	// logger.debug("end...");
	//
	// }
	//
}
