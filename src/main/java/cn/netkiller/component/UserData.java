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
import cn.netkiller.ipo.util.SqlUtil.SQL;

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
			if (!args.getOptionValues("table").equals("department")) {
				return;
			}
		}
		this.users();
		this.department();
		this.departmentsHasUser();
	}

	public void users() {
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

		Input input = new Input(new LinkedHashMap<Object, Object>());
		Process process = new Process();
		Output output = new Output();
		Position position = new Position(new RedisPosition(stringRedisTemplate, "User"), "user_id");
		// position.reset();

		String id = position.get();
		String sql = "select * from import_users";
		if (id != null) {
			sql += " where user_id > " + id;
		}

		// StdinInput stdin = new StdinInput();
		input.add(new JdbcTemplateInput(inputJdbcTemplate, sql));

		output.add(new JdbcTemplateOutput(outputJdbcTemplate, "lz_users", SQL.REPLACE));
		// output.add(new StdoutOutput());

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

	public void department() {

		logger.debug("==================== Department ====================");

		Input input = new Input(new LinkedHashMap<Object, Object>());
		Process process = new Process();
		Output output = new Output();
		Position position = new Position(new RedisPosition(stringRedisTemplate, "Department"), "dept_id");

		String dept_id = position.get();
		String sql = "select * from import_departments";
		if (dept_id != null) {
			sql += " where dept_id > " + dept_id;
		}

		input.add(new JdbcTemplateInput(inputJdbcTemplate, sql));

		output.add(new JdbcTemplateOutput(outputJdbcTemplate, "lz_departments"));
		// output.add(new StdoutOutput());

		process.add(new MapReplace("created_time", null, "now()"));
		// process.add(new MapRemove("accept_type"));
		process.add(new MapPut("created_by", "import"));
		process.add(new MapPut("company_id", "1"));

		InputProcessOutput ipo = new InputProcessOutput();

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setPosition(position);
		ipo.launch();
	}

	public void departmentsHasUser() {

		Input input = new Input(new LinkedHashMap<Object, Object>());
		Process process = new Process();
		Output output = new Output();
		Position position = new Position(new RedisPosition(stringRedisTemplate, "departmentsHasUser"), "id");

		String id = position.get();
		String sql = "select * from import_departments_has_user";
		if (id != null) {
			sql += " where id > " + id;
		}

		input.add(new JdbcTemplateInput(inputJdbcTemplate, sql));

		output.add(new JdbcTemplateOutput(outputJdbcTemplate, "lz_auth", SQL.REPLACE));

		process.add(new MapReplace("created_time", null, "now()"));
		// process.add(new MapRemove("accept_type"));

		process.add(new MapPut("created_by", "import"));
		process.add(new MapPut("company_id", "1"));
		process.add(new MapPut("biz_post_id", "1"));

		InputProcessOutput ipo = new InputProcessOutput(this.getClass().getName());

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setPosition(position);
		ipo.launch();

	}
}
