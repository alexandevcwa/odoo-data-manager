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
public class AccountPaymentRegister {

    @JacksonXmlProperty(localName = "id")
    private Integer id;

    @JacksonXmlProperty(localName = "currencyId")
    private Integer currencyId;

    @JacksonXmlProperty(localName = "journalId")
    private Integer journalId;

    @JacksonXmlProperty(localName = "partnerBankId")
    private Integer partnerBankId;

    @JacksonXmlProperty(localName = "customUserCurrencyId")
    private Integer customUserCurrencyId;

    @JacksonXmlProperty(localName = "sourceCurrencyId")
    private Integer sourceCurrencyId;

    @JacksonXmlProperty(localName = "companyId")
    private Integer companyId;

    @JacksonXmlProperty(localName = "partnerId")
    private Integer partnerId;

    @JacksonXmlProperty(localName = "paymentMethodLineId")
    private Integer paymentMethodLineId;

    @JacksonXmlProperty(localName = "writeoffAccountId")
    private Integer writeoffAccountId;

    @JacksonXmlProperty(localName = "createUid")
    private Integer createUid;

    @JacksonXmlProperty(localName = "writeUid")
    private Integer writeUid;

    @JacksonXmlProperty(localName = "communication")
    private String communication;

    @JacksonXmlProperty(localName = "installmentsMode")
    private String installmentsMode;

    @JacksonXmlProperty(localName = "paymentType")
    private String paymentType;

    @JacksonXmlProperty(localName = "partnerType")
    private String partnerType;

    @JacksonXmlProperty(localName = "paymentDifferenceHandling")
    private String paymentDifferenceHandling;

    @JacksonXmlProperty(localName = "writeoffLabel")
    private String writeoffLabel;

    @JacksonXmlProperty(localName = "paymentDate")
    private LocalDate paymentDate;

    @JacksonXmlProperty(localName = "amount")
    private BigDecimal amount;

    @JacksonXmlProperty(localName = "customUserAmount")
    private BigDecimal customUserAmount;

    @JacksonXmlProperty(localName = "sourceAmount")
    private BigDecimal sourceAmount;

    @JacksonXmlProperty(localName = "sourceAmountCurrency")
    private BigDecimal sourceAmountCurrency;

    @JacksonXmlProperty(localName = "groupPayment")
    private Boolean groupPayment;

    @JacksonXmlProperty(localName = "canEditWizard")
    private Boolean canEditWizard;

    @JacksonXmlProperty(localName = "canGroupPayments")
    private Boolean canGroupPayments;

    @JacksonXmlProperty(localName = "createDate")
    private LocalDateTime createDate;

    @JacksonXmlProperty(localName = "writeDate")
    private LocalDateTime writeDate;

    @JacksonXmlProperty(localName = "paymentTokenId")
    private Integer paymentTokenId;

    @JacksonXmlProperty(localName = "operation")
    private String operation;

    @JacksonXmlProperty(localName = "operationSys")
    private LocalDateTime operationSys;

    @JacksonXmlProperty(localName = "exported")
    private String exported;
}
