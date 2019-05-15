package com.qs.service;

import java.util.Date;

import com.qs.bean.Order;

/**
 * @author : qshomewy
 * @description : 订单实现
 */
public class OrderServiceImpl implements OrderService {

	public Order queryOrder(Long id) {
		return new Order(1001L, "苹果", new Date());
	}

	public Integer createOrder(Order order) {
		// 模拟异常
		int i = 1 / 0;
		return i;
	}
}
