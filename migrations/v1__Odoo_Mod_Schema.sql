/*************************
 * Autor: Alexander Machic
 * DB: Odoo
 * Uso: Tablas de exportación de base de datos
 *********************** */

/*************************
 * PRODUCT_CATEGORY_EXP
 * PRODUCT_TEMPLATE_EXP
 * PRODUCT_PRODUCT_EXP
 *********************** */

------------------------------------------
-- PRODUCT_CATEGORY_EXP
------------------------------------------
select * from product_category;
select * from product_category_exp;
create table product_category_exp as table product_category with no data;

alter table product_category_exp add column operation varchar(1);
alter table product_category_exp add column operation_sys timestamp default CURRENT_TIMESTAMP;
alter table product_category_exp add column exported varchar(1);

CREATE OR REPLACE FUNCTION f_producto_category_export()
RETURNS TRIGGER AS $$
DECLARE
    export product_category_exp%ROWTYPE;
BEGIN
    -- Copia la fila insertada o actualizada
    export := json_populate_record(NULL::product_category_exp, row_to_json(NEW));
	
	-- Asignar fecha de operacion
	export.operation_sys := NOW();

    -- Asigna tipo de operación
    IF TG_OP = 'INSERT' THEN
        export.operation := 'I';
    ELSIF TG_OP = 'UPDATE' THEN
        export.operation := 'U';
    END IF;

    -- Inserta en la tabla de exportación
    INSERT INTO product_category_exp VALUES (export.*);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trg_exp_product_category
AFTER insert or UPDATE ON product_category
FOR EACH ROW
EXECUTE FUNCTION f_producto_category_export();

------------------------------------------
-- PRODUCT_TEMPLATE_EXP
------------------------------------------
select * from product_template;
create table product_template_exp as table product_template with no data;
alter table product_template_exp add column operation varchar(1);
alter table product_template_exp add column operation_sys timestamp default CURRENT_TIMESTAMP;
alter table product_template_exp add column exported varchar(1);

CREATE OR REPLACE FUNCTION f_product_template_export()
RETURNS TRIGGER AS $$
DECLARE
    export product_template_exp%ROWTYPE;
BEGIN
    -- Copia la fila insertada o actualizada
    export := json_populate_record(NULL::product_template_exp, row_to_json(NEW));
	
	-- Asignar fecha de operacion
	export.operation_sys := NOW();

    -- Asigna tipo de operación
    IF TG_OP = 'INSERT' THEN
        export.operation := 'I';
    ELSIF TG_OP = 'UPDATE' THEN
        export.operation := 'U';
    END IF;

    -- Inserta en la tabla de exportación
    INSERT INTO product_template_exp VALUES (export.*);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_exp_product_template
AFTER insert or UPDATE ON product_template
FOR EACH ROW
EXECUTE FUNCTION f_product_template_export();

------------------------------------------
-- PRODUCT_TAXES_REL (OMITIDA)
------------------------------------------
select * from product_taxes_rel

------------------------------------------
-- PRODUCT_SUPPLIER_TAXES_REL (OMITIDA)
------------------------------------------
select * from product_supplier_taxes_rel

------------------------------------------
-- PRODUCT_PRODUCT_EXP
------------------------------------------
create table product_product_exp as table product_product with no data;
alter table product_product_exp add column operation varchar(1);
alter table product_product_exp add column operation_sys timestamp default CURRENT_TIMESTAMP;
alter table product_product_exp add column exported varchar(1);

CREATE OR REPLACE FUNCTION f_product_product_export()
RETURNS TRIGGER AS $$
DECLARE
    export product_product_exp%ROWTYPE;
BEGIN
    -- Copia la fila insertada o actualizada
    export := json_populate_record(NULL::product_product_exp, row_to_json(NEW));
	
	-- Asignar fecha de operacion
	export.operation_sys := NOW();

    -- Asigna tipo de operación
    IF TG_OP = 'INSERT' THEN
        export.operation := 'I';
    ELSIF TG_OP = 'UPDATE' THEN
        export.operation := 'U';
    END IF;

    -- Inserta en la tabla de exportación
    INSERT INTO product_product_exp VALUES (export.*);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_exp_product_product
