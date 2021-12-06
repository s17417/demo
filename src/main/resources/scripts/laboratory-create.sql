
    create table AbstractAnalyteResult (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        NumberResult bigint,
        TextResult varchar(255),
        labTestOrder_Id varchar(60),
        method_Id varchar(60),
        primary key (Id)
    ) engine=InnoDB;

    create table AbstractAnalyteResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        resultType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        labTestOrder_Id varchar(60),
        method_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table Analyte (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        primary key (Id)
    ) engine=InnoDB;

    create table Analyte_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit,
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTest (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        primary key (Id)
    ) engine=InnoDB;

    create table LaboratoryTest_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LabQualityControl (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentification varchar(128) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table LabQualityControl_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentification varchar(128),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LabQualityControlResult (
       targetValue bit not null,
        Id varchar(60) not null,
        order_Id varchar(60) not null,
        parentTargetValue_Id varchar(60),
        primary key (Id),
        check ((targetValue=TRUE AND parentTargetValue_Id=NULL) OR (targetValue=FALSE))
    ) engine=InnoDB;

    create table LabQualityControlResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        targetValue bit,
        order_Id varchar(60),
        parentTargetValue_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LabTestOrder (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        laboratoryTest_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table LabTestOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        laboratoryTest_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table Method (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        resultType integer not null,
        analyte_Id varchar(60) not null,
        laboratoryTest_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table Method_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit,
        resultType integer,
        analyte_Id varchar(60),
        laboratoryTest_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderingUnit (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        city varchar(255),
        country varchar(255),
        postalCode varchar(255),
        state varchar(255),
        street varchar(255),
        name varchar(60),
        shortName varchar(60),
        primary key (Id)
    ) engine=InnoDB;

    create table OrderingUnit_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        city varchar(255),
        country varchar(255),
        postalCode varchar(255),
        state varchar(255),
        street varchar(255),
        name varchar(255),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderResult (
       Id varchar(60) not null,
        order_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table OrderResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        order_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table Patient (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        dateOfBirth date,
        gender integer,
        name varchar(60),
        personalIdentificationNumber varchar(30),
        surname varchar(120) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table Patient_addresses (
       Patient_Id varchar(60) not null,
        city varchar(255),
        country varchar(255),
        postalCode varchar(255),
        state varchar(255),
        street varchar(255),
        addresses_ORDER integer not null,
        primary key (Patient_Id, addresses_ORDER)
    ) engine=InnoDB;

    create table Patient_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        dateOfBirth date,
        gender integer,
        name varchar(60),
        personalIdentificationNumber varchar(30),
        surname varchar(120),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table Patient_PatientComment_AUD (
       REV integer not null,
        Patient_Id varchar(60) not null,
        Id varchar(60) not null,
        REVTYPE tinyint,
        primary key (REV, Patient_Id, Id)
    ) engine=InnoDB;

    create table Patient_phoneNumbers (
       Patient_Id varchar(60) not null,
        phoneNumbers varchar(255) not null,
        phoneNumbers_ORDER integer not null,
        primary key (Patient_Id, phoneNumbers_ORDER)
    ) engine=InnoDB;

    create table PatientComment (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        comment varchar(255),
        Patient_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table PatientComment_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        primary key (Id, REV)
    ) engine=InnoDB;

    create table PatientOrder (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentification varchar(128) not null,
        orderDate date,
        orderingUnit_Id varchar(60),
        patient_Id varchar(60) not null,
        phisician_Id varchar(60),
        primary key (Id)
    ) engine=InnoDB;

    create table PatientOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentification varchar(128),
        orderDate date,
        orderingUnit_Id varchar(60),
        patient_Id varchar(60),
        phisician_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table Phisician (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        name varchar(60),
        personalIdentificationNumber varchar(30),
        surname varchar(120),
        primary key (Id)
    ) engine=InnoDB;

    create table Phisician_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        name varchar(60),
        personalIdentificationNumber varchar(30),
        surname varchar(120),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table PhisicianOrderingUnit (
       orderingUnitId varchar(60) not null,
        phisicianId varchar(60) not null,
        primary key (orderingUnitId, phisicianId)
    ) engine=InnoDB;

    create table PhisicianOrderingUnit_AUD (
       REV integer not null,
        orderingUnitId varchar(60) not null,
        phisicianId varchar(60) not null,
        REVTYPE tinyint,
        primary key (REV, orderingUnitId, phisicianId)
    ) engine=InnoDB;

    create table REVINFO (
       REV integer not null auto_increment,
        REVTSTMP bigint,
        primary key (REV)
    ) engine=InnoDB;

    alter table LabQualityControl 
       add constraint UK_trlsvlwdwm9wn8sa2mw4ra7ib unique (orderIdentification);

    alter table Patient 
       add constraint UK_9sy3r8nt1bq8e84mqsj7omfth unique (personalIdentificationNumber);

    alter table PatientOrder 
       add constraint UK_9erha28i1dowgkb2p0jaj68fc unique (orderIdentification);

    alter table Phisician 
       add constraint UK_hhhsqecp80pow3qrj66fnkmd7 unique (personalIdentificationNumber);

    alter table AbstractAnalyteResult 
       add constraint FK8bmge3b9exphdy4b6h2w3t2in 
       foreign key (labTestOrder_Id) 
       references LabTestOrder (Id);

    alter table AbstractAnalyteResult 
       add constraint FKi30odtwxrojto51hin0nbuejf 
       foreign key (method_Id) 
       references Method (Id);

    alter table AbstractAnalyteResult_AUD 
       add constraint FK4hvlsimcjwtf3swdrlquu868k 
       foreign key (REV) 
       references REVINFO (REV);

    alter table Analyte_AUD 
       add constraint FK4k2vc25sg3d5ol920sigottvi 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTest_AUD 
       add constraint FKbbqpm2hy4c1t9qh27ocqnc8ki 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LabQualityControl_AUD 
       add constraint FKjbkxxjanb8hwowdhfxxe2qdo0 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LabQualityControlResult 
       add constraint FKosxu1hwxe77e9lmmcpx6sb673 
       foreign key (order_Id) 
       references LabQualityControl (Id);

    alter table LabQualityControlResult 
       add constraint FKg6nvhcqnejdi35pqkye2ghnqr 
       foreign key (parentTargetValue_Id) 
       references LabQualityControlResult (Id);

    alter table LabQualityControlResult 
       add constraint FKsmdy7pbnxmwak88xtjh70px8 
       foreign key (Id) 
       references LabTestOrder (Id);

    alter table LabQualityControlResult_AUD 
       add constraint FKknkmyu7gyup215v6aaqslrdb6 
       foreign key (Id, REV) 
       references LabTestOrder_AUD (Id, REV);

    alter table LabTestOrder 
       add constraint FKjah3jpw3bulh8csm6hkmg6apn 
       foreign key (laboratoryTest_Id) 
       references LaboratoryTest (Id);

    alter table LabTestOrder_AUD 
       add constraint FK7qdwe1vfve8sqgj6f4xsi6cs 
       foreign key (REV) 
       references REVINFO (REV);

    alter table Method 
       add constraint FKkl6d5qmuiiwxra8rfd58fbvyx 
       foreign key (analyte_Id) 
       references Analyte (Id);

    alter table Method 
       add constraint FKnrwgpu4db8br1wg48b77qxlri 
       foreign key (laboratoryTest_Id) 
       references LaboratoryTest (Id);

    alter table Method_AUD 
       add constraint FKbtsb1u7ddd5tysh348l7vt4k2 
       foreign key (REV) 
       references REVINFO (REV);

    alter table OrderingUnit_AUD 
       add constraint FKd760x09gcxesegessgjp6qos6 
       foreign key (REV) 
       references REVINFO (REV);

    alter table OrderResult 
       add constraint FK9nup2ja6phkloufiqb3plae9h 
       foreign key (order_Id) 
       references PatientOrder (Id);

    alter table OrderResult 
       add constraint FK98ggq6oq25sv2cmppgr4oc5ty 
       foreign key (Id) 
       references LabTestOrder (Id);

    alter table OrderResult_AUD 
       add constraint FKap2gh8nv85jqmjxrw1h1jcinj 
       foreign key (Id, REV) 
       references LabTestOrder_AUD (Id, REV);

    alter table Patient_addresses 
       add constraint FK6rcnegjeixsve1wlafohe35ep 
       foreign key (Patient_Id) 
       references Patient (Id);

    alter table Patient_AUD 
       add constraint FKdlyae0mnlgiwq5mjh0e2s2qba 
       foreign key (REV) 
       references REVINFO (REV);

    alter table Patient_PatientComment_AUD 
       add constraint FK96mtlkd59a7rhigg7ains2mc4 
       foreign key (REV) 
       references REVINFO (REV);

    alter table Patient_phoneNumbers 
       add constraint FK5l8jlvv2g67kf9spp1nc7te14 
       foreign key (Patient_Id) 
       references Patient (Id);

    alter table PatientComment 
       add constraint FKpny0oc4dsrkolgfvfk0f0ntss 
       foreign key (Patient_Id) 
       references Patient (Id);

    alter table PatientComment_AUD 
       add constraint FKlhcer5lmym57jb4s2njuvlyby 
       foreign key (REV) 
       references REVINFO (REV);

    alter table PatientOrder 
       add constraint FK1mk9510jr2ecm3lnhwoad8hck 
       foreign key (orderingUnit_Id) 
       references OrderingUnit (Id);

    alter table PatientOrder 
       add constraint FKb5hkiem731707u1t7yst1gk4f 
       foreign key (patient_Id) 
       references Patient (Id);

    alter table PatientOrder 
       add constraint FK237kjypkrgluokjk0ou66efur 
       foreign key (phisician_Id) 
       references Phisician (Id);

    alter table PatientOrder_AUD 
       add constraint FKi7morp0fgm7cd579deey8oxw0 
       foreign key (REV) 
       references REVINFO (REV);

    alter table Phisician_AUD 
       add constraint FKcj5euetw0smq0n0yfdylh4x84 
       foreign key (REV) 
       references REVINFO (REV);

    alter table PhisicianOrderingUnit 
       add constraint FKqlct0yx5lgxkwygpol61dxjyb 
       foreign key (phisicianId) 
       references Phisician (Id);

    alter table PhisicianOrderingUnit 
       add constraint FK58oa64rbvatotssgvy7u6ah5m 
       foreign key (orderingUnitId) 
       references OrderingUnit (Id);

    alter table PhisicianOrderingUnit_AUD 
       add constraint FKhivinq2u3s7y9ewfebcck2nnn 
       foreign key (REV) 
       references REVINFO (REV);
