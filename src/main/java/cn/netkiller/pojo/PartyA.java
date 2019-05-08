package cn.netkiller.pojo;

//{"linkPhone":"18310358098","districtId":440305,"linkPost":"扫地僧","companyTel":"53165186518561","projectAddr":["44","4403","440305"],"addrDetail":"南头街道马家龙工业区19栋(鼎元宏易大厦)4楼401-405","projectAddress":"广东省深圳市南山区南头街道马家龙工业区19栋(鼎元宏易大厦)4楼401-405","cityId":4403,"linkMan":"张三","provinceId":44}

// 甲方
//{"linkPhone":"18310358098","customerCompanyName":"深圳领筑互联","companyTel":"0755-6257785","bankBranchName":"规划鸡飞狗跳","bankName":"建设银行","cityId":1403,"linkMan":"曹总","provinceId":"14","districtId":140303,"linkPost":"董事长","accountNo":"5641561465","addrDetail":"南山区","addr":["14","1403","140303"],"taxNo":"535643563546354"}
public class PartyA {
	public String linkPhone;
	public String customerCompanyName;
	public String companyTel;
	public String bankBranchName;
	public String bankName;
	public int cityId;
	public String linkMan;
	public String provinceId;
	public String districtId;
	public String linkPost;
	public String accountNo;
	public String addrDetail;
	public String[] addr;
	public String taxNo;
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	public String getCustomerCompanyName() {
		return customerCompanyName;
	}
	public void setCustomerCompanyName(String customerCompanyName) {
		this.customerCompanyName = customerCompanyName;
	}
	public String getCompanyTel() {
		return companyTel;
	}
	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}
	public String getBankBranchName() {
		return bankBranchName;
	}
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getLinkPost() {
		return linkPost;
	}
	public void setLinkPost(String linkPost) {
		this.linkPost = linkPost;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAddrDetail() {
		return addrDetail;
	}
	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}
	public String[] getAddr() {
		return addr;
	}
	public void setAddr(String[] addr) {
		this.addr = addr;
	}
	public String getTaxNo() {
		return taxNo;
	}
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

}
