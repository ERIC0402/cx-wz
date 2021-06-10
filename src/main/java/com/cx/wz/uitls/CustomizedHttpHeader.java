package com.cx.wz.uitls;

public class CustomizedHttpHeader {
    //version interceptor
    public static final String APP_SOURCE ="X-APP-SOURCE";
    public static final String APP_PACKAGE_NAME ="X-APP-PACKAGE-NAME";
    public static final String APP_VERSION ="X-APP-VERSION";
    public static final String ANDROID_ID ="X-ANDROID-ID";
    public static final String APP_TYPE ="X-APP-TYPE";
    public static final String APP_YITU_VERSION ="X-APP-YITU-VERSION";
    public static final String APP_VERSION_NAME ="X-APP-VERSION-NAME";
    public static final String APP_NAME ="X-APP-NAME";
    public static final String APP_ROOT="X-APP-ROOTED";
    public static final String APP_SIM_MOBILE="X-APP-SIM-MOBILE";
    public static final String APP_RANDOM="X-APP-RANDOM";
    public static final String APP_SIGN="X-APP-SIGN";
    public static final String APP_VIRTUAL_DEVICE="X-APP-VIRTUAL-DEVICE";
    public static final String APP_COMPRESS_FACE_DATA="X-APP-COMPRESS-FACE-DATA";
    public static final String APP_CODE_VERSION="X-APP-CODE-VERSION"; // 如 "V10"
    public static final String APP_PROGRESS_VERSION="X-APP-PROGRESS-VERSION"; // 贷款申请的流程，参考枚举类 ProgressVersion
    public static final String APP_LOCALE="X-APP-LOCALE";// app当前语言
    public static final String FISH_UUID ="X-FISH-UUID";// 智鱼UUID
    public static final String ST_UUID ="X-ST-UUID";// 星合UUID

    //device interceptor

    public static final String AF_ID ="X-AF-ID";
    public static final String GA_ID ="X-GA-ID";
    public static final String MAC_ADDRESS ="X-MAC_ADDRESS";
    public static final String IOS_ID ="X-IOS-ID";
    public static final String H5_ID ="X-H5-ID";
    public static final String H5_SEARCH ="X-H5-SEARCH";
    public static final String REFERRER ="X-REFERRER";
    public static final String HTTP_REFERRER ="Referer";// 浏览器referrer
    public static final String REFERRER_ID ="X-REFERRER-ID";
    public static final String REFERRER_2 ="X-REFERRER-2";
    public static final String REFERRER_SDK ="X-REFERRER-SDK";

    public static final String DEVICE_SIGN="X-DEVICE-SIGN";
    public static final String SD_SIGN="X-SD-SIGN";
    public static final String SDK_VERSION="X-SDK-VERSION";
    public static final String HARDWARE_INFO="X-HAEDWARE-INFO";

    public static final String GP_PKG_FLAG="X-GP-PKG-FLAG";//GP包标志

    //auth
    public static final String AUTH_TOKEN ="X-AUTH-TOKEN";
    public static final String AUTH_ADMIN_TOKEN ="X-ADMIN-TOKEN";
    public static final String CAPTCHA = "X-CAPTCHA";
    public static final String CAPTCHA_SID = "X-CAPTCHA-SID";
    public static final String SMS_CODE = "X-SMS-CODE";

    public static final String AUTH_POSEIDON_TOKEN ="X-AUTH-POSEIDON-TOKEN";
    public static final String AUTH_POSEIDON_MOBILE ="X-AUTH-POSEIDON-MOBILE";

    //callback
    public static final String CALLBACK_TOKEN = "X-CALLBACK-TOKEN";

    //Razorpay
    public static final String RAZORPAY_SIGN = "X-Razorpay-Signature";
    //Razorpay
    public static final String HVPTO_SIGN = "token";

    //moxie callback
    public static final String X_MOXIE_EVENT = "X-Moxie-Event";
    public static final String X_MOXIE_TYPE = "X-Moxie-Type";
    public static final String X_MOXIE_SIGNATURE = "X-Moxie-Signature";
    public static final String X_MOXIE_UID = "X-Moxie-Uid";

    //odeo auth
    public static final String X_ODEO_AUTH = "Authorization";
    public static final String X_ODEO_TIMESTAMP = "X-Odeo-Timestamp";
    public static final String X_ODEO_SIGNATURE = "X-Odeo-Signature";

    public static final String JKS_SECRET = "Secret";

}
