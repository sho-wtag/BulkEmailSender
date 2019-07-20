/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracbank.BulkEmailSender.user_auth.controller;

import com.bracbank.BulkEmailSender.configuration.AppProperty;
import com.bracbank.BulkEmailSender.configuration.AppResponse;
import com.bracbank.BulkEmailSender.user_auth.model.Feature;
import com.bracbank.BulkEmailSender.user_auth.model.UserInfo;
import com.bracbank.BulkEmailSender.user_auth.repo.AuthRepo;
import com.bracbank.BulkEmailSender.user_auth.repo.FeatureRepo;
import com.bracbank.BulkEmailSender.user_auth.service.AuthService;
import com.bracbank.BulkEmailSender.user_auth.service.SessionService;
import com.bracbank.BulkEmailSender.utils.KEY;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sarker
 */
@RestController
public class AuthController extends AppProperty {

    @Autowired
    HttpSession session;

    @Autowired
    AuthRepo authRepo;

    @Autowired
    FeatureRepo featureRepo;

    @Autowired
    AuthService authService;

    @Autowired
    SessionService sessionService;

    @RequestMapping(value = {"/"}, method = {RequestMethod.GET})
    public ModelAndView index() {
        return new ModelAndView("redirect:/auth/login");
    }

    @RequestMapping(value = {"/auth/version"}, method = {RequestMethod.GET})
    public ModelAndView version() {
        return new ModelAndView("version");
    }

    @RequestMapping(value = {"/auth/login"}, method = {RequestMethod.GET})
    public ModelAndView login() {
        UserInfo userInfo = sessionService.getUser();
        if (userInfo != null) {
            return new ModelAndView("redirect:/auth/launcher");
        } else {
            return new ModelAndView("login_page");
        }
    }

    @RequestMapping(value = {"/auth/launcher"}, method = {RequestMethod.GET})
    public ModelAndView launcher() {
        UserInfo userInfo = sessionService.getUser();
        if (userInfo != null) {
            return new ModelAndView("launcher_screen").addObject(KEY.USER, userInfo);
        } else {
            return new ModelAndView("redirect:/auth/login");
        }
    }

    @RequestMapping(value = {"/auth/load"}, method = {RequestMethod.GET})
    public String load() {
        authService.loadAllFeature();
        return "OK";
    }

    @RequestMapping(value = "/auth/mydata", method = RequestMethod.GET)
    public AppResponse<UserInfo> mydata() {
        UserInfo user = sessionService.getUser();
        if (user != null) {
            user.setMenu(authService.getMenu(user.getRole_name()));

            user.setFeatures(new HashMap<>());
            List<Feature> features = authService.getFeaturs(user.getRole_name());
            features.stream().forEach((feature) -> {
                if (feature.getType().equalsIgnoreCase("Feature")) {
                    user.getFeatures().put(feature.getModule().trim() + "##" + feature.getComponent().trim(), feature);
                } else {
                    user.getFeatures().put(feature.getFeature_id().toString(), feature);
                }
                if (feature.isIs_home()) {
                    user.setDefault_feature(featureRepo.findById(feature.getFeature_id()));
                }
            });

            return AppResponse.build(HttpStatus.OK).body(user);
        } else {
            return AppResponse.build(HttpStatus.UNAUTHORIZED).message("UserNot found");
        }
    }

    

    @RequestMapping(value = {"/auth/login"}, method = {RequestMethod.POST})
    public ModelAndView doLogin(@RequestParam("email") String email, @RequestParam("password") String password) {
        
        Calendar toDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance(); // date of expiration, 2 months
        maxDate.set(2019, 7, 1); // Aug 01, 2019
        
        Calendar minDate = Calendar.getInstance();
        minDate.set(2019, 5, 1); // May 1, 2019
        
        
        if( minDate.getTimeInMillis() >= toDate.getTimeInMillis() || toDate.getTimeInMillis() >= maxDate.getTimeInMillis() ){
            return new ModelAndView("login_page").addObject("status", "Demo version has been expired!!!");
        }
        
        try {
            UserInfo user = authRepo.findUser(email, password);
            if (user != null) {
                if (!user.getRole_name().equalsIgnoreCase("Setup Master")) {
                    session.setAttribute(KEY.USER, user);
                }
                session.setAttribute(KEY.USER, user);
                return new ModelAndView("redirect:/auth/launcher");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("login_page").addObject("status", "Invalid username/password");
    }

    @RequestMapping(value = {"/auth/forget-password"}, method = {RequestMethod.POST})
    public ModelAndView forgetPassword(@RequestParam("email") String email) {

        try {
            //emailService.sendHtmlEmail(email, "Forget password instructions for smartbilling", "this is test");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("login_page").addObject("status", "Forget passowrd instruction has been sent.");
    }

    @RequestMapping(value = {"/auth/logout"}, method = {RequestMethod.GET})
    public ModelAndView logout() {
        session.invalidate();
        return new ModelAndView("redirect:/auth/login");
    }

}
