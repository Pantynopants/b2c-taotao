package com.neusoft.service;

import java.util.List;

import com.neusoft.common.pojo.EUTreeNode;
import com.neusoft.common.pojo.TaotaoResult;

public interface ContentCategoryService {

	List<EUTreeNode> getCategoryList(long parentId);
	TaotaoResult insertContentCategory(long parentId, String name);
	TaotaoResult updateContentCategory(long nodeId, String name);
	TaotaoResult deleteContentCategory(long id);
}
