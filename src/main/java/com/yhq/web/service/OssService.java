package com.yhq.web.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.ListBucketsRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.yhq.oss.configuration.OssClientConfiguration;

@Service("ossService")
public class OssService {

	@Autowired
	private OssClientConfiguration ossClientConfiguration;

	/**
	 * 上传图片
	 * 
	 * @param multiparRequest
	 * @param fileList
	 */
	public void UploadPicture(MultipartHttpServletRequest multiparRequest, List<MultipartFile> fileList) {
		final int size = fileList != null ? fileList.size() : 0;
		if (size > 0) {
			for (MultipartFile multipartFile : fileList) {
				String path = multiparRequest.getSession().getServletContext().getRealPath("upload");
				String fileName = multipartFile.getOriginalFilename();
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存
				try {
					multipartFile.transferTo(targetFile);
					ossClientConfiguration.getOssClient()
							.putObject(new PutObjectRequest("oss-mytest-s2", "testPic.jpg", targetFile));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 列出bucketName列表
	 */
	public void listBulkName() {
		OSSClient ossClient = ossClientConfiguration.getOssClient();
		String bucketName = ossClientConfiguration.getBucketName();

		/*
		 * Determine whether the bucket exists
		 */
		if (!ossClient.doesBucketExist(bucketName)) {
			/*
			 * Create a new OSS bucket
			 */
			System.out.println("Creating bucket " + bucketName + "\n");
			ossClient.createBucket(bucketName);
			CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
			createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
			ossClient.createBucket(createBucketRequest);
		}
		/*
		 * List the buckets in your account
		 */
		System.out.println("Listing buckets");

		ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
		listBucketsRequest.setMaxKeys(500);

		for (Bucket bucket : ossClient.listBuckets()) {
			System.out.println(" - " + bucket.getName());
		}
	}
}
