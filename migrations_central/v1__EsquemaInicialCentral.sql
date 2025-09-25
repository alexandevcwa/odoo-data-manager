/*******************************************************************
* Date: 2025-09-24
* Autor: Alexander Machic
* Descripcion: Esquema inicial de la base de datos central
********************************************************************/



/*
* !!! Instrucciones !!!
* Crear un esquema personalizado para almacenar los datos de ventas
* de cada una de las sucursales.
* DROP DATABASE odoo_central;
* CREATE DATABASE odoo_central OWNER openpg;
* GRANT ALL PRIVILEGES ON DATABASE odoo_central TO openpg;
* CREATE SCHEMA IF NOT EXISTS odoo_s1 AUTHORIZATION openpg;
* CREATE SCHEMA IF NOT EXISTS odoo_s2 AUTHORIZATION openpg;
*/
---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------

-------------------------------------------------------------------------
-- EJECUTAR SOLO UNA VEZ POR BASE DE DATOS EN ESQUEMA PUBLICO
-------------------------------------------------------------------------
create schema public authorization openpg;
set search_path = public;
drop table if exists odoo_sucursal;
create table odoo_sucursal
(
    id          integer
        primary key,
    name        varchar not null,
    schema      varchar not null
);

drop table if exists odoo_files;
create table odoo_files(
    id          serial
        primary key,
    name        varchar not null,
    file_hash   varchar not null,
    create_date timestamp default CURRENT_TIMESTAMP,
    sucursal_id   integer references odoo_sucursal(id)
);
-------------------------------------------------------------------------
-- EJECUTAR POR ESQUEMA DE SUCURSAL
-------------------------------------------------------------------------
-- Cambiar odoo_s1 por el esquema correspondiente a la sucursal
SELECT current_database();
set search_path = "odoo_s2";

drop table if exists product_category_exp;
create table product_category_exp
(
    id                                     integer,
    parent_id                              integer,
    create_uid                             integer,
    write_uid                              integer,
    name                                   varchar,
    complete_name                          varchar,
    parent_path                            varchar,
    product_properties_definition          jsonb,
    create_date                            timestamp,
    write_date                             timestamp,
    property_account_income_categ_id       jsonb,
    property_account_expense_categ_id      jsonb,
    property_account_downpayment_categ_id  jsonb,
    removal_strategy_id                    integer,
    packaging_reserve_method               varchar,
    property_valuation                     jsonb,
    property_cost_method                   jsonb,
    property_stock_journal                 jsonb,
    property_stock_account_input_categ_id  jsonb,
    property_stock_account_output_categ_id jsonb,
    property_stock_valuation_account_id    jsonb,
    operation                              varchar(1),
    operation_sys                          timestamp default CURRENT_TIMESTAMP,
    exported                               varchar(1)
);

drop table if exists product_category;
create table product_category
(
    id                                     integer primary key,
    parent_id                              integer,
    create_uid                             integer,
    write_uid                              integer,
    name                                   varchar not null,
    complete_name                          varchar,
    parent_path                            varchar,
    product_properties_definition          jsonb,
    create_date                            timestamp,
    write_date                             timestamp,
    property_account_income_categ_id       jsonb,
    property_account_expense_categ_id      jsonb,
    property_account_downpayment_categ_id  jsonb,
    removal_strategy_id                    integer,
    packaging_reserve_method               varchar,
    property_valuation                     jsonb,
    property_cost_method                   jsonb,
    property_stock_journal                 jsonb,
    property_stock_account_input_categ_id  jsonb,
    property_stock_account_output_categ_id jsonb,
    property_stock_valuation_account_id    jsonb
);

comment on table product_category is 'Product Category';

comment on column product_category.parent_id is 'Parent Category';

comment on column product_category.create_uid is 'Created by';

comment on column product_category.write_uid is 'Last Updated by';

comment on column product_category.name is 'Name';

comment on column product_category.complete_name is 'Complete Name';

comment on column product_category.parent_path is 'Parent Path';

comment on column product_category.product_properties_definition is 'Product Properties';

comment on column product_category.create_date is 'Created on';

comment on column product_category.write_date is 'Last Updated on';

comment on column product_category.property_account_income_categ_id is 'Income Account';

comment on column product_category.property_account_expense_categ_id is 'Expense Account';

comment on column product_category.property_account_downpayment_categ_id is 'Downpayment Account';

comment on column product_category.removal_strategy_id is 'Force Removal Strategy';

comment on column product_category.packaging_reserve_method is 'Reserve Packagings';

comment on column product_category.property_valuation is 'Inventory Valuation';

comment on column product_category.property_cost_method is 'Costing Method';

comment on column product_category.property_stock_journal is 'Stock Journal';

comment on column product_category.property_stock_account_input_categ_id is 'Stock Input Account';

comment on column product_category.property_stock_account_output_categ_id is 'Stock Output Account';

comment on column product_category.property_stock_valuation_account_id is 'Stock Valuation Account';

create index product_category__parent_id_index
    on product_category (parent_id);

create index product_category__parent_path_index
    on product_category (parent_path);

create index product_category__property_account_income_categ_id_index
    on product_category ((property_account_income_categ_id IS NOT NULL))
    where (property_account_income_categ_id IS NOT NULL);

create index product_category__property_account_expense_categ_id_index
    on product_category ((property_account_expense_categ_id IS NOT NULL))
    where (property_account_expense_categ_id IS NOT NULL);

create index product_category__property_account_downpayment_categ_id_index
    on product_category ((property_account_downpayment_categ_id IS NOT NULL))
    where (property_account_downpayment_categ_id IS NOT NULL);

create index product_category__property_valuation_index
    on product_category ((property_valuation IS NOT NULL))
    where (property_valuation IS NOT NULL);

create index product_category__property_cost_method_index
    on product_category ((property_cost_method IS NOT NULL))
    where (property_cost_method IS NOT NULL);

create index product_category__property_stock_journal_index
    on product_category ((property_stock_journal IS NOT NULL))
    where (property_stock_journal IS NOT NULL);

create index product_category__property_stock_account_input_categ_id_index
    on product_category ((property_stock_account_input_categ_id IS NOT NULL))
    where (property_stock_account_input_categ_id IS NOT NULL);

create index product_category__property_stock_account_output_categ_id_index
    on product_category ((property_stock_account_output_categ_id IS NOT NULL))
    where (property_stock_account_output_categ_id IS NOT NULL);

create index product_category__property_stock_valuation_account_id_index
    on product_category ((property_stock_valuation_account_id IS NOT NULL))
    where (property_stock_valuation_account_id IS NOT NULL);

---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------

drop table if exists product_template_exp;
create table product_template_exp
(
    id                          integer,
    sequence                    integer,
    categ_id                    integer,
    uom_id                      integer,
    uom_po_id                   integer,
    company_id                  integer,
    color                       integer,
    create_uid                  integer,
    write_uid                   integer,
    type                        varchar,
    service_tracking            varchar,
    default_code                varchar,
    name                        jsonb,
    description                 jsonb,
    description_purchase        jsonb,
    description_sale            jsonb,
    product_properties          jsonb,
    list_price                  numeric,
    volume                      numeric,
    weight                      numeric,
    sale_ok                     boolean,
    purchase_ok                 boolean,
    active                      boolean,
    can_image_1024_be_zoomed    boolean,
    has_configurable_attributes boolean,
    is_favorite                 boolean,
    create_date                 timestamp,
    write_date                  timestamp,
    property_account_income_id  jsonb,
    property_account_expense_id jsonb,
    service_type                varchar,
    sale_line_warn              varchar,
    expense_policy              varchar,
    invoice_policy              varchar,
    sale_line_warn_msg          text,
    project_id                  jsonb,
    project_template_id         jsonb,
    sale_delay                  integer,
    tracking                    varchar,
    responsible_id              jsonb,
    property_stock_production   jsonb,
    property_stock_inventory    jsonb,
    description_picking         jsonb,
    description_pickingout      jsonb,
    description_pickingin       jsonb,
    is_storable                 boolean,
    lot_valuated                boolean,
    public_description          jsonb,
    available_in_pos            boolean,
    to_weight                   boolean,
    operation                   varchar(1),
    operation_sys               timestamp default CURRENT_TIMESTAMP,
    exported                    varchar(1)
);

drop table if exists product_template;
create table product_template
(
    id                          integer
        primary key,
    sequence                    integer,
    categ_id                    integer not null,
    uom_id                      integer not null,
    uom_po_id                   integer not null,
    company_id                  integer,
    color                       integer,
    create_uid                  integer,
    write_uid                   integer,
    type                        varchar not null,
    service_tracking            varchar not null,
    default_code                varchar,
    name                        jsonb   not null,
    description                 jsonb,
    description_purchase        jsonb,
    description_sale            jsonb,
    product_properties          jsonb,
    list_price                  numeric,
    volume                      numeric,
    weight                      numeric,
    sale_ok                     boolean,
    purchase_ok                 boolean,
    active                      boolean,
    can_image_1024_be_zoomed    boolean,
    has_configurable_attributes boolean,
    is_favorite                 boolean,
    create_date                 timestamp,
    write_date                  timestamp,
    property_account_income_id  jsonb,
    property_account_expense_id jsonb,
    service_type                varchar,
    sale_line_warn              varchar not null,
    expense_policy              varchar,
    invoice_policy              varchar,
    sale_line_warn_msg          text,
    project_id                  jsonb,
    project_template_id         jsonb,
    sale_delay                  integer,
    tracking                    varchar not null,
    responsible_id              jsonb,
    property_stock_production   jsonb,
    property_stock_inventory    jsonb,
    description_picking         jsonb,
    description_pickingout      jsonb,
    description_pickingin       jsonb,
    is_storable                 boolean,
    lot_valuated                boolean,
    public_description          jsonb,
    available_in_pos            boolean,
    to_weight                   boolean
);

comment on table product_template is 'Product';

comment on column product_template.sequence is 'Sequence';

comment on column product_template.categ_id is 'Product Category';

comment on column product_template.uom_id is 'Unit of Measure';

comment on column product_template.uom_po_id is 'Purchase Unit';

comment on column product_template.company_id is 'Company';

comment on column product_template.color is 'Color Index';

comment on column product_template.create_uid is 'Created by';

comment on column product_template.write_uid is 'Last Updated by';

comment on column product_template.type is 'Product Type';

comment on column product_template.service_tracking is 'Create on Order';

comment on column product_template.default_code is 'Internal Reference';

comment on column product_template.name is 'Name';

comment on column product_template.description is 'Description';

comment on column product_template.description_purchase is 'Purchase Description';

