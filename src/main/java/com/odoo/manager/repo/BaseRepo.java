package com.odoo.manager.repo;

import java.util.List;

public interface BaseRepo<T> {

    /**
     * Obtain all data from tables that will by exported,
     * 
     * @return
     */
    List<T> findAllByExportedIsNotNull();

    /**
     * Mark database rows as exported
     */
    void markGroupAsExported(List<T> group);
}
