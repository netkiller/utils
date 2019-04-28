package cn.netkiller.ipo.input;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

//import org.springframework.stereotype.Component;
//
//
//@Component
public class JdbcTemplateInput implements InputInterface {
	private final static Logger logger = LoggerFactory.getLogger(JdbcTemplateInput.class);

	// @Qualifier("inputJdbcTemplate")
	// @Autowired
	private JdbcTemplate inputJdbcTemplate;

	private String sql = null;
	private Iterator<Map<String, Object>> iterator = null;

	public JdbcTemplateInput(JdbcTemplate inputJdbcTemplate, String sql) {
		this.inputJdbcTemplate = inputJdbcTemplate;
		this.sql = sql;
	}

	@Override
	public boolean open() {
		this.iterator = inputJdbcTemplate.queryForList(this.sql).iterator();
		if (this.iterator != null) {
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> readLine() {

		if (this.iterator.hasNext()) {
			Map<String, Object> line = this.iterator.next();
			logger.warn(line.toString());
			return line;
		}
		return null;
	}

	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getDataType() {
		return new LinkedHashMap<String, Object>();
	}

	public boolean hasNext() {
		return this.iterator.hasNext();
	}
}
