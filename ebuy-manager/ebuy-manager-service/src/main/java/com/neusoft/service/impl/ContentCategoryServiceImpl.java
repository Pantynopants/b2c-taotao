package com.neusoft.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.common.pojo.EUTreeNode;
import com.neusoft.common.pojo.TaotaoResult;
import com.neusoft.mapper.TbContentCategoryMapper;
import com.neusoft.pojo.TbContentCategory;
import com.neusoft.pojo.TbContentCategoryExample;
import com.neusoft.pojo.TbContentCategoryExample.Criteria;
import com.neusoft.service.ContentCategoryService;

/**
 * 内容分类管理
 * <p>Title: ContentCategoryServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	
 * @date	2015年9月8日上午9:17:41
 * @version 1.0
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	@Override
	public List<EUTreeNode> getCategoryList(long parentId) {
		//根据parentid查询节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			//创建一个节点
			EUTreeNode node = new EUTreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			
			resultList.add(node);
		}
		return resultList;
	}
	@Override
	public TaotaoResult insertContentCategory(long parentId, String name) {
		
		//创建一个pojo
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		//'状态。可选值:1(正常),2(删除)',
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		//添加记录
		contentCategoryMapper.insert(contentCategory);
		long id = contentCategoryMapper.getIdAfterInsert();
		contentCategory.setId(id);
		//查看父节点的isParent列是否为true，如果不是true改成true
		TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
		//判断是否为true
		if(!parentCat.getIsParent()) {
			parentCat.setIsParent(true);
			//更新父节点
			contentCategoryMapper.updateByPrimaryKey(parentCat);
		}
		//返回结果
		return TaotaoResult.ok(contentCategory);
	}
	
	@Override
	public TaotaoResult updateContentCategory(long nodeId, String name) {
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(nodeId);
		contentCategory.setName(name);
		contentCategory.setUpdated(new Date());
		contentCategoryMapper.updateByPrimaryKey(contentCategory);
		return TaotaoResult.ok(contentCategory);
	}
	
	public void deleteById(long id){
		contentCategoryMapper.deleteByPrimaryKey(id);
		//查找并删除所有子节点
		TbContentCategoryExample ex = new TbContentCategoryExample();
		Criteria criteria = ex.createCriteria();
		criteria.andParentIdEqualTo(id);
		List<TbContentCategory> leaves = contentCategoryMapper.selectByExample(ex);
		for (TbContentCategory leaf : leaves) {
			if(leaf.getIsParent()){
				//若子节点为父节点   迭代删除
				deleteById(leaf.getId());
			}else{
			    contentCategoryMapper.deleteByPrimaryKey(leaf.getId());	
			}
		}
	}
	
	@Override
	public TaotaoResult deleteContentCategory(long id) {
		TbContentCategory currentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		//如果是叶节点直接删除
		if(!currentCategory.getIsParent()){
			contentCategoryMapper.deleteByPrimaryKey(id);
			return TaotaoResult.ok();
		}
		//迭代删除
		deleteById(id);
		return TaotaoResult.ok();
	}

}
