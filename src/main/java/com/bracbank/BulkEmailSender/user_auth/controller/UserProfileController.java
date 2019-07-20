package com.bracbank.BulkEmailSender.user_auth.controller;

import com.bracbank.BulkEmailSender.configuration.AppProperty;
import com.bracbank.BulkEmailSender.configuration.AppResponse;
import com.bracbank.BulkEmailSender.user_auth.model.UserInfo;
import com.bracbank.BulkEmailSender.user_auth.repo.AuthRepo;
import com.bracbank.BulkEmailSender.user_auth.repo.UserRepo;
import com.bracbank.BulkEmailSender.user_auth.service.SessionService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("user_auth/profile")
public class UserProfileController extends AppProperty{
    
    @Autowired
    private AuthRepo authRepo;
    
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    SessionService sessionService;
    
    
    
    @RequestMapping(value="/view", method = RequestMethod.GET)
    public ModelAndView view() {
        ModelAndView modelAndView = new ModelAndView("user_auth/view");
        return modelAndView;
    }
    
    
    @RequestMapping(value = "/show", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AppResponse<UserInfo> show() {
        UserInfo model = userRepo.details( sessionService.getUserId() );
        if (model != null) {
            return AppResponse.build(HttpStatus.OK).body(model);
        } else {
            return AppResponse.build(HttpStatus.NOT_FOUND).message(" Details not found");
        }

    }
    
    @RequestMapping(value = "/change-password", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public AppResponse<UserInfo> changePassword(@RequestBody Map<String, Object> data) {
        
        long user_id = sessionService.getUserId();
        
        try {
            
            if( userRepo.changePassword(user_id, data) ){
                
                return AppResponse.build(HttpStatus.OK).body(1);
            } else{
                return AppResponse.build(HttpStatus.OK).body(0);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return AppResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message(ex.getMessage());
        }
        
    }
    
}
