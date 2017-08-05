package com.neusoft.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.common.pojo.EUDataGridResult;
import com.neusoft.common.pojo.TaotaoResult;
import com.neusoft.common.utils.HttpClientUtil;
import com.neusoft.mapper.TbContentMapper;
import com.neusoft.pojo.TbContent;
import com.neusoft.pojo.TbContentExample;
import com.neusoft.pojo.TbItem;
import com.neusoft.pojo.TbContentExample.Criteria;
import com.neusoft.service.ContentService;

/**
 * 内容管理
 * <p>Title: ContentServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年9月8日上午11:09:53
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper  contentMapper;
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;
	
	
	@Override
	public TaotaoResult insertContent(TbContent content) {
		//补全pojo内容
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		
		//添加缓存同步逻辑
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return TaotaoResult.ok();
	}


	@Override
	public EUDataGridResult getContentList(long categoryId ,Integer page, Integer rows) {
		TbContentExample ex = new TbContentExample();
		Criteria criteria = ex.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		PageHelper.startPage(page, rows);
		List<TbContent> contentList = contentMapper.selectByExampleWithBLOBs(ex);
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(contentList);
		// 取记录总条数
		PageInfo<TbContent> pageInfo = new PageInfo<>(contentList);
		result.setTotal(pageInfo.getTotal());
		return result;
	}


	@Override
	public TaotaoResult editContent(TbContent content) {
		content.setUpdated(new Date());
		contentMapper.updateByPrimaryKeyWithBLOBs(content);
		
		//添加缓存同步逻辑
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return TaotaoResult.ok();
	}


	@Override
	public TaotaoResult deleteContent(List<Long> ids) {
		TbContent content = contentMapper.selectByPrimaryKey(ids.get(0));
		
		//添加缓存同步逻辑
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		for (Long id : ids) {
            contentMapper.deleteByPrimaryKey(id);
		}
		
		
		return TaotaoResult.ok();
	}

}
