package com.huiaong.user.service.impl;

import com.google.common.base.Throwables;
import com.huiaong.common.model.user.Role;
import com.huiaong.common.response.Response;
import com.huiaong.user.dao.RoleDao;
import com.huiaong.user.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Override
    public Response<List<Role>> findByIds(List<Long> ids) {
        try {
            return Response.ok(roleDao.findByIds(ids));
        } catch (Exception e) {
            log.error("find roles by ids:{} fail, cause={}", ids, Throwables.getStackTraceAsString(e));
            return Response.fail("roles.find.fail");
        }
    }
}
