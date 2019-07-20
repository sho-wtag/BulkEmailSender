/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracbank.BulkEmailSender.user_auth.service;

import com.bracbank.BulkEmailSender.user_auth.model.UserInfo;
import com.bracbank.BulkEmailSender.utils.KEY;
import javax.servlet.http.HttpSession;

import com.bracbank.BulkEmailSender.user_auth.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sarker
 */
@Component
public class SessionService {

    @Autowired
    HttpSession session;

    public UserInfo getUser() {
        return (UserInfo) session.getAttribute(KEY.USER);
    }

    public Long getUserId() {
        try {
            return getUser().getUser_id();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setToken(String token){
        session.setAttribute(KEY.APPxTKN, token);
    }

    public String getToken(){
        return (String)session.getAttribute(KEY.APPxTKN);
    }
}
