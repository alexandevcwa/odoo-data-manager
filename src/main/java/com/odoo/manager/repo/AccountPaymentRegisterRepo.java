package com.odoo.manager.repo;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.odoo.manager.model.AccountPaymentRegister;

@Slf4j
@Repository
public class AccountPaymentRegisterRepo extends AbstractBaseRepo<AccountPaymentRegister> {

    public AccountPaymentRegisterRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<AccountPaymentRegister> findAllByExportedIsNotNull() {
        return jdbcTemplate.query(SELECT_ALL_WHERE_EXPORTED_IS_NULL,
                new BeanPropertyRowMapper<>(AccountPaymentRegister.class));
    }

    @Override
    public void markGroupAsExported(List<AccountPaymentRegister> group) {
        jdbcTemplate.batchUpdate(UPDATE_EXPORTED_BY_ID, group, group.size(), (ps, item) -> {
            ps.setInt(1, item.getId());
        });
    }

    @Override
    public void saveBatch(List<AccountPaymentRegister> queue, String schema) {
        int inserted = onInsertAsBatch(queue, INSERT_ACCOUNT_PAYMENT_REGISTER, schema);
        log.info("Inserted {} rows into {}.account_payment_register_exp", inserted, schema);
    }

    private final String SELECT_ALL_WHERE_EXPORTED_IS_NULL = "select * from account_payment_register_exp where exported is null order by operation_sys";
    private final String UPDATE_EXPORTED_BY_ID = "update account_payment_register_exp set exported = 'E' where id = ?";
    private final String INSERT_ACCOUNT_PAYMENT_REGISTER = """
                INSERT INTO %s.account_payment_register_exp (
                    id,
                    currency_id,
                    journal_id,
                    partner_bank_id,
                    custom_user_currency_id,
                    source_currency_id,
                    company_id,
                    partner_id,
                    payment_method_line_id,
                    writeoff_account_id,
                    create_uid,
                    write_uid,
                    communication,
                    installments_mode,
                    payment_type,
                    partner_type,
                    payment_difference_handling,
                    writeoff_label,
                    payment_date,
                    amount,
                    custom_user_amount,
                    source_amount,
                    source_amount_currency,
                    group_payment,
                    can_edit_wizard,
                    can_group_payments,
                    create_date,
                    write_date,
                    payment_token_id,
                    operation,
                    operation_sys,
                    exported
                ) VALUES (
                    :id,
                    :currencyId,
                    :journalId,
                    :partnerBankId,
                    :customUserCurrencyId,
                    :sourceCurrencyId,
                    :companyId,
                    :partnerId,
                    :paymentMethodLineId,
                    :writeoffAccountId,
                    :createUid,
                    :writeUid,
                    :communication,
                    :installmentsMode,
                    :paymentType,
                    :partnerType,
                    :paymentDifferenceHandling,
                    :writeoffLabel,
                    :paymentDate,
                    :amount,
                    :customUserAmount,
                    :sourceAmount,
                    :sourceAmountCurrency,
                    :groupPayment,
                    :canEditWizard,
                    :canGroupPayments,
                    :createDate,
                    :writeDate,
                    :paymentTokenId,
                    :operation,
                    :operationSys,
                    :exported
                )
            """;

}
