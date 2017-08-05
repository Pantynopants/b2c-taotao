package com.neusoft.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.common.pojo.TaotaoResult;
import com.neusoft.pojo.TbUser;

public interface UserService {

	TaotaoResult checkData(String content, Integer type);
	TaotaoResult createUser(TbUser user);
	TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
	TaotaoResult getUserByToken(String token);
}