comment on column product_template.description_sale is 'Sales Description';

comment on column product_template.product_properties is 'Properties';

comment on column product_template.list_price is 'Sales Price';

comment on column product_template.volume is 'Volume';

comment on column product_template.weight is 'Weight';

comment on column product_template.sale_ok is 'Sales';

comment on column product_template.purchase_ok is 'Purchase';

comment on column product_template.active is 'Active';

comment on column product_template.can_image_1024_be_zoomed is 'Can Image 1024 be zoomed';

comment on column product_template.has_configurable_attributes is 'Is a configurable product';

comment on column product_template.is_favorite is 'Favorite';

comment on column product_template.create_date is 'Created on';

comment on column product_template.write_date is 'Last Updated on';

comment on column product_template.property_account_income_id is 'Income Account';

comment on column product_template.property_account_expense_id is 'Expense Account';

comment on column product_template.service_type is 'Track Service';

comment on column product_template.sale_line_warn is 'Sales Order Line';

comment on column product_template.expense_policy is 'Re-Invoice Costs';

comment on column product_template.invoice_policy is 'Invoicing Policy';

comment on column product_template.sale_line_warn_msg is 'Message for Sales Order Line';

comment on column product_template.project_id is 'Project';

comment on column product_template.project_template_id is 'Project Template';

comment on column product_template.sale_delay is 'Customer Lead Time';

comment on column product_template.tracking is 'Tracking';

comment on column product_template.responsible_id is 'Responsible';

comment on column product_template.property_stock_production is 'Production Location';

comment on column product_template.property_stock_inventory is 'Inventory Location';

comment on column product_template.description_picking is 'Description on Picking';

comment on column product_template.description_pickingout is 'Description on Delivery Orders';

comment on column product_template.description_pickingin is 'Description on Receptions';

comment on column product_template.is_storable is 'Track Inventory';

comment on column product_template.lot_valuated is 'Valuation by Lot/Serial number';

comment on column product_template.public_description is 'Product Description';

comment on column product_template.available_in_pos is 'Available in POS';

comment on column product_template.to_weight is 'To Weigh With Scale';

create index product_template__company_id_index
    on product_template (company_id);

create index product_template__property_account_income_id_index
    on product_template ((property_account_income_id IS NOT NULL))
    where (property_account_income_id IS NOT NULL);

create index product_template__property_account_expense_id_index
    on product_template ((property_account_expense_id IS NOT NULL))
    where (property_account_expense_id IS NOT NULL);

create index product_template__project_id_index
    on product_template ((project_id IS NOT NULL))
    where (project_id IS NOT NULL);

create index product_template__project_template_id_index
    on product_template ((project_template_id IS NOT NULL))
    where (project_template_id IS NOT NULL);

create index product_template__responsible_id_index
    on product_template ((responsible_id IS NOT NULL))
    where (responsible_id IS NOT NULL);

create index product_template__property_stock_production_index
    on product_template ((property_stock_production IS NOT NULL))
    where (property_stock_production IS NOT NULL);

create index product_template__property_stock_inventory_index
    on product_template ((property_stock_inventory IS NOT NULL))
    where (property_stock_inventory IS NOT NULL);

---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------

drop table if exists product_product_exp;
create table product_product_exp
(
    id                               integer,
    product_tmpl_id                  integer,
    create_uid                       integer,
    write_uid                        integer,
    default_code                     varchar,
    barcode                          varchar,
    combination_indices              varchar,
    standard_price                   jsonb,
    volume                           numeric,
    weight                           numeric,
    active                           boolean,
    can_image_variant_1024_be_zoomed boolean,
    write_date                       timestamp,
    create_date                      timestamp,
    lot_properties_definition        jsonb,
    operation                        varchar(1),
    operation_sys                    timestamp default CURRENT_TIMESTAMP,
    exported                         varchar(1)
);

drop table if exists product_product;
create table product_product
(
    id                               integer primary key,
    product_tmpl_id                  integer not null,
    create_uid                       integer,
    write_uid                        integer,
    default_code                     varchar,
    barcode                          varchar,
    combination_indices              varchar,
    standard_price                   jsonb,
    volume                           numeric,
    weight                           numeric,
    active                           boolean,
    can_image_variant_1024_be_zoomed boolean,
    write_date                       timestamp,
    create_date                      timestamp,
    lot_properties_definition        jsonb
);

comment on table product_product is 'Product Variant';

comment on column product_product.product_tmpl_id is 'Product Template';

comment on column product_product.create_uid is 'Created by';

comment on column product_product.write_uid is 'Last Updated by';

comment on column product_product.default_code is 'Internal Reference';

comment on column product_product.barcode is 'Barcode';

comment on column product_product.combination_indices is 'Combination Indices';

comment on column product_product.standard_price is 'Cost';

comment on column product_product.volume is 'Volume';

comment on column product_product.weight is 'Weight';

comment on column product_product.active is 'Active';

comment on column product_product.can_image_variant_1024_be_zoomed is 'Can Variant Image 1024 be zoomed';

comment on column product_product.write_date is 'Write Date';

comment on column product_product.create_date is 'Created on';

comment on column product_product.lot_properties_definition is 'Lot Properties';

create unique index product_product_combination_unique
    on product_product (product_tmpl_id, combination_indices)
    where (active IS TRUE);

create index product_product__default_code_index
    on product_product (default_code);

create index product_product__product_tmpl_id_index
    on product_product (product_tmpl_id);

create index product_product__barcode_index
    on product_product (barcode)
    where (barcode IS NOT NULL);

create index product_product__combination_indices_index
    on product_product (combination_indices);

create index product_product__standard_price_index
    on product_product ((standard_price IS NOT NULL))
    where (standard_price IS NOT NULL);

---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------

drop table if exists res_partner_exp;
create table res_partner_exp
(
    id                                       integer,
    company_id                               integer,
    create_date                              timestamp,
    name                                     varchar,
    title                                    integer,
    parent_id                                integer,
    user_id                                  integer,
    state_id                                 integer,
    country_id                               integer,
    industry_id                              integer,
    color                                    integer,
    commercial_partner_id                    integer,
    create_uid                               integer,
    write_uid                                integer,
    complete_name                            varchar,
    ref                                      varchar,
    lang                                     varchar,
    tz                                       varchar,
    vat                                      varchar,
    company_registry                         varchar,
    website                                  varchar,
    function                                 varchar,
    type                                     varchar,
    street                                   varchar,
    street2                                  varchar,
    zip                                      varchar,
    city                                     varchar,
    email                                    varchar,
    phone                                    varchar,
    mobile                                   varchar,
    commercial_company_name                  varchar,
    company_name                             varchar,
    barcode                                  jsonb,
    comment                                  text,
    partner_latitude                         numeric,
    partner_longitude                        numeric,
    active                                   boolean,
    employee                                 boolean,
    is_company                               boolean,
    partner_share                            boolean,
    write_date                               timestamp,
    message_bounce                           integer,
    email_normalized                         varchar,
    signup_type                              varchar,
    specific_property_product_pricelist      jsonb,
    partner_gid                              integer,
    additional_info                          varchar,
    phone_sanitized                          varchar,
    invoice_template_pdf_report_id           integer,
    supplier_rank                            integer,
    customer_rank                            integer,
    invoice_warn                             varchar,
    autopost_bills                           varchar,
    credit_limit                             jsonb,
    property_account_payable_id              jsonb,
    property_account_receivable_id           jsonb,
    property_account_position_id             jsonb,
    property_payment_term_id                 jsonb,
    property_supplier_payment_term_id        jsonb,
    trust                                    jsonb,
    ignore_abnormal_invoice_date             jsonb,
    ignore_abnormal_invoice_amount           jsonb,
    invoice_sending_method                   jsonb,
    invoice_edi_format_store                 jsonb,
    property_outbound_payment_method_line_id jsonb,
    property_inbound_payment_method_line_id  jsonb,
    invoice_warn_msg                         text,
    debit_limit                              numeric,
    peppol_endpoint                          varchar,
    peppol_eas                               varchar,
    sale_warn                                varchar,
    sale_warn_msg                            text,
    picking_warn                             varchar,
    property_stock_customer                  jsonb,
    property_stock_supplier                  jsonb,
    picking_warn_msg                         text,
    operation                                varchar(1),
    operation_sys                            timestamp default CURRENT_TIMESTAMP,
    exported                                 varchar(1)
);

drop table if exists res_partner;
create table res_partner
(
    id                                       integer primary key,
    company_id                               integer,
    create_date                              timestamp,
    name                                     varchar,
    title                                    integer,
    parent_id                                integer,
    user_id                                  integer,
    state_id                                 integer,
    country_id                               integer,
    industry_id                              integer,
    color                                    integer,
    commercial_partner_id                    integer,
    create_uid                               integer,
    write_uid                                integer,
    complete_name                            varchar,
    ref                                      varchar,
    lang                                     varchar,
    tz                                       varchar,
    vat                                      varchar,
    company_registry                         varchar,
    website                                  varchar,
    function                                 varchar,
    type                                     varchar,
    street                                   varchar,
    street2                                  varchar,
    zip                                      varchar,
    city                                     varchar,
    email                                    varchar,
    phone                                    varchar,
    mobile                                   varchar,
    commercial_company_name                  varchar,
    company_name                             varchar,
    barcode                                  jsonb,
    comment                                  text,
    partner_latitude                         numeric,
    partner_longitude                        numeric,
    active                                   boolean,
    employee                                 boolean,
    is_company                               boolean,
    partner_share                            boolean,
    write_date                               timestamp,
    message_bounce                           integer,
    email_normalized                         varchar,
    signup_type                              varchar,
    specific_property_product_pricelist      jsonb,
    partner_gid                              integer,
    additional_info                          varchar,
    phone_sanitized                          varchar,
    invoice_template_pdf_report_id           integer,
    supplier_rank                            integer,
    customer_rank                            integer,
    invoice_warn                             varchar,
    autopost_bills                           varchar not null,
    credit_limit                             jsonb,
    property_account_payable_id              jsonb,
    property_account_receivable_id           jsonb,
    property_account_position_id             jsonb,
    property_payment_term_id                 jsonb,
    property_supplier_payment_term_id        jsonb,
    trust                                    jsonb,
    ignore_abnormal_invoice_date             jsonb,
    ignore_abnormal_invoice_amount           jsonb,
    invoice_sending_method                   jsonb,
    invoice_edi_format_store                 jsonb,
    property_outbound_payment_method_line_id jsonb,
    property_inbound_payment_method_line_id  jsonb,
    invoice_warn_msg                         text,
    debit_limit                              numeric,
    peppol_endpoint                          varchar,
    peppol_eas                               varchar,
    sale_warn                                varchar,
    sale_warn_msg                            text,
    picking_warn                             varchar,
    property_stock_customer                  jsonb,
    property_stock_supplier                  jsonb,
    picking_warn_msg                         text,
    constraint res_partner_check_name
        check ((((type)::text = 'contact'::text) AND (name IS NOT NULL)) OR ((type)::text <> 'contact'::text))
);

