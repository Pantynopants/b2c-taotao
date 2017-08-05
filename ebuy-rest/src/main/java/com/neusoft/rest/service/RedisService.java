package com.neusoft.rest.service;

import com.neusoft.common.pojo.TaotaoResult;

public interface RedisService {

	TaotaoResult syncContent(long contentCid);
}
