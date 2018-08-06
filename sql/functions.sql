DROP TABLE IF EXISTS testtable CASCADE;
CREATE TABLE testtable(id integer PRIMARY KEY, test text);
INSERT INTO testtable(id,test) VALUES (1, 'Small Potato'), (2, 'Potato'), (3, 'Cheese'), (4, 'Cheese Dog');


-- deklarowanie parametrow wejsciowych
CREATE OR REPLACE FUNCTION min_value(a integer, b integer) RETURNS integer AS $$
DECLARE
	current_date timestamp := now(); -- obliczane dopiero podczas wykonywania funkcji
BEGIN
	/* to jest komentarz */
	RAISE NOTICE '%: min of % and %', current_date, a, b;
	IF a < b THEN
		RETURN a;
	ELSE
		RETURN b;
	END IF;
END;
$$ LANGUAGE plpgsql;


-- deklarowanie parametrow wejsciowych w bloku DECLARE
CREATE OR REPLACE FUNCTION max_value(integer, integer, OUT x integer) AS $$
DECLARE
	a ALIAS FOR $1;
	b ALIAS FOR $2;
BEGIN
	IF a > b THEN
		x := a;
	ELSE
		x := b;
	END IF;
END;
$$ LANGUAGE plpgsql;


-- zwracanie wielu wartosci
CREATE OR REPLACE FUNCTION get_pairs(OUT a integer, OUT b integer) RETURNS SETOF record AS $$
BEGIN
	FOR i IN 1..10 LOOP
		a = i;
		b = i * 10;
		RETURN NEXT;
	END LOOP;
END;
$$ LANGUAGE plpgsql;


-- zwracanie wielu wartosci jako tabela
CREATE OR REPLACE FUNCTION get_pairs2() RETURNS TABLE(a integer, b integer) AS $$
BEGIN
	FOR i IN 1..10 LOOP
		a = i;
		b = i * 10;
		RETURN NEXT;
	END LOOP;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_testtable(i integer) RETURNS TABLE(test_id integer, test_stuff text) AS $$
    SELECT * FROM testtable WHERE id >= i;
$$ LANGUAGE 'sql';


CREATE OR REPLACE FUNCTION get_testtable2(i integer) RETURNS SETOF testtable AS $$
    SELECT * FROM testtable WHERE id >= i;
$$ LANGUAGE 'sql';


CREATE OR REPLACE FUNCTION get_testtable3(i integer) RETURNS TABLE(test_id integer, test_stuff text) AS $$
DECLARE
	r record;
BEGIN
	-- przetwarzanie wynikow zapytania
	FOR r IN (SELECT * FROM testtable WHERE id >= i) LOOP
		test_id = r.id;
		test_stuff = r.test;
		RETURN NEXT;
	END LOOP;
END;
$$ LANGUAGE plpgsql;


-- obsluga wyjatkow
CREATE OR REPLACE FUNCTION divide(a integer, b integer) RETURNS integer AS $$
BEGIN
	RETURN a / b;
EXCEPTION
    WHEN division_by_zero THEN
        RAISE NOTICE 'wystapilo dzielenie przez zero';
        RETURN -1;
END;
$$ LANGUAGE plpgsql;
