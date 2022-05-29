create database wss;
use wss;

create table t_worker
(
    id     int auto_increment,
    name   varchar(30) not null,
    uid    int         not null comment 'User ID in t_user',
    wid    varchar(50) not null unique comment 'Worker ID',
    status int         not null comment '1 - 在职, 2 - 离职',
    gender varchar(10) not null comment 'F; M',
    constraint t_worker_pk
        primary key (id)
)
    comment 'Worker info';

create table t_product
(
    id    int auto_increment,
    price double      not null,
    pid   varchar(50) not null unique comment 'Product Identifier',
    description varchar(200),
    constraint t_product_pk
        primary key (id)
)
    comment 'Product info';

create table t_settings
(
    id         int auto_increment,
    attributes varchar(50)  not null,
    value      varchar(500) not null,
    constraint t_settings_pk
        primary key (id)
);

create table t_user
(
    id       int auto_increment,
    username varchar(50)  not null unique,
    password varchar(300) not null,
    role     int          not null comment '1 - Worker, 2 - Admin, 3 - Resigned',
    constraint t_user_pk
        primary key (id)
)
    comment 'This user table is used to check auth.';

create table t_record
(
    id     int auto_increment,
    uid    int    not null comment 'User ID',
    pid    int    not null comment 'Product ID',
    price  double not null comment 'Total price of this record',
    amount int not null comment 'record product amount',
    status int    not null comment '1 - Submitted, 2 - Permitted, 3 - Refused.',
    dateTime varchar(200) not null comment 'current datetime',
    constraint t_record_pk
        primary key (id),
    constraint t_record_t_product_id_fk
        foreign key (pid) references t_product (id),
    constraint t_record_t_user_id_fk
        foreign key (uid) references t_user (id)
)
    comment 'Submit/Salary Record';

insert into t_settings (attributes, value)
values ('adminPassword', 'Vm0wd2VHUXhTWGhXV0doVFYwZDRWRll3Wkc5V1ZsbDNXa1JTV0ZKdGVEQmFWVll3VmpKS1IySkVUbHBXVmxwUVdWVmFTMk14V25GVWJHUk9ZV3hhZVZkV1pEUlpWMUpJVm10c2FsSnRVazlaYlhoTFlqRmFjMVp0UmxkTlZuQlhWRlpXVjJGSFZuRlJWR3M5');
