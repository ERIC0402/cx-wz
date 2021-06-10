package com.cx.wz.uitls;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AppInfo {
    private Integer appVersion;
    private String appPackageName;
    private String appSource;
    private String deviceStrId;
    private SourceType sourceType;
    private String ip;
    private String gaid;
    private String macAddress;
    private String fishUuid;
    private String stUuid;
    private String androidId;
    private String deviceNo;
    private String remoteUrl;

    public AppInfo() {
    }

    public AppInfo(String appPackageName, Integer appVersion) {
        this.appPackageName = appPackageName;
        this.appVersion = appVersion;
    }

    public AppInfo(String appPackageName, Integer appVersion, String appSource, String deviceStr, SourceType sourceType) {
        this.appPackageName = appPackageName;
        this.appVersion = appVersion;
        this.appSource = appSource;
        this.deviceStrId = deviceStr;
        this.sourceType = sourceType;
    }

    public AppInfo(String appPackageName, Integer appVersion, String appSource, String deviceStr, SourceType sourceType, String ip) {
        this.appPackageName = appPackageName;
        this.appVersion = appVersion;
        this.appSource = appSource;
        this.deviceStrId = deviceStr;
        this.sourceType = sourceType;
        this.ip = ip;
    }

    public enum SourceType {
        APP,
        H5
    }
}
