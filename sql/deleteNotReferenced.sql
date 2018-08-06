DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS payment;

CREATE TABLE payment (
	id int PRIMARY KEY NOT NULL,
	amount int
);

CREATE TABLE person (
	id int PRIMARY KEY NOT NULL,
	name text NOT NULL,
	payment_id int REFERENCES payment(id) -- utworzenie klucza obcego do tabeli payment (kolumna id)
);


INSERT INTO payment(id, amount) VALUES(1, 200);
INSERT INTO payment(id, amount) VALUES(2, 500);
INSERT INTO payment(id, amount) VALUES(3, 750);
INSERT INTO payment(id, amount) VALUES(4, 820);

INSERT INTO person(id, name, payment_id) VALUES(1, 'Jan Kowalski', 2);
INSERT INTO person(id, name, payment_id) VALUES(2, 'Kazimierz Nowak', 2);
INSERT INTO person(id, name, payment_id) VALUES(3, 'Zdzislaw Borkowski', 3);
INSERT INTO person(id, name, payment_id) VALUES(4, 'Alojzy Babel', 3);
INSERT INTO person(id, name, payment_id) VALUES(5, 'Krzysztof Krzysztofski', 1);
INSERT INTO person(id, name, payment_id) VALUES(6, 'Eugeniusz Geniusz', 4);
INSERT INTO person(id, name, payment_id) VALUES(7, 'Zenon Zenowski', 4);


-- wylistowanie payment, ktore nie maja referencji od osob o id 1, 2, 3 i 5.
SELECT DISTINCT payment_id FROM person WHERE id IN (1, 2, 3, 5)
EXCEPT
SELECT payment_id FROM person WHERE id NOT IN (1, 2, 3, 5)
ORDER BY payment_id;


-- Usuniecie osob 1, 2, 3 i 5 oraz przypisanych im platnosci gdy nie ma do nich innych referencji.
-- W tabeli payment powinny zostac tylko wiersze o identyfikatorach: 3, 4

DROP TABLE IF EXISTS payment_to_delete;
CREATE TEMPORARY TABLE payment_to_delete AS (SELECT DISTINCT payment_id FROM person WHERE id IN (1, 2, 3, 5));

DELETE FROM person WHERE id IN (1, 2, 3, 5);
DELETE FROM payment WHERE 
	id IN(SELECT payment_id FROM payment_to_delete)
	AND 
	id NOT IN (SELECT payment_id FROM person)
