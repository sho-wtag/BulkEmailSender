package com.bracbank.BulkEmailSender.settings.model;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class SmtpSetting {
    @NotNull
    private Long id;
    @NotNull
    private String smtp_hostname;
    @NotNull
    private String smtp_port;
    private String smtp_encryption;
    @NotNull
    private String smtp_user;
    @NotNull
    private String smtp_pass;
    private String smtp_max_connections;
    private String smtp_max_messages;
    private String smtp_log;
    private String service_url;
    private String admin_email;

    public SmtpSetting() {
    }

    public SmtpSetting(@NotNull Long id, @NotNull String smtp_hostname, @NotNull String smtp_port, String smtp_encryption, @NotNull String smtp_user, @NotNull String smtp_pass, String smtp_max_connections, String smtp_max_messages, String smtp_log, String service_url, String admin_email) {
        this.id = id;
        this.smtp_hostname = smtp_hostname;
        this.smtp_port = smtp_port;
        this.smtp_encryption = smtp_encryption;
        this.smtp_user = smtp_user;
        this.smtp_pass = smtp_pass;
        this.smtp_max_connections = smtp_max_connections;
        this.smtp_max_messages = smtp_max_messages;
        this.smtp_log = smtp_log;
        this.service_url = service_url;
        this.admin_email = admin_email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSmtp_hostname() {
        return smtp_hostname;
    }

    public void setSmtp_hostname(String smtp_hostname) {
        this.smtp_hostname = smtp_hostname;
    }

    public String getSmtp_port() {
        return smtp_port;
    }

    public void setSmtp_port(String smtp_port) {
        this.smtp_port = smtp_port;
    }

    public String getSmtp_encryption() {
        return smtp_encryption;
    }

    public void setSmtp_encryption(String smtp_encryption) {
        this.smtp_encryption = smtp_encryption;
    }

    public String getSmtp_user() {
        return smtp_user;
    }

    public void setSmtp_user(String smtp_user) {
        this.smtp_user = smtp_user;
    }

    public String getSmtp_pass() {
        return smtp_pass;
    }

    public void setSmtp_pass(String smtp_pass) {
        this.smtp_pass = smtp_pass;
    }

    public String getSmtp_max_connections() {
        return smtp_max_connections;
    }

    public void setSmtp_max_connections(String smtp_max_connections) {
        this.smtp_max_connections = smtp_max_connections;
    }

    public String getSmtp_max_messages() {
        return smtp_max_messages;
    }

    public void setSmtp_max_messages(String smtp_max_messages) {
        this.smtp_max_messages = smtp_max_messages;
    }

    public String getSmtp_log() {
        return smtp_log;
    }

    public void setSmtp_log(String smtp_log) {
        this.smtp_log = smtp_log;
    }

    public String getService_url() {
        return service_url;
    }

    public void setService_url(String service_url) {
        this.service_url = service_url;
    }

    public String getAdmin_email() {
        return admin_email;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }

    private List<SmtpSetting> smtpSettings =new ArrayList<SmtpSetting>();

    public void setFeatures(List<SmtpSetting> smtpSettings) {
        this.smtpSettings = smtpSettings;
    }

    public List<SmtpSetting> getSmtpSettings() {
        return smtpSettings;
    }

}
