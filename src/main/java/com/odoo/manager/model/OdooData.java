package com.odoo.manager.model;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

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
@JacksonXmlRootElement(localName = "odooData")
public class OdooData {

    @JacksonXmlProperty(localName = "odooHeader")
    private Header header;

    @JacksonXmlElementWrapper(localName = "productCategoryItems")
    @JacksonXmlProperty(localName = "productCategory")
    private List<ProductCategory> productCategoryList;

    @JacksonXmlElementWrapper(localName = "productTemplateItems")
    @JacksonXmlProperty(localName = "productTemplate")
    private List<ProductTemplate> productTemplateList;

    @JacksonXmlElementWrapper(localName = "productProductItems")
    @JacksonXmlProperty(localName = "productProduct")
    private List<ProductProduct> productProductList;

    @JacksonXmlElementWrapper(localName = "resPartnerItems")
    @JacksonXmlProperty(localName = "resPartner")
    private List<ResPartner> resPartnerList;

    @JacksonXmlElementWrapper(localName = "saleOrderItems")
    @JacksonXmlProperty(localName = "saleOrder")
    private List<SaleOrder> saleOrderList;

    @JacksonXmlElementWrapper(localName = "saleOrderLineItems")
    @JacksonXmlProperty(localName = "saleOrderLine")
    private List<SaleOrderLine> saleOrderLineList;

    @JacksonXmlElementWrapper(localName = "saleAdvancePaymentInvItems")
    @JacksonXmlProperty(localName = "saleAdvancePaymentInv")
    private List<SaleAdvancePaymentInv> saleAdvancePaymentInvList;

    @JacksonXmlElementWrapper(localName = "accountMoveItems")
    @JacksonXmlProperty(localName = "accountMove")
    private List<AccountMove> accountMoveList;

    @JacksonXmlElementWrapper(localName = "accountMoveLineItems")
    @JacksonXmlProperty(localName = "accountMoveLine")
    private List<AccountMoveLine> accountMoveLineList;

    @JacksonXmlElementWrapper(localName = "accountPaymentRegisterItems")
    @JacksonXmlProperty(localName = "accountPaymentRegister")
    private List<AccountPaymentRegister> accountPaymentRegisterList;

    @JacksonXmlElementWrapper(localName = "accountPaymentItems")
    @JacksonXmlProperty(localName = "accountPayment")
    private List<AccountPayment> accountPaymentList;

    @JacksonXmlElementWrapper(localName = "accountPartialReconcileItems")
    @JacksonXmlProperty(localName = "accountPartialReconcile")
    private List<AccountPartialReconcile> accountPartialReconcileList;

    @JacksonXmlElementWrapper(localName = "accountFullReconcileItems")
    @JacksonXmlProperty(localName = "accountFullReconcile")
    private List<AccountFullReconcile> accountFullReconcileList;
}