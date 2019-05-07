package cn.netkiller.process;

import java.util.Map;

import cn.netkiller.ipo.process.ProcessMapInterface;

public class AddressProcess implements ProcessMapInterface {

	private Map<Integer, String> province;
	private Map<Integer, String> city;
	private Map<String, Integer> address;

	public AddressProcess(Map<Integer, String> province, Map<Integer, String> city, Map<String, Integer> address) {
		this.province = province;
		this.city = city;
		this.address = address;
	}

	@Override
	public Map<String, Object> run(Map<String, Object> row) {
		int province_id = (Integer) row.get("province_id");
		int city_id = (Integer) row.get("city_id");
		int district_id = (Integer) row.get("district_id");

		if (this.province.containsKey(province_id)) {
			String name = this.province.get(province_id);
			if (this.address.containsKey(name)) {
				row.put("province_id", this.address.get(name));

			}
		}

		if (this.city.containsKey(city_id)) {
			String name = this.city.get(city_id);
			if (this.address.containsKey(name)) {
				row.put("city_id", this.address.get(name));

			}
		}

		if (this.city.containsKey(district_id)) {
			String name = this.city.get(district_id);
			if (this.address.containsKey(name)) {
				row.put("district_id", this.address.get(name));

			}
		}

		return row;
	}

}
