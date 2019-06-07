create table funcionario (
    idFuncionario int not null auto_increment,
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
    administrativo boolean,
    dataNascimento date,
    primary key (idFuncionario)
);

create table setor (
    idSetor int not null auto_increment,
    descricao varchar(50),
    responsavel int,
    foreign key (responsavel)
        references funcionario (idFuncionario),
    primary key (idSetor)
);

create table epi (
    idEpi int not null auto_increment,
    nome varchar(50),
    tipo varchar(25),
    primary key (idEpi)
);

create table epi_setor (
    idEpiSetor int not null auto_increment,
    epi int,
    setor int,
    foreign key (epi)
        references epi (idEpi),
    foreign key (setor)
        references setor (idSetor),
    primary key (idEpiSetor)
);

create table epi_funcionario (
    idEpiFuncionario int not null auto_increment,
    epi int,
    responsavel int,
    validade date,
    foreign key (epi)
        references epi (idEpi),
    foreign key (responsavel)
        references funcionario (idFuncionario),
    primary key (idEpiFuncionario)
);

create table historico (
    idHistorico int not null auto_increment,
    status boolean,
    data date,
    funcionario int,
    setor int,
    foreign key (funcionario)
        references funcionario (idFuncionario),
    foreign key (setor)
        references setor (idSetor),
    primary key (idHistorico)
);

create table historico_epi (
    idHistoricoEpi int not null auto_increment,
    epi int,
    historico int,
    foreign key (historico)
        references historico (idHistorico),
    foreign key (epi)
        references epi (idEpi),
    primary key (idHistoricoEpi)
);