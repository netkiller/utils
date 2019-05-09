package cn.netkiller.ipo.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

@Service
public class AliyunOssService {

	@Value("${aliyun.oss.endpoint}")
	private String endpoint;
	@Value("${aliyun.oss.accessKeyId}")
	private String accessKeyId;
	@Value("${aliyun.oss.accessKeySecret}")
	private String accessKeySecret;
	@Value("${aliyun.oss.bucketName}")
	private String bucketName;

	private OSS ossClient;

	public AliyunOssService() {
	}

	public AliyunOssService(String bucketName) {
		this.bucketName = bucketName;

	}

	public void open() {
		// 创建OSSClient实例。
		ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
	}

	public void uploadFromUrl(String objectName, String url) {

		InputStream inputStream;
		try {
			inputStream = new URL(url).openStream();
			ossClient.putObject(this.bucketName, objectName, inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void close() {
		// 关闭OSSClient。
		ossClient.shutdown();
	}
}
