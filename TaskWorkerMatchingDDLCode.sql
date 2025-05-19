CREATE DATABASE TaskWorkerMatching;
GO
USE TaskWorkerMatching;
GO
if exists (select 1
from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
where r.fkeyid = object_id('AVAILABLETIMESLOTS') and o.name = 'FK_AVAILABL_DEPENDSON_WORKER')
alter table AVAILABLETIMESLOTS
drop constraint FK_AVAILABL_DEPENDSON_WORKER
go

if exists (select 1
from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
where r.fkeyid = object_id('EXECUTEDREQUEST') and o.name = 'FK_EXECUTED_EXECUTEDR_WORKER')
alter table EXECUTEDREQUEST
drop constraint FK_EXECUTED_EXECUTEDR_WORKER
go

if exists (select 1
from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
where r.fkeyid = object_id('EXECUTEDREQUEST') and o.name = 'FK_EXECUTED_EXECUTEDR_REQUEST')
alter table EXECUTEDREQUEST
drop constraint FK_EXECUTED_EXECUTEDR_REQUEST
go

if exists (select 1
from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
where r.fkeyid = object_id('REQUEST') and o.name = 'FK_REQUEST_CONTAINS_TASK')
alter table REQUEST
drop constraint FK_REQUEST_CONTAINS_TASK
go

if exists (select 1
from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
where r.fkeyid = object_id('REQUEST') and o.name = 'FK_REQUEST_REQUESTS_CLIENT')
alter table REQUEST
drop constraint FK_REQUEST_REQUESTS_CLIENT
go

if exists (select 1
from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
where r.fkeyid = object_id('TASK') and o.name = 'FK_TASK_REQUIRES_SPECIALI')
alter table TASK
drop constraint FK_TASK_REQUIRES_SPECIALI
go

if exists (select 1
from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
where r.fkeyid = object_id('WORKERSPECIALITIES') and o.name = 'FK_WORKERSP_WORKERSPE_SPECIALI')
alter table WORKERSPECIALITIES
drop constraint FK_WORKERSP_WORKERSPE_SPECIALI
go

if exists (select 1
from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
where r.fkeyid = object_id('WORKERSPECIALITIES') and o.name = 'FK_WORKERSP_WORKERSPE_WORKER')
alter table WORKERSPECIALITIES
drop constraint FK_WORKERSP_WORKERSPE_WORKER
go

if exists (select 1
from  sysindexes
where  id    = object_id('AVAILABLETIMESLOTS')
and   name  = 'DEPENDSON_FK'
and   indid > 0
and   indid < 255)
drop index AVAILABLETIMESLOTS.DEPENDSON_FK
go

if exists (select 1
from  sysobjects
where  id = object_id('AVAILABLETIMESLOTS')
and   type = 'U')
drop table AVAILABLETIMESLOTS
go

if exists (select 1
from  sysobjects
where  id = object_id('CLIENT')
and   type = 'U')
drop table CLIENT
go

if exists (select 1
from  sysindexes
where  id    = object_id('EXECUTEDREQUEST')
and   name  = 'EXECUTEDREQUEST2_FK'
and   indid > 0
and   indid < 255)
drop index EXECUTEDREQUEST.EXECUTEDREQUEST2_FK
go

if exists (select 1
from  sysindexes
where  id    = object_id('EXECUTEDREQUEST')
and   name  = 'EXECUTEDREQUEST_FK'
and   indid > 0
and   indid < 255)
drop index EXECUTEDREQUEST.EXECUTEDREQUEST_FK
go

if exists (select 1
from  sysobjects
where  id = object_id('EXECUTEDREQUEST')
and   type = 'U')
drop table EXECUTEDREQUEST
go

if exists (select 1
from  sysindexes
where  id    = object_id('REQUEST')
and   name  = 'ISDONEIN_FK'
and   indid > 0
and   indid < 255)
drop index REQUEST.ISDONEIN_FK
go

if exists (select 1
from  sysindexes
where  id    = object_id('REQUEST')
and   name  = 'REQUESTS_FK'
and   indid > 0
and   indid < 255)
drop index REQUEST.REQUESTS_FK
go

if exists (select 1
from  sysindexes
where  id    = object_id('REQUEST')
and   name  = 'EXECUTEDREQUEST_FK'
and   indid > 0
and   indid < 255)
drop index REQUEST.EXECUTEDREQUEST_FK
go

