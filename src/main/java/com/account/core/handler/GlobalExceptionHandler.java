package com.account.core.handler;

import com.account.domain.common.ResponseEntity;
import com.account.service.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;
import java.util.Objects;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    @Autowired
    MessageSource messageSource;

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity catchExceptionHandler(Exception exception) {
        log.error(exception.getMessage(), exception);

        String message;
        Throwable tempEx = getCause(exception);
        // 业务异常处理
        if (tempEx instanceof AppException) {
            AppException appEx = (AppException) tempEx;
            message = messageSource.getMessage(appEx.getDescription(), appEx.getParameters(), appEx.getMessage(), Locale.getDefault());
            return ResponseEntity.error(message);
        }

        //字段校验错误
        if (tempEx instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) tempEx;
            if (Objects.nonNull(ex)) {
                BindingResult bindingResult = ex.getBindingResult();
                if (Objects.nonNull(bindingResult)) {
                    FieldError fieldError = bindingResult.getFieldError();
                    if (Objects.nonNull(fieldError)) {
                        message = fieldError.getDefaultMessage();
                        return ResponseEntity.error(message);
                    }
                }
            }
        }

        if (exception instanceof DataIntegrityViolationException){

        }

        // 系统异常处理
        message = messageSource.getMessage("error.system.error", null, Locale.getDefault());
        return ResponseEntity.error(message);
    }

    /**
     * 获取异常
     *
     * @param ex 异常
     * @return 异常
     */
    private Throwable getCause(Throwable ex) {
        if (ex instanceof AppException) {
            return ex;
        } else {
            if (ex.getCause() == null) {
                return ex;
            } else {
                return getCause(ex.getCause());
            }
        }
    }
}
