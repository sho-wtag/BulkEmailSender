/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracbank.BulkEmailSender.configuration;


import com.bracbank.BulkEmailSender.user_auth.model.UserInfo;
import com.bracbank.BulkEmailSender.user_auth.service.SessionService;
import com.bracbank.BulkEmailSender.utils.AppUtil;
import com.bracbank.BulkEmailSender.utils.KEY;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author sarker
 */
public class AppProperty {
    
    @Autowired
    AppUtil util;
    
    @Autowired
    SessionService sessionService;
    
    @ModelAttribute
    public void addCommonObjects(Model model, HttpServletRequest request, HttpServletResponse resp,  HttpSession httpSession){
        model.addAttribute("BASE_URL", request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/");
        model.addAttribute("STATIC_RES", request.getContextPath() + "/res");
        model.addAttribute("ANGULAR", request.getContextPath() + "/res/angular");
        model.addAttribute("NG_SRC", request.getContextPath() + "/view");
        model.addAttribute("APP", request.getContextPath());
        model.addAttribute("APP_NAME", util.getAppName());
        model.addAttribute("APP_FULL_NAME", util.getAppFullName());
        model.addAttribute("APP_VERSION", "1.0.0" );
        model.addAttribute("SCRIPT_VERSION", "1.0.0" ); // should be app version. please change before release
        
        UserInfo ui = sessionService.getUser();
        if( ui != null ){
            model.addAttribute(KEY.USER, ui);
            model.addAttribute("ROLE_CODE", ui.getRole_code());
            model.addAttribute("ROLE_NAME", ui.getRole_name());
            model.addAttribute("USER_ID", ui.getUser_id());
            model.addAttribute("USER_CODE", ui.getUser_code());
        }
        
    }
}
