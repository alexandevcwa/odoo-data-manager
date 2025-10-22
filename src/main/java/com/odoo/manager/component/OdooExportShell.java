package com.odoo.manager.component;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import com.odoo.manager.service.OdooExportService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ShellComponent
@RequiredArgsConstructor
public class OdooExportShell {

    private final OdooExportService odooExportService;

    @ShellMethod(key = "odoo-export", value = "Export data from Odoo to XML file")
    public void exportOdooData(
            @ShellOption("path") String path,
            @ShellOption("schema") String schema) {
        odooExportService.export(path, schema);
    }
}