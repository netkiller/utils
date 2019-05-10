package cn.netkiller.process;

import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.netkiller.ipo.input.JdbcTemplateInput;
import cn.netkiller.ipo.process.ProcessInterface;
import cn.netkiller.ipo.service.AliyunOssService;

public class AttachmentProcess implements ProcessInterface {
	// private final static Logger logger = LoggerFactory.getLogger(AttachmentProcess.class);
	private AliyunOssService aliyunOssService;
	private JdbcTemplate inputJdbcTemplate;

	public AttachmentProcess(AliyunOssService aliyunOssService, JdbcTemplate inputJdbcTemplate) {
		this.aliyunOssService = aliyunOssService;
		this.inputJdbcTemplate = inputJdbcTemplate;
	}

	@Override
	public Object run(Object data) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) data;
		String sql = "select * from import__contract_attachment where contract_id =" + map.get("id");
		Iterator<Map<String, Object>> iterator = inputJdbcTemplate.queryForList(sql).iterator();
		if (iterator.hasNext()) {
			Map<String, Object> row = iterator.next();
			// logger.debug(line.toString());
			String url = String.format("http://120.76.214.182:8086/web/binary/saveas?model=ir.attachment&field=datas&filename_field=datas_fname&id=%s", row.get("id"));
			this.aliyunOssService.uploadFromUrl((String) row.get("name"), url);
		}

		return map;
	}

	@Override
	public boolean open() {
		aliyunOssService.open();
		return false;
	}

	@Override
	public boolean close() {
		aliyunOssService.close();
		return false;
	}

}