comment on column res_partner.title is 'Title';

comment on column res_partner.parent_id is 'Related Company';

comment on column res_partner.user_id is 'Salesperson';

comment on column res_partner.state_id is 'State';

comment on column res_partner.country_id is 'Country';

comment on column res_partner.industry_id is 'Industry';

comment on column res_partner.color is 'Color Index';

comment on column res_partner.commercial_partner_id is 'Commercial Entity';

comment on column res_partner.create_uid is 'Created by';

comment on column res_partner.write_uid is 'Last Updated by';

comment on column res_partner.complete_name is 'Complete Name';

comment on column res_partner.ref is 'Reference';

comment on column res_partner.lang is 'Language';

comment on column res_partner.tz is 'Timezone';

comment on column res_partner.vat is 'Tax ID';

comment on column res_partner.company_registry is 'Company ID';

comment on column res_partner.website is 'Website Link';

comment on column res_partner.function is 'Job Position';

comment on column res_partner.type is 'Address Type';

comment on column res_partner.street is 'Street';

comment on column res_partner.street2 is 'Street2';

comment on column res_partner.zip is 'Zip';

comment on column res_partner.city is 'City';

comment on column res_partner.email is 'Email';

comment on column res_partner.phone is 'Phone';

comment on column res_partner.mobile is 'Mobile';

comment on column res_partner.commercial_company_name is 'Company Name Entity';

comment on column res_partner.company_name is 'Company Name';

comment on column res_partner.barcode is 'Barcode';

comment on column res_partner.comment is 'Notes';

comment on column res_partner.partner_latitude is 'Geo Latitude';

comment on column res_partner.partner_longitude is 'Geo Longitude';

comment on column res_partner.active is 'Active';

comment on column res_partner.employee is 'Employee';

comment on column res_partner.is_company is 'Is a Company';

comment on column res_partner.partner_share is 'Share Partner';

comment on column res_partner.write_date is 'Last Updated on';

comment on column res_partner.message_bounce is 'Bounce';

comment on column res_partner.email_normalized is 'Normalized Email';

comment on column res_partner.signup_type is 'Signup Token Type';

comment on column res_partner.specific_property_product_pricelist is 'Specific Property Product Pricelist';

comment on column res_partner.partner_gid is 'Company database ID';

comment on column res_partner.additional_info is 'Additional info';

comment on column res_partner.phone_sanitized is 'Sanitized Number';

comment on column res_partner.invoice_template_pdf_report_id is 'Invoice template';

comment on column res_partner.supplier_rank is 'Supplier Rank';

comment on column res_partner.customer_rank is 'Customer Rank';

comment on column res_partner.invoice_warn is 'Invoice';

comment on column res_partner.autopost_bills is 'Auto-post bills';

comment on column res_partner.credit_limit is 'Credit Limit';

comment on column res_partner.property_account_payable_id is 'Account Payable';

comment on column res_partner.property_account_receivable_id is 'Account Receivable';

comment on column res_partner.property_account_position_id is 'Fiscal Position';

comment on column res_partner.property_payment_term_id is 'Customer Payment Terms';

comment on column res_partner.property_supplier_payment_term_id is 'Vendor Payment Terms';

comment on column res_partner.trust is 'Degree of trust you have in this debtor';

comment on column res_partner.ignore_abnormal_invoice_date is 'Ignore Abnormal Invoice Date';

comment on column res_partner.ignore_abnormal_invoice_amount is 'Ignore Abnormal Invoice Amount';

comment on column res_partner.invoice_sending_method is 'Invoice sending';

comment on column res_partner.invoice_edi_format_store is 'Invoice Edi Format Store';

comment on column res_partner.property_outbound_payment_method_line_id is 'Property Outbound Payment Method Line';

comment on column res_partner.property_inbound_payment_method_line_id is 'Property Inbound Payment Method Line';

comment on column res_partner.invoice_warn_msg is 'Message for Invoice';

comment on column res_partner.debit_limit is 'Payable Limit';

comment on column res_partner.peppol_endpoint is 'Peppol Endpoint';

comment on column res_partner.peppol_eas is 'Peppol e-address (EAS)';

comment on column res_partner.sale_warn is 'Sales Warnings';

comment on column res_partner.sale_warn_msg is 'Message for Sales Order';

comment on column res_partner.picking_warn is 'Stock Picking';

comment on column res_partner.property_stock_customer is 'Customer Location';

comment on column res_partner.property_stock_supplier is 'Vendor Location';

comment on column res_partner.picking_warn_msg is 'Message for Stock Picking';

comment on constraint res_partner_check_name on res_partner is 'CHECK( (type=''contact'' AND name IS NOT NULL) or (type!=''contact'') )';

create index res_partner__name_index
    on res_partner (name);

create index res_partner__complete_name_index
    on res_partner (complete_name);

create index res_partner__parent_id_index
    on res_partner (parent_id);

create index res_partner__ref_index
    on res_partner (ref);

create index res_partner__vat_index
    on res_partner (vat);

create index res_partner__company_id_index
    on res_partner (company_id);

create index res_partner__commercial_partner_id_index
    on res_partner (commercial_partner_id);

create index res_partner__barcode_index
    on res_partner ((barcode IS NOT NULL))
    where (barcode IS NOT NULL);

create index res_partner__specific_property_product_pricelist_index
    on res_partner ((specific_property_product_pricelist IS NOT NULL))
    where (specific_property_product_pricelist IS NOT NULL);

create index res_partner_mobile_partial_tgm
    on res_partner (regexp_replace(mobile::text, '[\s\\./\(\)\-]'::text, ''::text, 'g'::text))
    where (mobile IS NOT NULL);

create index res_partner_phone_partial_tgm
    on res_partner (regexp_replace(phone::text, '[\s\\./\(\)\-]'::text, ''::text, 'g'::text))
    where (phone IS NOT NULL);

create index res_partner__credit_limit_index
    on res_partner ((credit_limit IS NOT NULL))
    where (credit_limit IS NOT NULL);

create index res_partner__property_account_payable_id_index
    on res_partner ((property_account_payable_id IS NOT NULL))
    where (property_account_payable_id IS NOT NULL);

create index res_partner__property_account_receivable_id_index
    on res_partner ((property_account_receivable_id IS NOT NULL))
    where (property_account_receivable_id IS NOT NULL);

create index res_partner__property_account_position_id_index
    on res_partner ((property_account_position_id IS NOT NULL))
    where (property_account_position_id IS NOT NULL);

create index res_partner__property_payment_term_id_index
    on res_partner ((property_payment_term_id IS NOT NULL))
    where (property_payment_term_id IS NOT NULL);

create index res_partner__property_supplier_payment_term_id_index
    on res_partner ((property_supplier_payment_term_id IS NOT NULL))
    where (property_supplier_payment_term_id IS NOT NULL);

create index res_partner__trust_index
    on res_partner ((trust IS NOT NULL))
    where (trust IS NOT NULL);

create index res_partner__ignore_abnormal_invoice_date_index
    on res_partner ((ignore_abnormal_invoice_date IS NOT NULL))
    where (ignore_abnormal_invoice_date IS NOT NULL);

create index res_partner__ignore_abnormal_invoice_amount_index
    on res_partner ((ignore_abnormal_invoice_amount IS NOT NULL))
    where (ignore_abnormal_invoice_amount IS NOT NULL);

create index res_partner__invoice_sending_method_index
    on res_partner ((invoice_sending_method IS NOT NULL))
    where (invoice_sending_method IS NOT NULL);

create index res_partner__invoice_edi_format_store_index
    on res_partner ((invoice_edi_format_store IS NOT NULL))
    where (invoice_edi_format_store IS NOT NULL);

create index res_partner__property_outbound_payment_method_line_id_index
    on res_partner ((property_outbound_payment_method_line_id IS NOT NULL))
    where (property_outbound_payment_method_line_id IS NOT NULL);

create index res_partner__property_inbound_payment_method_line_id_index
    on res_partner ((property_inbound_payment_method_line_id IS NOT NULL))
    where (property_inbound_payment_method_line_id IS NOT NULL);

create index res_partner__property_stock_customer_index
    on res_partner ((property_stock_customer IS NOT NULL))
    where (property_stock_customer IS NOT NULL);

create index res_partner__property_stock_supplier_index
    on res_partner ((property_stock_supplier IS NOT NULL))
    where (property_stock_supplier IS NOT NULL);

---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------

drop table if exists sale_order_exp;
create table sale_order_exp
(
    id                           integer,
    campaign_id                  integer,
    source_id                    integer,
    medium_id                    integer,
    company_id                   integer,
    partner_id                   integer,
    journal_id                   integer,
    partner_invoice_id           integer,
    partner_shipping_id          integer,
    fiscal_position_id           integer,
    payment_term_id              integer,
    pricelist_id                 integer,
    currency_id                  integer,
    user_id                      integer,
    team_id                      integer,
    create_uid                   integer,
    write_uid                    integer,
    access_token                 varchar,
    name                         varchar,
    state                        varchar,
    client_order_ref             varchar,
    origin                       varchar,
    reference                    varchar,
    signed_by                    varchar,
    invoice_status               varchar,
    validity_date                date,
    note                         text,
    currency_rate                numeric,
    amount_untaxed               numeric,
    amount_tax                   numeric,
    amount_total                 numeric,
    locked                       boolean,
    require_signature            boolean,
    require_payment              boolean,
    create_date                  timestamp,
    commitment_date              timestamp,
    date_order                   timestamp,
    signed_on                    timestamp,
    write_date                   timestamp,
    prepayment_percent           double precision,
    pending_email_template_id    integer,
    sale_order_template_id       integer,
    customizable_pdf_form_fields jsonb,
    project_id                   integer,
    incoterm                     integer,
    warehouse_id                 integer,
    procurement_group_id         integer,
    incoterm_location            varchar,
    picking_policy               varchar,
    delivery_status              varchar,
    effective_date               timestamp,
    amount_unpaid                numeric,
    operation                    varchar(1),
    operation_sys                timestamp default CURRENT_TIMESTAMP,
    exported                     varchar(1)
);

