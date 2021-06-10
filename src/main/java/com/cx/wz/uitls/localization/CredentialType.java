package com.cx.wz.uitls.localization;

public enum CredentialType {
    KTP("enum.credential.type.ktp"),
    AADHAAR("enum.credential.type.aadhaar"),
    AADHAAR_2("enum.credential.type.aadhaar2"),
    PAN("enum.credential.type.pan"),
    PAN_2("enum.credential.type.pan2"),
    CMND("enum.credential.type.cmnd"), //9位或12位数字
    THID("enum.credential.type.thid"),
    MYID("enum.credential.type.myid"),
    SSS("enum.credential.type.sss_card"),// 菲律宾社保卡(SOCIAL SECURITY SYSTEM) 10位数字
    UMID("enum.credential.type.umid"),// 菲律宾身份证 Unified Multi-Purpose ID  12位数字
    PAGIBIG("enum.credential.type.pagibig_id"),
    PHILHEALTH ("enum.credential.type.philhealth_id"),
    TIN("enum.credential.type.tin_id"), // 菲律宾纳税卡,(DEPARTMENT OF FINANCE)签发，长度为 9+5 / 9+3 / 9
    GSIS("enum.credential.type.gsis_e_card"),// 菲律宾政府工作人员
    DRIVERLICENSE("enum.credential.type.driver.license"),// 驾驶证 1英文+10数字
    PASSPORT("enum.credential.type.passport"),// 护照 9位(可字母和数字)
    STUDENT("enum.credential.type.id_of_student"),// 菲律宾(学生证）
    PRC_ID("enum.credential.type.prc_id"),// Professional Regulation Commission (PRC) ID
    OTHERS("enum.credential.type.others"),
    CCCD("enum.credential.type.cccd"),
    NIN("enum.credential.type.nin"), // 尼日利亚证件
    CURP("enum.credential.type.curp"), // 墨西哥身份证
    ;

    private String code;

    CredentialType(String code) {
        this.code = code;
    }

    public String code() {
        return this.code;
    }
}
