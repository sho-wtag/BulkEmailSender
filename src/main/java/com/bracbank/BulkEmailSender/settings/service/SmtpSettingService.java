package com.bracbank.BulkEmailSender.settings.service;

import com.bracbank.BulkEmailSender.settings.model.SmtpSetting;
import com.bracbank.BulkEmailSender.settings.repo.SmtpSettingRepo;
import com.bracbank.BulkEmailSender.user_auth.service.SessionService;
import com.bracbank.BulkEmailSender.utils.AppUtil;
import com.bracbank.BulkEmailSender.utils.ModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmtpSettingService {

    @Autowired
    SmtpSettingRepo smtpSettingRepo;

    @Autowired
    ModelValidator modelValidator;

    @Autowired
    SessionService sessionService;

    @Autowired
    AppUtil appUtil;

    public List<SmtpSetting> findAll() {
        return smtpSettingRepo.findAll();
    }

//    public List<SmtpSetting> findAllActice() {
//        return smtpSettingRepo.findAllActice();
//    }

    public List<SmtpSetting> findAllByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public SmtpSetting findById(long id) {
        return smtpSettingRepo.findById(id);
    }

//    public SmtpSetting findByCode(String code) {
//        return smtpSettingRepo.findByCode(code);
//    }

    public boolean save(SmtpSetting smtpSetting) {
        return smtpSettingRepo.save(smtpSetting);
    }

    public boolean update(SmtpSetting smtpSetting) {
        smtpSetting.setSmtp_hostname(smtpSetting.getSmtp_hostname());
        smtpSetting.setSmtp_port(smtpSetting.getSmtp_port());
        return smtpSettingRepo.update(smtpSetting);
    }
}
