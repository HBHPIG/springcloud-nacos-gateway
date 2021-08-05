package com.my.inventory.modules.base.controller;


import com.my.inventory.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品规格表 前端控制器
 * </p>
 *
 * @author BH.huang
 * @since 2021-07-07
 */
@RestController
@Api(tags = "商品规格")
@RequestMapping("/baseSpecs")
public class BaseSpecsController {

    @GetMapping("/query")
    @ApiOperation("查询规格")
    public R query(){

        return R.ok().put("hello","你好");
    }

}
