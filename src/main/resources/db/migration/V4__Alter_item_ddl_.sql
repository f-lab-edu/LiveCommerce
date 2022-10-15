alter table item ADD COLUMN shop_id bigint not null comment '가게 ID';
alter table item drop model_number;
