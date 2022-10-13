ALTER TABLE item_option_group ADD FOREIGN KEY(item_id) REFERENCES item(id);

ALTER TABLE item_option ADD FOREIGN KEY(item_option_group_id) REFERENCES item_option_group(id);

ALTER TABLE item DROP COLUMN model_number;
