package com.neusoft.service;

import java.util.List;

import com.neusoft.common.pojo.EUDataGridResult;
import com.neusoft.common.pojo.TaotaoResult;
import com.neusoft.pojo.TbItemParam;

public interface ItemParamService {

	TaotaoResult getItemParamByCid(long cid);
	TaotaoResult insertItemParam(TbItemParam itemParam);
	EUDataGridResult getItemParamList(Integer page, Integer rows);
	TaotaoResult deleteItemParam(List<Long> ids);
}
