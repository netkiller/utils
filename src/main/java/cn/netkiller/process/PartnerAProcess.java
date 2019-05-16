package cn.netkiller.process;

import java.util.Map;

import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.google.gson.Gson;

import cn.netkiller.component.DataInsert;
import cn.netkiller.ipo.process.ProcessInterface;
import cn.netkiller.pojo.PartyA;

public class PartnerAProcess implements ProcessInterface {
	private final static Logger logger = LoggerFactory.getLogger(PartnerAProcess.class);
	private JdbcTemplate inputJdbcTemplate;

	public PartnerAProcess(JdbcTemplate inputJdbcTemplate) {
		this.inputJdbcTemplate = inputJdbcTemplate;
	}

	@Override
	public Object run(Object data) {
		@SuppressWarnings("unchecked")
		Map<String, Object> row = (Map<String, Object>) data;
		String sql = "select * from import_crm where id = " + row.get("part_a_id") + " limit 1";
		// System.out.println(">>>>>> " + sql);
		try {
			Map<String, Object> map = this.inputJdbcTemplate.queryForMap(sql);
			if (map != null) {
				PartyA part = new PartyA();

				part.linkPhone = (String) map.get("link_phone");
				part.customerCompanyName = (String) map.get("customer_company_name");
				part.companyTel = (String) map.get("link_phone");
				part.bankBranchName = "";
				part.bankName = (String) map.get("bank_name");
				part.cityId = 0;
				part.linkMan = (String) map.get("link_man");
				part.provinceId = null;
				part.districtId = null;
				part.linkPost = null;
				part.accountNo = (String) map.get("account_no");
				part.addrDetail = null;
				part.addr = null;
				part.taxNo = (String) map.get("tax_no");

				// ObjectMapper mapper = new ObjectMapper();
				// String jsonData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(part);
				Gson gson = new Gson();

				// convert java object to JSON format, and returned as JSON formatted string
				String json = gson.toJson(part);

				// com.fasterxml.jackson.core.io.
				// json = JsonStringEncoder.getInstance().quoteAsString(json).toString();
				// json = StringEscapeUtils.escapeJava(json);
				row.put("part_a_json", json);
				// row.put("part_a_json", StringEscapeUtils.escapeJson(json));
			}
		} catch (EmptyResultDataAccessException e) {
			// e.printStackTrace();
			logger.warn(e.getMessage());
		}
		// System.out.println(map.toString());

		return row;
	}

	@Override
	public boolean open() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		return false;
	}

}
