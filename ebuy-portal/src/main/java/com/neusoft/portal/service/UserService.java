package com.neusoft.portal.service;

import com.neusoft.pojo.TbUser;

public interface UserService {

	TbUser getUserByToken(String token);
}
