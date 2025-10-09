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
public class ResPartner {

    @JacksonXmlProperty(localName = "id")
    private Integer id;

    @JacksonXmlProperty(localName = "companyId")
    private Integer companyId;

    @JacksonXmlProperty(localName = "createDate")
    private LocalDateTime createDate;

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "title")
    private Integer title;

    @JacksonXmlProperty(localName = "parentId")
    private Integer parentId;

    @JacksonXmlProperty(localName = "userId")
    private Integer userId;

    @JacksonXmlProperty(localName = "stateId")
    private Integer stateId;

    @JacksonXmlProperty(localName = "countryId")
    private Integer countryId;

    @JacksonXmlProperty(localName = "industryId")
    private Integer industryId;

    @JacksonXmlProperty(localName = "color")
    private Integer color;

    @JacksonXmlProperty(localName = "commercialPartnerId")
    private Integer commercialPartnerId;

    @JacksonXmlProperty(localName = "createUid")
    private Integer createUid;

    @JacksonXmlProperty(localName = "writeUid")
    private Integer writeUid;

    @JacksonXmlProperty(localName = "completeName")
    private String completeName;

    @JacksonXmlProperty(localName = "ref")
    private String ref;

    @JacksonXmlProperty(localName = "lang")
    private String lang;

    @JacksonXmlProperty(localName = "tz")
    private String tz;

    @JacksonXmlProperty(localName = "vat")
    private String vat;

    @JacksonXmlProperty(localName = "companyRegistry")
    private String companyRegistry;

    @JacksonXmlProperty(localName = "website")
    private String website;

    @JacksonXmlProperty(localName = "function")
    private String function;

    @JacksonXmlProperty(localName = "type")
    private String type;

    @JacksonXmlProperty(localName = "street")
    private String street;

    @JacksonXmlProperty(localName = "street2")
    private String street2;

    @JacksonXmlProperty(localName = "zip")
    private String zip;

    @JacksonXmlProperty(localName = "city")
    private String city;

    @JacksonXmlProperty(localName = "email")
    private String email;

    @JacksonXmlProperty(localName = "phone")
    private String phone;

    @JacksonXmlProperty(localName = "mobile")
    private String mobile;

    @JacksonXmlProperty(localName = "commercialCompanyName")
    private String commercialCompanyName;

    @JacksonXmlProperty(localName = "companyName")
    private String companyName;

    @JacksonXmlProperty(localName = "barcode")
    private String barcode; // jsonb → String

    @JacksonXmlProperty(localName = "comment")
    private String comment;

    @JacksonXmlProperty(localName = "partnerLatitude")
    private BigDecimal partnerLatitude;

    @JacksonXmlProperty(localName = "partnerLongitude")
    private BigDecimal partnerLongitude;

    @JacksonXmlProperty(localName = "active")
    private Boolean active;

    @JacksonXmlProperty(localName = "employee")
    private Boolean employee;

    @JacksonXmlProperty(localName = "isCompany")
    private Boolean isCompany;

    @JacksonXmlProperty(localName = "partnerShare")
    private Boolean partnerShare;

    @JacksonXmlProperty(localName = "writeDate")
    private LocalDateTime writeDate;

    @JacksonXmlProperty(localName = "messageBounce")
    private Integer messageBounce;

    @JacksonXmlProperty(localName = "emailNormalized")
    private String emailNormalized;

    @JacksonXmlProperty(localName = "signupType")
    private String signupType;

    @JacksonXmlProperty(localName = "specificPropertyProductPricelist")
    private String specificPropertyProductPricelist; // jsonb → String

    @JacksonXmlProperty(localName = "partnerGid")
    private Integer partnerGid;

    @JacksonXmlProperty(localName = "additionalInfo")
    private String additionalInfo;

    @JacksonXmlProperty(localName = "phoneSanitized")
    private String phoneSanitized;

    @JacksonXmlProperty(localName = "invoiceTemplatePdfReportId")
    private Integer invoiceTemplatePdfReportId;

    @JacksonXmlProperty(localName = "supplierRank")
    private Integer supplierRank;

    @JacksonXmlProperty(localName = "customerRank")
    private Integer customerRank;

    @JacksonXmlProperty(localName = "invoiceWarn")
    private String invoiceWarn;

    @JacksonXmlProperty(localName = "autopostBills")
    private String autopostBills;

    @JacksonXmlProperty(localName = "creditLimit")
    private String creditLimit; // jsonb → String

    @JacksonXmlProperty(localName = "propertyAccountPayableId")
    private String propertyAccountPayableId; // jsonb → String

    @JacksonXmlProperty(localName = "propertyAccountReceivableId")
    private String propertyAccountReceivableId; // jsonb → String

    @JacksonXmlProperty(localName = "propertyAccountPositionId")
    private String propertyAccountPositionId; // jsonb → String

    @JacksonXmlProperty(localName = "propertyPaymentTermId")
    private String propertyPaymentTermId; // jsonb → String

    @JacksonXmlProperty(localName = "propertySupplierPaymentTermId")
    private String propertySupplierPaymentTermId; // jsonb → String

    @JacksonXmlProperty(localName = "trust")
    private String trust; // jsonb → String

    @JacksonXmlProperty(localName = "ignoreAbnormalInvoiceDate")
    private String ignoreAbnormalInvoiceDate; // jsonb → String

    @JacksonXmlProperty(localName = "ignoreAbnormalInvoiceAmount")
    private String ignoreAbnormalInvoiceAmount; // jsonb → String

    @JacksonXmlProperty(localName = "invoiceSendingMethod")
    private String invoiceSendingMethod; // jsonb → String

    @JacksonXmlProperty(localName = "invoiceEdiFormatStore")
    private String invoiceEdiFormatStore; // jsonb → String

    @JacksonXmlProperty(localName = "propertyOutboundPaymentMethodLineId")
    private String propertyOutboundPaymentMethodLineId; // jsonb → String

    @JacksonXmlProperty(localName = "propertyInboundPaymentMethodLineId")
    private String propertyInboundPaymentMethodLineId; // jsonb → String

    @JacksonXmlProperty(localName = "invoiceWarnMsg")
    private String invoiceWarnMsg;

    @JacksonXmlProperty(localName = "debitLimit")
    private BigDecimal debitLimit;

    @JacksonXmlProperty(localName = "peppolEndpoint")
    private String peppolEndpoint;

    @JacksonXmlProperty(localName = "peppolEas")
    private String peppolEas;

    @JacksonXmlProperty(localName = "saleWarn")
    private String saleWarn;

    @JacksonXmlProperty(localName = "saleWarnMsg")
    private String saleWarnMsg;

    @JacksonXmlProperty(localName = "pickingWarn")
    private String pickingWarn;

    @JacksonXmlProperty(localName = "propertyStockCustomer")
    private String propertyStockCustomer; // jsonb → String

    @JacksonXmlProperty(localName = "propertyStockSupplier")
    private String propertyStockSupplier; // jsonb → String

    @JacksonXmlProperty(localName = "pickingWarnMsg")
    private String pickingWarnMsg;

    @JacksonXmlProperty(localName = "operation")
    private String operation; // varchar(1)

    @JacksonXmlProperty(localName = "operationSys")
    private LocalDateTime operationSys;

    @JacksonXmlProperty(localName = "exported")
    private String exported;
}
