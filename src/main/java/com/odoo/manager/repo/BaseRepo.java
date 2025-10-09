package com.odoo.manager.repo;

import java.util.List;
import java.util.Queue;

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

    /**
     *
     * @param queue
     */
    void saveBatch(List<T> queue, String schema);
}
