package com.cx.wz.uitls.messageresource;

import com.cx.wz.uitls.localization.LocalizationContext;
import org.apache.commons.lang3.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

@Component
public class MessageHelper {

    private final Logger logger = LoggerFactory.getLogger(MessageHelper.class);

    private static final Locale enLocale=LocaleUtils.toLocale("en_US");

    private static final String DEFAULT_MESSAGE="unspecified message";

    @Autowired
    private MessageSource messageSource;
    @Autowired(required = false)
    private HttpSession session;
    @Autowired
    private LocalizationContext localizationContext;

    public String getMessage(String code) {
        return getMessage(code, DEFAULT_MESSAGE);
    }

    public String getMessage(String code, String defaultMessage) {
        return getMessage(code, new String[]{}, defaultMessage);
    }

    public String getMessage(String code, List<String> params) {
        return getMessage(code, params.toArray(new String[0]), DEFAULT_MESSAGE);
    }

    public String getMessage(String code, List<String> params, Locale locale) {
        return getMessage(code, params.toArray(new String[0]), DEFAULT_MESSAGE, locale);
    }

    public String getMessage(String code, String[] args, String defaultMessage) {
        return getMessage(code, args, defaultMessage, getCurrentLocale());
    }

    public String getMessage(String code, String defaultMessage, Locale locale) {
        return getMessage(code, new String[]{}, defaultMessage, locale);
    }

    public String getMessage(String code, String[] args, String defaultMessage, Locale locale) {
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }

    public String getMessageByDefaultLocale(String code){
        return messageSource.getMessage(code,new String[]{},DEFAULT_MESSAGE,localizationContext.defaultLocale());
    }

    public Locale getCurrentLocale() {
        try {
            // 异步报表的job获取不到session，暂时用此hard code将enum等转码为英语
            if(Thread.currentThread().getName().contains("报表任务线程")){
                return enLocale;
            }
            Locale locale = (Locale) session.getAttribute("locale");
            if (locale == null) {
                logger.warn("init locale failed, use default locale instead");
                if(null != session) {
                    session.setAttribute("locale", localizationContext.defaultLocale());
                }
                return localizationContext.defaultLocale();
            }
            return locale;
        } catch (Exception e) {
            logger.warn("init locale has exception, use default locale instead");
            return localizationContext.defaultLocale();
        }
    }

}
