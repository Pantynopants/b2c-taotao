package com.neusoft.rest.service;

import java.util.List;

import com.neusoft.pojo.TbContent;

public interface ContentService {

	List<TbContent> getContentList(long contentCid);
}
