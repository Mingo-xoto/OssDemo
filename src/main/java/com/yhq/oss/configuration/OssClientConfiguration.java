package com.yhq.oss.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.aliyun.oss.OSSClient;

@Configuration
public class OssClientConfiguration {

	private @Value("${oss.endpoint}") String endpoint;

	private @Value("${oss.accessKeyId}") String accessKeyID;

	private @Value("${oss.accessKeySecret}") String accessKeySecret;

	private @Value("${oss.bucketName}") String bucketName;

	public String getEndpoint() {
		return endpoint;
	}

	public String getAccessKeyID() {
		return accessKeyID;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public String getBucketName() {
		return bucketName;
	}

	public OSSClient getOssClient() {
		return new OSSClient(endpoint, accessKeyID, accessKeySecret);
	}

}
