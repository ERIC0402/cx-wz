package com.cx.wz.uitls;

import java.util.Map;

public class BizUtils {
    public static String generateParamByMap(Map<String , String> map){
        if(map == null){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = s.substring(0 , s.length() - 1);
        }
        return s;
    }

    public static String generateParamByMap2(Map<String , Object> map){
        if(map == null){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = s.substring(0 , s.length() - 1);
        }
        return s;
    }
}
