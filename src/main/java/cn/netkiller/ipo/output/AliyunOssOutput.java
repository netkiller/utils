package cn.netkiller.ipo.output;

import cn.netkiller.ipo.service.AliyunOssService;

public class AliyunOssOutput implements OutputInterface {

	private AliyunOssService aliyunOssService;

	public AliyunOssOutput(AliyunOssService aliyunOssService) {
		this.aliyunOssService = aliyunOssService;

	}

	@Override
	public boolean open() {
		aliyunOssService.open();
		return false;

	}

	@Override
	public boolean write(Object output) {
		return false;

	}

	@Override
	public boolean close() {
		aliyunOssService.close();
		return false;
	}

}
