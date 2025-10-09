package com.odoo.manager.repo;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.odoo.manager.model.AccountFullReconcile;

@Slf4j
@Repository
public class AccountFullReconcileRepo extends AbstractBaseRepo<AccountFullReconcile> {

    public AccountFullReconcileRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<AccountFullReconcile> findAllByExportedIsNotNull() {
        return jdbcTemplate.query(SELECT_ALL_WHERE_EXPORTED_IS_NULL,
                new BeanPropertyRowMapper<>(AccountFullReconcile.class));
    }

    @Override
    public void markGroupAsExported(List<AccountFullReconcile> group) {
        jdbcTemplate.batchUpdate(UPDATE_EXPORTED_BY_ID, group, group.size(), (ps, item) -> {
            ps.setInt(1, item.getId());
        });
    }

    @Override
    public void saveBatch(List<AccountFullReconcile> list, String schema) {
        int inserted = onInsertAsBatch(list,INSERT_ACCOUNT_FULL_RECONCILE,schema);
        log.info("Inserted {} rows into {}.account_full_reconcile_exp", inserted, schema);
    }

    private final String SELECT_ALL_WHERE_EXPORTED_IS_NULL = "select * from account_full_reconcile_exp where exported is null order by operation_sys";
    private final String UPDATE_EXPORTED_BY_ID = "update account_full_reconcile_exp set exported = 'E' where id = ?";
    private final String INSERT_ACCOUNT_FULL_RECONCILE = """
                INSERT INTO %s.account_full_reconcile_exp (
                    id,
                    exchange_move_id,
                    create_uid,
                    write_uid,
                    create_date,
                    write_date,
                    operation,
                    operation_sys,
                    exported
                ) VALUES (
                    :id,
                    :exchangeMoveId,
                    :createUid,
                    :writeUid,
                    :createDate,
                    :writeDate,
                    :operation,
                    :operationSys,
                    :exported
                )
            """;
}
