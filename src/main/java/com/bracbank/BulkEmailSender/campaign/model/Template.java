package com.bracbank.BulkEmailSender.campaign.model;

import javax.validation.constraints.NotNull;
import javax.xml.soap.Text;
import java.io.Serializable;
import java.util.Date;

public class Template implements Serializable{
    @NotNull
    private Long templates_id;
    private String name;
    private String description;
    private String html;
    private String text;
    private Date created;

    public Template() {
    }

    public Template(@NotNull Long templates_id, String name, String html, String description, String text, Date created) {
        this.templates_id = templates_id;
        this.name = name;
        this.html = html;
        this.description = description;
        this.text = text;
        this.created = created;
    }

    public Long getTemplates_id() {
        return templates_id;
    }

    public void setTemplates_id(Long templates_id) {
        this.templates_id = templates_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
