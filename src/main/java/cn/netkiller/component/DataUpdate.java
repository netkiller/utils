package cn.netkiller.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component

@Order(50)
public class DataUpdate implements ApplicationRunner {
	private final static Logger logger = LoggerFactory.getLogger(DataUpdate.class);

	public void run(ApplicationArguments args) throws Exception {

		//
		System.exit(0);
	}

}
