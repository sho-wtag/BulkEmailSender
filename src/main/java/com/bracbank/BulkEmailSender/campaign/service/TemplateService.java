package com.bracbank.BulkEmailSender.campaign.service;

import com.bracbank.BulkEmailSender.campaign.model.Template;
import com.bracbank.BulkEmailSender.campaign.repo.TemplateRepo;
import com.bracbank.BulkEmailSender.user_auth.service.SessionService;
import com.bracbank.BulkEmailSender.utils.AppUtil;
import com.bracbank.BulkEmailSender.utils.ModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService {

    @Autowired
    TemplateRepo templateRepo;

    @Autowired
    ModelValidator modelValidator;

    @Autowired
    SessionService sessionService;

    @Autowired
    AppUtil appUtil;

    public List<Template> findAll() {
        return templateRepo.findAll();
    }

    public Template findById(long id){
        return templateRepo.findById(id);
    }
    public boolean save(Template template) {
        return templateRepo.save(template);
    }

    public boolean update(Template template) {

        return templateRepo.update(template);
    }

}
