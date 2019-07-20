package com.bracbank.BulkEmailSender.campaign.service;

import com.bracbank.BulkEmailSender.campaign.model.Campaign;
import com.bracbank.BulkEmailSender.campaign.repo.CampaignRepo;
import com.bracbank.BulkEmailSender.user_auth.service.SessionService;
import com.bracbank.BulkEmailSender.utils.AppUtil;
import com.bracbank.BulkEmailSender.utils.ModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {

    @Autowired
    CampaignRepo campaignRepo;

    @Autowired
    ModelValidator modelValidator;

    @Autowired
    SessionService sessionService;

    @Autowired
    AppUtil appUtil;

    public List<Campaign> findAll() {
        return campaignRepo.findAll();
    }

    public Campaign findById(long id){
        return campaignRepo.findById(id);
    }
    public boolean save(Campaign campaign) {
        return campaignRepo.save(campaign);
    }

    public boolean update(Campaign campaign) {

        return campaignRepo.update(campaign);
    }

}
