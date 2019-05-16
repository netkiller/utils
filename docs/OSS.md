# OSS Upload file

```
	@Autowired
	private AliyunOssService aliyunOssService;
	
	public void test(){
		String objectName = "test/aabbcc.png";
		this.aliyunOssService.open();
		this.aliyunOssService.uploadFromUrl(objectName, "https://www.baidu.com/img/bd_logo1.png");
		this.aliyunOssService.close();
	}
```