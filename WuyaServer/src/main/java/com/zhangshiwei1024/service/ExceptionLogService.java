package com.zhangshiwei1024.service;

import com.zhangshiwei1024.entity.ExceptionLog;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface ExceptionLogService {
	List<ExceptionLog> getExceptionLogListByDate(String startDate, String endDate);

	@Async
	void saveExceptionLog(ExceptionLog log);

	void deleteExceptionLogById(Long id);
}
