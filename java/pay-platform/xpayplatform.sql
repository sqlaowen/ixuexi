/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : xpayplatform

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2016-06-05 08:56:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pay_ali_notify
-- ----------------------------
DROP TABLE IF EXISTS `pay_ali_notify`;
CREATE TABLE `pay_ali_notify` (
  `ali_notify_id` varchar(32) NOT NULL COMMENT '异步通知ID',
  `merge_payment_id` varchar(32) DEFAULT NULL COMMENT '合并支付id',
  `out_trade_no` varchar(32) DEFAULT NULL COMMENT '商户订单号',
  `notify_time` datetime DEFAULT NULL COMMENT '通知时间',
  `notify_type` varchar(32) DEFAULT NULL COMMENT '通知类型',
  `notify_id` varchar(512) DEFAULT NULL COMMENT '通知校验ID',
  `sign_type` varchar(16) DEFAULT NULL COMMENT '签名方式',
  `sign` varchar(512) DEFAULT NULL COMMENT '签名',
  `subject` varchar(256) DEFAULT NULL COMMENT '商品名称',
  `payment_type` varchar(16) DEFAULT NULL COMMENT '支付类型',
  `trade_no` varchar(64) DEFAULT NULL COMMENT '支付宝交易号',
  `trade_status` varchar(32) DEFAULT NULL COMMENT '交易状态',
  `gmt_create` datetime DEFAULT NULL COMMENT '交易创建时间',
  `gmt_payment` datetime DEFAULT NULL COMMENT '交易付款时间',
  `gmt_close` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `refund_status` varchar(32) DEFAULT NULL COMMENT '退款状态',
  `gmt_refund` datetime DEFAULT NULL COMMENT '退款时间',
  `seller_email` varchar(128) DEFAULT NULL COMMENT '卖家支付宝账号',
  `buyer_email` varchar(128) DEFAULT NULL COMMENT '买家支付宝账号',
  `seller_id` varchar(32) DEFAULT NULL COMMENT '卖家partnerId',
  `buyer_id` varchar(32) DEFAULT NULL COMMENT '买家partnerId',
  `price` int(11) DEFAULT NULL COMMENT '商品单价(分)',
  `total_fee` int(11) DEFAULT NULL COMMENT '交易金额(分)',
  `quantity` int(11) DEFAULT NULL COMMENT '购买数量',
  `body` varchar(512) DEFAULT NULL COMMENT '商品描述',
  `discount` varchar(32) DEFAULT NULL COMMENT '折扣',
  `is_total_fee_adjust` varchar(4) DEFAULT NULL COMMENT '是否调整总价',
  `use_coupon` varchar(4) DEFAULT NULL COMMENT '是否使用红包',
  `error_code` varchar(64) DEFAULT NULL COMMENT '错误代码',
  `bank_seq_no` varchar(64) DEFAULT NULL COMMENT '网银流水',
  `extra_common_param` varchar(128) DEFAULT NULL COMMENT '公用回传参数',
  `out_channel_type` varchar(512) DEFAULT NULL COMMENT '支付渠道组合信息',
  `out_channel_amount` varchar(512) DEFAULT NULL COMMENT '支付金额组合信息',
  `out_channel_inst` varchar(512) DEFAULT NULL COMMENT '实际支付渠道',
  `notify_value` varchar(4000) DEFAULT NULL COMMENT '异步通知参数',
  `create_time` datetime DEFAULT NULL COMMENT 'create_time',
  `create_user` varchar(32) DEFAULT NULL COMMENT 'create_user',
  PRIMARY KEY (`ali_notify_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ali异步通知';

-- ----------------------------
-- Records of pay_ali_notify
-- ----------------------------
INSERT INTO `pay_ali_notify` VALUES ('0b7c27c4c2c94c8da93b363b7a160d49', '681e49bda4c248a4a38fc361d0e7eec2', null, '2016-04-11 15:11:59', 'trade_status_sync', '8ca14dfcc5c49bf4d94a8741f6f2b3053n', 'MD5', '0831381d995d04907a89c8b73f0f5afd', '4900386249985_榆林市榆阳区刘胜利汽车配件销售门市', '1', '2016041100001000290090089274', 'TRADE_SUCCESS', '2016-04-11 15:11:50', '2016-04-11 15:11:59', null, null, null, 'zhang9192@vip.qq.com', '18811057461', '2088211554961671', '2088702995871297', '1', '1', '1', null, '0.00', 'N', 'N', null, null, null, null, null, null, '{buyer_id=2088702995871297, trade_no=2016041100001000290090089274, use_coupon=N, notify_time=2016-04-11 15:11:59, subject=4900386249985_榆林市榆阳区刘胜利汽车配件销售门市, sign_type=MD5, is_total_fee_adjust=N, notify_type=trade_status_sync, out_trade_no=681e49bda4c248a4a38fc361d0e7eec2, trade_status=TRADE_SUCCESS, gmt_payment=2016-04-11 15:11:59, discount=0.00, sign=0831381d995d04907a89c8b73f0f5afd, buyer_email=18811057461, gmt_create=2016-04-11 15:11:50, price=0.01, total_fee=0.01, seller_id=2088211554961671, quantity=1, seller_email=zhang9192@vip.qq.com, notify_id=8ca14dfcc5c49bf4d94a8741f6f2b3053n, payment_type=1}', '2016-04-12 07:43:26', null);
INSERT INTO `pay_ali_notify` VALUES ('10122f512bc143ef9cc989836867e791', '78b2de19706c4ab9b29f99d41fa6b591', null, '2016-04-18 10:42:34', 'trade_status_sync', '2057e0b8fe4088824ef2bb04704866885f', 'MD5', '7ddbd180c31d00a8e9e0937e3219b973', '合并支付', '1', '2016041800001000610089936585', 'TRADE_SUCCESS', '2016-04-18 10:42:19', '2016-04-18 10:42:33', null, null, null, 'zhang9192@vip.qq.com', 'wenshiwei8888@163.com', '2088211554961671', '2088402820936615', '1', '1', '1', null, '0.00', 'N', 'N', null, null, null, null, null, null, '{buyer_id=2088402820936615, trade_no=2016041800001000610089936585, use_coupon=N, notify_time=2016-04-18 10:42:34, subject=合并支付, sign_type=MD5, is_total_fee_adjust=N, notify_type=trade_status_sync, out_trade_no=78b2de19706c4ab9b29f99d41fa6b591, gmt_payment=2016-04-18 10:42:33, trade_status=TRADE_SUCCESS, discount=0.00, sign=7ddbd180c31d00a8e9e0937e3219b973, gmt_create=2016-04-18 10:42:19, buyer_email=wenshiwei8888@163.com, price=0.01, total_fee=0.01, seller_id=2088211554961671, quantity=1, seller_email=zhang9192@vip.qq.com, notify_id=2057e0b8fe4088824ef2bb04704866885f, payment_type=1}', '2016-04-18 10:42:56', null);
INSERT INTO `pay_ali_notify` VALUES ('ee453e91c3b54be1b03385d06f8b2053', '681e49bda4c248a4a38fc361d0e7eec2', null, '2016-04-11 15:11:59', 'trade_status_sync', '8ca14dfcc5c49bf4d94a8741f6f2b3053n', 'MD5', '0831381d995d04907a89c8b73f0f5afd', '4900386249985_榆林市榆阳区刘胜利汽车配件销售门市', '1', '2016041100001000290090089274', 'TRADE_SUCCESS', '2016-04-11 15:11:50', '2016-04-11 15:11:59', null, null, null, 'zhang9192@vip.qq.com', '18811057461', '2088211554961671', '2088702995871297', '1', '1', '1', null, '0.00', 'N', 'N', null, null, null, null, null, null, '{buyer_id=2088702995871297, trade_no=2016041100001000290090089274, use_coupon=N, notify_time=2016-04-11 15:11:59, subject=4900386249985_榆林市榆阳区刘胜利汽车配件销售门市, sign_type=MD5, is_total_fee_adjust=N, notify_type=trade_status_sync, out_trade_no=681e49bda4c248a4a38fc361d0e7eec2, trade_status=TRADE_SUCCESS, gmt_payment=2016-04-11 15:11:59, discount=0.00, sign=0831381d995d04907a89c8b73f0f5afd, buyer_email=18811057461, gmt_create=2016-04-11 15:11:50, price=0.01, total_fee=0.01, seller_id=2088211554961671, quantity=1, seller_email=zhang9192@vip.qq.com, notify_id=8ca14dfcc5c49bf4d94a8741f6f2b3053n, payment_type=1}', '2016-04-12 07:59:11', null);
INSERT INTO `pay_ali_notify` VALUES ('f1677ce884ee451a9a3283586f7a79db', '1678eb28e5bb43c091837ba41efdedad', null, '2015-12-29 18:48:17', 'trade_status_sync', 'df3f9defa92b657d66f17b92c5f8c23kpg', 'RSA', 'MCeKYooxJRlVRuEXmogR2ewaKX3XG/Nv9SneXg7nZU/6lWziDKXvrV7Lcay6TE6Iao58ZX3YF4DnPEX5iupMGeDE6x1tF/rSP1vd6kNCdAoU0nyhUWcRPbx7f+aYV/G8L+nK9pO416Yo4y76yiH9hDxXqfkpKPdI+vGhAOGyF/k=', '元旦', '1', '2015122921001004610003643722', 'TRADE_SUCCESS', '2015-12-29 18:48:16', '2015-12-29 18:48:16', null, null, null, 'zhang9192@vip.qq.com', 'wenshiwei8888@163.com', '2088211554961671', '2088402820936615', '1', '1', '1', '元旦01', '0.00', 'N', 'N', null, null, null, null, null, null, '{buyer_id=2088402820936615, trade_no=2015122921001004610003643722, body=元旦01, notify_time=2015-12-29 18:48:17, use_coupon=N, subject=元旦, sign_type=RSA, is_total_fee_adjust=N, notify_type=trade_status_sync, out_trade_no=1678eb28e5bb43c091837ba41efdedad, trade_status=TRADE_SUCCESS, gmt_payment=2015-12-29 18:48:16, discount=0.00, sign=MCeKYooxJRlVRuEXmogR2ewaKX3XG/Nv9SneXg7nZU/6lWziDKXvrV7Lcay6TE6Iao58ZX3YF4DnPEX5iupMGeDE6x1tF/rSP1vd6kNCdAoU0nyhUWcRPbx7f+aYV/G8L+nK9pO416Yo4y76yiH9hDxXqfkpKPdI+vGhAOGyF/k=, gmt_create=2015-12-29 18:48:16, buyer_email=wenshiwei8888@163.com, price=0.01, total_fee=0.01, seller_id=2088211554961671, quantity=1, notify_id=df3f9defa92b657d66f17b92c5f8c23kpg, seller_email=zhang9192@vip.qq.com, payment_type=1}', '2016-04-12 08:58:26', null);

-- ----------------------------
-- Table structure for pay_ali_return
-- ----------------------------
DROP TABLE IF EXISTS `pay_ali_return`;
CREATE TABLE `pay_ali_return` (
  `ali_notify_id` varchar(32) NOT NULL COMMENT '同步通知id',
  `merge_payment_id` varchar(32) DEFAULT NULL COMMENT '合并支付id',
  `out_trade_no` varchar(32) DEFAULT NULL COMMENT '商户订单号',
  `is_success` varchar(32) DEFAULT NULL COMMENT '成功标识',
  `sign_type` varchar(32) DEFAULT NULL COMMENT '签名方式',
  `sign` varchar(128) DEFAULT NULL COMMENT '签名',
  `subject` varchar(256) DEFAULT NULL COMMENT '商品名',
  `payment_type` varchar(16) DEFAULT NULL COMMENT '支付类型',
  `exterface` varchar(32) DEFAULT NULL COMMENT '接口名',
  `trade_no` varchar(64) DEFAULT NULL COMMENT '支付宝流水号',
  `trade_status` varchar(32) DEFAULT NULL COMMENT '交易状态',
  `notify_id` varchar(256) DEFAULT NULL COMMENT '通知校验ID',
  `notify_time` datetime DEFAULT NULL COMMENT '通知时间',
  `notify_type` varchar(32) DEFAULT NULL COMMENT '通知类型',
  `seller_email` varchar(128) DEFAULT NULL COMMENT '卖家支付宝账号',
  `buyer_email` varchar(128) DEFAULT NULL COMMENT '买家支付宝账号',
  `seller_id` varchar(32) DEFAULT NULL COMMENT '卖家partentId',
  `buyer_id` varchar(32) DEFAULT NULL COMMENT '买家partentId',
  `total_fee` int(11) DEFAULT NULL COMMENT '交易金额(分)',
  `body` varchar(512) DEFAULT NULL COMMENT '商品描述',
  `bank_seq_no` varchar(64) DEFAULT NULL COMMENT '网银流水',
  `extra_common_param` varchar(128) DEFAULT NULL COMMENT '公用回传参数',
  `return_value` varchar(4000) DEFAULT NULL COMMENT '同步返回参数',
  `create_time` datetime DEFAULT NULL COMMENT 'create_time',
  `create_user` varchar(32) DEFAULT NULL COMMENT 'create_user',
  PRIMARY KEY (`ali_notify_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ali同步通知';

-- ----------------------------
-- Records of pay_ali_return
-- ----------------------------

-- ----------------------------
-- Table structure for pay_merge_payment
-- ----------------------------
DROP TABLE IF EXISTS `pay_merge_payment`;
CREATE TABLE `pay_merge_payment` (
  `merge_payment_id` varchar(32) NOT NULL COMMENT '合并支付id',
  `merchant_id` varchar(32) DEFAULT NULL COMMENT '平台商户id',
  `gateway_id` varchar(32) DEFAULT NULL COMMENT '支付网关Id',
  `payment_state_id` int(11) DEFAULT NULL COMMENT '支付单状态id',
  `total_fee` int(11) DEFAULT NULL COMMENT '总金额(分)',
  `return_url` varchar(1024) DEFAULT NULL COMMENT '支付返回url',
  `notify_url` varchar(1024) DEFAULT NULL COMMENT '支付通知url',
  `bank_code` varchar(32) DEFAULT NULL COMMENT '银行简码',
  `time_out` datetime DEFAULT NULL COMMENT '超时时间',
  `create_time` datetime DEFAULT NULL COMMENT 'create_time',
  `create_user` varchar(32) DEFAULT NULL COMMENT 'create_user',
  `update_time` datetime DEFAULT NULL COMMENT 'update_time',
  `update_user` varchar(32) DEFAULT NULL COMMENT 'update_user',
  `trade_time` datetime DEFAULT NULL COMMENT '实际支付时间',
  `order_close_time` datetime DEFAULT NULL COMMENT '订单关闭时间',
  `trade_close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `third_trade_no` varchar(64) DEFAULT NULL COMMENT '第三方交易号(ali/wechat流水号)',
  PRIMARY KEY (`merge_payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合并支付单';

-- ----------------------------
-- Records of pay_merge_payment
-- ----------------------------
INSERT INTO `pay_merge_payment` VALUES ('02ac7c5541a34abd9af57e2315dd5943', 'zr001', 'c1375ff9fb9311e58f80185e0f846dee', '1', '1', null, 'http://www.baidu.com', null, '2016-04-08 09:17:49', '2016-04-06 09:17:50', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('02e8e69ee9924f6d8796c224064cf866', 'zr001', 'be985ac317e511e68b74185e0f846dee', '1', '1', null, 'http://101.36.73.229:11007/paynotify/wx/async', null, '2016-05-14 10:12:43', '2016-05-12 10:12:43', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('062e4a553aa14624bcb6f4ee8fe79cf1', 'zr001', 'c1375ff9fb9311e58f80185e0f846dee', '3', '1', null, 'http://www.baidu.com', null, '2016-04-08 09:24:27', '2016-04-06 09:24:28', null, '2016-04-13 14:34:36', null, null, null, null, '062e4a553aa14624bcb6f4ee8fe79cf1');
INSERT INTO `pay_merge_payment` VALUES ('0993d80cd46d462b9078524858f976fc', 'zr001', '9d7b4a9cf90411e5956df8cab8256d59', '1', '2', null, 'http://101.36.73.229:11007/paynotify/ali/async', null, '2016-04-13 10:30:24', '2016-04-12 10:30:24', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('0c75b4c4075c4a1e8e82a6f204d557c9', 'zr001', '5529d97ff90411e5956df8cab8256d59', '1', '101', 'http://www.baidu.com', 'http://www.baidu.com', 'CMB', '2016-04-06 15:08:01', '2016-04-05 15:08:01', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('0f34bdf4e1714dc29863d4e2bc92a00d', 'zr001', '5403e3bdfb9411e58f80185e0f846dee', '1', '1', null, 'http://www.baidu.com', null, '2016-04-08 10:35:03', '2016-04-06 10:35:04', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('0f9d177cee904a578f91cb69567f52fc', 'zr001', '88dec97ef90311e5956df8cab8256d59', '1', '1', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/sync', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/async', null, '2016-04-21 18:38:58', '2016-04-19 18:38:59', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('156a677934db4fe98adabaa332e6af08', 'zr001', '88dec97ef90311e5956df8cab8256d59', '1', '1', 'http://www.baidu.com', 'http://www.baidu.com', 'CMB', '2016-04-07 15:06:52', '2016-04-05 15:06:53', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('1678eb28e5bb43c091837ba41efdedad', 'zr001', '9d7b4a9cf90411e5956df8cab8256d59', '2', '2', null, 'http://101.36.73.229:11007/paynotify/ali/async', null, '2016-04-13 08:04:37', '2016-04-12 08:04:37', null, '2016-04-12 08:58:43', null, '2015-12-29 18:48:16', null, null, '2015122921001004610003643722');
INSERT INTO `pay_merge_payment` VALUES ('17c00d5acb544d858c40cd5c3ec697fd', 'zr001', '88dec97ef90311e5956df8cab8256d59', '3', '2', 'http://www.baidu.com', 'http://www.baidu.com', 'CMB', '2016-04-08 18:16:35', '2016-04-07 18:16:36', null, '2016-04-13 14:36:31', null, null, null, '2016-04-08 18:20:07', '17c00d5acb544d858c40cd5c3ec697fd');
INSERT INTO `pay_merge_payment` VALUES ('1f43d88d626049b48d4cac2d402bc8ca', 'zr001', '5403e3bdfb9411e58f80185e0f846dee', '1', '1', null, 'http://www.baidu.com', null, '2016-04-08 10:31:10', '2016-04-06 10:31:11', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('24e0c6ef6ac1447ab4420efc18915ca7', 'zr001', '5403e3bdfb9411e58f80185e0f846dee', '1', '1', null, 'http://101.36.73.229:11007/paynotify/wx/async', null, '2016-04-15 14:50:06', '2016-04-13 14:50:06', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('322d0c0836f841fc90eb3f99d0033f54', 'zr001', 'c1375ff9fb9311e58f80185e0f846dee', '1', '1', null, 'http://101.36.73.229:11007/paynotify/wx/async', null, '2016-04-15 14:45:09', '2016-04-13 14:45:10', null, '2016-04-13 14:47:31', null, null, null, null, '322d0c0836f841fc90eb3f99d0033f54');
INSERT INTO `pay_merge_payment` VALUES ('3330f2f4d7be4f948f9982070acbaf1f', 'merchantId', 'gatewayId', '1', '1', null, null, null, null, '2016-03-31 22:58:00', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('369b0c2663034ac08b0df8abd6865af0', 'zr001', '5403e3bdfb9411e58f80185e0f846dee', '1', '1', null, 'http://www.baidu.com', null, '2016-04-08 10:34:43', '2016-04-06 10:34:43', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('3780302428394c298a457019a5b3a774', 'zr001', '9d7b4a9cf90411e5956df8cab8256d59', '1', '101', 'http://www.baidu.com', 'http://www.baidu.com', 'CMB', '2016-04-08 17:57:12', '2016-04-07 17:57:13', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('3845772a0e984d0388796da22dff7922', 'zr001', '88dec97ef90311e5956df8cab8256d59', '1', '1', 'http://www.baidu.com', 'http://101.36.73.229:11007/paynotify/wx/async', null, '2016-04-15 14:53:16', '2016-04-13 14:53:17', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('41cc1a97c1f64b7b9259544a6dcb0594', 'zr001', '5529d97ff90411e5956df8cab8256d59', '1', '101', 'http://www.baidu.com', 'http://www.baidu.com', 'CMB', '2016-04-06 18:20:35', '2016-04-05 18:20:36', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('42eb4fe8fcdc470e9580bb49324a5a40', 'zr001', '9d7b4a9cf90411e5956df8cab8256d59', '1', '1', null, 'http://101.36.73.229:11007/paynotify/ali/async', null, '2016-04-14 15:32:13', '2016-04-12 15:32:14', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('45e42590ad0346be851dde598131f13d', 'zr001', '5529d97ff90411e5956df8cab8256d59', '1', '101', 'http://www.baidu.com', 'http://www.baidu.com', 'CMB', '2016-04-07 10:10:12', '2016-04-06 10:10:13', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('46f6591d3f86436d90e51259024875c0', 'zr001', '9d7b4a9cf90411e5956df8cab8256d59', '1', '1', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/sync', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/async', null, '2016-04-21 09:42:07', '2016-04-19 09:42:07', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('4c7be7247efc485790a943198f85353a', 'zr001', '88dec97ef90311e5956df8cab8256d59', '1', '1', 'http://101.36.73.229:11007/paynotify/ali/sync', 'http://101.36.73.229:11007/paynotify/ali/async', null, '2016-04-20 10:14:35', '2016-04-18 10:14:36', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('5223f4071f97463083593484d535e54b', 'zr001', '9d7b4a9cf90411e5956df8cab8256d59', '1', '101', 'http://www.baidu.com', 'http://www.baidu.com', 'CMB', '2016-04-08 18:07:58', '2016-04-07 18:07:59', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('5706b6af164a47d28f349e38ea7926f4', 'zr001', '88dec97ef90311e5956df8cab8256d59', '1', '1', 'http://101.36.73.229:11007/paynotify/ali/sync', 'http://101.36.73.229:11007/paynotify/ali/async', null, '2016-04-15 14:54:00', '2016-04-13 14:54:01', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('62e8612a9bc44fcca0c78f7540dedc72', 'zr001', '9d7b4a9cf90411e5956df8cab8256d59', '1', '1', null, 'http://101.36.73.229:11007/paynotify/ali/async', null, '2016-04-14 14:58:49', '2016-04-12 14:58:50', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('66d76f83ec8043068bad037324303e3a', 'zr001', '86f13154fb9411e58f80185e0f846dee', '1', '1', null, 'http://www.baidu.com', null, '2016-04-08 20:15:24', '2016-04-06 20:15:25', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('681e49bda4c248a4a38fc361d0e7eec2', 'zr001', '88dec97ef90311e5956df8cab8256d59', '2', '2', 'http://101.36.73.229:11007/paynotify/ali/sync', 'http://101.36.73.229:11007/paynotify/ali/async', null, '2016-04-13 07:20:58', '2016-04-12 07:20:58', null, '2016-04-12 07:59:11', null, '2016-04-11 15:11:59', null, null, '2016041100001000290090089274');
INSERT INTO `pay_merge_payment` VALUES ('6d0fb6de4cea4dedb8a66934b5b718d1', 'zr001', 'c1375ff9fb9311e58f80185e0f846dee', '1', '1', null, 'http://www.baidu.com', null, '2016-04-08 09:15:13', '2016-04-06 09:15:14', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('74752722117a416fbea89653b7b74c6b', 'zr001', '5403e3bdfb9411e58f80185e0f846dee', '3', '2', null, 'http://101.36.73.229:11007/paynotify/wx/async', null, '2016-04-13 09:32:17', '2016-04-12 09:32:18', null, '2016-04-13 14:38:23', null, null, null, null, 'bbccddxxxxxjjyy');
INSERT INTO `pay_merge_payment` VALUES ('78b2de19706c4ab9b29f99d41fa6b591', 'zr001', '88dec97ef90311e5956df8cab8256d59', '2', '1', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/sync', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/async', null, '2016-04-20 10:38:49', '2016-04-18 10:38:50', null, '2016-04-18 10:43:02', null, '2016-04-18 10:42:33', null, null, '2016041800001000610089936585');
INSERT INTO `pay_merge_payment` VALUES ('7ac43faefeb74993bbeba593b5cae7f9', 'merchantId', 'gatewayId', '1', '1', null, null, null, null, '2016-03-31 22:58:57', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('7dd60983ba1140dfb6783ca970f8be95', 'zr001', 'c1375ff9fb9311e58f80185e0f846dee', '1', '1', null, 'http://www.baidu.com', null, '2016-04-08 09:19:25', '2016-04-06 09:19:26', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('8c007ff22604438c8578562132f34e44', 'zr001', 'c1375ff9fb9311e58f80185e0f846dee', '3', '1', null, 'http://www.baidu.com', null, '2016-04-08 09:47:16', '2016-04-06 09:47:17', null, '2016-04-13 14:40:15', null, null, null, null, 'bbccddxxxxxjjyy');
INSERT INTO `pay_merge_payment` VALUES ('938228f8d1cb455f8265b29e06f9690e', 'zr001', '9d7b4a9cf90411e5956df8cab8256d59', '1', '101', 'http://www.baidu.com', 'http://www.baidu.com', 'CMB', '2016-04-08 18:04:08', '2016-04-07 18:04:09', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('995ac2ea44e8492eac18468d7988329c', 'zr001', '88dec97ef90311e5956df8cab8256d59', '1', '1', 'http://www.baidu.com', 'http://www.baidu.com', 'CMB', '2016-04-07 13:46:42', '2016-04-05 13:46:46', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('b247cd27bd704fd390a9d5ff70a7c47f', 'zr001', '9d7b4a9cf90411e5956df8cab8256d59', '1', '1', null, 'http://101.36.73.229:11007/paynotify/ali/async', null, '2016-04-15 14:51:40', '2016-04-13 14:51:40', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('b66ed9220169494c949b6bf119cc9837', 'zr001', '5403e3bdfb9411e58f80185e0f846dee', '2', '2', null, 'http://101.36.73.229:11007/paynotify/wx/async', null, '2016-04-12 17:04:32', '2016-04-11 17:04:33', null, '2016-04-11 18:47:14', null, '2016-03-21 16:19:10', null, null, '4001642001201603214161369254');
INSERT INTO `pay_merge_payment` VALUES ('b709ada0fc1641799f33a3c94feb4207', 'zr001', 'c1375ff9fb9311e58f80185e0f846dee', '1', '1', null, 'http://101.36.73.229:11007/paynotify/wx/async', null, '2016-05-06 18:17:27', '2016-05-04 18:17:28', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('bac5e75fffd34e129e3d083b4277254a', 'zr001', '86f13154fb9411e58f80185e0f846dee', '1', '2', null, 'http://101.36.73.229:11007/paynotify/wx/async', null, '2016-04-13 09:32:01', '2016-04-12 09:32:01', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('d9a4addcf6f44e2488f6b197a15b81b6', 'zr001', '9d7b4a9cf90411e5956df8cab8256d59', '1', '101', 'http://www.baidu.com', 'http://www.baidu.com', 'CMB', '2016-04-08 18:11:04', '2016-04-07 18:11:05', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('dea351cc2dc148598cea2727ee2e7b4f', 'zr001', '9d7b4a9cf90411e5956df8cab8256d59', '1', '1', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/sync', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/async', null, '2016-04-21 09:37:18', '2016-04-19 09:37:18', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('dea625a34a9e45ed8b2d1426c5d6e981', 'zr001', '9d7b4a9cf90411e5956df8cab8256d59', '1', '1', null, 'http://101.36.73.229:11007/paynotify/ali/async', null, '2016-04-14 15:19:49', '2016-04-12 15:19:50', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('e1fc455d8bcc4e3faa283c996b0ab259', 'zr001', '88dec97ef90311e5956df8cab8256d59', '1', '1', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/sync', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/async', null, '2016-05-01 18:34:53', '2016-04-29 18:34:54', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('e25804d5dcff4012aa8591725c5a5911', 'zr001', 'c1375ff9fb9311e58f80185e0f846dee', '1', '1', null, 'http://www.baidu.com', null, '2016-04-08 09:19:56', '2016-04-06 09:19:56', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('e4031b6a8f454b2a97d752ffe26f7362', 'zr001', '9d7b4a9cf90411e5956df8cab8256d59', '1', '1', null, 'http://101.36.73.229:11007/paynotify/ali/async', null, '2016-04-14 15:01:04', '2016-04-12 15:01:05', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('e63f89bfe95a4d278604fb38a0768eed', 'zr001', '9d7b4a9cf90411e5956df8cab8256d59', '1', '1', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/sync', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/async', null, '2016-04-21 09:46:27', '2016-04-19 09:46:28', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('e7af44bd29a745cea5ed1666c3b2ca3d', 'zr001', '5403e3bdfb9411e58f80185e0f846dee', '1', '1', null, 'http://101.36.73.229:11007/paynotify/wx/async', null, '2016-04-15 14:50:33', '2016-04-13 14:50:34', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('e8662cf200494030a23b2d8f6db7e879', 'zr001', '5403e3bdfb9411e58f80185e0f846dee', '1', '2', null, 'http://101.36.73.229:11007/paynotify/wx/async', null, '2016-04-13 09:31:03', '2016-04-12 09:31:03', null, '2016-04-13 14:29:11', null, null, null, null, 'e8662cf200494030a23b2d8f6db7e879');
INSERT INTO `pay_merge_payment` VALUES ('f29c31753b064b059fc61604edd61d31', 'zr001', '88dec97ef90311e5956df8cab8256d59', '1', '1', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/sync', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/async', null, '2016-04-20 10:36:19', '2016-04-18 10:36:20', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('f6fe3167cadf485d9c85cb938d32c0ce', 'zr001', 'c1375ff9fb9311e58f80185e0f846dee', '1', '1', null, 'http://www.baidu.com', null, '2016-04-08 10:28:08', '2016-04-06 10:28:08', null, '2016-04-13 14:29:11', null, null, null, null, 'f6fe3167cadf485d9c85cb938d32c0ce');
INSERT INTO `pay_merge_payment` VALUES ('f8ede68cf4004d74aebe988925855186', 'zr001', '9d7b4a9cf90411e5956df8cab8256d59', '1', '1', null, 'http://101.36.73.229:11007/paynotify/ali/async', null, '2016-04-14 15:31:53', '2016-04-12 15:31:54', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('f933eeaccc1c412fab318eaac3342872', 'zr001', '9d7b4a9cf90411e5956df8cab8256d59', '1', '1', 'http://101.36.73.229:11007/paynotify/ali/sync', 'http://101.36.73.229:11007/paynotify/ali/async', null, '2016-04-15 14:53:31', '2016-04-13 14:53:31', null, null, null, null, null, null, null);
INSERT INTO `pay_merge_payment` VALUES ('faab9c666b884cffa44c9ab771f049d9', 'zr001', '9d7b4a9cf90411e5956df8cab8256d59', '1', '1', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/sync', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/async', null, '2016-04-21 09:50:48', '2016-04-19 09:50:48', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for pay_payment
-- ----------------------------
DROP TABLE IF EXISTS `pay_payment`;
CREATE TABLE `pay_payment` (
  `payment_id` varchar(32) NOT NULL COMMENT '支付单id',
  `merchant_id` varchar(32) DEFAULT NULL COMMENT '平台商户id',
  `payment_state_id` int(11) DEFAULT NULL COMMENT '支付单状态id',
  `gateway_id` varchar(32) DEFAULT NULL COMMENT '支付网关Id',
  `merge_payment_id` varchar(32) DEFAULT NULL COMMENT '合并支付单id',
  `total_fee` int(11) DEFAULT NULL COMMENT '总金额(分)',
  `sp_name` varchar(256) DEFAULT NULL COMMENT '商品名称',
  `sp_detail` varchar(512) DEFAULT NULL COMMENT '商品描述',
  `time_out` datetime DEFAULT NULL COMMENT '超时时间',
  `return_url` varchar(1024) DEFAULT NULL COMMENT '支付返回url',
  `notify_url` varchar(1024) DEFAULT NULL COMMENT '支付通知url',
  `extra_common_param` varchar(4000) DEFAULT NULL COMMENT '公用回传参数',
  `from_ip` varchar(64) DEFAULT NULL COMMENT '来源ip',
  `payment_source` varchar(1024) DEFAULT NULL COMMENT '支付单来源',
  `bank_code` varchar(32) DEFAULT NULL COMMENT '银行简码',
  `payment_note` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT 'create_time',
  `create_user` varchar(32) DEFAULT NULL COMMENT 'create_user',
  `update_time` datetime DEFAULT NULL COMMENT 'update_time',
  `update_user` varchar(32) DEFAULT NULL COMMENT 'update_user',
  `order_close_time` datetime DEFAULT NULL COMMENT '订单关闭时间',
  `trade_time` datetime DEFAULT NULL COMMENT '实际支付时间',
  `trade_close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `third_trade_no` varchar(64) DEFAULT NULL COMMENT '第三方交易号(ali/wechat流水号)',
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付单';

-- ----------------------------
-- Records of pay_payment
-- ----------------------------
INSERT INTO `pay_payment` VALUES ('002d67379d654e9faaa14fc06d53e53d', 'zr001', '1', '88dec97ef90311e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-07 11:00:39', 'http://www.baidu.com', 'http://www.baidu.com', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-05 11:00:39', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('0a8adac08dc841cabc93d39941ff6a14', 'zr001', '1', '5529d97ff90411e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-08 10:10:12', 'http://www.baidu.com', 'http://www.baidu.com', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', 'CMB', null, '2016-04-06 10:10:13', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('0b571bcfb5fb4565819278513e803045', 'zr001', '1', '9d7b4a9cf90411e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-21 09:46:27', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/sync', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-19 09:46:28', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('0e6c79d83275454980c032f9e3949243', 'zr001', '1', '9d7b4a9cf90411e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-14 10:29:23', null, 'http://101.36.73.229:11007/paynotify/ali/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-12 10:29:24', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('16f941624b7444ca94fd24c4090f5d94', 'zr001', '1', '5529d97ff90411e5956df8cab8256d59', null, '100', 'spming2', null, '2016-04-07 10:10:12', 'http://www.baidu.com', 'http://www.baidu.com', null, '172.31.30.17', 'PAYSOURCE_ORDER', 'CMB', null, '2016-04-06 10:10:13', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('194b55ce4b8b4b38aa58a168f1e2f276', 'zr001', '1', '88dec97ef90311e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-20 10:36:19', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/sync', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-18 10:36:20', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('1de92739095545b8803659fc66c2af11', 'zr001', '1', '5529d97ff90411e5956df8cab8256d59', null, '100', 'spming2', null, '2016-04-06 15:08:01', 'http://www.baidu.com', 'http://www.baidu.com', null, '172.31.30.17', 'PAYSOURCE_ORDER', 'CMB', null, '2016-04-05 15:08:01', null, '2016-04-05 18:20:36', null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('1e0be07992344926838a9132d550b706', 'zr001', '1', '9d7b4a9cf90411e5956df8cab8256d59', null, '100', 'spming2', 'spDetail', '2016-04-08 18:11:04', 'http://www.baidu.com', 'http://www.baidu.com', null, '172.31.30.17', 'PAYSOURCE_ORDER', 'CMB', null, '2016-04-07 18:11:05', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('1f07d6eb4bca48b79f01a98f4ef874da', 'zr001', '1', '88dec97ef90311e5956df8cab8256d59', null, '100', 'spming2', null, '2016-04-04 03:26:28', 'http://www.baidu.com', 'http://www.baidu.com', null, '192.168.1.104', 'PAYSOURCE_ORDER', null, null, '2016-04-03 03:26:28', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('249ae1c6586c4cf6988a514fde315c80', 'zr001', '2', '88dec97ef90311e5956df8cab8256d59', '78b2de19706c4ab9b29f99d41fa6b591', '1', 'spming', 'spDetail', '2016-04-20 10:38:49', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/sync', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-18 10:38:50', null, '2016-04-18 10:43:02', null, null, '2016-04-18 10:42:33', null, '2016041800001000610089936585');
INSERT INTO `pay_payment` VALUES ('2f11c05e623d41c39a430f82803bea74', 'zr001', '1', '88dec97ef90311e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-21 18:38:58', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/sync', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-19 18:38:59', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('2f23dca9f92c418d821b5f05521d56f7', 'zr001', '3', '86f13154fb9411e58f80185e0f846dee', '062e4a553aa14624bcb6f4ee8fe79cf1', '1', 'spming', null, '2016-04-08 09:15:13', null, 'http://www.baidu.com', '{\"id\":\"xxx\"}', '192.168.247.1', 'PAYSOURCE_ORDER', null, null, '2016-04-06 09:15:14', null, '2016-04-12 13:28:45', null, null, null, null, '062e4a553aa14624bcb6f4ee8fe79cf1');
INSERT INTO `pay_payment` VALUES ('318f8befed834ec8951dfe53ac90cdae', 'zr001', '1', '88dec97ef90311e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-04 03:26:28', 'http://www.baidu.com', 'http://www.baidu.com', '{\"id\":\"xxx\"}', '192.168.1.104', 'PAYSOURCE_ORDER', null, null, '2016-04-03 03:26:28', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('32554d78b2fd4ffd9f166800621b0f32', 'zr001', '1', '9d7b4a9cf90411e5956df8cab8256d59', null, '1', 'spming2', 'spDetail', '2016-04-13 10:30:24', null, 'http://101.36.73.229:11007/paynotify/ali/async', null, '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-12 10:30:24', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('3624e8be48fd48bea45992e219ad37a6', 'zr001', '3', '5403e3bdfb9411e58f80185e0f846dee', '74752722117a416fbea89653b7b74c6b', '1', 'spming2', null, '2016-04-13 09:31:03', null, 'http://101.36.73.229:11007/paynotify/wx/async', null, '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-12 09:31:03', null, '2016-04-13 14:29:08', null, null, null, null, '74752722117a416fbea89653b7b74c6b');
INSERT INTO `pay_payment` VALUES ('4055cae593a54e66b63463b1e5127eb7', 'zr001', '2', '88dec97ef90311e5956df8cab8256d59', '681e49bda4c248a4a38fc361d0e7eec2', '1', 'spming2', 'spDetail', '2016-04-13 07:20:58', 'http://101.36.73.229:11007/paynotify/ali/sync', 'http://101.36.73.229:11007/paynotify/ali/async', null, '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-12 07:20:58', null, '2016-04-12 07:59:11', null, null, '2016-04-11 15:11:59', null, '2016041100001000290090089274');
INSERT INTO `pay_payment` VALUES ('4b5d33328c434dbc8ca34d9c628ab8e8', 'zr001', '1', '9d7b4a9cf90411e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-21 09:42:07', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/sync', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-19 09:42:07', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('51f54da44ae049d39b95e0cb28c728e2', 'zr001', '2', '5403e3bdfb9411e58f80185e0f846dee', 'b66ed9220169494c949b6bf119cc9836', '1', 'spming', null, '2016-04-13 17:04:32', null, 'http://101.36.73.229:11007/paynotify/wx/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-11 17:04:33', null, '2016-04-11 18:25:03', null, null, '2016-03-21 16:19:10', null, '4001642001201603214161369254');
INSERT INTO `pay_payment` VALUES ('5776e1e2065e4b8b82794ed2230fba82', 'zr001', '1', '88dec97ef90311e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-04 03:05:11', 'http://www.baidu.com', 'http://www.baidu.com', '{\"id\":\"xxx\"}', '192.168.1.104', 'PAYSOURCE_ORDER', 'CMB', null, '2016-04-03 03:05:11', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('5a06546f3e294c11bdceb4b190559e3c', 'zr001', '2', '88dec97ef90311e5956df8cab8256d59', '681e49bda4c248a4a38fc361d0e7eec2', '1', 'spming', 'spDetail', '2016-04-14 07:20:58', 'http://101.36.73.229:11007/paynotify/ali/sync', 'http://101.36.73.229:11007/paynotify/ali/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-12 07:20:58', null, '2016-04-12 07:59:11', null, null, '2016-04-11 15:11:59', null, '2016041100001000290090089274');
INSERT INTO `pay_payment` VALUES ('5eec49cf36fe4bee9e68f12de6a4d9c3', 'zr001', '2', '9d7b4a9cf90411e5956df8cab8256d59', '1678eb28e5bb43c091837ba41efdedad', '1', 'spming', 'spDetail', '2016-04-14 08:04:37', null, 'http://101.36.73.229:11007/paynotify/ali/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-12 08:04:37', null, '2016-04-12 08:58:44', null, null, '2015-12-29 18:48:16', null, '2015122921001004610003643722');
INSERT INTO `pay_payment` VALUES ('5ffef6a52ae249418c8fb6f0f27edb53', 'zr001', '3', 'c1375ff9fb9311e58f80185e0f846dee', '322d0c0836f841fc90eb3f99d0033f54', '1', 'spming', null, '2016-04-15 14:45:09', null, 'http://101.36.73.229:11007/paynotify/wx/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-13 14:45:09', null, '2016-04-13 14:47:31', null, null, null, null, '322d0c0836f841fc90eb3f99d0033f54');
INSERT INTO `pay_payment` VALUES ('655eea272ebd4d47bf569c41b11d2572', 'zr001', '1', '88dec97ef90311e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-20 10:14:35', 'http://101.36.73.229:11007/paynotify/ali/sync', 'http://101.36.73.229:11007/paynotify/ali/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-18 10:14:36', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('6c741450760b4d36ad5b559a83f401e5', 'zr001', '1', '9d7b4a9cf90411e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-09 17:57:12', 'http://www.baidu.com', 'http://www.baidu.com', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', 'CMB', null, '2016-04-07 17:57:13', null, '2016-04-07 18:07:59', null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('716cf9d02e4a4e4fbcc1711fcbf1fc57', 'zr001', '3', '5403e3bdfb9411e58f80185e0f846dee', '74752722117a416fbea89653b7b74c6b', '1', 'spming', null, '2016-04-14 09:31:03', null, 'http://101.36.73.229:11007/paynotify/wx/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-12 09:31:03', null, '2016-04-13 14:29:08', null, null, null, null, '74752722117a416fbea89653b7b74c6b');
INSERT INTO `pay_payment` VALUES ('787a9c9e035a4789941b43d737add6bc', 'zr001', '1', '9d7b4a9cf90411e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-14 10:30:24', null, 'http://101.36.73.229:11007/paynotify/ali/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-12 10:30:24', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('788f4032816140048b731690aae71990', 'zr001', '1', 'c1375ff9fb9311e58f80185e0f846dee', null, '1', 'spming', null, '2016-05-06 18:17:27', null, 'http://101.36.73.229:11007/paynotify/wx/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-05-04 18:17:28', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('7e519d75198c4feca8dfd9e6bcbcb57e', 'zr001', '1', '88dec97ef90311e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-15 14:54:00', 'http://101.36.73.229:11007/paynotify/ali/sync', 'http://101.36.73.229:11007/paynotify/ali/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-13 14:54:01', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('81533a295e664e19a47b4441804c67e5', 'zr001', '1', '5529d97ff90411e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-07 15:06:52', 'http://www.baidu.com', 'http://www.baidu.com', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', 'CMB', null, '2016-04-05 15:06:53', null, '2016-04-05 18:20:36', null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('84b2602f0b044fccbb4327d45f197c33', 'zr001', '1', '5529d97ff90411e5956df8cab8256d59', null, '100', 'spming2', null, '2016-04-06 10:11:27', 'http://www.baidu.com', 'http://www.baidu.com', null, '172.31.30.17', 'PAYSOURCE_ORDER', 'CMB', null, '2016-04-05 10:11:27', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('8b3ef21619a94491b1da17cc891c8277', 'zr001', '1', '88dec97ef90311e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-04 03:22:16', 'http://www.baidu.com', 'http://www.baidu.com', '{\"id\":\"xxx\"}', '192.168.1.104', 'PAYSOURCE_ORDER', null, null, '2016-04-03 03:22:16', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('8bfcf2dfa5f0400bb859cbfc426e48ec', 'zr001', '1', '9d7b4a9cf90411e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-21 09:37:18', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/sync', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-19 09:37:18', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('8eda2b42e8644ab78a8395eaad8e1c02', 'zr001', '3', '88dec97ef90311e5956df8cab8256d59', '17c00d5acb544d858c40cd5c3ec697fd', '1', 'spming2', 'spDetail', '2016-04-08 18:16:35', 'http://www.baidu.com', 'http://www.baidu.com', null, '172.31.30.17', 'PAYSOURCE_ORDER', 'CMB', null, '2016-04-07 18:16:36', null, '2016-04-13 14:29:03', null, null, null, '2016-04-08 18:20:07', '17c00d5acb544d858c40cd5c3ec697fd');
INSERT INTO `pay_payment` VALUES ('a4c4474fe5ce4a58a1135ece676d103f', 'zr001', '1', '88dec97ef90311e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-07 11:25:08', 'http://www.baidu.com', 'http://www.baidu.com', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', 'CMB', null, '2016-04-05 11:25:09', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('b355f3bf3f2e48d7877eb4d0f9a1800d', 'zr001', '1', '9d7b4a9cf90411e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-09 18:11:04', 'http://www.baidu.com', 'http://www.baidu.com', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', 'CMB', null, '2016-04-07 18:11:05', null, '2016-04-07 18:14:08', null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('bb9a95ad95ff4061b66f574492151115', 'zr001', '1', '9d7b4a9cf90411e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-21 09:50:48', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/sync', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-19 09:50:48', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('c0864843e291419da235602278ff8936', 'zr001', '1', '9d7b4a9cf90411e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-07 09:21:57', 'http://www.baidu.com', 'http://www.baidu.com', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-05 09:21:58', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('ca4984302ac6461195b7eba7f5ca38d1', 'zr001', '1', 'be985ac317e511e68b74185e0f846dee', null, '1', 'spming', null, '2016-05-14 10:12:43', null, 'http://101.36.73.229:11007/paynotify/wx/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-05-12 10:12:43', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('ca9425abb7f444f484564511780700b0', 'zr001', '1', '5403e3bdfb9411e58f80185e0f846dee', null, '1', 'spming', null, '2016-04-15 14:50:06', null, 'http://101.36.73.229:11007/paynotify/wx/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-13 14:50:06', null, '2016-04-13 14:50:34', null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('cbc09e2a4a3a4d7f82078610e5b899ab', 'zr001', '1', '9d7b4a9cf90411e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-05 04:33:02', 'http://www.baidu.com', 'http://www.baidu.com', '{\"id\":\"xxx\"}', '192.168.1.104', 'PAYSOURCE_ORDER', 'CMB', null, '2016-04-03 04:33:02', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('d19eff3e0a98479289628ba5670c0f1d', 'zr001', '1', '9d7b4a9cf90411e5956df8cab8256d59', null, '100', 'spming2', 'spDetail', '2016-04-08 17:57:12', 'http://www.baidu.com', 'http://www.baidu.com', null, '172.31.30.17', 'PAYSOURCE_ORDER', 'CMB', null, '2016-04-07 17:57:13', null, '2016-04-07 18:07:59', null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('d1f80040a5f2450b8af86fac970720b0', 'zr001', '1', '9d7b4a9cf90411e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-14 14:58:49', null, 'http://101.36.73.229:11007/paynotify/ali/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-12 14:58:50', null, '2016-04-12 15:32:14', null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('dc482518a8f049359b7d1b9ad903b231', 'zr001', '1', '88dec97ef90311e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-05-01 18:34:53', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/sync', 'http://zgxcw.ngrok.natapp.cn/pay-web/ali/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-29 18:34:54', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('e472b2d8967c4e1885db82ec711bfbb2', 'zr001', '3', '88dec97ef90311e5956df8cab8256d59', '17c00d5acb544d858c40cd5c3ec697fd', '1', 'spming', 'spDetail', '2016-04-09 18:16:35', 'http://www.baidu.com', 'http://www.baidu.com', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', 'CMB', null, '2016-04-07 18:16:36', null, '2016-04-13 14:29:03', null, null, null, '2016-04-08 18:20:07', '17c00d5acb544d858c40cd5c3ec697fd');
INSERT INTO `pay_payment` VALUES ('e4e45982c50c489bb073c33eae7127e6', 'zr001', '1', '88dec97ef90311e5956df8cab8256d59', null, '1', 'spming', null, '2016-04-15 14:53:16', 'http://www.baidu.com', 'http://101.36.73.229:11007/paynotify/wx/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-13 14:53:17', null, null, null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('e5960adee85740f0a5ca148c14002958', 'zr001', '2', '9d7b4a9cf90411e5956df8cab8256d59', '1678eb28e5bb43c091837ba41efdedad', '1', 'spming2', 'spDetail', '2016-04-13 08:04:37', null, 'http://101.36.73.229:11007/paynotify/ali/async', null, '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-12 08:04:37', null, '2016-04-12 08:58:44', null, null, '2015-12-29 18:48:16', null, '2015122921001004610003643722');
INSERT INTO `pay_payment` VALUES ('e90b6982b1e54e17bd47daaadea15bd1', 'zr001', '1', '9d7b4a9cf90411e5956df8cab8256d59', null, '1', 'spming', 'spDetail', '2016-04-15 14:51:40', null, 'http://101.36.73.229:11007/paynotify/ali/async', '{\"id\":\"xxx\"}', '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-13 14:51:40', null, '2016-04-13 14:53:31', null, null, null, null, null);
INSERT INTO `pay_payment` VALUES ('f8ec5a0df0c84d2d836cd4bfe956827c', 'zr001', '2', '5403e3bdfb9411e58f80185e0f846dee', 'b66ed9220169494c949b6bf119cc9837', '1', 'spming2', null, '2016-04-12 17:04:32', null, 'http://101.36.73.229:11007/paynotify/wx/async', null, '172.31.30.17', 'PAYSOURCE_ORDER', null, null, '2016-04-11 17:04:33', null, '2016-04-11 18:47:14', null, null, '2016-03-21 16:19:10', null, '4001642001201603214161369254');

-- ----------------------------
-- Table structure for pay_payment_close
-- ----------------------------
DROP TABLE IF EXISTS `pay_payment_close`;
CREATE TABLE `pay_payment_close` (
  `close_id` varchar(32) NOT NULL COMMENT '关闭订单(支付单)记录id',
  `payment_id` varchar(32) DEFAULT NULL COMMENT '支付单id',
  `merchant_id` varchar(32) DEFAULT NULL COMMENT '平台商户id',
  `order_id` varchar(32) DEFAULT NULL COMMENT '订单id',
  `close_code` varchar(8) DEFAULT NULL COMMENT '关闭是否成功[T:成功，F:失败]',
  `close_note` varchar(4000) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT 'create_time',
  `create_user` varchar(32) DEFAULT NULL COMMENT 'create_user',
  PRIMARY KEY (`close_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关闭订单(支付单)记录';

-- ----------------------------
-- Records of pay_payment_close
-- ----------------------------

-- ----------------------------
-- Table structure for pay_payment_gateway
-- ----------------------------
DROP TABLE IF EXISTS `pay_payment_gateway`;
CREATE TABLE `pay_payment_gateway` (
  `gateway_id` varchar(32) NOT NULL COMMENT '支付网关Id',
  `merchant_id` varchar(32) DEFAULT NULL COMMENT '平台商户id',
  `gateway_name` varchar(64) DEFAULT NULL COMMENT '支付网关名称',
  `gateway_code` varchar(64) DEFAULT NULL COMMENT '支付网关编码',
  `gateway_state` int(11) DEFAULT NULL COMMENT '状态[0:可用, 1:不可用]',
  `gateway_key` varchar(2048) DEFAULT NULL COMMENT '秘钥',
  `gateway_account` varchar(256) DEFAULT NULL COMMENT '账号[ali:合作者身份id, wx:公众号id]]',
  `gateway_user_name` varchar(256) DEFAULT NULL COMMENT '用户名[ali:收款方支付宝账号, wx:商户号id]]',
  `return_url` varchar(1024) DEFAULT NULL COMMENT '支付返回url',
  `notify_url` varchar(1024) DEFAULT NULL COMMENT '支付通知url',
  `pay_api` varchar(1024) DEFAULT NULL COMMENT '支付订单api',
  `query_api` varchar(1024) DEFAULT NULL COMMENT '查询订单api',
  `close_api` varchar(1024) DEFAULT NULL COMMENT '关闭订单api',
  `refund_api` varchar(1024) DEFAULT NULL COMMENT '退款订单api',
  `verify_api` varchar(1024) DEFAULT NULL COMMENT '消息验证api',
  `public_key` varchar(1024) DEFAULT NULL COMMENT '验签公钥',
  `cert_path` varchar(256) DEFAULT NULL COMMENT '证书路径',
  `sign_type` varchar(32) DEFAULT NULL COMMENT '加密方式',
  `gateway_version` varchar(32) DEFAULT NULL COMMENT '接口版本',
  `create_time` datetime DEFAULT NULL COMMENT 'create_time',
  `create_user` varchar(32) DEFAULT NULL COMMENT 'create_user',
  `update_time` datetime DEFAULT NULL COMMENT 'update_time',
  `update_user` varchar(32) DEFAULT NULL COMMENT 'update_user',
  PRIMARY KEY (`gateway_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付网关';

-- ----------------------------
-- Records of pay_payment_gateway
-- ----------------------------
INSERT INTO `pay_payment_gateway` VALUES ('5403e3bdfb9411e58f80185e0f846dee', 'zr001', 'wx H5页面支付', 'WXJSAPI', '0', 'CxukqT0OWlXXXrdKFkwfIKXnlFZ11qzC', 'wxe21ad38684c7166d', '1322370501', null, null, 'https://api.mch.weixin.qq.com/pay/unifiedorder', 'https://api.mch.weixin.qq.com/pay/orderquery', 'https://api.mch.weixin.qq.com/pay/closeorder', 'https://api.mch.weixin.qq.com/secapi/pay/refund', null, null, null, 'MD5', '1.0', null, null, null, null);
INSERT INTO `pay_payment_gateway` VALUES ('5529d97ff90411e5956df8cab8256d59', 'zr001', 'ali合并支付', 'ALIMERGEPAY', '0', 'azn0ihswk1elxa7ggt63vj37efjhlzld', '2088211554961671', 'zhang9192@vip.qq.com', null, null, 'https://mapi.alipay.com/gateway.do?', 'https://mapi.alipay.com/gateway.do?', 'https://mapi.alipay.com/gateway.do?', null, 'https://mapi.alipay.com/gateway.do?service=notify_verify&', null, null, 'MD5', '1.0', null, null, null, null);
INSERT INTO `pay_payment_gateway` VALUES ('86f13154fb9411e58f80185e0f846dee', 'zr001', 'wx APP支付', 'WXAPP', '0', 'CxukqT0OWlXXXrdKFkwfIKXnlFZ11qzC', 'wxe21ad38684c7166d', '1322370501', null, null, 'https://api.mch.weixin.qq.com/pay/unifiedorder', 'https://api.mch.weixin.qq.com/pay/orderquery', 'https://api.mch.weixin.qq.com/pay/closeorder', 'https://api.mch.weixin.qq.com/secapi/pay/refund', null, null, null, 'MD5', '1.0', null, null, null, null);
INSERT INTO `pay_payment_gateway` VALUES ('88dec97ef90311e5956df8cab8256d59', 'zr001', 'ali网关/即时到账支付', 'ALIDIRECTPAY', '0', 'azn0ihswk1elxa7ggt63vj37efjhlzld', '2088211554961671', 'zhang9192@vip.qq.com', null, null, 'https://mapi.alipay.com/gateway.do?', 'https://mapi.alipay.com/gateway.do?', 'https://mapi.alipay.com/gateway.do?', null, 'https://mapi.alipay.com/gateway.do?service=notify_verify&', null, null, 'MD5', '1.0', null, null, null, null);
INSERT INTO `pay_payment_gateway` VALUES ('9d7b4a9cf90411e5956df8cab8256d59', 'zr001', 'ali移动APP支付', 'ALIMOBILEPAY', '0', 'MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALk8SzovjbXyr0EclfWEg2ToCUKqHaPoiiI5umu0DuxbkMzLdRTQN380YYAqb4s88N91Ik7lyCt/fCdgbhZlBlzSVKj/dbxgJROXTqrnLzLyV3hNPLtW6PQurMT1qhw3RZPwADEoUcuAmW0qPi4STUwEklAumOk36iX156pmTxXNAgMBAAECgYBDM3yXP2lJ+SFbneNJjymcEhJJ/S2DqXlzsT3QlSJU3WNK6QUw59k6bOZyePK+sP0yFGy98t8L6NRB9Nn7hesoY4ygLDhkCbWjQ12yJZ3bUPcWSsIhGcMC89TRjHzqpn85E/75iw2ka7NhAusok42Y9UXIMSGGYrYvOjK2W4xkAQJBAOFrQmRYQiwLztLj5TUoDntq3RJDItaMjnaEGMchADrFeFZKQlagEk1kPtN7H3u4DqFSSP13erl8FLCwLQsUpRkCQQDSXXlJ4bH/c2abv/0h1YRvJWD7UKOSO7NXbd54zWzrKqoJbh7ysY6MMJCjz1ZEr6KnfAHG56OCQcWjQf4QbnjVAkAezSQuR+0KzbY+FMvN/qIz9P8uLqRFAsG4qgYkt3qrjS4LRGxaH9dAYfE/vnQn2JePLYoxsDDBxZWiKwCMog6JAkBIxIjGI8o8wSCHU9wuLfvHrrhjdrt+RCR/Y8QHKZg5qOJ0rKEL9puGpu4BnCENVWwAX4cF1O7cif/hv1wxNuWZAkEAnFnUlX7xtQo6Yuk9QJohJ3pWawusrbY+TljQpbYYa9v9A2jrjI4wZXYqzHSGa2lTlJgyqgNnJfwq+aWXX3F9sw==', '2088211554961671', 'zhang9192@vip.qq.com', null, null, null, 'https://mapi.alipay.com/gateway.do?', 'https://mapi.alipay.com/gateway.do?', null, 'https://mapi.alipay.com/gateway.do?service=notify_verify&', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB', null, 'RSA', '1.0', null, null, null, null);
INSERT INTO `pay_payment_gateway` VALUES ('be985ac317e511e68b74185e0f846dee', 'zr001', 'wx支付工具--企业付款', 'WXMCHPAY', '0', 'CxukqT0OWlXXXrdKFkwfIKXnlFZ11qzC', 'wxe21ad38684c7166d', '1322370501', null, null, 'https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers', 'https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo', null, null, null, null, null, 'MD5', '1.0', null, null, null, null);
INSERT INTO `pay_payment_gateway` VALUES ('c1375ff9fb9311e58f80185e0f846dee', 'zr001', 'wx原生(扫码)支付(pc)', 'WXNATIVE', '0', 'CxukqT0OWlXXXrdKFkwfIKXnlFZ11qzC', 'wxe21ad38684c7166d', '1322370501', null, null, 'https://api.mch.weixin.qq.com/pay/unifiedorder', 'https://api.mch.weixin.qq.com/pay/orderquery', 'https://api.mch.weixin.qq.com/pay/closeorder', 'https://api.mch.weixin.qq.com/secapi/pay/refund', null, null, null, 'MD5', '1.0', null, null, null, null);

-- ----------------------------
-- Table structure for pay_payment_log
-- ----------------------------
DROP TABLE IF EXISTS `pay_payment_log`;
CREATE TABLE `pay_payment_log` (
  `payment_log_id` varchar(32) NOT NULL COMMENT '支付单状态日志id',
  `payment_id` varchar(32) DEFAULT NULL COMMENT '支付单id',
  `payment_state_id` int(11) DEFAULT NULL COMMENT '支付单状态id',
  `create_time` datetime DEFAULT NULL COMMENT 'create_time',
  `create_user` varchar(32) DEFAULT NULL COMMENT 'create_user',
  PRIMARY KEY (`payment_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付单状态日志';

-- ----------------------------
-- Records of pay_payment_log
-- ----------------------------
INSERT INTO `pay_payment_log` VALUES ('01b14448ed764e5aa222760ed66e04d0', '194b55ce4b8b4b38aa58a168f1e2f276', '1', '2016-04-18 10:36:20', null);
INSERT INTO `pay_payment_log` VALUES ('03a8c49bcfc44dec861cd9efbe63026f', '002d67379d654e9faaa14fc06d53e53d', '1', '2016-04-05 11:00:39', null);
INSERT INTO `pay_payment_log` VALUES ('0aa1ad818407421a8f35e9e574590082', '3624e8be48fd48bea45992e219ad37a6', '1', '2016-04-12 09:31:03', null);
INSERT INTO `pay_payment_log` VALUES ('1173457da7674fd7bd46da601e57a005', 'c0864843e291419da235602278ff8936', '1', '2016-04-05 09:21:58', null);
INSERT INTO `pay_payment_log` VALUES ('18b28df0750845048ec1d3b1675760e7', '787a9c9e035a4789941b43d737add6bc', '1', '2016-04-12 10:30:24', null);
INSERT INTO `pay_payment_log` VALUES ('1a7239192e124f75be17446adb515525', 'f8ec5a0df0c84d2d836cd4bfe956827c', '2', '2016-04-11 18:24:57', null);
INSERT INTO `pay_payment_log` VALUES ('1a7a48a1a2bc4de89c9a43c886a3307f', 'e472b2d8967c4e1885db82ec711bfbb2', '1', '2016-04-07 18:16:36', null);
INSERT INTO `pay_payment_log` VALUES ('1d6808bcc3c14500964d4e27be5db82e', '4055cae593a54e66b63463b1e5127eb7', '2', '2016-04-12 07:59:11', null);
INSERT INTO `pay_payment_log` VALUES ('21e8fa6025f94a178b320cfa55cab9ed', '249ae1c6586c4cf6988a514fde315c80', '1', '2016-04-18 10:38:50', null);
INSERT INTO `pay_payment_log` VALUES ('239c7285c6e441dc97c6d1f1280e133c', '788f4032816140048b731690aae71990', '1', '2016-05-04 18:17:28', null);
INSERT INTO `pay_payment_log` VALUES ('271e4cb4d8b64f309a3c39794f26f65e', '4b5d33328c434dbc8ca34d9c628ab8e8', '1', '2016-04-19 09:42:07', null);
INSERT INTO `pay_payment_log` VALUES ('2b20ad7a69d0422abfcb897c0ffd9e2d', '2f23dca9f92c418d821b5f05521d56f7', '1', '2016-04-06 09:15:14', null);
INSERT INTO `pay_payment_log` VALUES ('2cfcde4ab0e746648e3f8c45ed594d7c', '51f54da44ae049d39b95e0cb28c728e2', '1', '2016-04-11 17:04:33', null);
INSERT INTO `pay_payment_log` VALUES ('2d11d7383b6644148347668124af46ff', '81533a295e664e19a47b4441804c67e5', '1', '2016-04-05 15:06:53', null);
INSERT INTO `pay_payment_log` VALUES ('34b861541a5f4d94bf76deff791400fa', '0a8adac08dc841cabc93d39941ff6a14', '1', '2016-04-06 10:10:13', null);
INSERT INTO `pay_payment_log` VALUES ('39fcb6cbbd0440f2857283ac4e9f62b9', '2f23dca9f92c418d821b5f05521d56f7', '3', '2016-04-12 13:28:45', null);
INSERT INTO `pay_payment_log` VALUES ('3ad5b905983e43188404948f17a41d3b', '3624e8be48fd48bea45992e219ad37a6', '3', '2016-04-13 14:29:08', null);
INSERT INTO `pay_payment_log` VALUES ('3c7e8c253f7140b48296a60fa8d3a5aa', '8bfcf2dfa5f0400bb859cbfc426e48ec', '1', '2016-04-19 09:37:18', null);
INSERT INTO `pay_payment_log` VALUES ('4a9aba62fbf943f2a8a830f1cf229eda', 'f8ec5a0df0c84d2d836cd4bfe956827c', '2', '2016-04-11 18:47:14', null);
INSERT INTO `pay_payment_log` VALUES ('4aaea393f5f045ada0510291b4ce49b4', 'f8ec5a0df0c84d2d836cd4bfe956827c', '1', '2016-04-11 17:04:33', null);
INSERT INTO `pay_payment_log` VALUES ('4f2fa31fd0e340f78c2c4dda9fd7877d', 'b355f3bf3f2e48d7877eb4d0f9a1800d', '1', '2016-04-07 18:11:05', null);
INSERT INTO `pay_payment_log` VALUES ('4f911c1fe2f14dbda5ecbf7695eb69be', '5ffef6a52ae249418c8fb6f0f27edb53', '1', '2016-04-13 14:45:10', null);
INSERT INTO `pay_payment_log` VALUES ('53a150ee67034d60a280b83ea367544d', '5eec49cf36fe4bee9e68f12de6a4d9c3', '1', '2016-04-12 08:04:37', null);
INSERT INTO `pay_payment_log` VALUES ('60ccf2c00fe8493097dd81a4f49840da', '32554d78b2fd4ffd9f166800621b0f32', '1', '2016-04-12 10:30:24', null);
INSERT INTO `pay_payment_log` VALUES ('651d64b907d649dfa84e74b8f467ef59', '8eda2b42e8644ab78a8395eaad8e1c02', '3', '2016-04-13 14:29:03', null);
INSERT INTO `pay_payment_log` VALUES ('661434af5ad649278680ce305258c614', '5a06546f3e294c11bdceb4b190559e3c', '2', '2016-04-12 07:59:11', null);
INSERT INTO `pay_payment_log` VALUES ('66a5c83b1202489895f3953623cb1cf7', '1de92739095545b8803659fc66c2af11', '1', '2016-04-05 15:08:01', null);
INSERT INTO `pay_payment_log` VALUES ('790222000a2a41c681f1fe593edf428b', '4055cae593a54e66b63463b1e5127eb7', '2', '2016-04-12 07:43:43', null);
INSERT INTO `pay_payment_log` VALUES ('79abac5f191d42ea99a528abd0e2c513', '84b2602f0b044fccbb4327d45f197c33', '1', '2016-04-05 10:11:27', null);
INSERT INTO `pay_payment_log` VALUES ('81f55cbb9f1d4186a29c910cab21bcbb', '1e0be07992344926838a9132d550b706', '1', '2016-04-07 18:11:05', null);
INSERT INTO `pay_payment_log` VALUES ('855c7eab81c5493abfdd54fe34bdca85', '5a06546f3e294c11bdceb4b190559e3c', '2', '2016-04-12 07:43:43', null);
INSERT INTO `pay_payment_log` VALUES ('89c0ec4cd0e047c2a98e1d45e00bc8b1', '5ffef6a52ae249418c8fb6f0f27edb53', '3', '2016-04-13 14:47:31', null);
INSERT INTO `pay_payment_log` VALUES ('8ad4894eaadd4b7695d92990a684ec95', '5eec49cf36fe4bee9e68f12de6a4d9c3', '2', '2016-04-12 08:58:44', null);
INSERT INTO `pay_payment_log` VALUES ('8e19c80848aa436bbdb010ec92001489', '8b3ef21619a94491b1da17cc891c8277', '1', '2016-04-03 03:22:16', null);
INSERT INTO `pay_payment_log` VALUES ('9189a9ca61a44fcd890ef6326c12b375', 'e5960adee85740f0a5ca148c14002958', '2', '2016-04-12 08:58:44', null);
INSERT INTO `pay_payment_log` VALUES ('91d35c65076d4247b772e52dad7503a2', '5776e1e2065e4b8b82794ed2230fba82', '1', '2016-04-03 03:05:11', null);
INSERT INTO `pay_payment_log` VALUES ('9211f3c893d04aa28ae2c2f9d819eef2', 'bb9a95ad95ff4061b66f574492151115', '1', '2016-04-19 09:50:48', null);
INSERT INTO `pay_payment_log` VALUES ('9af391d26c9f41e4ba14d170ed88ef16', '318f8befed834ec8951dfe53ac90cdae', '1', '2016-04-03 03:26:28', null);
INSERT INTO `pay_payment_log` VALUES ('9b4af7e057454c728183e5a92b3f67ed', '249ae1c6586c4cf6988a514fde315c80', '2', '2016-04-18 10:43:03', null);
INSERT INTO `pay_payment_log` VALUES ('a07c55f600154eb19e5c9531d4327937', 'ca4984302ac6461195b7eba7f5ca38d1', '1', '2016-05-12 10:12:43', null);
INSERT INTO `pay_payment_log` VALUES ('a2ba719313d742799e3ab7dbbcfdcfc9', 'cbc09e2a4a3a4d7f82078610e5b899ab', '1', '2016-04-03 04:33:02', null);
INSERT INTO `pay_payment_log` VALUES ('abf75c2fb88d4d0ab30252729fd1697e', 'e4e45982c50c489bb073c33eae7127e6', '1', '2016-04-13 14:53:17', null);
INSERT INTO `pay_payment_log` VALUES ('ad42bdafe54d40ccb6fb81a9d9124594', 'd19eff3e0a98479289628ba5670c0f1d', '1', '2016-04-07 17:57:13', null);
INSERT INTO `pay_payment_log` VALUES ('b3b0f8a8555d4a31b1a1e32f9b25957e', '716cf9d02e4a4e4fbcc1711fcbf1fc57', '3', '2016-04-13 14:29:08', null);
INSERT INTO `pay_payment_log` VALUES ('b7da021a1ef34b9c972a3ef76e66c6de', 'dc482518a8f049359b7d1b9ad903b231', '1', '2016-04-29 18:34:54', null);
INSERT INTO `pay_payment_log` VALUES ('bac46858e85e4805921934fbffdd4be5', '7e519d75198c4feca8dfd9e6bcbcb57e', '1', '2016-04-13 14:54:01', null);
INSERT INTO `pay_payment_log` VALUES ('bbb31e721a1e41dc9226d26e15dd3a35', 'a4c4474fe5ce4a58a1135ece676d103f', '1', '2016-04-05 11:25:09', null);
INSERT INTO `pay_payment_log` VALUES ('bddcd8cc62a04d9191fb50aba9492ff3', 'ca9425abb7f444f484564511780700b0', '1', '2016-04-13 14:50:06', null);
INSERT INTO `pay_payment_log` VALUES ('c094c2945bfc4fd888942be021b49060', '0b571bcfb5fb4565819278513e803045', '1', '2016-04-19 09:46:28', null);
INSERT INTO `pay_payment_log` VALUES ('c59874e3a6cc482d9eb1ca6cae52f13c', '16f941624b7444ca94fd24c4090f5d94', '1', '2016-04-06 10:10:13', null);
INSERT INTO `pay_payment_log` VALUES ('c61452f718a14bdb8011d0180584dc45', '716cf9d02e4a4e4fbcc1711fcbf1fc57', '1', '2016-04-12 09:31:03', null);
INSERT INTO `pay_payment_log` VALUES ('c6c2db2d67054aa193ad02334af41969', 'e5960adee85740f0a5ca148c14002958', '1', '2016-04-12 08:04:37', null);
INSERT INTO `pay_payment_log` VALUES ('cd05807f7c274b21bae8c8b39b01d576', '655eea272ebd4d47bf569c41b11d2572', '1', '2016-04-18 10:14:36', null);
INSERT INTO `pay_payment_log` VALUES ('dab8592c207f49fe996cb61b9bd75d23', '8eda2b42e8644ab78a8395eaad8e1c02', '1', '2016-04-07 18:16:36', null);
INSERT INTO `pay_payment_log` VALUES ('dff03b4038e040f0b60a050f1a9b2b7f', '6c741450760b4d36ad5b559a83f401e5', '1', '2016-04-07 17:57:13', null);
INSERT INTO `pay_payment_log` VALUES ('e0b18bde48304839a8c8e7c04ec98327', '1f07d6eb4bca48b79f01a98f4ef874da', '1', '2016-04-03 03:26:28', null);
INSERT INTO `pay_payment_log` VALUES ('e3885ac7c3cb42b8a4e473088486b7ff', '0e6c79d83275454980c032f9e3949243', '1', '2016-04-12 10:29:24', null);
INSERT INTO `pay_payment_log` VALUES ('e492a276be9c47668d6dfee69783c450', 'd1f80040a5f2450b8af86fac970720b0', '1', '2016-04-12 14:58:50', null);
INSERT INTO `pay_payment_log` VALUES ('e77e474c883346e0867d2a6788eaeaa3', '51f54da44ae049d39b95e0cb28c728e2', '2', '2016-04-11 18:25:05', null);
INSERT INTO `pay_payment_log` VALUES ('e8b8113b82cb4a6d8028c182a61fdc81', 'e472b2d8967c4e1885db82ec711bfbb2', '3', '2016-04-13 14:29:03', null);
INSERT INTO `pay_payment_log` VALUES ('eeb58d3cfbe7403dace8261fa51623d9', '5a06546f3e294c11bdceb4b190559e3c', '1', '2016-04-12 07:20:58', null);
INSERT INTO `pay_payment_log` VALUES ('f351dfdabd154bc2a0ae08fca5caa0a6', 'e90b6982b1e54e17bd47daaadea15bd1', '1', '2016-04-13 14:51:40', null);
INSERT INTO `pay_payment_log` VALUES ('f45a1abb3cfb46af9890e34236d4ded2', '2f11c05e623d41c39a430f82803bea74', '1', '2016-04-19 18:38:59', null);
INSERT INTO `pay_payment_log` VALUES ('f54f0c67d7a247868cbf19d686ba397e', '4055cae593a54e66b63463b1e5127eb7', '1', '2016-04-12 07:20:58', null);

-- ----------------------------
-- Table structure for pay_payment_order
-- ----------------------------
DROP TABLE IF EXISTS `pay_payment_order`;
CREATE TABLE `pay_payment_order` (
  `payment_order_id` varchar(32) NOT NULL COMMENT '订单ref支付单id',
  `payment_id` varchar(32) DEFAULT NULL COMMENT '支付单id',
  `order_id` varchar(32) DEFAULT NULL COMMENT '订单id',
  `create_time` datetime DEFAULT NULL COMMENT 'create_time',
  `create_user` varchar(32) DEFAULT NULL COMMENT 'create_user',
  PRIMARY KEY (`payment_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单ref支付单';

-- ----------------------------
-- Records of pay_payment_order
-- ----------------------------
INSERT INTO `pay_payment_order` VALUES ('04a7bd2836914bdea97ede337daddc7e', '002d67379d654e9faaa14fc06d53e53d', 'test20160401009', '2016-04-05 11:00:39', null);
INSERT INTO `pay_payment_order` VALUES ('0878e8254ca14daca1d9d8831980926b', '4b5d33328c434dbc8ca34d9c628ab8e8', 'test20160403015', '2016-04-19 09:42:07', null);
INSERT INTO `pay_payment_order` VALUES ('0ac03523e27c4604b2364d110dc4b864', 'e90b6982b1e54e17bd47daaadea15bd1', 'test20160402004', '2016-04-13 14:51:40', null);
INSERT INTO `pay_payment_order` VALUES ('0b66d2010626457ea25aa59fcfc7cbb5', '194b55ce4b8b4b38aa58a168f1e2f276', 'test20160403012', '2016-04-18 10:36:20', null);
INSERT INTO `pay_payment_order` VALUES ('0d8ebab069a44fc5b3aad32049703eb3', 'cbc09e2a4a3a4d7f82078610e5b899ab', 'test20160401005', '2016-04-03 04:33:02', null);
INSERT INTO `pay_payment_order` VALUES ('2b9cc79de66c41e48018196b38fae22d', '0e6c79d83275454980c032f9e3949243', 'test20160401065', '2016-04-12 10:29:24', null);
INSERT INTO `pay_payment_order` VALUES ('2d22e9c5974a4b91a3d58fad887b1bd3', '5776e1e2065e4b8b82794ed2230fba82', 'test20160401001', '2016-04-03 03:05:11', null);
INSERT INTO `pay_payment_order` VALUES ('32ce5403ecf540bda9e58f8a72950ebe', '32554d78b2fd4ffd9f166800621b0f32', 'test20160402002', '2016-04-12 10:30:24', null);
INSERT INTO `pay_payment_order` VALUES ('3663ac384d074dbfac163235ef11239b', 'dc482518a8f049359b7d1b9ad903b231', 'test20160403019', '2016-04-29 18:34:54', null);
INSERT INTO `pay_payment_order` VALUES ('387e0d5516f14a9e9692bc213fc29dff', 'd1f80040a5f2450b8af86fac970720b0', 'test20160402003', '2016-04-12 14:58:50', null);
INSERT INTO `pay_payment_order` VALUES ('38a24f4ef1a84593afffe67e130e0041', 'bb9a95ad95ff4061b66f574492151115', 'test20160403017', '2016-04-19 09:50:48', null);
INSERT INTO `pay_payment_order` VALUES ('4420579cad8e40a7b857f2994be20b2c', '2f23dca9f92c418d821b5f05521d56f7', 'test20160401020', '2016-04-06 09:15:14', null);
INSERT INTO `pay_payment_order` VALUES ('4a7688f1faa24141804c39f7f0bb2b0c', '787a9c9e035a4789941b43d737add6bc', 'test20160402001', '2016-04-12 10:30:24', null);
INSERT INTO `pay_payment_order` VALUES ('4eb997b649c4402ea75b323d5bddd907', '5a06546f3e294c11bdceb4b190559e3c', 'test20160401061', '2016-04-12 07:20:58', null);
INSERT INTO `pay_payment_order` VALUES ('5398001097e94d688c2a89e5e18dc388', '16f941624b7444ca94fd24c4090f5d94', 'test20160401032', '2016-04-06 10:10:13', null);
INSERT INTO `pay_payment_order` VALUES ('555d55c2801f4f33b96bec93a01ad95c', '51f54da44ae049d39b95e0cb28c728e2', 'test20160401051', '2016-04-11 17:04:33', null);
INSERT INTO `pay_payment_order` VALUES ('568df8b05cc64a868a9d179d5167a37e', 'ca4984302ac6461195b7eba7f5ca38d1', 'test20160401102', '2016-05-12 10:12:43', null);
INSERT INTO `pay_payment_order` VALUES ('6739b802127541db9d6919904351e24a', 'd19eff3e0a98479289628ba5670c0f1d', 'test20160401042', '2016-04-07 17:57:13', null);
INSERT INTO `pay_payment_order` VALUES ('73e3070ee84e4a76bf96b864a57f1498', '0a8adac08dc841cabc93d39941ff6a14', 'test20160401031', '2016-04-06 10:10:13', null);
INSERT INTO `pay_payment_order` VALUES ('76393f8d88b3426d83193da0507161a8', 'c0864843e291419da235602278ff8936', 'test20160401007', '2016-04-05 09:21:58', null);
INSERT INTO `pay_payment_order` VALUES ('7ce713cdc7c944a19f016568c330a268', '7e519d75198c4feca8dfd9e6bcbcb57e', 'test20160402010', '2016-04-13 14:54:01', null);
INSERT INTO `pay_payment_order` VALUES ('884d50c0b512485087a759fc02d61ae7', '8b3ef21619a94491b1da17cc891c8277', 'test20160401002', '2016-04-03 03:22:16', null);
INSERT INTO `pay_payment_order` VALUES ('8fdd8b0f754e4a4984f6c034457bf6b5', '8bfcf2dfa5f0400bb859cbfc426e48ec', 'test20160403014', '2016-04-19 09:37:18', null);
INSERT INTO `pay_payment_order` VALUES ('91a682a00332490398534513f94bdbd8', 'b355f3bf3f2e48d7877eb4d0f9a1800d', 'test20160401043', '2016-04-07 18:11:05', null);
INSERT INTO `pay_payment_order` VALUES ('92aa012bd50045348494c432098c31d2', '318f8befed834ec8951dfe53ac90cdae', 'test20160401003', '2016-04-03 03:26:28', null);
INSERT INTO `pay_payment_order` VALUES ('92ccc674c19546b8b6e82681fa166eb8', 'e5960adee85740f0a5ca148c14002958', 'test20160401064', '2016-04-12 08:04:37', null);
INSERT INTO `pay_payment_order` VALUES ('9575ba58de9b44628c84abdb2974c571', '249ae1c6586c4cf6988a514fde315c80', 'test20160403013', '2016-04-18 10:38:50', null);
INSERT INTO `pay_payment_order` VALUES ('98b58322e0334b6ea8ec62763aed65bc', '1f07d6eb4bca48b79f01a98f4ef874da', 'test20160401004', '2016-04-03 03:26:28', null);
INSERT INTO `pay_payment_order` VALUES ('9e981b47d1ed4f6e9a4b2602ae1a156a', '655eea272ebd4d47bf569c41b11d2572', 'test20160403011', '2016-04-18 10:14:36', null);
INSERT INTO `pay_payment_order` VALUES ('9f3b9b8e7ed24ad3bbfd00dc2a2cf9cd', '2f11c05e623d41c39a430f82803bea74', 'test20160403018', '2016-04-19 18:38:59', null);
INSERT INTO `pay_payment_order` VALUES ('a084519570544f52a7b61743639ec59c', '81533a295e664e19a47b4441804c67e5', 'test20160401011', '2016-04-05 15:06:53', null);
INSERT INTO `pay_payment_order` VALUES ('a29aee8c669e41058d4bf8d3eee62f2f', '1de92739095545b8803659fc66c2af11', 'test20160401012', '2016-04-05 15:08:01', null);
INSERT INTO `pay_payment_order` VALUES ('b0f72bb75e554f31a514ca33775bc0d5', '6c741450760b4d36ad5b559a83f401e5', 'test20160401041', '2016-04-07 17:57:13', null);
INSERT INTO `pay_payment_order` VALUES ('b2c50709cbd548dab5e340a8d0ea7192', '788f4032816140048b731690aae71990', 'test20160401101', '2016-05-04 18:17:28', null);
INSERT INTO `pay_payment_order` VALUES ('ba6f3588eb3947eb86efe2d6f9428577', '5ffef6a52ae249418c8fb6f0f27edb53', 'test20160401058', '2016-04-13 14:45:10', null);
INSERT INTO `pay_payment_order` VALUES ('c356b5775fdf487fa6fb23baf891d45e', '4055cae593a54e66b63463b1e5127eb7', 'test20160401062', '2016-04-12 07:20:58', null);
INSERT INTO `pay_payment_order` VALUES ('c3b5bf05b6e4474695860587a4fe6d35', '0b571bcfb5fb4565819278513e803045', 'test20160403016', '2016-04-19 09:46:28', null);
INSERT INTO `pay_payment_order` VALUES ('c3c7dbce8b0645e2af2aa4a951083b17', '5eec49cf36fe4bee9e68f12de6a4d9c3', 'test20160401063', '2016-04-12 08:04:37', null);
INSERT INTO `pay_payment_order` VALUES ('c551db17c20f478ab11f8c728211c9b1', '84b2602f0b044fccbb4327d45f197c33', 'test20160401008', '2016-04-05 10:11:27', null);
INSERT INTO `pay_payment_order` VALUES ('cd16d38eb8ed4730a287e41df7fae1e5', 'e4e45982c50c489bb073c33eae7127e6', 'test20160401100', '2016-04-13 14:53:17', null);
INSERT INTO `pay_payment_order` VALUES ('cf2c44d3f05b4e6d818dd1c346f64bfb', 'f8ec5a0df0c84d2d836cd4bfe956827c', 'test20160401052', '2016-04-11 17:04:33', null);
INSERT INTO `pay_payment_order` VALUES ('d0f16287b6c944bf8efb4e940286464f', '8eda2b42e8644ab78a8395eaad8e1c02', 'test20160401047', '2016-04-07 18:16:36', null);
INSERT INTO `pay_payment_order` VALUES ('d401998becd448f68e552204fdcf4258', 'ca9425abb7f444f484564511780700b0', 'test20160401059', '2016-04-13 14:50:06', null);
INSERT INTO `pay_payment_order` VALUES ('d9711167a0e4470484446eb9f5c07170', '1e0be07992344926838a9132d550b706', 'test20160401044', '2016-04-07 18:11:05', null);
INSERT INTO `pay_payment_order` VALUES ('dff2e091be8e4574bc43263d15d66687', 'a4c4474fe5ce4a58a1135ece676d103f', 'test20160401010', '2016-04-05 11:25:09', null);
INSERT INTO `pay_payment_order` VALUES ('e55b7c2446504508a0eef4e6fb35f1b1', 'e472b2d8967c4e1885db82ec711bfbb2', 'test20160401046', '2016-04-07 18:16:36', null);
INSERT INTO `pay_payment_order` VALUES ('f062c6193ef6485e92d8b7e1a855f982', '3624e8be48fd48bea45992e219ad37a6', 'test20160401055', '2016-04-12 09:31:03', null);
INSERT INTO `pay_payment_order` VALUES ('f8a1a105e1fe43868f84893614eeb800', '716cf9d02e4a4e4fbcc1711fcbf1fc57', 'test20160401054', '2016-04-12 09:31:03', null);

-- ----------------------------
-- Table structure for pay_payment_state
-- ----------------------------
DROP TABLE IF EXISTS `pay_payment_state`;
CREATE TABLE `pay_payment_state` (
  `payment_state_id` int(11) NOT NULL COMMENT '支付单状态id',
  `state_name` varchar(128) DEFAULT NULL COMMENT '状态名',
  `state_noet` varchar(512) DEFAULT NULL COMMENT '状态备注',
  `create_time` datetime DEFAULT NULL COMMENT 'create_time',
  `create_user` varchar(32) DEFAULT NULL COMMENT 'create_user',
  PRIMARY KEY (`payment_state_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付单状态枚举';

-- ----------------------------
-- Records of pay_payment_state
-- ----------------------------

-- ----------------------------
-- Table structure for pay_ref_merge_payment
-- ----------------------------
DROP TABLE IF EXISTS `pay_ref_merge_payment`;
CREATE TABLE `pay_ref_merge_payment` (
  `ref_id` varchar(32) NOT NULL COMMENT 'ref_id',
  `merge_payment_id` varchar(32) DEFAULT NULL COMMENT '合并支付id',
  `payment_id` varchar(32) DEFAULT NULL COMMENT '支付单id',
  `create_time` datetime DEFAULT NULL COMMENT 'create_time',
  `create_user` varchar(32) DEFAULT NULL COMMENT 'create_user',
  PRIMARY KEY (`ref_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ref-支付单-合并支付单';

-- ----------------------------
-- Records of pay_ref_merge_payment
-- ----------------------------
INSERT INTO `pay_ref_merge_payment` VALUES ('008365ccf69c49f9b3342f7b56b7c755', '4c7be7247efc485790a943198f85353a', '655eea272ebd4d47bf569c41b11d2572', '2016-04-18 10:14:36', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('03a0c9ed04984038a6893b0415d1c086', '78b2de19706c4ab9b29f99d41fa6b591', '249ae1c6586c4cf6988a514fde315c80', '2016-04-18 10:38:50', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('0439e3b6abb7403b88d185ed8c513abe', '062e4a553aa14624bcb6f4ee8fe79cf1', '2f23dca9f92c418d821b5f05521d56f7', '2016-04-06 09:24:28', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('07029f9bbdca41ab9ca224d1ab9a825a', '3780302428394c298a457019a5b3a774', '6c741450760b4d36ad5b559a83f401e5', '2016-04-07 17:57:13', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('074f0a32de414fccbdffdf73a6a4d8da', '41cc1a97c1f64b7b9259544a6dcb0594', '1de92739095545b8803659fc66c2af11', '2016-04-05 18:20:36', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('07ccea348c254a2ba5439115daceb706', '0993d80cd46d462b9078524858f976fc', '787a9c9e035a4789941b43d737add6bc', '2016-04-12 10:30:24', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('08927b28b28a460f850db89548e83151', '1f43d88d626049b48d4cac2d402bc8ca', '2f23dca9f92c418d821b5f05521d56f7', '2016-04-06 10:31:11', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('0e1c5d73eb914e2d9658d6cb8452d028', '5223f4071f97463083593484d535e54b', 'd19eff3e0a98479289628ba5670c0f1d', '2016-04-07 18:07:59', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('1502ec4f28f24f3a8673ea52021fa52b', 'dea625a34a9e45ed8b2d1426c5d6e981', 'd1f80040a5f2450b8af86fac970720b0', '2016-04-12 15:19:50', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('2a647e77a8e74049b10c3a69e9c8970b', '3845772a0e984d0388796da22dff7922', 'e4e45982c50c489bb073c33eae7127e6', '2016-04-13 14:53:17', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('2e30aaa765e5474b92aac41038fdcb61', '681e49bda4c248a4a38fc361d0e7eec2', '5a06546f3e294c11bdceb4b190559e3c', '2016-04-12 07:20:58', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('31f5fddf94794786b5aec5a9e704f212', '0c75b4c4075c4a1e8e82a6f204d557c9', '1de92739095545b8803659fc66c2af11', '2016-04-05 15:08:01', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('37bb2ead43304e2db6094db41bab1600', 'd9a4addcf6f44e2488f6b197a15b81b6', 'b355f3bf3f2e48d7877eb4d0f9a1800d', '2016-04-07 18:11:05', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('39adc7b6bc9c48acba3c273ebfb3d82d', 'e25804d5dcff4012aa8591725c5a5911', '2f23dca9f92c418d821b5f05521d56f7', '2016-04-06 09:19:56', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('3a6eaea12ff744b2809317bbd3d3b9f6', '02ac7c5541a34abd9af57e2315dd5943', '2f23dca9f92c418d821b5f05521d56f7', '2016-04-06 09:17:50', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('3af5851f100b4e3485656c4911f98226', 'b709ada0fc1641799f33a3c94feb4207', '788f4032816140048b731690aae71990', '2016-05-04 18:17:28', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('402a7e9d61194fe7b8c374213cc69b79', 'e4031b6a8f454b2a97d752ffe26f7362', 'd1f80040a5f2450b8af86fac970720b0', '2016-04-12 15:01:05', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('411f17251b784e79afb4904127d58eeb', '74752722117a416fbea89653b7b74c6b', '716cf9d02e4a4e4fbcc1711fcbf1fc57', '2016-04-12 09:32:18', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('4702f8647b3541fc9c66419fc4ec9719', '17c00d5acb544d858c40cd5c3ec697fd', '8eda2b42e8644ab78a8395eaad8e1c02', '2016-04-07 18:16:36', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('54f39e2ed0d94d5fbb1ce40c46900de0', '8c007ff22604438c8578562132f34e44', '2f23dca9f92c418d821b5f05521d56f7', '2016-04-06 09:47:17', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('5915cc37ffc844f0818667df6a8096be', '322d0c0836f841fc90eb3f99d0033f54', '5ffef6a52ae249418c8fb6f0f27edb53', '2016-04-13 14:45:10', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('5efb7ea6637c4a36b5bcee8bab7d2150', 'b247cd27bd704fd390a9d5ff70a7c47f', 'e90b6982b1e54e17bd47daaadea15bd1', '2016-04-13 14:51:40', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('643bc225cc2c49f1abcb6980582b654c', '995ac2ea44e8492eac18468d7988329c', 'a4c4474fe5ce4a58a1135ece676d103f', '2016-04-05 13:46:56', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('65c902b28755413991939e73daa1de36', '0993d80cd46d462b9078524858f976fc', '32554d78b2fd4ffd9f166800621b0f32', '2016-04-12 10:30:24', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('679d4348a44045c5a26e89cab2af647e', 'b66ed9220169494c949b6bf119cc9837', 'f8ec5a0df0c84d2d836cd4bfe956827c', '2016-04-11 17:04:33', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('6ca7df6764ca4f0b8ec864a918726d89', '24e0c6ef6ac1447ab4420efc18915ca7', 'ca9425abb7f444f484564511780700b0', '2016-04-13 14:50:06', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('7beb02bb15504bddaff83a9210981b60', '02e8e69ee9924f6d8796c224064cf866', 'ca4984302ac6461195b7eba7f5ca38d1', '2016-05-12 10:12:43', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('889f84e8761a4e4184b3ae2b05d7da9d', '938228f8d1cb455f8265b29e06f9690e', '6c741450760b4d36ad5b559a83f401e5', '2016-04-07 18:04:09', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('8b8dba4000b74d20bf983ca946c217a2', '45e42590ad0346be851dde598131f13d', '0a8adac08dc841cabc93d39941ff6a14', '2016-04-06 10:10:13', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('8f279bbcf2ca4ee78a82de1c6864a320', '62e8612a9bc44fcca0c78f7540dedc72', 'd1f80040a5f2450b8af86fac970720b0', '2016-04-12 14:58:50', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('9354ed99cb6d4237889d4569bd18ce98', 'f6fe3167cadf485d9c85cb938d32c0ce', '2f23dca9f92c418d821b5f05521d56f7', '2016-04-06 10:28:08', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('9851e4f40efd4a848eda9046b34f4ab7', 'e8662cf200494030a23b2d8f6db7e879', '3624e8be48fd48bea45992e219ad37a6', '2016-04-12 09:31:03', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('9ca7f1a6f14b4c8a8c248bfe73d12476', 'b66ed9220169494c949b6bf119cc9837', '51f54da44ae049d39b95e0cb28c728e2', '2016-04-11 17:04:33', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('9dc1461bbd624f499114cad08525f0de', '681e49bda4c248a4a38fc361d0e7eec2', '4055cae593a54e66b63463b1e5127eb7', '2016-04-12 07:20:58', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('9de557c0cb0740dfa3fcb2f416a4c712', '17c00d5acb544d858c40cd5c3ec697fd', 'e472b2d8967c4e1885db82ec711bfbb2', '2016-04-07 18:16:36', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('9e42d56b444743adb1cb68d89a08c7db', 'e7af44bd29a745cea5ed1666c3b2ca3d', 'ca9425abb7f444f484564511780700b0', '2016-04-13 14:50:34', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('a17ef2bcaa8b45aa83e301f292a6d97b', 'e63f89bfe95a4d278604fb38a0768eed', '0b571bcfb5fb4565819278513e803045', '2016-04-19 09:46:28', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('ac9c646b1d3a4450bb9357a372559474', 'faab9c666b884cffa44c9ab771f049d9', 'bb9a95ad95ff4061b66f574492151115', '2016-04-19 09:50:48', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('af66874f2d4d49f2a54c787e6254eed3', 'bac5e75fffd34e129e3d083b4277254a', '716cf9d02e4a4e4fbcc1711fcbf1fc57', '2016-04-12 09:32:01', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('b0ce9aa3a7974d6d9275c28d6f0eda1d', '5706b6af164a47d28f349e38ea7926f4', '7e519d75198c4feca8dfd9e6bcbcb57e', '2016-04-13 14:54:01', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('b1435427f94a408788dc66acad1cff4a', '3780302428394c298a457019a5b3a774', 'd19eff3e0a98479289628ba5670c0f1d', '2016-04-07 17:57:13', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('b44477a67e8a48c880c0afa705255b24', 'f933eeaccc1c412fab318eaac3342872', 'e90b6982b1e54e17bd47daaadea15bd1', '2016-04-13 14:53:31', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('b5a9b4fba6ca45698f1a1577ba482660', 'dea351cc2dc148598cea2727ee2e7b4f', '8bfcf2dfa5f0400bb859cbfc426e48ec', '2016-04-19 09:37:18', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('b939e2a30cc94af680f94a3aed61e1b0', '41cc1a97c1f64b7b9259544a6dcb0594', '81533a295e664e19a47b4441804c67e5', '2016-04-05 18:20:36', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('bc4cfbc5ac034cfd8fd16ad6c52acbe4', '156a677934db4fe98adabaa332e6af08', '81533a295e664e19a47b4441804c67e5', '2016-04-05 15:06:53', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('c0b5d863cc214212a4d998754093af97', 'f29c31753b064b059fc61604edd61d31', '194b55ce4b8b4b38aa58a168f1e2f276', '2016-04-18 10:36:20', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('c0eb2349457e4ebfb002be0c884bd20a', 'bac5e75fffd34e129e3d083b4277254a', '3624e8be48fd48bea45992e219ad37a6', '2016-04-12 09:32:01', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('c43c6a3a8d5b4309b98bda2be842038f', '6d0fb6de4cea4dedb8a66934b5b718d1', '2f23dca9f92c418d821b5f05521d56f7', '2016-04-06 09:15:14', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('c65d49e7f4854d489173e9fe80725663', '66d76f83ec8043068bad037324303e3a', '2f23dca9f92c418d821b5f05521d56f7', '2016-04-06 20:15:25', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('c8d7e05a39ac43a8ac0beea14150e6fe', '938228f8d1cb455f8265b29e06f9690e', 'd19eff3e0a98479289628ba5670c0f1d', '2016-04-07 18:04:09', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('ccd96952ca014a5bb1199865f4a4f7a8', '0f34bdf4e1714dc29863d4e2bc92a00d', '2f23dca9f92c418d821b5f05521d56f7', '2016-04-06 10:35:04', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('d0043390fb8844fc9e9347ce61efd1de', '1678eb28e5bb43c091837ba41efdedad', 'e5960adee85740f0a5ca148c14002958', '2016-04-12 08:04:38', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('d10dad2cfe1e419c91bda1bc50f13314', 'e8662cf200494030a23b2d8f6db7e879', '716cf9d02e4a4e4fbcc1711fcbf1fc57', '2016-04-12 09:31:03', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('d7ab8507182641f3b05189034e42e234', '42eb4fe8fcdc470e9580bb49324a5a40', 'd1f80040a5f2450b8af86fac970720b0', '2016-04-12 15:32:14', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('d7d811931c8c4400b803e60828eae0a2', '74752722117a416fbea89653b7b74c6b', '3624e8be48fd48bea45992e219ad37a6', '2016-04-12 09:32:18', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('d9750d55eedb4ea0a2d791b0342aaee5', '1678eb28e5bb43c091837ba41efdedad', '5eec49cf36fe4bee9e68f12de6a4d9c3', '2016-04-12 08:04:38', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('db8d50279394480ebeed625d9f4e79b0', '369b0c2663034ac08b0df8abd6865af0', '2f23dca9f92c418d821b5f05521d56f7', '2016-04-06 10:34:43', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('df52903cd1094c888e1ce00252389656', '7dd60983ba1140dfb6783ca970f8be95', '2f23dca9f92c418d821b5f05521d56f7', '2016-04-06 09:19:26', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('e2d2c5c34587435f9d510baaff037a4f', '0c75b4c4075c4a1e8e82a6f204d557c9', '81533a295e664e19a47b4441804c67e5', '2016-04-05 15:08:01', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('e43028a523b9432d93a7736b458bfc2d', 'd9a4addcf6f44e2488f6b197a15b81b6', '1e0be07992344926838a9132d550b706', '2016-04-07 18:11:05', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('e43bd2ce653b45229848744be5670b2e', 'f8ede68cf4004d74aebe988925855186', 'd1f80040a5f2450b8af86fac970720b0', '2016-04-12 15:31:54', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('e7d79c5e73b3447caa8135ca824f9603', '45e42590ad0346be851dde598131f13d', '16f941624b7444ca94fd24c4090f5d94', '2016-04-06 10:10:13', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('ea52b5144c034f2182181a09074869a6', '5223f4071f97463083593484d535e54b', '6c741450760b4d36ad5b559a83f401e5', '2016-04-07 18:07:59', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('ef91644833014ab3b5220f25d770995d', '0f9d177cee904a578f91cb69567f52fc', '2f11c05e623d41c39a430f82803bea74', '2016-04-19 18:38:59', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('f0c74a44888a43a3bd1a8a705b2f018d', '46f6591d3f86436d90e51259024875c0', '4b5d33328c434dbc8ca34d9c628ab8e8', '2016-04-19 09:42:07', null);
INSERT INTO `pay_ref_merge_payment` VALUES ('fb7b369f55ad47e78d9f8be9c4ea8654', 'e1fc455d8bcc4e3faa283c996b0ab259', 'dc482518a8f049359b7d1b9ad903b231', '2016-04-29 18:34:54', null);

-- ----------------------------
-- Table structure for pay_repeat_payment
-- ----------------------------
DROP TABLE IF EXISTS `pay_repeat_payment`;
CREATE TABLE `pay_repeat_payment` (
  `reapeat_id` varchar(32) NOT NULL COMMENT '重复支付id',
  `merchant_id` varchar(32) DEFAULT NULL COMMENT '平台商户id',
  `payment_id` varchar(32) DEFAULT NULL COMMENT '支付单id',
  `gateway_id` varchar(32) DEFAULT NULL COMMENT '支付网关Id',
  `merge_payment_id` varchar(32) DEFAULT NULL COMMENT '合并支付id',
  `total_fee` int(11) DEFAULT NULL COMMENT '总金额(分)',
  `third_trade_no` varchar(64) DEFAULT NULL COMMENT '第三方交易号(ali/wechat流水号)',
  `trade_time` datetime DEFAULT NULL COMMENT '实际支付时间',
  `payment_type` int(11) DEFAULT NULL COMMENT '支付方式[1:ali支付, 2:微信支付]',
  `create_time` datetime DEFAULT NULL COMMENT 'create_time',
  `create_user` varchar(32) DEFAULT NULL COMMENT 'create_user',
  PRIMARY KEY (`reapeat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='重复支付单记录';

-- ----------------------------
-- Records of pay_repeat_payment
-- ----------------------------
INSERT INTO `pay_repeat_payment` VALUES ('a47ac409fed840599bfac4ef815854c4', 'zr001', '2f23dca9f92c418d821b5f05521d56f7', 'c1375ff9fb9311e58f80185e0f846dee', '8c007ff22604438c8578562132f34e44', '1', 'bbccddxxxxxjjyy', null, '2', '2016-04-13 14:41:44', null);

-- ----------------------------
-- Table structure for pay_wx_coupon
-- ----------------------------
DROP TABLE IF EXISTS `pay_wx_coupon`;
CREATE TABLE `pay_wx_coupon` (
  `yh_id` varchar(32) NOT NULL COMMENT '微信支付优惠id',
  `wx_notify_id` varchar(32) DEFAULT NULL COMMENT '异步通知id',
  `coupon_id` varchar(20) DEFAULT NULL COMMENT '代金券或立减优惠ID',
  `coupon_fee` int(11) DEFAULT NULL COMMENT '单个代金券或立减优惠支付金额(分)',
  PRIMARY KEY (`yh_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信支付优惠券';

-- ----------------------------
-- Records of pay_wx_coupon
-- ----------------------------

-- ----------------------------
-- Table structure for pay_wx_notify
-- ----------------------------
DROP TABLE IF EXISTS `pay_wx_notify`;
CREATE TABLE `pay_wx_notify` (
  `wx_notify_id` varchar(32) NOT NULL COMMENT '异步通知id',
  `merge_payment_id` varchar(32) DEFAULT NULL COMMENT '合并支付id',
  `return_code` varchar(16) DEFAULT NULL COMMENT '返回状态码',
  `return_msg` varchar(128) DEFAULT NULL COMMENT '返回信息',
  `appid` varchar(32) DEFAULT NULL COMMENT '公众账号id',
  `mch_id` varchar(32) DEFAULT NULL COMMENT '商户号',
  `device_info` varchar(32) DEFAULT NULL COMMENT '设备号',
  `nonce_str` varchar(32) DEFAULT NULL COMMENT '随机字符串',
  `sign` varchar(32) DEFAULT NULL COMMENT '签名',
  `result_code` varchar(16) DEFAULT NULL COMMENT '业务结果',
  `err_code` varchar(32) DEFAULT NULL COMMENT '错误代码',
  `err_code_des` varchar(128) DEFAULT NULL COMMENT '错误代码描述',
  `openid` varchar(128) DEFAULT NULL COMMENT '用户标识',
  `is_subscribe` varchar(1) DEFAULT NULL COMMENT '是否关注公众账号',
  `trade_type` varchar(16) DEFAULT NULL COMMENT '交易类型',
  `bank_type` varchar(16) DEFAULT NULL COMMENT '付款银行',
  `total_fee` int(11) DEFAULT NULL COMMENT '总金额(分)',
  `fee_type` varchar(8) DEFAULT NULL COMMENT '货币种类',
  `cash_fee` int(11) DEFAULT NULL COMMENT '现金支付金额(分)',
  `cash_fee_type` varchar(16) DEFAULT NULL COMMENT '现金支付货币类型',
  `coupon_fee` int(11) DEFAULT NULL COMMENT '代金券立减优惠金额(分)',
  `coupon_count` int(11) DEFAULT NULL COMMENT '代金券或立减优惠使用数量',
  `transaction_id` varchar(32) DEFAULT NULL COMMENT '微信支付订单号',
  `out_trade_no` varchar(32) DEFAULT NULL COMMENT '商户订单号',
  `attach` varchar(128) DEFAULT NULL COMMENT '商家数据包',
  `time_end` varchar(14) DEFAULT NULL COMMENT '支付完成时间',
  `notify_value` varchar(4000) DEFAULT NULL COMMENT '异步通知参数',
  `create_time` datetime DEFAULT NULL COMMENT 'create_time',
  `create_user` varchar(32) DEFAULT NULL COMMENT 'create_user',
  PRIMARY KEY (`wx_notify_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信异步通知';

-- ----------------------------
-- Records of pay_wx_notify
-- ----------------------------
INSERT INTO `pay_wx_notify` VALUES ('072d1afeedeb41d79e6cc2789e536c0e', null, 'SUCCESS', null, 'wxe21ad38684c7166d', '1322370501', null, 'goxla72g1a9k08mpbn8sarg9somys1yr', '31D54C6185BD313EE89B4E46B8664B84', 'SUCCESS', null, null, 'oaVwYwwqPUFvPhFbhyUjZkUY8AsI', 'Y', 'NATIVE', 'CFT', '1', 'CNY', '1', null, null, null, '4001642001201603214161369254', 'b66ed9220169494c949b6bf119cc9837', null, '20160321161910', null, '2016-04-11 18:47:08', null);
INSERT INTO `pay_wx_notify` VALUES ('075186e5045646a69a7c225c48204e81', null, null, null, 'appid', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-04-01 00:51:13', null);
INSERT INTO `pay_wx_notify` VALUES ('3135c58eb9bd44cc8ccf6ee9806a88a4', null, 'SUCCESS', null, 'wxe21ad38684c7166d', '1322370501', null, 'goxla72g1a9k08mpbn8sarg9somys1yr', '31D54C6185BD313EE89B4E46B8664B84', 'SUCCESS', null, null, 'oaVwYwwqPUFvPhFbhyUjZkUY8AsI', 'Y', 'NATIVE', 'CFT', '1', 'CNY', '1', null, null, null, '4001642001201603214161369254', 'b66ed9220169494c949b6bf119cc9836', null, '20160321161910', null, '2016-04-11 18:24:30', null);
INSERT INTO `pay_wx_notify` VALUES ('409709f276fd471db3487298cab5f6b6', null, 'SUCCESS', null, 'wxe21ad38684c7166d', '1322370501', null, 'goxla72g1a9k08mpbn8sarg9somys1yr', '31D54C6185BD313EE89B4E46B8664B84', 'SUCCESS', null, null, 'oaVwYwwqPUFvPhFbhyUjZkUY8AsI', 'Y', 'NATIVE', 'CFT', '1', 'CNY', '1', null, null, null, '4001642001201603214161369254', 'b66ed9220169494c949b6bf119cc9837', null, '20160321161910', null, '2016-04-11 18:45:50', null);
INSERT INTO `pay_wx_notify` VALUES ('49c6b4aa8a4b44c6b192ba41d93a076d', null, 'SUCCESS', null, 'wxe21ad38684c7166d', '1322370501', null, 'goxla72g1a9k08mpbn8sarg9somys1yr', '31D54C6185BD313EE89B4E46B8664B84', 'SUCCESS', null, null, 'oaVwYwwqPUFvPhFbhyUjZkUY8AsI', 'Y', 'NATIVE', 'CFT', '1', 'CNY', '1', null, null, null, '4001642001201603214161369254', 'b66ed9220169494c949b6bf119cc9836', null, '20160321161910', null, '2016-04-11 18:05:52', null);
