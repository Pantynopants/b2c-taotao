package com.neusoft.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.neusoft.common.utils.JsonUtils;
import com.neusoft.mapper.TbItemCatMapper;
import com.neusoft.pojo.TbContent;
import com.neusoft.pojo.TbItemCat;
import com.neusoft.pojo.TbItemCatExample;
import com.neusoft.pojo.TbItemCatExample.Criteria;
import com.neusoft.rest.dao.JedisClient;
import com.neusoft.rest.pojo.CatNode;
import com.neusoft.rest.pojo.CatResult;
import com.neusoft.rest.service.ItemCatService;

/**
 * 商品分类服务
 * <p>Title: ItemCatServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年9月7日下午2:44:41
 * @version 1.0
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_ITEM_CAT}")
	private String REDIS_ITEM_CAT;
	
	@Override
	public CatResult getItemCatList() {
		
		CatResult catResult = new CatResult();
		//查询分类列表
		catResult.setData(getCatList(0));
		return catResult;
	}
	
	/**
	 * 查询分类列表
	 * <p>Title: getCatList</p>
	 * <p>Description: </p>
	 * @param parentId
	 * @return
	 */
	private List<?> getCatList(long parentId) {
		//从缓存中取内容
		try {
			String result = jedisClient.hget(REDIS_ITEM_CAT, parentId + "");
			if (!StringUtils.isBlank(result)&&result!=null) {
				//把字符串转换成list
				List<CatNode> resultList = JsonUtils.jsonToList(result, CatNode.class);
				return resultList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		//创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//返回值list
		List resultList = new ArrayList<>();
		//向list中添加节点
		int count = 0;
		for (TbItemCat tbItemCat : list) {
			//判断是否为父节点
			if (tbItemCat.getIsParent()) {
				CatNode catNode = new CatNode();
				if (parentId == 0) {
					catNode.setName("<a href='/search.html?q="+tbItemCat.getName()+"'>"+tbItemCat.getName()+"</a>");
				} else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/search.html?q="+tbItemCat.getName()+"");
				catNode.setItem(getCatList(tbItemCat.getId()));
				
				resultList.add(catNode);
				count ++;
				//第一层只取14条记录
				if (parentId == 0 && count >=14) {
					break;
				}
			//如果是叶子节点
			} else {
				resultList.add("/search.html?q="+tbItemCat.getName()+"|" + tbItemCat.getName());
			}
		}
		
		//向缓存中添加内容
		try {
			//把list转换成字符串
		    String cacheString = JsonUtils.objectToJson(resultList);
			jedisClient.hset(REDIS_ITEM_CAT, parentId + "", cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return resultList;
	}

}
