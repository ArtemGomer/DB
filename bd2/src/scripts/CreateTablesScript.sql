DROP TABLE Ячейки;
DROP TABLE Поставки;
DROP TABLE Поставляемые;
DROP TABLE Поставщики;
DROP TABLE Продажи;
DROP TABLE Заказы;
DROP TABLE Пошлина;
DROP TABLE Типы_товаров;

CREATE TABLE Пошлина
(
    id      INTEGER PRIMARY KEY,
    страна VARCHAR(100) NOT NULL UNIQUE,
    пошлина INTEGER      NOT NULL CHECK (пошлина > 0)
);

CREATE TABLE Поставщики
(
    id     INTEGER PRIMARY KEY,
    имя   VARCHAR(100) NOT NULL,
    пошлина_id INTEGER      NOT NULL,
    тип   VARCHAR(100) NOT NULL,
    CONSTRAINT dealers_fee_fk FOREIGN KEY (пошлина_id) REFERENCES Пошлина (id) ON DELETE CASCADE
);

CREATE TABLE Типы_товаров
(
    id   INTEGER PRIMARY KEY,
    тип VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE Поставляемые
(
    id            INTEGER PRIMARY KEY,
    поставщики_id     INTEGER NOT NULL,
    типы_товаров_id INTEGER NOT NULL,
    цена          INTEGER NOT NULL CHECK (цена > 0),
    размер_товара     INTEGER NOT NULL CHECK (размер_товара > 0),
    CONSTRAINT delivered_dealers_fk FOREIGN KEY (поставщики_id) REFERENCES Поставщики (id) ON DELETE CASCADE,
    CONSTRAINT delivered_goods_type_fk FOREIGN KEY (типы_товаров_id) REFERENCES Типы_товаров (id) ON DELETE CASCADE
);

CREATE TABLE Поставки
(
    id                 INTEGER PRIMARY KEY,
    поставляемые_id INTEGER NOT NULL,
    количество             INTEGER CHECK (количество > 0),
    дата_поставки       DATE    NOT NULL,
    брак         INTEGER CHECK (брак >= 0 AND брак <= 1),
    CONSTRAINT delivers_delivered_goods_fk FOREIGN KEY (поставляемые_id) REFERENCES Поставляемые (id) ON DELETE CASCADE
);

CREATE TABLE Заказы
(
    id         INTEGER PRIMARY KEY,
    имя       VARCHAR(100) NOT NULL,
    тип       VARCHAR(100) NOT NULL,
    исполнен         INTEGER CHECK (исполнен >= 0 AND исполнен <= 1) NOT NULL,
    количество     INTEGER CHECK (количество > 0),
    дата_заказа DATE         NOT NULL
);

CREATE TABLE Продажи
(
    id        INTEGER PRIMARY KEY,
    заказы_id  INTEGER NOT NULL,
    дата_продажи DATE    NOT NULL,
    CONSTRAINT sells_orders_fk FOREIGN KEY (заказы_id) REFERENCES Заказы (id) ON DELETE CASCADE
);

CREATE TABLE Ячейки
(
    id            INTEGER PRIMARY KEY,
    поставляемые_id INTEGER NOT NULL,
    количество        INTEGER CHECK (количество > 0),
    CONSTRAINT cell_delivered_goods_fk FOREIGN KEY (поставляемые_id) REFERENCES Поставляемые (id) ON DELETE CASCADE
);
