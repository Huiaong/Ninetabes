package com.huiaong.common.criteria.user;

import com.huiaong.common.criteria.base.PagingCriteria;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleCriteria extends PagingCriteria {

    private Long id;

    private String name;
}
