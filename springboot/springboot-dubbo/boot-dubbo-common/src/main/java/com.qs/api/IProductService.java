package com.qs.api;

import java.util.List;

import com.qs.bean.Product;

/**
 * 
 * @author : qshomewy
 * @description : 产品服务接口类
 */
public interface IProductService {

    Product queryProductById(int id);

    List<Product> queryAllProducts();
}
