package com.bracbank.BulkEmailSender.contacts.service;

import com.bracbank.BulkEmailSender.user_auth.service.SessionService;
import com.bracbank.BulkEmailSender.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadService {

    @Autowired
    SessionService sessionService;

    @Autowired
    AppUtil util;



}