if exists (select 1
from  sysobjects
where  id = object_id('REQUEST')
and   type = 'U')
drop table REQUEST
go

if exists (select 1
from  sysobjects
where  id = object_id('SPECIALITY')
and   type = 'U')
drop table SPECIALITY
go

if exists (select 1
from  sysindexes
where  id    = object_id('TASK')
and   name  = 'REQUIRES_FK'
and   indid > 0
and   indid < 255)
drop index TASK.REQUIRES_FK
go

if exists (select 1
from  sysobjects
where  id = object_id('TASK')
and   type = 'U')
drop table TASK
go

if exists (select 1
from  sysobjects
where  id = object_id('WORKER')
and   type = 'U')
drop table WORKER
go

if exists (select 1
from  sysindexes
where  id    = object_id('WORKERSPECIALITIES')
and   name  = 'WORKERSPECIALTIES2_FK'
and   indid > 0
and   indid < 255)
drop index WORKERSPECIALITIES.WORKERSPECIALTIES2_FK
go

if exists (select 1
from  sysindexes
where  id    = object_id('WORKERSPECIALITIES')
and   name  = 'WORKERSPECIALTIES_FK'
and   indid > 0
and   indid < 255)
drop index WORKERSPECIALITIES.WORKERSPECIALTIES_FK
go

if exists (select 1
from  sysobjects
where  id = object_id('WORKERSPECIALITIES')
and   type = 'U')
drop table WORKERSPECIALITIES
go

/*==============================================================*/
/* Table: AVAILABLETIMESLOTS                                    */
/*==============================================================*/
create table AVAILABLETIMESLOTS (
WID                  int                  not null,
SLOTID               int                  not null,
STARTOFSLOT          datetime             not null,
ENDOFSLOT            datetime             not null,
constraint PK_AVAILABLETIMESLOTS primary key nonclustered (WID, SLOTID)
)
go

/*==============================================================*/
/* Index: DEPENDSON_FK                                          */
/*==============================================================*/
create index DEPENDSON_FK on AVAILABLETIMESLOTS (
WID ASC
)
go

/*==============================================================*/
/* Table: CLIENT                                                */
/*==============================================================*/
create table CLIENT (
CID                  int                  not null,
NAME                 varchar(30)          not null,
PHONE                char(11)             not null,
ADDRESS              varchar(50)          not null,
EMAIL                varchar(255)         not null,
CARDNUM              char(16)             not null,
EXPDATE              datetime             not null,
CVV                  char(4)              not null,
constraint PK_CLIENT primary key nonclustered (CID)
)
go

/*==============================================================*/
/* Table: EXECUTEDREQUEST                                       */
/*==============================================================*/
create table EXECUTEDREQUEST (
WID                  int                  not null,
RID                  int                  not null,
ACTUALTIMETAKEN      int                  not null,
WORKERRATING         int                  not null,
CLIENTRATING         int                  not null,
CLIENTFEEDBACK       text                 null,
WORKERFEEDBACK       text                 null,
constraint PK_EXECUTEDREQUEST primary key (WID, RID)
)
go

/*==============================================================*/
/* Index: EXECUTEDREQUEST_FK                                    */
/*==============================================================*/
create index EXECUTEDREQUEST_FK on EXECUTEDREQUEST (
WID ASC
)
go

/*==============================================================*/
/* Index: EXECUTEDREQUEST2_FK                                   */
/*==============================================================*/
create index EXECUTEDREQUEST2_FK on EXECUTEDREQUEST (
RID ASC
)
go

/*==============================================================*/
/* Table: REQUEST                                               */
/*==============================================================*/
create table REQUEST (
RID                  int                  not null,
CID                  int                  not null,
TID                  int                  not null,
ADDRESS              varchar(50)          not null,
PLACEMENTTIME        datetime             not null,
PREFERREDTIMETOCARRYOUT datetime             null,
STATUS               varchar(10)          null,
constraint PK_REQUEST primary key nonclustered (RID)
)
go

/*==============================================================*/
/* Index: EXECUTEDREQUEST_FK_REQ                                */
/*==============================================================*/
create index EXECUTEDREQUEST_FK_REQ on REQUEST (
RID ASC
)
go

/*==============================================================*/
/* Index: REQUESTS_FK                                           */
/*==============================================================*/
create index REQUESTS_FK on REQUEST (
CID ASC
)
go

