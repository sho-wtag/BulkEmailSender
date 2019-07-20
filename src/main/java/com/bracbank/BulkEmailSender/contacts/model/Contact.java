package com.bracbank.BulkEmailSender.contacts.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class Contact implements Serializable{
    @NotNull
    private Integer cid;
    private String email;
    private String opt_in_ip;
    private String opt_in_country;
    private Integer imported;
    private Boolean active;
    private Boolean is_test;
    private Date status_change;
    private Date latest_open;
    private Date latest_click;
    private Date created;
    private String first_name;
    private String last_name;
    @NotNull
    private Integer user_id;

    public Contact() {
    }

    public Contact(@NotNull Integer cid, String email, String opt_in_ip, String opt_in_country, Integer imported,
                   Boolean active, Boolean is_test, Date status_change, Date latest_open, Date latest_click,
                   Date created, String first_name, String last_name, @NotNull Integer user_id) {
        this.cid = cid;
        this.email = email;
        this.opt_in_ip = opt_in_ip;
        this.opt_in_country = opt_in_country;
        this.imported = imported;
        this.active = active;
        this.is_test = is_test;
        this.status_change = status_change;
        this.latest_open = latest_open;
        this.latest_click = latest_click;
        this.created = created;
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_id = user_id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpt_in_ip() {
        return opt_in_ip;
    }

    public void setOpt_in_ip(String opt_in_ip) {
        this.opt_in_ip = opt_in_ip;
    }

    public String getOpt_in_country() {
        return opt_in_country;
    }

    public void setOpt_in_country(String opt_in_country) {
        this.opt_in_country = opt_in_country;
    }

    public Integer getImported() {
        return imported;
    }

    public void setImported(Integer imported) {
        this.imported = imported;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getIs_test() {
        return is_test;
    }

    public void setIs_test(Boolean is_test) {
        this.is_test = is_test;
    }

    public Date getStatus_change() {
        return status_change;
    }

    public void setStatus_change(Date status_change) {
        this.status_change = status_change;
    }

    public Date getLatest_open() {
        return latest_open;
    }

    public void setLatest_open(Date latest_open) {
        this.latest_open = latest_open;
    }

    public Date getLatest_click() {
        return latest_click;
    }

    public void setLatest_click(Date latest_click) {
        this.latest_click = latest_click;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
