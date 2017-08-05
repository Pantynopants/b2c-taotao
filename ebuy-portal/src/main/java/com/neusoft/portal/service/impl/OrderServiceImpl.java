package com.neusoft.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.neusoft.common.pojo.TaotaoResult;
import com.neusoft.common.utils.CookieUtils;
import com.neusoft.common.utils.HttpClientUtil;
import com.neusoft.common.utils.JsonUtils;
import com.neusoft.pojo.TbUser;
import com.neusoft.portal.pojo.Order;
import com.neusoft.portal.service.OrderService;

/**
 * 订单处理Service
 * <p>Title: OrderServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年9月16日下午2:45:08
 * @version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;
	@Value("${SSO_BASE_URL}")
	private String SSO_BASE_URL;
	@Value("${SSO_USER_TOKEN}")
	private String SSO_USER_TOKEN;
	

	@Override
	public String createOrder(Order order,String token) {
		//调用创建订单服务之前补全用户信息。
		//从cookie中后取TT_TOKEN的内容，根据token调用sso系统的服务根据token换取用户信息。
		String json = HttpClientUtil.doPost(SSO_BASE_URL + SSO_USER_TOKEN + token);
		//把json转换成taotaoResult
		TaotaoResult taotaoResult = TaotaoResult.format(json);
		if(taotaoResult.getStatus() == 200){
			TbUser user = JsonUtils.jsonToPojo(JsonUtils.objectToJson(taotaoResult.getData()), TbUser.class);
			System.out.println(JsonUtils.objectToJson(taotaoResult.getData()));
			order.setUserId(user.getId());
			order.setBuyerNick(user.getUsername());
		}
		//调用taotao-order的服务提交订单。
		json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, JsonUtils.objectToJson(order));
		//把json转换成taotaoResult
		taotaoResult = TaotaoResult.format(json);
		if (taotaoResult.getStatus() == 200) {
			Object orderId = taotaoResult.getData();
			return orderId.toString();
		}
		return "";
	}

}