AFTER insert or UPDATE ON product_product
FOR EACH ROW
EXECUTE FUNCTION f_product_product_export();

------------------------------------------
-- RES_PARTNER_EXP
------------------------------------------
create table res_partner_exp as table res_partner with no data;
alter table res_partner_exp add column operation varchar(1);
alter table res_partner_exp add column operation_sys timestamp default CURRENT_TIMESTAMP;
alter table res_partner_exp add column exported varchar(1);

CREATE OR REPLACE FUNCTION f_res_partner_export()
RETURNS TRIGGER AS $$
DECLARE
    export res_partner_exp%ROWTYPE;
BEGIN
    -- Copia la fila insertada o actualizada
    export := json_populate_record(NULL::res_partner_exp, row_to_json(NEW));
	
	-- Asignar fecha de operacion
	export.operation_sys := NOW();

    -- Asigna tipo de operación
    IF TG_OP = 'INSERT' THEN
        export.operation := 'I';
    ELSIF TG_OP = 'UPDATE' THEN
        export.operation := 'U';
    END IF;

    -- Inserta en la tabla de exportación
    INSERT INTO res_partner_exp VALUES (export.*);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_exp_res_partner
AFTER insert or UPDATE ON res_partner
FOR EACH ROW
EXECUTE FUNCTION f_res_partner_export();

------------------------------------------
-- SALE_ORDER_EXP
------------------------------------------
create table sale_order_exp as table sale_order with no data;
alter table sale_order_exp add column operation varchar(1);
alter table sale_order_exp add column operation_sys timestamp default CURRENT_TIMESTAMP;
alter table sale_order_exp add column exported varchar(1);

CREATE OR REPLACE FUNCTION f_sale_order_export()
RETURNS TRIGGER AS $$
DECLARE
    export sale_order_exp%ROWTYPE;
BEGIN
    -- Copia la fila insertada o actualizada
    export := json_populate_record(NULL::sale_order_exp, row_to_json(NEW));
	
	-- Asignar fecha de operacion
	export.operation_sys := NOW();

    -- Asigna tipo de operación
    IF TG_OP = 'INSERT' THEN
        export.operation := 'I';
    ELSIF TG_OP = 'UPDATE' THEN
        export.operation := 'U';
    END IF;

    -- Inserta en la tabla de exportación
    INSERT INTO sale_order_exp VALUES (export.*);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_exp_sale_order
AFTER insert or UPDATE ON sale_order
FOR EACH ROW
EXECUTE FUNCTION f_sale_order_export();

------------------------------------------
-- SALE_ORDER_LINE_EXP
------------------------------------------
create table sale_order_line_exp as table sale_order_line with no data;
alter table sale_order_line_exp add column operation varchar(1);
alter table sale_order_line_exp add column operation_sys timestamp default CURRENT_TIMESTAMP;
alter table sale_order_line_exp add column exported varchar(1);

CREATE OR REPLACE FUNCTION f_sale_order_line_export()
RETURNS TRIGGER AS $$
DECLARE
    export sale_order_line_exp%ROWTYPE;
BEGIN
    -- Copia la fila insertada o actualizada
	export := json_populate_record(NULL::sale_order_line_exp, row_to_json(NEW));

	if TG_OP = 'DELETE' then
		export := json_populate_record(NULL::sale_order_line_exp, row_to_json(OLD));
	end if;
    
	
	-- Asignar fecha de operacion
	export.operation_sys := NOW();

    -- Asigna tipo de operación
    IF TG_OP = 'INSERT' THEN
        export.operation := 'I';
    ELSIF TG_OP = 'UPDATE' THEN
        export.operation := 'U';
	ELSIF TG_OP = 'DELETE' THEN
		export.operation := 'D';
		INSERT INTO sale_order_line_exp VALUES (export.*);
		RETURN OLD;
    END IF;

    -- Inserta en la tabla de exportación
    INSERT INTO sale_order_line_exp VALUES (export.*);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trg_exp_sale_order_line
