package com.sndshun.library.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.sndshun.library.dto.UserInfoDTO;
import com.sndshun.library.dto.UserLoginDTO;
import com.sndshun.library.service.LoginService;
import com.sndshun.library.utils.Result;
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
}
