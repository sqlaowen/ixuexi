drop table if exists pay_ali_notify;

drop table if exists pay_ali_return;

drop table if exists pay_mergePayment_log;

drop table if exists pay_merge_payment;

drop table if exists pay_payment;

drop table if exists pay_payment_gateway;

drop table if exists pay_payment_log;

drop table if exists pay_payment_order;

drop table if exists pay_payment_reqLog;

drop table if exists pay_payment_state;

drop table if exists pay_ref_merge_payment;

drop table if exists pay_repeat_payment;

drop table if exists pay_wx_coupon;

drop table if exists pay_wx_notify;

/*==============================================================*/
/* Table: pay_ali_notify                                        */
/*==============================================================*/
create table pay_ali_notify
(
   ali_notify_id        varchar(32) not null comment '异步通知ID',
   merge_payment_id     varchar(32) comment '合并支付id',
   out_trade_no         varchar(32) comment '商户订单号',
   notify_time          datetime comment '通知时间',
   notify_type          varchar(32) comment '通知类型',
   notify_id            varchar(512) comment '通知校验ID',
   sign_type            varchar(16) comment '签名方式',
   sign                 varchar(512) comment '签名',
   subject              varchar(256) comment '商品名称',
   payment_type         varchar(16) comment '支付类型',
   trade_no             varchar(64) comment '支付宝交易号',
   trade_status         varchar(32) comment '交易状态',
   gmt_create           datetime comment '交易创建时间',
   gmt_payment          datetime comment '交易付款时间',
   gmt_close            datetime comment '交易关闭时间',
   refund_status        varchar(32) comment '退款状态',
   gmt_refund           datetime comment '退款时间',
   seller_email         varchar(128) comment '卖家支付宝账号',
   buyer_email          varchar(128) comment '买家支付宝账号',
   seller_id            varchar(32) comment '卖家partnerId',
   buyer_id             varchar(32) comment '买家partnerId',
   price                int comment '商品单价(分)',
   total_fee            int comment '交易金额(分)',
   quantity             int comment '购买数量',
   body                 varchar(512) comment '商品描述',
   discount             double(2,2) comment '折扣',
   is_total_fee_adjust  varchar(4) comment '是否调整总价',
   use_coupon           varchar(4) comment '是否使用红包',
   error_code           varchar(64) comment '错误代码',
   bank_seq_no          varchar(64) comment '网银流水',
   extra_common_param   varchar(128) comment '公用回传参数',
   out_channel_type     varchar(512) comment '支付渠道组合信息',
   out_channel_amount   varchar(512) comment '支付金额组合信息',
   out_channel_inst     varchar(512) comment '实际支付渠道',
   notify_value         varchar(4000) comment '异步通知参数',
   create_time          datetime comment 'create_time',
   create_user          varchar(32) comment 'create_user',
   primary key (ali_notify_id)
);

alter table pay_ali_notify comment 'ali异步通知';

/*==============================================================*/
/* Table: pay_ali_return                                        */
/*==============================================================*/
create table pay_ali_return
(
   ali_notify_id        varchar(32) not null comment '同步通知id',
   merge_payment_id     varchar(32) comment '合并支付id',
   out_trade_no         varchar(32) comment '商户订单号',
   is_success           varchar(32) comment '成功标识',
   sign_type            varchar(32) comment '签名方式',
   sign                 varchar(128) comment '签名',
   subject              varchar(256) comment '商品名',
   payment_type         varchar(16) comment '支付类型',
   exterface            varchar(32) comment '接口名',
   trade_no             varchar(64) comment '支付宝流水号',
   trade_status         varchar(32) comment '交易状态',
   notify_id            varchar(256) comment '通知校验ID',
   notify_time          datetime comment '通知时间',
   notify_type          varchar(32) comment '通知类型',
   seller_email         varchar(128) comment '卖家支付宝账号',
   buyer_email          varchar(128) comment '买家支付宝账号',
   seller_id            varchar(32) comment '卖家partentId',
   buyer_id             varchar(32) comment '买家partentId',
   total_fee            int comment '交易金额(分)',
   body                 varchar(512) comment '商品描述',
   bank_seq_no          varchar(64) comment '网银流水',
   extra_common_param   varchar(128) comment '公用回传参数',
   return_value         varchar(4000) comment '同步返回参数',
   create_time          datetime comment 'create_time',
   create_user          varchar(32) comment 'create_user',
   primary key (ali_notify_id)
);

