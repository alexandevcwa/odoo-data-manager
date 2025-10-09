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
public class SaleAdvancePaymentInv {

    @JacksonXmlProperty(localName = "id")
    private Integer id;

    @JacksonXmlProperty(localName = "currencyId")
    private Integer currencyId;

    @JacksonXmlProperty(localName = "companyId")
    private Integer companyId;

    @JacksonXmlProperty(localName = "createUid")
    private Integer createUid;

    @JacksonXmlProperty(localName = "writeUid")
    private Integer writeUid;

    @JacksonXmlProperty(localName = "advancePaymentMethod")
    private String advancePaymentMethod;

    @JacksonXmlProperty(localName = "fixedAmount")
    private BigDecimal fixedAmount;

    @JacksonXmlProperty(localName = "deductDownPayments")
    private Boolean deductDownPayments;

    @JacksonXmlProperty(localName = "consolidatedBilling")
    private Boolean consolidatedBilling;

    @JacksonXmlProperty(localName = "createDate")
    private LocalDateTime createDate;

    @JacksonXmlProperty(localName = "writeDate")
    private LocalDateTime writeDate;

    @JacksonXmlProperty(localName = "amount")
    private Double amount;

    @JacksonXmlProperty(localName = "operation")
    private String operation; // varchar(1)

    @JacksonXmlProperty(localName = "operationSys")
    private LocalDateTime operationSys;

    @JacksonXmlProperty(localName = "exported")
    private String exported;

}
