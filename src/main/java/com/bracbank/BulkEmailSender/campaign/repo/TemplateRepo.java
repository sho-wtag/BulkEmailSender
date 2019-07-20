package com.bracbank.BulkEmailSender.campaign.repo;

import com.bracbank.BulkEmailSender.campaign.model.Template;
import com.bracbank.BulkEmailSender.user_auth.service.SessionService;
import com.bracbank.BulkEmailSender.utils.AppUtil;
import com.bracbank.BulkEmailSender.utils.ModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TemplateRepo implements ModelRepo<Template>{

    @Autowired
    NamedParameterJdbcTemplate db;

    @Autowired
    JdbcTemplate jdb;

    @Autowired
    AppUtil util;

    @Autowired
    SessionService sessionService;

    @Override
    public List<Template> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT name, description, html, text, created");
        sql.append(" FROM templates ");
        sql.append(" where order by name ");
        return db.query(sql.toString(), new BeanPropertyRowMapper(Template.class));
    }

    @Override
    public List<Template> findAllByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Template findById(long id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT name, description, html, text, created");
        sql.append(" FROM templates where id=:templates_id order by id ");
        Map<String, Object> params = new HashMap<>();
        params.put("templates_id", id);
        return (Template) db.queryForObject(sql.toString(), params, new BeanPropertyRowMapper(Template.class));
    }

    @Override
    public Template findByCode(String code) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean save(Template template) {
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO templates( ");
        sql.append(" name, description, html, text, created)");
        sql.append(" VALUES ( ");
        sql.append(" :name, :description, :html, :text, :created)");
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(template);
        return db.update(sql.toString(), namedParameters)==1;
    }

    @Override
    public boolean update(Template template) {
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE templates SET  ");
        sql.append(" name=:name, ");
        sql.append(" description=:description, ");
        sql.append(" html=:html, ");
        sql.append(" text=:text, ");
        sql.append(" created=:created, ");
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(template);
        return db.update(sql.toString(), namedParameters) == 1;
    }

    @Override
    public boolean exist(Template template) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public class TemplateRowMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Template template = new Template();
            return template;
        }
    }

}
