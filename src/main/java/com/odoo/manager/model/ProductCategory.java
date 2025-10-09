package com.odoo.manager.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {

    @JacksonXmlProperty
    private Integer id;

    @JacksonXmlProperty(localName = "parentId")
    private Integer parentId;

    @JacksonXmlProperty(localName = "createUid")
    private Integer createUid;

    @JacksonXmlProperty(localName = "writeUid")
    private Integer writeUid;

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "completeName")
    private String completeName;

    @JacksonXmlProperty(localName = "parentPath")
    private String parentPath;

    // jsonb → String para XML
    @JacksonXmlProperty(localName = "productPropertiesDefinition")
    private String productPropertiesDefinition;

    @JacksonXmlProperty(localName = "createDate")
    private LocalDateTime createDate;

    @JacksonXmlProperty(localName = "writeDate")
    private LocalDateTime writeDate;

    @JacksonXmlProperty(localName = "propertyAccountIncomeCategId")
    private String propertyAccountIncomeCategId;

    @JacksonXmlProperty(localName = "propertyAccountExpenseCategId")
    private String propertyAccountExpenseCategId;

    @JacksonXmlProperty(localName = "propertyAccountDownpaymentCategId")
    private String propertyAccountDownpaymentCategId;

    @JacksonXmlProperty(localName = "removalStrategyId")
    private Integer removalStrategyId;

    @JacksonXmlProperty(localName = "packagingReserveMethod")
    private String packagingReserveMethod;

    @JacksonXmlProperty(localName = "propertyValuation")
    private String propertyValuation;

    @JacksonXmlProperty(localName = "propertyCostMethod")
    private String propertyCostMethod;

    @JacksonXmlProperty(localName = "propertyStockJournal")
    private String propertyStockJournal;

    @JacksonXmlProperty(localName = "propertyStockAccountInputCategId")
    private String propertyStockAccountInputCategId;

    @JacksonXmlProperty(localName = "propertyStockAccountOutputCategId")
    private String propertyStockAccountOutputCategId;

    @JacksonXmlProperty(localName = "propertyStockValuationAccountId")
    private String propertyStockValuationAccountId;

    @JacksonXmlProperty(localName = "operation")
    private String operation; // varchar(1)

    @JacksonXmlProperty(localName = "operationSys")
    private LocalDateTime operationSys;

    @JacksonXmlProperty(localName = "exported")
    private String exported; // 'E' cuando se exporta

}
