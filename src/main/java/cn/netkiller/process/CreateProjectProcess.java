package cn.netkiller.process;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import cn.netkiller.ipo.output.JdbcTemplateOutput;
import cn.netkiller.ipo.process.ProcessInterface;
import cn.netkiller.ipo.util.SqlUtil.SQL;

public class CreateProjectProcess implements ProcessInterface {

	private JdbcTemplate outputJdbcTemplate;
	private String table;

	public CreateProjectProcess(JdbcTemplate outputJdbcTemplate, String table) {
		this.outputJdbcTemplate = outputJdbcTemplate;
		this.table = table;
	}

	@Override
	public boolean open() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run(Object data) {

		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) data;

		JdbcTemplateOutput output = new JdbcTemplateOutput(outputJdbcTemplate, table, SQL.INSERT);
		output.write(data);

		map.put("proj_id", output.getId());

		return map;
	}

	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		return true;
	}

}
