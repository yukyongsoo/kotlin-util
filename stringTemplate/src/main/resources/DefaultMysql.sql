create table template_group (
    id varchar(40) not null,
    descr varchar(100) not null,
    templateIds varchar(255) not null,
    primary key (id)
);