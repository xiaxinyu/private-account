-- consume_rule seed (fixed syntax + enhanced keywords)
-- Schema aligned with table definition
BEGIN;

-- Fixed: REIMB-02 住宿报销
INSERT INTO `consume_rule`
(`id`,`categoryId`,`pattern`,`patternType`,`priority`,`active`,`bankCode`,`cardTypeCode`,`remark`,`version`,`createUser`,`createTime`,`updateUser`,`updateTime`)
VALUES('gen_20251211_reimb_travel_stay','REIMB-02','住宿报销','contains',90,1,NULL,NULL,'差旅费',0,'system',NOW(),'system',NOW());

-- LIVING-01 餐饮（新增常见品牌）
INSERT INTO `consume_rule` VALUES('gen_20251211_food_haidilao','LIVING-01','海底捞','contains',90,1,NULL,NULL,'餐饮',0,'system',NOW(),'system',NOW());
INSERT INTO `consume_rule` VALUES('gen_20251211_food_naixue','LIVING-01','奈雪','contains',90,1,NULL,NULL,'茶饮咖啡',0,'system',NOW(),'system',NOW());
INSERT INTO `consume_rule` VALUES('gen_20251211_food_xicha','LIVING-01','喜茶','contains',90,1,NULL,NULL,'茶饮咖啡',0,'system',NOW(),'system',NOW());

-- LIVING-02 超市/便利店（补充）
INSERT INTO `consume_rule` VALUES('gen_20251211_super_market_rt','LIVING-02','华润万家','contains',90,1,NULL,NULL,'超市',0,'system',NOW(),'system',NOW());
INSERT INTO `consume_rule` VALUES('gen_20251211_convenience_family','LIVING-02','全家便利店','contains',90,1,NULL,NULL,'便利店',0,'system',NOW(),'system',NOW());

-- LIVING-03 网购（补充主流平台）
INSERT INTO `consume_rule` VALUES('gen_20251211_ecommerce_tm','LIVING-03','天猫','contains',85,1,NULL,NULL,'网购',0,'system',NOW(),'system',NOW());
INSERT INTO `consume_rule` VALUES('gen_20251211_ecommerce_douyin','LIVING-03','抖音电商','contains',85,1,NULL,NULL,'网购',0,'system',NOW(),'system',NOW());

-- LIVING-06 基础医疗（补充）
INSERT INTO `consume_rule` VALUES('gen_20251211_medical_pharmacy','LIVING-06','连锁药房','contains',90,1,NULL,NULL,'药店',0,'system',NOW(),'system',NOW());
INSERT INTO `consume_rule` VALUES('gen_20251211_medical_checkup','LIVING-06','体检','contains',90,1,NULL,NULL,'体检中心',0,'system',NOW(),'system',NOW());

-- LIVING-07 快递（补充）
INSERT INTO `consume_rule` VALUES('gen_20251211_express_sf','LIVING-07','顺丰速运','contains',90,1,NULL,NULL,'快递',0,'system',NOW(),'system',NOW());
INSERT INTO `consume_rule` VALUES('gen_20251211_express_cainiao','LIVING-07','菜鸟驿站','contains',90,1,NULL,NULL,'快递',0,'system',NOW(),'system',NOW());

-- FIXED-03 水电燃气（regex 改写）
INSERT INTO `consume_rule` VALUES('gen_20251211_fixed_water','FIXED-03','水费|自来水','regex',95,1,NULL,NULL,'水费',0,'system',NOW(),'system',NOW());
INSERT INTO `consume_rule` VALUES('gen_20251211_fixed_electric','FIXED-03','电费|电力','regex',95,1,NULL,NULL,'电费',0,'system',NOW(),'system',NOW());
INSERT INTO `consume_rule` VALUES('gen_20251211_fixed_gas','FIXED-03','燃气费|煤气','regex',95,1,NULL,NULL,'燃气',0,'system',NOW(),'system',NOW());

-- FIXED-04 宽带/网络
INSERT INTO `consume_rule` VALUES('gen_20251211_broadband_ct','FIXED-04','中国电信','contains',95,1,NULL,NULL,'宽带/网络',0,'system',NOW(),'system',NOW());
INSERT INTO `consume_rule` VALUES('gen_20251211_broadband_cu','FIXED-04','中国联通','contains',95,1,NULL,NULL,'宽带/网络',0,'system',NOW(),'system',NOW());
INSERT INTO `consume_rule` VALUES('gen_20251211_broadband_cm','FIXED-04','中国移动','contains',95,1,NULL,NULL,'宽带/网络',0,'system',NOW(),'system',NOW());

-- FIXED-05 话费/套餐
INSERT INTO `consume_rule` VALUES('gen_20251211_phone_bill','FIXED-05','话费','contains',95,1,NULL,NULL,'通信套餐',0,'system',NOW(),'system',NOW());
INSERT INTO `consume_rule` VALUES('gen_20251211_phone_package','FIXED-05','套餐费','contains',95,1,NULL,NULL,'通信套餐',0,'system',NOW(),'system',NOW());

-- FIN-01 银行手续费
INSERT INTO `consume_rule` VALUES('gen_20251211_bank_fee_transfer','FIN-01','跨行手续费','contains',90,1,NULL,NULL,'银行手续费',0,'system',NOW(),'system',NOW());
INSERT INTO `consume_rule` VALUES('gen_20251211_bank_fee_sms','FIN-01','短信服务费','contains',90,1,NULL,NULL,'银行手续费',0,'system',NOW(),'system',NOW());

-- FIN-04 信用卡费用
INSERT INTO `consume_rule` VALUES('gen_20251211_credit_annual','FIN-04','信用卡年费','contains',90,1,NULL,NULL,'信用卡费用',0,'system',NOW(),'system',NOW());
INSERT INTO `consume_rule` VALUES('gen_20251211_credit_interest','FIN-04','利息','contains',90,1,NULL,NULL,'信用卡费用',0,'system',NOW(),'system',NOW());

-- TRANSPORT-07 铁路/航空
INSERT INTO `consume_rule` VALUES('gen_20251211_train_12306','TRANSPORT-07','12306','contains',90,1,NULL,NULL,'火车高铁',0,'system',NOW(),'system',NOW());
INSERT INTO `consume_rule` VALUES('gen_20251211_air_ticket','TRANSPORT-07','机票','contains',90,1,NULL,NULL,'航空出行',0,'system',NOW(),'system',NOW());

-- REIMB-03 商品/服务退款
INSERT INTO `consume_rule` VALUES('gen_20251211_refund_alipay','REIMB-03','支付宝退款','contains',90,1,NULL,NULL,'支付平台退款',0,'system',NOW(),'system',NOW());
INSERT INTO `consume_rule` VALUES('gen_20251211_refund_wechat','REIMB-03','微信支付退款','contains',90,1,NULL,NULL,'支付平台退款',0,'system',NOW(),'system',NOW());

COMMIT;
