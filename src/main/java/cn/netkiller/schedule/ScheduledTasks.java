package cn.netkiller.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
	private final static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	public ScheduledTasks() {
		// TODO Auto-generated constructor stub
	}

	@Scheduled(fixedRate = 5000) // 5秒运行一次调度任务
	public void echoCurrentTime() {
		logger.info("The time is now {}", dateFormat.format(new Date()));
	}

}