alter table pay_ali_return comment 'ali同步通知';

/*==============================================================*/
/* Table: pay_mergePayment_log                                  */
/*==============================================================*/
create table pay_mergePayment_log
(
   log_id               varchar(32) not null comment '合并支付单状态日志',
   merge_payment_id     varchar(32) comment '合并支付id',
   payment_state_id     int comment '支付单状态id',
   payment_note         varchar(256) comment '备注',
   create_time          datetime comment 'create_time',
   create_user          varchar(32) comment 'create_user',
   primary key (log_id)
);

alter table pay_mergePayment_log comment '合并支付单状态日志';

/*==============================================================*/
/* Table: pay_merge_payment                                     */
/*==============================================================*/
create table pay_merge_payment
(
   merge_payment_id     varchar(32) not null comment '合并支付id',
   merchant_id          varchar(32) comment '平台商户id',
   gateway_id           varchar(32) comment '支付网关Id',
   payment_state_id     int comment '支付单状态id',
   total_fee            int comment '总金额(分)',
   return_url           varchar(1024) comment '支付返回url',
   notify_url           varchar(1024) comment '支付通知url',
   payment_source       varchar(1024) comment '支付单来源',
   bank_code            varchar(32) comment '银行简码',
   openid               varchar(64) comment 'openid(微信H5支付使用)',
   time_out             datetime comment '超时时间',
   trade_time           datetime comment '实际支付时间',
   third_trade_no       varchar(64) comment '第三方交易号(ali/wechat流水号)',
   buyer_name           varchar(128) comment '买家',
   order_close_time     datetime comment '订单关闭时间',
   trade_close_time     datetime comment '交易关闭时间',
   create_time          datetime comment 'create_time',
   create_user          varchar(32) comment 'create_user',
   update_time          datetime comment 'update_time',
   update_user          varchar(32) comment 'update_user',
   primary key (merge_payment_id)
);

alter table pay_merge_payment comment '合并支付单';

/*==============================================================*/
/* Table: pay_payment                                           */
/*==============================================================*/
create table pay_payment
(
   payment_id           varchar(32) not null comment '支付单id',
   merchant_id          varchar(32) comment '平台商户id',
   payment_state_id     int comment '支付单状态id',
   gateway_id           varchar(32) comment '支付网关Id',
   total_fee            int comment '总金额(分)',
   sp_name              varchar(256) comment '商品名称',
   sp_detail            varchar(512) comment '商品描述',
   time_out             datetime comment '超时时间',
   return_url           varchar(1024) comment '支付返回url',
   notify_url           varchar(1024) comment '支付通知url',
   extra_common_param   varchar(4000) comment '公用回传参数',
   from_ip              varchar(64) comment '来源ip',
   payment_source       varchar(1024) comment '支付单来源',
   bank_code            varchar(32) comment '银行简码',
   openid               varchar(64) comment 'openid(微信H5支付使用)',
   payment_note         varchar(256) comment '备注',
   trade_time           datetime comment '实际支付时间',
   third_trade_no       varchar(64) comment '第三方交易号(ali/wechat流水号)',
   buyer_name           varchar(128) comment '买家',
   trade_close_time     datetime comment '交易关闭时间',
   order_close_time     datetime comment '订单关闭时间',
   create_time          datetime comment 'create_time',
   create_user          varchar(32) comment 'create_user',
   update_time          datetime comment 'update_time',
   update_user          varchar(32) comment 'update_user',
   primary key (payment_id)
);

alter table pay_payment comment '支付单';

