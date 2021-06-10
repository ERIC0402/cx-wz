package com.cx.wz.uitls;

import com.cx.wz.multipackage.AppPackageNameProductEntity;
import com.cx.wz.multipackage.AppPackageNameProductMapper;
import com.cx.wz.setting.SettingConfig;
import com.cx.wz.setting.entity.SettingEntity;
import com.cx.wz.setting.service.SettingService;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class BaseInfoUtil {

    public static final Logger logger = LoggerFactory.getLogger(BaseInfoUtil.class);

    @Autowired
    private AppPackageNameProductMapper appPackageNameProductMapper;

    @Autowired
    private Environment env;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private SettingService settingService;

    public String getProductName(String appckageName) {
        String productName =env.getProperty("merchant.product.name");
        AppPackageNameProductEntity appPackageNameProductEntity = appPackageNameProductMapper.findOneByPackageName(appckageName);
        if(null != appPackageNameProductEntity) {
            productName = appPackageNameProductEntity.getAppName();
        }
        return productName;
    }

    public String getBannerUrl(String appckageName) {
        String bannerUrl = null;
        AppPackageNameProductEntity appPackageNameProductEntity = appPackageNameProductMapper.findOneByPackageName(appckageName);
        if(null != appPackageNameProductEntity) {
            bannerUrl = appPackageNameProductEntity.getBannerUrl();
        }
        return bannerUrl;
    }

    public static String getEMS() {
        StringBuffer sb=new StringBuffer();
        Runtime r = Runtime.getRuntime();
        sb.append("系统物理可用内存总计：" + r.freeMemory()
                / 1024 / 1024 + "MB");
        return sb.toString();
    }

    public String getSystemPlatform() {
        SettingEntity platform = settingService.getSettingsByConfig(SettingConfig.SYSTEM_PLATFROM_LOCAL);
        if(null != platform ) {
            return platform.getValue();
        }
        return  "";
    }

    public boolean isINSystem() {
        SettingEntity platform = settingService.getSettingsByConfig(SettingConfig.SYSTEM_PLATFROM_LOCAL);
        if(null != platform ) {
            return "IN".equals(platform.getValue().trim());
        }
        return  false;
    }

    public boolean isTHSystem() {
        SettingEntity platform = settingService.getSettingsByConfig(SettingConfig.SYSTEM_PLATFROM_LOCAL);
        if(null != platform ) {
            return "TH".equals(platform.getValue().trim());
        }
        return  false;
    }

    public boolean isIDSystem() {
        SettingEntity platform = settingService.getSettingsByConfig(SettingConfig.SYSTEM_PLATFROM_LOCAL);
        if(null != platform ) {
            return "ID".equals(platform.getValue().trim());
        }
        return  false;
    }

    public boolean isVNSystem() {
        SettingEntity platform = settingService.getSettingsByConfig(SettingConfig.SYSTEM_PLATFROM_LOCAL);
        if(null != platform ) {
            return "VN".equals(platform.getValue().trim());
        }
        return  false;
    }

    public boolean isPHSystem() {
        SettingEntity platform = settingService.getSettingsByConfig(SettingConfig.SYSTEM_PLATFROM_LOCAL);
        if(null != platform ) {
            return "PH".equals(platform.getValue().trim());
        }
        return  false;
    }

    public static void showEMS() {
        StringBuffer sb=new StringBuffer();
        Runtime r = Runtime.getRuntime();
        sb.append("系统物理可用内存总计：" + r.freeMemory()
                / 1024 / 1024 + "MB");
        logger.info(" {} ",sb.toString());
    }

    public void updateAppNamePackageNameConf(String appPackageName, String appName) {
        if (StringUtils.isEmpty(appPackageName) || StringUtils.isEmpty(appName)) {
            return;
        }
        SettingEntity requestSignSetting = settingService.getSettingsByConfig(SettingConfig.REQUEST_SIGN_VALIDATE);
        boolean validSign = false;
        if(null != requestSignSetting && !StringUtils.isEmpty(requestSignSetting.getValue())){
            validSign = Boolean.parseBoolean(requestSignSetting.getValue());
        }
        if (appPackageNameProductMapper.findOneByPackageName(appPackageName) == null) {
            try {
                appPackageNameProductMapper.createAppNamePackageNameConf(appPackageName, appName,validSign);
            } catch (PSQLException e) {
                logger.error("Database exception: {}", e.getServerErrorMessage());
            } finally {
                cacheManager.getCache("packageproduct").clear();
            }
        }
    }

}
