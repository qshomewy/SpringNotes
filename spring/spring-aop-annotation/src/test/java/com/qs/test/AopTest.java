package com.qs.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.qs.bean.Order;
import com.qs.config.AopConfig;
import com.qs.service.OrderService;

/**
 * @author : qshomewy
 * @description : 
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AopConfig.class)
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
	
	/**
     * 多个切面作用于同一个切入点时, 可以用@Order指定切面的执行顺序
     * 优先级高的切面在切入方法前执行的通知(before)会优先执行,
     * 但是位于方法后执行的通知(after,afterReturning)反而会延后执行
     */
    @Test
    public void deleteOrder() {
        orderService.deleteOrder(1001L);
    }
}
