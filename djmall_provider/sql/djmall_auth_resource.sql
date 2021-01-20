/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : djmall

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2021-01-21 00:50:32
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='权限管理：资源表';

-- ----------------------------
-- Records of djmall_auth_resource
-- ----------------------------
INSERT INTO `djmall_auth_resource` VALUES ('1', '运营平台', '--', '0', '0', '--', '1');
INSERT INTO `djmall_auth_resource` VALUES ('2', '用户管理', '/user/toShow', '1', '0', 'USER_MANAGER', '1');
INSERT INTO `djmall_auth_resource` VALUES ('3', '基础管理', '--', '1', '0', '--', '1');
INSERT INTO `djmall_auth_resource` VALUES ('4', '订单管理', '--', '1', '0', '--', '1');
INSERT INTO `djmall_auth_resource` VALUES ('5', '商品管理', '--', '1', '0', '--', '1');
INSERT INTO `djmall_auth_resource` VALUES ('6', '权限管理', '--', '1', '0', 'PERMISSION_MANAGER', '1');
INSERT INTO `djmall_auth_resource` VALUES ('7', '资源管理  ', '/res/toShowResZtree', '6', '0', 'RESOURCE_MAMAGER', '1');
INSERT INTO `djmall_auth_resource` VALUES ('8', '角色管理', '/auth/role/toShow', '6', '0', 'ROLE_MANAGER', '1');
INSERT INTO `djmall_auth_resource` VALUES ('22', '资源新增按钮', '--', '7', '0', 'RESOURCE_ADD_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('23', '资源修改按钮', '--', '7', '0', 'RESOURCE_UPDATE_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('24', '资源删除按钮', '--', '7', '0', 'RESOURCE_DEL_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('25', '角色新增按钮', '--', '8', '0', 'ROLE_ADD_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('26', '角色修改按钮', '--', '8', '0', 'ROLE_UPDATE_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('27', '角色删除按钮', '--', '8', '0', 'ROLE_DEL_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('28', '角色关联资源按钮', '--', '8', '0', 'ROLE_ADD_RESOURCE_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('29', '用户删除按钮', '--', '2', '0', 'USER_DEL_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('30', '用户修改按钮', '--', '2', '0', 'USER_UPDATE_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('31', '用户关联角色按钮', '--', '2', '0', 'USER_ADD_ROLE_BTN', '2');
