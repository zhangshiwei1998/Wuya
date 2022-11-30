package com.zhangshiwei1024.mapper;

import com.zhangshiwei1024.entity.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 登录日志持久层接口
 * @Author: Naccl
 * @Date: 2020-12-03
 */
@Mapper
@Repository
public interface LoginLogMapper {
	List<LoginLog> getLoginLogListByDate(String startDate, String endDate);

	int saveLoginLog(LoginLog log);

	int deleteLoginLogById(Long id);
}
