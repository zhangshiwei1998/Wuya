package com.zhangshiwei1024.service;

import com.zhangshiwei1024.entity.User;

public interface UserService {
	User findUserByUsernameAndPassword(String username, String password);

	User findUserById(Long id);
}
