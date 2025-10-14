package com.odoo.manager.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuOXMLFile {
    private Integer id;
    private LocalDateTime fecha;
    private String archivo;
    private String sha256;
}
