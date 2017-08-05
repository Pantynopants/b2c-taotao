package com.neusoft.service;

import java.util.List;

import com.neusoft.common.pojo.EUTreeNode;

public interface ItemCatService {

	List<EUTreeNode> getCatList(long parentId);
}