before insert or update or delete ON sale_order_line
FOR EACH ROW
EXECUTE FUNCTION f_sale_order_line_export();

------------------------------------------
-- SALE_ADVANCE_PAYMENT_INV_EXP
------------------------------------------
create table sale_advance_payment_inv_exp as table sale_advance_payment_inv with no data;
alter table sale_advance_payment_inv_exp add column operation varchar(1);
alter table sale_advance_payment_inv_exp add column operation_sys timestamp default CURRENT_TIMESTAMP;
alter table sale_advance_payment_inv_exp add column exported varchar(1);

CREATE OR REPLACE FUNCTION f_sale_advance_payment_inv_export()
RETURNS TRIGGER AS $$
DECLARE
    export sale_advance_payment_inv_exp%ROWTYPE;
BEGIN
    -- Copia la fila insertada o actualizada
	export := json_populate_record(NULL::sale_advance_payment_inv_exp, row_to_json(NEW));

	if TG_OP = 'DELETE' then
		export := json_populate_record(NULL::sale_advance_payment_inv_exp, row_to_json(OLD));
	end if;
    
	
	-- Asignar fecha de operacion
	export.operation_sys := NOW();

    -- Asigna tipo de operación
    IF TG_OP = 'INSERT' THEN
        export.operation := 'I';
    ELSIF TG_OP = 'UPDATE' THEN
        export.operation := 'U';
	ELSIF TG_OP = 'DELETE' THEN
		export.operation := 'D';
		INSERT INTO sale_advance_payment_inv_exp VALUES (export.*);
		RETURN OLD;
    END IF;

    -- Inserta en la tabla de exportación
    INSERT INTO sale_advance_payment_inv_exp VALUES (export.*);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trg_exp_sale_advance_payment_inv
before insert or update or delete ON sale_advance_payment_inv
FOR EACH ROW
EXECUTE FUNCTION f_sale_advance_payment_inv_export();

------------------------------------------
-- ACCOUNT_MOVE_EXP
------------------------------------------
create table account_move_exp as table account_move with no data;
alter table account_move_exp add column operation varchar(1);
alter table account_move_exp add column operation_sys timestamp default CURRENT_TIMESTAMP;
alter table account_move_exp add column exported varchar(1);

CREATE OR REPLACE FUNCTION f_account_move_export()
RETURNS TRIGGER AS $$
DECLARE
    export account_move_exp%ROWTYPE;
BEGIN
    -- Copia la fila insertada o actualizada
	export := json_populate_record(NULL::account_move_exp, row_to_json(NEW));

	if TG_OP = 'DELETE' then
		export := json_populate_record(NULL::account_move_exp, row_to_json(OLD));
	end if;
    
	-- Asignar fecha de operacion
	export.operation_sys := NOW();

    -- Asigna tipo de operación
    IF TG_OP = 'INSERT' THEN
        export.operation := 'I';
    ELSIF TG_OP = 'UPDATE' THEN
        export.operation := 'U';
	ELSIF TG_OP = 'DELETE' THEN
		export.operation := 'D';
		INSERT INTO account_move_exp VALUES (export.*);
		RETURN OLD;
    END IF;

    -- Inserta en la tabla de exportación
    INSERT INTO account_move_exp VALUES (export.*);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_exp_account_move
before insert or update or delete ON account_move
FOR EACH ROW
EXECUTE FUNCTION f_account_move_export();


------------------------------------------
-- ACCOUNT_MOVE_LINE_EXP
------------------------------------------
create table account_move_line_exp as table account_move_line with no data;
alter table account_move_line_exp add column operation varchar(1);
alter table account_move_line_exp add column operation_sys timestamp default CURRENT_TIMESTAMP;
alter table account_move_line_exp add column exported varchar(1);

