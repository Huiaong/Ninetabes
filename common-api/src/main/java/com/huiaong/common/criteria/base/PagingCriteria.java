package com.huiaong.common.criteria.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class PagingCriteria implements Serializable {

    @JsonIgnore
    private Integer pageNo = 1;
    @JsonIgnore
    private Integer pageSize = 20;
    @JsonIgnore
    private Boolean hasNext = true;

    public PagingCriteria() {
    }

    @JsonIgnore
    public Boolean hasNext() {
        return this.hasNext;
    }

    public void nextPage() {
        if (this.pageNo == null) {
            this.pageNo = 1;
        }

        this.pageNo = this.pageNo + 1;
    }

    public Integer getLimit() {
        PageInfo pageInfo = new PageInfo(this.pageNo, this.pageSize);
        return pageInfo.getLimit();
    }

    public Integer getOffset() {
        PageInfo pageInfo = new PageInfo(this.pageNo, this.pageSize);
        return pageInfo.getOffset();
    }

}
