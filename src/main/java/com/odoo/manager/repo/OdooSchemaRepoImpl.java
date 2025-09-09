package com.odoo.manager.repo;

import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.odoo.manager.model.OdooSchema;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OdooSchemaRepoImpl implements OdooSchemaRepo {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<OdooSchema> findOdooSchemaByOdooCompanyId(short id) {
        // jdbcTemplate.query(SELECT_SCHEMA_NAME, ps -> {
        // ps.setShort(1, id);
        // }, rs -> {
        // if (rs.next()) {
        // return Optional.of(rs.getString(1));
        // }
        // return Optional.empty();
        // });
        // return Optional.empty();
        return jdbcTemplate.query(SELECT_SCHEMA_NAME, ps -> {
            ps.setShort(1, id);
        }, rs -> {
            if (rs.next()) {
                OdooSchema odooSchema = new OdooSchema();
                odooSchema.setInstanceId(rs.getInt("odoo_instance_id"));
                odooSchema.setSchema(rs.getString("odoo_schema"));
                odooSchema.setCompanyId(rs.getShort("odoo_company_id"));
                return Optional.of(odooSchema);
            }
            return Optional.empty();
        });
    }

    private final String SELECT_SCHEMA_NAME = "select odoo_instance_id, odoo_schema, odoo_company_id from odoo_schema_exp where odoo_instance_id = ?";

}
