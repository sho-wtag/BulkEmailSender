/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracbank.BulkEmailSender.contacts.service;

import com.bracbank.BulkEmailSender.contacts.repo.ContactRepo;
import com.bracbank.BulkEmailSender.user_auth.service.SessionService;
import com.bracbank.BulkEmailSender.utils.AppUtil;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mdshahadat.sarker
 */
@Service
public class ContactService {

    @Autowired
    SessionService sessionService;

    @Autowired
    AppUtil util;

    @Autowired
    ContactRepo contactRepo;

    int index = 0;

    public Map<String, Object> filter(int currentPage, int itemPerPage, Map<String, Object> request) throws Exception {

        request.put("offset", (currentPage - 1) * itemPerPage);
        request.put("limit", itemPerPage);

        Map<String, Object> data = new HashMap<>();
        data.put("itemCount", contactRepo.count(request));
        data.put("items", contactRepo.rowsData(request));

        return data;
    }
    
    public Map<String, Object> findById(long id){
        return contactRepo.findById(id);
    }
    
    public boolean save(Map<String, Object> model) throws Exception {
        return contactRepo.save(model);
    }
    
    public boolean update(Map<String, Object> model) throws Exception {
        return contactRepo.update(model);
    }

}
