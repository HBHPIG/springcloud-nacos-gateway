package com.my.inventory.modules.product.dao;

import com.my.inventory.modules.product.entity.ProductInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author BH.huang
 * @since 2021-07-08
 */
@Mapper
@Repository("productInfoDao")
public interface ProductInfoDao extends BaseMapper<ProductInfo> {

}
