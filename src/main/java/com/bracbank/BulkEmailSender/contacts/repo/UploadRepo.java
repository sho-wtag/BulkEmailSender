package com.bracbank.BulkEmailSender.contacts.repo;

import com.bracbank.BulkEmailSender.contacts.model.Contact;
import com.bracbank.BulkEmailSender.contacts.service.ContactService;
import com.bracbank.BulkEmailSender.user_auth.service.SessionService;
import com.bracbank.BulkEmailSender.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UploadRepo {

    @Autowired
    NamedParameterJdbcTemplate db;

    @Autowired
    JdbcTemplate jdb;

    @Autowired
    AppUtil util;

    @Autowired
    SessionService sessionService;

    @Autowired
    ContactService contactService;



}
