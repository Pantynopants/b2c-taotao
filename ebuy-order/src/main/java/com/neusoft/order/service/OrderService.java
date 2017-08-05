package com.neusoft.order.service;

import java.util.List;

import com.neusoft.common.pojo.TaotaoResult;
import com.neusoft.pojo.TbOrder;
import com.neusoft.pojo.TbOrderItem;
import com.neusoft.pojo.TbOrderShipping;

public interface OrderService {

	TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
}
