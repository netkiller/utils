package cn.netkiller.component;

import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cn.netkiller.ipo.Input;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.Output;
import cn.netkiller.ipo.Position;
import cn.netkiller.ipo.Process;
import cn.netkiller.ipo.input.JdbcTemplateInput;
import cn.netkiller.ipo.output.JdbcTemplateOutput;
import cn.netkiller.ipo.output.StdoutOutput;
import cn.netkiller.ipo.position.FilePosition;
import cn.netkiller.ipo.position.RedisPosition;
import cn.netkiller.ipo.process.map.MapPut;
import cn.netkiller.ipo.process.map.MapRemove;
import cn.netkiller.ipo.process.map.MapReplace;

@Component
@Order(30)
public class UserData implements ApplicationRunner {

	private final static Logger logger = LoggerFactory.getLogger(UserData.class);

	// @Autowired
	// private JdbcTemplate jdbcTemplate;

	@Qualifier("inputJdbcTemplate")
	@Autowired
	private JdbcTemplate inputJdbcTemplate;

	@Qualifier("outputJdbcTemplate")
	@Autowired
	private JdbcTemplate outputJdbcTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		if (args.containsOption("table")) {
			if (!args.getOptionValues("table").equals("user")) {
				return;
			}

		}

		logger.debug("==================== User ====================");

		// String query = "SELECT id, title, content from article where id = ";

		// jdbcTemplate.queryForObject(query, (resultSet, i) -> {
		// System.out.println(resultSet.getLong(1)+","+ resultSet.getString(2)+","+ resultSet.getString(3));
		// });

		// String jdbc = jdbcTemplate.queryForObject("select name from saas.tbl_eos_contracts limit 1", String.class);
		// logger.warn(jdbc);

		// var rows = inputJdbcTemplate.queryForList("select * from saas.tbl_eos_contracts");
		// for (Map<String, Object> row : rows) {
		// logger.warn(row.toString());
		// }

		// String input = inputJdbcTemplate.queryForObject("select name from test limit 1", String.class);
		// logger.warn(input);
		// String string = outputJdbcTemplate.queryForObject("select name from lz_users limit 1", String.class);
		// logger.warn(string);

		// TRUNCATE `test`.`lz_users`;
		outputJdbcTemplate.execute("delete from lz_users where created_by = 'import'");

		Input input = new Input(new LinkedHashMap<Object, Object>());
		Process process = new Process();
		Output output = new Output();
		Position position = new Position(new RedisPosition(stringRedisTemplate, this.getClass().getName()), "id");

		// // StdinInput stdin = new StdinInput();
		input.add(new JdbcTemplateInput(inputJdbcTemplate, "select * from import_users"));

		output.add(new JdbcTemplateOutput(outputJdbcTemplate, "lz_users"));
		output.add(new StdoutOutput());

		process.add(new MapReplace("parent_id", null, "NULL"));
		process.add(new MapRemove("accept_type"));
		process.add(new MapPut("created_by", "import"));
		process.add(new MapReplace("user_pwd", null, "123456"));

		InputProcessOutput ipo = new InputProcessOutput();

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setPosition(position);
		ipo.launch();

	}

}
