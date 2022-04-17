package com.sndshun.library.common.exception;


import com.sndshun.library.utils.Result;

/**
 * @Author mr.sun
 * @Creation 2022 2022/3/20 22:09
 * @Desc: 自定义异常
 */
public class CommonJsonException extends RuntimeException{
    private final Result result;

    public CommonJsonException(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }
}
