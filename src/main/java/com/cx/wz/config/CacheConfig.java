package com.cx.wz.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
    public static final String CAPTCHA_CACHE = "captcha";
    public static final String SMS_CODE_CACHE = "smscode";
    public static final String SMS_LIMIT_CACHE = "smslimit";
    public static final String ACCOUNTKIT_SMS_LIMIT_CACHE = "accountkitsmslimit";
    public static final String TOKEN_CACHE = "token";
    public static final String REFRESH_TOKEN_CACHE = "refreshtoken";
    public static final String CUSTOMER_CONTEXT_CACHE = "customercontext";
    public static final String ADMIN_CONTEXT_CACHE = "admincontextcache";
    public static final String REGION_CACHE = "regioncache";
    public static final String FACE_VERIFY_COUNT = "faceverifycount";
    public static final String DEVICE_SOUERCE_COUNT = "devicesourcecount";
    public static final String CREATE_LOAN_COUNT = "createLoancount";
    public static final String OCR_COUNT = "ocrcount";

    public static final String CUSTOMER_KEY = "customer";
    public static final String PRODUCT_KEY = "product";
    public static final String DEFAULT_PRODUCT = "defaultProduct";
    public static final String PRODUCT_GRADE_KEY = "productgrade";

    public static final String GOOGLE_TEST_PRODUCT = "googleTestProduct";

    public static final String PRIVACY_KEY = "privacy";
    public static final String SETTING_KEY = "setting";

    public static final String REST_SETTING_KEY = "restsetting";

    public static final String PACKAGE_PRODUCT_KEY = "packageproduct";

    public static final String LOAN_MARKET_KEY = "loanmarket";

    public static final String ADMIN_TOKEN_CACHE = "admintoken";
    public static final String CUSTOMER_TOKEN_CACHE = "customertoken";

    public static final String APP_VERSION_CACHE = "appversion";
    public static final String APP_PACKAGENAME_CACHE = "apppackagename";
    public static final String APP_SOURCE_CACHE = "appsource";
    public static final String ADSPLAY_COUNT_CACHE = "adsplayCount";
    public static final String UUANG_COUNT_CACHE = "uuangCount";


    public static final String LOAN_SOURCE_CACHE = "loansource";
    public static final String CUSTOMER_FLAG_CACHE = "customerflag";
    public static final String REJECT_REASON_CACHE = "rejectreason";
    public static final String KMI_TOKEN_CACHE = "kmitoken";
    public static final String ODEOPAY_TOKEN_CACHE = "odeopaytoken";
    public static final String GREYPAY_TOKEN_CACHE = "greypaytoken";
    public static final String PAYU_TOKEN_CACHE = "payutoken";
    public static final String CASHFREE_TOKEN_CACHE = "cashfreetoken";

    public static final String COLLECTION_STAGE_CACHE = "collectionstage";
    public static final String SYS_DICT_CACHE = "sysdict";
    public static final String SYS_CONFIG = "sysconfig";
    public static final String LOAN_APP_ID_CONTRACT_NO = "loanAppIdContractNo";
    public static final String OFFLINE_CHANNEL_NAME = "offlineChannelName";
    public static final String SKYPAY_ROUTE_CONF = "skypayRouteConf";
    public static final String ALL_COLLECTORS_CACHE= "allcollectors";
    public static final String EMG_CONTACT="emgcontact"; //t_contact表top 100000缓存
    public static final String SKYPAY_BANK_ADDRESS="skypayBankAddress";

    public static final String APIMARKETSOURCE="apimarketsource";
    public static final String APIPOSEIDONSOURCE="apiposeidonsource";

    public static final String RISKFACE="riskface";

    public static final String HARVEST_INFO_CHECK_LIMIT = "harvestinfochecklimit";
    public static final String MARKET_FEE_CHECK_LIMIT = "marketFeechecklimit";
    public static final String ADMIN_CENTER_DOMAIN = "admincenterdomain";

    public static final String RISK_BLACK_LIST = "riskblacklist";

    public static final String API_MARKET_FEE_CONFIG = "apimarketfeeconfig";
    public static final String API_POSEIDON_BALANCE = "poseidonBalance";


    public static final String VN_BRANCH_BANK_CACHE = "vnbranchbank";

    public static final String ADMIN_PRIVILEGE_CACHE = "privliegeCache";

    public static final String THIRDPARTY_DICTIONARY = "thirdpartydictionary";

    public static final String BLACKIP_CACHE = "blackip";
    public static final String IP_COUNTRY_CACHE = "ipcountry";

    public static final String APP_SOURCE_LOG_CACHE = "appsourcelog";

    public static final String COLLECTOR_EFFIENCT_WARRAPER = "collector_effienct_warraper";

    public static final String REQUEST_FREQUENCY = "REQUEST_FREQUENCY";

    public static final String REQUEST_BLACK_LIST = "REQUEST_BLACK_LIST";

    public static final String REQUEST_IP_LIMIT = "REQUEST_IP_LIMIT";
    public static final String REPORT_CACHE = "REPORT_CACHE";
    public static final String FISH_TOKEN_CACHE = "FISH_TOKEN_CACHE";







    @Override
    @Bean
    public CacheManager cacheManager() {
        return CaffeineCacheManager();
    }

    private SimpleCacheManager CaffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        CaffeineCache captchaCache = new CaffeineCache(CAPTCHA_CACHE, Caffeine.newBuilder()
                .maximumSize(100000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build());
        CaffeineCache smsCache = new CaffeineCache(SMS_CODE_CACHE, Caffeine.newBuilder()
                .maximumSize(100000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build());
        CaffeineCache accountkitSmsCache = new CaffeineCache(ACCOUNTKIT_SMS_LIMIT_CACHE, Caffeine.newBuilder()
                .maximumSize(100000)
                .expireAfterWrite(360, TimeUnit.MINUTES)
                .build());
        CaffeineCache smsLimitCache = new CaffeineCache(SMS_LIMIT_CACHE, Caffeine.newBuilder()
                .maximumSize(100000)
                .expireAfterWrite(24, TimeUnit.HOURS)
                .build());
        CaffeineCache tokenCache = new CaffeineCache(TOKEN_CACHE, Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(2, TimeUnit.HOURS)
                .build());
        CaffeineCache refreshTokenCache = new CaffeineCache(REFRESH_TOKEN_CACHE, Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(7, TimeUnit.DAYS)
                .build());
        CaffeineCache customerContextCache = new CaffeineCache(CUSTOMER_CONTEXT_CACHE, Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(2, TimeUnit.HOURS)
                .expireAfterAccess(2, TimeUnit.HOURS)
                .build());
        CaffeineCache adminContextCache = new CaffeineCache(ADMIN_CONTEXT_CACHE, Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(24, TimeUnit.HOURS)
                .expireAfterAccess(24, TimeUnit.HOURS)
                .build());
        CaffeineCache regionCache = new CaffeineCache(REGION_CACHE, Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(7, TimeUnit.DAYS)
                .expireAfterAccess(7, TimeUnit.DAYS)
                .build());

        CaffeineCache faceVerifyCountCache = new CaffeineCache(FACE_VERIFY_COUNT, Caffeine.newBuilder()
                .maximumSize(100000)
                .expireAfterWrite(2, TimeUnit.HOURS)
                .expireAfterAccess(2, TimeUnit.HOURS)
                .build());
        CaffeineCache devicesourcecount = new CaffeineCache(DEVICE_SOUERCE_COUNT, Caffeine.newBuilder()
                .maximumSize(100000)
                .expireAfterWrite(1, TimeUnit.HOURS)
                .expireAfterAccess(1, TimeUnit.HOURS)
                .build());
        CaffeineCache createLoancount = new CaffeineCache(CREATE_LOAN_COUNT, Caffeine.newBuilder()
                .maximumSize(100000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .expireAfterAccess(5, TimeUnit.MINUTES)
                .build());
        CaffeineCache ocrCountCache = new CaffeineCache(OCR_COUNT, Caffeine.newBuilder()
                .maximumSize(100000)
                .expireAfterWrite(1, TimeUnit.DAYS)
                .build());

        CaffeineCache customerCache = new CaffeineCache(CUSTOMER_KEY, Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .expireAfterAccess(1, TimeUnit.MINUTES)
                .build());

        CaffeineCache productCache = new CaffeineCache(PRODUCT_KEY, Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .expireAfterAccess(1, TimeUnit.MINUTES)
                .build());
        CaffeineCache defaultProductCache = new CaffeineCache(DEFAULT_PRODUCT, Caffeine.newBuilder()
                .maximumSize(10)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .expireAfterAccess(1, TimeUnit.MINUTES)
                .build());
        CaffeineCache googleTestProductCache = new CaffeineCache(GOOGLE_TEST_PRODUCT, Caffeine.newBuilder()
                .maximumSize(10)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .expireAfterAccess(1, TimeUnit.MINUTES)
                .build());

        CaffeineCache productgradeCache = new CaffeineCache(PRODUCT_GRADE_KEY, Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .expireAfterAccess(1, TimeUnit.MINUTES)
                .build());
        CaffeineCache privacyCache = new CaffeineCache(PRIVACY_KEY, Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(5, TimeUnit.HOURS)
                .expireAfterAccess(5, TimeUnit.HOURS)
                .build());
        CaffeineCache setting = new CaffeineCache(SETTING_KEY, Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .expireAfterAccess(1, TimeUnit.MINUTES)
                .build());
        CaffeineCache restsetting = new CaffeineCache(REST_SETTING_KEY, Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .expireAfterAccess(1, TimeUnit.MINUTES)
                .build());
        CaffeineCache packageproduct = new CaffeineCache(PACKAGE_PRODUCT_KEY, Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build());
        CaffeineCache loanmarket = new CaffeineCache(LOAN_MARKET_KEY, Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build());
        CaffeineCache admintoken = new CaffeineCache(ADMIN_TOKEN_CACHE, Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .build());
        CaffeineCache customertoken = new CaffeineCache(CUSTOMER_TOKEN_CACHE, Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .build());
        CaffeineCache appversion = new CaffeineCache(APP_VERSION_CACHE, Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build());
        CaffeineCache loansource = new CaffeineCache(LOAN_SOURCE_CACHE, Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build());
        CaffeineCache apppackagename = new CaffeineCache(APP_PACKAGENAME_CACHE, Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build());
        CaffeineCache appsource = new CaffeineCache(APP_SOURCE_CACHE, Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build());

        CaffeineCache appsourcelog = new CaffeineCache(APP_SOURCE_LOG_CACHE, Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build());
        CaffeineCache adsplayCount = new CaffeineCache(ADSPLAY_COUNT_CACHE, Caffeine.newBuilder()
                .maximumSize(10)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build());
        CaffeineCache uuangCount = new CaffeineCache(UUANG_COUNT_CACHE, Caffeine.newBuilder()
                .maximumSize(10)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build());
        CaffeineCache customerFlag = new CaffeineCache(CUSTOMER_FLAG_CACHE, Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build());
        CaffeineCache rejectreason = new CaffeineCache(REJECT_REASON_CACHE, Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build());
        CaffeineCache kmitoken = new CaffeineCache(KMI_TOKEN_CACHE, Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(2, TimeUnit.HOURS)
                .expireAfterAccess(2, TimeUnit.HOURS)
                .build());
        CaffeineCache odeopaytoken = new CaffeineCache(ODEOPAY_TOKEN_CACHE, Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .build());
        CaffeineCache cashfreetoken = new CaffeineCache(CASHFREE_TOKEN_CACHE, Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .expireAfterAccess(5, TimeUnit.MINUTES)
                .build());
        CaffeineCache greypaytoken = new CaffeineCache(GREYPAY_TOKEN_CACHE, Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(7, TimeUnit.DAYS)
                .expireAfterAccess(7, TimeUnit.DAYS)
                .build());
        CaffeineCache payutoken = new CaffeineCache(PAYU_TOKEN_CACHE, Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(2, TimeUnit.HOURS)
                .expireAfterAccess(2, TimeUnit.HOURS)
                .build());
        CaffeineCache collectionstage = new CaffeineCache(COLLECTION_STAGE_CACHE, Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .expireAfterAccess(5, TimeUnit.MINUTES)
                .build());
        CaffeineCache sysdict = new CaffeineCache(SYS_DICT_CACHE, Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build());
        CaffeineCache sysconfig = new CaffeineCache(SYS_CONFIG, Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .expireAfterAccess(5, TimeUnit.MINUTES)
                .build());
        CaffeineCache loanAppIdContractNo = new CaffeineCache(LOAN_APP_ID_CONTRACT_NO, Caffeine.newBuilder()
                .maximumSize(100000)
                .expireAfterWrite(24, TimeUnit.HOURS)
                .build());
        CaffeineCache offlineChannelName = new CaffeineCache(OFFLINE_CHANNEL_NAME, Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build());
        CaffeineCache skypayRouteConf = new CaffeineCache(SKYPAY_ROUTE_CONF, Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build());
        CaffeineCache allcollectors = new CaffeineCache(ALL_COLLECTORS_CACHE, Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .expireAfterAccess(5, TimeUnit.MINUTES)
                .build());
        CaffeineCache emgcontact = new CaffeineCache(EMG_CONTACT, Caffeine.newBuilder()
                .maximumSize(100000)
                .expireAfterWrite(24, TimeUnit.HOURS)
                .build());
        CaffeineCache skypayBankAddress=new CaffeineCache(SKYPAY_BANK_ADDRESS, Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(5,TimeUnit.HOURS)
                .build());
        CaffeineCache apimarketsource=new CaffeineCache(APIMARKETSOURCE, Caffeine.newBuilder()
                .maximumSize(10)
                .expireAfterWrite(1,TimeUnit.MINUTES)
                .build());
        CaffeineCache apiposeidonsource=new CaffeineCache(APIPOSEIDONSOURCE, Caffeine.newBuilder()
                .maximumSize(10)
                .expireAfterWrite(1,TimeUnit.MINUTES)
                .build());
        CaffeineCache riskface=new CaffeineCache(RISKFACE, Caffeine.newBuilder()
                .maximumSize(10)
                .expireAfterWrite(1,TimeUnit.MINUTES)
                .build());
        CaffeineCache adminCenterDomain = new CaffeineCache(ADMIN_CENTER_DOMAIN, Caffeine.newBuilder()
                .maximumSize(5)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build());
        CaffeineCache apimarketfeeconfig = new CaffeineCache(API_MARKET_FEE_CONFIG, Caffeine.newBuilder()
                .maximumSize(10)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build());
        CaffeineCache poseidonBalance = new CaffeineCache(API_POSEIDON_BALANCE, Caffeine.newBuilder()
                .maximumSize(10)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build());
        CaffeineCache riskBlackList = new CaffeineCache(RISK_BLACK_LIST, Caffeine.newBuilder()
                .maximumSize(100000)
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .build());
        CaffeineCache vnBankBranchList = new CaffeineCache(VN_BRANCH_BANK_CACHE, Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build());
        CaffeineCache privilegeCache = new CaffeineCache(ADMIN_PRIVILEGE_CACHE, Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(2, TimeUnit.MINUTES)
                .build());
        CaffeineCache thirdpartyDictionary = new CaffeineCache(THIRDPARTY_DICTIONARY, Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .build());
        CaffeineCache blackip = new CaffeineCache(BLACKIP_CACHE, Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .build());
        CaffeineCache ipcountry = new CaffeineCache(IP_COUNTRY_CACHE, Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .build());
        CaffeineCache collector_effienct_warraper = new CaffeineCache(COLLECTOR_EFFIENCT_WARRAPER, Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .expireAfterAccess(1, TimeUnit.MINUTES)
                .build());
        CaffeineCache request_frequence = new CaffeineCache(REQUEST_FREQUENCY, Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build());
        CaffeineCache request_black_list = new CaffeineCache(REQUEST_BLACK_LIST, Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .build());
        CaffeineCache request_ip_limit = new CaffeineCache(REQUEST_IP_LIMIT, Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(24*60, TimeUnit.MINUTES)
                .build());
        CaffeineCache reportCache = new CaffeineCache(REPORT_CACHE, Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(24*60, TimeUnit.MINUTES)
                .build());
        CaffeineCache fishTokenCache = new CaffeineCache(FISH_TOKEN_CACHE, Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build());
        cacheManager.setCaches(
                Arrays.asList(captchaCache, smsCache, smsLimitCache, accountkitSmsCache, tokenCache,
                        refreshTokenCache, customerContextCache, adminContextCache,
                        regionCache, faceVerifyCountCache, ocrCountCache,
                        customerCache, productCache, defaultProductCache, productgradeCache,googleTestProductCache, privacyCache,
                        setting, admintoken, customertoken, restsetting, packageproduct, createLoancount,
                        loanmarket, appversion, apppackagename, appsource, customerFlag,
                        rejectreason, kmitoken,odeopaytoken,cashfreetoken,greypaytoken,payutoken, collectionstage, sysdict, sysconfig, loanAppIdContractNo, offlineChannelName, allcollectors, emgcontact,
                        skypayRouteConf,adsplayCount,uuangCount,blackip,ipcountry,appsourcelog,
                        skypayBankAddress, apimarketsource, adminCenterDomain,collector_effienct_warraper,
                        riskBlackList,riskface, vnBankBranchList,privilegeCache,loansource,apimarketfeeconfig,apiposeidonsource,devicesourcecount,poseidonBalance,thirdpartyDictionary,request_frequence
                        ,request_black_list,request_ip_limit,reportCache,fishTokenCache));
        return cacheManager;
    }

}
