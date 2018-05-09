USE [db2015]
GO
------------------数据表：开始----------------------
if exists (select 1
            from  sysobjects
           where  id = object_id('SysMenu')
            and   type = 'U')
   drop table SysMenu
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SysRole')
            and   type = 'U')
   drop table SysRole
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SysRoleRefMenu')
            and   type = 'U')
   drop table SysRoleRefMenu
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SysRoleRefUser')
            and   type = 'U')
   drop table SysRoleRefUser
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SysUser')
            and   type = 'U')
   drop table SysUser
go

/*==============================================================*/
/* Table: SysMenu                                               */
/*==============================================================*/
create table SysMenu (
   MenuId               int                  identity,
   Name                 nvarchar(25)         null,
   Url                  nvarchar(500)        null,
   Level                int                  null,
   PId                  int                  null,
   Sequ                 int                  null,
   State                int                  null,
   Note                 nvarchar(512)        null,
   CreateTime           datetime             null,
   CreateUser           int                  null,
   UpdateTime           datetime             null,
   UpdateUser           int                  null,
   constraint PK_SYSMENU primary key (MenuId)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('SysMenu') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'SysMenu' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   '菜单表', 
   'user', @CurrentUser, 'table', 'SysMenu'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysMenu')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'MenuId')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'MenuId'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '菜单Id',
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'MenuId'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysMenu')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'Name')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'Name'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '菜单名',
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'Name'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysMenu')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'Url')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'Url'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '菜单地址',
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'Url'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysMenu')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'Level')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'Level'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '级别',
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'Level'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysMenu')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'PId')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'PId'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '父级ID',
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'PId'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysMenu')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'Sequ')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'Sequ'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '顺序',
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'Sequ'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysMenu')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'State')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'State'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '状态',
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'State'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysMenu')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'Note')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'Note'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '备注',
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'Note'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysMenu')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CreateTime')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'CreateTime'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建时间',
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'CreateTime'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysMenu')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CreateUser')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'CreateUser'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建人',
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'CreateUser'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysMenu')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UpdateTime')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'UpdateTime'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '操作时间',
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'UpdateTime'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysMenu')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UpdateUser')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'UpdateUser'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '操作人',
   'user', @CurrentUser, 'table', 'SysMenu', 'column', 'UpdateUser'
go

/*==============================================================*/
/* Table: SysRole                                               */
/*==============================================================*/
create table SysRole (
   RoleId               int                  identity,
   Name                 nvarchar(100)        null,
   State                int                  null,
   Note                 nvarchar(512)        null,
   CreateTime           datetime             null,
   CreateUser           int                  null,
   UpdateTime           datetime             null,
   UpdateUser           int                  null,
   constraint PK_SYSROLE primary key (RoleId)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('SysRole') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'SysRole' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   '角色表', 
   'user', @CurrentUser, 'table', 'SysRole'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysRole')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RoleId')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysRole', 'column', 'RoleId'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '角色Id',
   'user', @CurrentUser, 'table', 'SysRole', 'column', 'RoleId'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysRole')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'Name')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysRole', 'column', 'Name'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '角色名',
   'user', @CurrentUser, 'table', 'SysRole', 'column', 'Name'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysRole')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'State')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysRole', 'column', 'State'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '状态',
   'user', @CurrentUser, 'table', 'SysRole', 'column', 'State'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysRole')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'Note')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysRole', 'column', 'Note'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '备注',
   'user', @CurrentUser, 'table', 'SysRole', 'column', 'Note'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysRole')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CreateTime')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysRole', 'column', 'CreateTime'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建时间',
   'user', @CurrentUser, 'table', 'SysRole', 'column', 'CreateTime'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysRole')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CreateUser')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysRole', 'column', 'CreateUser'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建人',
   'user', @CurrentUser, 'table', 'SysRole', 'column', 'CreateUser'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysRole')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UpdateTime')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysRole', 'column', 'UpdateTime'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '操作时间',
   'user', @CurrentUser, 'table', 'SysRole', 'column', 'UpdateTime'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysRole')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UpdateUser')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysRole', 'column', 'UpdateUser'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '操作人',
   'user', @CurrentUser, 'table', 'SysRole', 'column', 'UpdateUser'
go

/*==============================================================*/
/* Table: SysRoleRefMenu                                        */
/*==============================================================*/
create table SysRoleRefMenu (
   RefId                int                  identity,
   RoleId               int                  null,
   MenuId               int                  null,
   constraint PK_SYSROLEREFMENU primary key (RefId)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('SysRoleRefMenu') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'SysRoleRefMenu' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   '角色Ref菜单表', 
   'user', @CurrentUser, 'table', 'SysRoleRefMenu'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysRoleRefMenu')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RoleId')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysRoleRefMenu', 'column', 'RoleId'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '角色Id',
   'user', @CurrentUser, 'table', 'SysRoleRefMenu', 'column', 'RoleId'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysRoleRefMenu')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'MenuId')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysRoleRefMenu', 'column', 'MenuId'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '菜单Id',
   'user', @CurrentUser, 'table', 'SysRoleRefMenu', 'column', 'MenuId'
go

/*==============================================================*/
/* Table: SysRoleRefUser                                        */
/*==============================================================*/
create table SysRoleRefUser (
   RefId                int                  identity,
   RoleId               int                  null,
   UserId               int                  null,
   constraint PK_SYSROLEREFUSER primary key (RefId)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('SysRoleRefUser') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'SysRoleRefUser' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   '角色-用户表', 
   'user', @CurrentUser, 'table', 'SysRoleRefUser'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysRoleRefUser')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RoleId')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysRoleRefUser', 'column', 'RoleId'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '角色Id',
   'user', @CurrentUser, 'table', 'SysRoleRefUser', 'column', 'RoleId'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysRoleRefUser')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UserId')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysRoleRefUser', 'column', 'UserId'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户ID',
   'user', @CurrentUser, 'table', 'SysRoleRefUser', 'column', 'UserId'
