package com.odoo.manager.repo;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.odoo.manager.model.AccountMoveLine;

import lombok.RequiredArgsConstructor;

@Slf4j
@Repository
public class AccountMoveLineRepo extends AbstractBaseRepo<AccountMoveLine> {

    public AccountMoveLineRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<AccountMoveLine> findAllByExportedIsNotNull() {
        return jdbcTemplate.query(SELECT_ALL_WHERE_EXPORTED_IS_NULL,
                new BeanPropertyRowMapper<>(AccountMoveLine.class));
    }

    @Override
    public void markGroupAsExported(List<AccountMoveLine> group) {
        jdbcTemplate.batchUpdate(UPDATE_EXPORTED_BY_ID, group, group.size(), (ps, item) -> {
            ps.setInt(1, item.getId());
        });
    }

    @Override
    public void saveBatch(List<AccountMoveLine> queue, String schema) {
        int inserted = onInsertAsBatch(queue, String.format(INSERT_ACCOUNT_MOVE_LINE, schema), schema);
        log.info("Inserted {} rows into {}.account_move_line_exp", inserted, schema);
    }

    private final String SELECT_ALL_WHERE_EXPORTED_IS_NULL = "select * from account_move_line_exp where exported is null order by operation_sys";
    private final String UPDATE_EXPORTED_BY_ID = "update account_move_line_exp set exported = 'E' where id = ?";
    private final String INSERT_ACCOUNT_MOVE_LINE = """
                INSERT INTO %s.account_move_line_exp (
                    id,
                    move_id,
                    journal_id,
                    company_id,
                    company_currency_id,
                    "sequence",
                    account_id,
                    currency_id,
                    partner_id,
                    reconcile_model_id,
                    payment_id,
                    statement_line_id,
                    statement_id,
                    group_tax_id,
                    tax_line_id,
                    tax_group_id,
                    tax_repartition_line_id,
                    full_reconcile_id,
                    product_id,
                    product_uom_id,
                    create_uid,
                    write_uid,
                    move_name,
                    parent_state,
                    "ref",
                    "name",
                    matching_number,
                    display_type,
                    "date",
                    invoice_date,
                    date_maturity,
                    discount_date,
                    analytic_distribution,
                    debit,
                    credit,
                    balance,
                    amount_currency,
                    tax_base_amount,
                    amount_residual,
                    amount_residual_currency,
                    quantity,
                    price_unit,
                    price_subtotal,
                    price_total,
                    discount,
                    discount_amount_currency,
                    discount_balance,
                    is_imported,
                    tax_tag_invert,
                    reconciled,
                    create_date,
                    write_date,
                    is_downpayment,
                    cogs_origin_id,
                    operation,
                    operation_sys,
                    exported
                ) VALUES (
                    :id,
                    :moveId,
                    :journalId,
                    :companyId,
                    :companyCurrencyId,
                    :sequence,
                    :accountId,
                    :currencyId,
                    :partnerId,
                    :reconcileModelId,
                    :paymentId,
                    :statementLineId,
                    :statementId,
                    :groupTaxId,
                    :taxLineId,
                    :taxGroupId,
                    :taxRepartitionLineId,
                    :fullReconcileId,
                    :productId,
                    :productUomId,
                    :createUid,
                    :writeUid,
                    :moveName,
                    :parentState,
                    :ref,
                    :name,
                    :matchingNumber,
                    :displayType,
                    :date,
                    :invoiceDate,
                    :dateMaturity,
                    :discountDate,
                    CAST(:analyticDistribution AS jsonb),
                    :debit,
                    :credit,
                    :balance,
                    :amountCurrency,
                    :taxBaseAmount,
                    :amountResidual,
                    :amountResidualCurrency,
                    :quantity,
                    :priceUnit,
                    :priceSubtotal,
                    :priceTotal,
                    :discount,
                    :discountAmountCurrency,
                    :discountBalance,
                    :isImported,
                    :taxTagInvert,
                    :reconciled,
                    :createDate,
                    :writeDate,
                    :isDownpayment,
                    :cogsOriginId,
                    :operation,
                    :operationSys,
                    :exported
                )
            """;

}
