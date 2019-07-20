/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracbank.BulkEmailSender.user_auth.repo;

import com.bracbank.BulkEmailSender.user_auth.model.Feature;
import com.bracbank.BulkEmailSender.user_auth.model.UserInfo;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepo {

    @Autowired
    NamedParameterJdbcTemplate db;
    
    

    public UserInfo findUser(String email, String password) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ui.user_id, ui.first_name, ui.last_name, ui.user_code, ui.email, ui.phone, ui.address, ui.country_id, ui.active, sr.role_name, sr.role_code  ");
        sql.append(" FROM user_info ui  ");
        sql.append(" join user_role ur on ui.user_id = ur.user_id ");
        sql.append(" join system_role sr on sr.role_id=ur.role_id and sr.active=1 ");
        sql.append(" where ui.email=:email and ui.password=:password and ui.active=1  ");
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        return (UserInfo) db.queryForObject(sql.toString(), params, new BeanPropertyRowMapper(UserInfo.class));
    }
    
    private Map<String, Feature> roleWiseFeature = new HashMap<String, Feature>();


}
