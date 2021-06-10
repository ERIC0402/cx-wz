package com.cx.wz.setting;

public enum SettingConfig {
    APPLICATION_TOTAL_LOAN_AMOUNT("loan_application", "total_loan_amount"),
    APPLICATION_DAILY_LOAN_AMOUNT("loan_application", "daily_loan_amount"),
    APPLICATION_ANDROID_VERSION("loan_app_client", "android_version"),
    APPLICATION_ANDROID_PACKAGE_VERSION("loan_app_client", "android_package_version"),
    APPLICATION_ANDROID_VERSION_NAME("loan_app_client", "android_version_name"),
    APPLICATION_ANDROID_VERSION_CHANGELOGS("loan_app_client", "android_version_changelogs"),
    APPLICATION_UPLOAD_CONTACT_VERSION("loan_app_client", "upload_contact_version"),
    APPLICATION_CONTACT_NBFC_NAME("loan_app_client", "contact.nbfc.name"),
    APPLICATION_LOAN_AGE_RANGE("loan_app_client", "app.loan.age.range"),
    APPLICATION_CHECK_BANKCARD("loan_app_client", "app.check.bankcard"),

    APPLICATION_IOS_VERSION("loan_app_client", "ios_version"),

    APPLICATION_UPDATE_URL("loan_application", "update_url"),
    APPLICATION_H5_URL("loan_application", "h5_url"),

    APPLICATION_SET_WORK_PHOTOS("loan_app_client", "set_work_photos"),
    APPLICATION_SET_WORK_INFO("loan_app_client", "set_work_info"),
    APPLICATION_SHOW_WORK_INFO("loan_app_client", "show_work_photos"),
    //SEC 规范
    APPLICATION_SEC_COMPANY_NAME("loan_app_client", "sec.company.name"),
    APPLICATION_SEC_COMPANY_NUMBER("loan_app_client", "sec.company.number"),
    APPLICATION_SEC_AUTHORITY_NUMBER("loan_app_client", "sec.authority.number"),
    /**
     * Set the loan app setting type
     */
    PAGE_LOAN_TEMPLATE_NO("page_template_no", "loan_detail_no"),

    APPLICATION_MAX_AMOUNT("loan_application", "set_max_amount"),
    APPLICATION_MIN_AMOUNT("loan_application", "set_min_amount"),
    APPLICATION_AMOUNT_STEP("loan_application", "set_amount_step"),
    APPLICATION_ALLOW_MORE_REPAYMENT_AMOUNT("loan_application", "allow.more.repayment.amount"),


    APPLICATION_MAX_PERIOD("loan_application", "set_max_period"),
    APPLICATION_MIN_PERIOD("loan_application", "set_min_period"),
    APPLICATION_PERIOD_STEP("loan_application", "set_period_step"),

    APPLICATION_PERIOD_UNIT("loan_application", "set_period_unit"),
    APPLICATION_INTEREST_RATE("loan_application", "set_interest_rate"),
    APPLICATION_SERVICE_RATE("loan_application", "set_service_rate"),
    APPLICATION_SEND_SMS_CLOSE("loan_application", "send.sms.close"),
    APPLICATION_SEND_SMS_REJECT("loan_application", "send.sms.reject"),
    APPLICATION_SEND_SMS_CLEAR_SIGN("loan_application", "send.sms.clear.sign"),
    APPLICATION_LOAN_ISSUE_BANKCARD_KTP_LIMIT("loan_application", "loan.issue.bankcard.ktp.limit"),
    APPLICATION_LOAN_ISSUE_BANKCARD_FULLNAME_VERIFY("loan_application", "loan.issue.bankcard.fullname.verify"),
    APPLICATION_LOAN_ISSUE_BANKCARD_KTP_LIMIT_ACTION("loan_application", "loan.issue.bankcard.ktp.limit.action"),
    APPLICATION_SEND_SMS_MOBILE_COUNT("loan_application", "collection.sms.mobile.count"),
    APPLICATION_SEND_SMS_CONTENT_COUNT("loan_application", "collection.sms.content.count"),
    APPLICATION_SEND_SMS_COLLECTOR_EVERY_DAY_COUNT("loan_application","collection.sms.collector.every.day.count"),
    APPLICATION_APP_DESCRIPTION("loan_app_client", "app_description"),
    APPLICATION_APP_LOGIN_TYPE("loan_app_client", "android_login_type"),
    APPLICATION_APP_VAYHUB("loan_app_client", "vayhub.switch"),
    APPLICATION_APP_ADVANCE_FACE_RECOGNITION_ACCESS_KEY("loan_app_client", "advance.face.recognition.access.key"),
    APPLICATION_APP_ADVANCE_FACE_RECOGNITION_SECRET_KEY("loan_app_client", "advance.face.recognition.secret.key"),
    APPLICATION_APP_ADVANCE_PICTURE_QUALITY_ACCESS_KEY("loan_app_client", "advance.picture.quality.access.key"),
    APPLICATION_APP_ADVANCE_PICTURE_QUALITY_SECRET_KEY("loan_app_client", "advance.picture.quality.secret.key"),

