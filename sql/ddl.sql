-- CREATE, DROP, ALTER
DROP database "test_data_db";
CREATE database test_data_db;
\l
\c test_data_db -- enter
\d --
CREATE TABLE charge_test_data (
id INTEGER PRIMARY KEY,
charge INTEGER NOT NULL,
charge_expected INTEGER NOT NULL
);