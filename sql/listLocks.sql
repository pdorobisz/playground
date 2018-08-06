SELECT locktype, relation::regclass, mode, transactionid AS tid, virtualtransaction AS vtid, pid, granted
FROM pg_catalog.pg_locks l LEFT JOIN pg_catalog.pg_database db
	ON db.oid = l.database
	-- nazwa bazy danych:
WHERE (db.datname = 'learning' OR db.datname IS NULL) AND NOT pid = pg_backend_pid()
ORDER BY pid, mode;


-- aktualny stan zapytan:
-- SELECT * FROM pg_stat_activity where datname='learning';