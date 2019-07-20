/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracbank.BulkEmailSender.user_auth.controller;

import com.bracbank.BulkEmailSender.configuration.AppProperty;
import com.bracbank.BulkEmailSender.configuration.AppResponse;
import com.bracbank.BulkEmailSender.user_auth.model.UserInfo;
import com.bracbank.BulkEmailSender.user_auth.repo.UserRepo;
import com.bracbank.BulkEmailSender.user_auth.service.UserService;
import com.bracbank.BulkEmailSender.utils.ModelValidator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sarker
 */
@RestController
@RequestMapping("user_auth/user")
public class UserController extends AppProperty {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelValidator modelValidator;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView view() {
        ModelAndView modelAndView = new ModelAndView("user_auth/view");
        return modelAndView;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public AppResponse<List<UserInfo>> getAll() {
        List<UserInfo> models = userRepo.findAll();
        if (!models.isEmpty()) {
            return AppResponse.build(HttpStatus.OK).body(models);
        } else {
            return AppResponse.build(HttpStatus.NO_CONTENT).message("No user found");
        }
    }
    
    @RequestMapping(value = "/user-list", method = RequestMethod.GET)
    public AppResponse<List<UserInfo>> getUserList() {
        List<UserInfo> models = userRepo.userList();
        if (!models.isEmpty()) {
            return AppResponse.build(HttpStatus.OK).body(models);
        } else {
            return AppResponse.build(HttpStatus.NO_CONTENT).message("No user found");
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AppResponse<UserInfo> get(@PathVariable("id") long id) {
        UserInfo model = userRepo.findById(id);
        if (model != null) {
            return AppResponse.build(HttpStatus.OK).body(model);
        } else {
            return AppResponse.build(HttpStatus.NOT_FOUND).message(" User not found");
        }

    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AppResponse<UserInfo> save(@RequestBody UserInfo model) {
        return userService.saveUser(model);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public AppResponse<UserInfo> update(@PathVariable("id") long id, @RequestBody UserInfo model) {
        try{
            
            return AppResponse.build(HttpStatus.OK).body(userService.updateUser(model));
        } catch(Exception ex){
            return AppResponse.build(HttpStatus.NO_CONTENT).message("No user updated");
        }
        
    }
    
    @RequestMapping(value = "/check-email", method = RequestMethod.PUT)
    public AppResponse<Object> checkEmail(@RequestBody Map<String, Object> model) {
        try{
            
            return AppResponse.build(HttpStatus.OK).body(userRepo.checkUserEmail(model.get("email").toString()));
        } catch(Exception ex){
            return AppResponse.build(HttpStatus.NO_CONTENT).message("No user found");
        }
        
    }
}
