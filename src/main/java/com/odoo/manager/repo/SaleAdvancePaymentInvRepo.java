package com.odoo.manager.repo;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.odoo.manager.model.SaleAdvancePaymentInv;

@Slf4j
@Repository
public class SaleAdvancePaymentInvRepo extends AbstractBaseRepo<SaleAdvancePaymentInv> {

    public SaleAdvancePaymentInvRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public List<SaleAdvancePaymentInv> findAllByExportedIsNotNull() {
        return jdbcTemplate.query(SELECT_ALL_WHERE_EXPORTED_IS_NULL,
                new BeanPropertyRowMapper<>(SaleAdvancePaymentInv.class));
    }

    @Override
    public void markGroupAsExported(List<SaleAdvancePaymentInv> group) {
        jdbcTemplate.batchUpdate(UPDATE_EXPORTED_BY_ID, group, group.size(), (ps, item) -> {
            ps.setInt(1, item.getId());
        });
    }

    @Override
    public void saveBatch(List<SaleAdvancePaymentInv> list, String schema) {
        int inserted = onInsertAsBatch(list,INSERT_SALE_ADVANCE_PAYMENT_INV, schema);
        log.info("Inserted {} rows into {}.sale_advance_payment_inv_exp", inserted, schema);
    }

    private final String SELECT_ALL_WHERE_EXPORTED_IS_NULL = "select * from sale_advance_payment_inv_exp where exported is null order by operation_sys";
    private final String UPDATE_EXPORTED_BY_ID = "update sale_advance_payment_inv_exp set exported = 'E' where id = ?";
    private final String INSERT_SALE_ADVANCE_PAYMENT_INV = """
    INSERT INTO %s.sale_advance_payment_inv_exp (
        id,
        currency_id,
        company_id,
        create_uid,
        write_uid,
        advance_payment_method,
        fixed_amount,
        deduct_down_payments,
        consolidated_billing,
        create_date,
        write_date,
        amount,
        operation,
        operation_sys,
        exported
    ) VALUES (
        :id,
        :currencyId,
        :companyId,
        :createUid,
        :writeUid,
        :advancePaymentMethod,
        :fixedAmount,
        :deductDownPayments,
        :consolidatedBilling,
        :createDate,
        :writeDate,
        :amount,
        :operation,
        :operationSys,
        :exported
    )
""";


}