drop table if exists sale_order;
create table sale_order
(
    id                           integer primary key,
    campaign_id                  integer,
    source_id                    integer,
    medium_id                    integer,
    company_id                   integer   not null,
    partner_id                   integer   not null,
    journal_id                   integer,
    partner_invoice_id           integer   not null,
    partner_shipping_id          integer   not null,
    fiscal_position_id           integer,
    payment_term_id              integer,
    pricelist_id                 integer,
    currency_id                  integer,
    user_id                      integer,
    team_id                      integer,
    create_uid                   integer,
    write_uid                    integer,
    access_token                 varchar,
    name                         varchar   not null,
    state                        varchar,
    client_order_ref             varchar,
    origin                       varchar,
    reference                    varchar,
    signed_by                    varchar,
    invoice_status               varchar,
    validity_date                date,
    note                         text,
    currency_rate                numeric,
    amount_untaxed               numeric,
    amount_tax                   numeric,
    amount_total                 numeric,
    locked                       boolean,
    require_signature            boolean,
    require_payment              boolean,
    create_date                  timestamp,
    commitment_date              timestamp,
    date_order                   timestamp not null,
    signed_on                    timestamp,
    write_date                   timestamp,
    prepayment_percent           double precision,
    pending_email_template_id    integer,
    sale_order_template_id       integer,
    customizable_pdf_form_fields jsonb,
    project_id                   integer,
    incoterm                     integer,
    warehouse_id                 integer,
    procurement_group_id         integer,
    incoterm_location            varchar,
    picking_policy               varchar   not null,
    delivery_status              varchar,
    effective_date               timestamp,
    amount_unpaid                numeric,
    constraint sale_order_date_order_conditional_required
        check ((((state)::text = 'sale'::text) AND (date_order IS NOT NULL)) OR ((state)::text <> 'sale'::text))
);

comment on table sale_order is 'Sales Order';

comment on column sale_order.campaign_id is 'Campaign';

comment on column sale_order.source_id is 'Source';

comment on column sale_order.medium_id is 'Medium';

comment on column sale_order.company_id is 'Company';

comment on column sale_order.partner_id is 'Customer';

comment on column sale_order.journal_id is 'Invoicing Journal';

comment on column sale_order.partner_invoice_id is 'Invoice Address';

comment on column sale_order.partner_shipping_id is 'Delivery Address';

comment on column sale_order.fiscal_position_id is 'Fiscal Position';

comment on column sale_order.payment_term_id is 'Payment Terms';

comment on column sale_order.pricelist_id is 'Pricelist';

comment on column sale_order.currency_id is 'Currency';

comment on column sale_order.user_id is 'Salesperson';

comment on column sale_order.team_id is 'Sales Team';

comment on column sale_order.create_uid is 'Created by';

comment on column sale_order.write_uid is 'Last Updated by';

comment on column sale_order.access_token is 'Security Token';

comment on column sale_order.name is 'Order Reference';

comment on column sale_order.state is 'Status';

comment on column sale_order.client_order_ref is 'Customer Reference';

comment on column sale_order.origin is 'Source Document';

comment on column sale_order.reference is 'Payment Ref.';

comment on column sale_order.signed_by is 'Signed By';

comment on column sale_order.invoice_status is 'Invoice Status';

comment on column sale_order.validity_date is 'Expiration';

comment on column sale_order.note is 'Terms and conditions';

comment on column sale_order.currency_rate is 'Currency Rate';

comment on column sale_order.amount_untaxed is 'Untaxed Amount';

comment on column sale_order.amount_tax is 'Taxes';

comment on column sale_order.amount_total is 'Total';

comment on column sale_order.locked is 'Locked';

comment on column sale_order.require_signature is 'Online signature';

comment on column sale_order.require_payment is 'Online payment';

comment on column sale_order.create_date is 'Creation Date';

comment on column sale_order.commitment_date is 'Delivery Date';

comment on column sale_order.date_order is 'Order Date';

comment on column sale_order.signed_on is 'Signed On';

comment on column sale_order.write_date is 'Last Updated on';

comment on column sale_order.prepayment_percent is 'Prepayment percentage';

comment on column sale_order.pending_email_template_id is 'Pending Email Template';

comment on column sale_order.customizable_pdf_form_fields is 'Customizable PDF Form Fields';

comment on column sale_order.project_id is 'Project';

comment on column sale_order.incoterm is 'Incoterm';

comment on column sale_order.warehouse_id is 'Warehouse';

comment on column sale_order.procurement_group_id is 'Procurement Group';

comment on column sale_order.incoterm_location is 'Incoterm Location';

comment on column sale_order.picking_policy is 'Shipping Policy';

comment on column sale_order.delivery_status is 'Delivery Status';

comment on column sale_order.effective_date is 'Effective Date';

comment on column sale_order.amount_unpaid is 'Amount To Pay In POS';

comment on constraint sale_order_date_order_conditional_required on sale_order is 'CHECK((state = ''sale'' AND date_order IS NOT NULL) OR state != ''sale'')';

create index sale_order_date_order_id_idx
    on sale_order (date_order desc, id desc);

create index sale_order__campaign_id_index
    on sale_order (campaign_id)
    where (campaign_id IS NOT NULL);

create index sale_order__source_id_index
    on sale_order (source_id)
    where (source_id IS NOT NULL);

create index sale_order__medium_id_index
    on sale_order (medium_id)
    where (medium_id IS NOT NULL);

create index sale_order__company_id_index
    on sale_order (company_id);

create index sale_order__partner_id_index
    on sale_order (partner_id);

create index sale_order__state_index
    on sale_order (state);

create index sale_order__create_date_index
    on sale_order (create_date);

create index sale_order__partner_invoice_id_index
    on sale_order (partner_invoice_id)
    where (partner_invoice_id IS NOT NULL);

create index sale_order__partner_shipping_id_index
    on sale_order (partner_shipping_id)
    where (partner_shipping_id IS NOT NULL);

create index sale_order__user_id_index
    on sale_order (user_id);

---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------

drop table if exists sale_order_line_exp;
create table sale_order_line_exp
(
    id                        integer,
    order_id                  integer,
    sequence                  integer,
    company_id                integer,
    currency_id               integer,
    order_partner_id          integer,
    salesman_id               integer,
    product_id                integer,
    product_uom               integer,
    linked_line_id            integer,
    combo_item_id             integer,
    product_packaging_id      integer,
    create_uid                integer,
    write_uid                 integer,
    state                     varchar,
    display_type              varchar,
    virtual_id                varchar,
    linked_virtual_id         varchar,
    qty_delivered_method      varchar,
    invoice_status            varchar,
    analytic_distribution     jsonb,
    name                      text,
    product_uom_qty           numeric,
    price_unit                numeric,
    discount                  numeric,
    price_subtotal            numeric,
    price_total               numeric,
    price_reduce_taxexcl      numeric,
    price_reduce_taxinc       numeric,
    qty_delivered             numeric,
    qty_invoiced              numeric,
    qty_to_invoice            numeric,
    untaxed_amount_invoiced   numeric,
    untaxed_amount_to_invoice numeric,
    is_downpayment            boolean,
    is_expense                boolean,
    create_date               timestamp,
    write_date                timestamp,
    technical_price_unit      double precision,
    price_tax                 double precision,
    product_packaging_qty     double precision,
    customer_lead             double precision,
    is_service                boolean,
    project_id                integer,
    task_id                   integer,
    route_id                  integer,
    warehouse_id              integer,
    operation                 varchar(1),
    operation_sys             timestamp default CURRENT_TIMESTAMP,
    exported                  varchar(1)
);

drop table if exists sale_order_line;
create table sale_order_line
(
    id                        integer primary key,
    order_id                  integer       not null,
    sequence                  integer,
    company_id                integer,
    currency_id               integer,
    order_partner_id          integer,
    salesman_id               integer,
    product_id                integer,
    product_uom               integer,
    linked_line_id            integer,
    combo_item_id             integer,
    product_packaging_id      integer,
    create_uid                integer,
    write_uid                 integer,
    state                     varchar,
    display_type              varchar,
    virtual_id                varchar,
    linked_virtual_id         varchar,
    qty_delivered_method      varchar,
    invoice_status            varchar,
    analytic_distribution     jsonb,
    name                      text             not null,
    product_uom_qty           numeric          not null,
    price_unit                numeric          not null,
    discount                  numeric,
    price_subtotal            numeric,
    price_total               numeric,
    price_reduce_taxexcl      numeric,
    price_reduce_taxinc       numeric,
    qty_delivered             numeric,
    qty_invoiced              numeric,
    qty_to_invoice            numeric,
    untaxed_amount_invoiced   numeric,
    untaxed_amount_to_invoice numeric,
    is_downpayment            boolean,
    is_expense                boolean,
    create_date               timestamp,
    write_date                timestamp,
    technical_price_unit      double precision,
    price_tax                 double precision,
    product_packaging_qty     double precision,
    customer_lead             double precision not null,
    is_service                boolean,
    project_id                integer,
    task_id                   integer,
    route_id                  integer,
    warehouse_id              integer,
    constraint sale_order_line_accountable_required_fields
        check ((display_type IS NOT NULL) OR is_downpayment OR
               ((product_id IS NOT NULL) AND (product_uom IS NOT NULL))),
    constraint sale_order_line_non_accountable_null_fields
        check ((display_type IS NULL) OR
               ((product_id IS NULL) AND (price_unit = (0)::numeric) AND (product_uom_qty = (0)::numeric) AND
                (product_uom IS NULL) AND (customer_lead = (0)::double precision)))
);

comment on table sale_order_line is 'Sales Order Line';

comment on column sale_order_line.order_id is 'Order Reference';

comment on column sale_order_line.sequence is 'Sequence';

comment on column sale_order_line.company_id is 'Company';

comment on column sale_order_line.currency_id is 'Currency';

comment on column sale_order_line.order_partner_id is 'Customer';

comment on column sale_order_line.salesman_id is 'Salesperson';

comment on column sale_order_line.product_id is 'Product';

comment on column sale_order_line.product_uom is 'Unit of Measure';

comment on column sale_order_line.linked_line_id is 'Linked Order Line';

comment on column sale_order_line.combo_item_id is 'Combo Item';

comment on column sale_order_line.product_packaging_id is 'Packaging';

comment on column sale_order_line.create_uid is 'Created by';

comment on column sale_order_line.write_uid is 'Last Updated by';

comment on column sale_order_line.state is 'Order Status';

comment on column sale_order_line.display_type is 'Display Type';

comment on column sale_order_line.virtual_id is 'Virtual';

