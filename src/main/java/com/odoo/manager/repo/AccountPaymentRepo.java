package com.odoo.manager.repo;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.odoo.manager.model.AccountPayment;

@Slf4j
@Repository
public class AccountPaymentRepo extends AbstractBaseRepo<AccountPayment> {

    public AccountPaymentRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<AccountPayment> findAllByExportedIsNotNull() {
        return jdbcTemplate.query(SELECT_ALL_WHERE_EXPORTED_IS_NULL, new BeanPropertyRowMapper<>(AccountPayment.class));
    }

    @Override
    public void markGroupAsExported(List<AccountPayment> group) {
        jdbcTemplate.batchUpdate(UPDATE_EXPORTED_BY_ID, group, group.size(), (ps, item) -> {
            ps.setInt(1, item.getId());
        });
    }

    @Override
    public void saveBatch(List<AccountPayment> list, String schema) {
        int inserted = onInsertAsBatch(list,INSERT_ACCOUNT_PAYMENT,schema);
        log.info("Inserted {} rows into {}.account_payment_exp", inserted, schema);
    }

    private final String SELECT_ALL_WHERE_EXPORTED_IS_NULL = "select * from account_payment_exp where exported is null order by operation_sys";
    private final String UPDATE_EXPORTED_BY_ID = "update account_payment_exp set exported = 'E' where id = ?";
    private final String INSERT_ACCOUNT_PAYMENT = """
                INSERT INTO %s.account_payment_exp (
                    id,
                    message_main_attachment_id,
                    move_id,
                    journal_id,
                    company_id,
                    partner_bank_id,
                    paired_internal_transfer_payment_id,
                    payment_method_line_id,
                    payment_method_id,
                    currency_id,
                    partner_id,
                    outstanding_account_id,
                    destination_account_id,
                    create_uid,
                    write_uid,
                    "name",
                    state,
                    payment_type,
                    partner_type,
                    memo,
                    payment_reference,
                    "date",
                    amount,
                    amount_company_currency_signed,
                    is_reconciled,
                    is_matched,
                    is_sent,
                    create_date,
                    write_date,
                    payment_transaction_id,
                    payment_token_id,
                    source_payment_id,
                    pos_payment_method_id,
                    force_outstanding_account_id,
                    pos_session_id,
                    pos_order_id,
                    operation,
                    operation_sys,
                    exported
                ) VALUES (
                    :id,
                    :messageMainAttachmentId,
                    :moveId,
                    :journalId,
                    :companyId,
                    :partnerBankId,
                    :pairedInternalTransferPaymentId,
                    :paymentMethodLineId,
                    :paymentMethodId,
                    :currencyId,
                    :partnerId,
                    :outstandingAccountId,
                    :destinationAccountId,
                    :createUid,
                    :writeUid,
                    :name,
                    :state,
                    :paymentType,
                    :partnerType,
                    :memo,
                    :paymentReference,
                    :date,
                    :amount,
                    :amountCompanyCurrencySigned,
                    :isReconciled,
                    :isMatched,
                    :isSent,
                    :createDate,
                    :writeDate,
                    :paymentTransactionId,
                    :paymentTokenId,
                    :sourcePaymentId,
                    :posPaymentMethodId,
                    :forceOutstandingAccountId,
                    :posSessionId,
                    :posOrderId,
                    :operation,
                    :operationSys,
                    :exported
                )
            """;
}
