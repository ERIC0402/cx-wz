--- t_package ---
create table t_package
(
    id                serial not null,
    package_name      varchar unique,
    app_name          varchar,
    merchant_number   varchar,
    status            varchar,
    version           varchar,
    account_source    varchar,
    comment           varchar,
    create_time       timestamp default now(),
    update_time       timestamp default now(),
    first_online_time timestamp,
    online_days       int,
    offline_days      int,
    last_online_time  timestamp,
    last_offline_time timestamp,
    online_base_time  timestamp,
    offline_base_time timestamp,
    last_check_time   timestamp
);

create unique index t_package_id_uindex
    on t_package (id);

alter table t_package
    add constraint t_package_pk
        primary key (id);

--- t_merchant ---
create table t_merchant
(
    id                serial not null,
    country           varchar,
    number            varchar unique,
    name              varchar,
    status            varchar,
    owner_id          int,
    offline_days      int,
    offline_base_time timestamp,
    create_time       timestamp default now(),
    update_time       timestamp default now()
);

create unique index t_merchant_id_uindex
    on t_merchant (id);

alter table t_merchant
    add constraint t_merchant_pk
        primary key (id);

--- t_admin ---
create table t_admin
(
    id          serial not null,
    user_name   varchar,
    pwd         varchar,
    nick_name   varchar unique,
    email       varchar,
    status      varchar,
    create_time timestamp default now(),
    update_time timestamp default now()
);

create unique index t_admin_id_uindex
    on t_admin (id);

alter table t_admin
    add constraint t_admin_pk
        primary key (id);


--- t_package_status_log ---
create table t_package_status_log
(
    id          serial not null,
    package_id  int,
    old_status  varchar,
    new_status  varchar,
    comment     varchar,
    create_time timestamp default now(),
    update_time timestamp default now()
);

create unique index t_package_status_log_id_uindex
    on t_package_status_log (id);

alter table t_package_status_log
    add constraint t_package_status_log_pk
        primary key (id);


--- t_crawler_result ---
create table t_crawler_result
(
    id              serial not null,
    package_name    varchar,
    status          varchar,
    online          bool,
    updated         timestamp,
    size            varchar,
    installs        varchar,
    current_version varchar,
    create_time     timestamp
);

create unique index t_crawler_result_id_uindex
    on t_crawler_result (id);

alter table t_crawler_result
    add constraint t_crawler_result_pk
        primary key (id);


--- setting ---
create table t_setting
(
    id          serial  not null
        constraint t_setting_pk
            primary key,
    key         varchar not null,
    value       varchar,
    category    varchar,
    create_time timestamp default now(),
    update_time timestamp default now()
);

create unique index t_setting_id_uindex
    on t_setting (id);

create unique index t_setting_key_uindex
    on t_setting (key);

alter table t_merchant
    add column online_package_count int;

alter table t_merchant
    add column level varchar default 'A';

alter table t_merchant
    add column uploading_package_count int;

alter table t_crawler_result
    add column requires_android varchar;

alter table t_crawler_result
    add column website varchar;

alter table t_crawler_result
    add column email varchar;

alter table t_crawler_result
    add column privacy_policy varchar;


--- t_package_plan ---
create table t_package_plan
(
    id          serial not null,
    package_id  int,
    plan_id  int,
    create_time timestamp default now()
);

create unique index t_package_plan_id_uindex
    on t_package_plan (id);

alter table t_package_plan
    add constraint t_package_plan_pk
        primary key (id);

--- t_plan ---
create table t_plan
(
    id          serial not null,
    plan  varchar,
    status  varchar,
    create_time timestamp default now(),
    update_time timestamp default now()
);

create unique index t_plan_id_uindex
    on t_plan (id);

alter table t_plan
    add constraint t_plan_pk
        primary key (id);