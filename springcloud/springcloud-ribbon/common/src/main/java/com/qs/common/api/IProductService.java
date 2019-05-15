package com.qs.common.api;

import java.util.List;

import com.qs.common.bean.Product;

/**
 * 
 * @author : qshomewy
 * @description : 产品服务接口类
 */
public interface IProductService {

    Product queryProductById(int id);

    List<Product> queryAllProducts();

    void saveProduct(Product product);
}
