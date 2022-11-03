alter table item ADD COLUMN seller_id bigint not null comment '가게 ID';
alter table item drop model_number;