    APPLICATION_APP_MOTION_FACE_RECOGNITION_ACCESS_KEY("loan_app_client", "motion.face.recognition.api.key"),
    APPLICATION_APP_MOTION_FACE_RECOGNITION_SECRET_KEY("loan_app_client", "motion.face.recognition.secret.key"),

    APPLICATION_APP_ALGO360_SDK_CLIENT_ID("loan_app_client","algo360.sdk.client.id"),
    APPLICATION_APP_ALGO360_SDK_CLIENT_SECRET("loan_app_client","algo360.sdk.client.secret"),
    APPLICATION_APP_ALGO360_SDK_SWITCH("loan_app_client","algo360.sdk.switch"),

    APPLICATION_APP_VIDEO_VERIFICATION("loan_app_client", "video.verification"),
    APPLICATION_CUSTOMER_MOBILE("loan_app_client", "customer_mobile"),
    APPLICATION_CUSTOMER_COMPANY_NAME("loan_app_client", "customer_company_name"),
    APPLICATION_AF_KEY("loan_app_client", "af_key"),
    APPLICATION_GOOGLE_MAPS_API_KEY("loan_application", "google_maps_api_key"),
    APPLICATION_HARVEST_HIGHLIGHT_SMS("loan_application", "harvest.highlight.sms"),
    APPLICATION_HARVEST_HIGHLIGHT_CONTACT("loan_application", "harvest.highlight.contact"),
    APPLICATION_DEPAY_REJECT_SEND("loan_application","delay.display.reject"),

    APPLICATION_DD_PMML_FLAG("loan_application","dd.pmml.result.flag"),

    APPLICATION_PASSWD_EXPIRED_DAYS("loan_application","passwd.expired.days"),

    APPLICATION_LOAN_RETURN_TIME_SUPPORT("loan_application", "loan.issue.return.time.support"),

    APPLICATION_LOAN_PAYSLIP("loan_application", "loan.file.payslip"),
    APPLICATION_LOAN_CAN_IGNORE_PAYSLIP("loan_application", "loan.can.ignore.payslip"),

    APPLICATION_OVER_DUE("loan_application","set_over_due_rate"),
    APPLICATION_GRACE_PERIOD("loan_application","set_grace_period_rate"),
    APPLICATION_ROLLVER_REVIEW_SWITCH("loan_application","rollver.review.switch"), //展期审核开关

    //提示用户增加紧急联系人和共同担保人的文案
    APPLICATION_PROMPT_USER_TO_INCREASE_CONTACT_COPY("loan_application","prompt.user.to.increase.contact.copy"), //展期审核开关

    //YITU OCR 模式选择
    YITU_OCR_MODEL("loan_application","yitu.ocr.model"),

    ACTIVITY_INVITATION_COUSTOMER_COUNT("activity_invitation", "set_customer_count"),
    ACTIVITY_INVITATION_DISCHARGE_INTEREST_DAYS("activity_invitation", "set_discharge_interest_days"),
    ACTIVITY_INVITATION_INVITEE_DISCHARGE_INTEREST_DAYS("activity_invitation", "set_invitee_discharge_interest_days"),
    ACTIVITY_INVITATION_BEGIN_DATE("activity_invitation", "set_begin_date"),
    ACTIVITY_INVITATION_ACTIVE("activity_invitation", "set_active"),

    FACEID_ENABLE("faceid", "enable"),
    FACEID_TRY_LIMIT("faceid", "try_limit"),
    FACEID_IGNORE("faceid", "ignore"),
    FACEID_LOAN_MARKET_IGNORE("faceid", "loan.market.ignore"),

    FACEID_ADVANCE_COMPARE_PASS("faceid", "advance.compare.pass.value"),

    FACEID_MENGXIA_COMPARE_PASS("faceid", "mengxia.compare.pass.value"),

    //AnCash

    ANCASH_COMPARE_PASS("ancash","ancash.compare.pass.value"),
    ANCASH_IGNORE("ancash", "ignore"),
    ANCASH_ENABLE("ancash", "enable"),

