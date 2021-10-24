
    alter table AbstractAnalyteResult 
       drop 
       foreign key FK8bmge3b9exphdy4b6h2w3t2in;

    alter table AbstractAnalyteResult 
       drop 
       foreign key FKi30odtwxrojto51hin0nbuejf;

    alter table AbstractAnalyteResult_AUD 
       drop 
       foreign key FK4hvlsimcjwtf3swdrlquu868k;

    alter table Analyte_AUD 
       drop 
       foreign key FK4k2vc25sg3d5ol920sigottvi;

    alter table LaboratoryTest_AUD 
       drop 
       foreign key FKbbqpm2hy4c1t9qh27ocqnc8ki;

    alter table LabQualityControl_AUD 
       drop 
       foreign key FKjbkxxjanb8hwowdhfxxe2qdo0;

    alter table LabQualityControlResult 
       drop 
       foreign key FKosxu1hwxe77e9lmmcpx6sb673;

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

    alter table LabTestOrder_AbstractAnalyteResult 
       drop 
       foreign key FKl4qxegp3hx43pbt1urmnvcso6;

    alter table LabTestOrder_AbstractAnalyteResult 
       drop 
       foreign key FKopvl1sqd0xjhvdn0tthqluvs0;

    alter table LabTestOrder_AbstractAnalyteResult_AUD 
       drop 
       foreign key FK373038awned839yg4h5g70wxc;

    alter table LabTestOrder_AUD 
       drop 
       foreign key FK7qdwe1vfve8sqgj6f4xsi6cs;

    alter table Method 
       drop 
       foreign key FKkl6d5qmuiiwxra8rfd58fbvyx;

    alter table Method 
       drop 
       foreign key FKnrwgpu4db8br1wg48b77qxlri;

    alter table Method_AbstractAnalyteResult 
       drop 
       foreign key FK6pyj42xda7axkkadrch2qnaja;

    alter table Method_AbstractAnalyteResult 
       drop 
       foreign key FKf25qrc9smscb9xybwisiiy7id;

    alter table Method_AbstractAnalyteResult_AUD 
       drop 
       foreign key FKcur2698wiagj6or665mdsesrm;

    alter table Method_AUD 
       drop 
       foreign key FKbtsb1u7ddd5tysh348l7vt4k2;

    alter table OrderingUnit_AUD 
       drop 
       foreign key FKd760x09gcxesegessgjp6qos6;

    alter table OrderResult 
       drop 
       foreign key FK9nup2ja6phkloufiqb3plae9h;

    alter table OrderResult 
       drop 
       foreign key FK98ggq6oq25sv2cmppgr4oc5ty;

    alter table OrderResult_AUD 
       drop 
       foreign key FKap2gh8nv85jqmjxrw1h1jcinj;

    alter table Patient_AUD 
       drop 
       foreign key FKdlyae0mnlgiwq5mjh0e2s2qba;

    alter table PatientOrder 
       drop 
       foreign key FK1mk9510jr2ecm3lnhwoad8hck;

    alter table PatientOrder 
       drop 
       foreign key FKb5hkiem731707u1t7yst1gk4f;

    alter table PatientOrder 
       drop 
       foreign key FK237kjypkrgluokjk0ou66efur;

    alter table PatientOrder_AUD 
       drop 
       foreign key FKi7morp0fgm7cd579deey8oxw0;

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

    drop table if exists AbstractAnalyteResult;

    drop table if exists AbstractAnalyteResult_AUD;

    drop table if exists Analyte;

    drop table if exists Analyte_AUD;

    drop table if exists LaboratoryTest;

    drop table if exists LaboratoryTest_AUD;

    drop table if exists LabQualityControl;

    drop table if exists LabQualityControl_AUD;

    drop table if exists LabQualityControlResult;

    drop table if exists LabQualityControlResult_AUD;

    drop table if exists LabTestOrder;

    drop table if exists LabTestOrder_AbstractAnalyteResult;

    drop table if exists LabTestOrder_AbstractAnalyteResult_AUD;

    drop table if exists LabTestOrder_AUD;

    drop table if exists Method;

    drop table if exists Method_AbstractAnalyteResult;

    drop table if exists Method_AbstractAnalyteResult_AUD;

    drop table if exists Method_AUD;

    drop table if exists OrderingUnit;

    drop table if exists OrderingUnit_AUD;

    drop table if exists OrderResult;

    drop table if exists OrderResult_AUD;

    drop table if exists Patient;

    drop table if exists Patient_AUD;

    drop table if exists PatientOrder;

    drop table if exists PatientOrder_AUD;

    drop table if exists Phisician;

    drop table if exists Phisician_AUD;

    drop table if exists PhisicianOrderingUnit;

    drop table if exists PhisicianOrderingUnit_AUD;

    drop table if exists REVINFO;
