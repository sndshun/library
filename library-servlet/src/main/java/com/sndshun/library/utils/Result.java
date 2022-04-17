package com.sndshun.library.utils;

import com.sndshun.library.common.config.ResultCode;

import java.io.Serializable;

/**
 * 统一响应返回体
 *
 * @author sndshun
 * @since 2022-03-26 17:16:09
 */

public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String message;
    private T data;

    private Result() {
    }

    private Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功
     *
     * @param data 返回给前台的数据
     * @param <T>  指定类型
     * @return 统一响应体
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }


    /**
     * 返回错误
     *
     * @param resultCode 状态码枚举类
     * @return 统一响应返回体
     */
    public static <T> Result<T> error(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMsg());
    }

    /**
     * 自定义异常信息
     *
     * @param msg 自定义返回信息
     * @return 统一响应体
     */
    public static <T> Result<T> error(String msg) {
        return new Result<>(ResultCode.FAIL.getCode(), msg);
    }

    /**
     * 链式编程   Result.result().data(..).code(..)
     *
     * @param <T> 当前类
     * @return 当前类
     */
    public static <T> Result<T> result() {
        return new Result<>();
    }

    /**
     * 设置状态码
     *
     * @param code 状态码
     * @return 当前类
     */
    public Result<T> code(Integer code) {
        this.code = code;
        return this;
    }

    /**
     * 设置返回信息
     *
     * @param msg 提示信息
     * @return 当前类
     */
    public Result<T> message(String msg) {
        this.message = msg;
        return this;
    }

    /**
     * 设置返回数据
     *
     * @param data 返回数据
     * @return 当前类
     */
    public Result<T> data(T data) {
        this.data = data;
        return this;
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

    public void setMessage(String massage) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
