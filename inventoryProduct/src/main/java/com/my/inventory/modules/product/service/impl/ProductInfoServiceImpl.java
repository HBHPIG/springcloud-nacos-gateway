package com.my.inventory.modules.product.service.impl;

import com.my.inventory.common.utils.RedisUtil;
import com.my.inventory.modules.product.entity.ProductInfo;
import com.my.inventory.modules.product.dao.ProductInfoDao;
import com.my.inventory.modules.product.service.ProductInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author BH.huang
 * @since 2021-07-08
 */
@Service("productInfoService")
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoDao, ProductInfo> implements ProductInfoService {

    @Autowired
    RedisUtil redisUtil;

    @Override
//    @Transactional
    public ProductInfo getProductBuId(Integer id) {
        return this.selectById(id);
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
    public String reductInventory () {



        return null;
    }
}