    //jiuwei
    JIUWEI_NAMESPACE("jiuwei","jiuwei_channel"),
    JIUWEI_CHANNEL_CALLBACK_URL("jiuwei","jiuwei_channel_callback_url"),

    //alarm
    alarm_email_send_account("alarm","alarm.email.send.account"),
    alarm_email_send_password("alarm","alarm.email.send.password"),
    alarm_email_send_SMTPHost("alarm","alarm.email.send.SMTPHost"),
    ALARM_EMAIL_TRANSPORT_PROTOCOL("alarm","alarm.email.transport.protocol"),
    ALARM_EMAIL_SEND_SMTPPORT("alarm","alarm.email.send.SMTPPort"),
    alarm_email_send_receive_pre_review_accounts("alarm","alarm.email.send.receive.pre.review.accounts"),
    alarm_email_send_receive_res_sms_succeed_bottom_rate_accounts("alarm","alarm.email.send.receive.res.sms.succeed.bottom.rate.accounts"),
    alarm_email_send_receive_default_accounts("alarm","alarm.email.send.receive.default.accounts"),
    alarm_email_send_receive_alarm_up_accounts("alarm","alarm.email.send.receive.alarm.up.accounts"),
    alarm_check_issue_failed_up("alarm","alarm.check.issue.failed.up"),
    alarm_check_sms_succeed_rate("alarm","alarm.check.sms.succeed.rate"),


    //system
    SYSTEM_OFFICIAL_WEBSITE("system", "official_website"),
    SYSTEM_API_MARKET_FAILED_RETRY_HOUR("system", "api.market.failed.retry.limithour"),
    SYSTEM_RISK_REPORT_FAILED_RETRY_HOUR("system", "risk.report.failed.retry.limithour"),
    SYSTEM_API_MARKET_SWITCH("system","api.market.switch"),
    SYSTEM_API_MARKET_PROTOCOL_DAY("system","api.market.protocol.day"),
    SYSTEM_SMS_PH_INFO_BIP_SENDER_NAME("system","sms.ph.info.bip.sender.name"),
    SYSTEM_SKYPAY_REALTIME_SENDER_ADDRESS("system","skypay.realtime.sender.address"),
    SYSTEM_PLATFROM_LOCAL("system", "platform_locale"),
    SYSTEM_FOOTER_COPYRIGHT("system", "footer-copyright"),
    SYSTEM_SKYLINE_SENDERID("system" , "sms.skyline.senderid"),
    SYSTEM_SKYLINE_SENDERID_OTP("system" , "sms.skyline.senderid.otp"),
    BUILD_APK_LIMIT("system", "build.apk.limit"),
    SYSTEM_VN_FUNPAY_WITHDRAW_HINT("system", "system.vn.funpay.withdraw.hint"),
    SYSTEM_VN_SUPERPAY_WITHDRAW_HINT("system", "system.vn.superpay.withdraw.hint"),
    CUSTOMER_INFO_CHANGE_FLASH("system", "customer.info.change.flash"),
    CUSTOMER_CREDENTIAL_TYPE_LIMIT("system", "credential.type.setting.limit"),
    CUSTOMER_CREDENTIAL_TYPE_NOT_SUPPORT("system", "credential.type.setting.not.support"),

    SYSTEM_HARVEST_EXPIRED_HOURS("loan_application","harvest.info.wait.hours"),
    SYSTEM_HARVEST_EXPIRED_ACTION("loan_application","harvest.info.wait.due.action"),
    SYSTEM_HARVEST_CENTER_INFO_EXPIRED_HOURS("loan_application","harvest.center.info.expired.hours"),
    SYSTEM_HARVEST_LOCAL_INFO_EXPIRED_HOURS("loan_application","harvest.local.info.expired.hours"),
    SYSTEM_DRAGONPAY_SWITCH_FOR_OLD_VERSION("system","dragonpay.switch.for.old.version"),
    SYSTEM_DRAGONPAY_DISBURSE_BEFORE_HOUR("system","dragonpay.disburse.before.hour"),
    SYSTEM_TESTING_ACCOUNT_WITHOUT_PREVIEW("system","testing.account.without.preview"),

    SYSTEM_VAYHUB_CALL_SWITCH("system","vayhub.call.switch"),
    SYSTEM_VAYHUB_RISK_SWITCH("system","vayhub.risk.switch"),

    LOAN_APP_REPAYMENT_TYPE("loan_application","loan.app.repayment.type"),
    LOAN_APP_REPAYMENT_CHANNEL("loan_application","loan.app.repayment.channel"),

