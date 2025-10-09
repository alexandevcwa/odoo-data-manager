package com.odoo.manager.repo;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.odoo.manager.model.ProductTemplate;

@Slf4j
@Repository
public class ProductTemplateBaseRepo extends AbstractBaseRepo<ProductTemplate>{

    public ProductTemplateBaseRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<ProductTemplate> findAllByExportedIsNotNull() {
        return jdbcTemplate.query(SELECT_ALL_WHERE_EXPORTED_IS_NULL,
                new BeanPropertyRowMapper<>(ProductTemplate.class));
    }

    @Override
    public void markGroupAsExported(List<ProductTemplate> group) {
        jdbcTemplate.batchUpdate(UPDATE_EXPORTED_BY_ID, group, group.size(), (ps, item) -> {
            ps.setInt(1, item.getId());
        });
    }

    @Override
    public void saveBatch(List<ProductTemplate> queue, String schema) {
        int inserted = onInsertAsBatch(queue, INSERT_PRODUCT_TEMPLATE, schema);
        log.info("Inserted {} rows into {}.product_template_exp", inserted, schema);
    }

    private final String SELECT_ALL_WHERE_EXPORTED_IS_NULL = "select * from product_template_exp where exported is null order by operation_sys";
    private final String UPDATE_EXPORTED_BY_ID = "update product_template_exp set exported = 'E' where id = ?";
    private final String INSERT_PRODUCT_TEMPLATE =  """
                INSERT INTO %s.product_template_exp (
                    id,
                    "sequence",
                    categ_id,
                    uom_id,
                    uom_po_id,
                    company_id,
                    color,
                    create_uid,
                    write_uid,
                    "type",
                    service_tracking,
                    default_code,
                    "name",
                    description,
                    description_purchase,
                    description_sale,
                    product_properties,
                    list_price,
                    volume,
                    weight,
                    sale_ok,
                    purchase_ok,
                    active,
                    can_image_1024_be_zoomed,
                    has_configurable_attributes,
                    is_favorite,
                    create_date,
                    write_date,
                    property_account_income_id,
                    property_account_expense_id,
                    service_type,
                    sale_line_warn,
                    expense_policy,
                    invoice_policy,
                    sale_line_warn_msg,
                    project_id,
                    project_template_id,
                    sale_delay,
                    tracking,
                    responsible_id,
                    property_stock_production,
                    property_stock_inventory,
                    description_picking,
                    description_pickingout,
                    description_pickingin,
                    is_storable,
                    lot_valuated,
                    public_description,
                    available_in_pos,
                    to_weight,
                    operation,
                    operation_sys,
                    exported
                ) VALUES (
                    :id,
                    :sequence,
                    :categId,
                    :uomId,
                    :uomPoId,
                    :companyId,
                    :color,
                    :createUid,
                    :writeUid,
                    :type,
                    :serviceTracking,
                    :defaultCode,
                    CAST(:name AS jsonb),
                    CAST(:description AS jsonb),
                    CAST(:descriptionPurchase AS jsonb),
                    CAST(:descriptionSale AS jsonb),
                    CAST(:productProperties AS jsonb),
                    :listPrice,
                    :volume,
                    :weight,
                    :saleOk,
                    :purchaseOk,
                    :active,
                    :canImage1024BeZoomed,
                    :hasConfigurableAttributes,
                    :isFavorite,
                    :createDate,
                    :writeDate,
                    CAST(:propertyAccountIncomeId AS jsonb),
                    CAST(:propertyAccountExpenseId AS jsonb),
                    :serviceType,
                    :saleLineWarn,
                    :expensePolicy,
                    :invoicePolicy,
                    :saleLineWarnMsg,
                    CAST(:projectId AS jsonb),
                    CAST(:projectTemplateId AS jsonb),
                    :saleDelay,
                    :tracking,
                    CAST(:responsibleId AS jsonb),
                    CAST(:propertyStockProduction AS jsonb),
                    CAST(:propertyStockInventory AS jsonb),
                    CAST(:descriptionPicking AS jsonb),
                    CAST(:descriptionPickingOut AS jsonb),
                    CAST(:descriptionPickingIn AS jsonb),
                    :isStorable,
                    :lotValuated,
                    CAST(:publicDescription AS jsonb),
                    :availableInPos,
                    :toWeight,
                    :operation,
                    :operationSys,
                    :exported
                )
            """;
}
