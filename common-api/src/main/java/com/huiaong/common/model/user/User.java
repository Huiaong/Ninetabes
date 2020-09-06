package com.huiaong.common.model.user;

import com.huiaong.common.model.base.BaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseBean {

    private String name;

    private String email;

    private String mobile;

    private String password;

    /**
     * @see com.huiaong.common.enums.user.UserType
     */
    private Integer type;

    /**
     * @see com.huiaong.common.enums.user.UserStatus
     */
    private Integer status;

    private List<UserRole> roleIds;

    private String portrait;

}
