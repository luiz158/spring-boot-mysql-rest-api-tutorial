create table funcionario (
    id_funcionario bigint not null auto_increment,
    nome varchar(70) default null,
    rua varchar(80) default null,
    numero int default null,
    cidade varchar(30) default null,
    estado char(2) default null,
    pais varchar(20) default null,
    cep char(9) default null,
    cpf varchar(14) not null,
    email varchar(80) default null,
    telefone varchar(15) default null,
    cargo varchar(30) default null,
    admin boolean,
    data_nascimento date,
    primary key (id_funcionario)
);

create table setor (
    id_setor bigint not null auto_increment,
    descricao varchar(50),
    responsavel bigint,
    foreign key (responsavel)
        references funcionario (id_funcionario),
    primary key (id_setor)
);

create table epi (
    id_epi bigint not null auto_increment,
    nome varchar(50),
    tipo varchar(25),
    primary key (id_epi)
);

create table epi_setor (
    id_epi_setor bigint not null auto_increment,
    epi bigint,
    setor bigint,
    foreign key (epi)
        references epi (id_epi),
    foreign key (setor)
        references setor (id_setor),
    primary key (id_epi_setor)
);

create table epi_funcionario (
    id_epi_funcionario bigint not null auto_increment,
    epi bigint,
    responsavel bigint,
    validade date,
    foreign key (epi)
        references epi (id_epi),
    foreign key (responsavel)
        references funcionario (id_funcionario),
    primary key (id_epi_funcionario)
);

create table historico (
    id_historico bigint not null auto_increment,
    status boolean,
    data date,
    funcionario bigint,
    setor bigint,
    foreign key (funcionario)
        references funcionario (id_funcionario),
    foreign key (setor)
        references setor (id_setor),
    primary key (id_historico)
);

create table historico_epi (
    id_historico_epi bigint not null auto_increment,
    epi bigint,
    id_historico bigint,
    foreign key (id_historico)
        references historico (id_historico),
    foreign key (epi)
        references epi (id_epi),
    primary key (id_historico_epi)
);