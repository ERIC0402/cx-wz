package com.cx.wz.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RecordAuthException extends BaseException {

    protected static String code = RecordErrorConstants.ERR_AUTH;

    public RecordAuthException(String code, String msg) {
        super(code, msg);
    }

    public RecordAuthException(String code) {
        super(code, null);
    }

    public RecordAuthException() {
        super(code, null);
    }

}
