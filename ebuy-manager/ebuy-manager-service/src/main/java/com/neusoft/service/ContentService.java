package com.neusoft.service;

import java.util.List;

import com.neusoft.common.pojo.EUDataGridResult;
import com.neusoft.common.pojo.TaotaoResult;
import com.neusoft.pojo.TbContent;

public interface ContentService {

	TaotaoResult insertContent(TbContent content);

	EUDataGridResult getContentList(long categoryId, Integer page, Integer rows);

	TaotaoResult editContent(TbContent content);

	TaotaoResult deleteContent(List<Long> ids);
}
