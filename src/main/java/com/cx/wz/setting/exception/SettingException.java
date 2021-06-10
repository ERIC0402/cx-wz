package com.cx.wz.setting.exception;

import com.cx.wz.config.exception.BaseException;
import com.cx.wz.config.exception.ErrorConstants;

public class SettingException extends BaseException {

    protected static String code = ErrorConstants.ERR_SETTING_EXCEPTION;

    public SettingException(String msg) {
        super(code, msg);
    }

}