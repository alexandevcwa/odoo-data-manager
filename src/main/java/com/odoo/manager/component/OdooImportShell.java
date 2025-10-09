package com.odoo.manager.component;

import com.odoo.manager.service.OdooImportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@Slf4j
@ShellComponent
@RequiredArgsConstructor
public class OdooImportShell {

    private final OdooImportService odooImportService;

    @ShellMethod(key = "odoo-import", value = "Import data from Odoo XML file")
    public void importOdooData(
            @ShellOption("path") String path
    ){
        odooImportService.importFromOXML(path);
    }

}
