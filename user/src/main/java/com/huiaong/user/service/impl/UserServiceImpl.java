package com.huiaong.user.service.impl;

import com.google.common.base.Throwables;
import com.huiaong.common.enums.user.LoginType;
import com.huiaong.common.model.user.User;
import com.huiaong.common.response.Response;
import com.huiaong.user.dao.UserDao;
import com.huiaong.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public Response<User> findByLoginNameAndLoginType(String loginName, LoginType loginType) {
        try {
            User user;
            switch (loginType) {
                case NAME:
                    user = userDao.findByName(loginName);
                    break;
                case EMAIL:
                    user = userDao.findByEmail(loginName);
                    break;
                case MOBILE:
                    user = userDao.findByMobile(loginName);
                    break;
                default:
                    return Response.fail("user.not.found");
            }
            if (Objects.isNull(user)) {
                log.error("user(loginName={}, loginType={}) not found", loginName, loginType);
                return Response.fail("user.not.found");
            }
            return Response.ok(user);
        } catch (Exception e) {
            log.error("find user by login name:{} and login type:{} fail, cause={}", loginName, loginType, Throwables.getStackTraceAsString(e));
            return Response.fail("user.find.fail");
        }
    }

}
