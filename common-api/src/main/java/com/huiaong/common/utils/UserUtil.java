package com.huiaong.common.utils;

import com.huiaong.common.dto.LoginUser;

public final class UserUtil {
    private static final ThreadLocal<LoginUser> user = new ThreadLocal<>();

    public static void putCurrentUser(LoginUser baseUser) {
        user.set(baseUser);
    }

    public static LoginUser getCurrentUser() {
        return user.get();
    }

    public static void clearCurrentUser() {
        user.remove();
    }

    public static Long getUserId() {
        LoginUser loginUser = user.get();
        return null != loginUser ? loginUser.getId() : null;
    }

    public static String getToken() {
        LoginUser loginUser = user.get();
        return null != loginUser ? loginUser.getToken() : null;
    }
}
