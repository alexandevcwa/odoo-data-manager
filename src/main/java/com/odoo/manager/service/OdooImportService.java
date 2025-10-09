package com.odoo.manager.service;

public interface OdooImportService {

    /**
     * Import data from Odoo XML file
     * @param oXmlPath Path to Odoo XML file
     */
    void importFromOXML(String oXmlPath);
}