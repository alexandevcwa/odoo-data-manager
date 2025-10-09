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
public class SaleOrderLine {
    @JacksonXmlProperty(localName = "id")
    private Integer id;

    @JacksonXmlProperty(localName = "orderId")
    private int orderId;

    @JacksonXmlProperty(localName = "sequence")
    private Integer sequence;

    @JacksonXmlProperty(localName = "companyId")
    private Integer companyId;

    @JacksonXmlProperty(localName = "currencyId")
    private Integer currencyId;

    @JacksonXmlProperty(localName = "orderPartnerId")
    private Integer orderPartnerId;

    @JacksonXmlProperty(localName = "salesmanId")
    private Integer salesmanId;

    @JacksonXmlProperty(localName = "productId")
    private Integer productId;

    @JacksonXmlProperty(localName = "productUom")
    private Integer productUom;

    @JacksonXmlProperty(localName = "linkedLineId")
    private Integer linkedLineId;

    @JacksonXmlProperty(localName = "comboItemId")
    private Integer comboItemId;

    @JacksonXmlProperty(localName = "productPackagingId")
    private Integer productPackagingId;

    @JacksonXmlProperty(localName = "createUid")
    private Integer createUid;

    @JacksonXmlProperty(localName = "writeUid")
    private Integer writeUid;

    @JacksonXmlProperty(localName = "state")
    private String state;

    @JacksonXmlProperty(localName = "displayType")
    private String displayType;

    @JacksonXmlProperty(localName = "virtualId")
    private String virtualId;

    @JacksonXmlProperty(localName = "linkedVirtualId")
    private String linkedVirtualId;

    @JacksonXmlProperty(localName = "qtyDeliveredMethod")
    private String qtyDeliveredMethod;

    @JacksonXmlProperty(localName = "invoiceStatus")
    private String invoiceStatus;

    @JacksonXmlProperty(localName = "analyticDistribution")
    private String analyticDistribution; // jsonb → String

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "productUomQty")
    private BigDecimal productUomQty;

    @JacksonXmlProperty(localName = "priceUnit")
    private BigDecimal priceUnit;

    @JacksonXmlProperty(localName = "discount")
    private BigDecimal discount;

    @JacksonXmlProperty(localName = "priceSubtotal")
    private BigDecimal priceSubtotal;

    @JacksonXmlProperty(localName = "priceTotal")
    private BigDecimal priceTotal;

    @JacksonXmlProperty(localName = "priceReduceTaxexcl")
    private BigDecimal priceReduceTaxexcl;

    @JacksonXmlProperty(localName = "priceReduceTaxinc")
    private BigDecimal priceReduceTaxinc;

    @JacksonXmlProperty(localName = "qtyDelivered")
    private BigDecimal qtyDelivered;

    @JacksonXmlProperty(localName = "qtyInvoiced")
    private BigDecimal qtyInvoiced;

    @JacksonXmlProperty(localName = "qtyToInvoice")
    private BigDecimal qtyToInvoice;

    @JacksonXmlProperty(localName = "untaxedAmountInvoiced")
    private BigDecimal untaxedAmountInvoiced;

    @JacksonXmlProperty(localName = "untaxedAmountToInvoice")
    private BigDecimal untaxedAmountToInvoice;

    @JacksonXmlProperty(localName = "isDownpayment")
    private Boolean isDownpayment;

    @JacksonXmlProperty(localName = "isExpense")
    private Boolean isExpense;

    @JacksonXmlProperty(localName = "createDate")
    private LocalDateTime createDate;

    @JacksonXmlProperty(localName = "writeDate")
    private LocalDateTime writeDate;

    @JacksonXmlProperty(localName = "technicalPriceUnit")
    private Double technicalPriceUnit;

    @JacksonXmlProperty(localName = "priceTax")
    private Double priceTax;

    @JacksonXmlProperty(localName = "productPackagingQty")
    private Double productPackagingQty;

    @JacksonXmlProperty(localName = "customerLead")
    private Double customerLead;

    @JacksonXmlProperty(localName = "isService")
    private Boolean isService;

    @JacksonXmlProperty(localName = "projectId")
    private Integer projectId;

    @JacksonXmlProperty(localName = "taskId")
    private Integer taskId;

    @JacksonXmlProperty(localName = "routeId")
    private Integer routeId;

    @JacksonXmlProperty(localName = "warehouseId")
    private Integer warehouseId;

    @JacksonXmlProperty(localName = "operation")
    private String operation; // varchar(1)

    @JacksonXmlProperty(localName = "operationSys")
    private LocalDateTime operationSys;

    @JacksonXmlProperty(localName = "exported")
    private String exported;
}
