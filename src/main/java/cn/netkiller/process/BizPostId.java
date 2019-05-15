package cn.netkiller.process;

import java.util.HashMap;
import java.util.Map;

import cn.netkiller.ipo.process.ProcessInterface;

public class BizPostId implements ProcessInterface {

	private static final Map<String, String> title = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
			put("财务总监", "1");
			put("查询项目合同", "2");
			put("成本合约部经理", "3");
			put("出纳", "4");
			put("法务", "5");
			put("工程事业部总经理", "6");
			put("工程主管", "7");
			put("行政专员", "8");
			put("金融事业部风控经理", "9");
			put("金融事业部总经理", "10");
			put("开票专员", "11");
			put("劳务公司", "12");
			put("劳务运营专员", "13");
			put("领筑电商用户", "14");
			put("平台事业部总经理", "15");
			put("全盘会计", "16");
			put("商务经理", "17");
			put("税务会计", "18");
			put("税务专员", "19");
			put("维保检测事业部总经理", "20");
			put("项目财务经理", "21");
			put("项目会计", "22");
			put("销售运营专员", "23");
			put("销售总监", "24");
			put("业务管理员", "25");
			put("运营专员", "26");
			put("装备事业部总经理", "27");
			put("总裁", "28");
		}
	};

	public BizPostId() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean open() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object run(Object data) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) data;
		String value = (String) map.get("biz_post_id");
		if (value.isEmpty()) {
			map.put("biz_post_id", "30");
		} else {
			// System.out.println(">>>>>>>> " + value.indexOf("，"));
			if (value.indexOf('，') >= 1) {
				String[] tmp = value.split("，");
				value = tmp[0].strip();
			}
			String id = title.get(value);
			map.put("biz_post_id", id);
		}
		return map;
	}

	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		return false;
	}

}
