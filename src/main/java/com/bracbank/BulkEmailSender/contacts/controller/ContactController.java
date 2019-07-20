package com.bracbank.BulkEmailSender.contacts.controller;

import com.bracbank.BulkEmailSender.configuration.AppProperty;
import com.bracbank.BulkEmailSender.configuration.AppResponse;
import com.bracbank.BulkEmailSender.contacts.service.ContactService;
import com.bracbank.BulkEmailSender.user_auth.service.AuthService;
import com.bracbank.BulkEmailSender.user_auth.service.SessionService;
import com.bracbank.BulkEmailSender.utils.AppUtil;
import com.bracbank.BulkEmailSender.utils.KEY;
import com.bracbank.BulkEmailSender.utils.ModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/contacts/management")
public class ContactController extends AppProperty{

    @Autowired
    SessionService sessionService;

    @Autowired
    AuthService authService;

    @Autowired
    ModelValidator modelValidator;

    @Autowired
    AppUtil appUtil;

    @Autowired
    ContactService contactService;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView view() {
        appUtil.genToken();
        ModelAndView modelAndView = new ModelAndView("contacts/view");
        modelAndView.addObject(KEY.JSPVIEWKEY, appUtil.getToken());
        return modelAndView;
    }

    @RequestMapping(value = "/filter/{currentPage}/{itemPerPage}", method = RequestMethod.POST)
    public AppResponse<Object> filter(
            @PathVariable("currentPage") int currentPage, 
            @PathVariable("itemPerPage") int itemPerPage, 
            @RequestBody Map<String, Object> params) {
        try {
            return AppResponse.build(HttpStatus.OK).body(contactService.filter(currentPage, itemPerPage, params));
        } catch (Exception ex) {
            return AppResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message(ex.getMessage());
        }
    }
    
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AppResponse<Object> get(@PathVariable("id") long id) {
        Map<String, Object> model = contactService.findById(id);
        if (model != null) {
            return AppResponse.build(HttpStatus.OK).body(model);
        } else {
            return AppResponse.build(HttpStatus.NOT_FOUND).message("Segment not found");
        }

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AppResponse<Object> save(@RequestBody Map<String, Object> model) {
        try {
            if (contactService.save(model)) {
                return AppResponse.build(HttpStatus.OK).body(model);
            } else {
                return AppResponse.build(HttpStatus.EXPECTATION_FAILED).message("Not created");
            }
        } catch (Exception ex) {
            return AppResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message(ex.getMessage());
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public AppResponse<Object> update(@PathVariable("id") long id, @RequestBody Map<String, Object> model) {

        try {
            if (contactService.update(model)) {
                return AppResponse.build(HttpStatus.OK).body("Success");
            } else {
                return AppResponse.build(HttpStatus.NOT_MODIFIED).message("Not updated");
            }
        } catch (Exception ex) {
            return AppResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message(ex.getMessage());
        }
    }
}
