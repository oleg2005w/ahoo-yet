INSERT INTO charge_test_data VALUES(
1,0,0),(2,-5,0),(3,50,50),(4,105,50);

SELECT * FROM charge_test_data;
---update
UPDATE charge_test_data SET charge = -7 WHERE id = 2;

SELECT * FROM charge_test_data ORDER BY (id) ASC;

DELETE FROM charge_test_data WHERE id=2;

INSERT INTO charge_test_data VALUES(2,-5,0);