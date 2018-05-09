drop table if exists ref_role_perm;

drop table if exists ref_user_role;

drop table if exists sys_area;

drop table if exists sys_permission;

drop table if exists sys_role;

drop table if exists sys_user;

/*==============================================================*/
/* Table: ref_role_perm                                         */
/*==============================================================*/
create table ref_role_perm
(
   ref_id               varchar(32) not null comment 'ref_id',
   perm_id              varchar(32) comment '资源id',
   role_id              varchar(32) comment '角色id',
   primary key (ref_id)
);

alter table ref_role_perm comment 'ref-角色-权限';

/*==============================================================*/
/* Table: ref_user_role                                         */
/*==============================================================*/
create table ref_user_role
(
   ref_id               varchar(32) not null comment 'ref_id',
   user_id              varchar(32) comment '用户ID',
   role_id              varchar(32) comment '角色id',
   primary key (ref_id)
);

alter table ref_user_role comment 'ref-用户-角色';

/*==============================================================*/
/* Table: sys_area                                              */
/*==============================================================*/
create table sys_area
(
   area_id              int not null comment 'area_id',
   area_name            varchar(128) comment 'area_name',
   area_pid             int comment 'area_pid',
   area_seq             int comment 'area_seq',
   area_level           int comment 'area_level',
   area_status          int comment 'area_status[0:可用, 1:不可用]',
   create_time          datetime comment 'create_time',
   create_user          varchar(32) comment 'create_user',
   update_time          datetime comment 'update_time',
   update_user          varchar(32) comment 'update_user',
   primary key (area_id)
);

alter table sys_area comment '区域';

/*==============================================================*/
/* Table: sys_permission                                        */
/*==============================================================*/
create table sys_permission
(
   perm_id              varchar(32) not null comment '资源id',
   perm_name            varchar(64) comment '资源名',
   perm_pid             varchar(32) comment 'pid',
   perm_type            int comment '类型[0:菜单, 1:功能]',
   perm_seq             int comment '排序',
   perm_url             varchar(512) comment 'url',
   perm_code            varchar(64) comment 'code',
   perm_icon            varchar(256) comment '图标',
   perm_status          int comment '状态[0:可用, 1:不可用]',
   perm_note            varchar(128) comment '备注',
   create_time          datetime comment 'create_time',
   create_user          varchar(32) comment 'create_user',
   update_time          datetime comment 'update_time',
   update_user          varchar(32) comment 'update_user',
   primary key (perm_id)
);

alter table sys_permission comment '系统资源';

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   role_id              varchar(32) not null comment '角色id',
   role_name            varchar(32) comment 'role_name',
   role_code            varchar(32) comment 'role_code',
   role_note            varchar(128) comment 'role_note',
   role_status          int comment 'role_status[0:可用, 1:不可用]',
   create_time          datetime comment 'create_time',
   create_user          varchar(32) comment 'create_user',
   update_time          datetime comment 'update_time',
   update_user          varchar(32) comment 'update_user',
   primary key (role_id)
);

alter table sys_role comment '系统角色';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   user_id              varchar(32) not null comment '用户ID',
   user_name            varchar(64) comment '用户姓名',
   user_account         varchar(64) comment '账号',
   user_pwd             varchar(64) comment '密码',
   user_sex             int comment '性别[0:男, 1:女]',
   user_phone           varchar(11) comment '手机号',
   user_status          int comment '状态[0:可用, 1:不可用]',
   user_note            varchar(128) comment '备注',
   create_time          datetime comment 'create_time',
   create_user          varchar(32) comment 'create_user',
   update_time          datetime comment 'update_time',
   update_user          varchar(32) comment 'update_user',
   primary key (user_id)
);

alter table sys_user comment '系统用户';