    LOAN_APP_CHEN_SCORE_AUTO_ISSUE("loan_application","chen.score.auto.issue.score"),
    LOAN_APP_MAO_PAO_SCORE("loan_application","maopao.score.auto.stepflow"),

    SYSTEM_DRAGONPAY_DISBURSE_PROPORTION("system", "dragonpay.disburse.proportion"),
    SYSTEM_SKYPAY_DISBURSE_PROPORTION("system", "skypay.disburse.proportion"),
    SYSTEM_PH_NEW_CONTRACT_FOR_OLD_VERSION("system", "ph.new.contract.for.old.version"),
    SYSTEM_REST_FAKE_PRODUCT_FLAG_IP_CHECK("system", "rest.fakeProductFlag.ip.check"),
    //复借用户是否需要重新上传紧急联系人和共同担保人开关
    SYSTEM_REAPPLY_NEED_RESUBMIT_CONTACT("system", "reapply.need.resubmit.contact"),

    //PH disburse channel, just use namespace

    // company
    COMPANY_SYSTEM_TITLE("company", "company_system_title"),
    COMPANY_SYSTEM_SUB_TITLE("company", "company_system_sub_title"),
    COMPANY_SYSTEM_COPYRIGHT("company", "company_system_copyright"),
    COMPANY_SYSTEM_LOGO_IMG("company", "company_system_logo_img"),
    COMPANY_SYSTEM_SLIDE_TITLE("company", "company_system_slide_title"),
    COMPANY_SYSTEM_FAVICON_IMG("company", "company_system_favicon_img"),
    // 客户唯一编号标识，微信群 ID
    COMPANY_CROP_ID("company", "company_wx_corp_id"),

    COMPANY_MULTI_LOGIN_CHECK("company", "multi.login.check"), //是否校验管理员多处登录

    //client me
//    ME_HOTLINE("loan_app_client","me_hotline"),
    ME_HELP_CENTER_URL("loan_app_client","me_help_center_url"),

    REQUEST_SIGN_VALIDATE("loan_app_client","valid.request.sign"),

    APP_PUSH_FLAG("loan_app_client","app.push.flag"),

    HARVEST_GRANT_STATUS_NOT_GRANT("loan_app_client","harvest.authorization.grant.status.not_grant"),
    HARVEST_GRANT_STATUS_GRANTED("loan_app_client","harvest.authorization.grant.status.granted"),
    HARVEST_GRANT_STATUS_EXPIRED_NEED_REGRANTED("loan_app_client","harvest.authorization.grant.status.expired_need_regrant"),
    APPLICATION_APPLY_VERSION("loan_app_client","client.loan.apply.version"),
    APP_REST_URL_SIGN_KEY("loan_application","rest_url_sign_key"),
    //assistant App
    SYSTEM_ASSISTANT_APP_PRO("system","assistant.app.pro"),

    //iOS_audit
    IOS_AUDIT_SWITCH("ios_audit","switch"),
    IOS_AUDIT_VERSION("ios_audit","version"),
    IOS_AUDIT_MOBILE("ios_audit","mobile"),

	//三方数据源至少绑定几项
    THIRDPARTY_DATA_MIN_COUNT("thirdparty_data", "MIN_COUNT"),
    //三方数据源绑定有效天数
    THIRDPARTY_DATA_VALID_DAYS("thirdparty_data", "VALID_DAYS"),
    // repayment willingness
    REPAYMENT_WILLINGNESS_SCORE("risk","loan.repayment.willingness.score"),
    REPAYMENT_WILLINGNESS_SCORE_TYPE("risk","loan.repayment.willingness.score.service.type"),
    REPAYMENT_WILLINGNESS_SCORE_SERVICE_URL("risk","loan.repayment.willingness.score.service.url"),

    CUSTOM_RISK_SERVICE("risk","custom.risk.service"),
    LIANGZI_RISK_SCORE("risk","liangzi.risk.score"),

    SYSTEM_FUNPAY_VTP_SHOP_LINK("loan_app_client","funpay.offline.vtp.deposit.shop.link.url"),
    SYSTEM_FUNPAY_VC_BANK_TYPE("loan_app_client","funpay.vc.bank.type"),

    SMS_VN_TRANSFORM_LANGUAGE("loan_application" , "sms.vn.transform.language"),
    VN_DISBURSE_FUNPAY("system_disburse_namespace", "FUNPAY"),
    VN_DISBURSE_NGANLUONG("system_disburse_namespace", "NGANLUONG"),
    VN_DISBURSE_BAOKIM("system_disburse_namespace", "BAOKIM"),

