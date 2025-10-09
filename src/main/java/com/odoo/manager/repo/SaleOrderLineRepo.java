package com.odoo.manager.repo;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.odoo.manager.model.SaleOrderLine;

import lombok.RequiredArgsConstructor;

@Slf4j
@Repository
public class SaleOrderLineRepo extends AbstractBaseRepo<SaleOrderLine> {

    public SaleOrderLineRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<SaleOrderLine> findAllByExportedIsNotNull() {
        return jdbcTemplate.query(SELECT_ALL_WHERE_EXPORTED_IS_NULL,
                new BeanPropertyRowMapper<>(SaleOrderLine.class));
    }

    @Override
    public void markGroupAsExported(List<SaleOrderLine> group) {
        jdbcTemplate.batchUpdate(UPDATE_EXPORTED_BY_ID, group, group.size(), (ps, item) -> {
            ps.setInt(1, item.getId());
        });
    }

    @Override
    public void saveBatch(List<SaleOrderLine> queue, String schema) {
        int inserted = onInsertAsBatch(queue, INSERT_SALE_ORDER_LINE, schema);
        log.info("Inserted {} rows into {}.sale_order_line_exp", inserted, schema);
    }

    private final String SELECT_ALL_WHERE_EXPORTED_IS_NULL = "select * from sale_order_line_exp where exported is null order by operation_sys";
    private final String UPDATE_EXPORTED_BY_ID = "update sale_order_line_exp set exported = 'E' where id = ?";
    private final String INSERT_SALE_ORDER_LINE = """
                INSERT INTO %s.sale_order_line_exp (
                    id,
                    order_id,
                    "sequence",
                    company_id,
                    currency_id,
                    order_partner_id,
                    salesman_id,
                    product_id,
                    product_uom,
                    linked_line_id,
                    combo_item_id,
                    product_packaging_id,
                    create_uid,
                    write_uid,
                    state,
                    display_type,
                    virtual_id,
                    linked_virtual_id,
                    qty_delivered_method,
                    invoice_status,
                    analytic_distribution,
                    "name",
                    product_uom_qty,
                    price_unit,
                    discount,
                    price_subtotal,
                    price_total,
                    price_reduce_taxexcl,
                    price_reduce_taxinc,
                    qty_delivered,
                    qty_invoiced,
                    qty_to_invoice,
                    untaxed_amount_invoiced,
                    untaxed_amount_to_invoice,
                    is_downpayment,
                    is_expense,
                    create_date,
                    write_date,
                    technical_price_unit,
                    price_tax,
                    product_packaging_qty,
                    customer_lead,
                    is_service,
                    project_id,
                    task_id,
                    route_id,
                    warehouse_id,
                    operation,
                    operation_sys,
                    exported
                ) VALUES (
                    :id,
                    :orderId,
                    :sequence,
                    :companyId,
                    :currencyId,
                    :orderPartnerId,
                    :salesmanId,
                    :productId,
                    :productUom,
                    :linkedLineId,
                    :comboItemId,
                    :productPackagingId,
                    :createUid,
                    :writeUid,
                    :state,
                    :displayType,
                    :virtualId,
                    :linkedVirtualId,
                    :qtyDeliveredMethod,
                    :invoiceStatus,
                    CAST(:analyticDistribution AS jsonb),
                    :name,
                    :productUomQty,
                    :priceUnit,
                    :discount,
                    :priceSubtotal,
                    :priceTotal,
                    :priceReduceTaxexcl,
                    :priceReduceTaxinc,
                    :qtyDelivered,
                    :qtyInvoiced,
                    :qtyToInvoice,
                    :untaxedAmountInvoiced,
                    :untaxedAmountToInvoice,
                    :isDownpayment,
                    :isExpense,
                    :createDate,
                    :writeDate,
                    :technicalPriceUnit,
                    :priceTax,
                    :productPackagingQty,
                    :customerLead,
                    :isService,
                    :projectId,
                    :taskId,
                    :routeId,
                    :warehouseId,
                    :operation,
                    :operationSys,
                    :exported
                )
            """;

}
