package com.zhangshiwei1024.service.impl;

import com.zhangshiwei1024.constant.RedisKeyConstants;
import com.zhangshiwei1024.entity.Visitor;
import com.zhangshiwei1024.mapper.VisitorMapper;
import com.zhangshiwei1024.model.dto.UserAgentDTO;
import com.zhangshiwei1024.model.dto.VisitLogUuidTime;
import com.zhangshiwei1024.service.RedisService;
import com.zhangshiwei1024.service.VisitorService;
import com.zhangshiwei1024.util.IpAddressUtils;
import com.zhangshiwei1024.util.UserAgentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zhangshiwei1024.exception.PersistenceException;

import java.util.List;

/**
 * @Description: 访客统计业务层实现
 * @Author: Naccl
 * @Date: 2021-01-31
 */
@Service
public class VisitorServiceImpl implements VisitorService {
	@Autowired
    VisitorMapper visitorMapper;
	@Autowired
    RedisService redisService;
	@Autowired
    UserAgentUtils userAgentUtils;

	@Override
	public List<Visitor> getVisitorListByDate(String startDate, String endDate) {
		return visitorMapper.getVisitorListByDate(startDate, endDate);
	}

	@Override
	public List<String> getNewVisitorIpSourceByYesterday() {
		return visitorMapper.getNewVisitorIpSourceByYesterday();
	}

	@Override
	public boolean hasUUID(String uuid) {
		return visitorMapper.hasUUID(uuid) != 0;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void saveVisitor(Visitor visitor) {
		String ipSource = IpAddressUtils.getCityInfo(visitor.getIp());
		UserAgentDTO userAgentDTO = userAgentUtils.parseOsAndBrowser(visitor.getUserAgent());
		visitor.setIpSource(ipSource);
		visitor.setOs(userAgentDTO.getOs());
		visitor.setBrowser(userAgentDTO.getBrowser());
		if (visitorMapper.saveVisitor(visitor) != 1) {
			throw new PersistenceException("访客添加失败");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updatePVAndLastTimeByUUID(VisitLogUuidTime dto) {
		visitorMapper.updatePVAndLastTimeByUUID(dto);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteVisitor(Long id, String uuid) {
		//删除Redis中该访客的uuid
		redisService.deleteValueBySet(RedisKeyConstants.IDENTIFICATION_SET, uuid);
		if (visitorMapper.deleteVisitorById(id) != 1) {
			throw new PersistenceException("删除访客失败");
		}
	}
}
