package com.odoo.manager.service;

public interface OdooExportService {

    /**
     * Exports data to the specified file path based on the provided schema.
     *
     * @param path The file path where the data will be exported to.
     * @param schema The schema that defines the structure of the exported data.
     */
    void export(String path, String schema);
}
