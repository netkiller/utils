package cn.netkiller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class Application {

	private final static Logger logger = LoggerFactory.getLogger(Application.class);

	public Application() {
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		logger.info("Application");
	}

}
