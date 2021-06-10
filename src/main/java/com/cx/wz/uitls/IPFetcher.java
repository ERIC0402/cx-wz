package com.cx.wz.uitls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPFetcher {
    private static final String FORWARDED_FOR_HEADER = "X-Forwarded-For";
    private static Logger logger = LoggerFactory.getLogger(IPFetcher.class);
    public static String getIPAddress(HttpServletRequest request) {
        String userAddress = StringUtils.isEmpty(request.getHeader(FORWARDED_FOR_HEADER)) ?
                request.getRemoteHost() :
                request.getHeader(FORWARDED_FOR_HEADER);

        if (StringUtils.hasText(userAddress) && userAddress.indexOf(',') > 0) {
            userAddress = userAddress.substring(0, userAddress.indexOf(','));
        }

        return userAddress;
    }

    /**
     * 获取IP地址
     *
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            //如果有多个，取值第一个
            if (!StringUtils.isEmpty(ip) && ip.contains(",")) {
                String[] ipArgs = ip.split(",");
                ip = ipArgs[0];
            }
        } catch (Exception e) {
            logger.error("IPUtils ERROR ", e);
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    public static boolean innerIP(String ip) {
        Pattern reg = Pattern.compile("^(127\\.0\\.0\\.1)|(localhost)|(10\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})|(172\\.((1[6-9])|(2\\d)|(3[01]))\\.\\d{1,3}\\.\\d{1,3})|(192\\.168\\.\\d{1,3}\\.\\d{1,3})$");
        Matcher match = reg.matcher(ip);
        return match.find();
    }
}
