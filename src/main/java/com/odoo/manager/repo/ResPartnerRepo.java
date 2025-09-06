package com.odoo.manager.repo;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.odoo.manager.model.ResPartner;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ResPartnerRepo implements BaseRepo<ResPartner> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<ResPartner> findAllByExportedIsNotNull() {
        return jdbcTemplate.query(SELECT_ALL_WHERE_EXPORTED_IS_NULL,
                new BeanPropertyRowMapper<>(ResPartner.class));
    }

    @Override
    public void markGroupAsExported(List<ResPartner> group) {
        jdbcTemplate.batchUpdate(UPDATE_EXPORTED_BY_ID, group, group.size(), (ps, item) -> {
            ps.setInt(1, item.getId());
        });
    }

    private final String SELECT_ALL_WHERE_EXPORTED_IS_NULL = "select * from res_partner_exp where exported is null order by operation_sys";
    private final String UPDATE_EXPORTED_BY_ID = "update res_partner_exp set exported = 'E' where id = ?";

}
