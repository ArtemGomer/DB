BEGIN
    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER tr_incr_fee BEFORE INSERT ON FEE FOR EACH ROW
    BEGIN
        SELECT sq_fee.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END;';
    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER tr_incr_goods_type BEFORE INSERT ON GOODS_TYPE FOR EACH ROW
    BEGIN
        SELECT sq_goods_type.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END;';
    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER tr_incr_delivered_goods BEFORE INSERT ON DELIVERED_GOODS FOR EACH ROW
    BEGIN
        SELECT sq_delivered_goods.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END;';
    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER tr_incr_dealers BEFORE INSERT ON DEALERS FOR EACH ROW
    BEGIN
        SELECT sq_dealers.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END;';
    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER tr_incr_delivers BEFORE INSERT ON DELIVERS FOR EACH ROW
    BEGIN
        SELECT sq_delivers.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END;';
    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER tr_incr_orders BEFORE INSERT ON ORDERS FOR EACH ROW
    BEGIN
        SELECT sq_orders.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END;';
    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER tr_incr_cells BEFORE INSERT ON CELLS FOR EACH ROW
        BEGIN
            SELECT sq_cells.NEXTVAL
            INTO :NEW.id
            FROM dual;
        END;';
    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER tr_incr_sells BEFORE INSERT ON SELLS FOR EACH ROW
    BEGIN
        SELECT sq_sells.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END;';

    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER tr_add_goods_type BEFORE INSERT ON ORDERS FOR EACH ROW
        DECLARE
            cnt INTEGER;
        BEGIN
            SELECT COUNT(*) INTO cnt FROM GOODS_TYPE WHERE type = :NEW.type;
            IF cnt = 0 THEN
                INSERT INTO GOODS_TYPE(type) VALUES(:NEW.type);
            END IF;
        END;';

    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER tr_add_cells_from_delivers AFTER INSERT ON DELIVERS FOR EACH ROW
        DECLARE
         good_size_new INTEGER;
         deliver_size_new INTEGER;
         good_amount_new INTEGER;
        BEGIN
         IF :NEW.defective = 0 THEN
             SELECT good_size into good_size_new FROM Delivered_goods WHERE id = :NEW.delivered_goods_id;
             good_amount_new := TRUNC(20 / good_size_new);
             deliver_size_new := good_size_new * :NEW.amount;
             WHILE deliver_size_new > 0
             LOOP
                 IF deliver_size_new >= good_amount_new * good_size_new THEN
                     INSERT INTO CELLS(delivered_goods_id, amount) VALUES(:NEW.delivered_goods_id, good_amount_new);
                     deliver_size_new := deliver_size_new - good_amount_new * good_size_new;
                 ELSE
                     INSERT INTO CELLS(delivered_goods_id, amount) VALUES(:NEW.delivered_goods_id, deliver_size_new / good_size_new);
                     deliver_size_new := deliver_size_new - good_amount_new * good_size_new;
                 END IF;
             END LOOP;
         END IF;
        END;';
END;