    VN_DEPOSIT_BANK_CARD("system_deposit_namespace", "BANK_CARD"),
    VN_DEPOSIT_NGANLUONG("system_deposit_namespace", "NGANLUONG"),
    VN_DEPOSIT_EPAY_VA("system_deposit_namespace", "EPAY_VA"),
    VN_DEPOSIT_ODDPAY_ONLINE("system_deposit_namespace", "ODDPAY_ONLINE"),
    VN_DEPOSIT_ODDPAY_VA("system_deposit_namespace", "ODDPAY_VA"),
    VN_DEPOSIT_PEARLPAY_VA("system_deposit_namespace", "PEARLPAY_VA"),
    VN_DEPOSIT_BAOKIM_VA("system_deposit_namespace", "BAOKIM_VA"),
    VN_DEPOSIT_SUPERPAY_VA("system_deposit_namespace", "SUPERPAY_VA"),
    VN_DEPOSIT_ZOOMPAY_VA("system_deposit_namespace", "ZOOMPAY_VA"),

    APPLICATION_SMS_FLAG("loan_application", "sms_flag"),
    APPLICATION_TONGDUN_FLAG("loan_application", "tongdun_flag"),

    VN_DISBURSE_ROUTE_TACTICS("vn_disburse_route_tactics", "issue_failed_count_strategy"),
    VN_DISBURSE_ROUTE_SCORE_NGANLUONG("vn_disburse_route_score", "NGANLUONG"),
    VN_DISBURSE_ROUTE_SCORE_FUNPAY("vn_disburse_route_score", "FUNPAY"),
    VN_DISBURSE_ROUTE_SCORE_EPAY("vn_disburse_route_score", "EPAY"),

    POST_PREVIEW_STEPS("post_prereview","post.preview.steps"),
    REVIEW_ASSIGN_STRATEGY("review_assign_strategy","review.assign.strategy"),

    PH_CHANNEL_FEE_ML("ph_channel_fee_impute_to_customer", "M_LHUILLIER"),
    PH_CHANNEL_FEE_COINS("ph_channel_fee_impute_to_customer", "COINS"),
    PH_CHANNEL_FEE_G_CASH("ph_channel_fee_impute_to_customer", "G_CASH"),
    PH_CHANNEL_FEE_RD("ph_channel_fee_impute_to_customer", "RD_PAWNSHOP"),
    PH_CHANNEL_SKYPAY_BANKS("ph_channel_fee_impute_to_customer", "SKYPAY_BANKS"),
    PH_CHANNEL_DRAGONPAY_BANKS("ph_channel_fee_impute_to_customer", "DRAGONPAY_BANKS"),

    PH_DRAGONPAY_LIFETIMEID_EMAIL("system", "DRAGONPAY_lifetimeId_email"),

    PH_CHANNEL_FEE_OLD_APP_DEDUCTION("loan_application", "ph_channel_fee_old_version_deduction"),
    PH_CHANNEL_FEE_NEW_APP_DEDUCTION("loan_application", "ph_channel_fee_new_version_deduction"),

    //other
    REGISTER_NOT_APPLY_REMINDER("other", "register_not_apply_reminder"),
    CLEAR_NOT_BORROWED_REMINDER("other", "clear_not_borrowed_reminder"),

    VN_DEPOSIT_BANK_CARD_H5_DISPLAY("system_deposit_h5","bank_card"),
    VN_DEPOSIT_NGANLUONG_H5_DISPLAY("system_deposit_h5","nganluong"),
    VN_DEPOSIT_FUNPAY_ONLINE_H5_DISPLAY("system_deposit_h5","funpay_online"),
    VN_DEPOSIT_FUNPAY_OFFLINE_H5_DISPLAY("system_deposit_h5","funpay_offline"),
    VN_DEPOSIT_FUNPAY_VC_H5_DISPLAY("system_deposit_h5","funpay_vc"),
    VN_DEPOSIT_BAOKIM_VA_H5_DISPLAY("system_deposit_h5","baokim_va"),
    VN_DEPOSIT_EPAY_VA_H5_DISPLAY("system_deposit_h5","epay_va"),
    VN_DEPOSIT_ODDPAY_ONLINE_H5_DISPLAY("system_deposit_h5","oddpay_online"),
    VN_DEPOSIT_SUPERPAY_VA_H5_DISPLAY("system_deposit_h5","superpay_va"),
    VN_DEPOSIT_ODDPAY_VA_H5_DISPLAY("system_deposit_h5","oddpay_va"),
    VN_DEPOSIT_PEARLPAY_VA_H5_DISPLAY("system_deposit_h5","pearlpay_va"),
    VN_DEPOSIT_ZOOMPAY_VA_H5_DISPLAY("system_deposit_h5","zoompay_va"),

