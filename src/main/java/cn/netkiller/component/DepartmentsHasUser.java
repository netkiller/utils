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
import cn.netkiller.ipo.process.map.MapReplace;

@Component
@Order(40)
public class DepartmentsHasUser implements ApplicationRunner {
	private final static Logger logger = LoggerFactory.getLogger(DepartmentsHasUser.class);

	@Qualifier("inputJdbcTemplate")
	@Autowired
	private JdbcTemplate inputJdbcTemplate;

	@Qualifier("outputJdbcTemplate")
	@Autowired
	private JdbcTemplate outputJdbcTemplate;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		System.out.println(args.containsOption("table"));
		System.out.println(args.getOptionValues("table"));
		System.out.println(args.getOptionValues("table").equals("departments_has_user"));
		if (args.containsOption("table")) {
			if (!args.getOptionValues("table").get(0).equals("departments_has_user")) {
				return;
			}
		}
		System.out.println("参数");
		
		outputJdbcTemplate.execute("delete from lz_auth where created_by = 'import'");

		Input input = new Input(new LinkedHashMap<Object, Object>());
		Process process = new Process();
		Output output = new Output();
		Position position = new Position(new FilePosition("/tmp/pos.txt"), "id");

		input.add(new JdbcTemplateInput(inputJdbcTemplate, "select * from import_departments_has_user"));

		output.add(new JdbcTemplateOutput(outputJdbcTemplate, "lz_auth"));

		process.add(new MapReplace("created_time", null, "now()"));
		// process.add(new MapRemove("accept_type"));
		
		process.add(new MapPut("created_by", "import"));
		process.add(new MapPut("company_id", "1"));
		process.add(new MapPut("biz_post_id", "1"));

		InputProcessOutput ipo = new InputProcessOutput(this.getClass().getName());

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		// ipo.setPosition(position);
		// ipo.setPipeline(true);
		ipo.launch();

	}

}
