package com.odoo.manager.repo;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.odoo.manager.model.ProductCategory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ProductCategoryBaseRepo extends AbstractBaseRepo<ProductCategory>{

    public ProductCategoryBaseRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<ProductCategory> findAllByExportedIsNotNull() {
        return jdbcTemplate.query(SELECT_ALL_WHERE_EXPORTED_IS_NULL,
                new BeanPropertyRowMapper<>(ProductCategory.class));
    }

    @Override
    public void markGroupAsExported(List<ProductCategory> group) {
        jdbcTemplate.batchUpdate(UPDATE_EXPORTED_BY_ID, group, group.size(), (ps, item) -> {
            ps.setInt(1, item.getId());
        });
    }

    @Override
    @Transactional
    public void saveBatch(List<ProductCategory> list, String schema) {
        int inserted =  onInsertAsBatch(list,INSERT_PRODUCT_CATEGORY,schema);
        log.info("Inserted {} rows into {}.product_category_exp", inserted, schema);
    }

    private final String SELECT_ALL_WHERE_EXPORTED_IS_NULL = "select * from product_category_exp where exported is null order by operation_sys";
    private final String UPDATE_EXPORTED_BY_ID = "update product_category_exp set exported = 'E' where id = ?";
    private final String INSERT_PRODUCT_CATEGORY = """
            INSERT INTO %s.product_category_exp (
                id,
                parent_id,
                create_uid,
                write_uid,
                "name",
                complete_name,
                parent_path,
                product_properties_definition,
                create_date,
                write_date,
                property_account_income_categ_id,
                property_account_expense_categ_id,
                property_account_downpayment_categ_id,
                removal_strategy_id,
                packaging_reserve_method,
                property_valuation,
                property_cost_method,
                property_stock_journal,
                property_stock_account_input_categ_id,
                property_stock_account_output_categ_id,
                property_stock_valuation_account_id,
                operation,
                operation_sys,
                exported
            ) VALUES (
                :id,
                :parentId,
                :createUid,
                :writeUid,
                :name,
                :completeName,
                :parentPath,
                CAST(:productPropertiesDefinition AS jsonb),
                :createDate,
                :writeDate,
                CAST(:propertyAccountIncomeCategId AS jsonb),
                CAST(:propertyAccountExpenseCategId AS jsonb),
                CAST(:propertyAccountDownpaymentCategId as jsonb),
                :removalStrategyId,
                :packagingReserveMethod,
                CAST(:propertyValuation as jsonb),
                CAST(:propertyCostMethod as jsonb),
                CAST(:propertyStockJournal as jsonb),
                CAST(:propertyStockAccountInputCategId as jsonb),
                CAST(:propertyStockAccountOutputCategId as jsonb),
                CAST(:propertyStockValuationAccountId as jsonb),
                :operation,
                :operationSys,
                :exported
            )""";

}
