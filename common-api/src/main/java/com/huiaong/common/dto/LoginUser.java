package com.huiaong.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class LoginUser implements Serializable {

    private Long id;

    private String name;

    private String token;

    private String portrait;

    private LocalDateTime cacheTime;

    private Long expireTime;

    private List<String> roles;
}
