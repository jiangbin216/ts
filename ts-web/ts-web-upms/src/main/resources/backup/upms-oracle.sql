/*
* @Author: YL
* @Date:   2017-07-18 09:13:04
* @Last Modified by:   YL
* @Last Modified time: 2017-07-18 10:28:52
*/
-- ----------------------------
--  sequence structure for `SQ_UPMS`
-- ----------------------------
-- Create sequence
create sequence SQ_UPMS
minvalue 1
maxvalue 9999999999999999
start with 9999
increment by 1
cache 2000;
-- ----------------------------
--  Table structure for `upms_log`
-- ----------------------------
-- Create table
create table UPMS_LOG
(
  id          NUMBER not null,
  DESCRIPTION         VARCHAR2(20),
  username    VARCHAR2(20),
  start_time  DATE default sysdate,
  spend_time  NUMBER,
  BASE_PATH        VARCHAR2(500),
  uri         VARCHAR2(500),
  url         VARCHAR2(500),
  method      VARCHAR2(10),
  user_agent  VARCHAR2(500),
  ip          VARCHAR2(500),
  permissions VARCHAR2(100),
  parameter   VARCHAR2(2000),
  result      VARCHAR2(2000)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table UPMS_LOG
  is 'upms系统-用户操作日志（created by yl on 2017-07-18）';
-- Add comments to the columns 
comment on column UPMS_LOG.id
  is '主键';
comment on column UPMS_LOG.des
  is '操作描述';
comment on column UPMS_LOG.username
  is '操作用户';
comment on column UPMS_LOG.start_time
  is '操作时间';
comment on column UPMS_LOG.spend_time
  is '消耗时间';
comment on column UPMS_LOG.path
  is '根路径';
comment on column UPMS_LOG.uri
  is 'URI';
comment on column UPMS_LOG.url
  is 'URL';
comment on column UPMS_LOG.method
  is '请求类型';
comment on column UPMS_LOG.user_agent
  is '用户标识';
comment on column UPMS_LOG.ip
  is 'IP地址';
comment on column UPMS_LOG.permissions
  is '权限值';
comment on column UPMS_LOG.parameter
  is '请求参数值';
comment on column UPMS_LOG.result
  is '请求返回结果值';
-- Create/Recreate primary, unique and foreign key constraints 
alter table UPMS_LOG
  add constraint PK_UPMS_LOG primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

INSERT INTO upms_log VALUES ('1', '后台首页', 'admin', to_date('2017-05-26 12:00:00', 'yyyy-mm-dd hh24:mi:ss'), '34', 'http://localhost:1111', '/manage/index', 'http://localhost:1111/manage/index', 'GET', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36', '127.0.0.1', null, null, null);
INSERT INTO upms_log VALUES ('2', '登录', 'admin', to_date('2017-05-26 12:00:00', 'yyyy-mm-dd hh24:mi:ss'), '3546', 'http://open.ts.cn:2222', '/auth/login', 'http://open.ts.cn:2222/auth/login', 'POST', 'curl/7.51.0', '127.0.0.1', null, '{username=[admin],backurl=[http://open.ts.cn:2222/manage/user/list],password=[123456],rememberMe=[false]}', null);
COMMIT;

-- ----------------------------
--  Table structure for `upms_organization`
-- ----------------------------
-- Create table
create table UPMS_ORGANIZATION
(
  id          NUMBER not null,
  pid         NUMBER,
  name        VARCHAR2(20),
  des         VARCHAR2(1000),
  create_time DATE default sysdate
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table UPMS_ORGANIZATION
  is 'upms系统-组织表（created by yl on 2017-07-18）';
-- Add comments to the columns 
comment on column UPMS_ORGANIZATION.id
  is '主键';
comment on column UPMS_ORGANIZATION.pid
  is '所属上级';
comment on column UPMS_ORGANIZATION.name
  is '组织名称';
comment on column UPMS_ORGANIZATION.des
  is '组织描述';
comment on column UPMS_ORGANIZATION.create_time
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table UPMS_ORGANIZATION
  add constraint PK_UPMS_ORGANIZATION primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;

INSERT INTO upms_organization VALUES ('1', null, '总部', '北京总部', to_date('2017-05-26 12:00:00', 'yyyy-mm-dd hh24:mi:ss'));
COMMIT;

-- ----------------------------
--  Table structure for `upms_permission`
-- ----------------------------
-- Create table
create table UPMS_PERMISSION
(
  id               NUMBER not null,
  system_id        NUMBER,
  pid              NUMBER,
  name             VARCHAR2(20),
  type             NUMBER,
  permission_value VARCHAR2(50),
  uri              VARCHAR2(100),
  icon             VARCHAR2(100),
  status           NUMBER default 1,
  create_time      DATE default sysdate,
  orders           NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table UPMS_PERMISSION
  is 'upms系统-权限表（created by yl on 2017-07-18）';
-- Add comments to the columns 
comment on column UPMS_PERMISSION.id
  is '主键';
comment on column UPMS_PERMISSION.system_id
  is '所属系统';
comment on column UPMS_PERMISSION.pid
  is '所属上级';
comment on column UPMS_PERMISSION.name
  is '名称';
comment on column UPMS_PERMISSION.type
  is '类型(1:目录,2:菜单,3:按钮)';
comment on column UPMS_PERMISSION.permission_value
  is '权限值';
comment on column UPMS_PERMISSION.uri
  is '路径';
comment on column UPMS_PERMISSION.icon
  is '图标';
comment on column UPMS_PERMISSION.status
  is '状态(0:禁止,1:正常)';
comment on column UPMS_PERMISSION.create_time
  is '创建时间';
comment on column UPMS_PERMISSION.orders
  is '排序';
-- Create/Recreate primary, unique and foreign key constraints 
alter table UPMS_PERMISSION
  add constraint PK_UPMS_PERMISSION primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;

INSERT INTO upms_permission VALUES ('1', '1', '0', '系统组织管理', '1', '', '', 'zmdi zmdi-accounts-list', '1', sysdate, '1');
INSERT INTO upms_permission values ('2', '1', '1', '系统管理', '2', 'upms:system:read', '/manage/system/index', '', '1', sysdate, '2');
INSERT INTO upms_permission values  ('3', '1', '1', '组织管理', '2', 'upms:organization:read', '/manage/organization/index', '', '1', sysdate, '3');
 INSERT INTO upms_permission values ('4', '1', '0', '角色用户管理', '1', '', '', 'zmdi zmdi-accounts', '1', sysdate, '4');
 INSERT INTO upms_permission values ('5', '1', '4', '角色管理', '2', 'upms:role:read', '/manage/role/index', '', '1', sysdate, '6');
 INSERT INTO upms_permission values ('6', '1', '4', '用户管理', '2', 'upms:user:read', '/manage/user/index', '', '1', sysdate, '5');
 INSERT INTO upms_permission values ('7', '1', '0', '权限资源管理', '1', '', '', 'zmdi zmdi-lock-outline', '1', sysdate, '7');
 INSERT INTO upms_permission values ('12', '1', '0', '其他数据管理', '1', '', '', 'zmdi zmdi-more', '1', sysdate, '12');
 INSERT INTO upms_permission values ('14', '1', '12', '会话管理', '2', 'upms:session:read', '/manage/session/index', '', '1', sysdate, '14');
 INSERT INTO upms_permission values ('15', '1', '12', '日志记录', '2', 'upms:log:read', '/manage/log/index', '', '1', sysdate, '15');
 INSERT INTO upms_permission values ('24', '1', '2', '新增系统', '3', 'upms:system:create', '/manage/system/create', 'zmdi zmdi-plus', '1', sysdate, '24');
 INSERT INTO upms_permission values ('25', '1', '2', '编辑系统', '3', 'upms:system:update', '/manage/system/update', 'zmdi zmdi-edit', '1', sysdate, '25');
 INSERT INTO upms_permission values ('26', '1', '2', '删除系统', '3', 'upms:system:delete', '/manage/system/delete', 'zmdi zmdi-close', '1', sysdate, '26');
 INSERT INTO upms_permission values ('27', '1', '3', '新增组织', '3', 'upms:organization:create', '/manage/organization/create', 'zmdi zmdi-plus', '1', sysdate, '27');
 INSERT INTO upms_permission values ('28', '1', '3', '编辑组织', '3', 'upms:organization:update', '/manage/organization/update', 'zmdi zmdi-edit', '1', sysdate, '28');
 INSERT INTO upms_permission values ('29', '1', '3', '删除组织', '3', 'upms:organization:delete', '/manage/organization/delete', 'zmdi zmdi-close', '1', sysdate, '29');
INSERT INTO upms_permission values  ('30', '1', '6', '新增用户', '3', 'upms:user:create', '/manage/user/create', 'zmdi zmdi-plus', '1', sysdate, '30');
INSERT INTO upms_permission values  ('31', '1', '6', '编辑用户', '3', 'upms:user:update', '/manage/user/update', 'zmdi zmdi-edit', '1', sysdate, '31');
INSERT INTO upms_permission values  ('32', '1', '6', '删除用户', '3', 'upms:user:delete', '/manage/user/delete', 'zmdi zmdi-close', '1', sysdate, '32');
INSERT INTO upms_permission values  ('33', '1', '5', '新增角色', '3', 'upms:role:create', '/manage/role/create', 'zmdi zmdi-plus', '1', sysdate, '33');
INSERT INTO upms_permission values  ('34', '1', '5', '编辑角色', '3', 'upms:role:update', '/manage/role/update', 'zmdi zmdi-edit', '1', sysdate, '34');
 INSERT INTO upms_permission values ('35', '1', '5', '删除角色', '3', 'upms:role:delete', '/manage/role/delete', 'zmdi zmdi-close', '1', sysdate, '35');
 INSERT INTO upms_permission values ('36', '1', '39', '新增权限', '3', 'upms:permission:create', '/manage/permission/create', 'zmdi zmdi-plus', '1', sysdate, '36');
 INSERT INTO upms_permission values ('37', '1', '39', '编辑权限', '3', 'upms:permission:update', '/manage/permission/update', 'zmdi zmdi-edit', '1', sysdate, '37');
 INSERT INTO upms_permission values ('38', '1', '39', '删除权限', '3', 'upms:permission:delete', '/manage/permission/delete', 'zmdi zmdi-close', '1', sysdate, '38');
 INSERT INTO upms_permission values ('39', '1', '7', '权限管理', '2', 'upms:permission:read', '/manage/permission/index', NULL, '1', sysdate, '39');
 INSERT INTO upms_permission values ('46', '1', '5', '角色权限', '3', 'upms:role:permission', '/manage/role/permission', 'zmdi zmdi-key', '1', sysdate, '40');
 INSERT INTO upms_permission values ('48', '1', '6', '用户组织', '3', 'upms:user:organization', '/manage/user/organization', 'zmdi zmdi-accounts-list', '1', sysdate, '41');
 INSERT INTO upms_permission values ('50', '1', '6', '用户角色', '3', 'upms:user:role', '/manage/user/role', 'zmdi zmdi-accounts', '1', sysdate, '42');
 INSERT INTO upms_permission values ('51', '1', '6', '用户权限', '3', 'upms:user:permission', '/manage/user/permission', 'zmdi zmdi-key', '1', sysdate, '43');
 INSERT INTO upms_permission values ('53', '1', '14', '强制退出', '3', 'upms:session:forceout', '/manage/session/forceout', 'zmdi zmdi-run', '1', sysdate, '44');
 INSERT INTO upms_permission values ('57', '1', '15', '删除权限', '3', 'upms:log:delete', '/manage/log/delete', 'zmdi zmdi-close', '1', sysdate, '45');
COMMIT;

-- ----------------------------
--  Table structure for `upms_role`
-- ----------------------------
-- Create table
create table UPMS_ROLE
(
  id          NUMBER not null,
  name        VARCHAR2(20),
  title       VARCHAR2(20),
  des         VARCHAR2(500),
  create_time DATE,
  orders      NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table UPMS_ROLE
  is 'upms系统-角色表（created by yl on 2017-07-18）';
-- Add comments to the columns 
comment on column UPMS_ROLE.id
  is '主键';
comment on column UPMS_ROLE.name
  is '角色名称';
comment on column UPMS_ROLE.title
  is '角色标题';
comment on column UPMS_ROLE.des
  is '角色描述';
comment on column UPMS_ROLE.create_time
  is '创建时间';
comment on column UPMS_ROLE.orders
  is '排序';
-- Create/Recreate primary, unique and foreign key constraints 
alter table UPMS_ROLE
  add constraint PK_UPMS_ROLE primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;

INSERT INTO upms_role VALUES ('1', 'super', '超级管理员', '拥有所有权限', sysdate, '1');
INSERT INTO upms_role VALUES('2', 'admin', '管理员', '拥有除权限管理系统外的所有权限', sysdate, '2');
COMMIT;

-- ----------------------------
--  Table structure for `upms_role_permission`
-- ----------------------------
-- Create table
create table UPMS_ROLE_PERMISSION
(
  id            NUMBER not null,
  role_id       NUMBER,
  permission_id NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table UPMS_ROLE_PERMISSION
  is 'upms系统-角色权限表（created by yl on 2017-07-18）';
-- Add comments to the columns 
comment on column UPMS_ROLE_PERMISSION.id
  is '主键';
comment on column UPMS_ROLE_PERMISSION.role_id
  is '角色表主键';
comment on column UPMS_ROLE_PERMISSION.permission_id
  is '权限表主键';
-- Create/Recreate primary, unique and foreign key constraints 
alter table UPMS_ROLE_PERMISSION
  add constraint PK_UPMS_ROLE_PERMISSION primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;

INSERT INTO upms_role_permission VALUES ('1', '1', '1');
INSERT INTO upms_role_permission VALUES ('2', '1', '2');
INSERT INTO upms_role_permission VALUES ('3', '1', '3');
INSERT INTO upms_role_permission VALUES ('4', '1', '4');
INSERT INTO upms_role_permission VALUES ('5', '1', '5');
INSERT INTO upms_role_permission VALUES ('6', '1', '6');
INSERT INTO upms_role_permission VALUES ('7', '1', '7');
INSERT INTO upms_role_permission VALUES ('8', '1', '39');
INSERT INTO upms_role_permission VALUES ('12', '1', '12');
INSERT INTO upms_role_permission VALUES ('14', '1', '14');
INSERT INTO upms_role_permission VALUES ('15', '1', '15');
INSERT INTO upms_role_permission VALUES ('17', '1', '17');
INSERT INTO upms_role_permission VALUES ('19', '1', '19');
INSERT INTO upms_role_permission VALUES ('20', '1', '20');
INSERT INTO upms_role_permission VALUES ('21', '1', '21');
INSERT INTO upms_role_permission VALUES ('24', '1', '24');
INSERT INTO upms_role_permission VALUES ('27', '1', '27');
INSERT INTO upms_role_permission VALUES ('28', '1', '28');
INSERT INTO upms_role_permission VALUES ('29', '1', '29');
INSERT INTO upms_role_permission VALUES ('30', '1', '30');
INSERT INTO upms_role_permission VALUES ('31', '1', '31');
INSERT INTO upms_role_permission VALUES ('32', '1', '32');
INSERT INTO upms_role_permission VALUES ('33', '1', '33');
INSERT INTO upms_role_permission VALUES ('34', '1', '34');
INSERT INTO upms_role_permission VALUES ('35', '1', '35');
INSERT INTO upms_role_permission VALUES ('36', '1', '36');
INSERT INTO upms_role_permission VALUES ('37', '1', '37');
INSERT INTO upms_role_permission VALUES ('38', '1', '38');
INSERT INTO upms_role_permission VALUES ('39', '1', '46');
INSERT INTO upms_role_permission VALUES ('40', '1', '51');
INSERT INTO upms_role_permission VALUES ('44', '1', '48');
INSERT INTO upms_role_permission VALUES ('45', '1', '50');
INSERT INTO upms_role_permission VALUES ('47', '1', '53');
INSERT INTO upms_role_permission VALUES ('48', '1', '18');
INSERT INTO upms_role_permission VALUES ('49', '1', '54');
INSERT INTO upms_role_permission VALUES ('50', '1', '54');
INSERT INTO upms_role_permission VALUES ('51', '1', '55');
INSERT INTO upms_role_permission VALUES ('52', '1', '54');
INSERT INTO upms_role_permission VALUES ('53', '1', '55');
INSERT INTO upms_role_permission VALUES ('54', '1', '56');
INSERT INTO upms_role_permission VALUES ('55', '1', '57');
INSERT INTO upms_role_permission VALUES ('56', '1', '58');
INSERT INTO upms_role_permission VALUES ('57', '1', '58');
INSERT INTO upms_role_permission VALUES ('58', '1', '59');
INSERT INTO upms_role_permission VALUES ('59', '1', '60');
INSERT INTO upms_role_permission VALUES ('60', '1', '61');
INSERT INTO upms_role_permission VALUES ('61', '1', '62');
INSERT INTO upms_role_permission VALUES ('62', '1', '62');
INSERT INTO upms_role_permission VALUES ('63', '1', '63');
INSERT INTO upms_role_permission VALUES ('64', '1', '62');
INSERT INTO upms_role_permission VALUES ('65', '1', '63');
INSERT INTO upms_role_permission VALUES ('66', '1', '64');
INSERT INTO upms_role_permission VALUES ('67', '1', '62');
INSERT INTO upms_role_permission VALUES ('68', '1', '63');
INSERT INTO upms_role_permission VALUES ('69', '1', '64');
INSERT INTO upms_role_permission VALUES ('70', '1', '65');
INSERT INTO upms_role_permission VALUES ('71', '1', '62');
INSERT INTO upms_role_permission VALUES ('72', '1', '63');
INSERT INTO upms_role_permission VALUES ('73', '1', '64');
INSERT INTO upms_role_permission VALUES ('74', '1', '65');
INSERT INTO upms_role_permission VALUES ('75', '1', '66');
INSERT INTO upms_role_permission VALUES ('76', '1', '62');
INSERT INTO upms_role_permission VALUES ('77', '1', '63');
INSERT INTO upms_role_permission VALUES ('78', '1', '64');
INSERT INTO upms_role_permission VALUES ('79', '1', '65');
INSERT INTO upms_role_permission VALUES ('80', '1', '66');
INSERT INTO upms_role_permission VALUES ('81', '1', '67');
INSERT INTO upms_role_permission VALUES ('82', '1', '68');
INSERT INTO upms_role_permission VALUES ('83', '1', '69');
INSERT INTO upms_role_permission VALUES ('84', '1', '69');
INSERT INTO upms_role_permission VALUES ('85', '1', '70');
INSERT INTO upms_role_permission VALUES ('86', '1', '69');
INSERT INTO upms_role_permission VALUES ('87', '1', '70');
INSERT INTO upms_role_permission VALUES ('88', '1', '71');
INSERT INTO upms_role_permission VALUES ('89', '1', '72');
INSERT INTO upms_role_permission VALUES ('90', '1', '72');
INSERT INTO upms_role_permission VALUES ('91', '1', '73');
INSERT INTO upms_role_permission VALUES ('92', '1', '72');
INSERT INTO upms_role_permission VALUES ('93', '1', '73');
INSERT INTO upms_role_permission VALUES ('94', '1', '74');
INSERT INTO upms_role_permission VALUES ('95', '1', '72');
INSERT INTO upms_role_permission VALUES ('96', '1', '73');
INSERT INTO upms_role_permission VALUES ('97', '1', '74');
INSERT INTO upms_role_permission VALUES ('98', '1', '75');
INSERT INTO upms_role_permission VALUES ('99', '1', '76');
INSERT INTO upms_role_permission VALUES ('100', '1', '76');
INSERT INTO upms_role_permission VALUES ('101', '1', '77');
INSERT INTO upms_role_permission VALUES ('102', '1', '76');
INSERT INTO upms_role_permission VALUES ('103', '1', '77');
INSERT INTO upms_role_permission VALUES ('105', '1', '79');
INSERT INTO upms_role_permission VALUES ('106', '1', '80');
INSERT INTO upms_role_permission VALUES ('107', '1', '81');
INSERT INTO upms_role_permission VALUES ('108', '1', '81');
INSERT INTO upms_role_permission VALUES ('109', '1', '82');
INSERT INTO upms_role_permission VALUES ('110', '1', '81');
INSERT INTO upms_role_permission VALUES ('111', '1', '82');
INSERT INTO upms_role_permission VALUES ('112', '1', '83');
INSERT INTO upms_role_permission VALUES ('113', '1', '84');
INSERT INTO upms_role_permission VALUES ('114', '1', '84');
INSERT INTO upms_role_permission VALUES ('115', '1', '85');
INSERT INTO upms_role_permission VALUES ('121', '1', '78');
INSERT INTO upms_role_permission VALUES ('122', '1', '78');
INSERT INTO upms_role_permission VALUES ('124', '1', '25');
INSERT INTO upms_role_permission VALUES ('125', '1', '26');
COMMIT;

-- ----------------------------
--  Table structure for `upms_system`
-- ----------------------------
-- Create table
create table UPMS_SYSTEM
(
  id          NUMBER not null,
  name        VARCHAR2(50),
  title       VARCHAR2(50),
  des         VARCHAR2(500),
  icon        VARCHAR2(50),
  theme       VARCHAR2(50),
  BASEPATH   VARCHAR2(100),
  status      NUMBER default 1,
  create_time DATE default sysdate,
  orders      NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table UPMS_SYSTEM
  is 'upms系统-系统表（created by yl on 2017-07-18）';
-- Add comments to the columns 
comment on column UPMS_SYSTEM.id
  is '主键';
comment on column UPMS_SYSTEM.name
  is '系统名称';
comment on column UPMS_SYSTEM.title
  is '系统标题';
comment on column UPMS_SYSTEM.des
  is '系统描述';
comment on column UPMS_SYSTEM.icon
  is '系统图标';
comment on column UPMS_SYSTEM.theme
  is '系统主题';
comment on column UPMS_SYSTEM.base_path
  is '系统跟目录';
comment on column UPMS_SYSTEM.status
  is '系统状态(-1:黑名单,1:正常';
comment on column UPMS_SYSTEM.create_time
  is '创建时间';
comment on column UPMS_SYSTEM.orders
  is '排序';
-- Create/Recreate primary, unique and foreign key constraints 
alter table UPMS_SYSTEM
  add constraint PK_UPMS_SYSTEM primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;

INSERT INTO upms_system values ('1', 'ts-upms-web',  '权限管理系统', '用户权限管理系统（RBAC细粒度用户权限、统一后台、单点登录、会话管理）', 'zmdi zmdi-shield-security', '#29A176', 'http://localhost:8090', '1', sysdate, '1');
COMMIT;

-- ----------------------------
--  Table structure for `upms_user`
-- ----------------------------
-- Create table
create table UPMS_USER
(
  id          NUMBER not null,
  username    VARCHAR2(20),
  password    VARCHAR2(32),
  salt        VARCHAR2(32),
  realname   VARCHAR2(20),
  avatar      VARCHAR2(50),
  phone       VARCHAR2(20),
  email       VARCHAR2(50),
  sex         NUMBER,
  locked      NUMBER default 0,
  create_time DATE default sysdate
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table UPMS_USER
  is 'upms系统-用户表（created by yl on 2017-07-18）';
-- Add comments to the columns 
comment on column UPMS_USER.id
  is '主键';
comment on column UPMS_USER.username
  is '帐号';
comment on column UPMS_USER.password
  is '密码MD5(密码+盐)';
comment on column UPMS_USER.salt
  is '盐';
comment on column UPMS_USER.real_name
  is '真实姓名';
comment on column UPMS_USER.avatar
  is '头像';
comment on column UPMS_USER.phone
  is '电话';
comment on column UPMS_USER.email
  is '邮箱';
comment on column UPMS_USER.sex
  is '性别';
comment on column UPMS_USER.locked
  is '状态(0:正常,1:锁定)';
comment on column UPMS_USER.create_time
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table UPMS_USER
  add constraint PK_UPMS_USER primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;

INSERT INTO upms_user VALUES
  ('1', 'admin', '3038D9CB63B3152A79B8153FB06C02F7', '66f1b370c660445a8657bf8bf1794486', 'ts',
        '/resources/images/avatar.jpg', '110', '119@qq.com', '1', '0', sysdate);
COMMIT;

-- ----------------------------
--  Table structure for `upms_user_organization`
-- ----------------------------
-- Create table
create table UPMS_USER_ORGANIZATION
(
  id              NUMBER not null,
  user_id         NUMBER,
  organization_id NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table UPMS_USER_ORGANIZATION
  is 'upms系统-用户组织关联表（created by yl on 2017-07-18）';
-- Add comments to the columns 
comment on column UPMS_USER_ORGANIZATION.id
  is '主键';
comment on column UPMS_USER_ORGANIZATION.user_id
  is '用户表主键';
comment on column UPMS_USER_ORGANIZATION.organization_id
  is '组织表主键';
-- Create/Recreate primary, unique and foreign key constraints 
alter table UPMS_USER_ORGANIZATION
  add constraint PK_UPMS_USER_ORGANIZATION primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;

INSERT INTO upms_user_organization VALUES ('19', '1', '1');
INSERT INTO upms_user_organization VALUES('20', '1', '4');
INSERT INTO upms_user_organization VALUES('21', '1', '5');
INSERT INTO upms_user_organization VALUES('22', '1', '6');
INSERT INTO upms_user_organization VALUES('23', '1', '7');
COMMIT;

-- ----------------------------
--  Table structure for `upms_user_permission`
-- ----------------------------
-- Create table
create table UPMS_USER_PERMISSION
(
  id            NUMBER not null,
  user_id       NUMBER,
  permission_id NUMBER,
  type          NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table UPMS_USER_PERMISSION
  is 'upms系统-用户权限关联表（created by yl on 2017-07-18）';
-- Add comments to the columns 
comment on column UPMS_USER_PERMISSION.id
  is '主键';
comment on column UPMS_USER_PERMISSION.user_id
  is '用户表主键';
comment on column UPMS_USER_PERMISSION.permission_id
  is '权限表主键';
comment on column UPMS_USER_PERMISSION.type
  is '权限类型(-1:减权限,1:增权限)';
-- Create/Recreate primary, unique and foreign key constraints 
alter table UPMS_USER_PERMISSION
  add constraint PK_UPMS_USER_PERMISSION primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;

INSERT INTO upms_user_permission VALUES ('3', '1', '22', '-1');
INSERT INTO upms_user_permission VALUES ('4', '1', '22', '1');
COMMIT;

-- ----------------------------
--  Table structure for `upms_user_role`
-- ----------------------------
-- Create table
create table UPMS_USER_ROLE
(
  id      NUMBER not null,
  user_id NUMBER,
  role_id NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table UPMS_USER_ROLE
  is 'upms系统-用户角色关联表（created by yl on 2017-07-18）';
-- Add comments to the columns 
comment on column UPMS_USER_ROLE.id
  is '主键';
comment on column UPMS_USER_ROLE.user_id
  is '用户表主键';
comment on column UPMS_USER_ROLE.role_id
  is '角色表主键';
-- Create/Recreate primary, unique and foreign key constraints 
alter table UPMS_USER_ROLE
  add constraint PK_UPMS_USER_ROLE primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;

INSERT INTO upms_user_role VALUES ('4', '1', '1');
INSERT INTO upms_user_role VALUES ('5', '1', '2');
COMMIT;