    //PH
    SYSTEM_PH_WITHDRAW_CHANNEL_SWITCH("system.ph.withdraw.channel.switch", "auto"),

    //ph deposit partner switch
    PH_DEPOSIT_PARTNER_SWITCH("ph_deposit_partner_switch",""),
    PH_DEPOSIT_PARTNER_MYSUPERPAY_SWITCH("ph_deposit_partner_switch","MYSUPERPAY"),
    PH_DEPOSIT_PARTNER_SKYPAY_SWITCH("ph_deposit_partner_switch","SKYPAY"),
    PH_DEPOSIT_PARTNER_DRAGONPAY_SWITCH("ph_deposit_partner_switch","DRAGONPAY"),
    //ph deposit partner score
    PH_DEPOSIT_PARTNER_SCORE("ph_deposit_partner_score", ""),
    //ph disburse switch
    PH_DISBURSE_PARTNER_SWITCH("ph_disburse_partner_switch",""),
    PH_DISBURSE_PARTNER_MYSUPERPAY_SWITCH("ph_disburse_partner_switch","MYSUPERPAY"),
    PH_DISBURSE_PARTNER_DRAGONPAY_SWITCH("ph_disburse_partner_switch","DRAGONPAY"),
    PH_DISBURSE_PARTNER_SKYPAY_SWITCH("ph_disburse_partner_switch","SKYPAY"),
    //ph disburse score
    PH_DISBURSE_PARTNER_SCORE("ph_disburse_partner_score", ""),

    PH_DEPOSIT_SUPPORT_LARGE_AMOUNT_REPAYMENT("system","support.large.repayment.amount"),
    //ph disburse switch
    PH_DISBURSE_DRAGONPAY_SWITCH("ph_disburse_switch","DRAGONPAY"),
    PH_DISBURSE_SKYPAY_BANK_SWITCH("ph_disburse_switch","SKYPAY_SUPPORTED_BANK"),

    PH_SKYPAY_UPGRADE_SWITCH("ph_skypay_upgrade_switch","is_upgrade"),

    //IN
    SYSTEM_IN_DISBURSE_CHANNEL("system.in.disburse.channel", "disburse.channel"),


    SYSTEM_WEBCALL_CHANNEL("system", "webcall.channel"),


    STAR_COMPLEX_NET("star.fee","star.complex.net"),//星探-贷前复杂网络
    STAR_VALIDATE_BANKCARD("star.fee","star.validate.bankcard"),//星探-贷前银行卡校验
    STAR_MULTI_MOBILE("star.fee","star.multi.mobile"),//星探-贷前一人多号
    STAR_PHONE_AGE("star.fee","star.phone.age"),//星探-贷前号码在网时长
    STAR_PHONE_DEPOSIT("star.fee","star.phone.deposit"),//星探-贷前号码充值行为
    STAR_PHONE_PREFERENCE("star.fee","star.phone.preference"),//星探-贷前兴趣偏好
    STAR_PHONE_VERIFY("star.fee","star.phone.verify"),//星探-贷前号码实名认证
    STAR_IDENTITY_CHECK("star.fee","star.identity.check"),//星探-贷前身份信息认证
    STAR_MOBILE_MULTI_INQUIRY_V1("star.fee","star.mobile.multi.inquiry.v1"),//星探-贷前手机号多头查询V1
    STAR_KTP_MULTI_INQUIRY_V1("star.fee","star.ktp.multi.inquiry.v1"),//星探-贷前证件号多头查询V1
    STAR_MOBILE_MULTI_INQUIRY_V2("star.fee","star.mobile.multi.inquiry.v2"),//星探-贷前手机号多头查询V2
    STAR_KTP_MULTI_INQUIRY_V2("star.fee","star.ktp.multi.inquiry.v2"),//星探-贷前证件号多头查询V2
    STAR_MOBILE_MULTI_INQUIRY_V3("star.fee","star.mobile.multi.inquiry.v3"),//星探-贷前手机号多头查询V3
    STAR_KTP_MULTI_INQUIRY_V3("star.fee","star.ktp.multi.inquiry.v3"),//星探-贷前证件号多头查询V3
    STAR_MOBILE_MULTI_INQUIRY_V4("star.fee","star.mobile.multi.inquiry.v4"),//星探-贷前手机号多头查询V4
    STAR_KTP_MULTI_INQUIRY_V4("star.fee","star.ktp.multi.inquiry.v4"),//星探-贷前证件号多头查询V4
    STAR_MULTI_INQUIRY_COMPLEX_V1("star.fee","star.multi.inquiry.complex.v1"),//星探-多头查询COMPLEX V1
    STAR_BLACKLIST_RELATED_V3("star.fee","star.blacklist.related.v3"),//星探-贷前关联黑名单v3
    STAR_IS_FACEBOOK("star.fee","star.is.facebook"),//星探-facebook开通检测
    STAR_IS_WHATSAPP("star.fee","star.is.whatsapp"),//星探-whatsapp开通检测

