package com.odoo.manager.repo;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.odoo.manager.model.ProductProduct;

@Slf4j
@Repository
public class ProductProductRepo extends AbstractBaseRepo<ProductProduct> {

    public ProductProductRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<ProductProduct> findAllByExportedIsNotNull() {
        return jdbcTemplate.query(SELECT_ALL_WHERE_EXPORTED_IS_NULL,
                new BeanPropertyRowMapper<>(ProductProduct.class));
    }

    @Override
    public void markGroupAsExported(List<ProductProduct> group) {
        jdbcTemplate.batchUpdate(UPDATE_EXPORTED_BY_ID, group, group.size(), (ps, item) -> {
            ps.setInt(1, item.getId());
        });
    }

    @Override
    public void saveBatch(List<ProductProduct> list, String schema) {
        int inserted = onInsertAsBatch(list, INSERT_PRODUCT_PRODUCT, schema);
        log.info("Inserted {} rows into {}.product_product_exp", inserted, schema);
    }

    private final String SELECT_ALL_WHERE_EXPORTED_IS_NULL = "select * from product_product_exp where exported is null order by operation_sys";
    private final String UPDATE_EXPORTED_BY_ID = "update product_product_exp set exported = 'E' where id = ?";
    private final String INSERT_PRODUCT_PRODUCT = """
    INSERT INTO %s.product_product_exp (
        id,
        product_tmpl_id,
        create_uid,
        write_uid,
        default_code,
        barcode,
        combination_indices,
        standard_price,
        volume,
        weight,
        active,
        can_image_variant_1024_be_zoomed,
        write_date,
        create_date,
        lot_properties_definition,
        operation,
        operation_sys,
        exported
    ) VALUES (
        :id,
        :productTmplId,
        :createUid,
        :writeUid,
        :defaultCode,
        :barcode,
        :combinationIndices,
        CAST(:standardPrice AS jsonb),
        :volume,
        :weight,
        :active,
        :canImageVariant1024BeZoomed,
        :writeDate,
        :createDate,
        CAST(:lotPropertiesDefinition AS jsonb),
        :operation,
        :operationSys,
        :exported
    )
""";

}