/*==============================================================*/
/* Index: ISDONEIN_FK                                           */
/*==============================================================*/
create index ISDONEIN_FK on REQUEST (
TID ASC
)
go

/*==============================================================*/
/* Table: SPECIALITY                                            */
/*==============================================================*/
create table SPECIALITY (
SPECID               int                  not null,
DESCRIPTION          varchar(50)          not null,
constraint PK_SPECIALITY primary key (SPECID)
)
go

/*==============================================================*/
/* Table: TASK                                                 */
/*==============================================================*/
create table TASK (
TID                  int                  not null,
DESCRIPTION          varchar(50)          not null,
SPECID               int                  not null,
ESTIMATEDTIME        int                  not null,
constraint PK_TASK primary key clustered (TID)
)
go

/*==============================================================*/
/* Index: REQUIRES_FK                                          */
/*==============================================================*/
create index REQUIRES_FK on TASK (
SPECID ASC
)
go

/*==============================================================*/
/* Table: WORKER                                               */
/*==============================================================*/
create table WORKER (
WID                  int                  not null,
NAME                 varchar(30)          not null,
PHONE                char(11)             not null,
ADDRESS              varchar(50)          not null,
EMAIL                varchar(255)         not null,
constraint PK_WORKER primary key clustered (WID)
)
go

/*==============================================================*/
/* Table: WORKERSPECIALITIES                                  */
/*==============================================================*/
create table WORKERSPECIALITIES (
WID                  int                  not null,
SPECID               int                  not null,
constraint PK_WORKERSPECIALITIES primary key nonclustered (WID, SPECID)
)
go

/*==============================================================*/
/* Index: WORKERSPECIALTIES_FK                               */
/*==============================================================*/
create index WORKERSPECIALTIES_FK on WORKERSPECIALITIES (
SPECID ASC
)
go

/*==============================================================*/
/* Index: WORKERSPECIALTIES2_FK                              */
/*==============================================================*/
create index WORKERSPECIALTIES2_FK on WORKERSPECIALITIES (
WID ASC
)
go

/*==============================================================*/
/* Foreign key constraints                                    */
/*==============================================================*/
alter table AVAILABLETIMESLOTS
add constraint FK_AVAILABL_DEPENDSON_WORKER foreign key (WID) references WORKER (WID)
go

alter table EXECUTEDREQUEST
add constraint FK_EXECUTED_EXECUTEDR_WORKER foreign key (WID) references WORKER (WID)
go

alter table EXECUTEDREQUEST
add constraint FK_EXECUTED_EXECUTEDR_REQUEST foreign key (RID) references REQUEST (RID)
go

alter table REQUEST
add constraint FK_REQUEST_CONTAINS_TASK foreign key (TID) references TASK (TID)
go

alter table REQUEST
add constraint FK_REQUEST_REQUESTS_CLIENT foreign key (CID) references CLIENT (CID)
go

alter table TASK
add constraint FK_TASK_REQUIRES_SPECIALI foreign key (SPECID) references SPECIALITY (SPECID)
go

alter table WORKERSPECIALITIES
add constraint FK_WORKERSP_WORKERSPE_SPECIALI foreign key (SPECID) references SPECIALITY (SPECID)
go

alter table WORKERSPECIALITIES
add constraint FK_WORKERSP_WORKERSPE_WORKER foreign key (WID) references WORKER (WID)
go

/*****************************************************************
         Part 1: Drop Dependent Foreign Key Constraints
*****************************************************************/
-- Drop FK constraints referencing SPECIALITY
IF EXISTS (SELECT 1 FROM sys.foreign_keys WHERE name = 'FK_TASK_REQUIRES_SPECIALI')
ALTER TABLE TASK DROP CONSTRAINT FK_TASK_REQUIRES_SPECIALI

IF EXISTS (SELECT 1 FROM sys.foreign_keys WHERE name = 'FK_WORKERSP_WORKERSPE_SPECIALI')
ALTER TABLE WORKERSPECIALITIES DROP CONSTRAINT FK_WORKERSP_WORKERSPE_SPECIALI

-- Drop FK constraints referencing TASK
IF EXISTS (SELECT 1 FROM sys.foreign_keys WHERE name = 'FK_REQUEST_CONTAINS_TASK')
ALTER TABLE REQUEST DROP CONSTRAINT FK_REQUEST_CONTAINS_TASK

