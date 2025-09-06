package com.odoo.manager.service;

public interface OdooExportService {

    /**
     * Interface that export a oxml file that contains all odoo sales data generated
     * 
     * @param path S.O. path where file will be save
     */
    void export(String path);
}
