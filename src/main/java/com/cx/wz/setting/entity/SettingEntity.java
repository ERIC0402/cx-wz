package com.cx.wz.setting.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class SettingEntity {
    private Long id;
    private String key;
    private String value;
    private String reapplyValue;
    private String namespace;
    private String comments;
    private Date updateTime;
    private Date createTime;
    private Boolean visibility;
    private String description;
    private SettingType type;
    private String rule;
    private GroupType groupType;
    private Status status;

    public SettingEntity() {}

    public enum Status {
        ACTIVE,
        INACTIVE,
        ;
    }

    public enum SettingType {
        CHECKBOX,
        COMPLEX,
        RADIO,
        SWITCH,
        TEXT,
        VALUE,
        IMAGE,
    }

    public enum GroupType {
        APP,
        APPLY,
        COMPANY_INFO,
        FACE_ID_OCR,
        HIS_LOAN,
        IMEI,
        LOAN_ISSUE,
        OTHER,
        PERSONAL_INFO,
        RE_APPLY,
        RISK_REPORT,
        SMS,
        SMS_PHONE,
        THIRDPARTY,
        AADHAARPAN,
    }
}
