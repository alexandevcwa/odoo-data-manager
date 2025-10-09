package com.odoo.manager.repo;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.odoo.manager.model.AccountPartialReconcile;

@Slf4j
@Repository
public class AccountPartialReconcileRepo extends AbstractBaseRepo<AccountPartialReconcile> {

    public AccountPartialReconcileRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<AccountPartialReconcile> findAllByExportedIsNotNull() {
        return jdbcTemplate.query(SELECT_ALL_WHERE_EXPORTED_IS_NULL,
                new BeanPropertyRowMapper<>(AccountPartialReconcile.class));
    }

    @Override
    public void markGroupAsExported(List<AccountPartialReconcile> group) {
        jdbcTemplate.batchUpdate(UPDATE_EXPORTED_BY_ID, group, group.size(), (ps, item) -> {
            ps.setInt(1, item.getId());
        });
    }

    @Override
    public void saveBatch(List<AccountPartialReconcile> queue, String schema) {
        int inserted = onInsertAsBatch(queue, INSERT_ACCOUNT_PARTIAL_RECONCILE, schema);
        log.info("Inserted {} rows into {}.account_partial_reconcile_exp", inserted, schema);
    }

    private final String SELECT_ALL_WHERE_EXPORTED_IS_NULL = "select * from account_partial_reconcile_exp where exported is null order by operation_sys";
    private final String UPDATE_EXPORTED_BY_ID = "update account_partial_reconcile_exp set exported = 'E' where id = ?";
    private final String INSERT_ACCOUNT_PARTIAL_RECONCILE = """
                INSERT INTO %s.account_partial_reconcile_exp (
                    id,
                    debit_move_id,
                    credit_move_id,
                    full_reconcile_id,
                    exchange_move_id,
                    debit_currency_id,
                    credit_currency_id,
                    company_id,
                    create_uid,
                    write_uid,
                    max_date,
                    amount,
                    debit_amount_currency,
                    credit_amount_currency,
                    create_date,
                    write_date,
                    operation,
                    operation_sys,
                    exported
                ) VALUES (
                    :id,
                    :debitMoveId,
                    :creditMoveId,
                    :fullReconcileId,
                    :exchangeMoveId,
                    :debitCurrencyId,
                    :creditCurrencyId,
                    :companyId,
                    :createUid,
                    :writeUid,
                    :maxDate,
                    :amount,
                    :debitAmountCurrency,
                    :creditAmountCurrency,
                    :createDate,
                    :writeDate,
                    :operation,
                    :operationSys,
                    :exported
                )
            """;


}
