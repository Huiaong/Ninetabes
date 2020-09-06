package com.huiaong.user.service;

import com.huiaong.common.enums.user.LoginType;
import com.huiaong.common.model.user.User;
import com.huiaong.common.response.Response;

public interface UserService {

    /**
     * 根据登录名和登录类型查询
     *
     * @param loginName
     * @param loginType
     * @return
     */
    Response<User> findByLoginNameAndLoginType(String loginName, LoginType loginType);

}
