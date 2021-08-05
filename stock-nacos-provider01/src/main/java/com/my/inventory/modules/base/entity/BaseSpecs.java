package com.my.inventory.modules.base.entity;

import com.baomidou.mybatisplus.enums.IdType;
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
 * 商品规格表
 * </p>
 *
 * @author BH.huang
 * @since 2021-07-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("base_specs")
public class BaseSpecs extends Model<BaseSpecs> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品规格ID
     */
    @TableId(value = "specs_id", type = IdType.AUTO)
    private Integer specsId;

    /**
     * 商品规格名称
     */
    @TableField("specs_name")
    private String specsName;

    /**
     * 商品规格编码
     */
    @TableField("specs_code")
    private String specsCode;

    /**
     * 公司ID
     */
    @TableField("company_id")
    private Long companyId;

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

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;


    @Override
    protected Serializable pkVal() {
        return this.specsId;
    }

}
