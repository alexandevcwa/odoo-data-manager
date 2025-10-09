package com.odoo.manager.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductTemplate {
    @JacksonXmlProperty(localName = "id")
    private Integer id;

    @JacksonXmlProperty(localName = "sequence")
    private Integer sequence;

    @JacksonXmlProperty(localName = "categId")
    private int categId;

    @JacksonXmlProperty(localName = "uomId")
    private int uomId;

    @JacksonXmlProperty(localName = "uomPoId")
    private int uomPoId;

    @JacksonXmlProperty(localName = "companyId")
    private Integer companyId;

    @JacksonXmlProperty(localName = "color")
    private Integer color;

    @JacksonXmlProperty(localName = "createUid")
    private Integer createUid;

    @JacksonXmlProperty(localName = "writeUid")
    private Integer writeUid;

    @JacksonXmlProperty(localName = "type")
    private String type;

    @JacksonXmlProperty(localName = "serviceTracking")
    private String serviceTracking;

    @JacksonXmlProperty(localName = "defaultCode")
    private String defaultCode;

    @JacksonXmlProperty(localName = "name")
    private String name; // jsonb → String

    @JacksonXmlProperty(localName = "description")
    private String description; // jsonb → String

    @JacksonXmlProperty(localName = "descriptionPurchase")
    private String descriptionPurchase;

    @JacksonXmlProperty(localName = "descriptionSale")
    private String descriptionSale;

    @JacksonXmlProperty(localName = "productProperties")
    private String productProperties;

    @JacksonXmlProperty(localName = "listPrice")
    private BigDecimal listPrice;

    @JacksonXmlProperty(localName = "volume")
    private BigDecimal volume;

    @JacksonXmlProperty(localName = "weight")
    private BigDecimal weight;

    @JacksonXmlProperty(localName = "saleOk")
    private Boolean saleOk;

    @JacksonXmlProperty(localName = "purchaseOk")
    private Boolean purchaseOk;

    @JacksonXmlProperty(localName = "active")
    private Boolean active;

    @JacksonXmlProperty(localName = "canImage1024BeZoomed")
    private Boolean canImage1024BeZoomed;

    @JacksonXmlProperty(localName = "hasConfigurableAttributes")
    private Boolean hasConfigurableAttributes;

    @JacksonXmlProperty(localName = "isFavorite")
    private Boolean isFavorite;

    @JacksonXmlProperty(localName = "createDate")
    private LocalDateTime createDate;

    @JacksonXmlProperty(localName = "writeDate")
    private LocalDateTime writeDate;

    @JacksonXmlProperty(localName = "propertyAccountIncomeId")
    private String propertyAccountIncomeId;

    @JacksonXmlProperty(localName = "propertyAccountExpenseId")
    private String propertyAccountExpenseId;

    @JacksonXmlProperty(localName = "serviceType")
    private String serviceType;

    @JacksonXmlProperty(localName = "saleLineWarn")
    private String saleLineWarn;

    @JacksonXmlProperty(localName = "expensePolicy")
    private String expensePolicy;

    @JacksonXmlProperty(localName = "invoicePolicy")
    private String invoicePolicy;

    @JacksonXmlProperty(localName = "saleLineWarnMsg")
    private String saleLineWarnMsg;

    @JacksonXmlProperty(localName = "projectId")
    private String projectId;

    @JacksonXmlProperty(localName = "projectTemplateId")
    private String projectTemplateId;

    @JacksonXmlProperty(localName = "saleDelay")
    private Integer saleDelay;

    @JacksonXmlProperty(localName = "tracking")
    private String tracking;

    @JacksonXmlProperty(localName = "responsibleId")
    private String responsibleId;

    @JacksonXmlProperty(localName = "propertyStockProduction")
    private String propertyStockProduction;

    @JacksonXmlProperty(localName = "propertyStockInventory")
    private String propertyStockInventory;

    @JacksonXmlProperty(localName = "descriptionPicking")
    private String descriptionPicking;

    @JacksonXmlProperty(localName = "descriptionPickingOut")
    private String descriptionPickingOut;

    @JacksonXmlProperty(localName = "descriptionPickingIn")
    private String descriptionPickingIn;

    @JacksonXmlProperty(localName = "isStorable")
    private Boolean isStorable;

    @JacksonXmlProperty(localName = "lotValuated")
    private Boolean lotValuated;

    @JacksonXmlProperty(localName = "publicDescription")
    private String publicDescription;

    @JacksonXmlProperty(localName = "availableInPos")
    private Boolean availableInPos;

    @JacksonXmlProperty(localName = "toWeight")
    private Boolean toWeight;

    @JacksonXmlProperty(localName = "operation")
    private String operation;

    @JacksonXmlProperty(localName = "operationSys")
    private LocalDateTime operationSys;

    @JacksonXmlProperty(localName = "exported")
    private String exported;
}
