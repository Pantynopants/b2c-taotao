package com.neusoft.order.pojo;

import java.util.List;

import com.neusoft.pojo.TbOrder;
import com.neusoft.pojo.TbOrderItem;
import com.neusoft.pojo.TbOrderShipping;

public class Order extends TbOrder {

	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	
	
}
