package com.huiaong.user.controller;

import com.google.common.base.CharMatcher;
import com.huiaong.common.dto.LoginUser;
import com.huiaong.common.enums.user.LoginType;
import com.huiaong.common.response.Response;
import com.huiaong.user.helper.LoginHelper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final LoginHelper loginHelper;

    @ResponseBody
    @PostMapping("/login")
    public Response<LoginUser> login(@RequestParam("username") String loginName, @RequestParam String password) {
        LoginType type;
        if (CharMatcher.inRange('0', '9').matchesAllOf(loginName)) {
            type = LoginType.MOBILE;
        } else if (CharMatcher.is('@').matchesAnyOf(password)) {
            type = LoginType.EMAIL;
        } else {
            type = LoginType.NAME;
        }
        return loginHelper.login(loginName, password, type);
    }

    @ResponseBody
    @GetMapping("/info")
    public Response<LoginUser> info() {
        return loginHelper.getInfo();
    }

    @ResponseBody
    @PostMapping("/logout")
    public Response<Boolean> logout() {
        return loginHelper.logout();
    }

    @ResponseBody
    @GetMapping("/refresh-token")
    public Response<Boolean> refreshToken() {
        return loginHelper.refreshToken();
    }

}
