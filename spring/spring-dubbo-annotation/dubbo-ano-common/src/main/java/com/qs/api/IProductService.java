package com.qs.api;

import com.qs.bean.Product;
import java.util.List;

/**
 * @author : qshomewy
 * @description : 产品服务接口类
 */
public interface IProductService {

	Product queryProductById(int id);

    List<Product> queryAllProducts();
}
