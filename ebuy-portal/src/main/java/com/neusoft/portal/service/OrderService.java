package com.neusoft.portal.service;

import com.neusoft.portal.pojo.Order;

public interface OrderService {

	String createOrder(Order order, String userToken);
}
