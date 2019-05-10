package cn.netkiller.ipo.process;

import cn.netkiller.ipo.service.AliyunOssService;

public class AliyunOssProcess implements ProcessInterface {

	private AliyunOssService aliyunOssService;

	public AliyunOssProcess(AliyunOssService aliyunOssService) {
		this.aliyunOssService = aliyunOssService;

	}

	@Override
	public Object run(Object data) {
		aliyunOssService.open();
		aliyunOssService.close();
		return data;
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
