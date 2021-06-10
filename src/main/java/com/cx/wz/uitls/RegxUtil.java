package com.cx.wz.uitls;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegxUtil {
    /**
     * 数字
     */
    public static final String INTEGER = "[^0-9]";
    /**
     * 正整数正则表达式 >=0 ^[1-9]\d*|0$
     */
    public static final String  INTEGER_NEGATIVE = "^[1-9]\\d*|0$";
    /**
     * 正Double正则表达式 >=0  ^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$　
     */
    public static final String  DOUBLE_NEGATIVE ="^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$";

    public static final String  MOBILE_PREFIX = "^[0-9]{8,15}$";

    public static final String WEB_URL_REGEX = "^(http|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?$";
    /**
     * 判断字段是否为正整数正则表达式 >=0 符合返回ture
     * @param str
     * @return boolean
     */
    public static  boolean isINTEGER_NEGATIVE(String str) {
        return Regular(str,INTEGER_NEGATIVE);
    }

    /**
     * 判断字段是否为正浮点数正则表达式 >=0 符合返回ture
     * @param str
     * @return boolean
     */
    public static  boolean isDOUBLE_NEGATIVE(String str) {
        return Regular(str,DOUBLE_NEGATIVE);
    }

    /**
     * 判断字段是否为正浮点数正则表达式 >=0 符合返回ture
     * @param str
     * @return boolean
     */
    public static  boolean isMobile(String str) {
        return Regular(str,MOBILE_PREFIX);
    }
    /**
     * 匹配是否符合正则表达式pattern 匹配返回true
     * @param str 匹配的字符串
     * @param pattern 匹配模式
     * @return boolean
     */
    private static  boolean Regular(String str,String pattern){
        if(null == str || str.trim().length()<=0)
            return false;
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        return m.matches();
    }


    public static final String COMA = ",";
    public static int getSplitLength(String str,String spit){
        str = str.replaceAll("\\s+","");
        if(StringUtils.isEmpty(str)) return 0;
        return str.split(spit).length;
    }


    public static String replaceBlank(String str) {
        String dest="";
        if (!StringUtils.isEmpty(str)) {
            Pattern p = Pattern.compile("[!！（）》《——+|{}【】‘；：”“’。，、？]|\t|\r|\n|");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("").replaceAll("\\s+"," ");
        }
        return dest;
    }


    public static final char UNDERLINE='_';

    public static String camelToUnderline(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String getSortKey(String sort){
        return sort.substring(0,sort.indexOf("."));
    }

    public static String replaceSortKey(String sort,String newKey){
        return sort.replace(getSortKey(sort),newKey);
    }

    public static boolean isWebURL(String url){
        return  Regular(url, WEB_URL_REGEX);
    }

    //获取字符串中所有的数字
    public static String getAllNumbersFromStr(String str){
        if (StringUtils.isEmpty(str)){
            return null;
        }
        Pattern pattern = Pattern.compile(INTEGER);
        Matcher matcher = pattern.matcher(str);
        return matcher.replaceAll("").trim();
    }
}
