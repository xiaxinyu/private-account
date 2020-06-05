package com.account.domain.common;

import lombok.ToString;

/**
 * 公共返回信息
 *
 * @author xiaxinyu
 * @date 2019.12.26
 */
@ToString
public class ResponseEntity<T> {
    /**
     * 响应成功业务编码
     */
    public static final Integer SUCCESS_CODE = 20000;

    /**
     * 响应失败业务编码
     */
    public static final Integer ERROR_CODE = 30000;

    /**
     * 无权限编码
     */
    public static final Integer NO_AUTHORIZATION_CODE = 40000;

    private T data;

    /**
     * 默认成功
     */
    private Integer code = SUCCESS_CODE;

    private String message;

    public static ResponseEntity ok() {
        return new ResponseEntity();
    }

    public static ResponseEntity error(String message) {
        return new ResponseEntity(ERROR_CODE, message);
    }

    public boolean isOk() {
        return code.intValue() == SUCCESS_CODE.intValue();
    }

    public boolean isNotAuthorized() {
        return code.intValue() == NO_AUTHORIZATION_CODE.intValue();
    }

    public boolean isError() {
        return code.intValue() == ERROR_CODE.intValue();
    }

    public ResponseEntity(T data) {
        this.data = data;
    }

    public ResponseEntity() {

    }

    public ResponseEntity(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
