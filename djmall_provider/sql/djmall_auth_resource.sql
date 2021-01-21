/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : djmall

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2021-01-21 20:15:46
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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='权限管理：资源表';

-- ----------------------------
-- Records of djmall_auth_resource
-- ----------------------------
INSERT INTO `djmall_auth_resource` VALUES ('1', '运营平台', '--', '0', '0', 'EEOP', '1');
INSERT INTO `djmall_auth_resource` VALUES ('2', '用户管理', '/user/toShow', '1', '0', 'USER_MANAGER', '1');
INSERT INTO `djmall_auth_resource` VALUES ('3', '基础管理', '--', '1', '0', 'BASIC_MANAGER', '1');
INSERT INTO `djmall_auth_resource` VALUES ('4', '订单管理', '--', '1', '0', 'ORDER_MANAGER', '1');
INSERT INTO `djmall_auth_resource` VALUES ('5', '商品管理', '--', '1', '0', 'PRODUCT_MANAGER', '1');
INSERT INTO `djmall_auth_resource` VALUES ('6', '权限管理', '--', '1', '0', 'PERMISSION_MANAGER', '1');
INSERT INTO `djmall_auth_resource` VALUES ('7', '资源管理  ', '/res/toShowResZtree', '6', '0', 'RESOURCE_MANAGER', '1');
INSERT INTO `djmall_auth_resource` VALUES ('8', '角色管理', '/auth/role/toShow', '6', '0', 'ROLE_MANAGER', '1');
INSERT INTO `djmall_auth_resource` VALUES ('9', '用户修改按钮', '--', '2', '0', 'USER_UPDATE_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('10', '用户激活按钮', '--', '2', '0', 'USER_ACTIVATE_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('11', '用户重置密码按钮', '--', '2', '0', 'USER_RESET_PASSWORDS_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('12', '用户删除按钮', '--', '2', '0', 'USER_DEL_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('13', '用户关联角色按钮', '--', '2', '0', 'USER_ADD_ROLE_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('14', '角色新增按钮', '--', '8', '0', 'ROLE_ADD_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('15', '角色编辑按钮', '--', '8', '0', 'ROLE_UPDATE_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('16', '角色删除按钮', '--', '8', '0', 'ROLE_DEL_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('17', '角色关联资源按钮', '--', '8', '0', 'ROLE_RELEVANCE_RESOURCE_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('18', '资源新增按钮', '--', '7', '0', 'RESOURCE_ADD_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('19', '资源修改按钮', '--', '7', '0', 'RESOURCE_UPDATE_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('20', '资源删除按钮', '--', '7', '0', 'RESOURCE_DEL_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('21', '运费', '--', '3', '0', 'FREIGHT_MANAGER', '1');
INSERT INTO `djmall_auth_resource` VALUES ('22', '字典数据', '--', '3', '0', 'DICTIONARIES_DATA_MANAGER', '1');
INSERT INTO `djmall_auth_resource` VALUES ('23', '商品属性维护', '--', '3', '0', 'PRODUCT_ATTRIBUTE_MAINTENANCE_MANAGER', '1');
INSERT INTO `djmall_auth_resource` VALUES ('24', '通用SKU维护', '--', '3', '0', 'BE_IN_COMMON_USE_SKU_MAINTENANCE_MANAGER', '1');
INSERT INTO `djmall_auth_resource` VALUES ('25', '运费新增按钮', '--', '21', '0', 'FREIGHT_ADD_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('26', '运费修改按钮', '--', '21', '0', 'FREIGHT_UPDATE_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('27', '字典新增按钮', '--', '22', '0', 'DICTIONARIES_ADD_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('28', '字典修改按钮', '--', '22', '0', 'DICTIONARIES_UPDATE_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('29', '商品属性新增按钮', '--', '23', '0', 'PRODUCT_ATTRIBUTE_ADD_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('30', '商品关联属性值按钮', '--', '23', '0', 'PRODUCT_RELEVANCE_ATTRIBUTE_VALUE_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('31', '关联属性值新增按钮', '--', '30', '0', 'RELEVANCE_ATTRIBUTE_VALUE_ADD_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('32', '关联属性值移除按钮', '--', '30', '0', 'RELEVANCE_ATTRIBUTE_VALUE_REMOVE_BTN', '2');
INSERT INTO `djmall_auth_resource` VALUES ('33', '通用SKU关联属性按钮', '--', '24', '0', 'BE_IN_COMMON_USE_SKU_RELEVANCE_ATTRIBUTE_BTN', '2');