/*==============================================================*/
/* Table: pay_payment_gateway                                   */
/*==============================================================*/
create table pay_payment_gateway
(
   gateway_id           varchar(32) not null comment '支付网关Id',
   merchant_id          varchar(32) comment '平台商户id',
   merchant_name        varchar(128) comment '平台商户名',
   gateway_name         varchar(64) comment '支付网关名称',
   gateway_code         varchar(64) comment '支付网关编码',
   gateway_state        int comment '状态[0:可用, 1:不可用]',
   gateway_key          varchar(2048) comment '秘钥',
   gateway_account      varchar(256) comment '账号[ali:合作者身份id, wx:公众号id]]',
   gateway_user_name    varchar(256) comment '用户名[ali:收款方支付宝账号, wx:商户号id]]',
   return_url           varchar(1024) comment '支付返回url',
   notify_url           varchar(1024) comment '支付通知url',
   pay_api              varchar(1024) comment '支付订单api',
   query_api            varchar(1024) comment '查询订单api',
   close_api            varchar(1024) comment '关闭订单api',
   refund_api           varchar(1024) comment '退款订单api',
   verify_api           varchar(1024) comment '消息验证api',
   public_key           varchar(1024) comment '验签公钥',
   cert_path            varchar(256) comment '证书路径',
   sign_type            varchar(32) comment '加密方式',
   gateway_version      varchar(32) comment '接口版本',
   update_user          varchar(32) comment 'update_user',
   create_time          datetime comment 'create_time',
   create_user          varchar(32) comment 'create_user',
   update_time          datetime comment 'update_time',
   primary key (gateway_id)
);

alter table pay_payment_gateway comment '支付网关';

/*==============================================================*/
/* Table: pay_payment_log                                       */
/*==============================================================*/
create table pay_payment_log
(
   payment_log_id       varchar(32) not null comment '支付单状态日志id',
   payment_id           varchar(32) comment '支付单id',
   payment_state_id     int comment '支付单状态id',
   payment_note         varchar(256) comment '备注',
   create_time          datetime comment 'create_time',
   create_user          varchar(32) comment 'create_user',
   primary key (payment_log_id)
);

alter table pay_payment_log comment '支付单状态日志';

/*==============================================================*/
/* Table: pay_payment_order                                     */
/*==============================================================*/
create table pay_payment_order
(
   payment_order_id     varchar(32) not null comment '订单ref支付单id',
   payment_id           varchar(32) comment '支付单id',
   order_id             varchar(32) comment '订单id',
   create_time          datetime comment 'create_time',
   create_user          varchar(32) comment 'create_user',
   primary key (payment_order_id)
);

alter table pay_payment_order comment '订单ref支付单';

/*==============================================================*/
/* Table: pay_payment_reqLog                                    */
/*==============================================================*/
create table pay_payment_reqLog
(
   log_id               varchar(32) not null comment '支付请求记录ID',
   order_ids            varchar(1024) comment '订单ids(使用,隔开)',
   pay_req              varchar(4000) comment '支付请求',
   pay_res              varchar(1024) comment '支付请求返回',
   log_note             varchar(1024) comment '备注',
   create_time          datetime comment 'create_time',
   create_user          varchar(32) comment 'create_user',
   primary key (log_id)
);

alter table pay_payment_reqLog comment '支付请求返回';

/*==============================================================*/
/* Table: pay_payment_state                                     */
/*==============================================================*/
create table pay_payment_state
(
   payment_state_id     int not null comment '支付单状态id',
   state_name           varchar(128) comment '状态名',
   state_noet           varchar(512) comment '状态备注',
   create_time          datetime comment 'create_time',
   create_user          varchar(32) comment 'create_user',
   primary key (payment_state_id)
);

alter table pay_payment_state comment '支付单状态枚举';

/*==============================================================*/
/* Table: pay_ref_merge_payment                                 */
/*==============================================================*/
create table pay_ref_merge_payment
(
   ref_id               varchar(32) not null comment 'ref_id',
   merge_payment_id     varchar(32) comment '合并支付id',
   payment_id           varchar(32) comment '支付单id',
   create_time          datetime comment 'create_time',
   create_user          varchar(32) comment 'create_user',
   primary key (ref_id)
);

alter table pay_ref_merge_payment comment 'ref-支付单-合并支付单';

/*==============================================================*/
/* Table: pay_repeat_payment                                    */
/*==============================================================*/
create table pay_repeat_payment
(
   reapeat_id           varchar(32) not null comment '重复支付id',
   merchant_id          varchar(32) comment '平台商户id',
   payment_id           varchar(32) comment '支付单id',
   gateway_id           varchar(32) comment '支付网关Id',
   merge_payment_id     varchar(32) comment '合并支付id',
   order_id             varchar(32) comment '订单id',
   total_fee            int comment '总金额(分)',
   third_trade_no       varchar(64) comment '第三方交易号(ali/wechat流水号)',
   trade_time           datetime comment '实际支付时间',
   buyer_name           varchar(128) comment '买家',
   create_time          datetime comment 'create_time',
   create_user          varchar(32) comment 'create_user',
   primary key (reapeat_id)
);

