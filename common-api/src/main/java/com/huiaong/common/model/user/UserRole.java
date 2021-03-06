package com.huiaong.common.model.user;

import com.huiaong.common.model.base.BaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserRole extends BaseBean {
    private Long userId;

    private Long roleId;
}
