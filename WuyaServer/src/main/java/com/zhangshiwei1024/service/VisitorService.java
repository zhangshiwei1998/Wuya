package com.zhangshiwei1024.service;

import com.zhangshiwei1024.entity.Visitor;
import com.zhangshiwei1024.model.dto.VisitLogUuidTime;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface VisitorService {
	List<Visitor> getVisitorListByDate(String startDate, String endDate);

	List<String> getNewVisitorIpSourceByYesterday();

	boolean hasUUID(String uuid);

	@Async
	void saveVisitor(Visitor visitor);

	void updatePVAndLastTimeByUUID(VisitLogUuidTime dto);

	void deleteVisitor(Long id, String uuid);
}
