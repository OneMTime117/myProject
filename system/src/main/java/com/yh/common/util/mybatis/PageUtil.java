package com.yh.common.util.mybatis;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yh.common.domain.dto.BasePageDTO;

public class PageUtil {

	/**
	 * mybatisPlus分页参数对象创建
	 */
	public static Page<?> getPage(BasePageDTO dto) {
		Page<?> page = new Page<>();
		page.setCurrent(dto.getCurrent());
		page.setSize(dto.getSize());
		return page;
	}
}
