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
public class AccountPayment {
    @JacksonXmlProperty(localName = "id")
    private Integer id;

    @JacksonXmlProperty(localName = "messageMainAttachmentId")
    private Integer messageMainAttachmentId;

    @JacksonXmlProperty(localName = "moveId")
    private Integer moveId;

    @JacksonXmlProperty(localName = "journalId")
    private int journalId;

    @JacksonXmlProperty(localName = "companyId")
    private int companyId;

    @JacksonXmlProperty(localName = "partnerBankId")
    private Integer partnerBankId;

    @JacksonXmlProperty(localName = "pairedInternalTransferPaymentId")
    private Integer pairedInternalTransferPaymentId;

    @JacksonXmlProperty(localName = "paymentMethodLineId")
    private Integer paymentMethodLineId;

    @JacksonXmlProperty(localName = "paymentMethodId")
    private Integer paymentMethodId;

    @JacksonXmlProperty(localName = "currencyId")
    private Integer currencyId;

    @JacksonXmlProperty(localName = "partnerId")
    private Integer partnerId;

    @JacksonXmlProperty(localName = "outstandingAccountId")
    private Integer outstandingAccountId;

    @JacksonXmlProperty(localName = "destinationAccountId")
    private Integer destinationAccountId;

    @JacksonXmlProperty(localName = "createUid")
    private Integer createUid;

    @JacksonXmlProperty(localName = "writeUid")
    private Integer writeUid;

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "state")
    private String state;

    @JacksonXmlProperty(localName = "paymentType")
    private String paymentType;

    @JacksonXmlProperty(localName = "partnerType")
    private String partnerType;

    @JacksonXmlProperty(localName = "memo")
    private String memo;

    @JacksonXmlProperty(localName = "paymentReference")
    private String paymentReference;

    @JacksonXmlProperty(localName = "date")
    private LocalDate date;

    @JacksonXmlProperty(localName = "amount")
    private BigDecimal amount;

    @JacksonXmlProperty(localName = "amountCompanyCurrencySigned")
    private BigDecimal amountCompanyCurrencySigned;

    @JacksonXmlProperty(localName = "isReconciled")
    private Boolean isReconciled;

    @JacksonXmlProperty(localName = "isMatched")
    private Boolean isMatched;

    @JacksonXmlProperty(localName = "isSent")
    private Boolean isSent;

    @JacksonXmlProperty(localName = "createDate")
    private LocalDateTime createDate;

    @JacksonXmlProperty(localName = "writeDate")
    private LocalDateTime writeDate;

    @JacksonXmlProperty(localName = "paymentTransactionId")
    private Integer paymentTransactionId;

    @JacksonXmlProperty(localName = "paymentTokenId")
    private Integer paymentTokenId;

    @JacksonXmlProperty(localName = "sourcePaymentId")
    private Integer sourcePaymentId;

    @JacksonXmlProperty(localName = "posPaymentMethodId")
    private Integer posPaymentMethodId;

    @JacksonXmlProperty(localName = "forceOutstandingAccountId")
    private Integer forceOutstandingAccountId;

    @JacksonXmlProperty(localName = "posSessionId")
    private Integer posSessionId;

    @JacksonXmlProperty(localName = "posOrderId")
    private Integer posOrderId;

    @JacksonXmlProperty(localName = "operation")
    private String operation; // varchar(1)

    @JacksonXmlProperty(localName = "operationSys")
    private LocalDateTime operationSys;

    @JacksonXmlProperty(localName = "exported")
    private String exported;
}
