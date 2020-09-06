package com.huiaong.common.model.user;

import com.huiaong.common.model.base.BaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Address extends BaseBean {

    private Integer pid;

    private String name;

    private Integer level;

    private String pinyin;

    private String englishName;

    private String unicodeCode;
}
