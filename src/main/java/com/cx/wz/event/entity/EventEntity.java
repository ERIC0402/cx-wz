package com.cx.wz.event.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;


@Data
@Accessors(chain = true)
@TableName("t_event")
public class EventEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    private Event event;

    private String packageName;

    private String androidId;

    private String source;

    private Date createTime;

    private Date updateTime;

    public enum Event{
        GOTO
    }


}


