ALTER TABLE patients ADD active BOOLEAN DEFAULT TRUE;
update patients SET active = TRUE;