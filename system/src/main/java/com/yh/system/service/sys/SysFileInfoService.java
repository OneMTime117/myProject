package com.yh.system.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.system.domain.entity.sys.SysFileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SysFileInfoService extends IService<SysFileInfo> {

	List<String> upload(MultipartFile[] files, String model, String remark) throws IOException;
}
