/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : djmall

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2021-01-14 22:54:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for djmall_auth_resource
-- ----------------------------
DROP TABLE IF EXISTS `djmall_auth_resource`;
CREATE TABLE `djmall_auth_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源ID',
  `resource_name` varchar(255) NOT NULL COMMENT '资源名',
  `url` varchar(255) DEFAULT NULL COMMENT '资源路径',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级ID',
  `is_del` int(11) DEFAULT NULL COMMENT '是否删除 0正常 1删除',
  `resource_code` varchar(255) DEFAULT NULL,
  `resource_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='权限管理：资源表';

-- ----------------------------
-- Records of djmall_auth_resource
-- ----------------------------
INSERT INTO `djmall_auth_resource` VALUES ('1', '运营平台', null, '0', '0', null, null);
INSERT INTO `djmall_auth_resource` VALUES ('2', '用户管理', null, '1', '0', null, null);
INSERT INTO `djmall_auth_resource` VALUES ('3', '基础管理', null, '1', '0', null, null);
INSERT INTO `djmall_auth_resource` VALUES ('4', '订单管理', null, '1', '0', null, null);
INSERT INTO `djmall_auth_resource` VALUES ('5', '商品管理', null, '1', '0', null, null);
INSERT INTO `djmall_auth_resource` VALUES ('6', '权限管理', null, '1', '0', null, null);
INSERT INTO `djmall_auth_resource` VALUES ('7', '资源管理  ', null, '6', '0', null, null);
INSERT INTO `djmall_auth_resource` VALUES ('8', '角色资源', null, '6', '0', null, null);
INSERT INTO `djmall_auth_resource` VALUES ('9', 'aaaa', null, '1', '0', null, null);
