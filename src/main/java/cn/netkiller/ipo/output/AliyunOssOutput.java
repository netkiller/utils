package cn.netkiller.ipo.output;

import cn.netkiller.ipo.service.AliyunOssService;

public class AliyunOssOutput implements OutputInterface {

	private AliyunOssService aliyunOssService;

	public AliyunOssOutput(AliyunOssService aliyunOssService) {
		this.aliyunOssService = aliyunOssService;

	}

	@Override
	public void open() {
		aliyunOssService.open();

	}

	@Override
	public void write(Object output) {

	}

	@Override
	public void close() {
		aliyunOssService.close();
	}

}
