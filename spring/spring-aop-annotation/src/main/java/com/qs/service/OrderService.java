package com.qs.service;

import com.qs.bean.Order;

/**
 * @author : qshomewy
 * @description : 订单接口
 */
public interface OrderService {
	
	Order queryOrder(Long id);
	
	Integer createOrder(Order order);
	
	Integer deleteOrder(Long id);
}
