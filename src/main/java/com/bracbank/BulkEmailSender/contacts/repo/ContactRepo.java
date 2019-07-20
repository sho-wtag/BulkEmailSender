/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracbank.BulkEmailSender.contacts.repo;


import com.bracbank.BulkEmailSender.user_auth.service.SessionService;
import com.bracbank.BulkEmailSender.utils.AppUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mdshahadat.sarker
 */

@Repository
public class ContactRepo {
    
    @Autowired
    JdbcTemplate jdb;
    
    @Autowired
    NamedParameterJdbcTemplate db;
    
    @Autowired
    SessionService sessionService;
    
    public List<Map<String, Object>> rowsData( Map<String, Object> params) {
        String firstName = AppUtil.toString(params.get("first_name"));
        String lastName = AppUtil.toString(params.get("last_name"));
        String email = AppUtil.toString(params.get("email"));
        StringBuilder sql = new StringBuilder();
        
        sql.append(" select top (:limit) s.contact_id, s.cid, s.first_name, s.last_name, s.email, s.active from ( ");
        sql.append(" SELECT ROW_NUMBER() OVER (order by first_name asc ) AS [ROW_NUMBER], contact_id, cid, first_name, last_name, email, active from contacts   ");
        
        sql.append(" where 1=1 ");
        
        if( !firstName.trim().equals("") ){
            sql.append(" AND lower(first_name) like :first_name ");
            params.put("first_name", "%" + firstName.trim().toLowerCase() +"%" );
        }
        
        if( !lastName.trim().equals("") ){
            sql.append(" AND lower(last_name) like :last_name ");
            params.put("last_name", "%" + lastName.trim().toLowerCase() +"%" );
        }
        
        if( !email.trim().equals("") ){
            sql.append(" AND lower(email) like :email ");
            params.put("email", "%" + email.trim().toLowerCase() +"%" );
        }
        
        sql.append(" ) as s  ");
        sql.append(" WHERE [ROW_NUMBER]>:offset ");
        
        
        
        System.out.println("sql" + sql.toString());
        return db.queryForList(sql.toString(), params);
    }
    
    public Long count(Map<String, Object> params){
        StringBuilder sql = new StringBuilder();
        String firstName = AppUtil.toString(params.get("first_name"));
        String lastName = AppUtil.toString(params.get("last_name"));
        String email = AppUtil.toString(params.get("email"));
        
        sql.append(" SELECT count(s.contact_id) cnt ");
        sql.append(" FROM contacts s ");
        sql.append(" where 1=1 ");
        
        if( !firstName.trim().equals("") ){
            sql.append(" AND lower(first_name) like :first_name ");
            params.put("first_name", "%" + firstName.trim().toLowerCase() +"%" );
        }
        
        if( !lastName.trim().equals("") ){
            sql.append(" AND lower(last_name) like :last_name ");
            params.put("last_name", "%" + lastName.trim().toLowerCase() +"%" );
        }
        
        if( !email.trim().equals("") ){
            sql.append(" AND lower(email) like :email ");
            params.put("email", "%" + email.trim().toLowerCase() +"%" );
        }
        
        return db.queryForObject(sql.toString(), params, Long.class);
    }
    
    public Map<String, Object> findById(long id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT contact_id, cid, first_name, last_name, email, active ");
        sql.append(" FROM contacts where contact_id=:contact_id ");
        Map<String, Object> params = new HashMap<>();
        params.put("contact_id", id);
        return db.queryForMap(sql.toString(), params);
    }

    
    public boolean save(Map<String, Object> model) {
        model.put("user_id", sessionService.getUserId());
        
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO contacts(cid, first_name, last_name, email, active, user_id) ");
        sql.append(" VALUES (:cid, :first_name, :last_name, :email, 1, :user_id ) ");
        
        return db.update(sql.toString(), model)==1;          
    }

    
    public boolean update(Map<String, Object> model) {

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE contacts SET  ");
        sql.append(" first_name=:first_name, ");
        sql.append(" last_name=:last_name, ");
        sql.append(" email=:email, ");
        sql.append(" updated_on=getdate(), ");
        sql.append(" active=:active ");
        sql.append(" WHERE contact_id=:contact_id; ");
        
        return db.update(sql.toString(), model) == 1;
    }
    
}
