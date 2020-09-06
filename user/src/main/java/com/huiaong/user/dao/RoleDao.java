package com.huiaong.user.dao;

import com.huiaong.common.criteria.user.RoleCriteria;
import com.huiaong.common.model.user.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    List<Role> findById(Long id);

    /**
     * 根据id列表查询
     *
     * @param ids
     * @return
     */
    List<Role> findByIds(List<Long> ids);

    /**
     * 根据criteria查询
     *
     * @param criteria
     * @return
     */
    List<Role> findByCriteria(RoleCriteria criteria);
}
