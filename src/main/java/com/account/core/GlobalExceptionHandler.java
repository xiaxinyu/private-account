package com.account.core;

import com.account.domain.common.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Autowired
    MessageSource messageSource;

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity catchExceptionHandler(Exception exception) {
        log.error(exception.getMessage(), exception);

        String message;
        Throwable tempEx = getCause(exception);
        if (tempEx instanceof AppException) {
            AppException appEx = (AppException) tempEx;
            message = messageSource.getMessage(appEx.getDescription(), appEx.getParameters(), appEx.getMessage(), Locale.getDefault());
            return ResponseEntity.error(message);
        }

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

        message = messageSource.getMessage("error.system.error", null, "System error", Locale.getDefault());
        return ResponseEntity.error(message);
    }

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
