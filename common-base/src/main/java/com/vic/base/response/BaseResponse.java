package com.vic.base.response;


import lombok.Getter;
import lombok.Setter;

/**
 * 公共响应类
 */
@Setter
@Getter
public class BaseResponse<T> {

    private static final int CODE_SUCCESS = 200;

    private static final int CODE_FAIL = 500;

    private static final int CODE_ERROR = 500;

    private static final int USER_NOT_LOGIN = 300;

    private int code;

    private String msg;

    private T data;

    public BaseResponse(int code, String msg, T data) {
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
    }

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>(CODE_SUCCESS, "success", null);
    }

    public static <T> BaseResponse<T> success(String message) {
        return new BaseResponse<>(CODE_SUCCESS, message, null);
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(CODE_SUCCESS, "success", data);
    }

    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse<>(CODE_SUCCESS, message, data);
    }

    public static <T> BaseResponse<T> error() {
        return new BaseResponse<>(CODE_ERROR, "fail", null);
    }

    public static <T> BaseResponse<T> error(String message) {
        return new BaseResponse<>(CODE_ERROR, message, null);
    }

    public static <T> BaseResponse<T> error(T data) {
        return new BaseResponse<>(CODE_ERROR, "fail", data);
    }

    public static <T> BaseResponse<T> error(String message, T data) {
        return new BaseResponse<>(CODE_ERROR, message, data);
    }

    public static <T> BaseResponse<T> badRequest() {
        return new BaseResponse<>(CODE_FAIL, "no identifier arguments", null);
    }

    public static <T> BaseResponse<T> badRequest(String message) {
        return new BaseResponse<>(CODE_FAIL, message, null);
    }

    public static <T> BaseResponse<T> badRequest(T data) {
        return new BaseResponse<>(CODE_FAIL, "no identifier arguments", data);
    }

    public static <T> BaseResponse<T> badRequest(String message, T data) {
        return new BaseResponse<>(CODE_FAIL, message, data);
    }

    public static <T> BaseResponse<T> noLogin(String message) {
        return new BaseResponse<>(USER_NOT_LOGIN, message, null);
    }

}

