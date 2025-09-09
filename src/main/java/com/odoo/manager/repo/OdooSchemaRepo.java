package com.odoo.manager.repo;

import java.util.Optional;

import com.odoo.manager.model.OdooSchema;

public interface OdooSchemaRepo {

    /**
     * Obtain odoo schema name to export information
     * 
     * @param id Company code
     * @return Scheman name
     */
    Optional<OdooSchema> findOdooSchemaByOdooCompanyId(short id);
}