CREATE OR REPLACE FUNCTION f_account_move_line_export()
RETURNS TRIGGER AS $$
DECLARE
    export account_move_line_exp%ROWTYPE;
BEGIN
    -- Copia la fila insertada o actualizada
	export := json_populate_record(NULL::account_move_line_exp, row_to_json(NEW));

	if TG_OP = 'DELETE' then
		export := json_populate_record(NULL::account_move_line_exp, row_to_json(OLD));
	end if;
    
	-- Asignar fecha de operacion
	export.operation_sys := NOW();

    -- Asigna tipo de operación
    IF TG_OP = 'INSERT' THEN
        export.operation := 'I';
    ELSIF TG_OP = 'UPDATE' THEN
        export.operation := 'U';
	ELSIF TG_OP = 'DELETE' THEN
		export.operation := 'D';
		INSERT INTO account_move_line_exp VALUES (export.*);
		RETURN OLD;
    END IF;

    -- Inserta en la tabla de exportación
    INSERT INTO account_move_line_exp VALUES (export.*);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_exp_account_move_line
before insert or update or delete ON account_move_line
FOR EACH ROW
EXECUTE FUNCTION f_account_move_line_export();

------------------------------------------
-- ACCOUNT_PAYMENT_REGISTER_EXP
------------------------------------------
create table account_payment_register_exp as table account_payment_register with no data;
alter table account_payment_register_exp add column operation varchar(1);
alter table account_payment_register_exp add column operation_sys timestamp default CURRENT_TIMESTAMP;
alter table account_payment_register_exp add column exported varchar(1);

CREATE OR REPLACE FUNCTION f_account_payment_register_export()
RETURNS TRIGGER AS $$
DECLARE
    export account_payment_register_exp%ROWTYPE;
BEGIN
    -- Copia la fila insertada o actualizada
	export := json_populate_record(NULL::account_payment_register_exp, row_to_json(NEW));

	if TG_OP = 'DELETE' then
		export := json_populate_record(NULL::account_payment_register_exp, row_to_json(OLD));
	end if;
    
	-- Asignar fecha de operacion
	export.operation_sys := NOW();

    -- Asigna tipo de operación
    IF TG_OP = 'INSERT' THEN
        export.operation := 'I';
    ELSIF TG_OP = 'UPDATE' THEN
        export.operation := 'U';
	ELSIF TG_OP = 'DELETE' THEN
		export.operation := 'D';
		INSERT INTO account_payment_register_exp VALUES (export.*);
		RETURN OLD;
    END IF;

    -- Inserta en la tabla de exportación
    INSERT INTO account_payment_register_exp VALUES (export.*);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_exp_account_payment_register
before insert or update or delete ON account_payment_register
FOR EACH ROW
EXECUTE FUNCTION f_account_payment_register_export();

------------------------------------------
-- ACCOUNT_PAYMENT_EXP
------------------------------------------
create table account_payment_exp as table ACCOUNT_PAYMENT with no data;
alter table account_payment_exp add column operation varchar(1);
alter table account_payment_exp add column operation_sys timestamp default CURRENT_TIMESTAMP;
alter table account_payment_exp add column exported varchar(1);

CREATE OR REPLACE FUNCTION f_account_payment_export()
RETURNS TRIGGER AS $$
DECLARE
    export account_payment_exp%ROWTYPE;
BEGIN
    -- Copia la fila insertada o actualizada
	export := json_populate_record(NULL::account_payment_exp, row_to_json(NEW));

	if TG_OP = 'DELETE' then
		export := json_populate_record(NULL::account_payment_exp, row_to_json(OLD));
	end if;
    
	-- Asignar fecha de operacion
	export.operation_sys := NOW();

    -- Asigna tipo de operación
    IF TG_OP = 'INSERT' THEN
        export.operation := 'I';
    ELSIF TG_OP = 'UPDATE' THEN
        export.operation := 'U';
	ELSIF TG_OP = 'DELETE' THEN
		export.operation := 'D';
		INSERT INTO account_payment_exp VALUES (export.*);
		RETURN OLD;
    END IF;

    -- Inserta en la tabla de exportación
    INSERT INTO account_payment_exp VALUES (export.*);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_exp_account_payment
