package com.cx.wz.setting.service;

import com.cx.wz.banner.dto.BannerDto;
import com.cx.wz.setting.SettingConfig;
import com.cx.wz.setting.entity.SettingEntity;
import com.cx.wz.setting.exception.SettingException;
import com.cx.wz.setting.repo.SettingMapper;
import com.cx.wz.uitls.RegxUtil;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SettingService {

    private final static Logger logger = LoggerFactory.getLogger(SettingService.class);
    private final static String[] EMPTY = new String[]{};

    @Autowired
    private SettingMapper settingMapper;

    private void checkParam(String namespace, String key){
        if (StringUtils.isEmpty(namespace)) {
            throw new SettingException("empty setting namespace");
        } else if (StringUtils.isEmpty(key)) {
            throw new SettingException("empty setting key");
        }
    }

    public SettingEntity getSettingsByKey(String namespace, String key) {
        checkParam(namespace , key);
        return settingMapper.findOneByKey(namespace, key);
    }

    public SettingEntity getSettingsByKeyNotCache(String namespace, String key) {
        checkParam(namespace , key);
        return settingMapper.findOneByKeyNotCache(namespace, key);
    }

    public String[] generArr(SettingEntity settingEntity) {
        if(null == settingEntity) return EMPTY;
        return settingEntity.getValue().split(",");
    }


    public SettingEntity getSettingsByConfig(SettingConfig settingConfig) {
        return getSettingsByKey(settingConfig.getNamespace(), settingConfig.getKey());
    }

    public SettingEntity getSettingsByConfigNotCache(SettingConfig settingConfig) {
        return getSettingsByKeyNotCache(settingConfig.getNamespace(), settingConfig.getKey());
    }

    public SettingEntity getTotalLoanAmount() {
        return getSettingsByConfig(SettingConfig.APPLICATION_TOTAL_LOAN_AMOUNT);
    }

    public SettingEntity getNBFCName() {
        return getSettingsByConfig(SettingConfig.APPLICATION_CONTACT_NBFC_NAME);
    }


    public SettingEntity getDailyLoanAmount() {
        return getSettingsByConfig(SettingConfig.APPLICATION_DAILY_LOAN_AMOUNT);
    }

    public SettingEntity getAndroidClientAppVersion() {
        return getSettingsByConfig(SettingConfig.APPLICATION_ANDROID_VERSION);
    }

    public SettingEntity getAndroidClientAppVersionName() {
        return getSettingsByConfig(SettingConfig.APPLICATION_ANDROID_VERSION_NAME);
    }

    public SettingEntity getAndroidClientAppVersionChangeLogs() {
        return getSettingsByConfig(SettingConfig.APPLICATION_ANDROID_VERSION_CHANGELOGS);
    }

    public SettingEntity getAndroidClientAppVersionUpdateUrl() {
        return getSettingsByConfig(SettingConfig.APPLICATION_UPDATE_URL);
    }

    public SettingEntity getOfficialWebsite() {
        return getSettingsByConfig(SettingConfig.SYSTEM_OFFICIAL_WEBSITE);
    }

    @Transactional
    public boolean updateSettingValueById(long id,String value){
        settingMapper.updateSettingValueById(id,value);
        return true;
    }

    public int updateSettingValueAndStatusById(long id, String value, SettingEntity.Status status) {
        return settingMapper.updateSettingValueAndStatusById(id, value, status);
    }

    public int updateSettingReapplyValueByKey(String key,String value) {
        return settingMapper.updateSettingReapplyValueByKey(key, value);
    }

    public int updateSingleSettingReapplyValue(String key) {
        return settingMapper.updateSingleSettingReapplyValue(key);
    }

    public int updateSettingVisibity(String key,Boolean visibility) {
        return settingMapper.updateSettingVisibity(key,visibility);
    }
    public int updateSettingStatusById(String key,String namespace, SettingEntity.Status Status) {
        return settingMapper.updateSettingStatusById(key,namespace,Status );
    }

    @Transactional
    public boolean updateProductProperties(List<Pair<String, String>> propertyList){
        for(int i = 0; i < propertyList.size(); i++){

            Pair<String, String> t = propertyList.get(i);

            if( !StringUtils.isEmpty(t.getKey()) && !StringUtils.isEmpty(t.getValue()) ) {
                long valueId = Long.parseLong(t.getKey());
                settingMapper.updateSettingValueById(valueId, t.getValue());
            }
        }
        return true;
    }

    public SettingEntity findById(long id){
        return settingMapper.findOne(id);
    }

    public List<SettingEntity> findApiMarketSettingList(){
        return settingMapper.findApiMarketSettingList();
    }

    @Transactional
    public boolean updateCouponSettings(String dischargeInterestDaysId,
                                        String dischargeInterestDaysValue,
                                        String dischargeInviteeInterestDaysId,
                                        String dischargeInviteeInterestDaysValue){
        if( !StringUtils.isEmpty(dischargeInterestDaysId) && !StringUtils.isEmpty(dischargeInterestDaysValue) ) {
            long valueId = Long.parseLong(dischargeInterestDaysId);
            settingMapper.updateSettingValueById(valueId, dischargeInterestDaysValue);
        }

        if( !StringUtils.isEmpty(dischargeInviteeInterestDaysId) && !StringUtils.isEmpty(dischargeInviteeInterestDaysValue) ) {
            long valueId = Long.parseLong(dischargeInviteeInterestDaysId);
            settingMapper.updateSettingValueById(valueId, dischargeInviteeInterestDaysValue);
        }

        return true;
    }

    @Cacheable(cacheNames = "setting")
    public List<SettingEntity> findApplicationSettingList(){
        return settingMapper.findApplicationSettingList();
    }

    public boolean getFaceIDEnable(){
        SettingEntity entity =settingMapper.findOneByKey(SettingConfig.FACEID_ENABLE.getNamespace(), SettingConfig.FACEID_ENABLE.getKey());

        boolean ret = true;
        try{
            ret = Boolean.parseBoolean(entity.getValue());
        }catch (Exception e){
            logger.info("getFaceIDEnable faceid.enable not set {}", e.getMessage());
        }

        return ret;
    }

    public boolean getDDPmmlFlag(){
        SettingEntity entity =settingMapper.findOneByKey(SettingConfig.APPLICATION_DD_PMML_FLAG.getNamespace(), SettingConfig.APPLICATION_DD_PMML_FLAG.getKey());
        boolean ret = false;
        try{
            ret = Boolean.parseBoolean(entity.getValue());
        }catch (Exception e){
            logger.info("getDDPmmlFlag  not set {}", e.getMessage());
        }

        return ret;
    }


    public String getSkylineSendId(String type){
        SettingEntity entity = null;
        if(StringUtils.isEmpty(type)){
             entity =
                    settingMapper.findOneByKey(SettingConfig.SYSTEM_SKYLINE_SENDERID.getNamespace()
                            , SettingConfig.SYSTEM_SKYLINE_SENDERID.getKey());

        }else {
            entity =
                    settingMapper.findOneByKey(SettingConfig.SYSTEM_SKYLINE_SENDERID_OTP.getNamespace()
                            , SettingConfig.SYSTEM_SKYLINE_SENDERID_OTP.getKey());
        }
        String value = StringUtils.EMPTY;
        if(entity != null){
            value = entity.getValue();
        }
        return value;
    }

    public double getHarvestLocalInfoExpiredHours() {
        SettingEntity settingEntity = getSettingsByConfig(SettingConfig.FACEID_ADVANCE_COMPARE_PASS);
        double simlity = 70.0;
        try{
            simlity = Double.parseDouble(settingEntity.getValue());
        }catch (Exception e){
            logger.info("getAdvanceFaceCompare error  {}", settingEntity.getValue());
        }
        return simlity;
    }

    public double getApiMarketFailedRetryHours() {
        SettingEntity settingEntity = getSettingsByConfig(SettingConfig.SYSTEM_API_MARKET_FAILED_RETRY_HOUR);
        Double simlity = 0.3;
        try{
            simlity = Double.parseDouble(settingEntity.getValue());
        }catch (Exception e){
            logger.info("getApiMarketFailedRetryHours error  {}", settingEntity.getValue());
        }
        return simlity;
    }

    public Double getChenScoreAutoIssue() {
        SettingEntity settingEntity = getSettingsByConfig(SettingConfig.LOAN_APP_CHEN_SCORE_AUTO_ISSUE);
        Double chenscore = null;
        try{
            chenscore = Double.parseDouble(settingEntity.getValue());
        }catch (Exception e){
            logger.info("getChenScoreAutoIssue error  {}", settingEntity.getValue());
        }
        return chenscore;
    }


// 外部风控
    public Double getExternalfraudScore(SettingConfig riskSetting) {
        SettingEntity settingEntity = getSettingsByConfig(riskSetting);
        Double chenscore = null;
        try{
            chenscore = Double.parseDouble(settingEntity.getValue());
        }catch (Exception e){
            logger.info("getExternalfraudScore error  {}", settingEntity.getValue());
        }
        return chenscore;
    }

    public double getRiskReportFailedRetryHours() {
        SettingEntity settingEntity = getSettingsByConfig(SettingConfig.SYSTEM_RISK_REPORT_FAILED_RETRY_HOUR);
        Double simlity = 0.0;
        try{
            simlity = Double.parseDouble(settingEntity.getValue());
        }catch (Exception e){
            logger.info("SYSTEM_RISK_REPORT_FAILED_RETRY_HOUR error  {}", settingEntity.getValue());
        }
        return simlity;
    }

    public double getAdvanceFaceCompare() {
        SettingEntity settingEntity = getSettingsByConfig(SettingConfig.FACEID_ADVANCE_COMPARE_PASS);
        double simlity = 70.0;
        try{
            simlity = Double.parseDouble(settingEntity.getValue());
        }catch (Exception e){
            logger.info("getAdvanceFaceCompare error  {}", settingEntity.getValue());
        }
        return simlity;
    }


    public boolean getFaceIDIgnore(){
        SettingEntity entity =settingMapper.findOneByKey(SettingConfig.FACEID_IGNORE.getNamespace(), SettingConfig.FACEID_IGNORE.getKey());

        boolean ret = false;
        try{
            ret = Boolean.parseBoolean(entity.getValue());
        }catch (Exception e){
            logger.info("getFaceIDIgnore faceid.ignore not set {}", e.getMessage());
        }

        return ret;
    }

    public boolean getAppPushFlag() {
        SettingEntity entity =settingMapper.findOneByKey(SettingConfig.APP_PUSH_FLAG.getNamespace(), SettingConfig.APP_PUSH_FLAG.getKey());

        boolean ret = false;
        try{
            ret = Boolean.parseBoolean(entity.getValue());
        }catch (Exception e){
            logger.info("getAppPushFlag  app.push.flag not set {}", e.getMessage());
        }

        return ret;
    }

    public boolean getCustomerInfoChangeFlash(){
        SettingEntity settingEntity = getSettingsByConfig(SettingConfig.CUSTOMER_INFO_CHANGE_FLASH);
        boolean ret = true;
        try {
            ret = Boolean.parseBoolean(settingEntity.getValue());
        } catch (Exception e) {
            logger.info("getCustomerInfoChangeFlash error {}", settingEntity.getValue());
        }
        return ret;
    }

    public boolean getLoanMarketFaceIDIgnore(){
        SettingEntity entity =settingMapper.findOneByKey(SettingConfig.FACEID_LOAN_MARKET_IGNORE.getNamespace(), SettingConfig.FACEID_LOAN_MARKET_IGNORE.getKey());

        boolean ret = false;
        try{
            ret = Boolean.parseBoolean(entity.getValue());
        }catch (Exception e){
            logger.info("getLoanmarket FaceIDIgnore loan.market.ignore not set {}", e.getMessage());
        }

        return ret;
    }

    public boolean checkMultiLogin(){
        SettingEntity entity =settingMapper.findOneByKey(SettingConfig.COMPANY_MULTI_LOGIN_CHECK.getNamespace(), SettingConfig.COMPANY_MULTI_LOGIN_CHECK.getKey());

        boolean ret = false;
        try{
            ret = Boolean.parseBoolean(entity.getValue());
        }catch (Exception e){
            logger.info("multi.login.check not set {}", e.getMessage());
        }

        return ret;
    }


    public int getFaceIDLimit(){
        SettingEntity entity =settingMapper.findOneByKey(SettingConfig.FACEID_TRY_LIMIT.getNamespace(), SettingConfig.FACEID_TRY_LIMIT.getKey());

        int ret = 5;
        try {
            ret = Integer.parseInt(entity.getValue());
        } catch (Exception e){
            logger.error("getFaceIDEnable {}", e);
        }
        return ret;
    }

    public int getPasswdExpiredDay(){
        SettingEntity entity =settingMapper.findOneByKey(SettingConfig.APPLICATION_PASSWD_EXPIRED_DAYS.getNamespace(), SettingConfig.APPLICATION_PASSWD_EXPIRED_DAYS.getKey());

        int ret = 30;
        try {
            ret = Integer.parseInt(entity.getValue());
        } catch (Exception e){
            logger.error("getPasswdExpiredDay {}", e);
        }
        return ret;
    }

    public double getMengxiaFaceSimility(){
        SettingEntity entity =settingMapper.findOneByKey(SettingConfig.FACEID_MENGXIA_COMPARE_PASS.getNamespace(), SettingConfig.FACEID_MENGXIA_COMPARE_PASS.getKey());

        double ret = 70.0;
        try {
            ret = Double.parseDouble(entity.getValue());
        } catch (Exception e){
            logger.error("getMengxiaFaceSimility {}", e);
        }
        return ret;
    }

    public int getBuildApkLimit() {
        SettingEntity entity = settingMapper.findOneByKey(SettingConfig.BUILD_APK_LIMIT.getNamespace(), SettingConfig.BUILD_APK_LIMIT.getKey());
        int ret = 0;
        try {
            ret = Integer.parseInt(entity.getValue().trim());
        } catch (Exception e){
            logger.error("getBuildApkLimit {}", e);
        }
        return ret;
    }

    public String getCompanyCropId() {
        SettingEntity entity = settingMapper.findOneByKey(SettingConfig.COMPANY_CROP_ID.getNamespace(), SettingConfig.COMPANY_CROP_ID.getKey());
        if (null == entity || StringUtils.isEmpty(entity.getValue())) {
            logger.error("company_wx_corp_id config error!");
            return null;
        } else {
            return entity.getValue().trim();
        }
    }

    public int getThirdpartyDataValidDays() {
        int validDays = 30;
        try {
            SettingEntity settingEntity = getSettingsByConfig(SettingConfig.THIRDPARTY_DATA_VALID_DAYS);
            validDays = Integer.valueOf(settingEntity.getValue());
        } catch (Exception e) {
            logger.error("{thirdparty_data, VALID_DAYS} config error!");
        }
        return validDays;
    }

    public double getHarvestExpireHours() {
        double limitHour = 24;
        try {
            SettingEntity settingEntity = getSettingsByKey(SettingConfig.SYSTEM_HARVEST_EXPIRED_HOURS.getNamespace(),SettingConfig.SYSTEM_HARVEST_EXPIRED_HOURS.getKey());
            limitHour = Double.parseDouble(settingEntity.getValue());
        } catch (Exception e){
            logger.error("隐私数据上报过期时间 {} ",limitHour);
        }
        return limitHour;
    }

    public Double getHarvestLocalExpireHours() {
        Double limitHour = null;
        try {
            SettingEntity settingEntity = getSettingsByKey(SettingConfig.SYSTEM_HARVEST_LOCAL_INFO_EXPIRED_HOURS.getNamespace(),SettingConfig.SYSTEM_HARVEST_LOCAL_INFO_EXPIRED_HOURS.getKey());
            if(StringUtils.isEmpty(settingEntity.getValue())) {
                return null;
            }
            limitHour = Double.parseDouble(settingEntity.getValue());
        } catch (Exception e){
            logger.error("本地隐私数据有效时间 {} ",limitHour);
        }
        return limitHour;
    }


    public double getHarvestCenterExpireHours() {
        double limitHour = 48;
        try {
            SettingEntity settingEntity = getSettingsByKey(SettingConfig.SYSTEM_HARVEST_CENTER_INFO_EXPIRED_HOURS.getNamespace(),SettingConfig.SYSTEM_HARVEST_CENTER_INFO_EXPIRED_HOURS.getKey());
            limitHour = Double.parseDouble(settingEntity.getValue());
        } catch (Exception e){
            logger.error("隐私中心数据有效时间 {} ",limitHour);
        }
        return limitHour;
    }

    public String getHarvestExpireAction() {
        String action = "";
        try {
            SettingEntity settingEntity =getSettingsByKey(SettingConfig.SYSTEM_HARVEST_EXPIRED_ACTION.getNamespace(),SettingConfig.SYSTEM_HARVEST_EXPIRED_ACTION.getKey());
            action = settingEntity.getValue();
        } catch (Exception e){
            logger.error("隐私数据上报过期执行动作异常 {} ");
        }
        return action;
    }

    public List<String> getSettingNamespaceList() {
        return settingMapper.getSettingNamespaceList();
    }

    public List<BannerDto> getMainBanners() {
        List<BannerDto> result = new ArrayList<>();

        Map<String, String> bannerSettingMap = settingMapper.findMainBannerSetting()
                .stream()
                .collect(Collectors.toMap(SettingEntity::getKey, SettingEntity::getValue));

        for (int i = 1; i <= bannerSettingMap.size() / 2; i++) {
            String url = bannerSettingMap.get("main_banner_url_" + i);
            if (RegxUtil.isWebURL(url)) {
                BannerDto bannerDto = new BannerDto();
                bannerDto.setUrl(url);
                bannerDto.setDetailUrl(bannerSettingMap.get("main_banner_detail_url_" + i));
                result.add(bannerDto);
            }
        }
        return result;
    }

    public List<SettingEntity> findByNamespace(String namespace) {
        return settingMapper.findByNamespace(namespace);
    }

    public List<String> findActiveRule() {
        return settingMapper.findActiveRule();
    }

    public Integer getDelayRejectHours(){
        SettingEntity settingEntity=settingMapper.findOneByKey("loan_application","delay.display.reject");
        if(settingEntity == null){
            return null;
        }else{
            return Integer.parseInt(settingEntity.getValue());
        }
    }

    public int updateValueIfNotEmpty(String namespace, String key, String value) {
        if (!StringUtils.isEmpty(value)) {
            return settingMapper.updateValueByNSAndKey(namespace, key, value);
        }
        return -1;
    }

    public int updateValueByNSAndKey(String namespace, String key, String value) {
        return settingMapper.updateValueByNSAndKey(namespace, key, value);
    }

    public double getAnCashCompare() {
        SettingEntity settingEntity = getSettingsByConfig(SettingConfig.ANCASH_COMPARE_PASS);
        double score = 70.0;
        try {
            score = Double.parseDouble(settingEntity.getValue());
        } catch (Exception e) {
            logger.info("getAdvanceFaceCompare error  {}", settingEntity.getValue());
        }
        return score;
    }
    public String getJiuWeiCompanyCropId() {
        SettingEntity entity = settingMapper.findOneByKey(SettingConfig.JIUWEI_NAMESPACE.getNamespace(), SettingConfig.JIUWEI_NAMESPACE.getKey());
        if (null == entity || StringUtils.isEmpty(entity.getValue())) {
            logger.error("jiuwei_namespace config error!");
            return null;
        } else {
            return entity.getValue().trim();
        }
    }

    public String getJiuWeiCallbackUrl() {
        SettingEntity entity = settingMapper.findOneByKey(SettingConfig.JIUWEI_CHANNEL_CALLBACK_URL.getNamespace(), SettingConfig.JIUWEI_CHANNEL_CALLBACK_URL.getKey());
        if (null == entity || StringUtils.isEmpty(entity.getValue())) {
            logger.error("jiuwei_channel_callback_url config error!");
            return null;
        } else {
            return entity.getValue().trim();
        }
    }

    public String myEmailAccount() {
        SettingEntity entity = settingMapper.findOneByKeyNotCache(SettingConfig.alarm_email_send_account.getNamespace(), SettingConfig.alarm_email_send_account.getKey());
        if (null == entity || StringUtils.isEmpty(entity.getValue())) {
            logger.error("alarm_email_send_account config error!");
            return "";
        } else {
            return entity.getValue().trim();
        }
    }

    public String myEmailPassword() {
        SettingEntity entity = settingMapper.findOneByKeyNotCache(SettingConfig.alarm_email_send_password.getNamespace(), SettingConfig.alarm_email_send_password.getKey());
        if (null == entity || StringUtils.isEmpty(entity.getValue())) {
            logger.error("alarm_email_send_password config error!");
            return "";
        } else {
            return entity.getValue().trim();
        }
    }
    public String SMTPHost() {
        SettingEntity entity = settingMapper.findOneByKeyNotCache(SettingConfig.alarm_email_send_SMTPHost.getNamespace(), SettingConfig.alarm_email_send_SMTPHost.getKey());
        if (null == entity || StringUtils.isEmpty(entity.getValue())) {
            logger.error("alarm_email_send_SMTPHost config error!");
            return "smtp.qq.com";
        } else {
            return entity.getValue().trim();
        }
    }
    public String SMTPTransport() {
        SettingEntity entity = settingMapper.findOneByKeyNotCache(SettingConfig.ALARM_EMAIL_TRANSPORT_PROTOCOL.getNamespace(), SettingConfig.ALARM_EMAIL_TRANSPORT_PROTOCOL.getKey());
        if (null == entity || StringUtils.isEmpty(entity.getValue())) {
            logger.error("ALARM_EMAIL_TRANSPORT_PROTOCOL config error!");
            return "smtp";
        } else {
            return entity.getValue().trim();
        }
    }

    public String SMTPPort() {
        SettingEntity entity = settingMapper.findOneByKeyNotCache(SettingConfig.ALARM_EMAIL_SEND_SMTPPORT.getNamespace(), SettingConfig.ALARM_EMAIL_SEND_SMTPPORT.getKey());
        if (null == entity || StringUtils.isEmpty(entity.getValue())) {
            logger.error("ALARM_EMAIL_SEND_SMTPPORT config error!");
            return "25";
        } else {
            return entity.getValue().trim();
        }
    }
    public String receiveResSmsSucceedBottomRateMailAccounts() {
        SettingEntity entity = settingMapper.findOneByKeyNotCache(SettingConfig.alarm_email_send_receive_res_sms_succeed_bottom_rate_accounts.getNamespace(), SettingConfig.alarm_email_send_receive_res_sms_succeed_bottom_rate_accounts.getKey());
        if (null == entity || StringUtils.isEmpty(entity.getValue())) {
            logger.error("alarm_email_send_receive_res_sms_succeed_bottom_rate_accounts config error!");
            return "";
        } else {
            return entity.getValue().trim();
        }
    }

    public String receivePreViewMailAccounts() {
        SettingEntity entity = settingMapper.findOneByKeyNotCache(SettingConfig.alarm_email_send_receive_pre_review_accounts.getNamespace(), SettingConfig.alarm_email_send_receive_pre_review_accounts.getKey());
        if (null == entity || StringUtils.isEmpty(entity.getValue())) {
            logger.error("alarm_email_send_receive_pre_review_accounts config error!");
            return "";
        } else {
            return entity.getValue().trim();
        }
    }
    public String receiveDefaultMailAccounts() {
        SettingEntity entity = settingMapper.findOneByKeyNotCache(SettingConfig.alarm_email_send_receive_default_accounts.getNamespace(), SettingConfig.alarm_email_send_receive_default_accounts.getKey());
        if (null == entity || StringUtils.isEmpty(entity.getValue())) {
            logger.error("alarm_email_send_receive_default_accounts config error!");
            return "";
        } else {
            return entity.getValue().trim();
        }
    }
    public String alarmUpAccounts() {
        SettingEntity entity = settingMapper.findOneByKeyNotCache(SettingConfig.alarm_email_send_receive_alarm_up_accounts.getNamespace(), SettingConfig.alarm_email_send_receive_alarm_up_accounts.getKey());
        if (null == entity || StringUtils.isEmpty(entity.getValue())) {
            logger.error("alarm_email_send_receive_alarm_up_accounts config error!");
            return "";
        } else {
            return entity.getValue().trim();
        }
    }

    public int getcheckIssueFailedUpValue() {
        SettingEntity settingEntity = settingMapper.findOneByKeyNotCache(SettingConfig.alarm_check_issue_failed_up.getNamespace(), SettingConfig.alarm_check_issue_failed_up.getKey());
        int warningValue = 50;
        try{
            if(null!=settingEntity){
                warningValue = Integer.valueOf(settingEntity.getValue());
            }
        }catch (Exception e){
            logger.info("getcheckIssueFailedUpValue error  {}", settingEntity.getValue());
        }
        return warningValue;
    }
    public double getcheckRegSMSSucceedRateValue() {
        SettingEntity settingEntity = settingMapper.findOneByKeyNotCache(SettingConfig.alarm_check_sms_succeed_rate.getNamespace(), SettingConfig.alarm_check_sms_succeed_rate.getKey());
        double warningValue = 0.4;
        try{
            if(null!=settingEntity){
                warningValue = Double.parseDouble(settingEntity.getValue());
            }
        }catch (Exception e){
            logger.info("getcheckRegSMSSucceedRateValue error  {}", settingEntity.getValue());
        }
        return warningValue;
    }

    public Map<String,String> getReturnTime() {
        Map<String,String> map = new LinkedHashMap<>();
        SettingEntity entity = settingMapper.findOneByKeyNotCache(SettingConfig.APPLICATION_LOAN_RETURN_TIME_SUPPORT.getNamespace(), SettingConfig.APPLICATION_LOAN_RETURN_TIME_SUPPORT.getKey());
        if (null == entity || StringUtils.isEmpty(entity.getValue())) {
                map.put("AnyTime","AnyTime");

        } else {
            String[] rules = entity.getValue().split(",");
            if (rules.length > 0){
                Arrays.asList(rules).stream().forEach(t -> {
                    map.put(t,t);
                });
            }
        }
        return map;
    }

    /**
     * Utility function used to transform string from lower underscore format to lower camel.
     *
     * @param key the String to transform..
     * @return the transformed string.
     */
    private String transformKeyToCamel(String key) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key);
    }

    /**
     * Utility function used to transform string from lower camel format to lower underscore.
     *
     * @param key the String to transform..
     * @return the transformed string.
     */
    private String transformKeyToUnderscore(String key) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, key);
    }

    /**
     * Utility function used to check if the key contains 'ImageUrl'.
     *
     * @param key The key to check.
     * @return The boolean value.
     */
    private boolean checkImageUrl(String key) {
        return StringUtils.contains(key, "ImageUrl");
    }
}
