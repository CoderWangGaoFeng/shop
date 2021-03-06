package com.wgf.shop.modules;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 商品表
 */
@Entity
@Table(name="table_goods")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="商品实体",description="商品出入叄数")
public class GoodsModule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 类型
     */
    @ApiModelProperty(value="菜单id")
    private Long typeId;

    /**
     *  商品名称
     */
    private String name;

    /**
     * 商品图片
     */
    private String img;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品销量
     */
    private String saleNumber;

    /**
     * 所属用户
     */
    private String accountId;

    /**
     * 商品状态
     */
    private Boolean status;

    /**
     * 商品数量（不入库）
     */
    @Transient
    private String num;
    /**
     * 所属菜单名
     */
    @Transient
    private String typeName;

}
