package com.qs.rabbit.config;

import com.qs.bean.ProductManager;
import com.qs.bean.Programmer;

/**
 * 
 * @author : qshomewy
 * @description : 消息委派处理类
 */
public class MessageDelegate {

    public void handleMessage(ProductManager manager) {
        System.out.println("收到一个产品经理" + manager);
    }

    public void handleMessage(Programmer programmer) {
        System.out.println("收到一个程序员" + programmer);
    }

}
