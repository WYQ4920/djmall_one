/*
Navicat MySQL Data Transfer

Source Server         : djmall_2001
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : djmall

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2021-01-14 12:30:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for djmall_auth_data
-- ----------------------------
DROP TABLE IF EXISTS `djmall_auth_data`;
CREATE TABLE `djmall_auth_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '基础数据表id',
  `parent_id` int(255) DEFAULT NULL COMMENT '基础数据表父id',
  `base_name` varchar(255) DEFAULT NULL COMMENT '基础数据name',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='dimall:data表';

-- ----------------------------
-- Records of djmall_auth_data
-- ----------------------------
INSERT INTO `djmall_auth_data` VALUES ('1', '0', '运营平台');
INSERT INTO `djmall_auth_data` VALUES ('2', '1', '用户管理');
INSERT INTO `djmall_auth_data` VALUES ('3', '1', '基础管理');
INSERT INTO `djmall_auth_data` VALUES ('4', '1', '订单管理');
INSERT INTO `djmall_auth_data` VALUES ('5', '1', '商品管理');
INSERT INTO `djmall_auth_data` VALUES ('6', '1', '权限管理');
INSERT INTO `djmall_auth_data` VALUES ('7', '6', '资源管理');
INSERT INTO `djmall_auth_data` VALUES ('8', '6', '角色管理');
