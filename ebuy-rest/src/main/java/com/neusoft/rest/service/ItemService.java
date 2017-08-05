package com.neusoft.rest.service;

import com.neusoft.common.pojo.TaotaoResult;

public interface ItemService {

	TaotaoResult getItemBaseInfo(long itemId);
	TaotaoResult getItemDesc(long itemId);
	TaotaoResult getItemParam(long itemId);
}
