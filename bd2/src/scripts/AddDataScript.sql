BEGIN
    INSERT INTO Fee(COUNTRY, PERCENT) VALUES ('Germany', 5);
    INSERT INTO Fee(COUNTRY, PERCENT) VALUES ('France', 6);
    INSERT INTO Fee(COUNTRY, PERCENT) VALUES ('Poland', 7);
    INSERT INTO Fee(COUNTRY, PERCENT) VALUES ('UK', 4);
    INSERT INTO Fee(COUNTRY, PERCENT) VALUES ('USA', 2);

    INSERT INTO Dealers(name, fee_id, type) VALUES ('dealer1', 1, 'dealer');
    INSERT INTO Dealers(name, fee_id, type) VALUES ('dealer2', 2, 'shop');
    INSERT INTO Dealers(name, fee_id, type) VALUES ('dealer3', 3, 'factory');
    INSERT INTO Dealers(name, fee_id, type) VALUES ('dealer4', 4, 'dealer');
    INSERT INTO Dealers(name, fee_id, type) VALUES ('dealer5', 5, 'shop');

    INSERT INTO Goods_type(type) VALUES ('bolt');
    INSERT INTO Goods_type(type) VALUES ('vint');
    INSERT INTO Goods_type(type) VALUES ('hammer');
    INSERT INTO Goods_type(type) VALUES ('nail');
    INSERT INTO Goods_type(type) VALUES ('drill');

    INSERT INTO Delivered_goods(dealer_id, goods_type_id, cost, good_size) VALUES (1, 5, 200, 2);
    INSERT INTO Delivered_goods(dealer_id, goods_type_id, cost, good_size) VALUES (2, 4, 100, 7);
    INSERT INTO Delivered_goods(dealer_id, goods_type_id, cost, good_size) VALUES (3, 3, 150, 3);
    INSERT INTO Delivered_goods(dealer_id, goods_type_id, cost, good_size) VALUES (4, 2, 80, 7);
    INSERT INTO Delivered_goods(dealer_id, goods_type_id, cost, good_size) VALUES (5, 1, 120, 4);

    INSERT INTO Delivers(delivered_goods_id, amount, deliver_date, defective)
    VALUES (1, 4, TO_DATE('072219', 'MMDDYY'), 0);
    INSERT INTO Delivers(delivered_goods_id, amount, deliver_date, defective)
    VALUES (2, 2, TO_DATE('072219', 'MMDDYY'), 0);
    INSERT INTO Delivers(delivered_goods_id, amount, deliver_date, defective)
    VALUES (3, 1, TO_DATE('072219', 'MMDDYY'), 0);
    INSERT INTO Delivers(delivered_goods_id, amount, deliver_date, defective)
    VALUES (4, 5, TO_DATE('072219', 'MMDDYY'), 0);
    INSERT INTO Delivers(delivered_goods_id, amount, deliver_date, defective)
    VALUES (5, 10, TO_DATE('072219', 'MMDDYY'), 0);

    INSERT INTO Orders(name, type, amount, order_date) VALUES ('vasiliy', 'bolt', 5, TO_DATE('072219', 'MMDDYY'));
    INSERT INTO Orders(name, type, amount, order_date) VALUES ('petr', 'vint', 4, TO_DATE('072219', 'MMDDYY'));
    INSERT INTO Orders(name, type, amount, order_date) VALUES ('vania', 'hammer', 3, TO_DATE('072219', 'MMDDYY'));
    INSERT INTO Orders(name, type, amount, order_date) VALUES ('artem', 'nail', 2, TO_DATE('072219', 'MMDDYY'));
    INSERT INTO Orders(name, type, amount, order_date) VALUES ('maxim', 'drill', 7, TO_DATE('072219', 'MMDDYY'));

    INSERT INTO SELLS(order_id, sell_date) VALUES (1, TO_DATE('072619', 'MMDDYY'));
    INSERT INTO SELLS(order_id, sell_date) VALUES (2, TO_DATE('082619', 'MMDDYY'));
    INSERT INTO SELLS(order_id, sell_date) VALUES (3, TO_DATE('091619', 'MMDDYY'));
END;