package com.huiaong.common.enums.user;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum UserStatus {

    NORMAL(1, "正常"),
    LOCKED(-1, "锁定"),
    FREEZE(-2, "冻结"),
    DELETED(-3, "删除");

    private final Integer value;
    private final String desc;

    public static UserStatus from(int value) {
        for (UserStatus range : UserStatus.values()) {
            if (Objects.equals(range.value, value)) {
                return range;
            }
        }
        throw new IllegalArgumentException("illegal user status: " + value);
    }

    public Integer value() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }
}
