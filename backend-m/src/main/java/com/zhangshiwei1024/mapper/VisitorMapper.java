package com.zhangshiwei1024.mapper;

import com.zhangshiwei1024.entity.Visitor;
import com.zhangshiwei1024.model.dto.VisitLogUuidTime;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 访客统计持久层接口
 * @Author: Naccl
 * @Date: 2021-01-31
 */
@Mapper
@Repository
public interface VisitorMapper {
	List<Visitor> getVisitorListByDate(String startDate, String endDate);

	List<String> getNewVisitorIpSourceByYesterday();

	int hasUUID(String uuid);

	int saveVisitor(Visitor visitor);

	int updatePVAndLastTimeByUUID(VisitLogUuidTime dto);

	int deleteVisitorById(Long id);
}