comment on column sale_order_line.linked_virtual_id is 'Linked Virtual';

comment on column sale_order_line.qty_delivered_method is 'Method to update delivered qty';

comment on column sale_order_line.invoice_status is 'Invoice Status';

comment on column sale_order_line.analytic_distribution is 'Analytic Distribution';

comment on column sale_order_line.name is 'Description';

comment on column sale_order_line.product_uom_qty is 'Quantity';

comment on column sale_order_line.price_unit is 'Unit Price';

comment on column sale_order_line.discount is 'Discount (%)';

comment on column sale_order_line.price_subtotal is 'Subtotal';

comment on column sale_order_line.price_total is 'Total';

comment on column sale_order_line.price_reduce_taxexcl is 'Price Reduce Tax excl';

comment on column sale_order_line.price_reduce_taxinc is 'Price Reduce Tax incl';

comment on column sale_order_line.qty_delivered is 'Delivery Quantity';

comment on column sale_order_line.qty_invoiced is 'Invoiced Quantity';

comment on column sale_order_line.qty_to_invoice is 'Quantity To Invoice';

comment on column sale_order_line.untaxed_amount_invoiced is 'Untaxed Invoiced Amount';

comment on column sale_order_line.untaxed_amount_to_invoice is 'Untaxed Amount To Invoice';

comment on column sale_order_line.is_downpayment is 'Is a down payment';

comment on column sale_order_line.is_expense is 'Is expense';

comment on column sale_order_line.create_date is 'Created on';

comment on column sale_order_line.write_date is 'Last Updated on';

comment on column sale_order_line.technical_price_unit is 'Technical Price Unit';

comment on column sale_order_line.price_tax is 'Total Tax';

comment on column sale_order_line.product_packaging_qty is 'Packaging Quantity';

comment on column sale_order_line.customer_lead is 'Lead Time';

comment on column sale_order_line.project_id is 'Generated Project';

comment on column sale_order_line.task_id is 'Generated Task';

comment on column sale_order_line.route_id is 'Route';

comment on column sale_order_line.warehouse_id is 'Warehouse';

comment on constraint sale_order_line_accountable_required_fields on sale_order_line is 'CHECK(display_type IS NOT NULL OR is_downpayment OR (product_id IS NOT NULL AND product_uom IS NOT NULL))';

comment on constraint sale_order_line_non_accountable_null_fields on sale_order_line is 'CHECK(display_type IS NULL OR (product_id IS NULL AND price_unit = 0 AND product_uom_qty = 0 AND product_uom IS NULL AND customer_lead = 0))';

create index sale_order_line__order_id_index
    on sale_order_line (order_id);

create index sale_order_line__company_id_index
    on sale_order_line (company_id);

create index sale_order_line__order_partner_id_index
    on sale_order_line (order_partner_id);

create index sale_order_line__product_id_index
    on sale_order_line (product_id)
    where (product_id IS NOT NULL);

create index sale_order_line__linked_line_id_index
    on sale_order_line (linked_line_id);

create index sale_order_line_name_search_services_index
    on sale_order_line (order_id desc, sequence asc, id asc)
    where (is_service = true);

create index sale_order_line__project_id_index
    on sale_order_line (project_id);

create index sale_order_line__task_id_index
    on sale_order_line (task_id);

---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------

drop table if exists sale_advance_payment_inv_exp;
create table sale_advance_payment_inv_exp
(
    id                     integer,
    currency_id            integer,
    company_id             integer,
    create_uid             integer,
    write_uid              integer,
    advance_payment_method varchar,
    fixed_amount           numeric,
    deduct_down_payments   boolean,
    consolidated_billing   boolean,
    create_date            timestamp,
    write_date             timestamp,
    amount                 double precision,
    operation              varchar(1),
    operation_sys          timestamp default CURRENT_TIMESTAMP,
    exported               varchar(1)
);

drop table if exists sale_advance_payment_inv;
create table sale_advance_payment_inv
(
    id                     integer primary key,
    currency_id            integer,
    company_id             integer,
    create_uid             integer,
    write_uid              integer,
    advance_payment_method varchar not null,
    fixed_amount           numeric,
    deduct_down_payments   boolean,
    consolidated_billing   boolean,
    create_date            timestamp,
    write_date             timestamp,
    amount                 double precision
);

comment on table sale_advance_payment_inv is 'Sales Advance Payment Invoice';

comment on column sale_advance_payment_inv.currency_id is 'Currency';

comment on column sale_advance_payment_inv.company_id is 'Company';

comment on column sale_advance_payment_inv.create_uid is 'Created by';

comment on column sale_advance_payment_inv.write_uid is 'Last Updated by';

comment on column sale_advance_payment_inv.advance_payment_method is 'Create Invoice';

comment on column sale_advance_payment_inv.fixed_amount is 'Down Payment Amount (Fixed)';

comment on column sale_advance_payment_inv.deduct_down_payments is 'Deduct down payments';

comment on column sale_advance_payment_inv.consolidated_billing is 'Consolidated Billing';

comment on column sale_advance_payment_inv.create_date is 'Created on';

comment on column sale_advance_payment_inv.write_date is 'Last Updated on';

comment on column sale_advance_payment_inv.amount is 'Down Payment';


---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------

drop table if exists account_move_exp;
create table account_move_exp
(
    id                                integer,
    sequence_number                   integer,
    message_main_attachment_id        integer,
    journal_id                        integer,
    company_id                        integer,
    origin_payment_id                 integer,
    statement_line_id                 integer,
    tax_cash_basis_rec_id             integer,
    tax_cash_basis_origin_move_id     integer,
    auto_post_origin_id               integer,
    secure_sequence_number            integer,
    invoice_payment_term_id           integer,
    partner_id                        integer,
    commercial_partner_id             integer,
    partner_shipping_id               integer,
    partner_bank_id                   integer,
    fiscal_position_id                integer,
    preferred_payment_method_line_id  integer,
    currency_id                       integer,
    reversed_entry_id                 integer,
    invoice_user_id                   integer,
    invoice_incoterm_id               integer,
    invoice_cash_rounding_id          integer,
    create_uid                        integer,
    write_uid                         integer,
    sequence_prefix                   varchar,
    access_token                      varchar,
    name                              varchar,
    ref                               varchar,
    state                             varchar,
    move_type                         varchar,
    auto_post                         varchar,
    inalterable_hash                  varchar,
    payment_reference                 varchar,
    qr_code_method                    varchar,
    payment_state                     varchar,
    invoice_source_email              varchar,
    invoice_partner_display_name      varchar,
    invoice_origin                    varchar,
    incoterm_location                 varchar,
    date                              date,
    auto_post_until                   date,
    invoice_date                      date,
    invoice_date_due                  date,
    delivery_date                     date,
    sending_data                      jsonb,
    narration                         text,
    invoice_currency_rate             numeric,
    amount_untaxed                    numeric,
    amount_tax                        numeric,
    amount_total                      numeric,
    amount_residual                   numeric,
    amount_untaxed_signed             numeric,
    amount_untaxed_in_currency_signed numeric,
    amount_tax_signed                 numeric,
    amount_total_signed               numeric,
    amount_total_in_currency_signed   numeric,
    amount_residual_signed            numeric,
    quick_edit_total_amount           numeric,
    is_storno                         boolean,
    always_tax_exigible               boolean,
    checked                           boolean,
    posted_before                     boolean,
    made_sequence_gap                 boolean,
    is_manually_modified              boolean,
    is_move_sent                      boolean,
    create_date                       timestamp,
    write_date                        timestamp,
    campaign_id                       integer,
    source_id                         integer,
    medium_id                         integer,
    team_id                           integer,
    stock_move_id                     integer,
    reversed_pos_order_id             integer,
    operation                         varchar(1),
    operation_sys                     timestamp default CURRENT_TIMESTAMP,
    exported                          varchar(1)
);

drop table if exists account_move;
create table account_move
(
    id                                integer primary key,
    sequence_number                   integer,
    message_main_attachment_id        integer,
    journal_id                        integer not null,
    company_id                        integer,
    origin_payment_id                 integer,
    statement_line_id                 integer,
    tax_cash_basis_rec_id             integer,
    tax_cash_basis_origin_move_id     integer,
    auto_post_origin_id               integer,
    secure_sequence_number            integer,
    invoice_payment_term_id           integer,
    partner_id                        integer,
    commercial_partner_id             integer,
    partner_shipping_id               integer,
    partner_bank_id                   integer,
    fiscal_position_id                integer,
    preferred_payment_method_line_id  integer,
    currency_id                       integer not null,
    reversed_entry_id                 integer,
    invoice_user_id                   integer,
    invoice_incoterm_id               integer,
    invoice_cash_rounding_id          integer,
    create_uid                        integer,
    write_uid                         integer,
    sequence_prefix                   varchar,
    access_token                      varchar,
    name                              varchar,
    ref                               varchar,
    state                             varchar not null,
    move_type                         varchar not null,
    auto_post                         varchar not null,
    inalterable_hash                  varchar,
    payment_reference                 varchar,
    qr_code_method                    varchar,
    payment_state                     varchar,
    invoice_source_email              varchar,
    invoice_partner_display_name      varchar,
    invoice_origin                    varchar,
    incoterm_location                 varchar,
    date                              date    not null,
    auto_post_until                   date,
    invoice_date                      date,
    invoice_date_due                  date,
    delivery_date                     date,
    sending_data                      jsonb,
    narration                         text,
    invoice_currency_rate             numeric,
    amount_untaxed                    numeric,
    amount_tax                        numeric,
    amount_total                      numeric,
    amount_residual                   numeric,
    amount_untaxed_signed             numeric,
    amount_untaxed_in_currency_signed numeric,
    amount_tax_signed                 numeric,
    amount_total_signed               numeric,
    amount_total_in_currency_signed   numeric,
    amount_residual_signed            numeric,
    quick_edit_total_amount           numeric,
    is_storno                         boolean,
    always_tax_exigible               boolean,
    checked                           boolean,
    posted_before                     boolean,
    made_sequence_gap                 boolean,
    is_manually_modified              boolean,
    is_move_sent                      boolean,
    create_date                       timestamp,
    write_date                        timestamp,
    campaign_id                       integer,
    source_id                         integer,
    medium_id                         integer,
    team_id                           integer,
    stock_move_id                     integer,
    reversed_pos_order_id             integer
);

comment on table account_move is 'Journal Entry';

comment on column account_move.sequence_number is 'Sequence Number';

comment on column account_move.message_main_attachment_id is 'Main Attachment';

comment on column account_move.journal_id is 'Journal';

