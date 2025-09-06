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
public class AccountPartialReconcile {

    @JacksonXmlProperty(localName = "id")
    private Integer id;

    @JacksonXmlProperty(localName = "debitMoveId")
    private int debitMoveId;

    @JacksonXmlProperty(localName = "creditMoveId")
    private int creditMoveId;

    @JacksonXmlProperty(localName = "fullReconcileId")
    private Integer fullReconcileId;

    @JacksonXmlProperty(localName = "exchangeMoveId")
    private Integer exchangeMoveId;

    @JacksonXmlProperty(localName = "debitCurrencyId")
    private Integer debitCurrencyId;

    @JacksonXmlProperty(localName = "creditCurrencyId")
    private Integer creditCurrencyId;

    @JacksonXmlProperty(localName = "companyId")
    private Integer companyId;

    @JacksonXmlProperty(localName = "createUid")
    private Integer createUid;

    @JacksonXmlProperty(localName = "writeUid")
    private Integer writeUid;

    @JacksonXmlProperty(localName = "maxDate")
    private LocalDate maxDate;

    @JacksonXmlProperty(localName = "amount")
    private BigDecimal amount;

    @JacksonXmlProperty(localName = "debitAmountCurrency")
    private BigDecimal debitAmountCurrency;

    @JacksonXmlProperty(localName = "creditAmountCurrency")
    private BigDecimal creditAmountCurrency;

    @JacksonXmlProperty(localName = "createDate")
    private LocalDateTime createDate;

    @JacksonXmlProperty(localName = "writeDate")
    private LocalDateTime writeDate;

    @JacksonXmlProperty(localName = "operation")
    private String operation; // varchar(1)

    @JacksonXmlProperty(localName = "operationSys")
    private LocalDateTime operationSys;
}
