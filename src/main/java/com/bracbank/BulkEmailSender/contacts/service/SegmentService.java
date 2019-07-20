package com.bracbank.BulkEmailSender.contacts.service;

import com.bracbank.BulkEmailSender.contacts.repo.SegmentRepo;
import com.bracbank.BulkEmailSender.user_auth.service.SessionService;
import com.bracbank.BulkEmailSender.utils.AppUtil;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SegmentService {
    @Autowired
    SessionService sessionService;

    @Autowired
    AppUtil util;

    @Autowired
    SegmentRepo segmentRepo;

    int index = 0;

    public Map<String, Object> filter(int currentPage, int itemPerPage, Map<String, Object> request) throws Exception {

        request.put("offset", (currentPage - 1) * itemPerPage);
        request.put("limit", itemPerPage);

        Map<String, Object> data = new HashMap<>();
        data.put("itemCount", segmentRepo.count(request));
        data.put("items", segmentRepo.rowsData(request));

        return data;
    }
    
    public Map<String, Object> findById(long id){
        return segmentRepo.findById(id);
    }
    
    public boolean save(Map<String, Object> model) throws Exception {
        return segmentRepo.save(model);
    }
    
    public boolean update(Map<String, Object> model) throws Exception {
        return segmentRepo.update(model);
    }
}
