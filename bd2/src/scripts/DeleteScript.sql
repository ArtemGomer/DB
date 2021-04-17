DROP SEQUENCE sq_fee;
DROP SEQUENCE sq_dealers;
DROP SEQUENCE sq_delivered_goods;
DROP SEQUENCE sq_goods_type;
DROP SEQUENCE sq_delivers;
DROP SEQUENCE sq_orders;
DROP SEQUENCE sq_cells;
DROP SEQUENCE sq_sells;

DROP TRIGGER tr_incr_fee;
DROP TRIGGER tr_incr_goods_type;
DROP TRIGGER tr_incr_delivered_goods;
DROP TRIGGER tr_incr_dealers;
DROP TRIGGER tr_incr_delivers;
DROP TRIGGER tr_incr_orders;
DROP TRIGGER tr_incr_cells;
DROP TRIGGER tr_incr_sells;
DROP TRIGGER tr_add_goods_type;
DROP TRIGGER tr_add_cells_from_delivers;

DROP TABLE Ячейки;
DROP TABLE Поставки;
DROP TABLE Поставляемые_товары;
DROP TABLE Поставщики;
DROP TABLE Продажи;
DROP TABLE Заказы;
DROP TABLE Пошлина;
DROP TABLE Типы_товаров;