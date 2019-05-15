package com.qs.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.qs.bean.Order;
import com.qs.service.OrderService;

/**
 * @author : qshomewy
 * @description : 关于多个切面在同一个切入点上执行顺序的例子 可以在spring-aop-annotation中查看
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:aop.xml")
public class AopTest {

	@Autowired
    private OrderService orderService;

    @Test
    public void queryOrder() {
 		orderService.queryOrder(1001L);
    }
  
    @Test
    public void createOrder() {
    	orderService.createOrder(new Order(1001L, "苹果", new Date()));
    }
}
