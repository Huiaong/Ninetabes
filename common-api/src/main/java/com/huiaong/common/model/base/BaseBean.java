package com.huiaong.common.model.base;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseBean implements Serializable {

    private Long id;

    private Long createId;

    private LocalDateTime createdAt;

    private Long updatedId;

    private LocalDateTime updatedAt;

    private Integer delFlag;

}
