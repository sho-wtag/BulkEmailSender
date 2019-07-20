/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracbank.BulkEmailSender.user_auth.service;


import com.bracbank.BulkEmailSender.configuration.AppResponse;
import com.bracbank.BulkEmailSender.user_auth.model.UserInfo;
import com.bracbank.BulkEmailSender.user_auth.repo.UserRepo;
import com.bracbank.BulkEmailSender.utils.AppUtil;
import com.bracbank.BulkEmailSender.utils.ModelValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sarker
 */
@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelValidator modelValidator;

    @Autowired
    SessionService sessionService;
    
    @Autowired
    AppUtil appUtil;
    
    public List<UserInfo> findAll() {
        return userRepo.findAll();
    }
    
    public List<UserInfo> userList() {
        return userRepo.userList();
    }

    public List<UserInfo> findAllByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public UserInfo findById(long id) {
        return userRepo.findById(id);
    }

    public UserInfo findByCode(String code) {
        return userRepo.findByCode(code);
    }

    @Transactional
    public AppResponse<UserInfo> saveUser(UserInfo model) {

        try {
            model.setCreated_by(sessionService.getUserId());
            
            model.setUser_id(0);
            if (modelValidator.isValid(model)) {

                //String plainPassword = AppUtil.generatePassword(8);
                //model.setPassword(AppUtil.SHA256(plainPassword));

                if (userRepo.saveUser(model).getUser_id() > 0) {
                    userRepo.mapUserRole(model.getUser_id(), model.getRole_id());

                    return AppResponse.build(HttpStatus.OK).body(userRepo.findById(model.getUser_id()));
                } else {
                    return AppResponse.build(HttpStatus.EXPECTATION_FAILED).message("Not created");
                }
            } else {
                return AppResponse.build(HttpStatus.NOT_ACCEPTABLE).message(modelValidator.validationMessage(model));
            }
        } catch (Exception ex) {
            return AppResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message(ex.getMessage());
        }
    }

    @Transactional
    public AppResponse<UserInfo> updateUser(UserInfo model) {

        try {
            model.setUpdated_by(sessionService.getUserId());
            
            if (modelValidator.isValid(model)) {
                if (userRepo.update(model)) {
                    userRepo.mapUserRole(model.getUser_id(), model.getRole_id());

                    return AppResponse.build(HttpStatus.OK).body(userRepo.findById(model.getUser_id()));
                } else {
                    return AppResponse.build(HttpStatus.NOT_MODIFIED).message("Not updated");
                }
            } else {
                return AppResponse.build(HttpStatus.NOT_ACCEPTABLE).message(modelValidator.validationMessage(model));
            }
        } catch (Exception ex) {
            return AppResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message(ex.getMessage());
        }
    }
}
