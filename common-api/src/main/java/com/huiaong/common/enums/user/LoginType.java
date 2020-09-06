package com.huiaong.common.enums.user;


import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum LoginType {
    NAME(1, "用户名"),
    EMAIL(2, "邮箱"),
    MOBILE(3, "手机号");

    private final Integer value;
    private final String desc;


    public static LoginType from(int value) {
        for (LoginType range : LoginType.values()) {
            if (Objects.equals(range.value, value)) {
                return range;
            }
        }
        throw new IllegalArgumentException("illegal login type: " + value);
    }

    public Integer value() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }
}
