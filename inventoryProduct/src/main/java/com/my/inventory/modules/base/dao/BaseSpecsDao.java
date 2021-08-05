package com.my.inventory.modules.base.dao;

import com.my.inventory.modules.base.entity.BaseSpecs;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商品规格表 Mapper 接口
 * </p>
 *
 * @author BH.huang
 * @since 2021-07-07
 */
@Mapper
@Repository("baseSpecsDao")
public interface BaseSpecsDao extends BaseMapper<BaseSpecs> {

}
