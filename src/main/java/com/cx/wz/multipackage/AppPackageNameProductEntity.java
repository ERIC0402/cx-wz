package com.cx.wz.multipackage;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class AppPackageNameProductEntity {
    private long id;
    private String appPackageName;
    private String appName;
    private String bannerUrl;
    private Boolean validRequestSign;
    private Date createTime;
    private String status;

}
