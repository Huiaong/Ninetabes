package com.huiaong.common.criteria.user;


import com.huiaong.common.criteria.base.PagingCriteria;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserCriteria extends PagingCriteria implements Serializable {

    private Long id;

    private String name;

    private String email;

    private String mobile;

    /**
     * @see com.huiaong.common.enums.user.UserType
     */
    private Integer type;

    /**
     * @see com.huiaong.common.enums.user.UserStatus
     */
    private Integer status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
