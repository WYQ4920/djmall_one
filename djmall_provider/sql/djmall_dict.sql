/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : djmall

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2021-01-21 10:13:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for djmall_dict
-- ----------------------------
DROP TABLE IF EXISTS `djmall_dict`;
CREATE TABLE `djmall_dict` (
  `code` varchar(255) DEFAULT NULL,
  `p_code` varchar(255) DEFAULT NULL,
  `dict_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of djmall_dict
-- ----------------------------
INSERT INTO `djmall_dict` VALUES ('PAY_TYPE', 'SYSTEM', '支付类型');
INSERT INTO `djmall_dict` VALUES ('USER_SEX', 'SYSTEM', '用户性别');
INSERT INTO `djmall_dict` VALUES ('USER_STATUS', 'SYSTEM', '用户状态');
INSERT INTO `djmall_dict` VALUES ('ORDER_STAUS', 'SYSTEM', '订单状态');
INSERT INTO `djmall_dict` VALUES ('PRODUCT_STATUS', 'SYSTEM', '商品状态');
INSERT INTO `djmall_dict` VALUES ('PRODUCT_TYPE', 'SYSTEM', '商品类型');
INSERT INTO `djmall_dict` VALUES ('EXPPESS_COMPANY', 'SYSTEM', '物流公司');
INSERT INTO `djmall_dict` VALUES ('PAY_ON_DELIVERY', 'SYSYTEM', '货到付款');
INSERT INTO `djmall_dict` VALUES ('SF', 'EXPPESS_COMPANY', '顺丰');
INSERT INTO `djmall_dict` VALUES ('YD', 'EXPPESS_COMPANY', '韵达');
INSERT INTO `djmall_dict` VALUES ('YT', 'EXPPESS_COMPANY', '圆通');
INSERT INTO `djmall_dict` VALUES ('CANCEL', 'ORDER_STAUS', '已取消');
INSERT INTO `djmall_dict` VALUES ('COMPLETE', 'ORDER_STAUS', '已完成');
INSERT INTO `djmall_dict` VALUES ('PAY_WAIT', 'ORDER_STAUS', '待支付');
INSERT INTO `djmall_dict` VALUES ('SEND_ING', 'ORDER_STAUS', '已发货');
INSERT INTO `djmall_dict` VALUES ('SEND_WAIT', 'ORDER_STAUS', '待发货');
INSERT INTO `djmall_dict` VALUES ('SEND_CONFIRM', 'ORDER_STAUS', '确认收货');
INSERT INTO `djmall_dict` VALUES ('ALIPAY', 'PAY_TYPE', '支付宝');
INSERT INTO `djmall_dict` VALUES ('WECHAT', 'PAY_TYPE', '微信');
INSERT INTO `djmall_dict` VALUES ('PRODUCT_UP', 'PRODUCT_STATUS', '上架');
INSERT INTO `djmall_dict` VALUES ('PRODUCT_DOWN', 'PRODUCT_STATUS', '下架');
INSERT INTO `djmall_dict` VALUES ('FOOD', 'PRODUCT_TYPE', '食品');
INSERT INTO `djmall_dict` VALUES ('PHONE', 'PRODUCT_TYPE', '手机');
INSERT INTO `djmall_dict` VALUES ('SHOES', 'PRODUCT_TYPE', '鞋');
INSERT INTO `djmall_dict` VALUES ('CLOSTHES', 'PRODUCT_TYPE', '衣服');
INSERT INTO `djmall_dict` VALUES ('COMPUTER', 'PRODUCT_TYPE', '电脑');
