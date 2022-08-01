create table template_group
(
    id          varchar(40)  not null,
    descr       varchar(100) not null,
    template_ids varchar(500) not null,
    primary key (id)
);

create table template
(
    id          varchar(40)  not null,
    parent_id   varchar(40)  null,
    content text not null,
    primary key (id)
);

create index parent_id_idx
    on template (parent_id);