alter table pay_repeat_payment comment '重复支付单记录';

/*==============================================================*/
/* Table: pay_wx_coupon                                         */
/*==============================================================*/
create table pay_wx_coupon
(
   yh_id                varchar(32) not null comment '微信支付优惠id',
   wx_notify_id         varchar(32) comment '异步通知id',
   coupon_id            varchar(20) comment '代金券或立减优惠ID',
   coupon_fee           int comment '单个代金券或立减优惠支付金额(分)',
   primary key (yh_id)
);

alter table pay_wx_coupon comment '微信支付优惠券';

/*==============================================================*/
/* Table: pay_wx_notify                                         */
/*==============================================================*/
create table pay_wx_notify
(
   wx_notify_id         varchar(32) not null comment '异步通知id',
   merge_payment_id     varchar(32) comment '合并支付id',
   return_code          varchar(16) comment '返回状态码',
   return_msg           varchar(128) comment '返回信息',
   appid                varchar(32) comment '公众账号id',
   mch_id               varchar(32) comment '商户号',
   device_info          varchar(32) comment '设备号',
   nonce_str            varchar(32) comment '随机字符串',
   sign                 varchar(32) comment '签名',
   result_code          varchar(16) comment '业务结果',
   err_code             varchar(32) comment '错误代码',
   err_code_des         varchar(128) comment '错误代码描述',
   openid               varchar(128) comment '用户标识',
   is_subscribe         varchar(1) comment '是否关注公众账号',
   trade_type           varchar(16) comment '交易类型',
   bank_type            varchar(16) comment '付款银行',
   total_fee            int comment '总金额(分)',
   fee_type             varchar(8) comment '货币种类',
   cash_fee             int comment '现金支付金额(分)',
   cash_fee_type        varchar(16) comment '现金支付货币类型',
   coupon_fee           int comment '代金券立减优惠金额(分)',
   coupon_count         int comment '代金券或立减优惠使用数量',
   transaction_id       varchar(32) comment '微信支付订单号',
   out_trade_no         varchar(32) comment '商户订单号',
   attach               varchar(128) comment '商家数据包',
   time_end             varchar(14) comment '支付完成时间',
   notify_value         varchar(4000) comment '异步通知参数',
   create_time          datetime comment 'create_time',
   create_user          varchar(32) comment 'create_user',
   primary key (wx_notify_id)
);

alter table pay_wx_notify comment '微信异步通知';

