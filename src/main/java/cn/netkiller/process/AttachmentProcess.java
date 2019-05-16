package cn.netkiller.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.gson.Gson;

import cn.netkiller.ipo.process.ProcessInterface;
import cn.netkiller.ipo.service.AliyunOssService;

public class AttachmentProcess implements ProcessInterface {
	private final static Logger logger = LoggerFactory.getLogger(AttachmentProcess.class);
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

		List<Map<String, String>> images = new ArrayList<Map<String, String>>();
		String sql = "select * from import_contract_attachment where contract_id=" + map.get("id");
		Iterator<Map<String, Object>> iterator = inputJdbcTemplate.queryForList(sql).iterator();
		while (iterator.hasNext()) {
			Map<String, Object> row = iterator.next();
			// logger.debug(line.toString());
			String name = String.format("contract/%s/%s", row.get("id"), (String) row.get("name"));

			// String url = String.format("http://120.76.214.182:8086/web/binary/saveas?model=ir.attachment&field=datas&filename_field=datas_fname&id=%s", row.get("id"));
			String url = String.format("http://120.76.214.182:8086/images/%s", row.get("filename"));

			boolean status = this.aliyunOssService.uploadFromUrl(name, url);
			if (status) {
				Map<String, String> image = new HashMap<String, String>();
				image.put("name", name);
				image.put("url", "http://lz-omcloud-test.oss-cn-shenzhen.aliyuncs.com/" + name);

				images.add(image);

				Gson gson = new Gson();

				// convert java object to JSON format, and returned as JSON formatted string
				String json = gson.toJson(images);
				map.put("contract_img_url", json);

				logger.debug(json);
			}
		}

		// [{"name":"vcbgcb.jpg","url":"http://lz-omcloud-test.oss-cn-shenzhen.aliyuncs.com/file/l/file-870e109d5b3c40b2b922d5de42139f49.jpg"}

		return map;
	}

	@Override
	public boolean open() {
		aliyunOssService.open();
		return true;
	}

	@Override
	public boolean close() {
		aliyunOssService.close();
		return true;
	}

}
