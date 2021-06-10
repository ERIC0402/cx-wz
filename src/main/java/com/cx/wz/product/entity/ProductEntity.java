package com.cx.wz.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@Data
@Accessors(chain = true)
@TableName("t_product")
public class ProductEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private long id;
    // 产品名称
    private String name;

    private String shortDes;

    private String bg;

    private Integer url;

    private Boolean isToDetail;

    private String title;

    private String description;

    private String footDes;
    // 产品状态
    private Status status;

    public enum Status {
        ACTIVE,
        INACTIVE,
        ;
    }

}


