create table destination
(
    id             bigint auto_increment
        primary key,
    ct_prvn_name   varchar(255)  not null,
    description    text   null,
    name           varchar(255)  not null,
    si_gun_gu_name varchar(255)  not null,
    point          varbinary(255) not null,
    index('ct_prvn_name', 'si_gun_gu_name')
);

