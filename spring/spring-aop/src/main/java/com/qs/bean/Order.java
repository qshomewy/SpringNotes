package com.qs.bean;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : qshomewy
 * @description : 订单实体
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	private long id;
	
	private String productName;
	
	private Date orderTime;
}
