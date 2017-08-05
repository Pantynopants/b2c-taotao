package com.neusoft.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.common.pojo.TaotaoResult;
import com.neusoft.portal.pojo.CartItem;

public interface CartService {

	TaotaoResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response);
	List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);
	TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);
	TaotaoResult updateCartItem(long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);
}
