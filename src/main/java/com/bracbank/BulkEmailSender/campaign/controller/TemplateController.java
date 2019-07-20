package com.bracbank.BulkEmailSender.campaign.controller;

import com.bracbank.BulkEmailSender.campaign.model.Template;
import com.bracbank.BulkEmailSender.campaign.service.TemplateService;
import com.bracbank.BulkEmailSender.configuration.AppProperty;
import com.bracbank.BulkEmailSender.configuration.AppResponse;
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

import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping("campaign/template")
public class TemplateController extends AppProperty {

    @Autowired
    SessionService sessionService;

    @Autowired
    ModelValidator modelValidator;

    @Autowired
    AuthService authService;

    @Autowired
    AppUtil appUtil;

    @Autowired
    TemplateService templateService;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView view() throws  Exception{
        appUtil.genToken();
        //System.out.println( URLDecoder.decode("%3Cp%3EPage%20Title%3C/p%3E%0A%0A%3Ch1%3EThis%20is%20a%20Heading%3C/h1%3E%0A%0A%3Cp%3EThis%20is%20a%20paragraph.%3C/p%3E%0A", "UTF-8") );
        ModelAndView modelAndView = new ModelAndView("campaign/view");
        modelAndView.addObject(KEY.JSPVIEWKEY, appUtil.genToken());
        return modelAndView;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public AppResponse<List<Template>> getAll() {
        List<Template> list = templateService.findAll();
        if (!list.isEmpty()) {
            return AppResponse.build(HttpStatus.OK).body(list);
        } else {
            return AppResponse.build(HttpStatus.NO_CONTENT).message("No template configuration found");
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AppResponse<Template> get(@PathVariable("id") long id) {
        Template model = templateService.findById(id);
        if (model != null) {
            return AppResponse.build(HttpStatus.OK).body(model);
        } else {
            return AppResponse.build(HttpStatus.NOT_FOUND).message("template settings not found");
        }

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AppResponse<Template> save(@RequestBody Template template) {
        template.setTemplates_id(0L);
        try {
            if (modelValidator.isValid(template)) {
                if (templateService.save(template)) {
                    return AppResponse.build(HttpStatus.OK).body(template);
                } else {
                    return AppResponse.build(HttpStatus.EXPECTATION_FAILED).message("Not created");
                }
            } else {
                return AppResponse.build(HttpStatus.NOT_ACCEPTABLE).message(modelValidator.validationMessage(template));
            }
        } catch (Exception ex) {
            return AppResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message(ex.getMessage());
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public AppResponse<Template> update(@RequestBody Template template) {

        try {
            if (modelValidator.isValid(template)) {
                if (templateService.save(template)) {
                    return AppResponse.build(HttpStatus.OK).body("Successfully updated");
                } else {
                    return AppResponse.build(HttpStatus.NOT_MODIFIED).message("Not updated");
                }
            } else {
                return AppResponse.build(HttpStatus.NOT_ACCEPTABLE).message(modelValidator.validationMessage(template));
            }
        } catch (Exception ex) {
            return AppResponse.build(HttpStatus.INTERNAL_SERVER_ERROR).message(ex.getMessage());
        }
    }

}
