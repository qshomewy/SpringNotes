package com.qs.consumer.feign;

import com.qs.common.feign.ProductFeign;
import com.qs.consumer.config.FeignConfig;
import com.qs.consumer.feign.impl.CProductFeignImpl;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 
 * @author : qshomewy
 * @description : 声明式接口调用
 */
@FeignClient(value = "producer",configuration = FeignConfig.class,fallback = CProductFeignImpl.class)
public interface CProductFeign extends ProductFeign {

}
