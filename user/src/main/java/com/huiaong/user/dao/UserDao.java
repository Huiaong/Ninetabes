package com.huiaong.user.dao;

import com.huiaong.common.criteria.user.UserCriteria;
import com.huiaong.common.model.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    /**
     * 根据Id查找
     *
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 根据name查找
     *
     * @param name
     * @return
     */
    User findByName(String name);

    /**
     * 根据邮箱查找
     *
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * 根据电话查找
     *
     * @param mobile
     * @return
     */
    User findByMobile(String mobile);

    /**
     * 根据criteria查找
     *
     * @param criteria
     * @return
     */
    List<User> findByCriteria(UserCriteria criteria);

}
