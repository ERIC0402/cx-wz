package com.cx.wz.device.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceEntity {
    private long id;
    private String androidId;
    private String gId;
    private String ip;
    private Date createTime;
    private Date updateTime;
    private String macAddress;


    @Override
    public String toString() {
        return "DeviceEntity{" +
                "id=" + id +
                ", androidId='" + androidId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
