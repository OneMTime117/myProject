package com.yh.system.controller.sys;

import cn.hutool.core.io.IoUtil;
import com.yh.system.config.result.Result;
import com.yh.system.service.sys.SysFileInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;

/**
 * @author yuanhuan
 * @description 文件通用接口
 * @date 2021/7/9 16:24
 */


@Api(value = "/file", tags = {"文件通用接口"})
@RequestMapping("/file")
@RestController
public class SysFileController {


	@Autowired
	private SysFileInfoService sysFileInfoService;

	/**
	 * 文件上传
	 */
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "formData", dataType = "MultipartFile[]", name = "files", value = ""),
			@ApiImplicitParam(paramType = "formData", dataType = "string", name = "model", value = ""),
			@ApiImplicitParam(paramType = "formData", dataType = "string", name = "remark", value = "")
	})
	@ApiOperation(value = "文件上传", notes = "文件上传", httpMethod = "POST")
	@PostMapping("upload")
	public ResponseEntity<List<String>> upload(@RequestParam MultipartFile[] files, @RequestParam String model, @RequestParam(required = false, defaultValue = "") String remark) throws IOException {
		// 表单传参时，对于空字符串字段,会转化为 "null"
		if (Objects.equals(remark, "null")) {
			remark = null;
		}
		List<String> upload = sysFileInfoService.upload(files, model, remark);
		return Result.ok(upload);
	}


	/**
	 * 文件下载
	 */
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", dataType = "string", name = "model", value = "业务类型")
	})
	@ApiOperation(value = "文件下载", notes = "文件下载", httpMethod = "GET")
	@GetMapping
	public ResponseEntity<byte[]> download(@RequestParam String model) throws UnsupportedEncodingException {
		String downloadFileName = "";
		byte[] bytes = null;
		String filePath = "";
		switch (model) {
			case "0":
				downloadFileName = "excel模板-test.xlsx";
				filePath = "template/excel/excel模板.xlsx";
		}
		//文件二进制数据
		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		IoUtil.copy(resourceAsStream, byteArrayOutputStream);
		bytes = byteArrayOutputStream.toByteArray();
		IoUtil.close(resourceAsStream);
		IoUtil.close(byteArrayOutputStream);

		//文件名额外处理
		downloadFileName = new String(downloadFileName.getBytes("UTF-8"), "iso-8859-1");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentDispositionFormData("attachment", downloadFileName);
		httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.OK);
	}
}
