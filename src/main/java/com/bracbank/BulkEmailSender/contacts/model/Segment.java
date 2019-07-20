package com.bracbank.BulkEmailSender.contacts.model;

import java.util.Date;

public class Segment {
    private Long segment_id;
    private Integer list;
    private String name;
    private Short type;
    private Date created;

    public Segment() {
    }

    public Segment(Long segment_id, Integer list, String name, Short type, Date created) {
        this.segment_id = segment_id;
        this.list = list;
        this.name = name;
        this.type = type;
        this.created = created;
    }

    public Long getSegment_id() {
        return segment_id;
    }

    public void setSegment_id(Long segment_id) {
        this.segment_id = segment_id;
    }

    public Integer getList() {
        return list;
    }

    public void setList(Integer list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
