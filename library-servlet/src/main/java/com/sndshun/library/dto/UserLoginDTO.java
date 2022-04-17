package com.sndshun.library.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author mr.sun
 * @Creation 2022 2022/3/27 21:34
 * @Desc:
 */
@Data
public class UserLoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

}
