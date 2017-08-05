package com.neusoft.service;

import java.util.List;

import com.neusoft.common.pojo.EUDataGridResult;
import com.neusoft.common.pojo.TaotaoResult;
import com.neusoft.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(long itemId);
	EUDataGridResult getItemList(int page, int rows);
	TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception;
	TaotaoResult getItemDesc(long itemId);
	TaotaoResult getItemParam(long itemId);
	TaotaoResult updateItem(TbItem item, String desc, String itemParams) throws Exception;
	TaotaoResult deleteItems(List<Long> ids);
	TaotaoResult instock(List<Long> ids);
	TaotaoResult reshelf(List<Long> ids);
}
