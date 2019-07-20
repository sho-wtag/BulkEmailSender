/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracbank.BulkEmailSender.user_auth.repo;

import com.bracbank.BulkEmailSender.user_auth.model.Feature;
import com.bracbank.BulkEmailSender.utils.ModelRepo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class FeatureRepo implements ModelRepo<Feature>{
    @Autowired
    NamedParameterJdbcTemplate nDB;
    
    @Autowired
    JdbcTemplate db;
    
    @Override
    public List<Feature> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ft.feature_id, ft.feature_name, ft.feature_code, ft.note, ft.module, ft.controller, ft.action, ft.component, ft.parent_id, pf.feature_name parent_name, ft.type, ft.url, ft.is_menu, ft.need_permission, ft.sort_order, ft.active ");
        sql.append(" FROM feature ft ");
        sql.append(" left join feature pf on (ft.parent_id=pf.feature_id) ");
        sql.append(" order by ft.module, ft.controller, ft.sort_order, ft.component ");
        return nDB.query(sql.toString(), new BeanPropertyRowMapper(Feature.class));
    }
    
    
    public List<Map<String, Object>> findAllForList() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ft.feature_id, ft.feature_name, ft.feature_code, ft.note, ft.module, ft.controller, ft.action, ft.component, ft.parent_id, pf.feature_name parent_name, mf.feature_name root_module, ft.type, ft.url, ft.is_menu, ft.need_permission, ft.sort_order, ft.active ");
        sql.append(" FROM feature ft ");
        sql.append(" left join feature pf on (ft.parent_id=pf.feature_id) ");
        sql.append(" left join feature mf on (pf.parent_id=mf.feature_id) ");
        sql.append(" order by ft.module, ft.controller, ft.sort_order, ft.component ");
        return db.queryForList(sql.toString());
    }
    
    public List<Feature> findAllWithRole() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ft.feature_id, "
                + "ft.feature_name, "
                + "ft.feature_code,"
                + "ft.module, "
                + "ft.controller,"
                + "ft.action,"
                + "ft.component,  "
                +" ft.parent_id,  "
                + " ft.type,"
                + "  ft.url,"
                + " ft.is_menu, "
                + " ft.need_permission, "
                + " ft.sort_order, "
                + " ft.active , "
                + " tmp.roles ");
        sql.append(" FROM feature ft  ");
        sql.append(" join ( ");
        
        sql.append(" select distinct rf.feature_id, ");
        sql.append("   STUFF((SELECT distinct ',' + sr1.role_name + '##$$@@$$##' ");
        sql.append("          from role_feature rf1 join system_role sr1 on (rf1.role_id=sr1.role_id) ");
        sql.append("          where rf.feature_id = rf1.feature_id ");
        sql.append("             FOR XML PATH(''), TYPE ");
        sql.append("             ).value('.', 'NVARCHAR(MAX)')  ");
        sql.append("         ,1,1,'') roles ");
        sql.append(" from role_feature rf ");
        
        sql.append(" ) as tmp on ft.feature_id=tmp.feature_id ");
        return nDB.query(sql.toString(), new RowMapper<Feature>() {
 
            public Feature mapRow(ResultSet rs, int rowNum) throws SQLException {
                Feature model = new Feature();
                model.setFeature_id(Long.parseLong(rs.getString("feature_id")));
                model.setFeature_name(rs.getString("feature_name"));
                model.setFeature_code(rs.getString("feature_code"));
                model.setModule(rs.getString("module"));
                model.setController(rs.getString("controller"));
                model.setAction(rs.getString("action"));
                model.setComponent(rs.getString("component"));
                model.setType(rs.getString("type"));
                model.setUrl(rs.getString("url"));
                model.setSort_order(rs.getInt("sort_order"));
                model.setActive(rs.getBoolean("active"));
                model.setIs_menu(rs.getBoolean("is_menu"));
                model.setNeed_permission(rs.getBoolean("need_permission"));
                String parentId=rs.getString("feature_id");
                if(parentId!=null && !parentId.trim().isEmpty()){
                    model.setParent_id(Long.parseLong(parentId));
                }
                
                try{
                    String[] roles=rs.getString("roles").split("##$$@@$$##");
                    for (String role : roles) {
                        model.getRoles().add(role);
                    }
                } catch(Exception e){
                    model.getRoles().add(null);
                }
                return model;
            }
             
        });
    }
    
    public List<Feature> findByRoleName(String roleName) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ft.feature_id, ft.feature_name, ft.feature_code, ft.note, ft.module, ft.controller, ft.action, ft.component,  ");
        sql.append(" ft.parent_id,  ft.type, ft.url, ft.is_menu, ft.need_permission, ft.sort_order, ft.active ,rf.is_home  ");
        sql.append(" FROM feature ft  ");
        sql.append(" join role_feature rf  on ft.feature_id =rf.feature_id ");
        sql.append(" join system_role sr on rf.role_id=sr.role_id ");
        sql.append(" where ft.active=1 and sr.active=1 and sr.role_name=:role_name ");
        sql.append(" order by ft.sort_order asc, ft.module asc, ft.feature_name asc ");
        Map<String, Object> params = new HashMap<>();
        params.put("role_name", roleName);
        return nDB.query(sql.toString(), params,new BeanPropertyRowMapper(Feature.class));
    }

    @Override
    public List<Feature> findAllByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Feature findById(long id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ft.feature_id, ft.feature_name, ft.feature_code, ft.note, ft.module, ft.controller, ft.action, ft.component, ft.parent_id, pf.feature_name parent_name, ft.type, ft.url, ft.is_menu, ft.need_permission, ft.sort_order, ft.active ");
        sql.append(" FROM feature ft ");
        sql.append(" left join feature pf on (ft.parent_id=pf.feature_id) ");
        sql.append(" where ft.feature_id=:feature_id ");
        Map<String, Object> params = new HashMap<>();
        params.put("feature_id", id);
        return (Feature) nDB.queryForObject(sql.toString(), params, new BeanPropertyRowMapper(Feature.class));
    }

    @Override
    public Feature findByCode(String code) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT feature_id, feature_name, feature_code, note, module, controller, action, component, parent_id, type, url, is_menu, need_permission, sort_order, active ");
        sql.append(" FROM feature where feature_code=:feature_code ");
        Map<String, Object> params = new HashMap<>();
        params.put("feature_code", code);
        return (Feature) nDB.queryForObject(sql.toString(), params, new BeanPropertyRowMapper(Feature.class));
    }

    @Override
    public boolean save(Feature model) {
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO feature( ");
        sql.append(" feature_name, feature_code, note, module, controller, action, component, parent_id, type, url, is_menu, need_permission, sort_order, ");
        sql.append(" created_by, created_on, version_no, active) ");
        sql.append(" VALUES ( ");
        sql.append(" :feature_name, :feature_code, :note, :module, :controller, :action, :component, :parent_id, :type, :url, :is_menu, :need_permission, :sort_order, ");
        sql.append(" :created_by, getdate(), 1, 1) ");
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(model);
        return nDB.update(sql.toString(), namedParameters)==1;          
    }

    @Override
    public boolean update(Feature model) {

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE feature SET  ");
        sql.append(" feature_name=:feature_name, ");
        sql.append(" note=:note, ");
        sql.append(" module=:module, ");
        sql.append(" controller=:controller, ");
        sql.append(" action=:action, ");
        sql.append(" component=:component, ");
        sql.append(" parent_id=:parent_id, ");
        sql.append(" type=:type, ");
        sql.append(" url=:url, ");
        sql.append(" is_menu=:is_menu, ");
        sql.append(" need_permission=:need_permission, ");
        sql.append(" sort_order=:sort_order, ");
        sql.append(" updated_by=:updated_by, ");
        sql.append(" version_no=1, ");
        sql.append(" active=:active ");
        sql.append(" WHERE feature_id=:feature_id; ");
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(model);
        return nDB.update(sql.toString(), namedParameters) == 1;
    }

    @Override
    public boolean exist(Feature model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public class SystemRoleRowMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Feature model = new Feature();
            model.setFeature_id(Long.parseLong(rs.getString("feature_id")));
            model.setFeature_name(rs.getString("feature_name"));
            model.setFeature_code(rs.getString("feature_code"));
            model.setModule(rs.getString("module"));
            model.setController(rs.getString("controller"));
            model.setAction(rs.getString("action"));
            model.setComponent(rs.getString("component"));
            model.setNote(rs.getString("note"));
            model.setSort_order(rs.getInt("sort_order"));
            model.setActive(rs.getBoolean("active"));
            return model;
        }
    }
}
