/*
Navicat MySQL Data Transfer

Source Server         : mysql56
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : china_mobile

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2017-05-29 20:05:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for fans_ref_card
-- ----------------------------
DROP TABLE IF EXISTS `fans_ref_card`;
CREATE TABLE `fans_ref_card` (
  `card_id` varchar(32) NOT NULL COMMENT '卡号id',
  `wx_id` varchar(32) DEFAULT NULL COMMENT '微信网关id',
  `open_id` varchar(32) DEFAULT NULL COMMENT 'open_id',
  `msisdn` varchar(13) DEFAULT NULL COMMENT '物联卡号',
  `iccid` varchar(20) DEFAULT NULL COMMENT 'iccid',
  `imsi` varchar(15) DEFAULT NULL COMMENT 'imsi',
  `search_no` int(11) DEFAULT NULL COMMENT '查询次数',
  `phone_no` varchar(11) DEFAULT NULL COMMENT '手机号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物联网卡号';

-- ----------------------------
-- Table structure for internet_card
-- ----------------------------
DROP TABLE IF EXISTS `internet_card`;
CREATE TABLE `internet_card` (
  `msisdn` varchar(13) NOT NULL COMMENT '物联卡号',
  `iccid` varchar(20) DEFAULT NULL COMMENT 'iccid',
  `imsi` varchar(15) DEFAULT NULL COMMENT 'imsi',
  `jihuo_time` date DEFAULT NULL COMMENT '激活时间',
  `kaika_time` date DEFAULT NULL COMMENT '开卡时间',
  `province` varchar(256) NOT NULL DEFAULT '' COMMENT '省份',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`msisdn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_fans
-- ----------------------------
DROP TABLE IF EXISTS `wx_fans`;
CREATE TABLE `wx_fans` (
  `open_id` varchar(32) NOT NULL COMMENT 'open_id',
  `wx_id` varchar(32) DEFAULT NULL COMMENT '微信网关id',
  `wx_ghid` varchar(32) DEFAULT NULL COMMENT '原始id',
  `fans_state` int(11) DEFAULT NULL COMMENT '用户状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信用户列表';

-- ----------------------------
-- Table structure for wx_getway
-- ----------------------------
DROP TABLE IF EXISTS `wx_getway`;
CREATE TABLE `wx_getway` (
  `wx_id` varchar(32) NOT NULL COMMENT '微信网关id',
  `company_id` varchar(32) DEFAULT NULL COMMENT '企业id',
  `wx_name` varchar(128) DEFAULT NULL COMMENT '公众号名',
  `app_id` varchar(32) DEFAULT NULL COMMENT 'appId',
  `app_secret` varchar(64) DEFAULT NULL COMMENT 'appSecret',
  `access_token` varchar(512) DEFAULT NULL COMMENT 'access_token',
  `ak_time_out` bigint(13) DEFAULT NULL COMMENT 'access_token过期时间',
  `wx_token` varchar(64) DEFAULT NULL COMMENT 'token令牌',
  `wx_type` int(1) DEFAULT NULL COMMENT '公众号类型[1:订阅号, 2:服务号]',
  `wx_ghid` varchar(32) DEFAULT NULL COMMENT '原始id',
  `wx_mailbox` varchar(64) DEFAULT NULL COMMENT '登录邮箱',
  `wx_no` varchar(64) DEFAULT NULL COMMENT '微信号',
  `wx_state` int(1) DEFAULT NULL COMMENT '网关状态[0:可用, 1:不可用]',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`wx_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信网关';

-- ----------------------------
-- Table structure for wx_receive_msg
-- ----------------------------
DROP TABLE IF EXISTS `wx_receive_msg`;
CREATE TABLE `wx_receive_msg` (
  `msg_id` varchar(32) NOT NULL COMMENT 'msg_id',
  `open_id` varchar(32) DEFAULT NULL COMMENT 'open_id',
  `wx_ghid` varchar(32) DEFAULT NULL COMMENT '原始id',
  `msg_type` varchar(16) DEFAULT NULL COMMENT '消息类型[文本:text, 图片:image, 语音:voice, 视频:video, 小视频:shortvideo, 地理位置:location, 链接消息:link, 事件:event]',
  `wx_msg_id` varchar(32) DEFAULT NULL COMMENT '微信msgId',
  `event_` varchar(16) DEFAULT NULL COMMENT '事件类型[订阅:subscribe, 取消订阅:unsubscribe, 扫描二维码:subscribe, 已关注:SCAN, 上报地理位置:LOCATION, 自定义菜单:CLICK, 点击链接:VIEW]',
  `event_key` varchar(512) DEFAULT NULL COMMENT '事件key',
  `ticket` varchar(512) DEFAULT NULL COMMENT '二维码的ticket',
  `latitude` varchar(16) DEFAULT NULL COMMENT '地理位置纬度',
  `longitude` varchar(16) DEFAULT NULL COMMENT '地理位置经度',
  `precision_` varchar(16) DEFAULT NULL COMMENT '地理位置精度',
  `pic_url` varchar(512) DEFAULT NULL COMMENT '图片链接',
  `media_id` varchar(64) DEFAULT NULL COMMENT '消息媒体id',
  `thumb_media_id` varchar(64) DEFAULT NULL COMMENT '消息缩略图的媒体id',
  `title` varchar(128) DEFAULT NULL COMMENT '链接消息标题',
  `description` varchar(256) DEFAULT NULL COMMENT '链接消息描述',
  `url` varchar(512) DEFAULT NULL COMMENT '链接消息链接',
  `location_x` varchar(16) DEFAULT NULL COMMENT '地理维度',
  `location_y` varchar(16) DEFAULT NULL COMMENT '地理经度',
  `scale` varchar(8) DEFAULT NULL COMMENT '地图缩放大小',
  `label_` varchar(128) DEFAULT NULL COMMENT '地理位置信息',
  `content` varchar(512) DEFAULT NULL COMMENT '消息内容',
  `format` varchar(8) DEFAULT NULL COMMENT '语音格式',
  `recognition` varchar(512) DEFAULT NULL COMMENT '开启语音识别后增加的字段,为语音识别结果，使用UTF8编码',
  `original_xml` varchar(1024) DEFAULT NULL COMMENT '原始xml串',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信收到的消息';