comment on column account_move.company_id is 'Company';

comment on column account_move.origin_payment_id is 'Payment';

comment on column account_move.statement_line_id is 'Statement Line';

comment on column account_move.tax_cash_basis_rec_id is 'Tax Cash Basis Entry of';

comment on column account_move.tax_cash_basis_origin_move_id is 'Cash Basis Origin';

comment on column account_move.auto_post_origin_id is 'First recurring entry';

comment on column account_move.secure_sequence_number is 'Inalterability No Gap Sequence #';

comment on column account_move.invoice_payment_term_id is 'Payment Terms';

comment on column account_move.partner_id is 'Partner';

comment on column account_move.commercial_partner_id is 'Commercial Entity';

comment on column account_move.partner_shipping_id is 'Delivery Address';

comment on column account_move.partner_bank_id is 'Recipient Bank';

comment on column account_move.fiscal_position_id is 'Fiscal Position';

comment on column account_move.preferred_payment_method_line_id is 'Preferred Payment Method Line';

comment on column account_move.currency_id is 'Currency';

comment on column account_move.reversed_entry_id is 'Reversal of';

comment on column account_move.invoice_user_id is 'Salesperson';

comment on column account_move.invoice_incoterm_id is 'Incoterm';

comment on column account_move.invoice_cash_rounding_id is 'Cash Rounding Method';

comment on column account_move.create_uid is 'Created by';

comment on column account_move.write_uid is 'Last Updated by';

comment on column account_move.sequence_prefix is 'Sequence Prefix';

comment on column account_move.access_token is 'Security Token';

comment on column account_move.name is 'Number';

comment on column account_move.ref is 'Reference';

comment on column account_move.state is 'Status';

comment on column account_move.move_type is 'Type';

comment on column account_move.auto_post is 'Auto-post';

comment on column account_move.inalterable_hash is 'Inalterability Hash';

comment on column account_move.payment_reference is 'Payment Reference';

comment on column account_move.qr_code_method is 'Payment QR-code';

comment on column account_move.payment_state is 'Payment Status';

comment on column account_move.invoice_source_email is 'Source Email';

comment on column account_move.invoice_partner_display_name is 'Invoice Partner Display Name';

comment on column account_move.invoice_origin is 'Origin';

comment on column account_move.incoterm_location is 'Incoterm Location';

comment on column account_move.date is 'Date';

comment on column account_move.auto_post_until is 'Auto-post until';

comment on column account_move.invoice_date is 'Invoice/Bill Date';

comment on column account_move.invoice_date_due is 'Due Date';

comment on column account_move.delivery_date is 'Delivery Date';

comment on column account_move.sending_data is 'Sending Data';

comment on column account_move.narration is 'Terms and Conditions';

comment on column account_move.invoice_currency_rate is 'Currency Rate';

comment on column account_move.amount_untaxed is 'Untaxed Amount';

comment on column account_move.amount_tax is 'Tax';

comment on column account_move.amount_total is 'Total';

comment on column account_move.amount_residual is 'Amount Due';

comment on column account_move.amount_untaxed_signed is 'Untaxed Amount Signed';

comment on column account_move.amount_untaxed_in_currency_signed is 'Untaxed Amount Signed Currency';

comment on column account_move.amount_tax_signed is 'Tax Signed';

comment on column account_move.amount_total_signed is 'Total Signed';

comment on column account_move.amount_total_in_currency_signed is 'Total in Currency Signed';

comment on column account_move.amount_residual_signed is 'Amount Due Signed';

comment on column account_move.quick_edit_total_amount is 'Total (Tax inc.)';

comment on column account_move.is_storno is 'Is Storno';

comment on column account_move.always_tax_exigible is 'Always Tax Exigible';

comment on column account_move.checked is 'Checked';

comment on column account_move.posted_before is 'Posted Before';

comment on column account_move.made_sequence_gap is 'Made Sequence Gap';

comment on column account_move.is_manually_modified is 'Is Manually Modified';

comment on column account_move.is_move_sent is 'Is Move Sent';

comment on column account_move.create_date is 'Created on';

comment on column account_move.write_date is 'Last Updated on';

comment on column account_move.campaign_id is 'Campaign';

comment on column account_move.source_id is 'Source';

comment on column account_move.medium_id is 'Medium';

comment on column account_move.team_id is 'Sales Team';

comment on column account_move.stock_move_id is 'Stock Move';

comment on column account_move.reversed_pos_order_id is 'Reversed POS Order';

create index account_move_checked_idx
    on account_move (journal_id)
    where (checked = false);

create index account_move_payment_idx
    on account_move (journal_id, state, payment_state, move_type, date);

create unique index account_move_unique_name
    on account_move (name, journal_id)
    where (((state)::text = 'posted'::text) AND ((name)::text <> '/'::text));

create index account_move_sequence_index
    on account_move (journal_id asc, sequence_prefix desc, sequence_number desc, name asc);

create index account_move_sequence_index2
    on account_move (journal_id asc, id desc, sequence_prefix asc);

create index account_move_journal_id_company_id_idx
    on account_move (journal_id, company_id, date);

create index account_move_made_gaps
    on account_move (journal_id, company_id, date)
    where (made_sequence_gap = true);

create index account_move_duplicate_bills_idx
    on account_move (ref)
    where ((move_type)::text = ANY ((ARRAY ['in_invoice'::character varying, 'in_refund'::character varying])::text[]));

create index account_move__message_main_attachment_id_index
    on account_move (message_main_attachment_id)
    where (message_main_attachment_id IS NOT NULL);

create index account_move__date_index
    on account_move (date);

create index account_move__move_type_index
    on account_move (move_type);

create index account_move__company_id_index
    on account_move (company_id);

create index account_move__origin_payment_id_index
    on account_move (origin_payment_id)
    where (origin_payment_id IS NOT NULL);

create index account_move__statement_line_id_index
    on account_move (statement_line_id)
    where (statement_line_id IS NOT NULL);

create index account_move__tax_cash_basis_rec_id_index
    on account_move (tax_cash_basis_rec_id)
    where (tax_cash_basis_rec_id IS NOT NULL);

create index account_move__tax_cash_basis_origin_move_id_index
    on account_move (tax_cash_basis_origin_move_id)
    where (tax_cash_basis_origin_move_id IS NOT NULL);

create index account_move__auto_post_origin_id_index
    on account_move (auto_post_origin_id)
    where (auto_post_origin_id IS NOT NULL);

create index account_move__secure_sequence_number_index
    on account_move (secure_sequence_number);

create index account_move__inalterable_hash_index
    on account_move (inalterable_hash)
    where (inalterable_hash IS NOT NULL);

create index account_move__invoice_date_index
    on account_move (invoice_date);

create index account_move__invoice_date_due_index
    on account_move (invoice_date_due);

create index account_move__partner_id_index
    on account_move (partner_id);

create index account_move__reversed_entry_id_index
    on account_move (reversed_entry_id)
    where (reversed_entry_id IS NOT NULL);

create index account_move__campaign_id_index
    on account_move (campaign_id)
    where (campaign_id IS NOT NULL);

create index account_move__source_id_index
    on account_move (source_id)
    where (source_id IS NOT NULL);

create index account_move__medium_id_index
    on account_move (medium_id)
    where (medium_id IS NOT NULL);

create index account_move__stock_move_id_index
    on account_move (stock_move_id)
    where (stock_move_id IS NOT NULL);

---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------

drop table if exists account_move_line_exp;
create table account_move_line_exp
(
    id                       integer,
    move_id                  integer,
    journal_id               integer,
    company_id               integer,
    company_currency_id      integer,
    sequence                 integer,
    account_id               integer,
    currency_id              integer,
    partner_id               integer,
    reconcile_model_id       integer,
    payment_id               integer,
    statement_line_id        integer,
    statement_id             integer,
    group_tax_id             integer,
    tax_line_id              integer,
    tax_group_id             integer,
    tax_repartition_line_id  integer,
    full_reconcile_id        integer,
    product_id               integer,
    product_uom_id           integer,
    create_uid               integer,
    write_uid                integer,
    move_name                varchar,
    parent_state             varchar,
    ref                      varchar,
    name                     varchar,
    matching_number          varchar,
    display_type             varchar,
    date                     date,
    invoice_date             date,
    date_maturity            date,
    discount_date            date,
    analytic_distribution    jsonb,
    debit                    numeric,
    credit                   numeric,
    balance                  numeric,
    amount_currency          numeric,
    tax_base_amount          numeric,
    amount_residual          numeric,
    amount_residual_currency numeric,
    quantity                 numeric,
    price_unit               numeric,
    price_subtotal           numeric,
    price_total              numeric,
    discount                 numeric,
    discount_amount_currency numeric,
    discount_balance         numeric,
    is_imported              boolean,
    tax_tag_invert           boolean,
    reconciled               boolean,
    create_date              timestamp,
    write_date               timestamp,
    is_downpayment           boolean,
    cogs_origin_id           integer,
    operation                varchar(1),
    operation_sys            timestamp default CURRENT_TIMESTAMP,
    exported                 varchar(1)
);

drop table if exists account_move_line;
create table account_move_line
(
    id                       integer primary key,
    move_id                  integer not null,
    journal_id               integer,
    company_id               integer,
    company_currency_id      integer,
    sequence                 integer,
    account_id               integer,
    currency_id              integer not null,
    partner_id               integer,
    reconcile_model_id       integer,
    payment_id               integer,
    statement_line_id        integer,
    statement_id             integer,
    group_tax_id             integer,
    tax_line_id              integer,
    tax_group_id             integer,
    tax_repartition_line_id  integer,
    full_reconcile_id        integer,
    product_id               integer,
    product_uom_id           integer,
    create_uid               integer,
    write_uid                integer,
    move_name                varchar,
    parent_state             varchar,
    ref                      varchar,
    name                     varchar,
    matching_number          varchar,
    display_type             varchar not null,
    date                     date,
    invoice_date             date,
    date_maturity            date,
    discount_date            date,
    analytic_distribution    jsonb,
    debit                    numeric,
    credit                   numeric,
    balance                  numeric,
    amount_currency          numeric,
    tax_base_amount          numeric,
    amount_residual          numeric,
    amount_residual_currency numeric,
    quantity                 numeric,
    price_unit               numeric,
    price_subtotal           numeric,
    price_total              numeric,
    discount                 numeric,
    discount_amount_currency numeric,
    discount_balance         numeric,
    is_imported              boolean,
    tax_tag_invert           boolean,
    reconciled               boolean,
    create_date              timestamp,
    write_date               timestamp,
    is_downpayment           boolean,
    cogs_origin_id           integer,
    constraint account_move_line_check_credit_debit
        check (((display_type)::text = ANY
                ((ARRAY ['line_section'::character varying, 'line_note'::character varying])::text[])) OR
               ((credit * debit) = (0)::numeric)),
    constraint account_move_line_check_amount_currency_balance_sign
        check (((display_type)::text = ANY
                ((ARRAY ['line_section'::character varying, 'line_note'::character varying])::text[])) OR
               (((balance <= (0)::numeric) AND (amount_currency <= (0)::numeric)) OR
                ((balance >= (0)::numeric) AND (amount_currency >= (0)::numeric)))),
    constraint account_move_line_check_accountable_required_fields
        check (((display_type)::text = ANY
                ((ARRAY ['line_section'::character varying, 'line_note'::character varying])::text[])) OR
               (account_id IS NOT NULL)),
    constraint account_move_line_check_non_accountable_fields_null
        check (((display_type)::text <> ALL
                ((ARRAY ['line_section'::character varying, 'line_note'::character varying])::text[])) OR
               ((amount_currency = (0)::numeric) AND (debit = (0)::numeric) AND (credit = (0)::numeric) AND
                (account_id IS NULL)))
);

