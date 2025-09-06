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
public class ProductProduct {

    @JacksonXmlProperty(localName = "id")
    private Integer id;

    @JacksonXmlProperty(localName = "productTmplId")
    private int productTmplId;

    @JacksonXmlProperty(localName = "createUid")
    private Integer createUid;

    @JacksonXmlProperty(localName = "writeUid")
    private Integer writeUid;

    @JacksonXmlProperty(localName = "defaultCode")
    private String defaultCode;

    @JacksonXmlProperty(localName = "barcode")
    private String barcode;

    @JacksonXmlProperty(localName = "combinationIndices")
    private String combinationIndices;

    @JacksonXmlProperty(localName = "standardPrice")
    private String standardPrice; // jsonb → String

    @JacksonXmlProperty(localName = "volume")
    private BigDecimal volume;

    @JacksonXmlProperty(localName = "weight")
    private BigDecimal weight;

    @JacksonXmlProperty(localName = "active")
    private Boolean active;

    @JacksonXmlProperty(localName = "canImageVariant1024BeZoomed")
    private Boolean canImageVariant1024BeZoomed;

    @JacksonXmlProperty(localName = "writeDate")
    private LocalDateTime writeDate;

    @JacksonXmlProperty(localName = "createDate")
    private LocalDateTime createDate;

    @JacksonXmlProperty(localName = "lotPropertiesDefinition")
    private String lotPropertiesDefinition; // jsonb → String

    @JacksonXmlProperty(localName = "operation")
    private String operation; // varchar(1)

    @JacksonXmlProperty(localName = "operationSys")
    private LocalDateTime operationSys;

}
