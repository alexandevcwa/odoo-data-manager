package com.odoo.manager.repo;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.odoo.manager.model.ResPartner;

@Slf4j
@Repository
public class ResPartnerRepo extends AbstractBaseRepo<ResPartner> {

    public ResPartnerRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<ResPartner> findAllByExportedIsNotNull() {
        return jdbcTemplate.query(SELECT_ALL_WHERE_EXPORTED_IS_NULL,
                new BeanPropertyRowMapper<>(ResPartner.class));
    }

    @Override
    public void markGroupAsExported(List<ResPartner> group) {
        jdbcTemplate.batchUpdate(UPDATE_EXPORTED_BY_ID, group, group.size(), (ps, item) -> {
            ps.setInt(1, item.getId());
        });
    }

    @Override
    public void saveBatch(List<ResPartner> list, String schema) {
        int inserted = onInsertAsBatch(list, String.format(INSERT_RES_PARTNER, schema), schema);
        log.info("Inserted {} rows into {}.res_partner_exp", inserted, schema);
    }

    private final String SELECT_ALL_WHERE_EXPORTED_IS_NULL = "select * from res_partner_exp where exported is null order by operation_sys";
    private final String UPDATE_EXPORTED_BY_ID = "update res_partner_exp set exported = 'E' where id = ?";

    private final String INSERT_RES_PARTNER = """
    INSERT INTO %s.res_partner_exp (
        id,
        company_id,
        create_date,
        "name",
        title,
        parent_id,
        user_id,
        state_id,
        country_id,
        industry_id,
        color,
        commercial_partner_id,
        create_uid,
        write_uid,
        complete_name,
        "ref",
        lang,
        tz,
        vat,
        company_registry,
        website,
        "function",
        "type",
        street,
        street2,
        zip,
        city,
        email,
        phone,
        mobile,
        commercial_company_name,
        company_name,
        barcode,
        "comment",
        partner_latitude,
        partner_longitude,
        active,
        employee,
        is_company,
        partner_share,
        write_date,
        message_bounce,
        email_normalized,
        signup_type,
        specific_property_product_pricelist,
        partner_gid,
        additional_info,
        phone_sanitized,
        invoice_template_pdf_report_id,
        supplier_rank,
        customer_rank,
        invoice_warn,
        autopost_bills,
        credit_limit,
        property_account_payable_id,
        property_account_receivable_id,
        property_account_position_id,
        property_payment_term_id,
        property_supplier_payment_term_id,
        trust,
        ignore_abnormal_invoice_date,
        ignore_abnormal_invoice_amount,
        invoice_sending_method,
        invoice_edi_format_store,
        property_outbound_payment_method_line_id,
        property_inbound_payment_method_line_id,
        invoice_warn_msg,
        debit_limit,
        peppol_endpoint,
        peppol_eas,
        sale_warn,
        sale_warn_msg,
        picking_warn,
        property_stock_customer,
        property_stock_supplier,
        picking_warn_msg,
        operation,
        operation_sys,
        exported
    ) VALUES (
        :id,
        :companyId,
        :createDate,
        :name,
        :title,
        :parentId,
        :userId,
        :stateId,
        :countryId,
        :industryId,
        :color,
        :commercialPartnerId,
        :createUid,
        :writeUid,
        :completeName,
        :ref,
        :lang,
        :tz,
        :vat,
        :companyRegistry,
        :website,
        :function,
        :type,
        :street,
        :street2,
        :zip,
        :city,
        :email,
        :phone,
        :mobile,
        :commercialCompanyName,
        :companyName,
        CAST(:barcode AS jsonb),
        :comment,
        :partnerLatitude,
        :partnerLongitude,
        :active,
        :employee,
        :isCompany,
        :partnerShare,
        :writeDate,
        :messageBounce,
        :emailNormalized,
        :signupType,
        CAST(:specificPropertyProductPricelist AS jsonb),
        :partnerGid,
        :additionalInfo,
        :phoneSanitized,
        :invoiceTemplatePdfReportId,
        :supplierRank,
        :customerRank,
        :invoiceWarn,
        :autopostBills,
        CAST(:creditLimit AS jsonb),
        CAST(:propertyAccountPayableId AS jsonb),
        CAST(:propertyAccountReceivableId AS jsonb),
        CAST(:propertyAccountPositionId AS jsonb),
        CAST(:propertyPaymentTermId AS jsonb),
        CAST(:propertySupplierPaymentTermId AS jsonb),
        CAST(:trust AS jsonb),
        CAST(:ignoreAbnormalInvoiceDate AS jsonb),
        CAST(:ignoreAbnormalInvoiceAmount AS jsonb),
        CAST(:invoiceSendingMethod AS jsonb),
        CAST(:invoiceEdiFormatStore AS jsonb),
        CAST(:propertyOutboundPaymentMethodLineId AS jsonb),
        CAST(:propertyInboundPaymentMethodLineId AS jsonb),
        :invoiceWarnMsg,
        :debitLimit,
        :peppolEndpoint,
        :peppolEas,
        :saleWarn,
        :saleWarnMsg,
        :pickingWarn,
        CAST(:propertyStockCustomer AS jsonb),
        CAST(:propertyStockSupplier AS jsonb),
        :pickingWarnMsg,
        :operation,
        :operationSys,
        :exported
    )
""";


}