comment on table account_move_line is 'Journal Item';

comment on column account_move_line.move_id is 'Journal Entry';

comment on column account_move_line.journal_id is 'Journal';

comment on column account_move_line.company_id is 'Company';

comment on column account_move_line.company_currency_id is 'Company Currency';

comment on column account_move_line.sequence is 'Sequence';

comment on column account_move_line.account_id is 'Account';

comment on column account_move_line.currency_id is 'Currency';

comment on column account_move_line.partner_id is 'Partner';

comment on column account_move_line.reconcile_model_id is 'Reconciliation Model';

comment on column account_move_line.payment_id is 'Originator Payment';

comment on column account_move_line.statement_line_id is 'Originator Statement Line';

comment on column account_move_line.statement_id is 'Statement';

comment on column account_move_line.group_tax_id is 'Originator Group of Taxes';

comment on column account_move_line.tax_line_id is 'Originator Tax';

comment on column account_move_line.tax_group_id is 'Originator tax group';

comment on column account_move_line.tax_repartition_line_id is 'Originator Tax Distribution Line';

comment on column account_move_line.full_reconcile_id is 'Matching';

comment on column account_move_line.product_id is 'Product';

comment on column account_move_line.product_uom_id is 'Unit of Measure';

comment on column account_move_line.create_uid is 'Created by';

comment on column account_move_line.write_uid is 'Last Updated by';

comment on column account_move_line.move_name is 'Number';

comment on column account_move_line.parent_state is 'Status';

comment on column account_move_line.ref is 'Reference';

comment on column account_move_line.name is 'Label';

comment on column account_move_line.matching_number is 'Matching #';

comment on column account_move_line.display_type is 'Display Type';

comment on column account_move_line.date is 'Date';

comment on column account_move_line.invoice_date is 'Invoice/Bill Date';

comment on column account_move_line.date_maturity is 'Due Date';

comment on column account_move_line.discount_date is 'Discount Date';

comment on column account_move_line.analytic_distribution is 'Analytic Distribution';

comment on column account_move_line.debit is 'Debit';

comment on column account_move_line.credit is 'Credit';

comment on column account_move_line.balance is 'Balance';

comment on column account_move_line.amount_currency is 'Amount in Currency';

comment on column account_move_line.tax_base_amount is 'Base Amount';

comment on column account_move_line.amount_residual is 'Residual Amount';

comment on column account_move_line.amount_residual_currency is 'Residual Amount in Currency';

comment on column account_move_line.quantity is 'Quantity';

comment on column account_move_line.price_unit is 'Unit Price';

comment on column account_move_line.price_subtotal is 'Subtotal';

comment on column account_move_line.price_total is 'Total';

comment on column account_move_line.discount is 'Discount (%)';

comment on column account_move_line.discount_amount_currency is 'Discount amount in Currency';

comment on column account_move_line.discount_balance is 'Discount Balance';

comment on column account_move_line.is_imported is 'Is Imported';

comment on column account_move_line.tax_tag_invert is 'Invert Tags';

comment on column account_move_line.reconciled is 'Reconciled';

comment on column account_move_line.create_date is 'Created on';

comment on column account_move_line.write_date is 'Last Updated on';

comment on column account_move_line.is_downpayment is 'Is Downpayment';

comment on column account_move_line.cogs_origin_id is 'Cogs Origin';

comment on constraint account_move_line_check_credit_debit on account_move_line is 'CHECK(display_type IN (''line_section'', ''line_note'') OR credit * debit=0)';

comment on constraint account_move_line_check_amount_currency_balance_sign on account_move_line is 'CHECK(
                display_type IN (''line_section'', ''line_note'')
                OR (
                    (balance <= 0 AND amount_currency <= 0)
                    OR
                    (balance >= 0 AND amount_currency >= 0)
                )
            )';

comment on constraint account_move_line_check_accountable_required_fields on account_move_line is 'CHECK(display_type IN (''line_section'', ''line_note'') OR account_id IS NOT NULL)';

comment on constraint account_move_line_check_non_accountable_fields_null on account_move_line is 'CHECK(display_type NOT IN (''line_section'', ''line_note'') OR (amount_currency = 0 AND debit = 0 AND credit = 0 AND account_id IS NULL))';

create index account_move_line_partner_id_ref_idx
    on account_move_line (partner_id, ref);

create index account_move_line_date_name_id_idx
    on account_move_line (date desc, move_name desc, id asc);

create index account_move_line__unreconciled_index
    on account_move_line (account_id, partner_id)
    where (((reconciled IS NULL) OR (reconciled = false) OR (reconciled IS NOT TRUE)) AND
           ((parent_state)::text = 'posted'::text));

create index account_move_line_journal_id_neg_amnt_residual_idx
    on account_move_line (journal_id)
    where ((amount_residual < (0)::numeric) AND ((parent_state)::text = 'posted'::text));

create index account_move_line_account_id_date_idx
    on account_move_line (account_id, date);

create index account_move_line__move_id_index
    on account_move_line (move_id);

create index account_move_line__journal_id_index
    on account_move_line (journal_id);

create index account_move_line__company_id_index
    on account_move_line (company_id);

create index account_move_line__move_name_index
    on account_move_line (move_name);

create index account_move_line__payment_id_index
    on account_move_line (payment_id)
    where (payment_id IS NOT NULL);

create index account_move_line__statement_line_id_index
    on account_move_line (statement_line_id)
    where (statement_line_id IS NOT NULL);

create index account_move_line__statement_id_index
    on account_move_line (statement_id)
    where (statement_id IS NOT NULL);

create index account_move_line__group_tax_id_index
    on account_move_line (group_tax_id)
    where (group_tax_id IS NOT NULL);

create index account_move_line__full_reconcile_id_index
    on account_move_line (full_reconcile_id)
    where (full_reconcile_id IS NOT NULL);

create index account_move_line__matching_number_index
    on account_move_line (matching_number);

create index account_move_line__product_id_index
    on account_move_line (product_id);

create index account_move_line__date_maturity_index
    on account_move_line (date_maturity);

create index account_move_line__cogs_origin_id_index
    on account_move_line (cogs_origin_id)
    where (cogs_origin_id IS NOT NULL);

---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------

drop table if exists account_payment_register_exp;
create table account_payment_register_exp
(
    id                          integer,
    currency_id                 integer,
    journal_id                  integer,
    partner_bank_id             integer,
    custom_user_currency_id     integer,
    source_currency_id          integer,
    company_id                  integer,
    partner_id                  integer,
    payment_method_line_id      integer,
    writeoff_account_id         integer,
    create_uid                  integer,
    write_uid                   integer,
    communication               varchar,
    installments_mode           varchar,
    payment_type                varchar,
    partner_type                varchar,
    payment_difference_handling varchar,
    writeoff_label              varchar,
    payment_date                date,
    amount                      numeric,
    custom_user_amount          numeric,
    source_amount               numeric,
    source_amount_currency      numeric,
    group_payment               boolean,
    can_edit_wizard             boolean,
    can_group_payments          boolean,
    create_date                 timestamp,
    write_date                  timestamp,
    payment_token_id            integer,
    operation                   varchar(1),
    operation_sys               timestamp default CURRENT_TIMESTAMP,
    exported                    varchar(1)
);

drop table if exists account_payment_register;
create table account_payment_register
(
    id                          integer primary key,
    currency_id                 integer,
    journal_id                  integer,
    partner_bank_id             integer,
    custom_user_currency_id     integer,
    source_currency_id          integer,
    company_id                  integer,
    partner_id                  integer,
    payment_method_line_id      integer,
    writeoff_account_id         integer,
    create_uid                  integer,
    write_uid                   integer,
    communication               varchar,
    installments_mode           varchar,
    payment_type                varchar,
    partner_type                varchar,
    payment_difference_handling varchar,
    writeoff_label              varchar,
    payment_date                date not null,
    amount                      numeric,
    custom_user_amount          numeric,
    source_amount               numeric,
    source_amount_currency      numeric,
    group_payment               boolean,
    can_edit_wizard             boolean,
    can_group_payments          boolean,
    create_date                 timestamp,
    write_date                  timestamp,
    payment_token_id            integer
);

comment on table account_payment_register is 'Pay';

comment on column account_payment_register.currency_id is 'Currency';

comment on column account_payment_register.journal_id is 'Journal';

comment on column account_payment_register.partner_bank_id is 'Recipient Bank Account';

comment on column account_payment_register.custom_user_currency_id is 'Custom User Currency';

comment on column account_payment_register.source_currency_id is 'Source Currency';

comment on column account_payment_register.company_id is 'Company';

comment on column account_payment_register.partner_id is 'Customer/Vendor';

comment on column account_payment_register.payment_method_line_id is 'Payment Method';

comment on column account_payment_register.writeoff_account_id is 'Difference Account';

comment on column account_payment_register.create_uid is 'Created by';

comment on column account_payment_register.write_uid is 'Last Updated by';

comment on column account_payment_register.communication is 'Memo';

comment on column account_payment_register.installments_mode is 'Installments Mode';

comment on column account_payment_register.payment_type is 'Payment Type';

comment on column account_payment_register.partner_type is 'Partner Type';

comment on column account_payment_register.payment_difference_handling is 'Payment Difference Handling';

comment on column account_payment_register.writeoff_label is 'Journal Item Label';

comment on column account_payment_register.payment_date is 'Payment Date';

