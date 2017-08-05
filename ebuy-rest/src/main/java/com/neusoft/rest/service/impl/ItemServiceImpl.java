package com.neusoft.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.common.pojo.TaotaoResult;
import com.neusoft.mapper.TbItemDescMapper;
import com.neusoft.mapper.TbItemMapper;
import com.neusoft.mapper.TbItemParamItemMapper;
import com.neusoft.pojo.TbItem;
import com.neusoft.pojo.TbItemDesc;
import com.neusoft.pojo.TbItemDescExample;
import com.neusoft.pojo.TbItemExample;
import com.neusoft.pojo.TbItemParamItem;
import com.neusoft.pojo.TbItemParamItemExample;
import com.neusoft.rest.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public TaotaoResult getItemBaseInfo(long itemId) {
		TaotaoResult tr = null;

		// 添加查询条件
		TbItemExample example = new TbItemExample();
		com.neusoft.pojo.TbItemExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem item = list.get(0);
			tr = new TaotaoResult(item);
			return tr;
		}
		return null;
	}

	@Override
	public TaotaoResult getItemDesc(long itemId) {
		TaotaoResult tr = null;
		// 添加查询条件
		TbItemDescExample example = new TbItemDescExample();
		com.neusoft.pojo.TbItemDescExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemDesc> list = itemDescMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			TbItemDesc itemDesc = list.get(0);
			tr = new TaotaoResult(itemDesc);
			return tr;
		}

		return null;
	}

	@Override
	public TaotaoResult getItemParam(long itemId) {
		TaotaoResult tr = null;

		// 添加查询条件
		TbItemParamItemExample example = new TbItemParamItemExample();
		com.neusoft.pojo.TbItemParamItemExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			TbItemParamItem itemParamItem = list.get(0);
			tr = new TaotaoResult(itemParamItem);
			return tr;
		}
		
		return null;
	}

}
