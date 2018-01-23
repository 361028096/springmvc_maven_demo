package com.run.dto;

import java.util.Date;

/**
 * @author : hewei
 * @date : 2017/12/8
 */
public class TestAttributeTwo {
    private String id;

    private String name;

    private Date dueTime;

    private Integer instalment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public Integer getInstalment() {
        return instalment;
    }

    public void setInstalment(Integer instalment) {
        this.instalment = instalment;
    }
}
