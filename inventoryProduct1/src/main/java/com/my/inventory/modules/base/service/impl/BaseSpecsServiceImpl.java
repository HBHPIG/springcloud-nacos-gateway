package com.my.inventory.modules.base.service.impl;

import com.my.inventory.modules.base.entity.BaseSpecs;
import com.my.inventory.modules.base.dao.BaseSpecsDao;
import com.my.inventory.modules.base.service.BaseSpecsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品规格表 服务实现类
 * </p>
 *
 * @author BH.huang
 * @since 2021-07-07
 */
@Service("baseSpecsService")
public class BaseSpecsServiceImpl extends ServiceImpl<BaseSpecsDao, BaseSpecs> implements BaseSpecsService {

}
