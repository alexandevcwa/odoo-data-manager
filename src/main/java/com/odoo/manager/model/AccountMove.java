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
public class AccountMove {

    @JacksonXmlProperty(localName = "id")
    private Integer id;

    @JacksonXmlProperty(localName = "sequenceNumber")
    private Integer sequenceNumber;

    @JacksonXmlProperty(localName = "messageMainAttachmentId")
    private Integer messageMainAttachmentId;

    @JacksonXmlProperty(localName = "journalId")
    private int journalId;

    @JacksonXmlProperty(localName = "companyId")
    private Integer companyId;

    @JacksonXmlProperty(localName = "originPaymentId")
    private Integer originPaymentId;

    @JacksonXmlProperty(localName = "statementLineId")
    private Integer statementLineId;

    @JacksonXmlProperty(localName = "taxCashBasisRecId")
    private Integer taxCashBasisRecId;

    @JacksonXmlProperty(localName = "taxCashBasisOriginMoveId")
    private Integer taxCashBasisOriginMoveId;

    @JacksonXmlProperty(localName = "autoPostOriginId")
    private Integer autoPostOriginId;

    @JacksonXmlProperty(localName = "secureSequenceNumber")
    private Integer secureSequenceNumber;

    @JacksonXmlProperty(localName = "invoicePaymentTermId")
    private Integer invoicePaymentTermId;

    @JacksonXmlProperty(localName = "partnerId")
    private Integer partnerId;

    @JacksonXmlProperty(localName = "commercialPartnerId")
    private Integer commercialPartnerId;

    @JacksonXmlProperty(localName = "partnerShippingId")
    private Integer partnerShippingId;

    @JacksonXmlProperty(localName = "partnerBankId")
    private Integer partnerBankId;

    @JacksonXmlProperty(localName = "fiscalPositionId")
    private Integer fiscalPositionId;

    @JacksonXmlProperty(localName = "preferredPaymentMethodLineId")
    private Integer preferredPaymentMethodLineId;

    @JacksonXmlProperty(localName = "currencyId")
    private int currencyId;

    @JacksonXmlProperty(localName = "reversedEntryId")
    private Integer reversedEntryId;

    @JacksonXmlProperty(localName = "invoiceUserId")
    private Integer invoiceUserId;

    @JacksonXmlProperty(localName = "invoiceIncotermId")
    private Integer invoiceIncotermId;

    @JacksonXmlProperty(localName = "invoiceCashRoundingId")
    private Integer invoiceCashRoundingId;

    @JacksonXmlProperty(localName = "createUid")
    private Integer createUid;

    @JacksonXmlProperty(localName = "writeUid")
    private Integer writeUid;

    @JacksonXmlProperty(localName = "sequencePrefix")
    private String sequencePrefix;

    @JacksonXmlProperty(localName = "accessToken")
    private String accessToken;

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "ref")
    private String ref;

    @JacksonXmlProperty(localName = "state")
    private String state;

    @JacksonXmlProperty(localName = "moveType")
    private String moveType;

    @JacksonXmlProperty(localName = "autoPost")
    private String autoPost;

    @JacksonXmlProperty(localName = "inalterableHash")
    private String inalterableHash;

    @JacksonXmlProperty(localName = "paymentReference")
    private String paymentReference;

    @JacksonXmlProperty(localName = "qrCodeMethod")
    private String qrCodeMethod;

    @JacksonXmlProperty(localName = "paymentState")
    private String paymentState;

    @JacksonXmlProperty(localName = "invoiceSourceEmail")
    private String invoiceSourceEmail;

    @JacksonXmlProperty(localName = "invoicePartnerDisplayName")
    private String invoicePartnerDisplayName;

    @JacksonXmlProperty(localName = "invoiceOrigin")
    private String invoiceOrigin;

    @JacksonXmlProperty(localName = "incotermLocation")
    private String incotermLocation;

    @JacksonXmlProperty(localName = "date")
    private LocalDate date;

    @JacksonXmlProperty(localName = "autoPostUntil")
    private LocalDate autoPostUntil;

    @JacksonXmlProperty(localName = "invoiceDate")
    private LocalDate invoiceDate;

    @JacksonXmlProperty(localName = "invoiceDateDue")
    private LocalDate invoiceDateDue;

    @JacksonXmlProperty(localName = "deliveryDate")
    private LocalDate deliveryDate;

    @JacksonXmlProperty(localName = "sendingData")
    private String sendingData; // jsonb → String

    @JacksonXmlProperty(localName = "narration")
    private String narration;

    @JacksonXmlProperty(localName = "invoiceCurrencyRate")
    private BigDecimal invoiceCurrencyRate;

    @JacksonXmlProperty(localName = "amountUntaxed")
    private BigDecimal amountUntaxed;

    @JacksonXmlProperty(localName = "amountTax")
    private BigDecimal amountTax;

    @JacksonXmlProperty(localName = "amountTotal")
    private BigDecimal amountTotal;

    @JacksonXmlProperty(localName = "amountResidual")
    private BigDecimal amountResidual;

    @JacksonXmlProperty(localName = "amountUntaxedSigned")
    private BigDecimal amountUntaxedSigned;

    @JacksonXmlProperty(localName = "amountUntaxedInCurrencySigned")
    private BigDecimal amountUntaxedInCurrencySigned;

    @JacksonXmlProperty(localName = "amountTaxSigned")
    private BigDecimal amountTaxSigned;

    @JacksonXmlProperty(localName = "amountTotalSigned")
    private BigDecimal amountTotalSigned;

    @JacksonXmlProperty(localName = "amountTotalInCurrencySigned")
    private BigDecimal amountTotalInCurrencySigned;

    @JacksonXmlProperty(localName = "amountResidualSigned")
    private BigDecimal amountResidualSigned;

    @JacksonXmlProperty(localName = "quickEditTotalAmount")
    private BigDecimal quickEditTotalAmount;

    @JacksonXmlProperty(localName = "isStorno")
    private Boolean isStorno;

    @JacksonXmlProperty(localName = "alwaysTaxExigible")
    private Boolean alwaysTaxExigible;

    @JacksonXmlProperty(localName = "checked")
    private Boolean checked;

    @JacksonXmlProperty(localName = "postedBefore")
    private Boolean postedBefore;

    @JacksonXmlProperty(localName = "madeSequenceGap")
    private Boolean madeSequenceGap;

    @JacksonXmlProperty(localName = "isManuallyModified")
    private Boolean isManuallyModified;

    @JacksonXmlProperty(localName = "isMoveSent")
    private Boolean isMoveSent;

    @JacksonXmlProperty(localName = "createDate")
    private LocalDateTime createDate;

    @JacksonXmlProperty(localName = "writeDate")
    private LocalDateTime writeDate;

    @JacksonXmlProperty(localName = "campaignId")
    private Integer campaignId;

    @JacksonXmlProperty(localName = "sourceId")
    private Integer sourceId;

    @JacksonXmlProperty(localName = "mediumId")
    private Integer mediumId;

    @JacksonXmlProperty(localName = "teamId")
    private Integer teamId;

    @JacksonXmlProperty(localName = "stockMoveId")
    private Integer stockMoveId;

    @JacksonXmlProperty(localName = "reversedPosOrderId")
    private Integer reversedPosOrderId;

    @JacksonXmlProperty(localName = "operation")
    private String operation; // varchar(1)

    @JacksonXmlProperty(localName = "operationSys")
    private LocalDateTime operationSys;
}
