DROP TABLE IF EXISTS sequence;  
CREATE TABLE sequence (  
         name VARCHAR(50) NOT NULL,  
         current_value INT NOT NULL,  
         increment INT NOT NULL DEFAULT 1,  
         PRIMARY KEY (name)  
);

DROP FUNCTION IF EXISTS currval; 
CREATE FUNCTION currval (seq_name VARCHAR(50))  
         RETURNS INTEGER
BEGIN  
         DECLARE value INTEGER;  
         SET value = 0;  
         SELECT current_value INTO value  
                   FROM sequence  
                   WHERE name = seq_name;  
         RETURN value;  
END;  


DROP FUNCTION IF EXISTS nextval;
CREATE FUNCTION nextval (seq_name VARCHAR(50))  
         RETURNS INTEGER 
BEGIN  
         UPDATE sequence  
                   SET current_value = current_value + increment  
                   WHERE name = seq_name;  
         RETURN currval(seq_name);  
END;  


DROP FUNCTION IF EXISTS setval; 
CREATE FUNCTION setval (seq_name VARCHAR(50), value INTEGER)  
         RETURNS INTEGER 
BEGIN  
         UPDATE sequence  
                   SET current_value = value  
                   WHERE name = seq_name;  
         RETURN currval(seq_name);  
END;  


INSERT INTO sequence VALUES ('TestSeq', 0, 1);


/*SELECT SETVAL('TestSeq', 10);---设置指定sequence的初始值
SELECT CURRVAL('TestSeq');--查询指定sequence的当前值
SELECT NEXTVAL('TestSeq');--查询指定sequence的下一个值
*/




drop table if exists DM_PERMISSION;

drop table if exists DM_RES_TYPE;

drop table if exists QX_RESOURCES;

drop table if exists QX_ROLE;

drop table if exists QX_ROLE_RESOURCES;

drop table if exists QX_USER;

drop table if exists QX_USER_ROLE;

/*==============================================================*/
/* Table: DM_PERMISSION                                         */
/*==============================================================*/
create table DM_PERMISSION
(
   PERMISSION_DM        int(2) not null comment '权限',
   PERMISSION_MC        varchar(80) not null comment '权限名称',
   YXBZ                 char(1) not null default 'Y' comment '有效标志',
   primary key (PERMISSION_DM)
);

alter table DM_PERMISSION comment 'DM_PERMISSION';

/*==============================================================*/
/* Table: DM_RES_TYPE                                           */
/*==============================================================*/
create table DM_RES_TYPE
(
   RES_TYPE_DM          varchar(2) not null comment '资源类型',
   RES_TYPE_MC          varchar(80) not null comment '资源类型名称',
   YXBZ                 char(1) not null default 'Y' comment '有效标志',
   primary key (RES_TYPE_DM)
);

alter table DM_RES_TYPE comment 'DM_RES_TYPE';

/*==============================================================*/
/* Table: QX_RESOURCES                                          */
/*==============================================================*/
create table QX_RESOURCES
(
   UUID                 varchar(32) not null comment 'UUID',
   NAME                 varchar(80) not null comment '名称',
   PARENTUUID           varchar(32) not null comment '父亲UUID',
   LAYER                int(11) not null comment '层级',
   RES_TYPE_DM          varchar(2) not null comment '类型',
   RESURL               varchar(512) comment '资源URL',
   DESCRIPTION          varchar(100) comment '描述',
   LRRQ                 date not null comment '录入日期',
   XGRQ                 date not null comment '修改日期',
   YXBZ                 char(1) not null default 'Y' comment '有效标志',
   primary key (UUID)
);

alter table QX_RESOURCES comment '资源表';

/*==============================================================*/
/* Table: QX_ROLE                                               */
/*==============================================================*/
create table QX_ROLE
(
   UUID                 varchar(32) not null comment 'UUID',
   NAME                 varchar(80) not null comment '名称',
   DESCRIPTION          varchar(100) comment '描述',
   LRRQ                 date not null comment '录入日期',
   XGRQ                 date not null comment '修改日期',
   YXBZ                 char(1) not null default 'Y' comment '有效标志',
   primary key (UUID)
);

alter table QX_ROLE comment '角色';

/*==============================================================*/
/* Table: QX_ROLE_RESOURCES                                     */
/*==============================================================*/
create table QX_ROLE_RESOURCES
(
   ROLE_UUID            varchar(32) not null comment '角色UUID',
   RESOURCES_UUID       varchar(32) not null comment '资源UUID',
   PERMISSION_DM        int(2) not null default 7 comment '权限',
   primary key (ROLE_UUID, RESOURCES_UUID)
);

alter table QX_ROLE_RESOURCES comment '角色资源';

/*==============================================================*/
/* Table: QX_USER                                               */
/*==============================================================*/
create table QX_USER
(
   UUID                 varchar(32) not null comment 'UUID',
   LOGINNAME            varchar(32) not null comment '登陆名称',
   PASSWORD             varchar(32) not null comment '密码',
   SALT                 varchar(32) not null comment '椒盐',
   NAME                 varchar(80) not null comment '名称',
   EMAIL                varchar(255) comment '邮件',
   DESCRIPTION          varchar(100) comment '描述',
   LRRQ                 date not null comment '录入日期',
   XGRQ                 date not null comment '修改日期',
   YXBZ                 char(1) not null default 'Y' comment '有效标志',
   primary key (UUID)
);

alter table QX_USER comment '用户';

/*==============================================================*/
/* Index: IDX_QX_USER_LOGINNAME                                 */
/*==============================================================*/
create unique index IDX_QX_USER_LOGINNAME on QX_USER
(
   LOGINNAME
);

/*==============================================================*/
/* Table: QX_USER_ROLE                                          */
/*==============================================================*/
create table QX_USER_ROLE
(
   USER_UUID            varchar(32) not null comment '用户UUID',
   ROLE_UUID            varchar(32) not null comment '角色UUID',
   primary key (USER_UUID, ROLE_UUID)
);

alter table QX_USER_ROLE comment '用户角色';




