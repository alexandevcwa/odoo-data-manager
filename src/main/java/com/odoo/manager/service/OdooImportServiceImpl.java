package com.odoo.manager.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.odoo.manager.model.*;
import com.odoo.manager.repo.*;
import com.odoo.manager.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OdooImportServiceImpl implements OdooImportService {

    private final XmlMapper xmlMapper;
    private final ProductCategoryBaseRepo productCategoryRepo;
    private final ProductTemplateBaseRepo productTemplateRepo;
    private final ProductProductRepo productProductRepo;
    private final ResPartnerRepo resPartnerRepo;
    private final SaleOrderRepo saleOrderRepo;
    private final SaleOrderLineRepo saleOrderLineRepo;
    private final SaleAdvancePaymentInvRepo saleAdvancePaymentInvRepo;
    private final AccountPaymentRegisterRepo accountPaymentRegisterRepo;
    private final AccountPaymentRepo accountPaymentRepo;
    private final AccountPartialReconcileRepo accountPartialReconcileRepo;
    private final AccountFullReconcileRepo accountFullReconcileRepo;
    private final AuOXMLFileRepo auOXMLFileRepo;
    private static String schema;

    private String sha256;

    @Override
    public void importFromOXML(String oXmlPath) {

        try {
            validateFileBeforeImport(oXmlPath);
            OdooData odooData = xmlMapper.readValue(new File(oXmlPath), OdooData.class);
            schema = odooData.getHeader().getOdooSchema().getSchema();
            importOdooData(odooData);
            saveImportFileAsAuditing(oXmlPath);
        } catch (IOException e) {
            log.error("Error reading Odoo XML file: {}", e.getMessage());
        }
    }

    private void validateFileBeforeImport(String oXmlPath) {
        this.sha256 = HashUtil.obtainSHA256(oXmlPath);
        boolean exists = auOXMLFileRepo.existsBySha256(sha256);
        if (exists) {
            throw new RuntimeException("Odoo XML ya fue importado anteriormente: " + sha256);
        }
    }

    private void saveImportFileAsAuditing(String file) {

        boolean inserted = auOXMLFileRepo.save(
                AuOXMLFile.builder()
                        .fecha(LocalDateTime.now())
                        .archivo(file)
                        .sha256(sha256)
                        .build()
        );
        if (inserted) {
            log.info("Odoo XML Importado Exitosamente: {}", file);
        } else {
            log.error("Error al registrar Odoo XML en tabla de auditoria: {}", file);
        }

    }

    private void importOdooData(OdooData odooData) {
        importProductCategory(odooData.getProductCategoryList());
        importProductTemplate(odooData.getProductTemplateList());
        importProductProduct(odooData.getProductProductList());
        importRestPartners(odooData.getResPartnerList());
        importSaleOrders(odooData.getSaleOrderList());
        importSaleOrderLines(odooData.getSaleOrderLineList());
        importSaleAdvancePaymentInvs(odooData.getSaleAdvancePaymentInvList());
        importAccountPaymentRegisters(odooData.getAccountPaymentRegisterList());
        importAccountPayments(odooData.getAccountPaymentList());
        importAccountPartialReconciles(odooData.getAccountPartialReconcileList());
        importAccountFullReconciles(odooData.getAccountFullReconcileList());
    }


    private void importProductCategory(List<ProductCategory> productCategoryList) {
        if (productCategoryList == null || productCategoryList.isEmpty()) {
            log.info("No Product Categories to import.");
            return;
        }
        productCategoryRepo.saveBatch(productCategoryList, schema);
    }

    private void importProductTemplate(List<ProductTemplate> productTemplateList) {
        if (productTemplateList == null || productTemplateList.isEmpty()) {
            log.info("No Product Templates to import.");
            return;
        }
        productTemplateRepo.saveBatch(productTemplateList, schema);
    }

    private void importProductProduct(List<ProductProduct> productProductList) {
        if (productProductList == null || productProductList.isEmpty()) {
            log.info("No Product Products to import.");
            return;
        }
        productProductRepo.saveBatch(productProductList, schema);
    }

    private void importRestPartners(List<ResPartner> resPartnerList) {
        if (resPartnerList == null || resPartnerList.isEmpty()) {
            log.info("No Res Partners to import.");
            return;
        }
        resPartnerRepo.saveBatch(resPartnerList, schema);
    }

    private void importSaleOrders(List<SaleOrder> saleOrderList) {
        if (saleOrderList == null || saleOrderList.isEmpty()) {
            log.info("No Sale Orders to import.");
            return;
        }
        saleOrderRepo.saveBatch(saleOrderList, schema);
    }

    private void importSaleOrderLines(List<SaleOrderLine> saleOrderLineList) {
        if (saleOrderLineList == null || saleOrderLineList.isEmpty()) {
            log.info("No Sale Order Lines to import.");
            return;
        }
        saleOrderLineRepo.saveBatch(saleOrderLineList, schema);
    }

    private void importSaleAdvancePaymentInvs(List<SaleAdvancePaymentInv> saleAdvancePaymentInvList) {
        if (saleAdvancePaymentInvList == null || saleAdvancePaymentInvList.isEmpty()) {
            log.info("No Sale Advance Payment Invoices to import.");
            return;
        }
        saleAdvancePaymentInvRepo.saveBatch(saleAdvancePaymentInvList, schema);
    }

    private void importAccountPaymentRegisters(List<AccountPaymentRegister> accountPaymentRegisterList) {
        if (accountPaymentRegisterList == null || accountPaymentRegisterList.isEmpty()) {
            log.info("No Account Payment Registers to import.");
            return;
        }
        accountPaymentRegisterRepo.saveBatch(accountPaymentRegisterList, schema);
    }

    private void importAccountPayments(List<AccountPayment> accountPaymentList) {
        if (accountPaymentList == null || accountPaymentList.isEmpty()) {
            log.info("No Account Payments to import.");
            return;
        }
        accountPaymentRepo.saveBatch(accountPaymentList, schema);
    }

    private void importAccountPartialReconciles(List<AccountPartialReconcile> accountPartialReconcileList) {
        if (accountPartialReconcileList == null || accountPartialReconcileList.isEmpty()) {
            log.info("No Account Partial Reconciles to import.");
            return;
        }
        accountPartialReconcileRepo.saveBatch(accountPartialReconcileList, schema);
    }

    private void importAccountFullReconciles(List<AccountFullReconcile> accountFullReconcileList) {
        if (accountFullReconcileList == null || accountFullReconcileList.isEmpty()) {
            log.info("No Account Full Reconciles to import.");
            return;
        }
        accountFullReconcileRepo.saveBatch(accountFullReconcileList, schema);
    }
}
