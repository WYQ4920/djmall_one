/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : djmall

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2021-01-27 21:05:05
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
  `user_sex` varchar(255) NOT NULL COMMENT '用户性别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='权限管理：用户表';

-- ----------------------------
-- Records of djmall_auth_user
-- ----------------------------
INSERT INTO `djmall_auth_user` VALUES ('1', 'admin', 'd0da592ee282f861da0e90f7fe0aa885', 'aa', 'a20fd84c6b2b9bf13b8e917e5e9648d9', '15211111111', '1@163.com', 'MAN');
INSERT INTO `djmall_auth_user` VALUES ('2', 'yonghu', '9030f49e7b3a92f2d6fbab3bf428a9e6', 'ss', '2604a65335ca5daeebbe116d60390883', '15211111112', '2@163.com', 'WOMAN');
INSERT INTO `djmall_auth_user` VALUES ('3', 'sh01', '37f7bf02910d7ca27b9c08befa6acac2', 'bb', '5abd6b762fa69107f78527158562305d', '15211111113', '3@163.com', 'MAN');
INSERT INTO `djmall_auth_user` VALUES ('4', 'yh01', '5640abe011632d1807bfa55526b2c9e7', 'mmm', '9b9f345985b5d24ffe29fe11e71cf86a', '15211111114', '4@163.com', 'MAN');
