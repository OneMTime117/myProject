package com.yh.system.service.sys.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.system.domain.dto.sys.SysDictDTO;
import com.yh.system.domain.entity.sys.SysDict;
import com.yh.system.mapper.sys.SysDictMapper;
import com.yh.system.service.sys.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

	@Autowired
	private SysDictMapper sysDictMapper;

	/**
	 * 根据字典表编码,获取所有字段数据
	 */
	@Override
	public List<SysDictDTO> listFieldByDictCode(String dictCode) {
		List<SysDict> sysDicts = sysDictMapper.selectList(new LambdaQueryWrapper<SysDict>()
				.eq(SysDict::getDictCode, dictCode)
				.orderByAsc(SysDict::getId));
		ArrayList<SysDictDTO> sysDictDTOS = new ArrayList<>();
		for (SysDict sysDict : sysDicts) {
			SysDictDTO sysDictDTO = new SysDictDTO();
			BeanUtil.copyProperties(sysDict, sysDictDTO);
			sysDictDTOS.add(sysDictDTO);
		}
		return sysDictDTOS;
	}
}