go

/*==============================================================*/
/* Table: SysUser                                               */
/*==============================================================*/
create table SysUser (
   UserId               int                  identity,
   Name                 nvarchar(20)         null,
   Sex                  int                  null,
   Account              nvarchar(20)         null,
   Pwd                  nvarchar(400)        null,
   State                int                  null,
   Note                 nvarchar(512)        null,
   CreateTime           datetime             null,
   CreateUser           int                  null,
   UpdateTime           datetime             null,
   UpdateUser           int                  null,
   constraint PK_SYSUSER primary key (UserId)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('SysUser') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'SysUser' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   '用户表', 
   'user', @CurrentUser, 'table', 'SysUser'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysUser')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UserId')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'UserId'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户ID',
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'UserId'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysUser')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'Name')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'Name'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户名',
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'Name'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysUser')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'Sex')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'Sex'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '性别',
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'Sex'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysUser')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'Account')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'Account'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '账号',
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'Account'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysUser')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'Pwd')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'Pwd'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '密码',
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'Pwd'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysUser')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'State')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'State'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '状态',
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'State'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysUser')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'Note')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'Note'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '备注',
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'Note'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysUser')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CreateTime')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'CreateTime'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建时间',
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'CreateTime'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysUser')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CreateUser')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'CreateUser'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建人',
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'CreateUser'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysUser')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UpdateTime')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'UpdateTime'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '操作时间',
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'UpdateTime'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('SysUser')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'UpdateUser')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'UpdateUser'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '操作人',
   'user', @CurrentUser, 'table', 'SysUser', 'column', 'UpdateUser'
go

-------------------------------数据表：结束--------------------------------------------











-------------------------------函数：开始----------------------------------------------
--==============================fn_splitStr 分割字符串：开始=================================
/****** Object:  UserDefinedFunction [dbo].[fn_splitStr]    Script Date: 02/07/2015 22:53:47 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[fn_splitStr]') AND type IN (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[fn_splitStr]
GO

/****** Object:  UserDefinedFunction [dbo].[fn_splitStr]    Script Date: 02/07/2015 22:53:47 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		
-- Create date: 2014-09-11
-- Description:	分割字符串
-- =============================================
CREATE FUNCTION [dbo].[fn_splitStr]
(
	@str VARCHAR(8000)--字符串
	,@split VARCHAR(100)--分割符
)
RETURNS @table TABLE 
(
	id INT IDENTITY(1,1)
	,col VARCHAR(100)				
)
AS
BEGIN
	DECLARE @cur VARCHAR(100)
	DECLARE @index INT
	SET @index=CHARINDEX(@split,@str)
	WHILE @index>0
		BEGIN
			SET @cur=LTRIM(RTRIM(SUBSTRING(@str,1,@index-1)))
			IF @cur !=''
				INSERT INTO @table (col) VALUES (@cur)
			SET @str=LTRIM(RTRIM(SUBSTRING(@str,@index+1,LEN(@str))))
			SET @index=CHARINDEX(@split,@str)
			IF @index=0 AND @str!=''
				INSERT INTO @table (col) VALUES (@str)
		END
	RETURN 
END

GO
--==============================fn_splitStr 分割字符串：结束=================================
-------------------------------函数：结束--------------------------------------------








-------------------------------存储过程：开始----------------------------------------
--==============================pro_paging 分页存储过程：开始=================================
/****** Object:  StoredProcedure [dbo].[pro_paging]    Script Date: 02/07/2015 22:55:19 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pro_paging]') AND type IN (N'P', N'PC'))
DROP PROCEDURE [dbo].[pro_paging]
GO

/****** Object:  StoredProcedure [dbo].[pro_paging]    Script Date: 02/07/2015 22:55:19 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		
-- Create date: 2014-09-11
-- Description:	分页
-- =============================================
CREATE PROCEDURE [dbo].[pro_paging]
	@table NVARCHAR(4000)		--表
	,@field NVARCHAR(4000)='*'	--字段
	,@where NVARCHAR(4000)=''	--条件
	,@sort NVARCHAR(4000)		--排序
	,@psize INT =10				--每页大小
	,@pindex INT=1				--当前页
	,@rownum INT =0 OUTPUT		--总数据量
AS
BEGIN

	SET NOCOUNT ON;

    DECLARE @sql NVARCHAR(4000)
	SET @sql=N'select @rownum=count(1) from '+@table+' where 1=1 '+@where
	EXEC sys.sp_executesql @sql,N'@rownum int OUTPUT',@rownum OUTPUT
	DECLARE @snum INT
	DECLARE @enum INT
	SET @snum=(@pindex-1)*@psize+1
	SET @enum=@psize*@pindex
	SET @sql=N'select * from ('
	+N'select '+@field+N',ROW_NUMBER() OVER(ORDER BY '+@sort+N' ) [_rnum] from '+@table
	+N' where 1=1 '+@where+' ) _table where _rnum between '+CONVERT(VARCHAR(100),@snum)+N' and '+CONVERT(VARCHAR(100),@enum)
	EXEC sys.sp_executesql @sql
END


GO
--==============================pro_paging 分页存储过程：结束=================================
-------------------------------存储过程：结束----------------------------------------