/*****************************************************************
         Part 2: Drop and Recreate Tables with New Schema
*****************************************************************/
-- Drop tables in dependency order
IF EXISTS (SELECT 1 FROM sysobjects WHERE id = object_id('TASK') AND type = 'U')
DROP TABLE TASK

IF EXISTS (SELECT 1 FROM sysobjects WHERE id = object_id('SPECIALITY') AND type = 'U')
DROP TABLE SPECIALITY

-- Recreate SPECIALITY with new schema
CREATE TABLE SPECIALITY (
    SPECIALTYID      INT           NOT NULL PRIMARY KEY,
    NAME             VARCHAR(30)   NOT NULL
)
GO

-- Recreate TASK with new schema
CREATE TABLE TASK (
    TID              INT           NOT NULL PRIMARY KEY,
    NAME             VARCHAR(30)   NOT NULL,
    SID              INT           NOT NULL,           -- Renamed from SPECID
    AVGNEEDEDTIME    INT           NOT NULL,           -- New column
    FEE              INT           NOT NULL            -- New column
)
GO

/*****************************************************************
         Part 3: Recreate Foreign Key Constraints
*****************************************************************/
-- Recreate FK from TASK to SPECIALITY
ALTER TABLE TASK
ADD CONSTRAINT FK_TASK_REQUIRES_SPECIALI 
FOREIGN KEY (SID) REFERENCES SPECIALITY(SPECIALTYID)
GO

-- Recreate FK from WORKERSPECIALITIES to SPECIALITY
ALTER TABLE WORKERSPECIALITIES
ADD CONSTRAINT FK_WORKERSP_WORKERSPE_SPECIALI 
FOREIGN KEY (SPECID) REFERENCES SPECIALITY(SPECIALTYID)
GO

-- Recreate FK from REQUEST to TASK
ALTER TABLE REQUEST 
ADD CONSTRAINT FK_REQUEST_CONTAINS_TASK 
FOREIGN KEY (TID) REFERENCES TASK(TID)
GO

/*****************************************************************
               Modify EXECUTEDREQUEST Table
*****************************************************************/

-- 1. Drop existing foreign keys referencing EXECUTEDREQUEST
IF EXISTS (SELECT 1 FROM sys.foreign_keys WHERE name = 'FK_EXECUTED_EXECUTEDR_WORKER')
ALTER TABLE EXECUTEDREQUEST DROP CONSTRAINT FK_EXECUTED_EXECUTEDR_WORKER

IF EXISTS (SELECT 1 FROM sys.foreign_keys WHERE name = 'FK_EXECUTED_EXECUTEDR_REQUEST')
ALTER TABLE EXECUTEDREQUEST DROP CONSTRAINT FK_EXECUTED_EXECUTEDR_REQUEST

-- 2. Drop existing table if exists
IF EXISTS (SELECT 1 FROM sysobjects WHERE id = object_id('EXECUTEDREQUEST') AND type = 'U')
DROP TABLE EXECUTEDREQUEST

-- 3. Create new table with updated schema
CREATE TABLE EXECUTEDREQUEST (
    REQUESTID        INT           NOT NULL,  -- References REQUEST.RID
    WORKERID         INT           NOT NULL,  -- References WORKER.WID
    ACTUALTIMETAKEN  INT           NOT NULL,
    WORKERRATING     INT           NOT NULL,
    CLIENTRATING     INT           NOT NULL,
    CLIENTFEEDBACK   TEXT          NULL,
    WORKERFEEDBACK   TEXT          NULL,
    
    -- Composite primary key
    CONSTRAINT PK_EXECUTEDREQUEST PRIMARY KEY (REQUESTID, WORKERID),
    
    -- Foreign key to REQUEST
    CONSTRAINT FK_EXECUTEDREQUEST_REQUEST 
    FOREIGN KEY (REQUESTID) REFERENCES REQUEST(RID),
    
    -- Foreign key to WORKER
    CONSTRAINT FK_EXECUTEDREQUEST_WORKER 
    FOREIGN KEY (WORKERID) REFERENCES WORKER(WID)
)
GO

-- 4. Create indexes for better performance
CREATE INDEX IDX_EXECUTEDREQUEST_REQUEST ON EXECUTEDREQUEST(REQUESTID)
CREATE INDEX IDX_EXECUTEDREQUEST_WORKER ON EXECUTEDREQUEST(WORKERID)
GO