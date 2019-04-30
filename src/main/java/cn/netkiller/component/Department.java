package cn.netkiller.component;

import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cn.netkiller.ipo.Input;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.Output;
import cn.netkiller.ipo.Position;
import cn.netkiller.ipo.Process;
import cn.netkiller.ipo.input.JdbcTemplateInput;
import cn.netkiller.ipo.output.JdbcTemplateOutput;
import cn.netkiller.ipo.position.FilePosition;
import cn.netkiller.ipo.process.map.MapPut;
import cn.netkiller.ipo.process.map.MapRemove;
import cn.netkiller.ipo.process.map.MapReplace;

@Component
@Order(2)
public class Department implements ApplicationRunner {
	private final static Logger logger = LoggerFactory.getLogger(Department.class);
	@Qualifier("inputJdbcTemplate")
	@Autowired
	private JdbcTemplate inputJdbcTemplate;

	@Qualifier("outputJdbcTemplate")
	@Autowired
	private JdbcTemplate outputJdbcTemplate;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		logger.debug("==================== Department ====================");

		outputJdbcTemplate.execute("delete from lz_departments where created_by = 'import'");

		Input input = new Input(new LinkedHashMap<Object, Object>());
		Process process = new Process();
		Output output = new Output();
		Position position = new Position(new FilePosition("/tmp/pos.txt"), "id");

		input.add(new JdbcTemplateInput(inputJdbcTemplate, "select * from import_departments"));

		output.add(new JdbcTemplateOutput(outputJdbcTemplate, "lz_departments"));
		// output.add(new StdoutOutput());

		// process.add(new MapReplace("name", "公司", "====="));
		// process.add(new MapRemove("accept_type"));
		process.add(new MapPut("created_by", "import"));
		process.add(new MapPut("company_id", "1"));

		InputProcessOutput ipo = new InputProcessOutput();

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		// ipo.setPosition(position);
		// ipo.setPipeline(true);
		ipo.launch();
	}
}
