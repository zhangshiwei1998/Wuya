package com.zhangshiwei1024.service.impl;

import com.zhangshiwei1024.entity.ExceptionLog;
import com.zhangshiwei1024.mapper.ExceptionLogMapper;
import com.zhangshiwei1024.model.dto.UserAgentDTO;
import com.zhangshiwei1024.service.ExceptionLogService;
import com.zhangshiwei1024.util.IpAddressUtils;
import com.zhangshiwei1024.util.UserAgentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zhangshiwei1024.exception.PersistenceException;

import java.util.List;

/**
 * @Description: 异常日志业务层实现
 * @Author: Naccl
 * @Date: 2020-12-03
 */
@Service
public class ExceptionLogServiceImpl implements ExceptionLogService {
	@Autowired
    ExceptionLogMapper exceptionLogMapper;
	@Autowired
    UserAgentUtils userAgentUtils;

	@Override
	public List<ExceptionLog> getExceptionLogListByDate(String startDate, String endDate) {
		return exceptionLogMapper.getExceptionLogListByDate(startDate, endDate);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void saveExceptionLog(ExceptionLog log) {
		String ipSource = IpAddressUtils.getCityInfo(log.getIp());
		UserAgentDTO userAgentDTO = userAgentUtils.parseOsAndBrowser(log.getUserAgent());
		log.setIpSource(ipSource);
		log.setOs(userAgentDTO.getOs());
		log.setBrowser(userAgentDTO.getBrowser());
		if (exceptionLogMapper.saveExceptionLog(log) != 1) {
			throw new PersistenceException("日志添加失败");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteExceptionLogById(Long id) {
		if (exceptionLogMapper.deleteExceptionLogById(id) != 1) {
			throw new PersistenceException("删除日志失败");
		}
	}
}
