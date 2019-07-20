/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracbank.BulkEmailSender.user_auth.service;

import com.bracbank.BulkEmailSender.user_auth.model.Feature;
import com.bracbank.BulkEmailSender.user_auth.repo.FeatureRepo;
import com.bracbank.BulkEmailSender.user_auth.repo.RoleRepo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bracbank.BulkEmailSender.user_auth.model.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

/**
 *
 * @author juba
 */
@Service
public class AuthService implements ApplicationRunner {

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    FeatureRepo featureRepo;

    private final Map<String, Feature> URL_FEATURES = new HashMap<String, Feature>();

    public void loadAllFeature() {
        List<Feature> features = featureRepo.findAllWithRole();
        features.stream().forEach((feature) -> {
            if (feature.getType().equals("Feature")) {
                String url = "/" + feature.getModule() + "/" + feature.getController() + "/" + feature.getAction();
                URL_FEATURES.put(url, feature);
            }
        });
    }

    public void prepareMenu(List<Feature> features, Feature parent, String roleName) {
        if (features.size() == 0) {
            return;
        }
        if (parent.getFeature_id() != null) {
            List<Feature> f = features.stream().filter(obj -> obj.getParent_id().equals(parent.getFeature_id())).collect(Collectors.toList());
            parent.setFeatures(f);
            features.removeAll(f);
            parent.getFeatures().stream().forEach((t) -> {
                prepareMenu(features, t, roleName);
            });
        } else {
            List<Feature> f = new ArrayList<>();
            for(Feature pf: features){
                if( pf.getParent_id() == null || pf.getParent_id() == 0L || pf.getParent_id() == 0 ){
                    f.add(pf);
                }
            }
            
            parent.setFeatures(f);
            features.removeAll(f);
            parent.getFeatures().stream().forEach((t) -> {
                prepareMenu(features, t, roleName);
            });
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadAllFeature();
    }

    public Feature getMenu(String role) {
        Feature parent = new Feature("MENU", "0000", "ROOT");
        List<Feature> features = featureRepo.findByRoleName(role);
        prepareMenu(new ArrayList<Feature>(features), parent, role);
        return parent;
    }

    public List<Feature> getFeaturs(String role) {
        return featureRepo.findByRoleName(role);
    }

}
