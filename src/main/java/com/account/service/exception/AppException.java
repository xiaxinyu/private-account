package com.account.service.exception;

import com.account.domain.common.Constants;

import java.util.Objects;

/**
 * Defines a custom application exception with its error code, description and
 * root cause.
 *
 * @author xiaxinyu3
 * @date 2020.4.21
 */
public class AppException extends RuntimeException {
    /**
     * Code be used to get the error message, which is defined in the related
     * error constants file
     */
    private Integer errorCode;

    /**
     * Holds the exception message description.
     */
    private String description;

    /**
     * parameters.
     */
    private Object[] parameters;

    /**
     * Root cause of this exception
     */
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
