package com.yhq.web.controller;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.yhq.log4j.constant.LoggerAliasConstant;
import com.yhq.oss.configuration.OssClientConfiguration;
import com.yhq.web.service.OssService;

@Controller
@RequestMapping(value = "/test/")
public class TestController {

	private Logger log = Logger.getLogger(TestController.class);

	private Logger trace = Logger.getLogger(LoggerAliasConstant.TRACE);
	private Logger success = Logger.getLogger(LoggerAliasConstant.SUCCESS);

	@Autowired
	private OssClientConfiguration ossClientConfiguration;

	@Autowired
	private OssService ossService;

	@ResponseBody
	@RequestMapping(value = "ossTest")
	public ModelMap toFirstJsp() {
		ModelMap map = new ModelMap();
		try {
			map.addAttribute("ossClient", ossClientConfiguration.getOssClient());
			log.info("-log记录info级别日志--");
			trace.info("-trace记录info级别日志--");
			trace.info("-trace记录info级别日志--");
			trace.info("-trace记录info级别日志--");
			trace.info("-trace记录info级别日志--");
			success.info("-success记录info级别日志--");
			log.fatal("fatal级别日志");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("---", e);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "uploadPicture", produces = { "application/json;charset=UTF-8" })
	public ModelMap uploadPicture(MultipartHttpServletRequest multiparRequest) {
		ModelMap modelMap = new ModelMap();
		try {
			List<MultipartFile> fileList = multiparRequest.getFiles("picture");
			ossService.listBulkName();
			ossService.UploadPicture(multiparRequest, fileList);
//			ossClientConfiguration.getOssClient().deleteBucket(ossClientConfiguration.getBucketName());
			ossClientConfiguration.getOssClient().deleteBucket("oss-mytest-s");
			ossService.listBulkName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelMap;
	}

}
