DROP TABLE IF EXISTS tmp_table1;
DROP TABLE IF EXISTS tmp_table2;
DROP TABLE IF EXISTS tmp_table3;

CREATE TEMP TABLE tmp_table1 (
	id int PRIMARY KEY NOT NULL,
	name varchar(20)
);

INSERT INTO tmp_table1(id, name) VALUES(1, 'one');
INSERT INTO tmp_table1(id, name) VALUES(2, 'two');
INSERT INTO tmp_table1(id, name) VALUES(3, 'three');
INSERT INTO tmp_table1(id, name) VALUES(4, 'four');

-- stworzenie tabeli na podstawie wynikow zapytania
CREATE TABLE tmp_table2 AS SELECT id i, name n FROM tmp_table1 WHERE id % 2 = 0;

-- inny sposow stworznie tabeli z wynikow (mniejsze mozliwosci niz CREATE TABLE AS)
SELECT id i, name n INTO TEMP tmp_table3 FROM tmp_table1 WHERE id % 2 = 1;
SELECT * FROM tmp_table3;
