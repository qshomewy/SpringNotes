package com.qs.common.feign;

import org.springframework.web.bind.annotation.*;

import com.qs.common.bean.Product;

import java.util.List;

/**
 * 
 * @author : qshomewy
 * @description : 声明式服务调用
 */
public interface ProductFeign {

    @GetMapping("products")
    List<Product> productList();

    /**
     * 这是需要强调的是使用feign时候@PathVariable一定要用value指明参数，
     * 不然会抛出.IllegalStateException: PathVariable annotation was empty on param 异常
     */
    @GetMapping("product/{id}")
    Product productDetail(@PathVariable(value = "id") int id);


    @PostMapping("product")
    void save(@RequestBody Product product);
}
