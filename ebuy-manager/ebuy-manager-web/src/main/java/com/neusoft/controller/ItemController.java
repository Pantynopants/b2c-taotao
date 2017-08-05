package com.neusoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neusoft.common.pojo.EUDataGridResult;
import com.neusoft.common.pojo.TaotaoResult;
import com.neusoft.pojo.TbItem;
import com.neusoft.service.ItemService;


/**
 * 商品管理Controller
 * <p>Title: ItemController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年9月2日上午10:52:46
 * @version 1.0
 */

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows) {
		EUDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	
	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	private TaotaoResult createItem(TbItem item, String desc, String itemParams) throws Exception {
		TaotaoResult result = itemService.createItem(item, desc, itemParams);
		return result;
	}
	
	@RequestMapping(value="/item/update", method=RequestMethod.POST)
	@ResponseBody
	private TaotaoResult updateItem(TbItem item, String desc, String itemParams) throws Exception {
		TaotaoResult result = itemService.updateItem(item, desc, itemParams);
		return result;
	}
	
	@RequestMapping("item/delete")
	@ResponseBody
	public Object deleteItems(@RequestParam List<Long> ids){
		TaotaoResult itemsDeleteInfo = itemService.deleteItems(ids);
		return itemsDeleteInfo;
	}
	
	@RequestMapping("item/instock")
	@ResponseBody
	public Object instock(@RequestParam List<Long> ids){
		TaotaoResult itemsInstockInfo = itemService.instock(ids);
		return itemsInstockInfo;
	}
	
	@RequestMapping("item/reshelf")
	@ResponseBody
	public Object reshelf(@RequestParam List<Long> ids){
		TaotaoResult itemsReshelfInfo = itemService.reshelf(ids);
		return itemsReshelfInfo;
	}
	
	@RequestMapping("item/desc/{itemId}")
	@ResponseBody
	public Object getItemDesc(@PathVariable Long itemId){
		TaotaoResult itemDescInfo = itemService.getItemDesc(itemId);
		return itemDescInfo;
	}
	
	@RequestMapping("item/param/{itemId}")
	@ResponseBody
	public Object getItemParam(@PathVariable Long itemId){
		TaotaoResult itemParamInfo = itemService.getItemParam(itemId);
		return itemParamInfo;
	}
}
