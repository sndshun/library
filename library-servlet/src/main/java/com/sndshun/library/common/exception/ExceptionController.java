package com.sndshun.library.common.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.sndshun.library.common.config.ResultCode;
import com.sndshun.library.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;

/**
 * 全局异常拦截
 *
 * @author sndshun
 * @since 2022-03-26 17:16:09
 */
@RestControllerAdvice
public class ExceptionController {


    @ExceptionHandler(Exception.class)
    public Result exception(HttpServletRequest request, Exception e) {
        return Result.error(ResultCode.ERROR).data(e);
    }

    @ExceptionHandler(CommonJsonException.class)
    public Result commonJsonException(CommonJsonException jsonException) {
        return jsonException.getResult();
    }

    @ExceptionHandler(NotLoginException.class)
    public Result notLoginException(NotLoginException nle) {
        String message = "";
        if (nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未提供token,请登录";
        } else if (nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "token无效";
        } else if (nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "token已过期";
        } else if (nle.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "token已被顶下线";
        } else if (nle.getType().equals(NotLoginException.KICK_OUT)) {
            message = "token已被踢下线";
        } else {
            message = "当前会话未登录";
        }
        return Result.error(message);
    }

    @ExceptionHandler(NotPermissionException.class)
    public Result notPermissionException() {
        return Result.error(ResultCode.PERMISSION_NO_ACCESS);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public Result fileNotFoundException() {
        return Result.error(ResultCode.FILE_NOT_FOUND);
    }
}
