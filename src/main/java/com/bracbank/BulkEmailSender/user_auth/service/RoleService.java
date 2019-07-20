/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracbank.BulkEmailSender.user_auth.service;

import com.bracbank.BulkEmailSender.user_auth.repo.RoleRepo;
import com.bracbank.BulkEmailSender.utils.AppUtil;
import java.util.List;
import java.util.Map;

import com.bracbank.BulkEmailSender.user_auth.model.SystemRole;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sarker
 */
@Service
public class RoleService {

    @Autowired
    RoleRepo roleRepo;
    
    @Autowired
    SessionService sessionService;
    
    @Autowired
    AppUtil util;
    
    public List<SystemRole> findAll() {
        return roleRepo.findAll();
    }
    
    public List<SystemRole> findAllActice() {
        return roleRepo.findAllActice();
    }
    
    public List<SystemRole> findAllByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public SystemRole findById(long id) {
        return roleRepo.findById(id);
    }

    public SystemRole findByCode(String code) {
        return roleRepo.findByCode(code);
    }
    
    public boolean save(SystemRole model) {
        model.setCreated_by(sessionService.getUserId());
        model.setUpdated_by(sessionService.getUserId());
        model.setVersion_no(util.getMaxVersion());
        return roleRepo.save(model);
    }

    public boolean update(SystemRole model) {
        model.setCreated_by(sessionService.getUserId());
        model.setUpdated_by(sessionService.getUserId());
        model.setVersion_no(util.getMaxVersion());
        return roleRepo.update(model);
    }
    
    public List<Map<String, Object>> findRoleFeature(long roleId) {
        return roleRepo.findRoleFeature(roleId);
    }

    @Transactional
    public Object mapRoleFeatures(Map<String, Object> request) {

        JSONArray jSONArray = null;
        try {
            jSONArray = new JSONArray( request.get("features").toString() );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        long role_id = AppUtil.toLong(request.get("role_id"));
        long defaultFeature = AppUtil.toLong(request.get("default_home"));
        
        roleRepo.deleteRoleFeature(role_id);
        roleRepo.mapRoleFeature( role_id, jSONArray );
        roleRepo.updateHomeFeature(role_id, defaultFeature);
            
        return defaultFeature;
    }
}
