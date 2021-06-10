package com.cx.wz.config;

import com.cx.wz.config.exception.BaseException;
import com.cx.wz.config.exception.ErrorConstants;
import com.cx.wz.config.exception.ErrorMessage;
import com.cx.wz.uitls.mdc.MDCKey;
import com.cx.wz.uitls.messageresource.MessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@ControllerAdvice
public class ExceptionTranslator {

    private final Logger logger = LoggerFactory.getLogger(ExceptionTranslator.class);

    @Autowired
    private MessageHelper messageHelper;

//    @Autowired
//    private CounterService counterService;

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ResponseEntity handleBaseException(BaseException e) {
        String message = e.getMessage();
        if (StringUtils.isEmpty(message)) {
            message = resolveI18NMessage(e);
        }
        logger.error(message, e);
        MDC.put(MDCKey.ERROR_MESSAGE.name(), message);
//        counterService.increment(e.getCode());
        ErrorMessage errorMessage = new ErrorMessage(e.getCode(), message);
        HttpStatus httpStatus = resolveAnnotatedResponseStatus(e);
        return new ResponseEntity<>(errorMessage, httpStatus);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class,
            ServletRequestBindingException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            UnsatisfiedServletRequestParameterException.class})
    @ResponseBody
    public ResponseEntity handleRequestBindingException(Exception e) {
        logger.error(e.getMessage(), e);
        MDC.put(MDCKey.ERROR_MESSAGE.name(), e.getMessage());
//        counterService.increment(ErrorConstants.ERR_MISSING_PARAMETER);
        ErrorMessage errorMessage = new ErrorMessage(ErrorConstants.ERR_MISSING_PARAMETER, e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseBody
    public ResponseEntity handleRequestBindingException(MissingServletRequestPartException e) {
        logger.error(e.getMessage(), e);
        MDC.put(MDCKey.ERROR_MESSAGE.name(), e.getMessage());
//        counterService.increment(ErrorConstants.ERR_MISSING_PARAMETER);
        ErrorMessage errorMessage = new ErrorMessage(ErrorConstants.ERR_MISSING_PARAMETER, "missing parameter");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleUnexpectedException(Exception e) {
        logger.error(e.getMessage(), e);
        MDC.put(MDCKey.ERROR_MESSAGE.name(), e.getMessage());
        ErrorMessage errorMessage = new ErrorMessage(ErrorConstants.ERR_EXCEPTION, "internal server error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private HttpStatus resolveAnnotatedResponseStatus(BaseException e) {
        ResponseStatus annotation = AnnotatedElementUtils.findMergedAnnotation(e.getClass(), ResponseStatus.class);
        if (annotation != null) {
            return annotation.value();
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private String resolveI18NMessage(BaseException e) {
        if (StringUtils.isEmpty(e.getMessage())) {
            return messageHelper.getMessage(e.getCode(), e.getParams(), "unspecified error message");
        }
        return e.getMessage();
    }

}
