package com.odoo.manager.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaleOrder {
    @JacksonXmlProperty(localName = "id")
    private Integer id;

    @JacksonXmlProperty(localName = "campaignId")
    private Integer campaignId;

    @JacksonXmlProperty(localName = "sourceId")
    private Integer sourceId;

    @JacksonXmlProperty(localName = "mediumId")
    private Integer mediumId;

    @JacksonXmlProperty(localName = "companyId")
    private int companyId;

    @JacksonXmlProperty(localName = "partnerId")
    private int partnerId;

    @JacksonXmlProperty(localName = "journalId")
    private Integer journalId;

    @JacksonXmlProperty(localName = "partnerInvoiceId")
    private int partnerInvoiceId;

    @JacksonXmlProperty(localName = "partnerShippingId")
    private int partnerShippingId;

    @JacksonXmlProperty(localName = "fiscalPositionId")
    private Integer fiscalPositionId;

    @JacksonXmlProperty(localName = "paymentTermId")
    private Integer paymentTermId;

    @JacksonXmlProperty(localName = "pricelistId")
    private Integer pricelistId;

    @JacksonXmlProperty(localName = "currencyId")
    private Integer currencyId;

    @JacksonXmlProperty(localName = "userId")
    private Integer userId;

    @JacksonXmlProperty(localName = "teamId")
    private Integer teamId;

    @JacksonXmlProperty(localName = "createUid")
    private Integer createUid;

    @JacksonXmlProperty(localName = "writeUid")
    private Integer writeUid;

    @JacksonXmlProperty(localName = "accessToken")
    private String accessToken;

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "state")
    private String state;

    @JacksonXmlProperty(localName = "clientOrderRef")
    private String clientOrderRef;

    @JacksonXmlProperty(localName = "origin")
    private String origin;

    @JacksonXmlProperty(localName = "reference")
    private String reference;

    @JacksonXmlProperty(localName = "signedBy")
    private String signedBy;

    @JacksonXmlProperty(localName = "invoiceStatus")
    private String invoiceStatus;

    @JacksonXmlProperty(localName = "validityDate")
    private LocalDate validityDate;

    @JacksonXmlProperty(localName = "note")
    private String note;

    @JacksonXmlProperty(localName = "currencyRate")
    private BigDecimal currencyRate;

    @JacksonXmlProperty(localName = "amountUntaxed")
    private BigDecimal amountUntaxed;

    @JacksonXmlProperty(localName = "amountTax")
    private BigDecimal amountTax;

    @JacksonXmlProperty(localName = "amountTotal")
    private BigDecimal amountTotal;

    @JacksonXmlProperty(localName = "locked")
    private Boolean locked;

    @JacksonXmlProperty(localName = "requireSignature")
    private Boolean requireSignature;

    @JacksonXmlProperty(localName = "requirePayment")
    private Boolean requirePayment;

    @JacksonXmlProperty(localName = "createDate")
    private LocalDateTime createDate;

    @JacksonXmlProperty(localName = "commitmentDate")
    private LocalDateTime commitmentDate;

    @JacksonXmlProperty(localName = "dateOrder")
    private LocalDateTime dateOrder;

    @JacksonXmlProperty(localName = "signedOn")
    private LocalDateTime signedOn;

    @JacksonXmlProperty(localName = "writeDate")
    private LocalDateTime writeDate;

    @JacksonXmlProperty(localName = "prepaymentPercent")
    private Double prepaymentPercent;

    @JacksonXmlProperty(localName = "pendingEmailTemplateId")
    private Integer pendingEmailTemplateId;

    @JacksonXmlProperty(localName = "saleOrderTemplateId")
    private Integer saleOrderTemplateId;

    @JacksonXmlProperty(localName = "customizablePdfFormFields")
    private String customizablePdfFormFields; // jsonb → String

    @JacksonXmlProperty(localName = "projectId")
    private Integer projectId;

    @JacksonXmlProperty(localName = "incoterm")
    private Integer incoterm;

    @JacksonXmlProperty(localName = "warehouseId")
    private Integer warehouseId;

    @JacksonXmlProperty(localName = "procurementGroupId")
    private Integer procurementGroupId;

    @JacksonXmlProperty(localName = "incotermLocation")
    private String incotermLocation;

    @JacksonXmlProperty(localName = "pickingPolicy")
    private String pickingPolicy;

    @JacksonXmlProperty(localName = "deliveryStatus")
    private String deliveryStatus;

    @JacksonXmlProperty(localName = "effectiveDate")
    private LocalDateTime effectiveDate;

    @JacksonXmlProperty(localName = "amountUnpaid")
    private BigDecimal amountUnpaid;

    @JacksonXmlProperty(localName = "operation")
    private String operation; // varchar(1)

    @JacksonXmlProperty(localName = "operationSys")
    private LocalDateTime operationSys;

    @JacksonXmlProperty(localName = "exported")
    private String exported;
}
