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

@Repository
public class SegmentRepo {

    @Autowired
    NamedParameterJdbcTemplate db;

    @Autowired
    JdbcTemplate jdb;

    @Autowired
    AppUtil util;

    @Autowired
    SessionService sessionService;
    
    public List<Map<String, Object>> rowsData( Map<String, Object> params) {
        String name = AppUtil.toString(params.get("name"));
        StringBuilder sql = new StringBuilder();
        
        sql.append(" select top (:limit) s.segment_id, s.name, s.description, s.active from ( ");
        sql.append(" SELECT ROW_NUMBER() OVER (order by name asc ) AS [ROW_NUMBER], segment_id, name, description, active from segments   ");
        
        sql.append(" where 1=1 ");
        
        if( !name.trim().equals("") ){
            sql.append(" AND lower(name) like :name ");
            params.put("name", "%" + name.trim().toLowerCase() +"%" );
        }
        
        sql.append(" ) as s  ");
        sql.append(" WHERE [ROW_NUMBER]>:offset ");
        
        
        
        System.out.println("sql" + sql.toString());
        return db.queryForList(sql.toString(), params);
    }
    
    public Long count(Map<String, Object> params){
        StringBuilder sql = new StringBuilder();
        String name = AppUtil.toString(params.get("name"));
        
        sql.append(" SELECT count(s.segment_id) cnt ");
        sql.append(" FROM segments s ");
        sql.append(" where 1=1 ");
        
        if( !name.trim().equals("") ){
            sql.append(" AND lower(name) like :name ");
            params.put("name", "%" + name.trim().toLowerCase() +"%" );
        }
        
        return db.queryForObject(sql.toString(), params, Long.class);
    }
    
    public Map<String, Object> findById(long id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT segment_id, name, description, active ");
        sql.append(" FROM segments where segment_id=:segment_id ");
        Map<String, Object> params = new HashMap<>();
        params.put("segment_id", id);
        return db.queryForMap(sql.toString(), params);
    }

    
    public boolean save(Map<String, Object> model) {
        model.put("user_id", sessionService.getUserId());
        
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO segments(name, description, active, user_id) ");
        sql.append(" VALUES ( :name, :description, 1, :user_id ) ");
        
        return db.update(sql.toString(), model)==1;          
    }

    
    public boolean update(Map<String, Object> model) {

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE segments SET  ");
        sql.append(" name=:name, ");
        sql.append(" description=:description, ");
        sql.append(" updated_on=getdate(), ");
        sql.append(" active=:active ");
        sql.append(" WHERE segment_id=:segment_id; ");
        
        return db.update(sql.toString(), model) == 1;
    }
}
