package com.account.core;

import com.account.domain.common.Constants;

import java.util.Objects;

public class AppException extends RuntimeException {
    private Integer errorCode;
    private String description;
    private Object[] parameters;
    private Throwable cause;

    public AppException(String description) {
        super(description);
        this.errorCode = Constants.FAILURE;
        this.description = description;
    }

    public AppException(String description, Object[] parameters) {
        super(description);
        this.errorCode = Constants.FAILURE;
        this.description = description;
        this.parameters = parameters;
    }

    public AppException(Integer errCode, String description, Object[] parameters) {
        super(description);
        this.errorCode = errCode;
        this.description = description;
        this.parameters = parameters;
    }

    public AppException(String description, Throwable cause, Integer errorCode) {
        super(description, cause);
        this.errorCode = errorCode == null ? Constants.FAILURE : errorCode;
        this.description = description;
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public String getDescription() {
        return this.description;
    }

    public Object[] getParameters() {
        return this.parameters;
    }

    @Override
    public String toString() {
        if (Objects.isNull(super.getCause())) {
            return super.toString();
        } else {
            return super.toString() + "<---- Caused by: " + cause.toString() + " ---->";
        }
    }
}