    RULE_STAR_COMPLEX_NET("risk","star.complex.net.2.rule"),//星探-贷前复杂网络
    RULE_STAR_VALIDATE_BANKCARD("risk","star.validate.bankcard.2.rule"),//星探-贷前银行卡校验
    RULE_STAR_MULTI_MOBILE("risk","star.multi.mobile.2.rule"),//星探-贷前一人多号
    RULE_STAR_PHONE_AGE("risk","star.phone.age.rule"),//星探-贷前号码在网时长
    RULE_STAR_PHONE_DEPOSIT("risk","star.phone.deposit.2.rule"),//星探-贷前号码充值行为
    RULE_STAR_PHONE_PREFERENCE("risk","star.phone.preference.2.rule"),//星探-贷前兴趣偏好
    RULE_STAR_PHONE_VERIFY("risk","star.phone.verify.2.rule"),//星探-贷前号码实名认证
    RULE_STAR_IDENTITY_CHECK("risk","star.identity.check.2.rule"),//星探-贷前身份信息认证
    RULE_STAR_MOBILE_MULTI_INQUIRY_V1("risk","star.mobile.multi.inquiry.v1.2.rule"),//星探-贷前手机号多头查询V1
    RULE_STAR_KTP_MULTI_INQUIRY_V1("risk","star.ktp.multi.inquiry.v1.2.rule"),//星探-贷前证件号多头查询V1
    RULE_STAR_MOBILE_MULTI_INQUIRY_V2("risk","star.mobile.multi.inquiry.v2.2.rule"),//星探-贷前手机号多头查询V2
    RULE_STAR_KTP_MULTI_INQUIRY_V2("risk","star.ktp.multi.inquiry.v2.2.rule"),//星探-贷前证件号多头查询V2
    RULE_STAR_MOBILE_MULTI_INQUIRY_V3("risk","star.mobile.multi.inquiry.v3.2.rule"),//星探-贷前手机号多头查询V3
    RULE_STAR_KTP_MULTI_INQUIRY_V3("risk","star.ktp.multi.inquiry.v3.2.rule"),//星探-贷前证件号多头查询V3
    RULE_STAR_MOBILE_MULTI_INQUIRY_V4("risk","star.mobile.multi.inquiry.v4.2.rule"),//星探-贷前手机号多头查询V4
    RULE_STAR_KTP_MULTI_INQUIRY_V4("risk","star.ktp.multi.inquiry.v4.2.rule"),//星探-贷前证件号多头查询V4
    RULE_STAR_MULTI_INQUIRY_COMPLEX_V1("risk","star.multi.inquiry.complex.v1.2.rule"),//星探-多头查询COMPLEX V1
    RULE_STAR_BLACKLIST_RELATED_V3("risk","star.blacklist.related.v3.2.rule"),//星探-贷前关联黑名单v3
    RULE_STAR_IS_FACEBOOK("risk","star.is.facebook.2.rule"),//星探-facebook开通检测
    RULE_STAR_IS_WHATSAPP("risk","star.is.whatsapp.2.rule"),//星探-whatsapp开通检测

