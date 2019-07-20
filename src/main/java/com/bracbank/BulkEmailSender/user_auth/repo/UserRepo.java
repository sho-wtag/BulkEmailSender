/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracbank.BulkEmailSender.user_auth.repo;

import com.bracbank.BulkEmailSender.user_auth.service.SessionService;
import com.bracbank.BulkEmailSender.utils.AppUtil;
import com.bracbank.BulkEmailSender.utils.ModelRepo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bracbank.BulkEmailSender.user_auth.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo implements ModelRepo<UserInfo>{
    @Autowired
    NamedParameterJdbcTemplate nDb;
    
    @Autowired
    JdbcTemplate db;
    
    @Autowired
    SessionService sessionService;
    
    @Autowired
    AppUtil appUtil;
    
    
    public List<UserInfo> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ui.user_id, ui.first_name, ui.last_name, ui.user_code, ui.email, ui.phone, ui.address, ui.country_id, ui.active, sr.role_name ");
        sql.append(" FROM user_info ui ");
        sql.append(" JOIN user_role ur ON (ui.user_id=ur.user_id) ");
        sql.append(" JOIN system_role sr ON (ur.role_id=sr.role_id) ");
        sql.append(" where ui.active=1 ");
        sql.append(" order by ui.user_id, ui.user_code ");
        
        Map<String, Object> params = new HashMap<>();
        return nDb.query(sql.toString(), params, new BeanPropertyRowMapper(UserInfo.class));
    }
    
    public List<UserInfo> userList() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ui.user_id, ui.first_name, ui.last_name, ui.user_code, ui.email, ui.phone, ui.address, ui.country_id, ui.active, sr.role_name ");
        sql.append(" FROM user_info ui ");
        sql.append(" JOIN user_role ur ON (ui.user_id=ur.user_id) ");
        sql.append(" JOIN system_role sr ON (ur.role_id=sr.role_id) ");
        sql.append(" where ui.active=1 ");
        sql.append(" order by ui.user_id, ui.user_code ");
        
        Map<String, Object> params = new HashMap<>();
        return nDb.query(sql.toString(), params, new BeanPropertyRowMapper(UserInfo.class));
    }

    @Override
    public List<UserInfo> findAllByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserInfo findById(long id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ui.user_id, ui.first_name, ui.last_name, ui.user_code, ui.email, ui.phone, ui.address, ui.country_id, ui.active, sr.role_name, sr.role_id  ");
        sql.append(" FROM user_info ui  ");
        sql.append(" join user_role ur on ui.user_id = ur.user_id ");
        sql.append(" join system_role sr on sr.role_id=ur.role_id and sr.active=1 ");
        sql.append(" WHERE ui.user_id=:user_id ");
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", id);
        return (UserInfo) nDb.queryForObject(sql.toString(), params, new BeanPropertyRowMapper(UserInfo.class));
    }
    
    public UserInfo details(long user_id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ui.user_id, ui.first_name, ui.last_name, ui.user_code, ui.email, ui.phone, ui.address, ui.country_id, ui.active, sr.role_name, sr.role_id  ");
        sql.append(" FROM user_info ui  ");
        sql.append(" join user_role ur on ui.user_id = ur.user_id ");
        sql.append(" join system_role sr on sr.role_id=ur.role_id and sr.active=1 ");
        sql.append(" where ui.user_id=:user_id and ui.active=1 ");

        Map<String, Object> params = new HashMap<>();
        params.put("user_id", user_id);
        return (UserInfo) nDb.queryForObject(sql.toString(), params, new BeanPropertyRowMapper(UserInfo.class));
    }
    
    @Override
    public UserInfo findByCode(String code) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT user_id, first_name, last_name, user_code, email, phone, address, country_id, active ");
        sql.append(" FROM user_info where user_code=:user_code order by sl ");
        Map<String, Object> params = new HashMap<>();
        params.put("user_code", code);
        return (UserInfo) nDb.queryForObject(sql.toString(), params, new BeanPropertyRowMapper(UserInfo.class));
    }

    @Override
    public boolean save(UserInfo model) {
        throw new UnsupportedOperationException("Not supported yet.");      
    }
    
    public UserInfo saveUser(UserInfo model) {
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO user_info( ");
        sql.append(" user_id, first_name, last_name, user_code, email, password, phone, address, country_id, ");
        sql.append(" created_by, created_on) ");
        sql.append(" VALUES ( ");
        sql.append(" :user_id, :first_name, :last_name, :user_code, :email, :password, :phone, :address, :country_id, ");
        sql.append(" :created_by, now()) ");
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(model);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        nDb.update(sql.toString(), namedParameters, keyHolder);
        
        model.setUser_id( keyHolder.getKey().longValue() ); 
        
        return model;          
    }

    @Override
    public boolean update(UserInfo model) {

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE user_info SET  ");
        sql.append(" first_name=:first_name, ");
        sql.append(" last_name=:last_name, ");
        
        sql.append(" email=:email, ");
        if( model.getPassword() != null && !model.getPassword().isEmpty() ){
            sql.append(" password=:password, ");
        }
        
        sql.append(" phone=:phone, ");
        sql.append(" address=:address, ");
        
        sql.append(" updated_by=:updated_by, ");
        sql.append(" updated_on=:updated_on, ");
        sql.append(" active=:active ");
        sql.append(" WHERE user_id=:user_id; ");
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(model);
        return nDb.update(sql.toString(), namedParameters) == 1;
    }
    
    public boolean changePassword(long user_id, Map<String, Object> model) {
        
        StringBuilder sql = new StringBuilder();
        sql.append(" update user_info set ");
        sql.append(" password=? ");
        sql.append(" where user_id=? and password=? ");
        
        return db.update(sql.toString(), String.valueOf(model.get("new_pwd")), user_id, String.valueOf(model.get("pwd")) ) == 1;
    }


    public long mapUserRole(long user_id, long role_id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO user_role (user_id, role_id) ");
        sql.append(" VALUES (:user_id, :role_id ) ");
        sql.append(" ON DUPLICATE KEY UPDATE ");
        sql.append(" role_id=:role_id ");
        
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_id", user_id );
        paramMap.put("role_id", role_id );
        nDb.update(sql.toString(), paramMap);
        return role_id;
    }
    

    @Override
    public boolean exist(UserInfo model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public class SystemRoleRowMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserInfo model = new UserInfo();
            return model;
        }
    }
    
    
    public Map<String,Object> checkUserEmail(String email) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select count(ui.user_id) cnt from user_info ui ");
        sql.append(" where ui.email=:email ");
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        return nDb.queryForMap(sql.toString(), params);
    }
}
