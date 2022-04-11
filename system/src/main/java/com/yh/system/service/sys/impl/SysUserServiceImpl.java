package com.yh.system.service.sys.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.common.constant.RedisKeyConstant;
import com.yh.common.exception.BusinessException;
import com.yh.common.util.mybatis.PageUtil;
import com.yh.common.util.redis.RedisUtil;
import com.yh.system.domain.dto.user.UserInfoDTO;
import com.yh.system.domain.dto.user.UserInfoQueryDTO;
import com.yh.system.domain.dto.user.UserLoginDTO;
import com.yh.system.domain.entity.sys.SysRole;
import com.yh.system.domain.entity.sys.SysUser;
import com.yh.system.domain.entity.sys.SysUserRole;
import com.yh.system.mapper.sys.SysRoleMapper;
import com.yh.system.mapper.sys.SysUserMapper;
import com.yh.system.mapper.sys.SysUserRoleMapper;
import com.yh.system.service.sys.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public UserInfoDTO login(UserLoginDTO dto) {
		SysUser sysUser = this.getOne(new LambdaQueryWrapper<SysUser>()
				.eq(SysUser::getUsername, dto.getUsername()));
		if (sysUser == null) {
			throw new BusinessException("用户名不存在");
		}

		String passwordEncode = SecureUtil.md5(dto.getPassword());
		if (!Objects.equals(sysUser.getPassword(), passwordEncode)) {
			throw new BusinessException("用户名或密码错误");
		}

		//返回用户、角色、权限信息
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		BeanUtils.copyProperties(sysUser, userInfoDTO);
		SysUserRole sysUserRole = sysUserRoleMapper.selectOne(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, sysUser.getId()));
		if (sysUserRole!=null){
			SysRole sysRole = sysRoleMapper.selectById(sysUserRole.getRoleId());
			userInfoDTO.setSysRole(sysRole);
		}

		//生成token,并将其保存到redis
		String token = IdUtil.simpleUUID();
		userInfoDTO.setToken(token);

		redisUtil.setObject(RedisKeyConstant.TOKEN_PREFIX + token, userInfoDTO, 3 * 60 * 60);
		return userInfoDTO;
	}

	@Override
	public IPage<UserInfoDTO> listUserInfo(UserInfoQueryDTO queryDTO) {
		Page<?> page = PageUtil.getPage(queryDTO);
		IPage<UserInfoDTO> userInfoDTOIPage = sysUserMapper.listPage(page, queryDTO);
		return userInfoDTOIPage;
	}

	@Transactional
	@Override
	public void addUserInfo(UserInfoDTO dto) {
		//校验用户数据
		//用户名,密码,ip,电话,真实姓名,角色id
		String username = dto.getUsername();
		String password = dto.getPassword();

		if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
			throw new BusinessException("用户名或密码不能为空");
		}

		int count = this.count(new LambdaQueryWrapper<SysUser>()
				.eq(SysUser::getUsername, username));
		if (count > 0) {
			throw new BusinessException("用户名已存在");
		}

		String roleId = dto.getRoleId();
		List<String> roleIdList = sysRoleMapper.selectList(null).stream().map(SysRole::getId).collect(Collectors.toList());
		if (!roleIdList.contains(roleId)) {
			throw new BusinessException("当前角色不存在");
		}

		String passwordEncode = SecureUtil.md5(dto.getPassword());
		//保存用户信息(密码进行MD5加密)
		SysUser sysUser = new SysUser();
		BeanUtil.copyProperties(dto, sysUser);
		sysUser.setPassword(passwordEncode);
		this.save(sysUser);
		dto.setId(sysUser.getId());
		//更新用户绑定的角色
		updateRole(dto);
	}

	@Transactional
	@Override
	public void deleteByUserId(String id) {
		this.removeById(id);
		sysUserRoleMapper.delete(new LambdaUpdateWrapper<SysUserRole>()
				.eq(SysUserRole::getUserId, id));
	}

	@Transactional
	@Override
	public void updatePassword(UserInfoDTO dto) {
		SysUser byId = this.getById(dto.getId());
		if (byId == null) {
			throw new BusinessException("当前用户不存在");
		}

		String passwordEncode = SecureUtil.md5(dto.getPassword());
		this.update(new LambdaUpdateWrapper<SysUser>()
				.set(SysUser::getPassword, passwordEncode)
				.eq(SysUser::getId, dto.getId()));
	}

	@Transactional
	@Override
	public void updateUserInfo(UserInfoDTO dto) {
		//校验用户数据
		//用户名,ip,电话,真实姓名,角色id
		String username = dto.getUsername();
		SysUser byId = this.getById(dto.getId());
		if (byId == null) {
			throw new BusinessException("当前用户不存在");
		}

		if (StrUtil.isBlank(username)) {
			throw new BusinessException("用户名不能为空");
		}

		int count = this.count(new LambdaQueryWrapper<SysUser>()
				.eq(SysUser::getUsername, username)
				.ne(SysUser::getId, dto.getId()));
		if (count > 0) {
			throw new BusinessException("用户名已存在");
		}

		String roleId = dto.getRoleId();
		List<String> roleIdList = sysRoleMapper.selectList(null).stream().map(SysRole::getId).collect(Collectors.toList());
		if (!roleIdList.contains(roleId)) {
			throw new BusinessException("当前角色不存在");
		}

		//更新用户信息(密码不能更新)
		SysUser sysUser = new SysUser();
		BeanUtil.copyProperties(dto, sysUser);
		sysUser.setPassword(null);
		this.updateById(sysUser);

		//更新用户绑定的角色
		updateRole(dto);
	}


	private void updateRole(UserInfoDTO dto) {
		//更新用户绑定的角色
		sysUserRoleMapper.delete(new LambdaUpdateWrapper<SysUserRole>()
				.eq(SysUserRole::getUserId, dto.getId()));
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setUserId(dto.getId());
		sysUserRole.setRoleId(dto.getRoleId());
		sysUserRoleMapper.insert(sysUserRole);
	}
}




