package com.yh.system.service.sys.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.system.mapper.sys.SysApiLogMapper;
import com.yh.system.domain.entity.sys.SysApiLog;
import com.yh.system.service.sys.SysApiLogService;
@Service
public class SysApiLogServiceImpl extends ServiceImpl<SysApiLogMapper, SysApiLog> implements SysApiLogService{

}
