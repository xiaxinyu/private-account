package com.account.domain.model;

import lombok.*;

/**
 * Created by Summer.Xia on 08/31/2015.
 */
@Setter
@Getter
@ToString
public class Page {
    private int pageIndex;
    private int pageSize;
    private int recordBegin;
    private int recordEnd;

    public Page(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.recordBegin = getRecordBegin();
    }

    public int getRecordBegin() {
        return Math.max(0, (pageIndex - 1) * pageSize);
    }
}