before insert or update or delete ON account_payment
FOR EACH ROW
EXECUTE FUNCTION f_account_payment_export();

------------------------------------------
-- ACCOUNT_PARTIAL_RECONCILE_EXP
------------------------------------------
create table account_partial_reconcile_exp as table ACCOUNT_PARTIAL_RECONCILE with no data;
alter table account_partial_reconcile_exp add column operation varchar(1);
alter table account_partial_reconcile_exp add column operation_sys timestamp default CURRENT_TIMESTAMP;
alter table account_partial_reconcile_exp add column exported varchar(1);

CREATE OR REPLACE FUNCTION f_account_partial_reconcile_export()
RETURNS TRIGGER AS $$
DECLARE
    export account_partial_reconcile_exp%ROWTYPE;
BEGIN
    -- Copia la fila insertada o actualizada
	export := json_populate_record(NULL::account_partial_reconcile_exp, row_to_json(NEW));

	if TG_OP = 'DELETE' then
		export := json_populate_record(NULL::account_partial_reconcile_exp, row_to_json(OLD));
	end if;
    
	-- Asignar fecha de operacion
	export.operation_sys := NOW();

    -- Asigna tipo de operación
    IF TG_OP = 'INSERT' THEN
        export.operation := 'I';
    ELSIF TG_OP = 'UPDATE' THEN
        export.operation := 'U';
	ELSIF TG_OP = 'DELETE' THEN
		export.operation := 'D';
		INSERT INTO account_partial_reconcile_exp VALUES (export.*);
		RETURN OLD;
    END IF;

    -- Inserta en la tabla de exportación
    INSERT INTO account_partial_reconcile_exp VALUES (export.*);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_exp_account_partial_reconcile
before insert or update or delete ON account_partial_reconcile
FOR EACH ROW
EXECUTE FUNCTION f_account_partial_reconcile_export();

------------------------------------------
-- ACCOUNT_FULL_RECONCILE_EXP
------------------------------------------
create table account_full_reconcile_exp as table account_full_reconcile with no data;
alter table account_full_reconcile_exp add column operation varchar(1);
alter table account_full_reconcile_exp add column operation_sys timestamp default current_timestamp;
alter table account_full_reconcile_exp add column exported varchar(1);

CREATE OR REPLACE FUNCTION f_account_full_reconcile_export()
RETURNS TRIGGER AS $$
DECLARE
    export account_full_reconcile_exp%ROWTYPE;
BEGIN
    -- Copia la fila insertada o actualizada
	export := json_populate_record(NULL::account_full_reconcile_exp, row_to_json(NEW));

	if TG_OP = 'DELETE' then
		export := json_populate_record(NULL::account_full_reconcile_exp, row_to_json(OLD));
	end if;
    
	-- Asignar fecha de operacion
	export.operation_sys := NOW();

    -- Asigna tipo de operación
    IF TG_OP = 'INSERT' THEN
        export.operation := 'I';
    ELSIF TG_OP = 'UPDATE' THEN
        export.operation := 'U';
	ELSIF TG_OP = 'DELETE' THEN
		export.operation := 'D';
		INSERT INTO account_full_reconcile_exp VALUES (export.*);
		RETURN OLD;
    END IF;

    -- Inserta en la tabla de exportación
    INSERT INTO account_full_reconcile_exp VALUES (export.*);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_exp_account_full_reconcile
before insert or update or delete ON account_full_reconcile
FOR EACH ROW
EXECUTE FUNCTION f_account_full_reconcile_export();