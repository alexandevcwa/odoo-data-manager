package com.odoo.manager.repo;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.odoo.manager.model.SaleOrder;

@Slf4j
@Repository
public class SaleOrderRepo extends AbstractBaseRepo<SaleOrder> {

    public SaleOrderRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<SaleOrder> findAllByExportedIsNotNull() {
        return jdbcTemplate.query(SELECT_ALL_WHERE_EXPORTED_IS_NULL,
                new BeanPropertyRowMapper<>(SaleOrder.class));
    }

    @Override
    public void markGroupAsExported(List<SaleOrder> group) {
        jdbcTemplate.batchUpdate(UPDATE_EXPORTED_BY_ID, group, group.size(), (ps, item) -> {
            ps.setInt(1, item.getId());
        });
    }

    @Override
    public void saveBatch(List<SaleOrder> list, String schema) {
        int inserted = onInsertAsBatch(list, INSERT_SALE_ORDER, schema);
        log.info("Inserted {} rows into {}.sale_order_exp", inserted, schema);
    }

    private final String SELECT_ALL_WHERE_EXPORTED_IS_NULL = "select * from sale_order_exp where exported is null order by operation_sys";
    private final String UPDATE_EXPORTED_BY_ID = "update sale_order_exp set exported = 'E' where id = ?";
    private final String INSERT_SALE_ORDER = """
                INSERT INTO %s.sale_order_exp (
                    id,
                    campaign_id,
                    source_id,
                    medium_id,
                    company_id,
                    partner_id,
                    journal_id,
                    partner_invoice_id,
                    partner_shipping_id,
                    fiscal_position_id,
                    payment_term_id,
                    pricelist_id,
                    currency_id,
                    user_id,
                    team_id,
                    create_uid,
                    write_uid,
                    access_token,
                    "name",
                    state,
                    client_order_ref,
                    origin,
                    reference,
                    signed_by,
                    invoice_status,
                    validity_date,
                    note,
                    currency_rate,
                    amount_untaxed,
                    amount_tax,
                    amount_total,
                    "locked",
                    require_signature,
                    require_payment,
                    create_date,
                    commitment_date,
                    date_order,
                    signed_on,
                    write_date,
                    prepayment_percent,
                    pending_email_template_id,
                    sale_order_template_id,
                    customizable_pdf_form_fields,
                    project_id,
                    incoterm,
                    warehouse_id,
                    procurement_group_id,
                    incoterm_location,
                    picking_policy,
                    delivery_status,
                    effective_date,
                    amount_unpaid,
                    operation,
                    operation_sys,
                    exported
                ) VALUES (
                    :id,
                    :campaignId,
                    :sourceId,
                    :mediumId,
                    :companyId,
                    :partnerId,
                    :journalId,
                    :partnerInvoiceId,
                    :partnerShippingId,
                    :fiscalPositionId,
                    :paymentTermId,
                    :pricelistId,
                    :currencyId,
                    :userId,
                    :teamId,
                    :createUid,
                    :writeUid,
                    :accessToken,
                    :name,
                    :state,
                    :clientOrderRef,
                    :origin,
                    :reference,
                    :signedBy,
                    :invoiceStatus,
                    :validityDate,
                    :note,
                    :currencyRate,
                    :amountUntaxed,
                    :amountTax,
                    :amountTotal,
                    :locked,
                    :requireSignature,
                    :requirePayment,
                    :createDate,
                    :commitmentDate,
                    :dateOrder,
                    :signedOn,
                    :writeDate,
                    :prepaymentPercent,
                    :pendingEmailTemplateId,
                    :saleOrderTemplateId,
                    CAST(:customizablePdfFormFields AS jsonb),
                    :projectId,
                    :incoterm,
                    :warehouseId,
                    :procurementGroupId,
                    :incotermLocation,
                    :pickingPolicy,
                    :deliveryStatus,
                    :effectiveDate,
                    :amountUnpaid,
                    :operation,
                    :operationSys,
                    :exported
                )
            """;

}
