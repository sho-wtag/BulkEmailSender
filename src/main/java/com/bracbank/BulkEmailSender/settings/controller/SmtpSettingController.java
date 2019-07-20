package com.bracbank.BulkEmailSender.settings.controller;

import com.bracbank.BulkEmailSender.configuration.AppProperty;
import com.bracbank.BulkEmailSender.configuration.AppResponse;
import com.bracbank.BulkEmailSender.settings.model.SmtpSetting;
import com.bracbank.BulkEmailSender.settings.service.SmtpSettingService;
import com.bracbank.BulkEmailSender.user_auth.service.AuthService;
import com.bracbank.BulkEmailSender.user_auth.service.SessionService;
import com.bracbank.BulkEmailSender.utils.AppUtil;
import com.bracbank.BulkEmailSender.utils.KEY;
import com.bracbank.BulkEmailSender.utils.ModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("settings/smtp")
public class SmtpSettingController extends AppProperty{

    @Autowired
    SessionService sessionService;

    @Autowired
    ModelValidator modelValidator;

    @Autowired
    AuthService authService;

    @Autowired
    AppUtil appUtil;
    
    @Autowired
    SmtpSettingService smtpSettingService;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView view() {
        appUtil.genToken();
        ModelAndView modelAndView = new ModelAndView("settings/view"); 
        modelAndView.addObject(KEY.JSPVIEWKEY, appUtil.genToken());
        return modelAndView;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public AppResponse<List<SmtpSetting>> getAll() {
        List<SmtpSetting> list = smtpSettingService.findAll();
        if (!list.isEmpty()) {
            return AppResponse.build(HttpStatus.OK).body(list);
        } else {
            return AppResponse.build(HttpStatus.NO_CONTENT).message("No smtp settings found");
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AppResponse<SmtpSetting> get(@PathVariable("id") long id) {
        SmtpSetting model = smtpSettingService.findById(id);
        if (model != null) {
            return AppResponse.build(HttpStatus.OK).body(model);
        } else {
            return AppResponse.build(HttpStatus.NOT_FOUND).message("smtp settings not found");
        }

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AppResponse<SmtpSetting> save(@RequestBody SmtpSetting model) {
       model.setId(0L);
        try {
            if (modelValidator.isValid(model)) {
                if (smtpSettingService.save(model)) {
                    return AppResponse.build(HttpStatus.OK).body(model);
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

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public AppResponse<SmtpSetting> update(@RequestBody SmtpSetting smtpSetting) {

        try {
            if (modelValidator.isValid(smtpSetting)) {
                if (smtpSettingService.save(smtpSetting)) {
                    return AppResponse.build(HttpStatus.OK).body("Successfully updated");
                } else {
                    return AppResponse.build(HttpStatus.NOT_MODIFIED).message("Not updated");
                }
            } else {
                return AppResponse.build(HttpStatus.NOT_ACCEPTABLE).message(modelValidator.validationMessage(smtpSetting));
            }
        } catch (Exception ex) {
            return AppResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message(ex.getMessage());
        }
    }


}
