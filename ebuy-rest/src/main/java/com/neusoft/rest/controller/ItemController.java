package com.neusoft.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neusoft.common.pojo.TaotaoResult;
import com.neusoft.rest.service.ItemService;
@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public Object getItemInfo(@PathVariable Long itemId){
		TaotaoResult itemBaseInfo = itemService.getItemBaseInfo(itemId);
		return itemBaseInfo;
	}
	
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public Object getItemDesc(@PathVariable Long itemId){
		TaotaoResult itemBaseInfo = itemService.getItemDesc(itemId);
		return itemBaseInfo;
	}
	
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public Object getItemParam(@PathVariable Long itemId){
		TaotaoResult itemBaseInfo = itemService.getItemParam(itemId);
		return itemBaseInfo;
	}
}