    SWITCH_STAR_COMPLEX_NET("risk","star.complex.net.1.switch"),//星探-贷前复杂网络
    SWITCH_STAR_VALIDATE_BANKCARD("risk","star.validate.bankcard.1.switch"),//星探-贷前银行卡校验
    SWITCH_STAR_MULTI_MOBILE("risk","star.multi.mobile.1.switch"),//星探-贷前一人多号
    SWITCH_STAR_PHONE_AGE("risk","star.phone.age.1.switch"),//星探-贷前号码在网时长
    SWITCH_STAR_PHONE_DEPOSIT("risk","star.phone.deposit.1.switch"),//星探-贷前号码充值行为
    SWITCH_STAR_PHONE_PREFERENCE("risk","star.phone.preference.1.switch"),//星探-贷前兴趣偏好
    SWITCH_STAR_PHONE_VERIFY("risk","star.phone.verify.1.switch"),//星探-贷前号码实名认证
    SWITCH_STAR_IDENTITY_CHECK("risk","star.identity.check.1.switch"),//星探-贷前身份信息认证
    SWITCH_STAR_BLACKLIST_CHECK("risk","star.blacklist.check.1.switch"),//星探-贷前黑名单信息认证
    SWITCH_STAR_MOBILE_MULTI_INQUIRY_V1("risk","star.mobile.multi.inquiry.v1.1.switch"),//星探-贷前手机号多头查询V1
    SWITCH_STAR_KTP_MULTI_INQUIRY_V1("risk","star.ktp.multi.inquiry.v1.1.switch"),//星探-贷前证件号多头查询V1
    SWITCH_STAR_MOBILE_MULTI_INQUIRY_V2("risk","star.mobile.multi.inquiry.v2.1.switch"),//星探-贷前手机号多头查询V2
    SWITCH_STAR_KTP_MULTI_INQUIRY_V2("risk","star.ktp.multi.inquiry.v2.1.switch"),//星探-贷前证件号多头查询V2
    SWITCH_STAR_MOBILE_MULTI_INQUIRY_V3("risk","star.mobile.multi.inquiry.v3.1.switch"),//星探-贷前手机号多头查询V3
    SWITCH_STAR_KTP_MULTI_INQUIRY_V3("risk","star.ktp.multi.inquiry.v3.1.switch"),//星探-贷前证件号多头查询V3
    SWITCH_STAR_MOBILE_MULTI_INQUIRY_V4("risk","star.mobile.multi.inquiry.v4.1.switch"),//星探-贷前手机号多头查询V4
    SWITCH_STAR_KTP_MULTI_INQUIRY_V4("risk","star.ktp.multi.inquiry.v4.1.switch"),//星探-贷前证件号多头查询V4
    SWITCH_STAR_MULTI_INQUIRY_COMPLEX_V1("risk","star.multi.inquiry.complex.v1.1.switch"),//星探-多头查询COMPLEX V1
    SWITCH_STAR_BLACKLIST_RELATED_V3("risk","star.blacklist.related.v3.1.switch"),//星探-贷前关联黑名单v3
    SWITCH_STAR_IS_FACEBOOK("risk","star.is.facebook.switch.2.rule"),//星探-facebook开通检测
    SWITCH_STAR_IS_WHATSAPP("risk","star.is.whatsapp.1.switch"),//星探-whatsapp开通检测

    SWITCH_STAR_APPLY_COUNT_24_HOURS("risk","star.apply.count.24.hours.1.switch"),//星探-24小时内申请数
    SWITCH_STAR_APPLY_COUNT_7_DAYS("risk","star.apply.count.7.days.1.switch"),//星探-7天内申请数
    SWITCH_STAR_APPLY_COUNT_14_DAYS("risk","star.apply.count.14.days.1.switch"),//星探-14天内申请数
    SWITCH_STAR_APPLY_COUNT_30_DAYS("risk","star.apply.count.30.days.1.switch"),//星探-30天内申请数
    SWITCH_STAR_GREYLIST_CHECK("risk","star.greylist.check.1.switch"),//星探-灰名单校验
    SWITCH_STAR_DEVICE_BLACKLIST_CHECK("risk","star.device.blacklist.check.1.switch"),//星探-设备黑名单校验
    SWITCH_STAR_MULTI_LOAN_AMOUNT("risk","star.multi.loan.amount.1.switch"),//星探-共债金额
    SWITCH_STAR_MULTI_LOAN_ORG_COUNT("risk","star.multi.loan.org.count.1.switch"),//星探-共债平台个数
    SWITCH_STAR_SHARE_MOBILE("risk","star.share.mobile.1.switch"),//星探-共用手机号
    SWITCH_STAR_SHARE_KTP("risk","star.share.ktp.1.switch"),//星探-共用身份证
    SWITCH_STAR_SHARE_BANKCARD("risk","star.share.bankcard.1.switch"),//星探-共用银行卡


    ORIENTE_RISK_SWITHC("risk", "oriente.risk.1.switch"),//是否开启oriente

    NOTIFY_MAIL("system", "notify_email"), // 预警邮件

    RISK_DIS_RATIO("risk", "dis.ratio"), // 风控分发比例




    RISK_FREE_ALL_IN_ONE("risk","free.all.in.one"),//机审全部都跑（非收费的）中间命中规则不拒绝
    RISK_COST_ALL_IN_ONE("risk","cost.all.in.one"),//机审全部都跑（收费的）中间命中规则不拒绝
    ;

    private final String namespace;
    private final String key;

    SettingConfig(String namespace, String key) {
        this.namespace = namespace;
        this.key = key;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public String getKey() {
        return this.key;
    }
}

