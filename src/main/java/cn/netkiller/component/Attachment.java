package cn.netkiller.component;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cn.netkiller.ipo.Input;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.Output;
import cn.netkiller.ipo.Position;
import cn.netkiller.ipo.Process;
import cn.netkiller.ipo.input.JdbcTemplateInput;
import cn.netkiller.ipo.output.AliyunOssOutput;
import cn.netkiller.ipo.output.JdbcTemplateUpdateOutput;
import cn.netkiller.ipo.position.RedisPosition;
import cn.netkiller.ipo.process.map.MapPut;
import cn.netkiller.ipo.process.map.MapReplace;
import cn.netkiller.ipo.service.AliyunOssService;
import cn.netkiller.ipo.util.SqlUtil.SQL;
import cn.netkiller.process.AttachmentProcess;

@Component
public class Attachment {

	@Qualifier("inputJdbcTemplate")
	@Autowired
	private JdbcTemplate inputJdbcTemplate;

	@Qualifier("outputJdbcTemplate")
	@Autowired
	private JdbcTemplate outputJdbcTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private AliyunOssService aliyunOssService;

	public Attachment() {
	}

	private void project() {

		//

		// outputJdbcTemplate.execute("delete from lz_auth where created_by = 'import'");

		Input input = new Input(new LinkedHashMap<Object, Object>());
		Process process = new Process();
		Output output = new Output();
		Position position = new Position(new RedisPosition(stringRedisTemplate, "Attachment"), "id");

		input.add(new JdbcTemplateInput(inputJdbcTemplate, "select id, contract_img_url from lz_cloud_om_dev.om_project_contract where ipo='import'"));

		output.add(new JdbcTemplateUpdateOutput(outputJdbcTemplate, "lz_cloud_om_dev.om_project_contract", "id"));

		process.add(new MapReplace("created_time", null, "now()"));
		process.add(new MapPut("created_by", "import"));
		process.add(new MapPut("company_id", "1"));
		process.add(new AttachmentProcess(aliyunOssService, inputJdbcTemplate));

		InputProcessOutput ipo = new InputProcessOutput(this.getClass().getName());

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setPosition(position);
		ipo.launch();
	}

}
