package com.yh.system.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.system.domain.dto.sys.SysDictDTO;
import com.yh.system.domain.entity.sys.SysDict;

import java.util.List;

public interface SysDictService extends IService<SysDict> {

	List<SysDictDTO> listFieldByDictCode(String dictCode);
}
