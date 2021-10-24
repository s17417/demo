
    create table Invitation (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        email varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB

    create table Tenant (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        databasePassword varchar(60) not null,
        databseUserName varchar(60) not null,
        ddlGeneration varchar(180) not null,
        dialect varchar(180) not null,
        driverClassName varchar(120) not null,
        name varchar(60) not null,
        sqlServerUrl varchar(180) not null,
        primary key (Id)
    ) engine=InnoDB

    create table TenantInvitation (
       tenantId varchar(60) not null,
        invitationId varchar(60) not null,
        primary key (tenantId, invitationId)
    ) engine=InnoDB

    create table Users (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        accountNonExpired bit default true not null,
        accountNonLocked bit default true not null,
        creationTimeStamp datetime(6),
        credentialsNonExpired bit default true not null,
        email varchar(60) not null,
        enabled bit default false not null,
        firstname varchar(60) not null,
        password varchar(60) not null,
        surname varchar(60) not null,
        updateTimeStamp datetime(6),
        primary key (Id)
    ) engine=InnoDB

    create table UsersTenantRole (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        role varchar(50),
        tenant_Id varchar(60) not null,
        user_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB

    alter table Invitation 
       add constraint UK_qn4y76t73gep60579uvfo4mb2 unique (email)

    alter table Tenant 
       add constraint UK_gvop3sv0mcmrwiplqeio34rff unique (name)

    alter table Users 
       add constraint UK_ncoa9bfasrql0x4nhmh1plxxy unique (email)

    alter table UsersTenantRole 
       add constraint UK9cl40xgsbs5ho6jkca3j3ye7w unique (tenant_Id, user_Id)

    alter table TenantInvitation 
       add constraint FK2a8vmakf1aqndt1xf62f0b87x 
       foreign key (invitationId) 
       references Invitation (Id)

    alter table TenantInvitation 
       add constraint FKobqg7498nrupxb6fhy2x34kvr 
       foreign key (tenantId) 
       references Tenant (Id)

    alter table UsersTenantRole 
       add constraint FKg0wgq4vslgfqgg74uh85xdxgk 
       foreign key (tenant_Id) 
       references Tenant (Id)

    alter table UsersTenantRole 
       add constraint FK71cqb2gv0joq4mydqgojadjhw 
       foreign key (user_Id) 
       references Users (Id)

    create table Invitation (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        email varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB

    create table Tenant (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        databasePassword varchar(60) not null,
        databseUserName varchar(60) not null,
        ddlGeneration varchar(180) not null,
        dialect varchar(180) not null,
        driverClassName varchar(120) not null,
        name varchar(60) not null,
        sqlServerUrl varchar(180) not null,
        primary key (Id)
    ) engine=InnoDB

    create table TenantInvitation (
       tenantId varchar(60) not null,
        invitationId varchar(60) not null,
        primary key (tenantId, invitationId)
    ) engine=InnoDB

    create table Users (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        accountNonExpired bit default true not null,
        accountNonLocked bit default true not null,
        creationTimeStamp datetime(6),
        credentialsNonExpired bit default true not null,
        email varchar(60) not null,
        enabled bit default false not null,
        firstname varchar(60) not null,
        password varchar(60) not null,
        surname varchar(60) not null,
        updateTimeStamp datetime(6),
        primary key (Id)
    ) engine=InnoDB

    create table UsersTenantRole (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        role varchar(50),
        tenant_Id varchar(60) not null,
        user_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB

    alter table Invitation 
       add constraint UK_qn4y76t73gep60579uvfo4mb2 unique (email)

    alter table Tenant 
       add constraint UK_gvop3sv0mcmrwiplqeio34rff unique (name)

    alter table Users 
       add constraint UK_ncoa9bfasrql0x4nhmh1plxxy unique (email)

    alter table UsersTenantRole 
       add constraint UK9cl40xgsbs5ho6jkca3j3ye7w unique (tenant_Id, user_Id)

    alter table TenantInvitation 
       add constraint FK2a8vmakf1aqndt1xf62f0b87x 
       foreign key (invitationId) 
       references Invitation (Id)

    alter table TenantInvitation 
       add constraint FKobqg7498nrupxb6fhy2x34kvr 
       foreign key (tenantId) 
       references Tenant (Id)

    alter table UsersTenantRole 
       add constraint FKg0wgq4vslgfqgg74uh85xdxgk 
       foreign key (tenant_Id) 
       references Tenant (Id)

    alter table UsersTenantRole 
       add constraint FK71cqb2gv0joq4mydqgojadjhw 
       foreign key (user_Id) 
       references Users (Id)
