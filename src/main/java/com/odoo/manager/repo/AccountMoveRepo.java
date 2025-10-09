package com.odoo.manager.repo;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.odoo.manager.model.AccountMove;

import lombok.RequiredArgsConstructor;

@Slf4j
@Repository
public class AccountMoveRepo extends AbstractBaseRepo<AccountMove> {

    public AccountMoveRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public List<AccountMove> findAllByExportedIsNotNull() {
        return jdbcTemplate.query(SELECT_ALL_WHERE_EXPORTED_IS_NULL, new BeanPropertyRowMapper<>(AccountMove.class));
    }

    @Override
    public void markGroupAsExported(List<AccountMove> group) {
        jdbcTemplate.batchUpdate(UPDATE_EXPORTED_BY_ID, group, group.size(), (ps, item) -> {
            ps.setInt(1, item.getId());
        });
    }

    @Override
    public void saveBatch(List<AccountMove> queue, String schema) {
        int inserted = onInsertAsBatch(queue, INSERT_ACCOUNT_MOVE, schema);
        log.info("Inserted {} rows into {}.account_move_exp", inserted, schema);
    }

    private final String SELECT_ALL_WHERE_EXPORTED_IS_NULL = "select * from account_move_exp where exported is null order by operation_sys";
    private final String UPDATE_EXPORTED_BY_ID = "update account_move_exp set exported = 'E' where id = ?";
    private final String INSERT_ACCOUNT_MOVE = """
                INSERT INTO %s.account_move_exp (
                    id,
                    sequence_number,
                    message_main_attachment_id,
                    journal_id,
                    company_id,
                    origin_payment_id,
                    statement_line_id,
                    tax_cash_basis_rec_id,
                    tax_cash_basis_origin_move_id,
                    auto_post_origin_id,
                    secure_sequence_number,
                    invoice_payment_term_id,
                    partner_id,
                    commercial_partner_id,
                    partner_shipping_id,
                    partner_bank_id,
                    fiscal_position_id,
                    preferred_payment_method_line_id,
                    currency_id,
                    reversed_entry_id,
                    invoice_user_id,
                    invoice_incoterm_id,
                    invoice_cash_rounding_id,
                    create_uid,
                    write_uid,
                    sequence_prefix,
                    access_token,
                    "name",
                    "ref",
                    state,
                    move_type,
                    auto_post,
                    inalterable_hash,
                    payment_reference,
                    qr_code_method,
                    payment_state,
                    invoice_source_email,
                    invoice_partner_display_name,
                    invoice_origin,
                    incoterm_location,
                    "date",
                    auto_post_until,
                    invoice_date,
                    invoice_date_due,
                    delivery_date,
                    sending_data,
                    narration,
                    invoice_currency_rate,
                    amount_untaxed,
                    amount_tax,
                    amount_total,
                    amount_residual,
                    amount_untaxed_signed,
                    amount_untaxed_in_currency_signed,
                    amount_tax_signed,
                    amount_total_signed,
                    amount_total_in_currency_signed,
                    amount_residual_signed,
                    quick_edit_total_amount,
                    is_storno,
                    always_tax_exigible,
                    checked,
                    posted_before,
                    made_sequence_gap,
                    is_manually_modified,
                    is_move_sent,
                    create_date,
                    write_date,
                    campaign_id,
                    source_id,
                    medium_id,
                    team_id,
                    stock_move_id,
                    reversed_pos_order_id,
                    operation,
                    operation_sys,
                    exported
                ) VALUES (
                    :id,
                    :sequenceNumber,
                    :messageMainAttachmentId,
                    :journalId,
                    :companyId,
                    :originPaymentId,
                    :statementLineId,
                    :taxCashBasisRecId,
                    :taxCashBasisOriginMoveId,
                    :autoPostOriginId,
                    :secureSequenceNumber,
                    :invoicePaymentTermId,
                    :partnerId,
                    :commercialPartnerId,
                    :partnerShippingId,
                    :partnerBankId,
                    :fiscalPositionId,
                    :preferredPaymentMethodLineId,
                    :currencyId,
                    :reversedEntryId,
                    :invoiceUserId,
                    :invoiceIncotermId,
                    :invoiceCashRoundingId,
                    :createUid,
                    :writeUid,
                    :sequencePrefix,
                    :accessToken,
                    :name,
                    :ref,
                    :state,
                    :moveType,
                    :autoPost,
                    :inalterableHash,
                    :paymentReference,
                    :qrCodeMethod,
                    :paymentState,
                    :invoiceSourceEmail,
                    :invoicePartnerDisplayName,
                    :invoiceOrigin,
                    :incotermLocation,
                    :date,
                    :autoPostUntil,
                    :invoiceDate,
                    :invoiceDateDue,
                    :deliveryDate,
                    CAST(:sendingData AS jsonb),
                    :narration,
                    :invoiceCurrencyRate,
                    :amountUntaxed,
                    :amountTax,
                    :amountTotal,
                    :amountResidual,
                    :amountUntaxedSigned,
                    :amountUntaxedInCurrencySigned,
                    :amountTaxSigned,
                    :amountTotalSigned,
                    :amountTotalInCurrencySigned,
                    :amountResidualSigned,
                    :quickEditTotalAmount,
                    :isStorno,
                    :alwaysTaxExigible,
                    :checked,
                    :postedBefore,
                    :madeSequenceGap,
                    :isManuallyModified,
                    :isMoveSent,
                    :createDate,
                    :writeDate,
                    :campaignId,
                    :sourceId,
                    :mediumId,
                    :teamId,
                    :stockMoveId,
                    :reversedPosOrderId,
                    :operation,
                    :operationSys,
                    :exported
                )
            """;


}
