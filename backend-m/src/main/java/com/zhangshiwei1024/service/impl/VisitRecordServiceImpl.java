package com.zhangshiwei1024.service.impl;

import com.zhangshiwei1024.entity.VisitRecord;
import com.zhangshiwei1024.mapper.VisitRecordMapper;
import com.zhangshiwei1024.service.VisitRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 访问记录业务层实现
 * @Author: Naccl
 * @Date: 2021-02-23
 */
@Service
public class VisitRecordServiceImpl implements VisitRecordService {
	@Autowired
    VisitRecordMapper visitRecordMapper;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void saveVisitRecord(VisitRecord visitRecord) {
		visitRecordMapper.saveVisitRecord(visitRecord);
	}
}
