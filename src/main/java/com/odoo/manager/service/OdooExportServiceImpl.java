package com.odoo.manager.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.odoo.manager.model.AccountFullReconcile;
import com.odoo.manager.model.AccountMove;
import com.odoo.manager.model.AccountMoveLine;
import com.odoo.manager.model.AccountPartialReconcile;
import com.odoo.manager.model.AccountPayment;
import com.odoo.manager.model.AccountPaymentRegister;
import com.odoo.manager.model.OdooData;
import com.odoo.manager.model.ProductCategory;
import com.odoo.manager.model.ProductProduct;
import com.odoo.manager.model.ProductTemplate;
import com.odoo.manager.model.ResPartner;
import com.odoo.manager.model.SaleAdvancePaymentInv;
import com.odoo.manager.model.SaleOrder;
import com.odoo.manager.model.SaleOrderLine;
import com.odoo.manager.repo.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OdooExportServiceImpl implements OdooExportService {

    private final XmlMapper xmlMapper;
    private final TransactionTemplate transactionTemplate;

    private final ProductCategoryRepo productCategoryExpRepo;
    private final ProductTemplateRepo productTemplateRepo;
    private final ProductProductRepo productProductRepo;
    private final ResPartnerRepo resPartnerRepo;
    private final SaleOrderRepo saleOrderRepo;
    private final SaleOrderLineRepo saleOrderLineRepo;
    private final SaleAdvancePaymentInvRepo saleAdvancePaymentInvRepo;
    private final AccountMoveRepo accountMoveRepo;
    private final AccountMoveLineRepo accountMoveLineRepo;
    private final AccountPaymentRegisterRepo accountPaymentRegisterRepo;
    private final AccountPaymentRepo accountPaymentRepo;
    private final AccountPartialReconcileRepo accountPartialReconcileRepo;
    private final AccountFullReconcileRepo accountFullReconcileRepo;

    @Override
    public void export(String path) {

        try {
            String fileName = generateOdooFileName(path);

            OdooData odooData = obtainerOdooDataObj();

            transactionTemplate.execute(status -> {
                try {
                    confirmOdooDataExported(odooData);
                    xmlMapper.writerWithDefaultPrettyPrinter()
                            .writeValue(new File(fileName), odooData);
                } catch (IOException e) {
                    log.error("Error al escribir el archivo: " + e.getMessage());
                } catch (Exception e) {
                    log.error("Error inesperado: " + e.getMessage());
                    status.setRollbackOnly();
                }

                return null;
            });

            log.info("Archivo exportado en la ruta: " + fileName);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

    }

    private OdooData obtainerOdooDataObj() {
        List<ProductCategory> productCategories = productCategoryExpRepo.findAllByExportedIsNotNull();
        List<ProductTemplate> productTemplates = productTemplateRepo.findAllByExportedIsNotNull();
        List<ProductProduct> productProducts = productProductRepo.findAllByExportedIsNotNull();
        List<ResPartner> resPartners = resPartnerRepo.findAllByExportedIsNotNull();
        List<SaleOrder> saleOrders = saleOrderRepo.findAllByExportedIsNotNull();
        List<SaleOrderLine> saleOrderLines = saleOrderLineRepo.findAllByExportedIsNotNull();
        List<SaleAdvancePaymentInv> saleAdvancePaymentInvts = saleAdvancePaymentInvRepo.findAllByExportedIsNotNull();
        List<AccountMove> accountMoves = accountMoveRepo.findAllByExportedIsNotNull();
        List<AccountMoveLine> accountMoveLines = accountMoveLineRepo.findAllByExportedIsNotNull();
        List<AccountPaymentRegister> accountPaymentRegisters = accountPaymentRegisterRepo.findAllByExportedIsNotNull();
        List<AccountPayment> accountPayments = accountPaymentRepo.findAllByExportedIsNotNull();
        List<AccountPartialReconcile> accountPartialReconciles = accountPartialReconcileRepo
                .findAllByExportedIsNotNull();
        List<AccountFullReconcile> accountFullReconciles = accountFullReconcileRepo.findAllByExportedIsNotNull();

        return OdooData.builder()
                .productCategoryList(productCategories)
                .productTemplateList(productTemplates)
                .productProductList(productProducts)
                .resPartnerList(resPartners)
                .saleOrderList(saleOrders)
                .saleOrderLineList(saleOrderLines)
                .saleAdvancePaymentInvList(saleAdvancePaymentInvts)
                .accountMoveList(accountMoves)
                .accountMoveLineList(accountMoveLines)
                .accountPaymentRegisterList(accountPaymentRegisters)
                .accountPaymentList(accountPayments)
                .accountPartialReconcileList(accountPartialReconciles)
                .accountFullReconcileList(accountFullReconciles)
                .build();
    }

    private void confirmOdooDataExported(OdooData odooData) {
        productCategoryExpRepo.markGroupAsExported(odooData.getProductCategoryList());
        productTemplateRepo.markGroupAsExported(odooData.getProductTemplateList());
        productProductRepo.markGroupAsExported(odooData.getProductProductList());
        resPartnerRepo.markGroupAsExported(odooData.getResPartnerList());
        saleOrderRepo.markGroupAsExported(odooData.getSaleOrderList());
        saleOrderLineRepo.markGroupAsExported(odooData.getSaleOrderLineList());
        saleAdvancePaymentInvRepo.markGroupAsExported(odooData.getSaleAdvancePaymentInvList());
        accountMoveRepo.markGroupAsExported(odooData.getAccountMoveList());
        accountMoveLineRepo.markGroupAsExported(odooData.getAccountMoveLineList());
        accountPaymentRegisterRepo.markGroupAsExported(odooData.getAccountPaymentRegisterList());
        accountPaymentRepo.markGroupAsExported(odooData.getAccountPaymentList());
        accountPartialReconcileRepo.markGroupAsExported(odooData.getAccountPartialReconcileList());
        accountFullReconcileRepo.markGroupAsExported(odooData.getAccountFullReconcileList());
    }

    private String generateOdooFileName(String path) throws IOException {
        verifyIsDirectory(path);
        final String dateTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        return path + "/Odoo_Export_" + dateTime + ".oxml";
    }

    private void verifyIsDirectory(String path) throws IOException {

        if (path.contains("\\") || path.contains("\\\\")) {
            throw new IOException("El formato de la ruta es incorrecto, utilizar [/] para separar los directorios");
        }
        log.info(path);

        Path fPath = Paths.get(path);
        if (!Files.exists(fPath) && !Files.isDirectory(fPath)) {
            throw new IOException("El directorio ingresado no existe.");
        }
    }
}
