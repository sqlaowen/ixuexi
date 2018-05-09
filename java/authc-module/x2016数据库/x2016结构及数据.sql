/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : x2016

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2016-05-24 16:38:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ref_role_perm
-- ----------------------------
DROP TABLE IF EXISTS `ref_role_perm`;
CREATE TABLE `ref_role_perm` (
  `ref_id` varchar(32) NOT NULL COMMENT 'ref_id',
  `perm_id` varchar(32) DEFAULT NULL COMMENT '资源id',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`ref_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ref-角色-权限';

-- ----------------------------
-- Records of ref_role_perm
-- ----------------------------
INSERT INTO `ref_role_perm` VALUES ('0350a0b1f3db416ab6f5e6b9c000f5e7', '10ae9b55ba894ac7911b86d8a0ba4327', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('15e8578d2b2b45cbaf2f30dfc562afd1', '620bf2b1e97946e5b77ca37d5215ae02', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('326eaa684afd4b1095f7483bc070f8bd', '69229a369448437f823b119dce4f0ac5', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('33c11a7f7c5e46c1b08591b53f9e3899', '0e084c555f1b41319c8155d026c9e5a5', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('49e365bbf80f45b9bd507a78229675f8', '1e63a69ea7e3468aa91ed9a7218940c8', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('4dd03a9edc1847cd96a0d3824f79d5bf', '5368e6241f4511e6a2d6185e0f846dee', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('50dafa9eb3314016bbfe55123ee0cf78', '76bb0542420f4bdb82ea1179ab31c3ba', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('5433410ecb3340ec9d24b1593068c159', '41c4e33a3b864757a86240e4715414cd', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('6251b7a14174409c92496a62c364b67c', 'b03a9c561f4511e6a2d6185e0f846dee', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('64ecfede54e34852876e5d5a349ab5fa', 'd5d5ce4e816040818f8e6013f5233dae', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('760a2a4b9982477c8fe5bafe200152d3', '1cf958f482f84168871f04701e63a899', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('89f3d4164f2b46f0a4b9b35d7f05a79b', '3fe4edab42c34269bff7b94dcfe2c9cc', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('8f1c32c5b2a041a6871fe49f938a0641', 'f6917532efc2469cbf91ac8b65ea7b68', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('961b34af8cd64168a776ffdc50fee163', 'de8cbe731f4511e6a2d6185e0f846dee', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('9c1fe775449f4b84a8b547adfa1f6e67', '8199119e4f3248f9b7a104592f74b03a', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('a08c2c78e5c044b4898dadc81c681575', '049b98098cc84b95882863b90d42c9cd', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('aa187877b2414d798d48a474b5b88a69', 'eccbbabb32234587b0b356b1f1533a7a', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('ad2ce18982a64a5a9ff7a41e3b7ba8fe', 'cb647f7e33444039adb575b81953bd7b', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('b467bfd9bd8e472bb430c39d9a02061d', 'd713a6291f4511e6a2d6185e0f846dee', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('b86c15f2d4464b4ea437f831237282d1', 'daec5eb51f4511e6a2d6185e0f846dee', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('da84af44ff754aaf8887e5d156ede72b', '307e2205f606412c8aa982eb6bc157c7', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('ef14bb2c1ce94dc084b40b1b8172284d', 'c87ecdecc144475db536a2a3040b5f57', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('fb68b06e15a24c50bc5ac34715f436f7', '6f2d63662d0f459691fcad346926c8d2', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('fc3c4a01c21341bb966a2cc75cb36b7d', '075af738bf9844868d0d9a3a838e8abb', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_role_perm` VALUES ('fd2fa386ec4e48239e470dca4e41f0b4', 'd2b97a561f4511e6a2d6185e0f846dee', '59d537b81f5911e6a2d6185e0f846dee');

-- ----------------------------
-- Table structure for ref_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ref_user_role`;
CREATE TABLE `ref_user_role` (
  `ref_id` varchar(32) NOT NULL COMMENT 'ref_id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`ref_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ref-用户-角色';

-- ----------------------------
-- Records of ref_user_role
-- ----------------------------
INSERT INTO `ref_user_role` VALUES ('a605ef13a5194a548347170ca5446208', 'e2e904211f4311e6a2d6185e0f846dee', '59d537b81f5911e6a2d6185e0f846dee');
INSERT INTO `ref_user_role` VALUES ('r1', 'u1', 'ro1');
INSERT INTO `ref_user_role` VALUES ('r2', 'u2', 'ro2');

-- ----------------------------
-- Table structure for sys_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area` (
  `area_id` int(11) NOT NULL COMMENT 'area_id',
  `area_name` varchar(128) DEFAULT NULL COMMENT 'area_name',
  `area_pid` int(11) DEFAULT NULL COMMENT 'area_pid',
  `area_seq` int(11) DEFAULT NULL COMMENT 'area_seq',
  `area_level` int(11) DEFAULT NULL COMMENT 'area_level',
  `area_status` int(11) DEFAULT NULL COMMENT 'area_status[0:可用, 1:不可用]',
  `create_time` datetime DEFAULT NULL COMMENT 'create_time',
  `create_user` varchar(32) DEFAULT NULL COMMENT 'create_user',
  `update_time` datetime DEFAULT NULL COMMENT 'update_time',
  `update_user` varchar(32) DEFAULT NULL COMMENT 'update_user',
  PRIMARY KEY (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域';

-- ----------------------------
-- Records of sys_area
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `perm_id` varchar(32) NOT NULL COMMENT '资源id',
  `perm_name` varchar(64) DEFAULT NULL COMMENT '资源名',
  `perm_pid` varchar(32) DEFAULT NULL COMMENT 'pid',
  `perm_type` int(11) DEFAULT NULL COMMENT '类型[0:菜单, 1:功能]',
  `perm_seq` int(11) DEFAULT NULL COMMENT '排序',
  `perm_url` varchar(512) DEFAULT NULL COMMENT 'url',
  `perm_code` varchar(64) DEFAULT NULL COMMENT 'code',
  `perm_icon` varchar(256) DEFAULT NULL COMMENT '图标',
  `perm_status` int(11) DEFAULT NULL COMMENT '状态[0:可用, 1:不可用]',
  `perm_note` varchar(128) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT 'create_time',
  `create_user` varchar(32) DEFAULT NULL COMMENT 'create_user',
  `update_time` datetime DEFAULT NULL COMMENT 'update_time',
  `update_user` varchar(32) DEFAULT NULL COMMENT 'update_user',
  PRIMARY KEY (`perm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统资源';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('049b98098cc84b95882863b90d42c9cd', '权限:删除', 'de8cbe731f4511e6a2d6185e0f846dee', '1', '4', null, 'sperm:delete', null, '0', '', '2016-05-24 13:51:14', null, null, null);
INSERT INTO `sys_permission` VALUES ('075af738bf9844868d0d9a3a838e8abb', '用户:角色', 'd713a6291f4511e6a2d6185e0f846dee', '1', '5', null, 'suser:role', null, '0', '分配角色', '2016-05-24 13:55:37', null, '2016-05-24 13:55:51', null);
INSERT INTO `sys_permission` VALUES ('0e084c555f1b41319c8155d026c9e5a5', '用户:修改', 'd713a6291f4511e6a2d6185e0f846dee', '1', '2', null, 'suser:edit', null, '0', '', '2016-05-24 13:54:49', null, null, null);
INSERT INTO `sys_permission` VALUES ('10ae9b55ba894ac7911b86d8a0ba4327', '菜单:添加', 'daec5eb51f4511e6a2d6185e0f846dee', '1', '1', null, 'smenu:add', null, '0', '', '2016-05-24 13:48:20', null, null, null);
INSERT INTO `sys_permission` VALUES ('1cf958f482f84168871f04701e63a899', '用户:删除', 'd713a6291f4511e6a2d6185e0f846dee', '1', '4', null, 'suser:delete', null, '0', '', '2016-05-24 13:55:15', null, null, null);
INSERT INTO `sys_permission` VALUES ('1e63a69ea7e3468aa91ed9a7218940c8', '菜单:修改', 'daec5eb51f4511e6a2d6185e0f846dee', '1', '2', null, 'smenu:edit', null, '0', '', '2016-05-24 13:48:35', null, null, null);
INSERT INTO `sys_permission` VALUES ('307e2205f606412c8aa982eb6bc157c7', '权限:修改', 'de8cbe731f4511e6a2d6185e0f846dee', '1', '2', null, 'sperm:edit', null, '0', '', '2016-05-24 13:50:39', null, null, null);
INSERT INTO `sys_permission` VALUES ('3fe4edab42c34269bff7b94dcfe2c9cc', '菜单:保存', 'daec5eb51f4511e6a2d6185e0f846dee', '1', '3', null, 'smenu:save', null, '0', '', '2016-05-24 13:48:51', null, null, null);
INSERT INTO `sys_permission` VALUES ('41c4e33a3b864757a86240e4715414cd', '角色:修改', 'd2b97a561f4511e6a2d6185e0f846dee', '1', '2', null, 'srole:edit', null, '0', '', '2016-05-24 13:52:00', null, null, null);
INSERT INTO `sys_permission` VALUES ('5368e6241f4511e6a2d6185e0f846dee', '系统设置', '0', '0', '1', '', null, null, '0', '', '2016-05-21 22:01:49', null, '2016-05-24 15:11:13', null);
INSERT INTO `sys_permission` VALUES ('620bf2b1e97946e5b77ca37d5215ae02', '用户:保存', 'd713a6291f4511e6a2d6185e0f846dee', '1', '3', null, 'suser:save', null, '0', '', '2016-05-24 13:55:03', null, null, null);
INSERT INTO `sys_permission` VALUES ('69229a369448437f823b119dce4f0ac5', '权限:保存', 'de8cbe731f4511e6a2d6185e0f846dee', '1', '3', null, 'sperm:save', null, '0', '', '2016-05-24 13:51:00', null, null, null);
INSERT INTO `sys_permission` VALUES ('6f2d63662d0f459691fcad346926c8d2', '权限:添加', 'de8cbe731f4511e6a2d6185e0f846dee', '1', '1', null, 'sperm:add', null, '0', '', '2016-05-24 13:50:28', null, null, null);
INSERT INTO `sys_permission` VALUES ('76bb0542420f4bdb82ea1179ab31c3ba', '角色:授权', 'd2b97a561f4511e6a2d6185e0f846dee', '1', '6', null, 'srole:grant', null, '0', '保存授权', '2016-05-24 13:53:44', null, '2016-05-24 13:56:12', null);
INSERT INTO `sys_permission` VALUES ('8199119e4f3248f9b7a104592f74b03a', '角色:保存', 'd2b97a561f4511e6a2d6185e0f846dee', '1', '3', null, 'srole:save', null, '0', '', '2016-05-24 13:52:13', null, null, null);
INSERT INTO `sys_permission` VALUES ('b03a9c561f4511e6a2d6185e0f846dee', '系统权限', '5368e6241f4511e6a2d6185e0f846dee', '0', '1', '', null, null, '0', '', '2016-05-21 22:01:49', null, '2016-05-24 13:43:37', null);
INSERT INTO `sys_permission` VALUES ('c87ecdecc144475db536a2a3040b5f57', '角色:权限', 'd2b97a561f4511e6a2d6185e0f846dee', '1', '5', null, 'srole:perm', null, '0', '分配权限', '2016-05-24 13:52:53', null, '2016-05-24 13:56:05', null);
INSERT INTO `sys_permission` VALUES ('cb647f7e33444039adb575b81953bd7b', '菜单:删除', 'daec5eb51f4511e6a2d6185e0f846dee', '1', '4', null, 'smenu:delete', null, '0', '', '2016-05-24 13:49:11', null, null, null);
INSERT INTO `sys_permission` VALUES ('d2b97a561f4511e6a2d6185e0f846dee', '角色管理', 'b03a9c561f4511e6a2d6185e0f846dee', '0', '3', '/role/index', null, null, '0', '', '2016-05-21 22:01:49', null, null, null);
INSERT INTO `sys_permission` VALUES ('d5d5ce4e816040818f8e6013f5233dae', '角色:添加', 'd2b97a561f4511e6a2d6185e0f846dee', '1', '1', null, 'srole:add', null, '0', '', '2016-05-24 13:51:49', null, null, null);
INSERT INTO `sys_permission` VALUES ('d713a6291f4511e6a2d6185e0f846dee', '用户管理', 'b03a9c561f4511e6a2d6185e0f846dee', '0', '4', '/user/index', null, null, '0', '', '2016-05-21 22:01:49', null, null, null);
INSERT INTO `sys_permission` VALUES ('daec5eb51f4511e6a2d6185e0f846dee', '菜单管理', 'b03a9c561f4511e6a2d6185e0f846dee', '0', '1', '/menu/index/', null, null, '0', '', '2016-05-21 22:01:49', null, null, null);
INSERT INTO `sys_permission` VALUES ('de8cbe731f4511e6a2d6185e0f846dee', '权限管理', 'b03a9c561f4511e6a2d6185e0f846dee', '0', '2', '/perm/index', null, null, '0', '', '2016-05-21 22:01:49', null, null, null);
INSERT INTO `sys_permission` VALUES ('eccbbabb32234587b0b356b1f1533a7a', '用户:添加', 'd713a6291f4511e6a2d6185e0f846dee', '1', '1', null, 'suser:add', null, '0', '', '2016-05-24 13:54:32', null, null, null);
INSERT INTO `sys_permission` VALUES ('f6917532efc2469cbf91ac8b65ea7b68', '角色:删除', 'd2b97a561f4511e6a2d6185e0f846dee', '1', '4', null, 'srole:delete', null, '0', '', '2016-05-24 13:52:25', null, null, null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `role_name` varchar(32) DEFAULT NULL COMMENT 'role_name',
  `role_code` varchar(32) DEFAULT NULL COMMENT 'role_code',
  `role_note` varchar(128) DEFAULT NULL COMMENT 'role_note',
  `role_status` int(11) DEFAULT NULL COMMENT 'role_status[0:可用, 1:不可用]',
  `create_time` datetime DEFAULT NULL COMMENT 'create_time',
  `create_user` varchar(32) DEFAULT NULL COMMENT 'create_user',
  `update_time` datetime DEFAULT NULL COMMENT 'update_time',
  `update_user` varchar(32) DEFAULT NULL COMMENT 'update_user',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('59d537b81f5911e6a2d6185e0f846dee', 'root', 'root', '管理员角色', '0', '2016-05-21 21:04:10', null, null, null);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户姓名',
  `user_account` varchar(64) DEFAULT NULL COMMENT '账号',
  `user_pwd` varchar(64) DEFAULT NULL COMMENT '密码',
  `user_sex` int(11) DEFAULT NULL COMMENT '性别[0:男, 1:女]',
  `user_phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `user_status` int(11) DEFAULT NULL COMMENT '状态[0:可用, 1:不可用]',
  `user_note` varchar(128) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT 'create_time',
  `create_user` varchar(32) DEFAULT NULL COMMENT 'create_user',
  `update_time` datetime DEFAULT NULL COMMENT 'update_time',
  `update_user` varchar(32) DEFAULT NULL COMMENT 'update_user',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('e2e904211f4311e6a2d6185e0f846dee', '超级管理员', 'admin', 'admin', '0', '', '0', '', '2016-05-21 22:02:53', null, '2016-05-24 13:58:04', null);
