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

    @JacksonXmlProperty(localName = "ExportDate")
    private LocalDateTime exportDate;

    @JacksonXmlProperty(localName = "SystemVersion")
    private String systemVersion;

    @JacksonXmlProperty(localName = "RecordCount")
    private Long recordCount;

    @JacksonXmlProperty(localName = "HostIP")
    private String hostIp;

    @JacksonXmlProperty(localName = "HostName")
    private String hostName;
}
