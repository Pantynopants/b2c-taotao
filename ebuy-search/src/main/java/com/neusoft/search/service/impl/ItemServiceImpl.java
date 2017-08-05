package com.neusoft.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.common.pojo.TaotaoResult;
import com.neusoft.common.utils.ExceptionUtil;
import com.neusoft.search.mapper.ItemMapper;
import com.neusoft.search.pojo.Item;
import com.neusoft.search.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	
	//private SolrServer solrServer;//过时方法，废弃不用
	@Autowired
	private HttpSolrClient solrServer;//使用HttpSolrClient，实例化名称solrServer懒得改，注意
	
	@Override
	public TaotaoResult importAllItems() {
		try {
			
			//查询商品列表
			List<Item> list = itemMapper.getItemList();
			//把商品信息写入索引库
			for (Item item : list) {
				//创建一个SolrInputDocument对象
				SolrInputDocument document = new SolrInputDocument();
				document.setField("id", item.getId());
				document.setField("item_title", item.getTitle());
				document.setField("item_sell_point", item.getSell_point());
				document.setField("item_price", item.getPrice());
				document.setField("item_image", item.getImage());
				document.setField("item_category_name", item.getCategory_name());
				document.setField("item_desc", item.getItem_des());
				//写入索引库
				solrServer.add(document);
			}
			//提交修改
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();
	}

}