-- 初始化网关
INSERT INTO `pay_payment_gateway` VALUES ('5403e3bdfb9411e58f80185e0f846dee', 'zr001', '管理员', 'wx H5页面支付', 'WXJSAPI', '0', 'CxukqT0OWlXXXrdKFkwfIKXnlFZ11qzC', 'wxe21ad38684c7166d', '1322370501', null, null, 'https://api.mch.weixin.qq.com/pay/unifiedorder', 'https://api.mch.weixin.qq.com/pay/orderquery', 'https://api.mch.weixin.qq.com/pay/closeorder', 'https://api.mch.weixin.qq.com/secapi/pay/refund', null, null, null, 'MD5', '1.0', null, null, null, null);
INSERT INTO `pay_payment_gateway` VALUES ('5529d97ff90411e5956df8cab8256d59', 'zr001', '管理员', 'ali合并支付', 'ALIMERGEPAY', '0', 'azn0ihswk1elxa7ggt63vj37efjhlzld', '2088211554961671', 'zhang9192@vip.qq.com', null, null, 'https://mapi.alipay.com/gateway.do?', 'https://mapi.alipay.com/gateway.do?', 'https://mapi.alipay.com/gateway.do?', null, 'https://mapi.alipay.com/gateway.do?service=notify_verify&', null, null, 'MD5', '1.0', null, null, null, null);
INSERT INTO `pay_payment_gateway` VALUES ('86f13154fb9411e58f80185e0f846dee', 'zr001', '管理员', 'wx APP支付', 'WXAPP', '0', 'CxukqT0OWlXXXrdKFkwfIKXnlFZ11qzC', 'wxe21ad38684c7166d', '1322370501', null, null, 'https://api.mch.weixin.qq.com/pay/unifiedorder', 'https://api.mch.weixin.qq.com/pay/orderquery', 'https://api.mch.weixin.qq.com/pay/closeorder', 'https://api.mch.weixin.qq.com/secapi/pay/refund', null, null, null, 'MD5', '1.0', null, null, null, null);
INSERT INTO `pay_payment_gateway` VALUES ('88dec97ef90311e5956df8cab8256d59', 'zr001', '管理员', 'ali网关/即时到账支付', 'ALIDIRECTPAY', '0', 'azn0ihswk1elxa7ggt63vj37efjhlzld', '2088211554961671', 'zhang9192@vip.qq.com', null, null, 'https://mapi.alipay.com/gateway.do?', 'https://mapi.alipay.com/gateway.do?', 'https://mapi.alipay.com/gateway.do?', null, 'https://mapi.alipay.com/gateway.do?service=notify_verify&', null, null, 'MD5', '1.0', null, null, null, null);
INSERT INTO `pay_payment_gateway` VALUES ('9d7b4a9cf90411e5956df8cab8256d59', 'zr001', '管理员', 'ali移动APP支付', 'ALIMOBILEPAY', '0', 'MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALk8SzovjbXyr0EclfWEg2ToCUKqHaPoiiI5umu0DuxbkMzLdRTQN380YYAqb4s88N91Ik7lyCt/fCdgbhZlBlzSVKj/dbxgJROXTqrnLzLyV3hNPLtW6PQurMT1qhw3RZPwADEoUcuAmW0qPi4STUwEklAumOk36iX156pmTxXNAgMBAAECgYBDM3yXP2lJ+SFbneNJjymcEhJJ/S2DqXlzsT3QlSJU3WNK6QUw59k6bOZyePK+sP0yFGy98t8L6NRB9Nn7hesoY4ygLDhkCbWjQ12yJZ3bUPcWSsIhGcMC89TRjHzqpn85E/75iw2ka7NhAusok42Y9UXIMSGGYrYvOjK2W4xkAQJBAOFrQmRYQiwLztLj5TUoDntq3RJDItaMjnaEGMchADrFeFZKQlagEk1kPtN7H3u4DqFSSP13erl8FLCwLQsUpRkCQQDSXXlJ4bH/c2abv/0h1YRvJWD7UKOSO7NXbd54zWzrKqoJbh7ysY6MMJCjz1ZEr6KnfAHG56OCQcWjQf4QbnjVAkAezSQuR+0KzbY+FMvN/qIz9P8uLqRFAsG4qgYkt3qrjS4LRGxaH9dAYfE/vnQn2JePLYoxsDDBxZWiKwCMog6JAkBIxIjGI8o8wSCHU9wuLfvHrrhjdrt+RCR/Y8QHKZg5qOJ0rKEL9puGpu4BnCENVWwAX4cF1O7cif/hv1wxNuWZAkEAnFnUlX7xtQo6Yuk9QJohJ3pWawusrbY+TljQpbYYa9v9A2jrjI4wZXYqzHSGa2lTlJgyqgNnJfwq+aWXX3F9sw==', '2088211554961671', 'zhang9192@vip.qq.com', null, null, null, 'https://mapi.alipay.com/gateway.do?', 'https://mapi.alipay.com/gateway.do?', null, 'https://mapi.alipay.com/gateway.do?service=notify_verify&', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB', null, 'RSA', '1.0', null, null, null, null);
INSERT INTO `pay_payment_gateway` VALUES ('c1375ff9fb9311e58f80185e0f846dee', 'zr001', '管理员', 'wx原生(扫码)支付(pc)', 'WXNATIVE', '0', 'CxukqT0OWlXXXrdKFkwfIKXnlFZ11qzC', 'wxe21ad38684c7166d', '1322370501', null, null, 'https://api.mch.weixin.qq.com/pay/unifiedorder', 'https://api.mch.weixin.qq.com/pay/orderquery', 'https://api.mch.weixin.qq.com/pay/closeorder', 'https://api.mch.weixin.qq.com/secapi/pay/refund', null, null, null, 'MD5', '1.0', null, null, null, null);
