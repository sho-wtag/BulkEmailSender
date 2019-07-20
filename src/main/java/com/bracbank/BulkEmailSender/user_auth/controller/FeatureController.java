package com.bracbank.BulkEmailSender.user_auth.controller;

import com.bracbank.BulkEmailSender.configuration.AppProperty;
import com.bracbank.BulkEmailSender.configuration.AppResponse;
import com.bracbank.BulkEmailSender.user_auth.model.Feature;
import com.bracbank.BulkEmailSender.user_auth.repo.FeatureRepo;
import com.bracbank.BulkEmailSender.user_auth.service.AuthService;
import com.bracbank.BulkEmailSender.user_auth.service.SessionService;
import com.bracbank.BulkEmailSender.utils.AppUtil;
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

@RestController
@RequestMapping("user_auth/feature")
public class FeatureController extends AppProperty{

    @Autowired
    FeatureRepo featureRepo;

    @Autowired
    ModelValidator modelValidator;

    @Autowired
    AuthService authService;
    
    @Autowired
    SessionService sessionService;
    
    @Autowired
    AppUtil util;
    
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView view() {
        ModelAndView modelAndView = new ModelAndView("user_auth/view");
        return modelAndView;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public AppResponse<Object> getAll() {
        List<Map<String, Object>> features = featureRepo.findAllForList();
        if (!features.isEmpty()) {
            return AppResponse.build(HttpStatus.OK).body(features);
        } else {
            return AppResponse.build(HttpStatus.NO_CONTENT).message("No feature found");
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AppResponse<Feature> get(@PathVariable("id") long id) {
        Feature feature = featureRepo.findById(id);
        if (feature != null) {
            return AppResponse.build(HttpStatus.OK).body(feature);
        } else {
            return AppResponse.build(HttpStatus.NOT_FOUND).message(" Feature not found");
        }

    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AppResponse<Feature> save(@RequestBody Feature model) {
        model.setFeature_id(0L);
        model.setCreated_by(sessionService.getUserId());
        model.setUpdated_by(sessionService.getUserId());
        model.setVersion_no(util.getMaxVersion());
        
        try {
            if (modelValidator.isValid(model)) {
                if (featureRepo.save(model)) {
                    authService.loadAllFeature();
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

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public AppResponse<Feature> update(@PathVariable("id") long id, @RequestBody Feature model) {

        try {
            model.setCreated_by(sessionService.getUserId());
            model.setUpdated_by(sessionService.getUserId());
            model.setVersion_no(util.getMaxVersion());
        
            if (modelValidator.isValid(model)) {
                if (featureRepo.update(model)) {
                    authService.loadAllFeature();
                    return AppResponse.build(HttpStatus.OK).body(featureRepo.findById(model.getFeature_id()));
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
