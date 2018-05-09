/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : xpayplatform

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2016-04-13 14:57:06
*/

SET FOREIGN_KEY_CHECKS=0;

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
INSERT INTO `pay_payment_gateway` VALUES ('c1375ff9fb9311e58f80185e0f846dee', 'zr001', 'wx原生(扫码)支付(pc)', 'WXNATIVE', '0', 'CxukqT0OWlXXXrdKFkwfIKXnlFZ11qzC', 'wxe21ad38684c7166d', '1322370501', null, null, 'https://api.mch.weixin.qq.com/pay/unifiedorder', 'https://api.mch.weixin.qq.com/pay/orderquery', 'https://api.mch.weixin.qq.com/pay/closeorder', 'https://api.mch.weixin.qq.com/secapi/pay/refund', null, null, null, 'MD5', '1.0', null, null, null, null);
