package com.cx.wz.uitls;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class RemoteUrlFetcher {

    public static String getRemoteUrl(HttpServletRequest request) {
        StringBuffer requestURL = request.getRequestURL();
        if (StringUtils.isEmpty(requestURL)) {
            return null;
        } else {
            try {
                String u1 = request.getRequestURL().toString().split("//")[0];
                String u2 = request.getRequestURL().toString().split("//")[1].split("/")[0];
                String url = u1 + "//" + u2;
                return url;
            } catch (Exception e) {
                return request.getRequestURL().toString();
            }
        }
    }
}
