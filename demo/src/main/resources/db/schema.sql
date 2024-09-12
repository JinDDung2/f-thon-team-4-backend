create table destination
(
    id             bigint auto_increment
        primary key,
    ct_prvn_name   varchar(255)   null,
    description    varchar(255)   null,
    name           varchar(255)   null,
    si_gun_gu_name varchar(255)   null,
    point          varbinary(255) null,
    index('ct_prvn_name', 'si_gun_gu_name')
);

