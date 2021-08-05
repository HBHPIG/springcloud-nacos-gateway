package com.my.inventory.modules.product.service;

import com.my.inventory.modules.product.entity.ProductInfo;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author BH.huang
 * @since 2021-07-08
 */
public interface ProductInfoService extends IService<ProductInfo> {
    /**
     * 获取商品信息
     * @param id
     * @return
     */
    ProductInfo getProductBuId(Integer id);

    /**
     * 减库存
     * @return
     */
    String reductInventory();
}
