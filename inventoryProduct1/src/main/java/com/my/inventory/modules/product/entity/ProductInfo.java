package com.my.inventory.modules.product.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *  商品信息
 * </p>
 *
 * @author BH.huang
 * @since 2021-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product_info")
public class ProductInfo extends Model<ProductInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableId(value = "info_id", type = IdType.AUTO)
    private Integer infoId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品编码
     */
    @TableField("info_code")
    private String infoCode;

    /**
     * 金额
     */
    private BigDecimal price;

    /**
     * 创建日期
     */
    private Date creatime;

    /**
     * 创建者
     */
    private String optuser;

    /**
     * 状态：0无效，1有效，2删除
     */
    private Integer status;

    /**
     * 备注
     */
    private String remarks;


    @Override
    protected Serializable pkVal() {
        return this.infoId;
    }

}
