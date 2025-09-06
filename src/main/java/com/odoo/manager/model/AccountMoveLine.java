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
public class AccountMoveLine {
    @JacksonXmlProperty(localName = "id")
    private Integer id;

    @JacksonXmlProperty(localName = "moveId")
    private int moveId;

    @JacksonXmlProperty(localName = "journalId")
    private Integer journalId;

    @JacksonXmlProperty(localName = "companyId")
    private Integer companyId;

    @JacksonXmlProperty(localName = "companyCurrencyId")
    private Integer companyCurrencyId;

    @JacksonXmlProperty(localName = "sequence")
    private Integer sequence;

    @JacksonXmlProperty(localName = "accountId")
    private Integer accountId;

    @JacksonXmlProperty(localName = "currencyId")
    private int currencyId;

    @JacksonXmlProperty(localName = "partnerId")
    private Integer partnerId;

    @JacksonXmlProperty(localName = "reconcileModelId")
    private Integer reconcileModelId;

    @JacksonXmlProperty(localName = "paymentId")
    private Integer paymentId;

    @JacksonXmlProperty(localName = "statementLineId")
    private Integer statementLineId;

    @JacksonXmlProperty(localName = "statementId")
    private Integer statementId;

    @JacksonXmlProperty(localName = "groupTaxId")
    private Integer groupTaxId;

    @JacksonXmlProperty(localName = "taxLineId")
    private Integer taxLineId;

    @JacksonXmlProperty(localName = "taxGroupId")
    private Integer taxGroupId;

    @JacksonXmlProperty(localName = "taxRepartitionLineId")
    private Integer taxRepartitionLineId;

    @JacksonXmlProperty(localName = "fullReconcileId")
    private Integer fullReconcileId;

    @JacksonXmlProperty(localName = "productId")
    private Integer productId;

    @JacksonXmlProperty(localName = "productUomId")
    private Integer productUomId;

    @JacksonXmlProperty(localName = "createUid")
    private Integer createUid;

    @JacksonXmlProperty(localName = "writeUid")
    private Integer writeUid;

    @JacksonXmlProperty(localName = "moveName")
    private String moveName;

    @JacksonXmlProperty(localName = "parentState")
    private String parentState;

    @JacksonXmlProperty(localName = "ref")
    private String ref;

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "matchingNumber")
    private String matchingNumber;

    @JacksonXmlProperty(localName = "displayType")
    private String displayType;

    @JacksonXmlProperty(localName = "date")
    private LocalDate date;

    @JacksonXmlProperty(localName = "invoiceDate")
    private LocalDate invoiceDate;

    @JacksonXmlProperty(localName = "dateMaturity")
    private LocalDate dateMaturity;

    @JacksonXmlProperty(localName = "discountDate")
    private LocalDate discountDate;

    @JacksonXmlProperty(localName = "analyticDistribution")
    private String analyticDistribution; // jsonb → String

    @JacksonXmlProperty(localName = "debit")
    private BigDecimal debit;

    @JacksonXmlProperty(localName = "credit")
    private BigDecimal credit;

    @JacksonXmlProperty(localName = "balance")
    private BigDecimal balance;

    @JacksonXmlProperty(localName = "amountCurrency")
    private BigDecimal amountCurrency;

    @JacksonXmlProperty(localName = "taxBaseAmount")
    private BigDecimal taxBaseAmount;

    @JacksonXmlProperty(localName = "amountResidual")
    private BigDecimal amountResidual;

    @JacksonXmlProperty(localName = "amountResidualCurrency")
    private BigDecimal amountResidualCurrency;

    @JacksonXmlProperty(localName = "quantity")
    private BigDecimal quantity;

    @JacksonXmlProperty(localName = "priceUnit")
    private BigDecimal priceUnit;

    @JacksonXmlProperty(localName = "priceSubtotal")
    private BigDecimal priceSubtotal;

    @JacksonXmlProperty(localName = "priceTotal")
    private BigDecimal priceTotal;

    @JacksonXmlProperty(localName = "discount")
    private BigDecimal discount;

    @JacksonXmlProperty(localName = "discountAmountCurrency")
    private BigDecimal discountAmountCurrency;

    @JacksonXmlProperty(localName = "discountBalance")
    private BigDecimal discountBalance;

    @JacksonXmlProperty(localName = "isImported")
    private Boolean isImported;

    @JacksonXmlProperty(localName = "taxTagInvert")
    private Boolean taxTagInvert;

    @JacksonXmlProperty(localName = "reconciled")
    private Boolean reconciled;

    @JacksonXmlProperty(localName = "createDate")
    private LocalDateTime createDate;

    @JacksonXmlProperty(localName = "writeDate")
    private LocalDateTime writeDate;

    @JacksonXmlProperty(localName = "isDownpayment")
    private Boolean isDownpayment;

    @JacksonXmlProperty(localName = "cogsOriginId")
    private Integer cogsOriginId;

    @JacksonXmlProperty(localName = "operation")
    private String operation; // varchar(1)

    @JacksonXmlProperty(localName = "operationSys")
    private LocalDateTime operationSys;
}
