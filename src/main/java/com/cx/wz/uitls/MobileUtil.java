package com.cx.wz.uitls;

import com.cx.wz.uitls.localization.LocalizationContext;
import com.cx.wz.uitls.localization.SuluLocale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class MobileUtil {
    public static String PHL = "63"; // 菲律宾
    public static String IDN = "62"; // 印度尼西亚
    public static String CN = "86"; // 中国
    public static String VNM = "84"; // 越南
    public static String IN = "91"; // 越南
    public static String MX = "52"; // 墨西哥

    @Autowired
    private LocalizationContext localizationContext;

    private Map<String, String> vnMobileMappingMap;

    @PostConstruct
    private void initVnMobileMappingMap() {
        vnMobileMappingMap = new HashMap<>();
        vnMobileMappingMap.put("120", "70");
        vnMobileMappingMap.put("121", "79");
        vnMobileMappingMap.put("122", "77");
        vnMobileMappingMap.put("126", "76");
        vnMobileMappingMap.put("128", "78");
        vnMobileMappingMap.put("123", "83");
        vnMobileMappingMap.put("124", "84");
        vnMobileMappingMap.put("125", "85");
        vnMobileMappingMap.put("127", "81");
        vnMobileMappingMap.put("129", "82");
        vnMobileMappingMap.put("162", "32");
        vnMobileMappingMap.put("163", "33");
        vnMobileMappingMap.put("164", "34");
        vnMobileMappingMap.put("165", "35");
        vnMobileMappingMap.put("166", "36");
        vnMobileMappingMap.put("167", "37");
        vnMobileMappingMap.put("168", "38");
        vnMobileMappingMap.put("169", "39");
        vnMobileMappingMap.put("186", "56");
        vnMobileMappingMap.put("188", "58");
        vnMobileMappingMap.put("199", "59");
    }

    /**
     * 显示手机号：13****9999， 前两位，后四位中间*
     * @return
     */
    public String mobileFormat(String mobile){
        if(mobile.length() > 6){
            return mobile.substring(0,2)+"****"+mobile.substring(mobile.length()-4);
        }
        return null;
    }
    public String trimMobile(String mobile) {
        String _mobile = mobile.trim().replace("+", "");
        if (_mobile.startsWith(localizationContext.getDefaultCallingCode())) {
            if (localizationContext.getSuluLocale() == SuluLocale.IN && _mobile.length() <= 10) {
                _mobile = "0" + _mobile;
            } else {
                _mobile = _mobile.replaceFirst(localizationContext.getDefaultCallingCode(), "0");
            }
        }
        if (!_mobile.startsWith("0")) {
            _mobile = "0" + _mobile;
        }
        return _mobile.replaceAll("\\s+", "").replaceAll("\\-", "");
    }

    public boolean ifMatch(String mobile) {
        if (localizationContext.getSuluLocale() != SuluLocale.ID) {
            return true;
        }
        if (mobile.length() > 14) {
            return false;
        }
        //TSEL
        if (mobile.startsWith("0811") || mobile.startsWith("0812") || mobile.startsWith("0813") || mobile.startsWith("0821") || mobile.startsWith("0822") || mobile.startsWith("0823") || mobile.startsWith("0851") || mobile.startsWith("0852") || mobile.startsWith("0853")) {
            return true;
        }
        //XL
        if (mobile.startsWith("0817") || mobile.startsWith("0818") || mobile.startsWith("0819") || mobile.startsWith("0859") || mobile.startsWith("0877") || mobile.startsWith("0878") || mobile.startsWith("0831") || mobile.startsWith("0832") || mobile.startsWith("0833") || mobile.startsWith("0838")|| mobile.startsWith("0839")) {
            return true;
        }
        //ISAT
        if (mobile.startsWith("0814") || mobile.startsWith("0815") || mobile.startsWith("0816") || mobile.startsWith("0855") || mobile.startsWith("0856") || mobile.startsWith("0857") || mobile.startsWith("0858")) {
            return true;
        }
        //HUT
        if (mobile.startsWith("0895") || mobile.startsWith("0896") || mobile.startsWith("0897") || mobile.startsWith("0898") || mobile.startsWith("0899")) {
            return true;
        }
        //Smartfren
        if (mobile.startsWith("0881") || mobile.startsWith("0882") || mobile.startsWith("0883") || mobile.startsWith("0884") || mobile.startsWith("0885") || mobile.startsWith("0886") || mobile.startsWith("0887") || mobile.startsWith("0888") || mobile.startsWith("0889")) {
            return true;
        }
        //HUT
        if (mobile.startsWith("0828")) {
            return true;
        }
        return false;
    }

    public String removeLeadingZero(String mobile){
        if (mobile.startsWith("0")){
            mobile=mobile.replaceFirst("0","");
        }
        return mobile;
    }
    public String remove62Mobile(String mobile){
        if (mobile.startsWith("62")){
            mobile=mobile.replaceFirst("62","");
        }
        return mobile;
    }

    //墨西哥
    public String remove52Mobile(String mobile){
        mobile = mobile.trim();
        if (mobile.startsWith("52") && mobile.length() > 10){
            mobile=mobile.replaceFirst("52","");
        }
        return mobile;
    }
    //indian
    public String remove91Mobile(String mobile){
        mobile = mobile.trim();
        if (mobile.startsWith("91") && mobile.length() > 10){
            mobile=mobile.replaceFirst("91","");
        }
        return mobile;
    }

    public String countryMobile(String mobile) {
        String _mobile = mobile.trim().replace("+", "");
        if (_mobile.startsWith("0")) {
            _mobile = _mobile.replaceFirst("0", localizationContext.getDefaultCallingCode());
        }
        return _mobile;
    }

    //将 0 开头的 变换成 +62
    public String changeMobile(String mobile) {
        String result = countryMobile(mobile);
        if (result.startsWith(localizationContext.getDefaultCallingCode())) {
            result = result.replaceFirst("", "+");
        }
        return result;
    }

    public boolean isStartWith62(String mobile) {
        String _mobile = mobile.trim().replace("+", "");
        if (_mobile.startsWith(localizationContext.getDefaultCallingCode())) {
            return true;
        }
        return false;
    }

    public boolean checkMobile(String mobile, String support) {
        String[] sp = support.trim().split(",");
        String _mobile = mobile.trim().replace("+", "");
        if (_mobile.startsWith(localizationContext.getDefaultCallingCode())){
            return true;
        }
        for (String vo : sp) {
            if (_mobile.startsWith(vo.trim())) {
                return true;
            }
        }

        return false;
    }

    // 过滤掉非 ascni 字符
    public String stringToAscii(String value) {
        if (StringUtils.isEmpty(value)) return "";
        if (IDN.equals(localizationContext.getDefaultCallingCode())
                || PHL.equals(localizationContext.getDefaultCallingCode())) {
            StringBuffer sbu = new StringBuffer();
            char[] chars = value.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (String.valueOf(chars[i]).getBytes().length == 1) {
                    sbu.append(chars[i]);
                }
            }
            return sbu.toString().replaceAll("\\s+", " ");
        } else {
            return value.replaceAll("\\s+", " ");
        }

    }

    public String mappingVNMobile(String mobile) {
        if (Objects.equals(localizationContext.getSuluLocale(),SuluLocale.VN)) {
            return mappingForVNMobile(mobile);
        } else {
            return mobile;
        }
    }

    private String mappingForVNMobile(String mobile) {
        if (mobile != null && mobile.length() == 11) {
            String oldPart = mobile.substring(1, 4);
            if (vnMobileMappingMap.containsKey(oldPart)) {
                String newPart = vnMobileMappingMap.get(oldPart);
                return new StringBuilder().append("0").append(newPart).append(mobile.substring(4)).toString();
            } else {
                return mobile;
            }
        } else {
            return mobile;
        }
    }
}
