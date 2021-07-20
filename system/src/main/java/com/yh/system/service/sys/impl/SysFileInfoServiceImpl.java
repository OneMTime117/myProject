package com.yh.system.service.sys.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.common.constant.FileExtConstant;
import com.yh.common.constant.FilePathConstant;
import com.yh.common.exception.BusinessException;
import com.yh.system.domain.entity.sys.SysFileInfo;
import com.yh.system.mapper.sys.SysFileInfoMapper;
import com.yh.system.service.sys.SysFileInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysFileInfoServiceImpl extends ServiceImpl<SysFileInfoMapper, SysFileInfo> implements SysFileInfoService {

	@Value("${fileUpload.domain}")
	private String domain;

	@Value("${fileUpload.dir}")
	private String dir;


	@Transactional
	@Override
	public List<String> upload(MultipartFile[] files, String model, String remark) throws IOException {
		if (files.length <= 0) {
			throw new BusinessException("至少上传一个文件");
		}

		//不同model，对应保存在不同目录下、支持不同后缀
		String path = "";
		String[] fileExt = {};
		switch (model) {
			case "0":
				path = FilePathConstant.commonFilePath;
				fileExt = FileExtConstant.IMAGE_TYPE;
				break;
			case "1":
				break;
			case "2":
				break;
			case "3":
				break;
			default:
				throw new BusinessException("model值错误");
		}

		ArrayList<SysFileInfo> sysFileInfos = new ArrayList<>();
		for (MultipartFile file : files) {
			//组装文件信息
			SysFileInfo sysFileInfo = new SysFileInfo();
			sysFileInfo.setModel(model);
			String originalFilename = file.getOriginalFilename();
			sysFileInfo.setFileSize(file.getSize());
			String suffix = "." + FileNameUtil.getSuffix(originalFilename);
			//添加年月日作为目录结构
			path += LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy/MM/dd/");
			String fileName = IdUtil.simpleUUID() + suffix;
			sysFileInfo.setExt(suffix);
			sysFileInfo.setPath(path);
			sysFileInfo.setName(fileName);
			if (StrUtil.isNotBlank(remark)) {
				originalFilename = remark + suffix;
			}
			sysFileInfo.setOriginName(originalFilename);
			sysFileInfo.setUrl(domain + path + fileName);
			//校验文件信息
			validateFile(sysFileInfo, fileExt);
			//存放文件
			File newFile = FileUtil.touch(dir + path + fileName);
			file.transferTo(newFile);
			sysFileInfos.add(sysFileInfo);
		}
		this.saveBatch(sysFileInfos);
		List<String> urls = sysFileInfos.stream().map(SysFileInfo::getUrl).collect(Collectors.toList());
		return urls;
	}

	private void validateFile(SysFileInfo sysFileInfo, String[] fileExt) {
		String originName = sysFileInfo.getOriginName();
		if (originName.length() > 50) {
			throw new BusinessException("文件名不能大于50个字符");
		}
		if (originName.contains(".\\") || originName.contains("..\\") || originName.contains("../") || originName.contains("./")) {
			throw new BusinessException("文件名不合法");
		}
		String ext = sysFileInfo.getExt();
		if (!StrUtil.containsAny(ext, fileExt)) {
			throw new BusinessException("文件后缀不满足model指定业务");
		}
		long size = sysFileInfo.getFileSize();
		if (size > 50 * 1024 * 1024) {
			throw new BusinessException("文件不能大于50M");
		}
		String url = sysFileInfo.getUrl();
		if (url.length() > 500) {
			throw new BusinessException("url过长");
		}
	}
}
