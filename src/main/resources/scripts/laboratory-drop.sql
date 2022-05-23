
    alter table AbstractAnalyteResult 
       drop 
       foreign key FK8bmge3b9exphdy4b6h2w3t2in; 

    alter table AbstractAnalyteResult 
       drop 
       foreign key FKi30odtwxrojto51hin0nbuejf;

    alter table AbstractAnalyteResult_AUD 
       drop 
       foreign key FK4hvlsimcjwtf3swdrlquu868k;

    alter table AbstractOrder_AUD 
       drop 
       foreign key FKp5iurhe3l1huersgl4xhd0k82;

    alter table Analyte_AUD 
       drop 
       foreign key FK4k2vc25sg3d5ol920sigottvi;

    alter table LaboratoryTest 
       drop 
       foreign key FKbtrkm84momkbq5lqc8b0gi2ay;

    alter table LaboratoryTest_AUD 
       drop 
       foreign key FKbbqpm2hy4c1t9qh27ocqnc8ki;

    alter table LaboratoryTestHistory 
       drop 
       foreign key FK1iw9ydv52bxcevjjlwyhhrwm5;

    alter table LabQualityControl 
       drop 
       foreign key FK6bfgd24ei1djon6txiukomg60;

    alter table LabQualityControl_AUD 
       drop 
       foreign key FK5vn58bjbwrlnaw2wq3hivf23x;

    alter table LabQualityControlResult 
       drop 
       foreign key FKhc7spogtqlcdu14vv19ih4tqv;

    alter table LabQualityControlResult 
       drop 
       foreign key FKg6nvhcqnejdi35pqkye2ghnqr;

    alter table LabQualityControlResult 
       drop 
       foreign key FKsmdy7pbnxmwak88xtjh70px8;

    alter table LabQualityControlResult_AUD 
       drop 
       foreign key FKknkmyu7gyup215v6aaqslrdb6;

    alter table LabTestOrder 
       drop 
       foreign key FKjah3jpw3bulh8csm6hkmg6apn;

    alter table LabTestOrder_AUD 
       drop 
       foreign key FK7qdwe1vfve8sqgj6f4xsi6cs;

    alter table Method 
       drop 
       foreign key FKkl6d5qmuiiwxra8rfd58fbvyx;

    alter table Method 
       drop 
       foreign key FKnrwgpu4db8br1wg48b77qxlri;

    alter table Method_AUD 
       drop 
       foreign key FKbtsb1u7ddd5tysh348l7vt4k2;

    alter table OrderingUnit_AUD 
       drop 
       foreign key FKd760x09gcxesegessgjp6qos6;

    alter table OrderResult 
       drop 
       foreign key FKf7w0xssqiu7vy91uvk42qvumk;

    alter table OrderResult 
       drop 
       foreign key FK98ggq6oq25sv2cmppgr4oc5ty;

    alter table OrderResult_AUD 
       drop 
       foreign key FKap2gh8nv85jqmjxrw1h1jcinj;

    alter table Patient_addresses 
       drop 
       foreign key FK6rcnegjeixsve1wlafohe35ep;

    alter table Patient_AUD 
       drop 
       foreign key FKdlyae0mnlgiwq5mjh0e2s2qba;

    alter table Patient_PatientComment_AUD 
       drop 
       foreign key FK96mtlkd59a7rhigg7ains2mc4;

    alter table Patient_phoneNumbers 
       drop 
       foreign key FK5l8jlvv2g67kf9spp1nc7te14;

    alter table PatientComment 
       drop 
       foreign key FKpny0oc4dsrkolgfvfk0f0ntss;

    alter table PatientComment_AUD 
       drop 
       foreign key FKlhcer5lmym57jb4s2njuvlyby;

    alter table PatientOrder 
       drop 
       foreign key FK1mk9510jr2ecm3lnhwoad8hck;

    alter table PatientOrder 
       drop 
       foreign key FKb5hkiem731707u1t7yst1gk4f;

    alter table PatientOrder 
       drop 
       foreign key FK237kjypkrgluokjk0ou66efur;

    alter table PatientOrder 
       drop 
       foreign key FKgcc7dp56rsy3fdovglmx2tlno;

    alter table PatientOrder_AUD 
       drop 
       foreign key FK570w5utmawcgcvrg7xp3fkjfd;

    alter table Phisician_AUD 
       drop 
       foreign key FKcj5euetw0smq0n0yfdylh4x84;

    alter table PhisicianOrderingUnit 
       drop 
       foreign key FKqlct0yx5lgxkwygpol61dxjyb;

    alter table PhisicianOrderingUnit 
       drop 
       foreign key FK58oa64rbvatotssgvy7u6ah5m;

    alter table PhisicianOrderingUnit_AUD 
       drop 
       foreign key FKhivinq2u3s7y9ewfebcck2nnn;

    alter table QualitativeFormatMethod_resultTemplates 
       drop 
       foreign key FKsvkk12kvdypx9kp69qba8emk4;

    alter table QualitativeFormatMethod_resultTemplates_AUD 
       drop 
       foreign key FKnq8dn9dr2hxa3d70rf59tkicg;

    alter table QuantitativeFormatMethod_refferentialRanges 
       drop 
       foreign key FKakkpqci9i3lrq4ql9vyxl03lh;

    alter table Sample 
       drop 
       foreign key FKtcs6g9uujpbeq9eaui0n1qy7a;

    alter table Sample 
       drop 
       foreign key FKru88e978gj24srmr5bhe11ci7;

    alter table Sample 
       drop 
       foreign key FK5urc15yfju1sedjs71hx3vwhx;

    alter table Sample_AUD 
       drop 
       foreign key FK7c3l5hxfww0tdps18v0amndd1;

    alter table SpecimentType_AUD 
       drop 
       foreign key FK4jh9vuijlnqx2sftathnhptm0;

    drop table if exists AbstractAnalyteResult;

    drop table if exists AbstractAnalyteResult_AUD;

    drop table if exists AbstractOrder;

    drop table if exists AbstractOrder_AUD;

    drop table if exists Analyte;

    drop table if exists Analyte_AUD;

    drop table if exists LaboratoryTest;

    drop table if exists LaboratoryTest_AUD;

    drop table if exists LaboratoryTestHistory;

    drop table if exists LabQualityControl;

    drop table if exists LabQualityControl_AUD;

    drop table if exists LabQualityControlResult;

    drop table if exists LabQualityControlResult_AUD;

    drop table if exists LabTestOrder;

    drop table if exists LabTestOrder_AUD;

    drop table if exists Method;

    drop table if exists Method_AUD;

    drop table if exists OrderingUnit;

    drop table if exists OrderingUnit_AUD;

    drop table if exists OrderResult;

    drop table if exists OrderResult_AUD;

    drop table if exists Patient;

    drop table if exists Patient_addresses;

    drop table if exists Patient_AUD;

    drop table if exists Patient_PatientComment_AUD;

    drop table if exists Patient_phoneNumbers;

    drop table if exists PatientComment;

    drop table if exists PatientComment_AUD;

    drop table if exists PatientOrder;

    drop table if exists PatientOrder_AUD;

    drop table if exists Phisician;

    drop table if exists Phisician_AUD;

    drop table if exists PhisicianOrderingUnit;

    drop table if exists PhisicianOrderingUnit_AUD;

    drop table if exists QualitativeFormatMethod_resultTemplates;

    drop table if exists QualitativeFormatMethod_resultTemplates_AUD;

    drop table if exists QuantitativeFormatMethod_refferentialRanges;

    drop table if exists REVINFO;

    drop table if exists Sample;

    drop table if exists Sample_AUD;

    drop table if exists SpecimentType;

    drop table if exists SpecimentType_AUD;
