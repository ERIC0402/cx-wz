package com.cx.wz.uitls.localization;


import com.google.common.collect.Lists;
import org.apache.commons.lang3.LocaleUtils;

import java.util.List;
import java.util.Locale;

public enum SuluLocale {
//    ID(LocaleUtils.toLocale("in_ID"), Currency.IDR, "62", Lists.newArrayList(CredentialType.KTP)),//印尼
//    IN(LocaleUtils.toLocale("kok_IN"), Currency.IDR, "91", Lists.newArrayList(CredentialType.AADHAAR)),//印度
//    ZH(LocaleUtils.toLocale("zh_CN"), Currency.IDR, "86", Lists.newArrayList(CredentialType.KTP)),//中文
//    PH(LocaleUtils.toLocale("en_US"), Currency.PHP, "63",
//            Lists.newArrayList(CredentialType.SSS,
//                    CredentialType.UMID,
//                    CredentialType.DRIVERLICENSE,
//                    CredentialType.PASSPORT,
//                    CredentialType.PHILHEALTH,
//                    CredentialType.PAGIBIG,
//                    CredentialType.PRC_ID,
//                    CredentialType.OTHERS
//            )),//菲律宾
//    VN(LocaleUtils.toLocale("vi_VN"), Currency.VND, "84", Lists.newArrayList(CredentialType.CMND, CredentialType.CCCD)),//越南
//    TH(LocaleUtils.toLocale("th_TH"), Currency.THB, "66", Lists.newArrayList(CredentialType.THID)),//泰国
//    MY(LocaleUtils.toLocale("ms_MY"), Currency.MYR, "60", Lists.newArrayList(CredentialType.MYID)),//马来西亚
//    ;

    ID(LocaleUtils.toLocale("in_ID"), Currency.IDR, "62") {
        public List<CredentialType> getCredentialType() {
            return Lists.newArrayList(CredentialType.KTP);
        }
    },//印尼
    IN(LocaleUtils.toLocale("kok_IN"), Currency.IDR, "91") {
        public List<CredentialType> getCredentialType() {
            return Lists.newArrayList(CredentialType.AADHAAR);
        }
    },//印度
    ZH(LocaleUtils.toLocale("zh_CN"), Currency.IDR, "86") {
        public List<CredentialType> getCredentialType() {
            return Lists.newArrayList(CredentialType.KTP);
        }
    },//中文
    PH(LocaleUtils.toLocale("en_US"), Currency.PHP, "63") {
        public List<CredentialType> getCredentialType() {
            return Lists.newArrayList(CredentialType.SSS,
                    CredentialType.UMID,
                    CredentialType.DRIVERLICENSE,
                    CredentialType.PASSPORT,
                    CredentialType.PHILHEALTH,
                    CredentialType.PAGIBIG,
                    CredentialType.PRC_ID,
                    CredentialType.OTHERS
            );
        }
    },//菲律宾
    VN(LocaleUtils.toLocale("vi_VN"), Currency.VND, "84") {
        public List<CredentialType> getCredentialType() {
            return Lists.newArrayList(CredentialType.CMND, CredentialType.CCCD);
        }
    },//越南
    TH(LocaleUtils.toLocale("th_TH"), Currency.THB, "66") {
        public List<CredentialType> getCredentialType() {
            return Lists.newArrayList(CredentialType.THID);
        }
    },//泰国
    MY(LocaleUtils.toLocale("ms_MY"), Currency.MYR, "60") {
        public List<CredentialType> getCredentialType() {
            return Lists.newArrayList(CredentialType.MYID);
        }
    },//马来西亚
    NG(LocaleUtils.toLocale("en_NG"), Currency.NGN, "234") {
        public List<CredentialType> getCredentialType() {
            return Lists.newArrayList(CredentialType.NIN);
        }
    },//尼日利亚
    MX(LocaleUtils.toLocale("es_MX"), Currency.MXN, "52") {
        public List<CredentialType> getCredentialType() {
            return Lists.newArrayList(CredentialType.CURP);
        }
    },//墨西哥
    ;


    private final Locale locale;
    private final Currency currency;
    private final String callingCode;

    SuluLocale(Locale locale, Currency currency, String callingCode) {
        this.locale = locale;
        this.currency = currency;
        this.callingCode = callingCode;
    }

    public Locale getLocale() {
        return locale;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getCallingCode() {
        return callingCode;
    }

    public abstract List<CredentialType> getCredentialType();
}
