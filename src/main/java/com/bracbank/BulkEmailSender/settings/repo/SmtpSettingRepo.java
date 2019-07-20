package com.bracbank.BulkEmailSender.settings.repo;

import com.bracbank.BulkEmailSender.settings.model.SmtpSetting;
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
public class SmtpSettingRepo implements ModelRepo<SmtpSetting> {

    @Autowired
    NamedParameterJdbcTemplate db;

    @Autowired
    JdbcTemplate jdb;

    @Autowired
    AppUtil util;

    @Autowired
    SessionService sessionService;

    @Override
    public List<SmtpSetting> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT smtp_hostname, smtp_port, smtp_encryption, smtp_user,smtp_pass, smtp_max_connections, " +
                "smtp_max_messages, service_url, admin_email ");
        sql.append(" FROM smtp_settings ");
        sql.append(" where order by smtp_hostname ");
        return db.query(sql.toString(), new BeanPropertyRowMapper(SmtpSetting.class));
    }

    @Override
    public List<SmtpSetting> findAllByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SmtpSetting findById(long id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT smtp_hostname, smtp_port, smtp_encryption, smtp_user,smtp_pass, smtp_max_connections, smtp_max_messages, smtp_log, service_url, admin_email ");
        sql.append(" FROM smtp_settings where id=:smtp_id order by id ");
        Map<String, Object> params = new HashMap<>();
        params.put("smtp_id", id);
        return (SmtpSetting) db.queryForObject(sql.toString(), params, new BeanPropertyRowMapper(SmtpSetting.class));
    }

    @Override
    public SmtpSetting findByCode(String code) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean save(SmtpSetting model) {
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO smtp_settings( ");
        sql.append(" smtp_hostname, smtp_port, smtp_encryption, smtp_user, smtp_pass,");
        sql.append(" smtp_max_connections, smtp_max_messages, smtp_log, service_url, admin_email) ");
        sql.append(" VALUES ( ");
        sql.append(" :smtp_hostname, :smtp_port, :smtp_encryption, :smtp_user, :smtp_pass, ");
        sql.append(" :smtp_max_connections,:smtp_max_messages,:smtp_log,:service_url,:admin_email ) ");
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(model);
        return db.update(sql.toString(), namedParameters)==1;
    }

    @Override
    public boolean update(SmtpSetting model) {
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE smtp_settings SET  ");
        sql.append(" smtp_hostname=:smtp_hostname, ");
        sql.append(" smtp_port=:smtp_port, ");
        sql.append(" smtp_encryption=:smtp_encryption, ");
        sql.append(" smtp_user=:smtp_user, ");
        sql.append(" smtp_pass=:smtp_pass, ");
        sql.append(" smtp_max_connections=:smtp_max_connections ");
        sql.append(" smtp_max_messages=:smtp_max_messages ");
        sql.append(" smtp_log=:smtp_log ");
        sql.append(" service_url=:service_url ");
        sql.append(" admin_email=:admin_email ");
        sql.append(" WHERE id=:id; ");
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(model);
        return db.update(sql.toString(), namedParameters) == 1;
    }

    @Override
    public boolean exist(SmtpSetting model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public class SmtpSettingRowMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            SmtpSetting smtpModel = new SmtpSetting();
            return smtpModel;
        }
    }
}
