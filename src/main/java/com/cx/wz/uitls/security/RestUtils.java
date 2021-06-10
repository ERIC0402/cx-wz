package com.cx.wz.uitls.security;

import com.cx.wz.uitls.*;
import com.cx.wz.uitls.device.GA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RestUtils {

    private static final Logger logger = LoggerFactory.getLogger(RestUtils.class);

    public static GA assembleGA(String referrer) {
        try {
            GA ga = new GA();
            String array[] = referrer.split("&");
            Map<String,String> map = new HashMap<>();
            for (String a : array){
                int index = a.indexOf("=");
                if (index !=0) {
                    String key = a.substring(0, index);
                    String value = a.substring(index + 1);
                    map.put(key,value);
                }

            }
            ga.setSource(map.get("utm_source"));
            if(Arrays.asList("adsplay","fbuuang").contains(ga.getSource())) {
                ga.setSource(ga.getSource()+"(非定义)");
            }
            if (null != map.get("adsplayload") || null != map.get("gclid") || referrer.contains("pcampaignid=inline|youtubeads|")) {
                ga.setSource("adsplay");
            }

            if (null != map.get("fb") && map.containsKey("promoCode")) {
                ga.setSource(map.get("fb"));
                Map<String, String> temp = cloneMap(map);
                temp.remove("promoCode");
                referrer = BizUtils.generateParamByMap(temp);
            }
            ga.setReferrer(referrer);
            ga.setMedium(map.get("utm_medium"));
            ga.setTerm(map.get("utm_term"));
            ga.setContent(map.get("utm_content"));
            ga.setCampaign(map.get("utm_campaign"));
            ga.setPartnerId(map.get("partner_id"));
            ga.setPartnerClickId(map.get("click_id"));
            return ga;
        } catch (Exception e) {
            logger.error("不合规渠道：[{}] {} ", referrer, e.toString());
            return null;
        }
    }

    /**
     * Function used to generate an AppInfo from the HttpServletRequest.
     *
     * @param request
     * @return
     */
    public static AppInfo generateAppInfo(HttpServletRequest request) {
        String appPackageName = request.getHeader(CustomizedHttpHeader.APP_PACKAGE_NAME);
        String appVersionStr = request.getHeader(CustomizedHttpHeader.APP_VERSION);
        String referrer = request.getHeader(CustomizedHttpHeader.REFERRER);
        Integer appVersion = null;
        if (!StringUtils.isEmpty(appVersionStr)) {
            appVersion = Integer.parseInt(appVersionStr);
        }

        String androidId = request.getHeader(CustomizedHttpHeader.ANDROID_ID);
        String gaid = request.getHeader(CustomizedHttpHeader.GA_ID);
        String macAddress = request.getHeader(CustomizedHttpHeader.MAC_ADDRESS);
        String ipAddress = IPFetcher.getIPAddress(request);
        String remoteUrl = RemoteUrlFetcher.getRemoteUrl(request);
        String appSource = null;
        if(StringUtils.isEmpty(referrer)) {
            GA ga = RestUtils.assembleGA(referrer);
            if (ga != null) {
                appSource = ga.getSource();
            }
        }

        String deviceStr;
        AppInfo.SourceType sourceType;
        deviceStr = androidId;
        sourceType = AppInfo.SourceType.APP;
        AppInfo appInfo = new AppInfo();
        appInfo.setAppPackageName(appPackageName)
                .setAppVersion(appVersion)
                .setAppSource(appSource)
                .setDeviceStrId(deviceStr)
                .setSourceType(sourceType)
                .setAndroidId(androidId)
                .setRemoteUrl(remoteUrl)
                .setGaid(gaid)
                .setMacAddress(macAddress)
                .setIp(ipAddress);
        return appInfo;
    }

    public static boolean ignoreLog(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/loanapp/admin/logo");
    }

    public static boolean ignoreLog(String methodInfo) {
        return methodInfo.startsWith("class:LoanAppController | method:getLogoConfig |");
    }

    private static Map<String, String> cloneMap(Map<String, String> map) {
        Map<String, String> ret = new HashMap<>();
        if (map == null) {
            return ret;
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            ret.put(entry.getKey(), entry.getValue());
        }
        return ret;
    }
}
