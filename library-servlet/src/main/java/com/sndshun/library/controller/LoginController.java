package com.sndshun.library.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.sndshun.library.dto.UserInfoDTO;
import com.sndshun.library.dto.UserLoginDTO;
import com.sndshun.library.service.LoginService;
import com.sndshun.library.utils.MailMessage;
import com.sndshun.library.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author mr.sun
 * @Creation 2022 2022/3/27 21:27
 * @Desc:
 */
@RestController
public class LoginController {
    @Resource
    private LoginService loginService;
    @Autowired(required = false)
    private MailMessage mailMessage;
    @Resource
    private Cache<String, String> codeCache;

    @PostMapping("login")
    public Result<String> passwordLogin(@RequestBody UserLoginDTO dto) {
        return Result.success(loginService.passwordLogin(dto.getUsername(), dto.getPassword()));
    }

    @RequestMapping("getUserInfo")
    public Result<UserInfoDTO> getInfo(@RequestHeader String library) {

        return Result.success(loginService.getUserInfo(library));
    }
    @RequestMapping("logout")
    public Result<Boolean> logout(@RequestHeader String library) {
        StpUtil.logoutByTokenValue(library);
        return Result.success(true);
    }
    @RequestMapping("getCode")
    public Result<Boolean> getCode(@RequestParam String email) {
        //生成四位随机数
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 1000));
        codeCache.put(email, code);
        mailMessage.placeOrder(email, "验证码为：" + code);
        return Result.success(true);
    }
    @PostMapping("register")
    public Result<Boolean> register(@RequestBody UserLoginDTO dto) {
        return Result.success(loginService.register(dto));
    }
}
