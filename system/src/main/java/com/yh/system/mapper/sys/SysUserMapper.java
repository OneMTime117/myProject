package com.yh.system.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yh.system.domain.entity.sys.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends BaseMapper<SysUser> {
	List<SysUser> findAllByDeleted(@Param("deleted") Byte deleted);
}