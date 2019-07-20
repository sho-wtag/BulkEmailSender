/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracbank.BulkEmailSender.user_auth.repo;


import com.bracbank.BulkEmailSender.user_auth.model.SystemRole;
import com.bracbank.BulkEmailSender.user_auth.service.SessionService;
import com.bracbank.BulkEmailSender.utils.AppUtil;
import com.bracbank.BulkEmailSender.utils.ModelRepo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepo implements ModelRepo<SystemRole>{
    @Autowired
    NamedParameterJdbcTemplate db;
    
    @Autowired
    JdbcTemplate jdb;
    
    @Autowired
    AppUtil util;
    
    @Autowired
    SessionService sessionService;
    
    @Override
    public List<SystemRole> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT role_id, role_name, role_code, note, active ");
        sql.append(" FROM system_role ");
        sql.append(" where role_name != 'SuperAdmin' and role_name != 'Super Admin' order by role_name ");
        return db.query(sql.toString(), new BeanPropertyRowMapper(SystemRole.class));
    }
    
    public List<SystemRole> managerRoles() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT role_id, role_name, role_code, note, active ");
        sql.append(" FROM system_role ");
        sql.append(" where role_name != 'SuperAdmin' and role_name != 'Super Admin' and role_name != 'Owner' and role_name != 'owner' order by role_name ");
        return db.query(sql.toString(), new BeanPropertyRowMapper(SystemRole.class));
    }
    
    public List<SystemRole> findAllActice() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT role_id, role_name, role_code, note, active ");
        sql.append(" FROM system_role where active =1 order by role_id  ");
        return db.query(sql.toString(), new BeanPropertyRowMapper(SystemRole.class));
    }
    
    @Override
    public List<SystemRole> findAllByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemRole findById(long id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT role_id, role_name, role_code, note, active ");
        sql.append(" FROM system_role where role_id=:role_id order by role_id ");
        Map<String, Object> params = new HashMap<>();
        params.put("role_id", id);
        return (SystemRole) db.queryForObject(sql.toString(), params, new BeanPropertyRowMapper(SystemRole.class));
    }

    @Override
    public SystemRole findByCode(String code) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT role_id, role_name, role_code, note, active ");
        sql.append(" FROM system_role where role_code=:role_code order by role_id ");
        Map<String, Object> params = new HashMap<>();
        params.put("role_code", code);
        return (SystemRole) db.queryForObject(sql.toString(), params, new BeanPropertyRowMapper(SystemRole.class));
    }
    
    @Override
    public boolean save(SystemRole model) {
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO system_role( ");
        sql.append(" role_id, role_name, role_code, note, ");
        sql.append(" created_by, created_on, version_no) ");
        sql.append(" VALUES ( ");
        sql.append(" :role_id, :role_name, :role_code, :note, ");
        sql.append(" :created_by, now(), 1 ) ");
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(model);
        return db.update(sql.toString(), namedParameters)==1;          
    }

    @Override
    public boolean update(SystemRole model) {

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE system_role SET  ");
        sql.append(" role_code=:role_code, ");
        sql.append(" role_name=:role_name, ");
        sql.append(" note=:note, ");
        sql.append(" updated_by=:updated_by, ");
        sql.append(" updated_on=:updated_on, ");
        sql.append(" active=:active ");
        sql.append(" WHERE role_id=:role_id; ");
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(model);
        return db.update(sql.toString(), namedParameters) == 1;
    }

    @Override
    public boolean exist(SystemRole model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public List<Map<String, Object>> findRoleFeature(long roleId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ft.feature_id, ft.feature_name, ft.feature_code, ft.module, ft.controller, ft.parent_id, ft.action, ft.component, ft.is_menu, ft.type, CASE WHEN rf.is_home IS NULL THEN 0 ELSE 1 END is_home, CASE WHEN rf.feature_id IS NULL THEN 0 ELSE 1 END is_selected ");
        sql.append(" FROM feature ft ");
        sql.append(" left join role_feature rf on (ft.feature_id=rf.feature_id and rf.role_id=:role_id) ");
        sql.append(" where ft.active=1 order by ft.parent_id ,ft.sort_order ASC ,ft.feature_name ASC ");
        Map<String, Object> params = new HashMap<>();
        params.put("role_id", roleId);
        return db.queryForList(sql.toString(), params);
    }
    
    public void deleteRoleFeature(long roleId){
        StringBuilder sql = new StringBuilder();
        
        sql.append(" delete from role_feature where role_id=? ");
        jdb.update(sql.toString(), roleId);
    }
    
    public JSONArray mapRoleFeature(final long roleId, final JSONArray featureList) {
        StringBuilder sql = new StringBuilder();
        
        sql.append(" insert into role_feature (role_id, feature_id, is_home, version_no, active) values (?,?,0,1,1) ");

        jdb.batchUpdate(sql.toString(),
        new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setObject(1, roleId );
                try {
                    ps.setObject(2, AppUtil.toLong(featureList.get(i)) );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public int getBatchSize() {
                return featureList.length();
            }
        });
        
        return featureList;
    }

    
    public boolean updateHomeFeature(long role_id, long feature_id) {

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE role_feature SET  ");
        sql.append(" is_home=1, ");
        sql.append(" updated_by=:updated_by ");
        sql.append(" WHERE role_id=:role_id and feature_id=:feature_id; ");
        
        Map<String, Object> params = new HashMap<>();
        params.put("role_id", role_id);
        params.put("feature_id", feature_id);
        params.put("updated_by", sessionService.getUserId());
        
        return db.update(sql.toString(), params) == 1;
    }
    
    
    public class SystemRoleRowMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            SystemRole model = new SystemRole();
            model.setRole_id(Long.parseLong(rs.getString("role_id")));
            model.setRole_name(rs.getString("role_name"));
            model.setRole_code(rs.getString("role_code"));
            model.setNote(rs.getString("note"));
            model.setActive(rs.getBoolean("active"));
            return model;
        }
    }
}
