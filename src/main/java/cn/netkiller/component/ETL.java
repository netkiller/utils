package cn.netkiller.component;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cn.netkiller.Application;
import cn.netkiller.ipo.Input;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.Output;
import cn.netkiller.ipo.Process;
import cn.netkiller.ipo.input.FileInput;
import cn.netkiller.ipo.output.StdoutOutput;
import cn.netkiller.ipo.process.Replace;

@Component
public class ETL implements ApplicationRunner {

	private final static Logger logger = LoggerFactory.getLogger(ETL.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Qualifier("masterJdbcTemplate")
	@Autowired
	private JdbcTemplate masterJdbcTemplate;

	@Qualifier("slaveJdbcTemplate")
	@Autowired
	private JdbcTemplate slaveJdbcTemplate;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		String query = "SELECT id, title, content from article where id = ";

		// jdbcTemplate.queryForObject(query, (resultSet, i) -> {
		// System.out.println(resultSet.getLong(1)+","+ resultSet.getString(2)+","+ resultSet.getString(3));
		// });

		// String jdbc = jdbcTemplate.queryForObject("select name from saas.tbl_eos_contracts limit 1", String.class);
		// logger.warn(jdbc);

		var rows = jdbcTemplate.queryForList("select * from saas.tbl_eos_contracts");
		for (Map<String, Object> row : rows) {

			// tag.setId((Integer) row.get("id"));
			// tag.setName((String) row.get("name"));
			logger.warn(row.toString());
		}

		String master = masterJdbcTemplate.queryForObject("select name from test limit 1", String.class);
		logger.warn(master);

		String slave = slaveJdbcTemplate.queryForObject("select name from test limit 1", String.class);
		logger.warn(slave);

		//
		// Input input = new Input();
		// // StdinInput stdin = new StdinInput();
		// // input.add(new StdinInput());
		//
		// // input.add(new FileInput(file.getURI().getPath()));
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
		// // Thread exit = new Thread(new Runnable() {
		// // @Override
		// // public void run() {
		// // try {
		// // Thread.sleep(10000);
		// // ipo.shutdown();
		// // } catch (InterruptedException e) {
		// // // TODO Auto-generated catch block
		// // e.printStackTrace();
		// // }
		// //
		// // }
		// // });
		// // exit.setName("shutdown");
		// // exit.start();
		//
		// ipo.setInput(input);
		// ipo.setProcess(process);
		// ipo.setOutput(output);
		// ipo.setBatch(5);
		// ipo.setPipeline(true);
		// ipo.launch();

	}

}
