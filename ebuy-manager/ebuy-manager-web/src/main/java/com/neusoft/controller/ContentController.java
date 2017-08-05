package com.neusoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neusoft.common.pojo.EUDataGridResult;
import com.neusoft.common.pojo.TaotaoResult;
import com.neusoft.pojo.TbContent;
import com.neusoft.service.ContentService;

/**
 * 内容管理Controller
 * <p>Title: ContentController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年9月8日上午11:13:52
 * @version 1.0
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent content) {
		TaotaoResult result = contentService.insertContent(content);
		return result;
	}
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGridResult queryContentList(@RequestParam(value="categoryId") Long categoryId, Integer page, Integer rows){
		EUDataGridResult result = contentService.getContentList(categoryId, page, rows);
		return result;
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public TaotaoResult editContent(TbContent content) {
		TaotaoResult result = contentService.editContent(content);
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteContent(@RequestParam List<Long> ids) {
		TaotaoResult result = contentService.deleteContent(ids);
		return result;
	}
}
