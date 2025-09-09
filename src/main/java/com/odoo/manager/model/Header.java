package com.odoo.manager.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Header {

    @JacksonXmlProperty(localName = "exportedDate")
    private LocalDateTime exportedDate;

    @JacksonXmlProperty(localName = "odooSchema")
    private OdooSchema odooSchema;

}
