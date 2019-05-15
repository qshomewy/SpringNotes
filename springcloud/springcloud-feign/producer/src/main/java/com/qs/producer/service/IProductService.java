package com.qs.producer.service;

import com.qs.common.bean.Product;

import java.util.List;
public interface IProductService {

    Product queryProductById(int id) ;

    List<Product> queryAllProducts();

    void saveProduct(Product product);
}
