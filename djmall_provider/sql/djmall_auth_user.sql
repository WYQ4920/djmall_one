/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : djmall

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2021-01-19 10:07:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for djmall_auth_user
-- ----------------------------
DROP TABLE IF EXISTS `djmall_auth_user`;
CREATE TABLE `djmall_auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `user_pwd` varchar(255) NOT NULL COMMENT '用户密码',
  `nick_name` varchar(255) NOT NULL COMMENT '用户昵称',
  `salt` varchar(255) NOT NULL COMMENT '密码盐',
  `user_phone` varchar(255) NOT NULL COMMENT '用户手机号',
  `user_email` varchar(255) NOT NULL COMMENT '电子邮箱',
  `user_sex` int(11) NOT NULL COMMENT '用户性别：1为男，2为女',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='权限管理：用户表';

-- ----------------------------
-- Records of djmall_auth_user
-- ----------------------------
INSERT INTO `djmall_auth_user` VALUES ('1', 'zs', '123456', '', '', '', '', '1', '1');
INSERT INTO `djmall_auth_user` VALUES ('2', 'aa', '123456', '', '', '', '', '2', '2');
INSERT INTO `djmall_auth_user` VALUES ('3', 'ss', '123456', '', '', '', '', '1', '1');
INSERT INTO `djmall_auth_user` VALUES ('4', 'ab', '123456', '', '', '', '', '2', '1');
INSERT INTO `djmall_auth_user` VALUES ('5', 'aaa', 'f0c69fc5845531fe3d42c98029480285', '', 'c8c47d3eff969778b420b105c931e8d9', '15211111111', '1@163.com', '1', '1');
INSERT INTO `djmall_auth_user` VALUES ('6', 'aa1', '36693c5479c7069d532baa1471b407c4', '', '60022e38e2b2e307b6a88fa330666c1c', '15211111112', '2@163.com', '1', '2');
