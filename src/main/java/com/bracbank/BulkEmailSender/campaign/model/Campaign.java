package com.bracbank.BulkEmailSender.campaign.model;

import javax.validation.constraints.NotNull;
import javax.xml.soap.Text;
import java.io.Serializable;
import java.util.Date;

public class Campaign implements Serializable{
    @NotNull
    private Long campaign_id;
    private String cid;
    private Short type;
    private Integer parent;
    private String campaign_name;
    private Text description;
    @NotNull
    private Integer template;
    private String source_url;
    private String editor_name;
    private Text editor_data;
    private Date last_check;
    private String check_status;
    private String from;
    private String address;
    private String reply_to;
    private String subject;
    private Text html;
    private Text html_prepared;
    private Text text;
    private Short status;
    private Short tracking_disabled;
    private Date scheduled;
    private Date status_change;
    private Integer delivered;
    private Integer opened;
    private Integer clicks;
    private Integer unsubscribed;
    private Integer bounced;
    private Integer complained;
    private Date created;

    public Campaign() {
    }

    public Campaign(@NotNull Long campaign_id, String cid, Short type, Integer parent, String campaign_name, Text description,
                    @NotNull Integer list, @NotNull Integer segment, @NotNull Integer template, String source_url,
                    String editor_name, Text editor_data, Date last_check, String check_status, String from, String address,
                    String reply_to, String subject, Text html, Text html_prepared, Text text, Short status,
                    Short tracking_disabled, Date scheduled, Date status_change, Integer delivered, Integer opened,
                    Integer clicks, Integer unsubscribed, Integer bounced, Integer complained, Date created) {
        this.campaign_id = campaign_id;
        this.cid = cid;
        this.type = type;
        this.parent = parent;
        this.campaign_name = campaign_name;
        this.description = description;
        this.template = template;
        this.source_url = source_url;
        this.editor_name = editor_name;
        this.editor_data = editor_data;
        this.last_check = last_check;
        this.check_status = check_status;
        this.from = from;
        this.address = address;
        this.reply_to = reply_to;
        this.subject = subject;
        this.html = html;
        this.html_prepared = html_prepared;
        this.text = text;
        this.status = status;
        this.tracking_disabled = tracking_disabled;
        this.scheduled = scheduled;
        this.status_change = status_change;
        this.delivered = delivered;
        this.opened = opened;
        this.clicks = clicks;
        this.unsubscribed = unsubscribed;
        this.bounced = bounced;
        this.complained = complained;
        this.created = created;
    }

    public Long getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(Long campaign_id) {
        this.campaign_id = campaign_id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getCampaign_name() {
        return campaign_name;
    }

    public void setCampaign_name(String campaign_name) {
        this.campaign_name = campaign_name;
    }

    public Text getDescription() {
        return description;
    }

    public void setDescription(Text description) {
        this.description = description;
    }

    public Integer getTemplate() {
        return template;
    }

    public void setTemplate(Integer template) {
        this.template = template;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getEditor_name() {
        return editor_name;
    }

    public void setEditor_name(String editor_name) {
        this.editor_name = editor_name;
    }

    public Text getEditor_data() {
        return editor_data;
    }

    public void setEditor_data(Text editor_data) {
        this.editor_data = editor_data;
    }

    public Date getLast_check() {
        return last_check;
    }

    public void setLast_check(Date last_check) {
        this.last_check = last_check;
    }

    public String getCheck_status() {
        return check_status;
    }

    public void setCheck_status(String check_status) {
        this.check_status = check_status;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReply_to() {
        return reply_to;
    }

    public void setReply_to(String reply_to) {
        this.reply_to = reply_to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Text getHtml() {
        return html;
    }

    public void setHtml(Text html) {
        this.html = html;
    }

    public Text getHtml_prepared() {
        return html_prepared;
    }

    public void setHtml_prepared(Text html_prepared) {
        this.html_prepared = html_prepared;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getTracking_disabled() {
        return tracking_disabled;
    }

    public void setTracking_disabled(Short tracking_disabled) {
        this.tracking_disabled = tracking_disabled;
    }

    public Date getScheduled() {
        return scheduled;
    }

    public void setScheduled(Date scheduled) {
        this.scheduled = scheduled;
    }

    public Date getStatus_change() {
        return status_change;
    }

    public void setStatus_change(Date status_change) {
        this.status_change = status_change;
    }

    public Integer getDelivered() {
        return delivered;
    }

    public void setDelivered(Integer delivered) {
        this.delivered = delivered;
    }

    public Integer getOpened() {
        return opened;
    }

    public void setOpened(Integer opened) {
        this.opened = opened;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public Integer getUnsubscribed() {
        return unsubscribed;
    }

    public void setUnsubscribed(Integer unsubscribed) {
        this.unsubscribed = unsubscribed;
    }

    public Integer getBounced() {
        return bounced;
    }

    public void setBounced(Integer bounced) {
        this.bounced = bounced;
    }

    public Integer getComplained() {
        return complained;
    }

    public void setComplained(Integer complained) {
        this.complained = complained;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }


}
