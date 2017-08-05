package com.neusoft.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.common.pojo.EUDataGridResult;
import com.neusoft.common.pojo.TaotaoResult;
import com.neusoft.common.utils.HttpClientUtil;
import com.neusoft.common.utils.IDUtils;
import com.neusoft.mapper.TbItemDescMapper;
import com.neusoft.mapper.TbItemMapper;
import com.neusoft.mapper.TbItemParamItemMapper;
import com.neusoft.pojo.TbItem;
import com.neusoft.pojo.TbItemDesc;
import com.neusoft.pojo.TbItemDescExample;
import com.neusoft.pojo.TbItemExample;
import com.neusoft.pojo.TbItemParamItem;
import com.neusoft.pojo.TbItemParamItemExample;
import com.neusoft.pojo.TbItemExample.Criteria;
import com.neusoft.service.ItemService;

/**
 * 商品管理Service
 * <p>
 * Title: ItemServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.itcast.com
 * </p>
 * 
 * @author 入云龙
 * @date 2015年9月2日上午10:47:14
 * @version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	private String solrImportAllUrl = "http://localhost:8083/search/manager/importall";
	
	@Override
	public TbItem getItemById(long itemId) {

		// TbItem item = itemMapper.selectByPrimaryKey(itemId);
		// 添加查询条件
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}

	/**
	 * 商品列表查询
	 * <p>
	 * Title: getItemList
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param page
	 * @param rows
	 * @return
	 * @see com.neusoft.service.ItemService#getItemList(long, long)
	 */
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		// 查询商品列表
		TbItemExample example = new TbItemExample();
		// 分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception {
		// item补全
		// 生成商品ID
		Long itemId = IDUtils.genItemId();
		item.setId(itemId);
		// '商品状态，1-正常，2-下架，3-删除',
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		// 插入到数据库
		itemMapper.insert(item);
		// 添加商品描述信息
		TaotaoResult result = insertItemDesc(itemId, desc);
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		// 添加规格参数
		result = insertItemParamItem(itemId, itemParam);
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updateItem(TbItem item, String desc, String itemParam) throws Exception {
		Long itemId = item.getId();
		TbItem oldItem = getItemById(itemId);
		// '商品状态，1-正常，2-下架，3-删除',
		item.setStatus((byte) 1);
		Date created = oldItem.getCreated();
		item.setCreated(created);
		item.setUpdated(new Date());
		// 插入到数据库
		itemMapper.updateByPrimaryKey(item);
		// 添加商品描述信息
		TaotaoResult result = updateItemDesc(itemId, desc);
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		// 添加规格参数
		result = updateItemParamItem(itemId, itemParam);
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		return TaotaoResult.ok();
	}
	

	@Override
	public TaotaoResult deleteItems(List<Long> ids) {
		for (Long id : ids) {
			itemMapper.deleteByPrimaryKey(id);
		}
		return TaotaoResult.ok();
	}
	
	@Override
	public TaotaoResult instock(List<Long> ids) {
		for (Long id : ids) {
			TbItem item = getItemById(id);
			item.setStatus((byte) 2);
			itemMapper.updateByPrimaryKey(item);
		}
		return TaotaoResult.ok();
	}
	
	@Override
	public TaotaoResult reshelf(List<Long> ids) {
		for (Long id : ids) {
			TbItem item = getItemById(id);
			item.setStatus((byte) 1);
			itemMapper.updateByPrimaryKey(item);
		}
		return TaotaoResult.ok();
	}

	/**
	 * 添加商品描述
	 * <p>
	 * Title: insertItemDesc
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param desc
	 */
	private TaotaoResult insertItemDesc(Long itemId, String desc) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}

	private TaotaoResult updateItemDesc(Long itemId, String desc) {
		TbItemDesc oldItemDescById = getItemDescById(itemId);
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(oldItemDescById.getCreated());
		itemDesc.setUpdated(new Date());
		itemDescMapper.updateByPrimaryKeyWithBLOBs(itemDesc);
		return TaotaoResult.ok();
	}

	/**
	 * 添加规格参数
	 * <p>
	 * Title: insertItemParamItem
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param itemId
	 * @param itemParam
	 * @return
	 */
	private TaotaoResult insertItemParamItem(Long itemId, String itemParam) {
		// 创建一个pojo
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		// 向表中插入数据
		itemParamItemMapper.insert(itemParamItem);

		return TaotaoResult.ok();
	}

	private TaotaoResult updateItemParamItem(Long itemId, String itemParam) {
		TbItemParamItem oldItemParamItemByItemId = getItemParamItemByItemId(itemId);
		// 创建一个pojo
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setId(oldItemParamItemByItemId.getId());
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(oldItemParamItemByItemId.getCreated());
		itemParamItem.setUpdated(new Date());
		// 向表中插入数据
		itemParamItemMapper.updateByPrimaryKeyWithBLOBs(itemParamItem);

		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult getItemDesc(long itemId) {
		TaotaoResult tr = new TaotaoResult(getItemDescById(itemId));
		return tr;
	}

	@Override
	public TaotaoResult getItemParam(long itemId) {
		TaotaoResult tr = new TaotaoResult(getItemParamItemByItemId(itemId));
		return tr;
	}

	public TbItemDesc getItemDescById(long itemId) {
		TbItemDescExample example = new TbItemDescExample();
		com.neusoft.pojo.TbItemDescExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemDesc> list = itemDescMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			TbItemDesc itemDesc = list.get(0);
			return itemDesc;
		}
		return null;
	}

	public TbItemParamItem getItemParamItemByItemId(long itemId) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		com.neusoft.pojo.TbItemParamItemExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			TbItemParamItem itemParamItem = list.get(0);
			return itemParamItem;
		}
		return null;
	}

}
