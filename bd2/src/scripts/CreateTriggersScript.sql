BEGIN
    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER tr_incr_fee BEFORE INSERT ON Пошлина FOR EACH ROW
    BEGIN
        SELECT sq_fee.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END;';
    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER tr_incr_goods_type BEFORE INSERT ON Типы_товаров FOR EACH ROW
    BEGIN
        SELECT sq_goods_type.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END;';
    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER tr_incr_delivered_goods BEFORE INSERT ON Поставляемые FOR EACH ROW
    BEGIN
        SELECT sq_delivered_goods.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END;';
    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER tr_incr_dealers BEFORE INSERT ON Поставщики FOR EACH ROW
    BEGIN
        SELECT sq_dealers.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END;';
    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER tr_incr_delivers BEFORE INSERT ON Поставки FOR EACH ROW
    BEGIN
        SELECT sq_delivers.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END;';
    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER tr_incr_orders BEFORE INSERT ON Заказы FOR EACH ROW
    BEGIN
        SELECT sq_orders.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END;';
    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER tr_incr_cells BEFORE INSERT ON Ячейки FOR EACH ROW
        BEGIN
            SELECT sq_cells.NEXTVAL
            INTO :NEW.id
            FROM dual;
        END;';
    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER tr_incr_sells BEFORE INSERT ON Продажи FOR EACH ROW
    BEGIN
        SELECT sq_sells.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END;';

    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER tr_add_goods_type BEFORE INSERT ON Заказы FOR EACH ROW
        DECLARE
            cnt INTEGER;
        BEGIN
            SELECT COUNT(*) INTO cnt FROM Типы_товаров WHERE тип = :NEW.тип;
            IF cnt = 0 THEN
                INSERT INTO Типы_товаров(тип) VALUES(:NEW.тип);
            END IF;
        END;';

    EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER tr_add_cells_from_delivers AFTER INSERT ON Поставки FOR EACH ROW
        DECLARE
         good_size_new INTEGER;
         deliver_size_new INTEGER;
         good_amount_new INTEGER;
        BEGIN
         IF :NEW.брак = 0 THEN
             SELECT размер_товара into good_size_new FROM Поставляемые WHERE id = :NEW.поставляемые_id;
             good_amount_new := TRUNC(20 / good_size_new);
             deliver_size_new := good_size_new * :NEW.количество;
             WHILE deliver_size_new > 0
             LOOP
                 IF deliver_size_new >= good_amount_new * good_size_new THEN
                     INSERT INTO Ячейки(поставляемые_id, количество) VALUES(:NEW.поставляемые_id, good_amount_new);
                     deliver_size_new := deliver_size_new - good_amount_new * good_size_new;
                 ELSE
                     INSERT INTO Ячейки(поставляемые_id, количество) VALUES(:NEW.поставляемые_id, deliver_size_new / good_size_new);
                     deliver_size_new := deliver_size_new - good_amount_new * good_size_new;
                 END IF;
             END LOOP;
         END IF;
        END;';
END;
