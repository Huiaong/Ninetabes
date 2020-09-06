package com.huiaong.user.dao;

import com.huiaong.common.model.user.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleDao {

    List<UserRole> findByUserId(Long userId);
}
