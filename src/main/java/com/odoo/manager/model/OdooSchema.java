package com.odoo.manager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OdooSchema {
    private Integer instanceId;
    private String schema;
    private Short companyId;
}
