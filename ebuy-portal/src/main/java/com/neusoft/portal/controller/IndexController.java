package com.neusoft.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neusoft.common.pojo.TaotaoResult;
import com.neusoft.portal.service.ContentService;

@Controller
public class IndexController {
	
	@Autowired
	private ContentService contentService;

	@RequestMapping("/index")
	public String showIndex(Model model) {
		String adJson = contentService.getContentList();
		model.addAttribute("ad1", adJson);
		return "index";
	}
	
	//TEXT_PLAIN_VALUE
	/*@RequestMapping(value="/httpclient/post", method=RequestMethod.POST, 
			produces= "text/html;charset=utf-8")
	//@ResponseBody
	public String testPost(String username, String password) {
		String result = "username:" + username + "\tpassword:" + password;
		System.out.println(result);
		return "username:" + username + ",password:" + password;
		
	}*/
	
	@RequestMapping(value="/httpclient/post", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult testPost(String username, String password) {
		String result = "username:" + username + "\tpassword:" + password;
		System.out.println(result);
		//return "username:" + username + ",password:" + password;
		
		return TaotaoResult.ok();
		
	}
	
}
