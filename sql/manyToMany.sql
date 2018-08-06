DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS shop_order;
DROP TABLE IF EXISTS item;

CREATE TABLE shop_order (
	id int PRIMARY KEY NOT NULL,
	name varchar(20)
);

CREATE TABLE item (
	id int PRIMARY KEY NOT NULL,
	name varchar(20)
);

CREATE TABLE order_item (
	order_id int REFERENCES shop_order(id),
	item_id int REFERENCES item(id),
	amount int
);

INSERT INTO item(id, name) VALUES(1, 'pralka');
INSERT INTO item(id, name) VALUES(2, 'lodowka');
INSERT INTO item(id, name) VALUES(3, 'kuchenka');
INSERT INTO item(id, name) VALUES(4, 'mikrofalowka');

INSERT INTO shop_order(id, name) VALUES(1, 'Duze zakupy');
INSERT INTO shop_order(id, name) VALUES(2, 'Wyposazenie kuchni');
INSERT INTO shop_order(id, name) VALUES(3, 'Moje zakupy');
INSERT INTO shop_order(id, name) VALUES(4, 'Do domu');

INSERT INTO order_item(order_id, item_id, amount) VALUES(1, 1, 3);
INSERT INTO order_item(order_id, item_id, amount) VALUES(1, 2, 1);
INSERT INTO order_item(order_id, item_id, amount) VALUES(2, 3, 10);
INSERT INTO order_item(order_id, item_id, amount) VALUES(4, 1, 2);
INSERT INTO order_item(order_id, item_id, amount) VALUES(4, 2, 5);
INSERT INTO order_item(order_id, item_id, amount) VALUES(4, 3, 4);

-- polaczenie order i item
SELECT * FROM order_item oi
INNER JOIN shop_order so ON so.id=oi.order_id
INNER JOIN item i ON i.id=oi.item_id;