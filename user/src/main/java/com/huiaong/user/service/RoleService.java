package com.huiaong.user.service;

import com.huiaong.common.model.user.Role;
import com.huiaong.common.response.Response;

import java.util.List;

public interface RoleService {

    /**
     * 根据id列表查询
     *
     * @param ids
     * @return
     */
    Response<List<Role>> findByIds(List<Long> ids);

}