comment on column account_payment_register.amount is 'Amount';

comment on column account_payment_register.custom_user_amount is 'Custom User Amount';

comment on column account_payment_register.source_amount is 'Amount to Pay (company currency)';

comment on column account_payment_register.source_amount_currency is 'Amount to Pay (foreign currency)';

comment on column account_payment_register.group_payment is 'Group Payments';

comment on column account_payment_register.can_edit_wizard is 'Can Edit Wizard';

comment on column account_payment_register.can_group_payments is 'Can Group Payments';

comment on column account_payment_register.create_date is 'Created on';

comment on column account_payment_register.write_date is 'Last Updated on';

comment on column account_payment_register.payment_token_id is 'Saved payment token';

---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------

drop table if exists account_payment_exp;
create table account_payment_exp
(
    id                                  integer,
    message_main_attachment_id          integer,
    move_id                             integer,
    journal_id                          integer,
    company_id                          integer,
    partner_bank_id                     integer,
    paired_internal_transfer_payment_id integer,
    payment_method_line_id              integer,
    payment_method_id                   integer,
    currency_id                         integer,
    partner_id                          integer,
    outstanding_account_id              integer,
    destination_account_id              integer,
    create_uid                          integer,
    write_uid                           integer,
    name                                varchar,
    state                               varchar,
    payment_type                        varchar,
    partner_type                        varchar,
    memo                                varchar,
    payment_reference                   varchar,
    date                                date,
    amount                              numeric,
    amount_company_currency_signed      numeric,
    is_reconciled                       boolean,
    is_matched                          boolean,
    is_sent                             boolean,
    create_date                         timestamp,
    write_date                          timestamp,
    payment_transaction_id              integer,
    payment_token_id                    integer,
    source_payment_id                   integer,
    pos_payment_method_id               integer,
    force_outstanding_account_id        integer,
    pos_session_id                      integer,
    pos_order_id                        integer,
    operation                           varchar(1),
    operation_sys                       timestamp default CURRENT_TIMESTAMP,
    exported                            varchar(1)
);

drop table if exists account_payment;
create table account_payment
(
    id                                  integer primary key,
    message_main_attachment_id          integer,
    move_id                             integer,
    journal_id                          integer not null,
    company_id                          integer not null,
    partner_bank_id                     integer,
    paired_internal_transfer_payment_id integer,
    payment_method_line_id              integer,
    payment_method_id                   integer,
    currency_id                         integer,
    partner_id                          integer,
    outstanding_account_id              integer,
    destination_account_id              integer,
    create_uid                          integer,
    write_uid                           integer,
    name                                varchar,
    state                               varchar not null,
    payment_type                        varchar not null,
    partner_type                        varchar not null,
    memo                                varchar,
    payment_reference                   varchar,
    date                                date    not null,
    amount                              numeric
        constraint account_payment_check_amount_not_negative
            check (amount >= 0.0),
    amount_company_currency_signed      numeric,
    is_reconciled                       boolean,
    is_matched                          boolean,
    is_sent                             boolean,
    create_date                         timestamp,
    write_date                          timestamp,
    payment_transaction_id              integer,
    payment_token_id                    integer,
    source_payment_id                   integer,
    pos_payment_method_id               integer,
    force_outstanding_account_id        integer,
    pos_session_id                      integer,
    pos_order_id                        integer
);

comment on table account_payment is 'Payments';

comment on column account_payment.message_main_attachment_id is 'Main Attachment';

comment on column account_payment.move_id is 'Journal Entry';

comment on column account_payment.journal_id is 'Journal';

comment on column account_payment.company_id is 'Company';

comment on column account_payment.partner_bank_id is 'Recipient Bank Account';

comment on column account_payment.paired_internal_transfer_payment_id is 'Paired Internal Transfer Payment';

comment on column account_payment.payment_method_line_id is 'Payment Method';

comment on column account_payment.payment_method_id is 'Method';

comment on column account_payment.currency_id is 'Currency';

comment on column account_payment.partner_id is 'Customer/Vendor';

comment on column account_payment.outstanding_account_id is 'Outstanding Account';

comment on column account_payment.destination_account_id is 'Destination Account';

comment on column account_payment.create_uid is 'Created by';

comment on column account_payment.write_uid is 'Last Updated by';

comment on column account_payment.name is 'Number';

comment on column account_payment.state is 'State';

comment on column account_payment.payment_type is 'Payment Type';

comment on column account_payment.partner_type is 'Partner Type';

comment on column account_payment.memo is 'Memo';

comment on column account_payment.payment_reference is 'Payment Reference';

comment on column account_payment.date is 'Date';

comment on column account_payment.amount is 'Amount';

comment on constraint account_payment_check_amount_not_negative on account_payment is 'CHECK(amount >= 0.0)';

comment on column account_payment.amount_company_currency_signed is 'Amount Company Currency Signed';

comment on column account_payment.is_reconciled is 'Is Reconciled';

comment on column account_payment.is_matched is 'Is Matched With a Bank Statement';

comment on column account_payment.is_sent is 'Is Sent';

comment on column account_payment.create_date is 'Created on';

comment on column account_payment.write_date is 'Last Updated on';

comment on column account_payment.payment_transaction_id is 'Payment Transaction';

comment on column account_payment.payment_token_id is 'Saved Payment Token';

comment on column account_payment.source_payment_id is 'Source Payment';

comment on column account_payment.pos_payment_method_id is 'POS Payment Method';

comment on column account_payment.force_outstanding_account_id is 'Forced Outstanding Account';

comment on column account_payment.pos_session_id is 'POS Session';

comment on column account_payment.pos_order_id is 'POS Order';

create index account_payment_journal_id_company_id_idx
    on account_payment (journal_id, company_id);

create index account_payment_unmatched_idx
    on account_payment (journal_id, company_id)
    where ((NOT is_matched) OR (is_matched IS NULL));

create index account_payment__message_main_attachment_id_index
    on account_payment (message_main_attachment_id)
    where (message_main_attachment_id IS NOT NULL);

create index account_payment__move_id_index
    on account_payment (move_id);

create index account_payment__paired_internal_transfer_payment_id_index
    on account_payment (paired_internal_transfer_payment_id)
    where (paired_internal_transfer_payment_id IS NOT NULL);

create index account_payment__outstanding_account_id_index
    on account_payment (outstanding_account_id)
    where (outstanding_account_id IS NOT NULL);

create index account_payment__source_payment_id_index
    on account_payment (source_payment_id)
    where (source_payment_id IS NOT NULL);

---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------

drop table if exists account_partial_reconcile_exp;
create table account_partial_reconcile_exp
(
    id                     integer,
    debit_move_id          integer,
    credit_move_id         integer,
    full_reconcile_id      integer,
    exchange_move_id       integer,
    debit_currency_id      integer,
    credit_currency_id     integer,
    company_id             integer,
    create_uid             integer,
    write_uid              integer,
    max_date               date,
    amount                 numeric,
    debit_amount_currency  numeric,
    credit_amount_currency numeric,
    create_date            timestamp,
    write_date             timestamp,
    operation              varchar(1),
    operation_sys          timestamp default CURRENT_TIMESTAMP,
    exported               varchar(1)
);

drop table if exists account_partial_reconcile;
create table account_partial_reconcile
(
    id                     integer primary key,
    debit_move_id          integer not null,
    credit_move_id         integer not null,
    full_reconcile_id      integer,
    exchange_move_id       integer,
    debit_currency_id      integer,
    credit_currency_id     integer,
    company_id             integer,
    create_uid             integer,
    write_uid              integer,
    max_date               date,
    amount                 numeric,
    debit_amount_currency  numeric,
    credit_amount_currency numeric,
    create_date            timestamp,
    write_date             timestamp
);

comment on table account_partial_reconcile is 'Partial Reconcile';

comment on column account_partial_reconcile.debit_move_id is 'Debit Move';

comment on column account_partial_reconcile.credit_move_id is 'Credit Move';

comment on column account_partial_reconcile.full_reconcile_id is 'Full Reconcile';

comment on column account_partial_reconcile.exchange_move_id is 'Exchange Move';

comment on column account_partial_reconcile.debit_currency_id is 'Currency of the debit journal item.';

comment on column account_partial_reconcile.credit_currency_id is 'Currency of the credit journal item.';

comment on column account_partial_reconcile.company_id is 'Company';

comment on column account_partial_reconcile.create_uid is 'Created by';

comment on column account_partial_reconcile.write_uid is 'Last Updated by';

comment on column account_partial_reconcile.max_date is 'Max Date of Matched Lines';

comment on column account_partial_reconcile.amount is 'Amount';

comment on column account_partial_reconcile.debit_amount_currency is 'Debit Amount Currency';

comment on column account_partial_reconcile.credit_amount_currency is 'Credit Amount Currency';

comment on column account_partial_reconcile.create_date is 'Created on';

comment on column account_partial_reconcile.write_date is 'Last Updated on';

create index account_partial_reconcile__debit_move_id_index
    on account_partial_reconcile (debit_move_id);

create index account_partial_reconcile__credit_move_id_index
    on account_partial_reconcile (credit_move_id);

create index account_partial_reconcile__full_reconcile_id_index
    on account_partial_reconcile (full_reconcile_id)
    where (full_reconcile_id IS NOT NULL);

create index account_partial_reconcile__exchange_move_id_index
    on account_partial_reconcile (exchange_move_id)
    where (exchange_move_id IS NOT NULL);

---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------

drop table if exists account_full_reconcile_exp;
create table account_full_reconcile_exp
(
    id               integer,
    exchange_move_id integer,
    create_uid       integer,
    write_uid        integer,
    create_date      timestamp,
    write_date       timestamp,
    operation        varchar(1),
    operation_sys    timestamp default CURRENT_TIMESTAMP,
    exported         varchar(1)
);

drop table if exists account_full_reconcile;
create table account_full_reconcile
(
    id               integer primary key,
    exchange_move_id integer,
    create_uid       integer,
    write_uid        integer,
    create_date      timestamp,
    write_date       timestamp
);

comment on table account_full_reconcile is 'Full Reconcile';

comment on column account_full_reconcile.exchange_move_id is 'Exchange Move';

comment on column account_full_reconcile.create_uid is 'Created by';

comment on column account_full_reconcile.write_uid is 'Last Updated by';

comment on column account_full_reconcile.create_date is 'Created on';

comment on column account_full_reconcile.write_date is 'Last Updated on';

create index account_full_reconcile__exchange_move_id_index
    on account_full_reconcile (exchange_move_id)
    where (exchange_move_id IS NOT NULL);