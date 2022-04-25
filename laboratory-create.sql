
    create table AbstractAnalyteResult (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        NumberResult decimal(36,18),
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
        TextResult varchar(255),
        NumberResult decimal(36,18),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table AbstractOrder (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table AbstractOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTest (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60) not null,
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
        isActive bit,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTestHistory (
       updateTimeStamp varchar(255) not null,
        cretionTimeStamp datetime(6),
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        laboratoryTest_Id varchar(60) not null,
        primary key (laboratoryTest_Id, updateTimeStamp)
    ) engine=InnoDB;

    create table LabQualityControl (
       description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60) not null,
        reportingDeadLine datetime(6),
        Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table LabQualityControl_AUD (
       Id varchar(60) not null,
        REV integer not null,
        description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60),
        reportingDeadLine datetime(6),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LabQualityControlResult (
       targetValue bit not null,
        Id varchar(60) not null,
        controlSample_Id varchar(60) not null,
        parentTargetValue_Id varchar(60),
        primary key (Id),
        check ((targetValue=TRUE AND parentTargetValue_Id=NULL) OR (targetValue=FALSE))
    ) engine=InnoDB;

    create table LabQualityControlResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        targetValue bit,
        controlSample_Id varchar(60),
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
        labTestOrderStatus varchar(255) not null,
        resultDescription varchar(512),
        tatMode varchar(255) not null,
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
        labTestOrderStatus varchar(255),
        resultDescription varchar(512),
        tatMode varchar(255),
        laboratoryTest_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table Method (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        analyticalMethodType varchar(180) not null,
        printable bit default true,
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        analyte_Id varchar(60) not null,
        laboratoryTest_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table Method_AUD (
       Id varchar(60) not null,
        REV integer not null,
        resultType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit,
        analyticalMethodType varchar(180),
        printable bit,
        analyte_Id varchar(60),
        laboratoryTest_Id varchar(60),
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderingUnit (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
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
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderResult (
       Id varchar(60) not null,
        patientSample_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table OrderResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        patientSample_Id varchar(60),
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
        comment varchar(4096),
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
       orderDate date,
        Id varchar(60) not null,
        orderingUnit_Id varchar(60),
        patient_Id varchar(60) not null,
        phisician_Id varchar(60),
        primary key (Id)
    ) engine=InnoDB;

    create table PatientOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
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

    create table QualitativeFormatMethod_resultTemplates (
       QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255)
    ) engine=InnoDB;

    create table QualitativeFormatMethod_resultTemplates_AUD (
       REV integer not null,
        QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255) not null,
        REVTYPE tinyint,
        primary key (REV, QualitativeFormatMethod_Id, resultTemplates)
    ) engine=InnoDB;

    create table QuantitativeFormatMethod_refferentialRanges (
       QuantitativeFormatMethod_Id varchar(60) not null,
        fromAge bigint,
        gender integer,
        toAge bigint,
        valueFrom decimal(19,2),
        valueTo decimal(19,2),
        refferentialRanges_ORDER integer not null,
        primary key (QuantitativeFormatMethod_Id, refferentialRanges_ORDER)
    ) engine=InnoDB;

    create table REVINFO (
       REV integer not null auto_increment,
        REVTSTMP bigint,
        primary key (REV)
    ) engine=InnoDB;

    create table Sample (
       sampleType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(60) not null,
        specimentType_Id varchar(60) not null,
        patientOrder_Id varchar(60),
        labQualityControl_Id varchar(60),
        primary key (Id),
        check ((sampleType='PATIENT' AND patientOrder_Id IS NOT NULL AND labQualityControl_Id IS NULL) OR (sampleType='CONTROL' AND labQualityControl_Id IS NOT NULL AND patientOrder_Id IS NULL))
    ) engine=InnoDB;

    create table Sample_AUD (
       Id varchar(60) not null,
        REV integer not null,
        sampleType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(255),
        specimentType_Id varchar(60),
        labQualityControl_Id varchar(60),
        patientOrder_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table SpecimentType (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        isActive bit not null,
        speciment varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table SpecimentType_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        isActive bit,
        speciment varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    alter table AbstractOrder 
       add constraint UK_8mc8121is13twuiqwxy15k3g4 unique (orderIdentificationCode);

    alter table Analyte 
       add constraint UK_47x69sl7aoei6c0giwnca5qxt unique (shortName);

    alter table LaboratoryTest 
       add constraint UK_fiqfkklxeqombys4ekliflm47 unique (shortName);

    alter table Patient 
       add constraint personalIdentificationNumber unique (personalIdentificationNumber);

    alter table Phisician 
       add constraint UK_hhhsqecp80pow3qrj66fnkmd7 unique (personalIdentificationNumber);

    alter table Sample 
       add constraint UK_7y5oe0qpo05a5pbgjechxalre unique (sampleId);

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

    alter table AbstractOrder_AUD 
       add constraint FKp5iurhe3l1huersgl4xhd0k82 
       foreign key (REV) 
       references REVINFO (REV);

    alter table Analyte_AUD 
       add constraint FK4k2vc25sg3d5ol920sigottvi 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTest 
       add constraint FKbtrkm84momkbq5lqc8b0gi2ay 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table LaboratoryTest_AUD 
       add constraint FKbbqpm2hy4c1t9qh27ocqnc8ki 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTestHistory 
       add constraint FK1iw9ydv52bxcevjjlwyhhrwm5 
       foreign key (laboratoryTest_Id) 
       references LaboratoryTest (Id);

    alter table LabQualityControl 
       add constraint FK6bfgd24ei1djon6txiukomg60 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table LabQualityControl_AUD 
       add constraint FK5vn58bjbwrlnaw2wq3hivf23x 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

    alter table LabQualityControlResult 
       add constraint FKhc7spogtqlcdu14vv19ih4tqv 
       foreign key (controlSample_Id) 
       references Sample (Id);

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
       add constraint FKf7w0xssqiu7vy91uvk42qvumk 
       foreign key (patientSample_Id) 
       references Sample (Id);

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

    alter table PatientOrder 
       add constraint FKgcc7dp56rsy3fdovglmx2tlno 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table PatientOrder_AUD 
       add constraint FK570w5utmawcgcvrg7xp3fkjfd 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

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

    alter table QualitativeFormatMethod_resultTemplates 
       add constraint FKsvkk12kvdypx9kp69qba8emk4 
       foreign key (QualitativeFormatMethod_Id) 
       references Method (Id);

    alter table QualitativeFormatMethod_resultTemplates_AUD 
       add constraint FKnq8dn9dr2hxa3d70rf59tkicg 
       foreign key (REV) 
       references REVINFO (REV);

    alter table QuantitativeFormatMethod_refferentialRanges 
       add constraint FKakkpqci9i3lrq4ql9vyxl03lh 
       foreign key (QuantitativeFormatMethod_Id) 
       references Method (Id);

    alter table Sample 
       add constraint FKtcs6g9uujpbeq9eaui0n1qy7a 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table Sample 
       add constraint FKru88e978gj24srmr5bhe11ci7 
       foreign key (patientOrder_Id) 
       references PatientOrder (Id);

    alter table Sample 
       add constraint FK5urc15yfju1sedjs71hx3vwhx 
       foreign key (labQualityControl_Id) 
       references LabQualityControl (Id);

    alter table Sample_AUD 
       add constraint FK7c3l5hxfww0tdps18v0amndd1 
       foreign key (REV) 
       references REVINFO (REV);

    alter table SpecimentType_AUD 
       add constraint FK4jh9vuijlnqx2sftathnhptm0 
       foreign key (REV) 
       references REVINFO (REV);

    create table AbstractAnalyteResult (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        NumberResult decimal(36,18),
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
        TextResult varchar(255),
        NumberResult decimal(36,18),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table AbstractOrder (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table AbstractOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTest (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60) not null,
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
        isActive bit,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTestHistory (
       updateTimeStamp varchar(255) not null,
        cretionTimeStamp datetime(6),
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        laboratoryTest_Id varchar(60) not null,
        primary key (laboratoryTest_Id, updateTimeStamp)
    ) engine=InnoDB;

    create table LabQualityControl (
       description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60) not null,
        reportingDeadLine datetime(6),
        Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table LabQualityControl_AUD (
       Id varchar(60) not null,
        REV integer not null,
        description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60),
        reportingDeadLine datetime(6),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LabQualityControlResult (
       targetValue bit not null,
        Id varchar(60) not null,
        controlSample_Id varchar(60) not null,
        parentTargetValue_Id varchar(60),
        primary key (Id),
        check ((targetValue=TRUE AND parentTargetValue_Id=NULL) OR (targetValue=FALSE))
    ) engine=InnoDB;

    create table LabQualityControlResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        targetValue bit,
        controlSample_Id varchar(60),
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
        labTestOrderStatus varchar(255) not null,
        resultDescription varchar(512),
        tatMode varchar(255) not null,
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
        labTestOrderStatus varchar(255),
        resultDescription varchar(512),
        tatMode varchar(255),
        laboratoryTest_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table Method (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        analyticalMethodType varchar(180) not null,
        printable bit default true,
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        analyte_Id varchar(60) not null,
        laboratoryTest_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table Method_AUD (
       Id varchar(60) not null,
        REV integer not null,
        resultType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit,
        analyticalMethodType varchar(180),
        printable bit,
        analyte_Id varchar(60),
        laboratoryTest_Id varchar(60),
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderingUnit (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
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
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderResult (
       Id varchar(60) not null,
        patientSample_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table OrderResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        patientSample_Id varchar(60),
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
        comment varchar(4096),
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
       orderDate date,
        Id varchar(60) not null,
        orderingUnit_Id varchar(60),
        patient_Id varchar(60) not null,
        phisician_Id varchar(60),
        primary key (Id)
    ) engine=InnoDB;

    create table PatientOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
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

    create table QualitativeFormatMethod_resultTemplates (
       QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255)
    ) engine=InnoDB;

    create table QualitativeFormatMethod_resultTemplates_AUD (
       REV integer not null,
        QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255) not null,
        REVTYPE tinyint,
        primary key (REV, QualitativeFormatMethod_Id, resultTemplates)
    ) engine=InnoDB;

    create table QuantitativeFormatMethod_refferentialRanges (
       QuantitativeFormatMethod_Id varchar(60) not null,
        fromAge bigint,
        gender integer,
        toAge bigint,
        valueFrom decimal(19,2),
        valueTo decimal(19,2),
        refferentialRanges_ORDER integer not null,
        primary key (QuantitativeFormatMethod_Id, refferentialRanges_ORDER)
    ) engine=InnoDB;

    create table REVINFO (
       REV integer not null auto_increment,
        REVTSTMP bigint,
        primary key (REV)
    ) engine=InnoDB;

    create table Sample (
       sampleType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(60) not null,
        specimentType_Id varchar(60) not null,
        patientOrder_Id varchar(60),
        labQualityControl_Id varchar(60),
        primary key (Id),
        check ((sampleType='PATIENT' AND patientOrder_Id IS NOT NULL AND labQualityControl_Id IS NULL) OR (sampleType='CONTROL' AND labQualityControl_Id IS NOT NULL AND patientOrder_Id IS NULL))
    ) engine=InnoDB;

    create table Sample_AUD (
       Id varchar(60) not null,
        REV integer not null,
        sampleType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(255),
        specimentType_Id varchar(60),
        labQualityControl_Id varchar(60),
        patientOrder_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table SpecimentType (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        isActive bit not null,
        speciment varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table SpecimentType_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        isActive bit,
        speciment varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    alter table AbstractOrder 
       add constraint UK_8mc8121is13twuiqwxy15k3g4 unique (orderIdentificationCode);

    alter table Analyte 
       add constraint UK_47x69sl7aoei6c0giwnca5qxt unique (shortName);

    alter table LaboratoryTest 
       add constraint UK_fiqfkklxeqombys4ekliflm47 unique (shortName);

    alter table Patient 
       add constraint personalIdentificationNumber unique (personalIdentificationNumber);

    alter table Phisician 
       add constraint UK_hhhsqecp80pow3qrj66fnkmd7 unique (personalIdentificationNumber);

    alter table Sample 
       add constraint UK_7y5oe0qpo05a5pbgjechxalre unique (sampleId);

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

    alter table AbstractOrder_AUD 
       add constraint FKp5iurhe3l1huersgl4xhd0k82 
       foreign key (REV) 
       references REVINFO (REV);

    alter table Analyte_AUD 
       add constraint FK4k2vc25sg3d5ol920sigottvi 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTest 
       add constraint FKbtrkm84momkbq5lqc8b0gi2ay 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table LaboratoryTest_AUD 
       add constraint FKbbqpm2hy4c1t9qh27ocqnc8ki 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTestHistory 
       add constraint FK1iw9ydv52bxcevjjlwyhhrwm5 
       foreign key (laboratoryTest_Id) 
       references LaboratoryTest (Id);

    alter table LabQualityControl 
       add constraint FK6bfgd24ei1djon6txiukomg60 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table LabQualityControl_AUD 
       add constraint FK5vn58bjbwrlnaw2wq3hivf23x 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

    alter table LabQualityControlResult 
       add constraint FKhc7spogtqlcdu14vv19ih4tqv 
       foreign key (controlSample_Id) 
       references Sample (Id);

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
       add constraint FKf7w0xssqiu7vy91uvk42qvumk 
       foreign key (patientSample_Id) 
       references Sample (Id);

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

    alter table PatientOrder 
       add constraint FKgcc7dp56rsy3fdovglmx2tlno 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table PatientOrder_AUD 
       add constraint FK570w5utmawcgcvrg7xp3fkjfd 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

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

    alter table QualitativeFormatMethod_resultTemplates 
       add constraint FKsvkk12kvdypx9kp69qba8emk4 
       foreign key (QualitativeFormatMethod_Id) 
       references Method (Id);

    alter table QualitativeFormatMethod_resultTemplates_AUD 
       add constraint FKnq8dn9dr2hxa3d70rf59tkicg 
       foreign key (REV) 
       references REVINFO (REV);

    alter table QuantitativeFormatMethod_refferentialRanges 
       add constraint FKakkpqci9i3lrq4ql9vyxl03lh 
       foreign key (QuantitativeFormatMethod_Id) 
       references Method (Id);

    alter table Sample 
       add constraint FKtcs6g9uujpbeq9eaui0n1qy7a 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table Sample 
       add constraint FKru88e978gj24srmr5bhe11ci7 
       foreign key (patientOrder_Id) 
       references PatientOrder (Id);

    alter table Sample 
       add constraint FK5urc15yfju1sedjs71hx3vwhx 
       foreign key (labQualityControl_Id) 
       references LabQualityControl (Id);

    alter table Sample_AUD 
       add constraint FK7c3l5hxfww0tdps18v0amndd1 
       foreign key (REV) 
       references REVINFO (REV);

    alter table SpecimentType_AUD 
       add constraint FK4jh9vuijlnqx2sftathnhptm0 
       foreign key (REV) 
       references REVINFO (REV);

    create table AbstractAnalyteResult (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        NumberResult decimal(36,18),
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
        TextResult varchar(255),
        NumberResult decimal(36,18),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table AbstractOrder (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table AbstractOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTest (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60) not null,
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
        isActive bit,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTestHistory (
       updateTimeStamp varchar(255) not null,
        cretionTimeStamp datetime(6),
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        laboratoryTest_Id varchar(60) not null,
        primary key (laboratoryTest_Id, updateTimeStamp)
    ) engine=InnoDB;

    create table LabQualityControl (
       description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60) not null,
        reportingDeadLine datetime(6),
        Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table LabQualityControl_AUD (
       Id varchar(60) not null,
        REV integer not null,
        description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60),
        reportingDeadLine datetime(6),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LabQualityControlResult (
       targetValue bit not null,
        Id varchar(60) not null,
        controlSample_Id varchar(60) not null,
        parentTargetValue_Id varchar(60),
        primary key (Id),
        check ((targetValue=TRUE AND parentTargetValue_Id=NULL) OR (targetValue=FALSE))
    ) engine=InnoDB;

    create table LabQualityControlResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        targetValue bit,
        controlSample_Id varchar(60),
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
        labTestOrderStatus varchar(255) not null,
        resultDescription varchar(512),
        tatMode varchar(255) not null,
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
        labTestOrderStatus varchar(255),
        resultDescription varchar(512),
        tatMode varchar(255),
        laboratoryTest_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table Method (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        analyticalMethodType varchar(180) not null,
        printable bit default true,
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        analyte_Id varchar(60) not null,
        laboratoryTest_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table Method_AUD (
       Id varchar(60) not null,
        REV integer not null,
        resultType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit,
        analyticalMethodType varchar(180),
        printable bit,
        analyte_Id varchar(60),
        laboratoryTest_Id varchar(60),
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderingUnit (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
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
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderResult (
       Id varchar(60) not null,
        patientSample_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table OrderResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        patientSample_Id varchar(60),
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
        comment varchar(4096),
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
       orderDate date,
        Id varchar(60) not null,
        orderingUnit_Id varchar(60),
        patient_Id varchar(60) not null,
        phisician_Id varchar(60),
        primary key (Id)
    ) engine=InnoDB;

    create table PatientOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
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

    create table QualitativeFormatMethod_resultTemplates (
       QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255)
    ) engine=InnoDB;

    create table QualitativeFormatMethod_resultTemplates_AUD (
       REV integer not null,
        QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255) not null,
        REVTYPE tinyint,
        primary key (REV, QualitativeFormatMethod_Id, resultTemplates)
    ) engine=InnoDB;

    create table QuantitativeFormatMethod_refferentialRanges (
       QuantitativeFormatMethod_Id varchar(60) not null,
        fromAge bigint,
        gender integer,
        toAge bigint,
        valueFrom decimal(19,2),
        valueTo decimal(19,2),
        refferentialRanges_ORDER integer not null,
        primary key (QuantitativeFormatMethod_Id, refferentialRanges_ORDER)
    ) engine=InnoDB;

    create table REVINFO (
       REV integer not null auto_increment,
        REVTSTMP bigint,
        primary key (REV)
    ) engine=InnoDB;

    create table Sample (
       sampleType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(60) not null,
        specimentType_Id varchar(60) not null,
        patientOrder_Id varchar(60),
        labQualityControl_Id varchar(60),
        primary key (Id),
        check ((sampleType='PATIENT' AND patientOrder_Id IS NOT NULL AND labQualityControl_Id IS NULL) OR (sampleType='CONTROL' AND labQualityControl_Id IS NOT NULL AND patientOrder_Id IS NULL))
    ) engine=InnoDB;

    create table Sample_AUD (
       Id varchar(60) not null,
        REV integer not null,
        sampleType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(255),
        specimentType_Id varchar(60),
        labQualityControl_Id varchar(60),
        patientOrder_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table SpecimentType (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        isActive bit not null,
        speciment varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table SpecimentType_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        isActive bit,
        speciment varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    alter table AbstractOrder 
       add constraint UK_8mc8121is13twuiqwxy15k3g4 unique (orderIdentificationCode);

    alter table Analyte 
       add constraint UK_47x69sl7aoei6c0giwnca5qxt unique (shortName);

    alter table LaboratoryTest 
       add constraint UK_fiqfkklxeqombys4ekliflm47 unique (shortName);

    alter table Patient 
       add constraint personalIdentificationNumber unique (personalIdentificationNumber);

    alter table Phisician 
       add constraint UK_hhhsqecp80pow3qrj66fnkmd7 unique (personalIdentificationNumber);

    alter table Sample 
       add constraint UK_7y5oe0qpo05a5pbgjechxalre unique (sampleId);

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

    alter table AbstractOrder_AUD 
       add constraint FKp5iurhe3l1huersgl4xhd0k82 
       foreign key (REV) 
       references REVINFO (REV);

    alter table Analyte_AUD 
       add constraint FK4k2vc25sg3d5ol920sigottvi 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTest 
       add constraint FKbtrkm84momkbq5lqc8b0gi2ay 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table LaboratoryTest_AUD 
       add constraint FKbbqpm2hy4c1t9qh27ocqnc8ki 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTestHistory 
       add constraint FK1iw9ydv52bxcevjjlwyhhrwm5 
       foreign key (laboratoryTest_Id) 
       references LaboratoryTest (Id);

    alter table LabQualityControl 
       add constraint FK6bfgd24ei1djon6txiukomg60 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table LabQualityControl_AUD 
       add constraint FK5vn58bjbwrlnaw2wq3hivf23x 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

    alter table LabQualityControlResult 
       add constraint FKhc7spogtqlcdu14vv19ih4tqv 
       foreign key (controlSample_Id) 
       references Sample (Id);

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
       add constraint FKf7w0xssqiu7vy91uvk42qvumk 
       foreign key (patientSample_Id) 
       references Sample (Id);

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

    alter table PatientOrder 
       add constraint FKgcc7dp56rsy3fdovglmx2tlno 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table PatientOrder_AUD 
       add constraint FK570w5utmawcgcvrg7xp3fkjfd 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

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

    alter table QualitativeFormatMethod_resultTemplates 
       add constraint FKsvkk12kvdypx9kp69qba8emk4 
       foreign key (QualitativeFormatMethod_Id) 
       references Method (Id);

    alter table QualitativeFormatMethod_resultTemplates_AUD 
       add constraint FKnq8dn9dr2hxa3d70rf59tkicg 
       foreign key (REV) 
       references REVINFO (REV);

    alter table QuantitativeFormatMethod_refferentialRanges 
       add constraint FKakkpqci9i3lrq4ql9vyxl03lh 
       foreign key (QuantitativeFormatMethod_Id) 
       references Method (Id);

    alter table Sample 
       add constraint FKtcs6g9uujpbeq9eaui0n1qy7a 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table Sample 
       add constraint FKru88e978gj24srmr5bhe11ci7 
       foreign key (patientOrder_Id) 
       references PatientOrder (Id);

    alter table Sample 
       add constraint FK5urc15yfju1sedjs71hx3vwhx 
       foreign key (labQualityControl_Id) 
       references LabQualityControl (Id);

    alter table Sample_AUD 
       add constraint FK7c3l5hxfww0tdps18v0amndd1 
       foreign key (REV) 
       references REVINFO (REV);

    alter table SpecimentType_AUD 
       add constraint FK4jh9vuijlnqx2sftathnhptm0 
       foreign key (REV) 
       references REVINFO (REV);

    create table AbstractAnalyteResult (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        NumberResult decimal(36,18),
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
        TextResult varchar(255),
        NumberResult decimal(36,18),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table AbstractOrder (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table AbstractOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTest (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60) not null,
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
        isActive bit,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTestHistory (
       updateTimeStamp varchar(255) not null,
        cretionTimeStamp datetime(6),
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        laboratoryTest_Id varchar(60) not null,
        primary key (laboratoryTest_Id, updateTimeStamp)
    ) engine=InnoDB;

    create table LabQualityControl (
       description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60) not null,
        reportingDeadLine datetime(6),
        Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table LabQualityControl_AUD (
       Id varchar(60) not null,
        REV integer not null,
        description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60),
        reportingDeadLine datetime(6),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LabQualityControlResult (
       targetValue bit not null,
        Id varchar(60) not null,
        controlSample_Id varchar(60) not null,
        parentTargetValue_Id varchar(60),
        primary key (Id),
        check ((targetValue=TRUE AND parentTargetValue_Id=NULL) OR (targetValue=FALSE))
    ) engine=InnoDB;

    create table LabQualityControlResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        targetValue bit,
        controlSample_Id varchar(60),
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
        labTestOrderStatus varchar(255) not null,
        resultDescription varchar(512),
        tatMode varchar(255) not null,
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
        labTestOrderStatus varchar(255),
        resultDescription varchar(512),
        tatMode varchar(255),
        laboratoryTest_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table Method (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        analyticalMethodType varchar(180) not null,
        printable bit default true,
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        analyte_Id varchar(60) not null,
        laboratoryTest_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table Method_AUD (
       Id varchar(60) not null,
        REV integer not null,
        resultType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit,
        analyticalMethodType varchar(180),
        printable bit,
        analyte_Id varchar(60),
        laboratoryTest_Id varchar(60),
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderingUnit (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
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
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderResult (
       Id varchar(60) not null,
        patientSample_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table OrderResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        patientSample_Id varchar(60),
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
        comment varchar(4096),
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
       orderDate date,
        Id varchar(60) not null,
        orderingUnit_Id varchar(60),
        patient_Id varchar(60) not null,
        phisician_Id varchar(60),
        primary key (Id)
    ) engine=InnoDB;

    create table PatientOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
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

    create table QualitativeFormatMethod_resultTemplates (
       QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255)
    ) engine=InnoDB;

    create table QualitativeFormatMethod_resultTemplates_AUD (
       REV integer not null,
        QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255) not null,
        REVTYPE tinyint,
        primary key (REV, QualitativeFormatMethod_Id, resultTemplates)
    ) engine=InnoDB;

    create table QuantitativeFormatMethod_refferentialRanges (
       QuantitativeFormatMethod_Id varchar(60) not null,
        fromAge bigint,
        gender integer,
        toAge bigint,
        valueFrom decimal(19,2),
        valueTo decimal(19,2),
        refferentialRanges_ORDER integer not null,
        primary key (QuantitativeFormatMethod_Id, refferentialRanges_ORDER)
    ) engine=InnoDB;

    create table REVINFO (
       REV integer not null auto_increment,
        REVTSTMP bigint,
        primary key (REV)
    ) engine=InnoDB;

    create table Sample (
       sampleType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(60) not null,
        specimentType_Id varchar(60) not null,
        patientOrder_Id varchar(60),
        labQualityControl_Id varchar(60),
        primary key (Id),
        check ((sampleType='PATIENT' AND patientOrder_Id IS NOT NULL AND labQualityControl_Id IS NULL) OR (sampleType='CONTROL' AND labQualityControl_Id IS NOT NULL AND patientOrder_Id IS NULL))
    ) engine=InnoDB;

    create table Sample_AUD (
       Id varchar(60) not null,
        REV integer not null,
        sampleType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(255),
        specimentType_Id varchar(60),
        labQualityControl_Id varchar(60),
        patientOrder_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table SpecimentType (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        isActive bit not null,
        speciment varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table SpecimentType_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        isActive bit,
        speciment varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    alter table AbstractOrder 
       add constraint UK_8mc8121is13twuiqwxy15k3g4 unique (orderIdentificationCode);

    alter table Analyte 
       add constraint UK_47x69sl7aoei6c0giwnca5qxt unique (shortName);

    alter table LaboratoryTest 
       add constraint UK_fiqfkklxeqombys4ekliflm47 unique (shortName);

    alter table Patient 
       add constraint personalIdentificationNumber unique (personalIdentificationNumber);

    alter table Phisician 
       add constraint UK_hhhsqecp80pow3qrj66fnkmd7 unique (personalIdentificationNumber);

    alter table Sample 
       add constraint UK_7y5oe0qpo05a5pbgjechxalre unique (sampleId);

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

    alter table AbstractOrder_AUD 
       add constraint FKp5iurhe3l1huersgl4xhd0k82 
       foreign key (REV) 
       references REVINFO (REV);

    alter table Analyte_AUD 
       add constraint FK4k2vc25sg3d5ol920sigottvi 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTest 
       add constraint FKbtrkm84momkbq5lqc8b0gi2ay 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table LaboratoryTest_AUD 
       add constraint FKbbqpm2hy4c1t9qh27ocqnc8ki 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTestHistory 
       add constraint FK1iw9ydv52bxcevjjlwyhhrwm5 
       foreign key (laboratoryTest_Id) 
       references LaboratoryTest (Id);

    alter table LabQualityControl 
       add constraint FK6bfgd24ei1djon6txiukomg60 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table LabQualityControl_AUD 
       add constraint FK5vn58bjbwrlnaw2wq3hivf23x 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

    alter table LabQualityControlResult 
       add constraint FKhc7spogtqlcdu14vv19ih4tqv 
       foreign key (controlSample_Id) 
       references Sample (Id);

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
       add constraint FKf7w0xssqiu7vy91uvk42qvumk 
       foreign key (patientSample_Id) 
       references Sample (Id);

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

    alter table PatientOrder 
       add constraint FKgcc7dp56rsy3fdovglmx2tlno 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table PatientOrder_AUD 
       add constraint FK570w5utmawcgcvrg7xp3fkjfd 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

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

    alter table QualitativeFormatMethod_resultTemplates 
       add constraint FKsvkk12kvdypx9kp69qba8emk4 
       foreign key (QualitativeFormatMethod_Id) 
       references Method (Id);

    alter table QualitativeFormatMethod_resultTemplates_AUD 
       add constraint FKnq8dn9dr2hxa3d70rf59tkicg 
       foreign key (REV) 
       references REVINFO (REV);

    alter table QuantitativeFormatMethod_refferentialRanges 
       add constraint FKakkpqci9i3lrq4ql9vyxl03lh 
       foreign key (QuantitativeFormatMethod_Id) 
       references Method (Id);

    alter table Sample 
       add constraint FKtcs6g9uujpbeq9eaui0n1qy7a 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table Sample 
       add constraint FKru88e978gj24srmr5bhe11ci7 
       foreign key (patientOrder_Id) 
       references PatientOrder (Id);

    alter table Sample 
       add constraint FK5urc15yfju1sedjs71hx3vwhx 
       foreign key (labQualityControl_Id) 
       references LabQualityControl (Id);

    alter table Sample_AUD 
       add constraint FK7c3l5hxfww0tdps18v0amndd1 
       foreign key (REV) 
       references REVINFO (REV);

    alter table SpecimentType_AUD 
       add constraint FK4jh9vuijlnqx2sftathnhptm0 
       foreign key (REV) 
       references REVINFO (REV);

    create table AbstractAnalyteResult (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        NumberResult decimal(36,18),
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
        TextResult varchar(255),
        NumberResult decimal(36,18),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table AbstractOrder (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table AbstractOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTest (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60) not null,
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
        isActive bit,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTestHistory (
       updateTimeStamp varchar(255) not null,
        cretionTimeStamp datetime(6),
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        laboratoryTest_Id varchar(60) not null,
        primary key (laboratoryTest_Id, updateTimeStamp)
    ) engine=InnoDB;

    create table LabQualityControl (
       description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60) not null,
        reportingDeadLine datetime(6),
        Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table LabQualityControl_AUD (
       Id varchar(60) not null,
        REV integer not null,
        description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60),
        reportingDeadLine datetime(6),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LabQualityControlResult (
       targetValue bit not null,
        Id varchar(60) not null,
        controlSample_Id varchar(60) not null,
        parentTargetValue_Id varchar(60),
        primary key (Id),
        check ((targetValue=TRUE AND parentTargetValue_Id=NULL) OR (targetValue=FALSE))
    ) engine=InnoDB;

    create table LabQualityControlResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        targetValue bit,
        controlSample_Id varchar(60),
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
        labTestOrderStatus varchar(255) not null,
        resultDescription varchar(512),
        tatMode varchar(255) not null,
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
        labTestOrderStatus varchar(255),
        resultDescription varchar(512),
        tatMode varchar(255),
        laboratoryTest_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table Method (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        analyticalMethodType varchar(180) not null,
        printable bit default true,
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        analyte_Id varchar(60) not null,
        laboratoryTest_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table Method_AUD (
       Id varchar(60) not null,
        REV integer not null,
        resultType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit,
        analyticalMethodType varchar(180),
        printable bit,
        analyte_Id varchar(60),
        laboratoryTest_Id varchar(60),
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderingUnit (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
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
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderResult (
       Id varchar(60) not null,
        patientSample_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table OrderResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        patientSample_Id varchar(60),
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
        comment varchar(4096),
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
       orderDate date,
        Id varchar(60) not null,
        orderingUnit_Id varchar(60),
        patient_Id varchar(60) not null,
        phisician_Id varchar(60),
        primary key (Id)
    ) engine=InnoDB;

    create table PatientOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
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

    create table QualitativeFormatMethod_resultTemplates (
       QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255)
    ) engine=InnoDB;

    create table QualitativeFormatMethod_resultTemplates_AUD (
       REV integer not null,
        QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255) not null,
        REVTYPE tinyint,
        primary key (REV, QualitativeFormatMethod_Id, resultTemplates)
    ) engine=InnoDB;

    create table QuantitativeFormatMethod_refferentialRanges (
       QuantitativeFormatMethod_Id varchar(60) not null,
        fromAge bigint,
        gender integer,
        toAge bigint,
        valueFrom decimal(19,2),
        valueTo decimal(19,2),
        refferentialRanges_ORDER integer not null,
        primary key (QuantitativeFormatMethod_Id, refferentialRanges_ORDER)
    ) engine=InnoDB;

    create table REVINFO (
       REV integer not null auto_increment,
        REVTSTMP bigint,
        primary key (REV)
    ) engine=InnoDB;

    create table Sample (
       sampleType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(60) not null,
        specimentType_Id varchar(60) not null,
        patientOrder_Id varchar(60),
        labQualityControl_Id varchar(60),
        primary key (Id),
        check ((sampleType='PATIENT' AND patientOrder_Id IS NOT NULL AND labQualityControl_Id IS NULL) OR (sampleType='CONTROL' AND labQualityControl_Id IS NOT NULL AND patientOrder_Id IS NULL))
    ) engine=InnoDB;

    create table Sample_AUD (
       Id varchar(60) not null,
        REV integer not null,
        sampleType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(255),
        specimentType_Id varchar(60),
        labQualityControl_Id varchar(60),
        patientOrder_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table SpecimentType (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        isActive bit not null,
        speciment varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table SpecimentType_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        isActive bit,
        speciment varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    alter table AbstractOrder 
       add constraint UK_8mc8121is13twuiqwxy15k3g4 unique (orderIdentificationCode);

    alter table Analyte 
       add constraint UK_47x69sl7aoei6c0giwnca5qxt unique (shortName);

    alter table LaboratoryTest 
       add constraint UK_fiqfkklxeqombys4ekliflm47 unique (shortName);

    alter table Patient 
       add constraint personalIdentificationNumber unique (personalIdentificationNumber);

    alter table Phisician 
       add constraint UK_hhhsqecp80pow3qrj66fnkmd7 unique (personalIdentificationNumber);

    alter table Sample 
       add constraint UK_7y5oe0qpo05a5pbgjechxalre unique (sampleId);

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

    alter table AbstractOrder_AUD 
       add constraint FKp5iurhe3l1huersgl4xhd0k82 
       foreign key (REV) 
       references REVINFO (REV);

    alter table Analyte_AUD 
       add constraint FK4k2vc25sg3d5ol920sigottvi 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTest 
       add constraint FKbtrkm84momkbq5lqc8b0gi2ay 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table LaboratoryTest_AUD 
       add constraint FKbbqpm2hy4c1t9qh27ocqnc8ki 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTestHistory 
       add constraint FK1iw9ydv52bxcevjjlwyhhrwm5 
       foreign key (laboratoryTest_Id) 
       references LaboratoryTest (Id);

    alter table LabQualityControl 
       add constraint FK6bfgd24ei1djon6txiukomg60 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table LabQualityControl_AUD 
       add constraint FK5vn58bjbwrlnaw2wq3hivf23x 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

    alter table LabQualityControlResult 
       add constraint FKhc7spogtqlcdu14vv19ih4tqv 
       foreign key (controlSample_Id) 
       references Sample (Id);

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
       add constraint FKf7w0xssqiu7vy91uvk42qvumk 
       foreign key (patientSample_Id) 
       references Sample (Id);

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

    alter table PatientOrder 
       add constraint FKgcc7dp56rsy3fdovglmx2tlno 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table PatientOrder_AUD 
       add constraint FK570w5utmawcgcvrg7xp3fkjfd 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

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

    alter table QualitativeFormatMethod_resultTemplates 
       add constraint FKsvkk12kvdypx9kp69qba8emk4 
       foreign key (QualitativeFormatMethod_Id) 
       references Method (Id);

    alter table QualitativeFormatMethod_resultTemplates_AUD 
       add constraint FKnq8dn9dr2hxa3d70rf59tkicg 
       foreign key (REV) 
       references REVINFO (REV);

    alter table QuantitativeFormatMethod_refferentialRanges 
       add constraint FKakkpqci9i3lrq4ql9vyxl03lh 
       foreign key (QuantitativeFormatMethod_Id) 
       references Method (Id);

    alter table Sample 
       add constraint FKtcs6g9uujpbeq9eaui0n1qy7a 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table Sample 
       add constraint FKru88e978gj24srmr5bhe11ci7 
       foreign key (patientOrder_Id) 
       references PatientOrder (Id);

    alter table Sample 
       add constraint FK5urc15yfju1sedjs71hx3vwhx 
       foreign key (labQualityControl_Id) 
       references LabQualityControl (Id);

    alter table Sample_AUD 
       add constraint FK7c3l5hxfww0tdps18v0amndd1 
       foreign key (REV) 
       references REVINFO (REV);

    alter table SpecimentType_AUD 
       add constraint FK4jh9vuijlnqx2sftathnhptm0 
       foreign key (REV) 
       references REVINFO (REV);

    create table AbstractAnalyteResult (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        NumberResult decimal(36,18),
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
        TextResult varchar(255),
        NumberResult decimal(36,18),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table AbstractOrder (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table AbstractOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTest (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60) not null,
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
        isActive bit,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTestHistory (
       updateTimeStamp varchar(255) not null,
        cretionTimeStamp datetime(6),
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        laboratoryTest_Id varchar(60) not null,
        primary key (laboratoryTest_Id, updateTimeStamp)
    ) engine=InnoDB;

    create table LabQualityControl (
       description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60) not null,
        reportingDeadLine datetime(6),
        Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table LabQualityControl_AUD (
       Id varchar(60) not null,
        REV integer not null,
        description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60),
        reportingDeadLine datetime(6),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LabQualityControlResult (
       targetValue bit not null,
        Id varchar(60) not null,
        controlSample_Id varchar(60) not null,
        parentTargetValue_Id varchar(60),
        primary key (Id),
        check ((targetValue=TRUE AND parentTargetValue_Id=NULL) OR (targetValue=FALSE))
    ) engine=InnoDB;

    create table LabQualityControlResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        targetValue bit,
        controlSample_Id varchar(60),
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
        labTestOrderStatus varchar(255) not null,
        resultDescription varchar(512),
        tatMode varchar(255) not null,
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
        labTestOrderStatus varchar(255),
        resultDescription varchar(512),
        tatMode varchar(255),
        laboratoryTest_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table Method (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        analyticalMethodType varchar(180) not null,
        printable bit default true,
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        analyte_Id varchar(60) not null,
        laboratoryTest_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table Method_AUD (
       Id varchar(60) not null,
        REV integer not null,
        resultType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit,
        analyticalMethodType varchar(180),
        printable bit,
        analyte_Id varchar(60),
        laboratoryTest_Id varchar(60),
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderingUnit (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
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
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderResult (
       Id varchar(60) not null,
        patientSample_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table OrderResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        patientSample_Id varchar(60),
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
        comment varchar(4096),
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
       orderDate date,
        Id varchar(60) not null,
        orderingUnit_Id varchar(60),
        patient_Id varchar(60) not null,
        phisician_Id varchar(60),
        primary key (Id)
    ) engine=InnoDB;

    create table PatientOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
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

    create table QualitativeFormatMethod_resultTemplates (
       QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255)
    ) engine=InnoDB;

    create table QualitativeFormatMethod_resultTemplates_AUD (
       REV integer not null,
        QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255) not null,
        REVTYPE tinyint,
        primary key (REV, QualitativeFormatMethod_Id, resultTemplates)
    ) engine=InnoDB;

    create table QuantitativeFormatMethod_refferentialRanges (
       QuantitativeFormatMethod_Id varchar(60) not null,
        fromAge bigint,
        gender integer,
        toAge bigint,
        valueFrom decimal(19,2),
        valueTo decimal(19,2),
        refferentialRanges_ORDER integer not null,
        primary key (QuantitativeFormatMethod_Id, refferentialRanges_ORDER)
    ) engine=InnoDB;

    create table REVINFO (
       REV integer not null auto_increment,
        REVTSTMP bigint,
        primary key (REV)
    ) engine=InnoDB;

    create table Sample (
       sampleType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(60) not null,
        specimentType_Id varchar(60) not null,
        patientOrder_Id varchar(60),
        labQualityControl_Id varchar(60),
        primary key (Id),
        check ((sampleType='PATIENT' AND patientOrder_Id IS NOT NULL AND labQualityControl_Id IS NULL) OR (sampleType='CONTROL' AND labQualityControl_Id IS NOT NULL AND patientOrder_Id IS NULL))
    ) engine=InnoDB;

    create table Sample_AUD (
       Id varchar(60) not null,
        REV integer not null,
        sampleType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(255),
        specimentType_Id varchar(60),
        labQualityControl_Id varchar(60),
        patientOrder_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table SpecimentType (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        isActive bit not null,
        speciment varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table SpecimentType_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        isActive bit,
        speciment varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    alter table AbstractOrder 
       add constraint UK_8mc8121is13twuiqwxy15k3g4 unique (orderIdentificationCode);

    alter table Analyte 
       add constraint UK_47x69sl7aoei6c0giwnca5qxt unique (shortName);

    alter table LaboratoryTest 
       add constraint UK_fiqfkklxeqombys4ekliflm47 unique (shortName);

    alter table Patient 
       add constraint personalIdentificationNumber unique (personalIdentificationNumber);

    alter table Phisician 
       add constraint UK_hhhsqecp80pow3qrj66fnkmd7 unique (personalIdentificationNumber);

    alter table Sample 
       add constraint UK_7y5oe0qpo05a5pbgjechxalre unique (sampleId);

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

    alter table AbstractOrder_AUD 
       add constraint FKp5iurhe3l1huersgl4xhd0k82 
       foreign key (REV) 
       references REVINFO (REV);

    alter table Analyte_AUD 
       add constraint FK4k2vc25sg3d5ol920sigottvi 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTest 
       add constraint FKbtrkm84momkbq5lqc8b0gi2ay 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table LaboratoryTest_AUD 
       add constraint FKbbqpm2hy4c1t9qh27ocqnc8ki 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTestHistory 
       add constraint FK1iw9ydv52bxcevjjlwyhhrwm5 
       foreign key (laboratoryTest_Id) 
       references LaboratoryTest (Id);

    alter table LabQualityControl 
       add constraint FK6bfgd24ei1djon6txiukomg60 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table LabQualityControl_AUD 
       add constraint FK5vn58bjbwrlnaw2wq3hivf23x 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

    alter table LabQualityControlResult 
       add constraint FKhc7spogtqlcdu14vv19ih4tqv 
       foreign key (controlSample_Id) 
       references Sample (Id);

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
       add constraint FKf7w0xssqiu7vy91uvk42qvumk 
       foreign key (patientSample_Id) 
       references Sample (Id);

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

    alter table PatientOrder 
       add constraint FKgcc7dp56rsy3fdovglmx2tlno 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table PatientOrder_AUD 
       add constraint FK570w5utmawcgcvrg7xp3fkjfd 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

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

    alter table QualitativeFormatMethod_resultTemplates 
       add constraint FKsvkk12kvdypx9kp69qba8emk4 
       foreign key (QualitativeFormatMethod_Id) 
       references Method (Id);

    alter table QualitativeFormatMethod_resultTemplates_AUD 
       add constraint FKnq8dn9dr2hxa3d70rf59tkicg 
       foreign key (REV) 
       references REVINFO (REV);

    alter table QuantitativeFormatMethod_refferentialRanges 
       add constraint FKakkpqci9i3lrq4ql9vyxl03lh 
       foreign key (QuantitativeFormatMethod_Id) 
       references Method (Id);

    alter table Sample 
       add constraint FKtcs6g9uujpbeq9eaui0n1qy7a 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table Sample 
       add constraint FKru88e978gj24srmr5bhe11ci7 
       foreign key (patientOrder_Id) 
       references PatientOrder (Id);

    alter table Sample 
       add constraint FK5urc15yfju1sedjs71hx3vwhx 
       foreign key (labQualityControl_Id) 
       references LabQualityControl (Id);

    alter table Sample_AUD 
       add constraint FK7c3l5hxfww0tdps18v0amndd1 
       foreign key (REV) 
       references REVINFO (REV);

    alter table SpecimentType_AUD 
       add constraint FK4jh9vuijlnqx2sftathnhptm0 
       foreign key (REV) 
       references REVINFO (REV);

    create table AbstractAnalyteResult (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        NumberResult decimal(36,18),
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
        TextResult varchar(255),
        NumberResult decimal(36,18),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table AbstractOrder (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table AbstractOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTest (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60) not null,
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
        isActive bit,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTestHistory (
       updateTimeStamp varchar(255) not null,
        cretionTimeStamp datetime(6),
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        laboratoryTest_Id varchar(60) not null,
        primary key (laboratoryTest_Id, updateTimeStamp)
    ) engine=InnoDB;

    create table LabQualityControl (
       description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60) not null,
        reportingDeadLine datetime(6),
        Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table LabQualityControl_AUD (
       Id varchar(60) not null,
        REV integer not null,
        description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60),
        reportingDeadLine datetime(6),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LabQualityControlResult (
       targetValue bit not null,
        Id varchar(60) not null,
        controlSample_Id varchar(60) not null,
        parentTargetValue_Id varchar(60),
        primary key (Id),
        check ((targetValue=TRUE AND parentTargetValue_Id=NULL) OR (targetValue=FALSE))
    ) engine=InnoDB;

    create table LabQualityControlResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        targetValue bit,
        controlSample_Id varchar(60),
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
        labTestOrderStatus varchar(255) not null,
        resultDescription varchar(512),
        tatMode varchar(255) not null,
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
        labTestOrderStatus varchar(255),
        resultDescription varchar(512),
        tatMode varchar(255),
        laboratoryTest_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table Method (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        analyticalMethodType varchar(180) not null,
        printable bit default true,
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        analyte_Id varchar(60) not null,
        laboratoryTest_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table Method_AUD (
       Id varchar(60) not null,
        REV integer not null,
        resultType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit,
        analyticalMethodType varchar(180),
        printable bit,
        analyte_Id varchar(60),
        laboratoryTest_Id varchar(60),
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderingUnit (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
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
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderResult (
       Id varchar(60) not null,
        patientSample_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table OrderResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        patientSample_Id varchar(60),
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
        comment varchar(4096),
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
       orderDate date,
        Id varchar(60) not null,
        orderingUnit_Id varchar(60),
        patient_Id varchar(60) not null,
        phisician_Id varchar(60),
        primary key (Id)
    ) engine=InnoDB;

    create table PatientOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
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

    create table QualitativeFormatMethod_resultTemplates (
       QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255)
    ) engine=InnoDB;

    create table QualitativeFormatMethod_resultTemplates_AUD (
       REV integer not null,
        QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255) not null,
        REVTYPE tinyint,
        primary key (REV, QualitativeFormatMethod_Id, resultTemplates)
    ) engine=InnoDB;

    create table QuantitativeFormatMethod_refferentialRanges (
       QuantitativeFormatMethod_Id varchar(60) not null,
        fromAge bigint,
        gender integer,
        toAge bigint,
        valueFrom decimal(19,2),
        valueTo decimal(19,2),
        refferentialRanges_ORDER integer not null,
        primary key (QuantitativeFormatMethod_Id, refferentialRanges_ORDER)
    ) engine=InnoDB;

    create table REVINFO (
       REV integer not null auto_increment,
        REVTSTMP bigint,
        primary key (REV)
    ) engine=InnoDB;

    create table Sample (
       sampleType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(60) not null,
        specimentType_Id varchar(60) not null,
        patientOrder_Id varchar(60),
        labQualityControl_Id varchar(60),
        primary key (Id),
        check ((sampleType='PATIENT' AND patientOrder_Id IS NOT NULL AND labQualityControl_Id IS NULL) OR (sampleType='CONTROL' AND labQualityControl_Id IS NOT NULL AND patientOrder_Id IS NULL))
    ) engine=InnoDB;

    create table Sample_AUD (
       Id varchar(60) not null,
        REV integer not null,
        sampleType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(255),
        specimentType_Id varchar(60),
        labQualityControl_Id varchar(60),
        patientOrder_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table SpecimentType (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        isActive bit not null,
        speciment varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table SpecimentType_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        isActive bit,
        speciment varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    alter table AbstractOrder 
       add constraint UK_8mc8121is13twuiqwxy15k3g4 unique (orderIdentificationCode);

    alter table Analyte 
       add constraint UK_47x69sl7aoei6c0giwnca5qxt unique (shortName);

    alter table LaboratoryTest 
       add constraint UK_fiqfkklxeqombys4ekliflm47 unique (shortName);

    alter table Patient 
       add constraint personalIdentificationNumber unique (personalIdentificationNumber);

    alter table Phisician 
       add constraint UK_hhhsqecp80pow3qrj66fnkmd7 unique (personalIdentificationNumber);

    alter table Sample 
       add constraint UK_7y5oe0qpo05a5pbgjechxalre unique (sampleId);

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

    alter table AbstractOrder_AUD 
       add constraint FKp5iurhe3l1huersgl4xhd0k82 
       foreign key (REV) 
       references REVINFO (REV);

    alter table Analyte_AUD 
       add constraint FK4k2vc25sg3d5ol920sigottvi 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTest 
       add constraint FKbtrkm84momkbq5lqc8b0gi2ay 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table LaboratoryTest_AUD 
       add constraint FKbbqpm2hy4c1t9qh27ocqnc8ki 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTestHistory 
       add constraint FK1iw9ydv52bxcevjjlwyhhrwm5 
       foreign key (laboratoryTest_Id) 
       references LaboratoryTest (Id);

    alter table LabQualityControl 
       add constraint FK6bfgd24ei1djon6txiukomg60 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table LabQualityControl_AUD 
       add constraint FK5vn58bjbwrlnaw2wq3hivf23x 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

    alter table LabQualityControlResult 
       add constraint FKhc7spogtqlcdu14vv19ih4tqv 
       foreign key (controlSample_Id) 
       references Sample (Id);

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
       add constraint FKf7w0xssqiu7vy91uvk42qvumk 
       foreign key (patientSample_Id) 
       references Sample (Id);

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

    alter table PatientOrder 
       add constraint FKgcc7dp56rsy3fdovglmx2tlno 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table PatientOrder_AUD 
       add constraint FK570w5utmawcgcvrg7xp3fkjfd 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

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

    alter table QualitativeFormatMethod_resultTemplates 
       add constraint FKsvkk12kvdypx9kp69qba8emk4 
       foreign key (QualitativeFormatMethod_Id) 
       references Method (Id);

    alter table QualitativeFormatMethod_resultTemplates_AUD 
       add constraint FKnq8dn9dr2hxa3d70rf59tkicg 
       foreign key (REV) 
       references REVINFO (REV);

    alter table QuantitativeFormatMethod_refferentialRanges 
       add constraint FKakkpqci9i3lrq4ql9vyxl03lh 
       foreign key (QuantitativeFormatMethod_Id) 
       references Method (Id);

    alter table Sample 
       add constraint FKtcs6g9uujpbeq9eaui0n1qy7a 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table Sample 
       add constraint FKru88e978gj24srmr5bhe11ci7 
       foreign key (patientOrder_Id) 
       references PatientOrder (Id);

    alter table Sample 
       add constraint FK5urc15yfju1sedjs71hx3vwhx 
       foreign key (labQualityControl_Id) 
       references LabQualityControl (Id);

    alter table Sample_AUD 
       add constraint FK7c3l5hxfww0tdps18v0amndd1 
       foreign key (REV) 
       references REVINFO (REV);

    alter table SpecimentType_AUD 
       add constraint FK4jh9vuijlnqx2sftathnhptm0 
       foreign key (REV) 
       references REVINFO (REV);

    create table AbstractAnalyteResult (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        NumberResult decimal(36,18),
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
        TextResult varchar(255),
        NumberResult decimal(36,18),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table AbstractOrder (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table AbstractOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTest (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60) not null,
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
        isActive bit,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTestHistory (
       updateTimeStamp varchar(255) not null,
        cretionTimeStamp datetime(6),
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        laboratoryTest_Id varchar(60) not null,
        primary key (laboratoryTest_Id, updateTimeStamp)
    ) engine=InnoDB;

    create table LabQualityControl (
       description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60) not null,
        reportingDeadLine datetime(6),
        Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table LabQualityControl_AUD (
       Id varchar(60) not null,
        REV integer not null,
        description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60),
        reportingDeadLine datetime(6),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LabQualityControlResult (
       targetValue bit not null,
        Id varchar(60) not null,
        controlSample_Id varchar(60) not null,
        parentTargetValue_Id varchar(60),
        primary key (Id),
        check ((targetValue=TRUE AND parentTargetValue_Id=NULL) OR (targetValue=FALSE))
    ) engine=InnoDB;

    create table LabQualityControlResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        targetValue bit,
        controlSample_Id varchar(60),
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
        labTestOrderStatus varchar(255) not null,
        resultDescription varchar(512),
        tatMode varchar(255) not null,
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
        labTestOrderStatus varchar(255),
        resultDescription varchar(512),
        tatMode varchar(255),
        laboratoryTest_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table Method (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        analyticalMethodType varchar(180) not null,
        printable bit default true,
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        analyte_Id varchar(60) not null,
        laboratoryTest_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table Method_AUD (
       Id varchar(60) not null,
        REV integer not null,
        resultType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit,
        analyticalMethodType varchar(180),
        printable bit,
        analyte_Id varchar(60),
        laboratoryTest_Id varchar(60),
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderingUnit (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
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
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderResult (
       Id varchar(60) not null,
        patientSample_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table OrderResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        patientSample_Id varchar(60),
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
        comment varchar(4096),
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
       orderDate date,
        Id varchar(60) not null,
        orderingUnit_Id varchar(60),
        patient_Id varchar(60) not null,
        phisician_Id varchar(60),
        primary key (Id)
    ) engine=InnoDB;

    create table PatientOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
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

    create table QualitativeFormatMethod_resultTemplates (
       QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255)
    ) engine=InnoDB;

    create table QualitativeFormatMethod_resultTemplates_AUD (
       REV integer not null,
        QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255) not null,
        REVTYPE tinyint,
        primary key (REV, QualitativeFormatMethod_Id, resultTemplates)
    ) engine=InnoDB;

    create table QuantitativeFormatMethod_refferentialRanges (
       QuantitativeFormatMethod_Id varchar(60) not null,
        fromAge bigint,
        gender integer,
        toAge bigint,
        valueFrom decimal(19,2),
        valueTo decimal(19,2),
        refferentialRanges_ORDER integer not null,
        primary key (QuantitativeFormatMethod_Id, refferentialRanges_ORDER)
    ) engine=InnoDB;

    create table REVINFO (
       REV integer not null auto_increment,
        REVTSTMP bigint,
        primary key (REV)
    ) engine=InnoDB;

    create table Sample (
       sampleType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(60) not null,
        specimentType_Id varchar(60) not null,
        patientOrder_Id varchar(60),
        labQualityControl_Id varchar(60),
        primary key (Id),
        check ((sampleType='PATIENT' AND patientOrder_Id IS NOT NULL AND labQualityControl_Id IS NULL) OR (sampleType='CONTROL' AND labQualityControl_Id IS NOT NULL AND patientOrder_Id IS NULL))
    ) engine=InnoDB;

    create table Sample_AUD (
       Id varchar(60) not null,
        REV integer not null,
        sampleType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(255),
        specimentType_Id varchar(60),
        labQualityControl_Id varchar(60),
        patientOrder_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table SpecimentType (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        isActive bit not null,
        speciment varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table SpecimentType_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        isActive bit,
        speciment varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    alter table AbstractOrder 
       add constraint UK_8mc8121is13twuiqwxy15k3g4 unique (orderIdentificationCode);

    alter table Analyte 
       add constraint UK_47x69sl7aoei6c0giwnca5qxt unique (shortName);

    alter table LaboratoryTest 
       add constraint UK_fiqfkklxeqombys4ekliflm47 unique (shortName);

    alter table Patient 
       add constraint personalIdentificationNumber unique (personalIdentificationNumber);

    alter table Phisician 
       add constraint UK_hhhsqecp80pow3qrj66fnkmd7 unique (personalIdentificationNumber);

    alter table Sample 
       add constraint UK_7y5oe0qpo05a5pbgjechxalre unique (sampleId);

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

    alter table AbstractOrder_AUD 
       add constraint FKp5iurhe3l1huersgl4xhd0k82 
       foreign key (REV) 
       references REVINFO (REV);

    alter table Analyte_AUD 
       add constraint FK4k2vc25sg3d5ol920sigottvi 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTest 
       add constraint FKbtrkm84momkbq5lqc8b0gi2ay 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table LaboratoryTest_AUD 
       add constraint FKbbqpm2hy4c1t9qh27ocqnc8ki 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTestHistory 
       add constraint FK1iw9ydv52bxcevjjlwyhhrwm5 
       foreign key (laboratoryTest_Id) 
       references LaboratoryTest (Id);

    alter table LabQualityControl 
       add constraint FK6bfgd24ei1djon6txiukomg60 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table LabQualityControl_AUD 
       add constraint FK5vn58bjbwrlnaw2wq3hivf23x 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

    alter table LabQualityControlResult 
       add constraint FKhc7spogtqlcdu14vv19ih4tqv 
       foreign key (controlSample_Id) 
       references Sample (Id);

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
       add constraint FKf7w0xssqiu7vy91uvk42qvumk 
       foreign key (patientSample_Id) 
       references Sample (Id);

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

    alter table PatientOrder 
       add constraint FKgcc7dp56rsy3fdovglmx2tlno 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table PatientOrder_AUD 
       add constraint FK570w5utmawcgcvrg7xp3fkjfd 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

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

    alter table QualitativeFormatMethod_resultTemplates 
       add constraint FKsvkk12kvdypx9kp69qba8emk4 
       foreign key (QualitativeFormatMethod_Id) 
       references Method (Id);

    alter table QualitativeFormatMethod_resultTemplates_AUD 
       add constraint FKnq8dn9dr2hxa3d70rf59tkicg 
       foreign key (REV) 
       references REVINFO (REV);

    alter table QuantitativeFormatMethod_refferentialRanges 
       add constraint FKakkpqci9i3lrq4ql9vyxl03lh 
       foreign key (QuantitativeFormatMethod_Id) 
       references Method (Id);

    alter table Sample 
       add constraint FKtcs6g9uujpbeq9eaui0n1qy7a 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table Sample 
       add constraint FKru88e978gj24srmr5bhe11ci7 
       foreign key (patientOrder_Id) 
       references PatientOrder (Id);

    alter table Sample 
       add constraint FK5urc15yfju1sedjs71hx3vwhx 
       foreign key (labQualityControl_Id) 
       references LabQualityControl (Id);

    alter table Sample_AUD 
       add constraint FK7c3l5hxfww0tdps18v0amndd1 
       foreign key (REV) 
       references REVINFO (REV);

    alter table SpecimentType_AUD 
       add constraint FK4jh9vuijlnqx2sftathnhptm0 
       foreign key (REV) 
       references REVINFO (REV);

    create table AbstractAnalyteResult (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        NumberResult decimal(36,18),
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
        TextResult varchar(255),
        NumberResult decimal(36,18),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table AbstractOrder (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table AbstractOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTest (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60) not null,
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
        isActive bit,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTestHistory (
       updateTimeStamp varchar(255) not null,
        cretionTimeStamp datetime(6),
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        laboratoryTest_Id varchar(60) not null,
        primary key (laboratoryTest_Id, updateTimeStamp)
    ) engine=InnoDB;

    create table LabQualityControl (
       description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60) not null,
        reportingDeadLine datetime(6),
        Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table LabQualityControl_AUD (
       Id varchar(60) not null,
        REV integer not null,
        description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60),
        reportingDeadLine datetime(6),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LabQualityControlResult (
       targetValue bit not null,
        Id varchar(60) not null,
        controlSample_Id varchar(60) not null,
        parentTargetValue_Id varchar(60),
        primary key (Id),
        check ((targetValue=TRUE AND parentTargetValue_Id=NULL) OR (targetValue=FALSE))
    ) engine=InnoDB;

    create table LabQualityControlResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        targetValue bit,
        controlSample_Id varchar(60),
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
        labTestOrderStatus varchar(255) not null,
        resultDescription varchar(512),
        tatMode varchar(255) not null,
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
        labTestOrderStatus varchar(255),
        resultDescription varchar(512),
        tatMode varchar(255),
        laboratoryTest_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table Method (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        analyticalMethodType varchar(180) not null,
        printable bit default true,
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        analyte_Id varchar(60) not null,
        laboratoryTest_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table Method_AUD (
       Id varchar(60) not null,
        REV integer not null,
        resultType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit,
        analyticalMethodType varchar(180),
        printable bit,
        analyte_Id varchar(60),
        laboratoryTest_Id varchar(60),
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderingUnit (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
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
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderResult (
       Id varchar(60) not null,
        patientSample_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table OrderResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        patientSample_Id varchar(60),
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
        comment varchar(4096),
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
       orderDate date,
        Id varchar(60) not null,
        orderingUnit_Id varchar(60),
        patient_Id varchar(60) not null,
        phisician_Id varchar(60),
        primary key (Id)
    ) engine=InnoDB;

    create table PatientOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
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

    create table QualitativeFormatMethod_resultTemplates (
       QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255)
    ) engine=InnoDB;

    create table QualitativeFormatMethod_resultTemplates_AUD (
       REV integer not null,
        QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255) not null,
        REVTYPE tinyint,
        primary key (REV, QualitativeFormatMethod_Id, resultTemplates)
    ) engine=InnoDB;

    create table QuantitativeFormatMethod_refferentialRanges (
       QuantitativeFormatMethod_Id varchar(60) not null,
        fromAge bigint,
        gender integer,
        toAge bigint,
        valueFrom decimal(19,2),
        valueTo decimal(19,2),
        refferentialRanges_ORDER integer not null,
        primary key (QuantitativeFormatMethod_Id, refferentialRanges_ORDER)
    ) engine=InnoDB;

    create table REVINFO (
       REV integer not null auto_increment,
        REVTSTMP bigint,
        primary key (REV)
    ) engine=InnoDB;

    create table Sample (
       sampleType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(60) not null,
        specimentType_Id varchar(60) not null,
        patientOrder_Id varchar(60),
        labQualityControl_Id varchar(60),
        primary key (Id),
        check ((sampleType='PATIENT' AND patientOrder_Id IS NOT NULL AND labQualityControl_Id IS NULL) OR (sampleType='CONTROL' AND labQualityControl_Id IS NOT NULL AND patientOrder_Id IS NULL))
    ) engine=InnoDB;

    create table Sample_AUD (
       Id varchar(60) not null,
        REV integer not null,
        sampleType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(255),
        specimentType_Id varchar(60),
        labQualityControl_Id varchar(60),
        patientOrder_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table SpecimentType (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        isActive bit not null,
        speciment varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table SpecimentType_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        isActive bit,
        speciment varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    alter table AbstractOrder 
       add constraint UK_8mc8121is13twuiqwxy15k3g4 unique (orderIdentificationCode);

    alter table Analyte 
       add constraint UK_47x69sl7aoei6c0giwnca5qxt unique (shortName);

    alter table LaboratoryTest 
       add constraint UK_fiqfkklxeqombys4ekliflm47 unique (shortName);

    alter table Patient 
       add constraint personalIdentificationNumber unique (personalIdentificationNumber);

    alter table Phisician 
       add constraint UK_hhhsqecp80pow3qrj66fnkmd7 unique (personalIdentificationNumber);

    alter table Sample 
       add constraint UK_7y5oe0qpo05a5pbgjechxalre unique (sampleId);

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

    alter table AbstractOrder_AUD 
       add constraint FKp5iurhe3l1huersgl4xhd0k82 
       foreign key (REV) 
       references REVINFO (REV);

    alter table Analyte_AUD 
       add constraint FK4k2vc25sg3d5ol920sigottvi 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTest 
       add constraint FKbtrkm84momkbq5lqc8b0gi2ay 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table LaboratoryTest_AUD 
       add constraint FKbbqpm2hy4c1t9qh27ocqnc8ki 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTestHistory 
       add constraint FK1iw9ydv52bxcevjjlwyhhrwm5 
       foreign key (laboratoryTest_Id) 
       references LaboratoryTest (Id);

    alter table LabQualityControl 
       add constraint FK6bfgd24ei1djon6txiukomg60 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table LabQualityControl_AUD 
       add constraint FK5vn58bjbwrlnaw2wq3hivf23x 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

    alter table LabQualityControlResult 
       add constraint FKhc7spogtqlcdu14vv19ih4tqv 
       foreign key (controlSample_Id) 
       references Sample (Id);

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
       add constraint FKf7w0xssqiu7vy91uvk42qvumk 
       foreign key (patientSample_Id) 
       references Sample (Id);

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

    alter table PatientOrder 
       add constraint FKgcc7dp56rsy3fdovglmx2tlno 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table PatientOrder_AUD 
       add constraint FK570w5utmawcgcvrg7xp3fkjfd 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

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

    alter table QualitativeFormatMethod_resultTemplates 
       add constraint FKsvkk12kvdypx9kp69qba8emk4 
       foreign key (QualitativeFormatMethod_Id) 
       references Method (Id);

    alter table QualitativeFormatMethod_resultTemplates_AUD 
       add constraint FKnq8dn9dr2hxa3d70rf59tkicg 
       foreign key (REV) 
       references REVINFO (REV);

    alter table QuantitativeFormatMethod_refferentialRanges 
       add constraint FKakkpqci9i3lrq4ql9vyxl03lh 
       foreign key (QuantitativeFormatMethod_Id) 
       references Method (Id);

    alter table Sample 
       add constraint FKtcs6g9uujpbeq9eaui0n1qy7a 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table Sample 
       add constraint FKru88e978gj24srmr5bhe11ci7 
       foreign key (patientOrder_Id) 
       references PatientOrder (Id);

    alter table Sample 
       add constraint FK5urc15yfju1sedjs71hx3vwhx 
       foreign key (labQualityControl_Id) 
       references LabQualityControl (Id);

    alter table Sample_AUD 
       add constraint FK7c3l5hxfww0tdps18v0amndd1 
       foreign key (REV) 
       references REVINFO (REV);

    alter table SpecimentType_AUD 
       add constraint FK4jh9vuijlnqx2sftathnhptm0 
       foreign key (REV) 
       references REVINFO (REV);

    create table AbstractAnalyteResult (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        NumberResult decimal(36,18),
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
        TextResult varchar(255),
        NumberResult decimal(36,18),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table AbstractOrder (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table AbstractOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTest (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60) not null,
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
        isActive bit,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTestHistory (
       updateTimeStamp varchar(255) not null,
        cretionTimeStamp datetime(6),
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        laboratoryTest_Id varchar(60) not null,
        primary key (laboratoryTest_Id, updateTimeStamp)
    ) engine=InnoDB;

    create table LabQualityControl (
       description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60) not null,
        reportingDeadLine datetime(6),
        Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table LabQualityControl_AUD (
       Id varchar(60) not null,
        REV integer not null,
        description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60),
        reportingDeadLine datetime(6),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LabQualityControlResult (
       targetValue bit not null,
        Id varchar(60) not null,
        controlSample_Id varchar(60) not null,
        parentTargetValue_Id varchar(60),
        primary key (Id),
        check ((targetValue=TRUE AND parentTargetValue_Id=NULL) OR (targetValue=FALSE))
    ) engine=InnoDB;

    create table LabQualityControlResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        targetValue bit,
        controlSample_Id varchar(60),
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
        labTestOrderStatus varchar(255) not null,
        resultDescription varchar(512),
        tatMode varchar(255) not null,
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
        labTestOrderStatus varchar(255),
        resultDescription varchar(512),
        tatMode varchar(255),
        laboratoryTest_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table Method (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        analyticalMethodType varchar(180) not null,
        printable bit default true,
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        analyte_Id varchar(60) not null,
        laboratoryTest_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table Method_AUD (
       Id varchar(60) not null,
        REV integer not null,
        resultType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit,
        analyticalMethodType varchar(180),
        printable bit,
        analyte_Id varchar(60),
        laboratoryTest_Id varchar(60),
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderingUnit (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
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
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderResult (
       Id varchar(60) not null,
        patientSample_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table OrderResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        patientSample_Id varchar(60),
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
        comment varchar(4096),
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
       orderDate date,
        Id varchar(60) not null,
        orderingUnit_Id varchar(60),
        patient_Id varchar(60) not null,
        phisician_Id varchar(60),
        primary key (Id)
    ) engine=InnoDB;

    create table PatientOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
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

    create table QualitativeFormatMethod_resultTemplates (
       QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255)
    ) engine=InnoDB;

    create table QualitativeFormatMethod_resultTemplates_AUD (
       REV integer not null,
        QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255) not null,
        REVTYPE tinyint,
        primary key (REV, QualitativeFormatMethod_Id, resultTemplates)
    ) engine=InnoDB;

    create table QuantitativeFormatMethod_refferentialRanges (
       QuantitativeFormatMethod_Id varchar(60) not null,
        fromAge bigint,
        gender integer,
        toAge bigint,
        valueFrom decimal(19,2),
        valueTo decimal(19,2),
        refferentialRanges_ORDER integer not null,
        primary key (QuantitativeFormatMethod_Id, refferentialRanges_ORDER)
    ) engine=InnoDB;

    create table REVINFO (
       REV integer not null auto_increment,
        REVTSTMP bigint,
        primary key (REV)
    ) engine=InnoDB;

    create table Sample (
       sampleType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(60) not null,
        specimentType_Id varchar(60) not null,
        patientOrder_Id varchar(60),
        labQualityControl_Id varchar(60),
        primary key (Id),
        check ((sampleType='PATIENT' AND patientOrder_Id IS NOT NULL AND labQualityControl_Id IS NULL) OR (sampleType='CONTROL' AND labQualityControl_Id IS NOT NULL AND patientOrder_Id IS NULL))
    ) engine=InnoDB;

    create table Sample_AUD (
       Id varchar(60) not null,
        REV integer not null,
        sampleType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(255),
        specimentType_Id varchar(60),
        labQualityControl_Id varchar(60),
        patientOrder_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table SpecimentType (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        isActive bit not null,
        speciment varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table SpecimentType_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        isActive bit,
        speciment varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    alter table AbstractOrder 
       add constraint UK_8mc8121is13twuiqwxy15k3g4 unique (orderIdentificationCode);

    alter table Analyte 
       add constraint UK_47x69sl7aoei6c0giwnca5qxt unique (shortName);

    alter table LaboratoryTest 
       add constraint UK_fiqfkklxeqombys4ekliflm47 unique (shortName);

    alter table Patient 
       add constraint personalIdentificationNumber unique (personalIdentificationNumber);

    alter table Phisician 
       add constraint UK_hhhsqecp80pow3qrj66fnkmd7 unique (personalIdentificationNumber);

    alter table Sample 
       add constraint UK_7y5oe0qpo05a5pbgjechxalre unique (sampleId);

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

    alter table AbstractOrder_AUD 
       add constraint FKp5iurhe3l1huersgl4xhd0k82 
       foreign key (REV) 
       references REVINFO (REV);

    alter table Analyte_AUD 
       add constraint FK4k2vc25sg3d5ol920sigottvi 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTest 
       add constraint FKbtrkm84momkbq5lqc8b0gi2ay 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table LaboratoryTest_AUD 
       add constraint FKbbqpm2hy4c1t9qh27ocqnc8ki 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTestHistory 
       add constraint FK1iw9ydv52bxcevjjlwyhhrwm5 
       foreign key (laboratoryTest_Id) 
       references LaboratoryTest (Id);

    alter table LabQualityControl 
       add constraint FK6bfgd24ei1djon6txiukomg60 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table LabQualityControl_AUD 
       add constraint FK5vn58bjbwrlnaw2wq3hivf23x 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

    alter table LabQualityControlResult 
       add constraint FKhc7spogtqlcdu14vv19ih4tqv 
       foreign key (controlSample_Id) 
       references Sample (Id);

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
       add constraint FKf7w0xssqiu7vy91uvk42qvumk 
       foreign key (patientSample_Id) 
       references Sample (Id);

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

    alter table PatientOrder 
       add constraint FKgcc7dp56rsy3fdovglmx2tlno 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table PatientOrder_AUD 
       add constraint FK570w5utmawcgcvrg7xp3fkjfd 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

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

    alter table QualitativeFormatMethod_resultTemplates 
       add constraint FKsvkk12kvdypx9kp69qba8emk4 
       foreign key (QualitativeFormatMethod_Id) 
       references Method (Id);

    alter table QualitativeFormatMethod_resultTemplates_AUD 
       add constraint FKnq8dn9dr2hxa3d70rf59tkicg 
       foreign key (REV) 
       references REVINFO (REV);

    alter table QuantitativeFormatMethod_refferentialRanges 
       add constraint FKakkpqci9i3lrq4ql9vyxl03lh 
       foreign key (QuantitativeFormatMethod_Id) 
       references Method (Id);

    alter table Sample 
       add constraint FKtcs6g9uujpbeq9eaui0n1qy7a 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table Sample 
       add constraint FKru88e978gj24srmr5bhe11ci7 
       foreign key (patientOrder_Id) 
       references PatientOrder (Id);

    alter table Sample 
       add constraint FK5urc15yfju1sedjs71hx3vwhx 
       foreign key (labQualityControl_Id) 
       references LabQualityControl (Id);

    alter table Sample_AUD 
       add constraint FK7c3l5hxfww0tdps18v0amndd1 
       foreign key (REV) 
       references REVINFO (REV);

    alter table SpecimentType_AUD 
       add constraint FK4jh9vuijlnqx2sftathnhptm0 
       foreign key (REV) 
       references REVINFO (REV);

    create table AbstractAnalyteResult (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        NumberResult decimal(36,18),
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
        TextResult varchar(255),
        NumberResult decimal(36,18),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table AbstractOrder (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table AbstractOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTest (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60) not null,
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
        isActive bit,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTestHistory (
       updateTimeStamp varchar(255) not null,
        cretionTimeStamp datetime(6),
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        laboratoryTest_Id varchar(60) not null,
        primary key (laboratoryTest_Id, updateTimeStamp)
    ) engine=InnoDB;

    create table LabQualityControl (
       description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60) not null,
        reportingDeadLine datetime(6),
        Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table LabQualityControl_AUD (
       Id varchar(60) not null,
        REV integer not null,
        description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60),
        reportingDeadLine datetime(6),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LabQualityControlResult (
       targetValue bit not null,
        Id varchar(60) not null,
        controlSample_Id varchar(60) not null,
        parentTargetValue_Id varchar(60),
        primary key (Id),
        check ((targetValue=TRUE AND parentTargetValue_Id=NULL) OR (targetValue=FALSE))
    ) engine=InnoDB;

    create table LabQualityControlResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        targetValue bit,
        controlSample_Id varchar(60),
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
        labTestOrderStatus varchar(255) not null,
        resultDescription varchar(512),
        tatMode varchar(255) not null,
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
        labTestOrderStatus varchar(255),
        resultDescription varchar(512),
        tatMode varchar(255),
        laboratoryTest_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table Method (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        analyticalMethodType varchar(180) not null,
        printable bit default true,
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        analyte_Id varchar(60) not null,
        laboratoryTest_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table Method_AUD (
       Id varchar(60) not null,
        REV integer not null,
        resultType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit,
        analyticalMethodType varchar(180),
        printable bit,
        analyte_Id varchar(60),
        laboratoryTest_Id varchar(60),
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderingUnit (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
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
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderResult (
       Id varchar(60) not null,
        patientSample_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table OrderResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        patientSample_Id varchar(60),
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
        comment varchar(4096),
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
       orderDate date,
        Id varchar(60) not null,
        orderingUnit_Id varchar(60),
        patient_Id varchar(60) not null,
        phisician_Id varchar(60),
        primary key (Id)
    ) engine=InnoDB;

    create table PatientOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
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

    create table QualitativeFormatMethod_resultTemplates (
       QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255)
    ) engine=InnoDB;

    create table QualitativeFormatMethod_resultTemplates_AUD (
       REV integer not null,
        QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255) not null,
        REVTYPE tinyint,
        primary key (REV, QualitativeFormatMethod_Id, resultTemplates)
    ) engine=InnoDB;

    create table QuantitativeFormatMethod_refferentialRanges (
       QuantitativeFormatMethod_Id varchar(60) not null,
        fromAge bigint,
        gender integer,
        toAge bigint,
        valueFrom decimal(19,2),
        valueTo decimal(19,2),
        refferentialRanges_ORDER integer not null,
        primary key (QuantitativeFormatMethod_Id, refferentialRanges_ORDER)
    ) engine=InnoDB;

    create table REVINFO (
       REV integer not null auto_increment,
        REVTSTMP bigint,
        primary key (REV)
    ) engine=InnoDB;

    create table Sample (
       sampleType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(60) not null,
        specimentType_Id varchar(60) not null,
        patientOrder_Id varchar(60),
        labQualityControl_Id varchar(60),
        primary key (Id),
        check ((sampleType='PATIENT' AND patientOrder_Id IS NOT NULL AND labQualityControl_Id IS NULL) OR (sampleType='CONTROL' AND labQualityControl_Id IS NOT NULL AND patientOrder_Id IS NULL))
    ) engine=InnoDB;

    create table Sample_AUD (
       Id varchar(60) not null,
        REV integer not null,
        sampleType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(255),
        specimentType_Id varchar(60),
        labQualityControl_Id varchar(60),
        patientOrder_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table SpecimentType (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        isActive bit not null,
        speciment varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table SpecimentType_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        isActive bit,
        speciment varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    alter table AbstractOrder 
       add constraint UK_8mc8121is13twuiqwxy15k3g4 unique (orderIdentificationCode);

    alter table Analyte 
       add constraint UK_47x69sl7aoei6c0giwnca5qxt unique (shortName);

    alter table LaboratoryTest 
       add constraint UK_fiqfkklxeqombys4ekliflm47 unique (shortName);

    alter table Patient 
       add constraint personalIdentificationNumber unique (personalIdentificationNumber);

    alter table Phisician 
       add constraint UK_hhhsqecp80pow3qrj66fnkmd7 unique (personalIdentificationNumber);

    alter table Sample 
       add constraint UK_7y5oe0qpo05a5pbgjechxalre unique (sampleId);

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

    alter table AbstractOrder_AUD 
       add constraint FKp5iurhe3l1huersgl4xhd0k82 
       foreign key (REV) 
       references REVINFO (REV);

    alter table Analyte_AUD 
       add constraint FK4k2vc25sg3d5ol920sigottvi 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTest 
       add constraint FKbtrkm84momkbq5lqc8b0gi2ay 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table LaboratoryTest_AUD 
       add constraint FKbbqpm2hy4c1t9qh27ocqnc8ki 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTestHistory 
       add constraint FK1iw9ydv52bxcevjjlwyhhrwm5 
       foreign key (laboratoryTest_Id) 
       references LaboratoryTest (Id);

    alter table LabQualityControl 
       add constraint FK6bfgd24ei1djon6txiukomg60 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table LabQualityControl_AUD 
       add constraint FK5vn58bjbwrlnaw2wq3hivf23x 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

    alter table LabQualityControlResult 
       add constraint FKhc7spogtqlcdu14vv19ih4tqv 
       foreign key (controlSample_Id) 
       references Sample (Id);

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
       add constraint FKf7w0xssqiu7vy91uvk42qvumk 
       foreign key (patientSample_Id) 
       references Sample (Id);

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

    alter table PatientOrder 
       add constraint FKgcc7dp56rsy3fdovglmx2tlno 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table PatientOrder_AUD 
       add constraint FK570w5utmawcgcvrg7xp3fkjfd 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

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

    alter table QualitativeFormatMethod_resultTemplates 
       add constraint FKsvkk12kvdypx9kp69qba8emk4 
       foreign key (QualitativeFormatMethod_Id) 
       references Method (Id);

    alter table QualitativeFormatMethod_resultTemplates_AUD 
       add constraint FKnq8dn9dr2hxa3d70rf59tkicg 
       foreign key (REV) 
       references REVINFO (REV);

    alter table QuantitativeFormatMethod_refferentialRanges 
       add constraint FKakkpqci9i3lrq4ql9vyxl03lh 
       foreign key (QuantitativeFormatMethod_Id) 
       references Method (Id);

    alter table Sample 
       add constraint FKtcs6g9uujpbeq9eaui0n1qy7a 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table Sample 
       add constraint FKru88e978gj24srmr5bhe11ci7 
       foreign key (patientOrder_Id) 
       references PatientOrder (Id);

    alter table Sample 
       add constraint FK5urc15yfju1sedjs71hx3vwhx 
       foreign key (labQualityControl_Id) 
       references LabQualityControl (Id);

    alter table Sample_AUD 
       add constraint FK7c3l5hxfww0tdps18v0amndd1 
       foreign key (REV) 
       references REVINFO (REV);

    alter table SpecimentType_AUD 
       add constraint FK4jh9vuijlnqx2sftathnhptm0 
       foreign key (REV) 
       references REVINFO (REV);

    create table AbstractAnalyteResult (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        NumberResult decimal(36,18),
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
        TextResult varchar(255),
        NumberResult decimal(36,18),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table AbstractOrder (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table AbstractOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTest (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60) not null,
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
        isActive bit,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTestHistory (
       updateTimeStamp varchar(255) not null,
        cretionTimeStamp datetime(6),
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        laboratoryTest_Id varchar(60) not null,
        primary key (laboratoryTest_Id, updateTimeStamp)
    ) engine=InnoDB;

    create table LabQualityControl (
       description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60) not null,
        reportingDeadLine datetime(6),
        Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table LabQualityControl_AUD (
       Id varchar(60) not null,
        REV integer not null,
        description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60),
        reportingDeadLine datetime(6),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LabQualityControlResult (
       targetValue bit not null,
        Id varchar(60) not null,
        controlSample_Id varchar(60) not null,
        parentTargetValue_Id varchar(60),
        primary key (Id),
        check ((targetValue=TRUE AND parentTargetValue_Id=NULL) OR (targetValue=FALSE))
    ) engine=InnoDB;

    create table LabQualityControlResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        targetValue bit,
        controlSample_Id varchar(60),
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
        labTestOrderStatus varchar(255) not null,
        resultDescription varchar(512),
        tatMode varchar(255) not null,
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
        labTestOrderStatus varchar(255),
        resultDescription varchar(512),
        tatMode varchar(255),
        laboratoryTest_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table Method (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        analyticalMethodType varchar(180) not null,
        printable bit default true,
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        analyte_Id varchar(60) not null,
        laboratoryTest_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table Method_AUD (
       Id varchar(60) not null,
        REV integer not null,
        resultType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit,
        analyticalMethodType varchar(180),
        printable bit,
        analyte_Id varchar(60),
        laboratoryTest_Id varchar(60),
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderingUnit (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
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
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderResult (
       Id varchar(60) not null,
        patientSample_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table OrderResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        patientSample_Id varchar(60),
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
        comment varchar(4096),
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
       orderDate date,
        Id varchar(60) not null,
        orderingUnit_Id varchar(60),
        patient_Id varchar(60) not null,
        phisician_Id varchar(60),
        primary key (Id)
    ) engine=InnoDB;

    create table PatientOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
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

    create table QualitativeFormatMethod_resultTemplates (
       QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255)
    ) engine=InnoDB;

    create table QualitativeFormatMethod_resultTemplates_AUD (
       REV integer not null,
        QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255) not null,
        REVTYPE tinyint,
        primary key (REV, QualitativeFormatMethod_Id, resultTemplates)
    ) engine=InnoDB;

    create table QuantitativeFormatMethod_refferentialRanges (
       QuantitativeFormatMethod_Id varchar(60) not null,
        fromAge bigint,
        gender integer,
        toAge bigint,
        valueFrom decimal(19,2),
        valueTo decimal(19,2),
        refferentialRanges_ORDER integer not null,
        primary key (QuantitativeFormatMethod_Id, refferentialRanges_ORDER)
    ) engine=InnoDB;

    create table REVINFO (
       REV integer not null auto_increment,
        REVTSTMP bigint,
        primary key (REV)
    ) engine=InnoDB;

    create table Sample (
       sampleType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(60) not null,
        specimentType_Id varchar(60) not null,
        patientOrder_Id varchar(60),
        labQualityControl_Id varchar(60),
        primary key (Id),
        check ((sampleType='PATIENT' AND patientOrder_Id IS NOT NULL AND labQualityControl_Id IS NULL) OR (sampleType='CONTROL' AND labQualityControl_Id IS NOT NULL AND patientOrder_Id IS NULL))
    ) engine=InnoDB;

    create table Sample_AUD (
       Id varchar(60) not null,
        REV integer not null,
        sampleType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(255),
        specimentType_Id varchar(60),
        labQualityControl_Id varchar(60),
        patientOrder_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table SpecimentType (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        isActive bit not null,
        speciment varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table SpecimentType_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        isActive bit,
        speciment varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    alter table AbstractOrder 
       add constraint UK_8mc8121is13twuiqwxy15k3g4 unique (orderIdentificationCode);

    alter table Analyte 
       add constraint UK_47x69sl7aoei6c0giwnca5qxt unique (shortName);

    alter table LaboratoryTest 
       add constraint UK_fiqfkklxeqombys4ekliflm47 unique (shortName);

    alter table Patient 
       add constraint personalIdentificationNumber unique (personalIdentificationNumber);

    alter table Phisician 
       add constraint UK_hhhsqecp80pow3qrj66fnkmd7 unique (personalIdentificationNumber);

    alter table Sample 
       add constraint UK_7y5oe0qpo05a5pbgjechxalre unique (sampleId);

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

    alter table AbstractOrder_AUD 
       add constraint FKp5iurhe3l1huersgl4xhd0k82 
       foreign key (REV) 
       references REVINFO (REV);

    alter table Analyte_AUD 
       add constraint FK4k2vc25sg3d5ol920sigottvi 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTest 
       add constraint FKbtrkm84momkbq5lqc8b0gi2ay 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table LaboratoryTest_AUD 
       add constraint FKbbqpm2hy4c1t9qh27ocqnc8ki 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTestHistory 
       add constraint FK1iw9ydv52bxcevjjlwyhhrwm5 
       foreign key (laboratoryTest_Id) 
       references LaboratoryTest (Id);

    alter table LabQualityControl 
       add constraint FK6bfgd24ei1djon6txiukomg60 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table LabQualityControl_AUD 
       add constraint FK5vn58bjbwrlnaw2wq3hivf23x 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

    alter table LabQualityControlResult 
       add constraint FKhc7spogtqlcdu14vv19ih4tqv 
       foreign key (controlSample_Id) 
       references Sample (Id);

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
       add constraint FKf7w0xssqiu7vy91uvk42qvumk 
       foreign key (patientSample_Id) 
       references Sample (Id);

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

    alter table PatientOrder 
       add constraint FKgcc7dp56rsy3fdovglmx2tlno 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table PatientOrder_AUD 
       add constraint FK570w5utmawcgcvrg7xp3fkjfd 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

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

    alter table QualitativeFormatMethod_resultTemplates 
       add constraint FKsvkk12kvdypx9kp69qba8emk4 
       foreign key (QualitativeFormatMethod_Id) 
       references Method (Id);

    alter table QualitativeFormatMethod_resultTemplates_AUD 
       add constraint FKnq8dn9dr2hxa3d70rf59tkicg 
       foreign key (REV) 
       references REVINFO (REV);

    alter table QuantitativeFormatMethod_refferentialRanges 
       add constraint FKakkpqci9i3lrq4ql9vyxl03lh 
       foreign key (QuantitativeFormatMethod_Id) 
       references Method (Id);

    alter table Sample 
       add constraint FKtcs6g9uujpbeq9eaui0n1qy7a 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table Sample 
       add constraint FKru88e978gj24srmr5bhe11ci7 
       foreign key (patientOrder_Id) 
       references PatientOrder (Id);

    alter table Sample 
       add constraint FK5urc15yfju1sedjs71hx3vwhx 
       foreign key (labQualityControl_Id) 
       references LabQualityControl (Id);

    alter table Sample_AUD 
       add constraint FK7c3l5hxfww0tdps18v0amndd1 
       foreign key (REV) 
       references REVINFO (REV);

    alter table SpecimentType_AUD 
       add constraint FK4jh9vuijlnqx2sftathnhptm0 
       foreign key (REV) 
       references REVINFO (REV);

    create table AbstractAnalyteResult (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        NumberResult decimal(36,18),
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
        TextResult varchar(255),
        NumberResult decimal(36,18),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table AbstractOrder (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table AbstractOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTest (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60) not null,
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
        isActive bit,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTestHistory (
       updateTimeStamp varchar(255) not null,
        cretionTimeStamp datetime(6),
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        laboratoryTest_Id varchar(60) not null,
        primary key (laboratoryTest_Id, updateTimeStamp)
    ) engine=InnoDB;

    create table LabQualityControl (
       description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60) not null,
        reportingDeadLine datetime(6),
        Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table LabQualityControl_AUD (
       Id varchar(60) not null,
        REV integer not null,
        description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60),
        reportingDeadLine datetime(6),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LabQualityControlResult (
       targetValue bit not null,
        Id varchar(60) not null,
        controlSample_Id varchar(60) not null,
        parentTargetValue_Id varchar(60),
        primary key (Id),
        check ((targetValue=TRUE AND parentTargetValue_Id=NULL) OR (targetValue=FALSE))
    ) engine=InnoDB;

    create table LabQualityControlResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        targetValue bit,
        controlSample_Id varchar(60),
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
        labTestOrderStatus varchar(255) not null,
        resultDescription varchar(512),
        tatMode varchar(255) not null,
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
        labTestOrderStatus varchar(255),
        resultDescription varchar(512),
        tatMode varchar(255),
        laboratoryTest_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table Method (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        analyticalMethodType varchar(180) not null,
        printable bit default true,
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        analyte_Id varchar(60) not null,
        laboratoryTest_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table Method_AUD (
       Id varchar(60) not null,
        REV integer not null,
        resultType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit,
        analyticalMethodType varchar(180),
        printable bit,
        analyte_Id varchar(60),
        laboratoryTest_Id varchar(60),
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderingUnit (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
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
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderResult (
       Id varchar(60) not null,
        patientSample_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table OrderResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        patientSample_Id varchar(60),
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
        comment varchar(4096),
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
       orderDate date,
        Id varchar(60) not null,
        orderingUnit_Id varchar(60),
        patient_Id varchar(60) not null,
        phisician_Id varchar(60),
        primary key (Id)
    ) engine=InnoDB;

    create table PatientOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
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

    create table QualitativeFormatMethod_resultTemplates (
       QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255)
    ) engine=InnoDB;

    create table QualitativeFormatMethod_resultTemplates_AUD (
       REV integer not null,
        QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255) not null,
        REVTYPE tinyint,
        primary key (REV, QualitativeFormatMethod_Id, resultTemplates)
    ) engine=InnoDB;

    create table QuantitativeFormatMethod_refferentialRanges (
       QuantitativeFormatMethod_Id varchar(60) not null,
        fromAge bigint,
        gender integer,
        toAge bigint,
        valueFrom decimal(19,2),
        valueTo decimal(19,2),
        refferentialRanges_ORDER integer not null,
        primary key (QuantitativeFormatMethod_Id, refferentialRanges_ORDER)
    ) engine=InnoDB;

    create table REVINFO (
       REV integer not null auto_increment,
        REVTSTMP bigint,
        primary key (REV)
    ) engine=InnoDB;

    create table Sample (
       sampleType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(60) not null,
        specimentType_Id varchar(60) not null,
        patientOrder_Id varchar(60),
        labQualityControl_Id varchar(60),
        primary key (Id),
        check ((sampleType='PATIENT' AND patientOrder_Id IS NOT NULL AND labQualityControl_Id IS NULL) OR (sampleType='CONTROL' AND labQualityControl_Id IS NOT NULL AND patientOrder_Id IS NULL))
    ) engine=InnoDB;

    create table Sample_AUD (
       Id varchar(60) not null,
        REV integer not null,
        sampleType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(255),
        specimentType_Id varchar(60),
        labQualityControl_Id varchar(60),
        patientOrder_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table SpecimentType (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        isActive bit not null,
        speciment varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table SpecimentType_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        isActive bit,
        speciment varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    alter table AbstractOrder 
       add constraint UK_8mc8121is13twuiqwxy15k3g4 unique (orderIdentificationCode);

    alter table Analyte 
       add constraint UK_47x69sl7aoei6c0giwnca5qxt unique (shortName);

    alter table LaboratoryTest 
       add constraint UK_fiqfkklxeqombys4ekliflm47 unique (shortName);

    alter table Patient 
       add constraint personalIdentificationNumber unique (personalIdentificationNumber);

    alter table Phisician 
       add constraint UK_hhhsqecp80pow3qrj66fnkmd7 unique (personalIdentificationNumber);

    alter table Sample 
       add constraint UK_7y5oe0qpo05a5pbgjechxalre unique (sampleId);

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

    alter table AbstractOrder_AUD 
       add constraint FKp5iurhe3l1huersgl4xhd0k82 
       foreign key (REV) 
       references REVINFO (REV);

    alter table Analyte_AUD 
       add constraint FK4k2vc25sg3d5ol920sigottvi 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTest 
       add constraint FKbtrkm84momkbq5lqc8b0gi2ay 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table LaboratoryTest_AUD 
       add constraint FKbbqpm2hy4c1t9qh27ocqnc8ki 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTestHistory 
       add constraint FK1iw9ydv52bxcevjjlwyhhrwm5 
       foreign key (laboratoryTest_Id) 
       references LaboratoryTest (Id);

    alter table LabQualityControl 
       add constraint FK6bfgd24ei1djon6txiukomg60 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table LabQualityControl_AUD 
       add constraint FK5vn58bjbwrlnaw2wq3hivf23x 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

    alter table LabQualityControlResult 
       add constraint FKhc7spogtqlcdu14vv19ih4tqv 
       foreign key (controlSample_Id) 
       references Sample (Id);

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
       add constraint FKf7w0xssqiu7vy91uvk42qvumk 
       foreign key (patientSample_Id) 
       references Sample (Id);

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

    alter table PatientOrder 
       add constraint FKgcc7dp56rsy3fdovglmx2tlno 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table PatientOrder_AUD 
       add constraint FK570w5utmawcgcvrg7xp3fkjfd 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

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

    alter table QualitativeFormatMethod_resultTemplates 
       add constraint FKsvkk12kvdypx9kp69qba8emk4 
       foreign key (QualitativeFormatMethod_Id) 
       references Method (Id);

    alter table QualitativeFormatMethod_resultTemplates_AUD 
       add constraint FKnq8dn9dr2hxa3d70rf59tkicg 
       foreign key (REV) 
       references REVINFO (REV);

    alter table QuantitativeFormatMethod_refferentialRanges 
       add constraint FKakkpqci9i3lrq4ql9vyxl03lh 
       foreign key (QuantitativeFormatMethod_Id) 
       references Method (Id);

    alter table Sample 
       add constraint FKtcs6g9uujpbeq9eaui0n1qy7a 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table Sample 
       add constraint FKru88e978gj24srmr5bhe11ci7 
       foreign key (patientOrder_Id) 
       references PatientOrder (Id);

    alter table Sample 
       add constraint FK5urc15yfju1sedjs71hx3vwhx 
       foreign key (labQualityControl_Id) 
       references LabQualityControl (Id);

    alter table Sample_AUD 
       add constraint FK7c3l5hxfww0tdps18v0amndd1 
       foreign key (REV) 
       references REVINFO (REV);

    alter table SpecimentType_AUD 
       add constraint FK4jh9vuijlnqx2sftathnhptm0 
       foreign key (REV) 
       references REVINFO (REV);

    create table AbstractAnalyteResult (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        NumberResult decimal(36,18),
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
        TextResult varchar(255),
        NumberResult decimal(36,18),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table AbstractOrder (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table AbstractOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        orderIdentificationCode varchar(128),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
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
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTest (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60) not null,
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
        isActive bit,
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        specimentType_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LaboratoryTestHistory (
       updateTimeStamp varchar(255) not null,
        cretionTimeStamp datetime(6),
        description varchar(255),
        name varchar(180),
        shortName varchar(30),
        laboratoryTest_Id varchar(60) not null,
        primary key (laboratoryTest_Id, updateTimeStamp)
    ) engine=InnoDB;

    create table LabQualityControl (
       description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60) not null,
        reportingDeadLine datetime(6),
        Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table LabQualityControl_AUD (
       Id varchar(60) not null,
        REV integer not null,
        description varchar(255),
        expirationDate datetime(6),
        externalIdentificationCode varchar(60),
        name varchar(60),
        reportingDeadLine datetime(6),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table LabQualityControlResult (
       targetValue bit not null,
        Id varchar(60) not null,
        controlSample_Id varchar(60) not null,
        parentTargetValue_Id varchar(60),
        primary key (Id),
        check ((targetValue=TRUE AND parentTargetValue_Id=NULL) OR (targetValue=FALSE))
    ) engine=InnoDB;

    create table LabQualityControlResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        targetValue bit,
        controlSample_Id varchar(60),
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
        labTestOrderStatus varchar(255) not null,
        resultDescription varchar(512),
        tatMode varchar(255) not null,
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
        labTestOrderStatus varchar(255),
        resultDescription varchar(512),
        tatMode varchar(255),
        laboratoryTest_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table Method (
       resultType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit not null,
        analyticalMethodType varchar(180) not null,
        printable bit default true,
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        analyte_Id varchar(60) not null,
        laboratoryTest_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table Method_AUD (
       Id varchar(60) not null,
        REV integer not null,
        resultType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        isActive bit,
        analyticalMethodType varchar(180),
        printable bit,
        analyte_Id varchar(60),
        laboratoryTest_Id varchar(60),
        decimalFormat varchar(36),
        limitOfDetection decimal(36,18),
        limitOfQuantification decimal(36,18),
        roundingMode varchar(255),
        sensitivity decimal(36,18),
        units varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderingUnit (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
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
        city varchar(100),
        country varchar(100),
        postalCode varchar(15),
        state varchar(100),
        street varchar(100),
        name varchar(255),
        shortName varchar(30),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table OrderResult (
       Id varchar(60) not null,
        patientSample_Id varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table OrderResult_AUD (
       Id varchar(60) not null,
        REV integer not null,
        patientSample_Id varchar(60),
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
        comment varchar(4096),
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
       orderDate date,
        Id varchar(60) not null,
        orderingUnit_Id varchar(60),
        patient_Id varchar(60) not null,
        phisician_Id varchar(60),
        primary key (Id)
    ) engine=InnoDB;

    create table PatientOrder_AUD (
       Id varchar(60) not null,
        REV integer not null,
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

    create table QualitativeFormatMethod_resultTemplates (
       QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255)
    ) engine=InnoDB;

    create table QualitativeFormatMethod_resultTemplates_AUD (
       REV integer not null,
        QualitativeFormatMethod_Id varchar(60) not null,
        resultTemplates varchar(255) not null,
        REVTYPE tinyint,
        primary key (REV, QualitativeFormatMethod_Id, resultTemplates)
    ) engine=InnoDB;

    create table QuantitativeFormatMethod_refferentialRanges (
       QuantitativeFormatMethod_Id varchar(60) not null,
        fromAge bigint,
        gender integer,
        toAge bigint,
        valueFrom decimal(19,2),
        valueTo decimal(19,2),
        refferentialRanges_ORDER integer not null,
        primary key (QuantitativeFormatMethod_Id, refferentialRanges_ORDER)
    ) engine=InnoDB;

    create table REVINFO (
       REV integer not null auto_increment,
        REVTSTMP bigint,
        primary key (REV)
    ) engine=InnoDB;

    create table Sample (
       sampleType varchar(31) not null,
        Id varchar(60) not null,
        versionTimestamp datetime(6),
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(60) not null,
        specimentType_Id varchar(60) not null,
        patientOrder_Id varchar(60),
        labQualityControl_Id varchar(60),
        primary key (Id),
        check ((sampleType='PATIENT' AND patientOrder_Id IS NOT NULL AND labQualityControl_Id IS NULL) OR (sampleType='CONTROL' AND labQualityControl_Id IS NOT NULL AND patientOrder_Id IS NULL))
    ) engine=InnoDB;

    create table Sample_AUD (
       Id varchar(60) not null,
        REV integer not null,
        sampleType varchar(31) not null,
        REVTYPE tinyint,
        createdBy varchar(60),
        cretionTimeStamp datetime(6),
        lastModifiedBy varchar(60),
        updateTimeStamp datetime(6),
        collectionDate datetime(6),
        sampleId varchar(255),
        specimentType_Id varchar(60),
        labQualityControl_Id varchar(60),
        patientOrder_Id varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    create table SpecimentType (
       Id varchar(60) not null,
        versionTimestamp datetime(6),
        isActive bit not null,
        speciment varchar(60) not null,
        primary key (Id)
    ) engine=InnoDB;

    create table SpecimentType_AUD (
       Id varchar(60) not null,
        REV integer not null,
        REVTYPE tinyint,
        isActive bit,
        speciment varchar(60),
        primary key (Id, REV)
    ) engine=InnoDB;

    alter table AbstractOrder 
       add constraint UK_8mc8121is13twuiqwxy15k3g4 unique (orderIdentificationCode);

    alter table Analyte 
       add constraint UK_47x69sl7aoei6c0giwnca5qxt unique (shortName);

    alter table LaboratoryTest 
       add constraint UK_fiqfkklxeqombys4ekliflm47 unique (shortName);

    alter table Patient 
       add constraint personalIdentificationNumber unique (personalIdentificationNumber);

    alter table Phisician 
       add constraint UK_hhhsqecp80pow3qrj66fnkmd7 unique (personalIdentificationNumber);

    alter table Sample 
       add constraint UK_7y5oe0qpo05a5pbgjechxalre unique (sampleId);

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

    alter table AbstractOrder_AUD 
       add constraint FKp5iurhe3l1huersgl4xhd0k82 
       foreign key (REV) 
       references REVINFO (REV);

    alter table Analyte_AUD 
       add constraint FK4k2vc25sg3d5ol920sigottvi 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTest 
       add constraint FKbtrkm84momkbq5lqc8b0gi2ay 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table LaboratoryTest_AUD 
       add constraint FKbbqpm2hy4c1t9qh27ocqnc8ki 
       foreign key (REV) 
       references REVINFO (REV);

    alter table LaboratoryTestHistory 
       add constraint FK1iw9ydv52bxcevjjlwyhhrwm5 
       foreign key (laboratoryTest_Id) 
       references LaboratoryTest (Id);

    alter table LabQualityControl 
       add constraint FK6bfgd24ei1djon6txiukomg60 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table LabQualityControl_AUD 
       add constraint FK5vn58bjbwrlnaw2wq3hivf23x 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

    alter table LabQualityControlResult 
       add constraint FKhc7spogtqlcdu14vv19ih4tqv 
       foreign key (controlSample_Id) 
       references Sample (Id);

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
       add constraint FKf7w0xssqiu7vy91uvk42qvumk 
       foreign key (patientSample_Id) 
       references Sample (Id);

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

    alter table PatientOrder 
       add constraint FKgcc7dp56rsy3fdovglmx2tlno 
       foreign key (Id) 
       references AbstractOrder (Id);

    alter table PatientOrder_AUD 
       add constraint FK570w5utmawcgcvrg7xp3fkjfd 
       foreign key (Id, REV) 
       references AbstractOrder_AUD (Id, REV);

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

    alter table QualitativeFormatMethod_resultTemplates 
       add constraint FKsvkk12kvdypx9kp69qba8emk4 
       foreign key (QualitativeFormatMethod_Id) 
       references Method (Id);

    alter table QualitativeFormatMethod_resultTemplates_AUD 
       add constraint FKnq8dn9dr2hxa3d70rf59tkicg 
       foreign key (REV) 
       references REVINFO (REV);

    alter table QuantitativeFormatMethod_refferentialRanges 
       add constraint FKakkpqci9i3lrq4ql9vyxl03lh 
       foreign key (QuantitativeFormatMethod_Id) 
       references Method (Id);

    alter table Sample 
       add constraint FKtcs6g9uujpbeq9eaui0n1qy7a 
       foreign key (specimentType_Id) 
       references SpecimentType (Id);

    alter table Sample 
       add constraint FKru88e978gj24srmr5bhe11ci7 
       foreign key (patientOrder_Id) 
       references PatientOrder (Id);

    alter table Sample 
       add constraint FK5urc15yfju1sedjs71hx3vwhx 
       foreign key (labQualityControl_Id) 
       references LabQualityControl (Id);

    alter table Sample_AUD 
       add constraint FK7c3l5hxfww0tdps18v0amndd1 
       foreign key (REV) 
       references REVINFO (REV);

    alter table SpecimentType_AUD 
       add constraint FK4jh9vuijlnqx2sftathnhptm0 
       foreign key (REV) 
       references REVINFO (REV);
