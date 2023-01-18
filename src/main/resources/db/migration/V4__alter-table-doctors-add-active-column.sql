ALTER TABLE doctors ADD active BOOLEAN DEFAULT TRUE;
update doctors SET active = TRUE;