package com.bracbank.BulkEmailSender.campaign.repo;

import com.bracbank.BulkEmailSender.campaign.model.Campaign;
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
public class CampaignRepo implements ModelRepo<Campaign> {

    @Autowired
    NamedParameterJdbcTemplate db;

    @Autowired
    JdbcTemplate jdb;

    @Autowired
    AppUtil util;

    @Autowired
    SessionService sessionService;

    @Override
    public List<Campaign> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT cid, type, parent, name, description, list, segment, template, source_url, editor_name, editor_data," +
                " last_check, check_status, from, address, reply_to, subject, html, html_prepared, text, status, " +
                "tracking_disabled, scheduled, status_change, delivered, opened, clicks, unsubscribed, bounced, " +
                "complained, created");
        sql.append(" FROM campaigns ");
        sql.append(" where order by name ");
        return db.query(sql.toString(), new BeanPropertyRowMapper(Campaign.class));
    }

    @Override
    public List<Campaign> findAllByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Campaign findById(long id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT cid, type, parent, name, description, list, segment, template, source_url, editor_name, editor_data,\" +\n" +
                "\" last_check, check_status, from, address, reply_to, subject, html, html_prepared, text, status, \" +\n" +
                "\"tracking_disabled, scheduled, status_change, delivered, opened, clicks, unsubscribed, bounced, \" +\n" +
                "\"complained, created");
        sql.append(" FROM campaigns where id=:campaign_id order by id ");
        Map<String, Object> params = new HashMap<>();
        params.put("campaign_id", id);
        return (Campaign) db.queryForObject(sql.toString(), params, new BeanPropertyRowMapper(Campaign.class));
    }

    @Override
    public Campaign findByCode(String code) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean save(Campaign campaign) {
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO campaigns( ");
        sql.append(" cid, type, parent, name, description, list, segment, template, source_url, editor_name,");
        sql.append(" editor_data, last_check, check_status, from, address, reply_to, subject, html, html_prepared, text,");
        sql.append(" status, tracking_disabled, scheduled, status_change, delivered, opened, clicks, unsubscribed, bounced, complained, created)");
        sql.append(" VALUES ( ");
        sql.append(" :cid, :type, :parent, :name, :description, :list, :segment, :template, :source_url, :editor_name,");
        sql.append(" :editor_data, :last_check, :check_status, :from, :address, :reply_to, :subject, :html, :html_prepared, :text,");
        sql.append(" :status, :tracking_disabled, :scheduled, :status_change, :delivered, :opened, :clicks, :unsubscribed, :bounced, :complained, :created)");
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(campaign);
        return db.update(sql.toString(), namedParameters)==1;
    }

    @Override
    public boolean update(Campaign campaign) {
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE campaigns SET  ");
        sql.append(" cid=:cid, ");
        sql.append(" type=:type, ");
        sql.append(" parent=:parent, ");
        sql.append(" name=:name, ");
        sql.append(" description=:description, ");
        sql.append(" list=:list ");
        sql.append(" segment=:segment ");
        sql.append(" template=:template ");
        sql.append(" source_url=:source_url ");
        sql.append(" editor_name=:editor_name ");
        sql.append(" editor_data=:editor_data ");
        sql.append(" last_check=:last_check ");
        sql.append(" check_status=:check_status ");
        sql.append(" from=:from ");
        sql.append(" address=:address ");
        sql.append(" reply_to=:reply_to ");
        sql.append(" subject=:subject ");
        sql.append(" html=:html ");
        sql.append(" html_prepared=:html_prepared ");
        sql.append(" text=:text ");
        sql.append(" status=:status ");
        sql.append(" tracking_disabled=:tracking_disabled ");
        sql.append(" scheduled=:scheduled ");
        sql.append(" status_change=:status_change ");
        sql.append(" delivered=:delivered ");
        sql.append(" opened=:opened ");
        sql.append(" clicks=:clicks ");
        sql.append(" unsubscribed=:unsubscribed ");
        sql.append(" bounced=:bounced ");
        sql.append(" complained=:complained ");
        sql.append(" created=:created ");
        sql.append(" WHERE campaign_id=:campaign_id; ");
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(campaign);
        return db.update(sql.toString(), namedParameters) == 1;
    }

    @Override
    public boolean exist(Campaign campaign) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public class CampaignRowMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Campaign campaign = new Campaign();
            return campaign;
        }
    }
}
