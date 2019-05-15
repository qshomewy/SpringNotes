package com.qs.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.qs.bean.Order;

/**
 * @author : qshomewy
 * @description : 订单实现
 */
@Service
public class OrderServiceImpl implements OrderService {

	public Order queryOrder(Long id) {
		return new Order(id, "苹果", new Date());
	}

	public Integer createOrder(Order order) {
		int i = 1 / 0;
		return i;
	}

	public Integer deleteOrder(Long id) {
		return 1;
	}
}
