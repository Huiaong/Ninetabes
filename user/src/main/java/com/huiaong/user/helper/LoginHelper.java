package com.huiaong.user.helper;

import cn.hutool.json.JSONUtil;
import com.google.common.base.Throwables;
import com.huiaong.common.dto.LoginUser;
import com.huiaong.common.enums.user.LoginType;
import com.huiaong.common.enums.user.UserStatus;
import com.huiaong.common.exception.BusinessException;
import com.huiaong.common.model.user.Role;
import com.huiaong.common.model.user.User;
import com.huiaong.common.model.user.UserRole;
import com.huiaong.common.response.Response;
import com.huiaong.common.utils.EncryptUtil;
import com.huiaong.common.utils.UserUtil;
import com.huiaong.user.constant.UserConstant;
import com.huiaong.user.service.RoleService;
import com.huiaong.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class LoginHelper {
    private final UserService userService;
    private final RoleService roleService;
    private final RedissonClient redissonClient;

    public Response<LoginUser> login(String loginName, String password, LoginType loginType) {
        try {
            Response<User> pikaUserResp = userService.findByLoginNameAndLoginType(loginName, loginType);
            if (!pikaUserResp.isSuccess()) {
                return Response.fail(pikaUserResp.getError());
            }

            User user = pikaUserResp.getResult();
            switch (UserStatus.from(user.getStatus())) {
                case NORMAL:
                    if (!EncryptUtil.match(password, user.getPassword())) {
                        log.error("user(loginName={}, loginType={})'s password mismatch, login failed", loginName, loginType);
                        return Response.fail("user.password.mismatch");
                    }

                    LoginUser loginUser = new LoginUser();
                    BeanUtils.copyProperties(user, loginUser);

                    String token = user.getId().toString().concat("_").concat(UUID.randomUUID().toString());
                    loginUser.setToken(token);

                    List<Long> ids = user.getRoleIds().stream().map(UserRole::getRoleId).collect(Collectors.toList());
                    Response<List<Role>> rolesResp = roleService.findByIds(ids);
                    if (!rolesResp.isSuccess()) {
                        log.error("find roles by ids:{} fail, cause by:{}", ids, rolesResp.getError());
                        throw new BusinessException(rolesResp.getError());
                    }

                    List<Role> roles = rolesResp.getResult();
                    loginUser.setRoles(roles.stream().map(Role::getName).collect(Collectors.toList()));

                    this.cacheLoginUser(loginUser);

                    return Response.ok(loginUser);
                case LOCKED:
                    return Response.fail("user.status.locked");
                case FREEZE:
                    return Response.fail("user.status.freeze");
                case DELETED:
                    return Response.fail("user.status.deleted");
                default:
                    throw new IllegalStateException("Unexpected value: " + user.getStatus());
            }
        } catch (Exception e) {
            log.error("user login by loginName:{} password:{} loginType:{} fail, cause={}", loginName, password, loginType, Throwables.getStackTraceAsString(e));
            return Response.fail("user.login.fail");
        }
    }

    private void cacheLoginUser(LoginUser loginUser) {
        loginUser.setCacheTime(LocalDateTime.now());
        loginUser.setExpireTime(UserConstant.EXPIRE_TIME);

        RBucket<String> tokenBucket = redissonClient.getBucket(UserConstant.SESSION.concat(loginUser.getId().toString()));
        tokenBucket.set(JSONUtil.toJsonStr(loginUser), UserConstant.EXPIRE_TIME, TimeUnit.MILLISECONDS);
    }

    public Response<LoginUser> getInfo() {
        RBucket<String> tokenBucket = redissonClient.getBucket(UserConstant.SESSION.concat(String.valueOf(UserUtil.getUserId())));
        String loginUserJson = tokenBucket.get();
        LoginUser loginUser = JSONUtil.toBean(loginUserJson, LoginUser.class);
        return Response.ok(loginUser);
    }

    public Response<Boolean> logout() {
        RBucket<String> tokenBucket = redissonClient.getBucket(UserConstant.SESSION.concat(String.valueOf(UserUtil.getUserId())));
        return Response.ok(tokenBucket.delete());
    }

    public Response<Boolean> refreshToken() {
        RBucket<String> tokenBucket = redissonClient.getBucket(UserConstant.SESSION.concat(String.valueOf(UserUtil.getUserId())));
        return Response.ok(tokenBucket.expire(UserConstant.EXPIRE_TIME, TimeUnit.MILLISECONDS));
    }
}
