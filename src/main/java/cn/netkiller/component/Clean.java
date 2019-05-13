package cn.netkiller.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Order(5)
public class Clean implements ApplicationRunner {
	@Qualifier("inputJdbcTemplate")
	@Autowired
	private JdbcTemplate inputJdbcTemplate;

	@Qualifier("outputJdbcTemplate")
	@Autowired
	private JdbcTemplate outputJdbcTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public Clean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		//
		// outputJdbcTemplate.execute("delete from lz_departments where created_by = 'import'");
		// outputJdbcTemplate.execute("delete from lz_auth where created_by = 'import'");

	}

}
