/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : localhost:3306
 Source Schema         : partner

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 18/04/2023 17:01:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dynamic
-- ----------------------------
DROP TABLE IF EXISTS `dynamic`;
CREATE TABLE `dynamic`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '内容',
  `imgs` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '简介',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `uid` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户标识',
  `deleted` int(255) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '动态' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dynamic
-- ----------------------------
INSERT INTO `dynamic` VALUES (1, '测试', '测试内容', NULL, '测试', '2022-11-29 21:37:24', '2022-11-29 21:37:26', 'b80333662477430eab52d4c18acab2ad', NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '邮箱',
  `uid` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户唯一id',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除 0存在  id删除',
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uid_index`(`uid`) USING BTREE,
  UNIQUE INDEX `username_index`(`username`, `deleted`) USING BTREE,
  UNIQUE INDEX `email_index`(`email`, `deleted`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'bgg', '$2a$10$KYJgiVBuZIWmZni93GBmC.g2fMTVhja8tzZ1LtToGOf44q1E2Z8Cq', 'Me', '河北保定', '1157712386@qq.com', 'efb74e2cdc5b4ae680959d25b7cd101c', 0, '2023-03-01 16:06:50', '2023-03-01 16:06:50', 'loginId=5a4f2a4963c148d1a27cb8bdb712792c&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpblR5cGUiOiJsb2dpbiIsImxvZ2luSWQiOiI1YTRmMmE0OTYzYzE0OGQxYTI3Y2I4YmRiNzEyNzkyYyIsInJuU3RyIjoiNDJtdGFyUjE0TkVnUjhqTXFrYmdoNHFXZkJEWjgwc24ifQ.Hr6CIGaeG42nQmZZp8DQlDJEqyxK_ICleM7c19xYhR4');
INSERT INTO `sys_user` VALUES (2, 'admin', '$2a$10$N6Yd/ifpjDgvw8yJ3x2hOOM/qIU6RoM2kmSlzRKfdS2fY5gQgz.E2', '管理员', '河北保定', '1516545@qq.com', '5a4f2a4963c148d1a27cb8bdb712792c', 0, '2023-04-09 10:57:12', '2023-04-09 10:57:12', 'http://localhost:9090/file/download/11b69004e05948dcbd1f00e929e2698e.jpg?loginId=5a4f2a4963c148d1a27cb8bdb712792c&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpblR5cGUiOiJsb2dpbiIsImxvZ2luSWQiOiI1YTRmMmE0OTYzYzE0OGQxYTI3Y2I4YmRiNzEyNzkyYyIsInJuU3RyIjoiNDJtdGFyUjE0TkVnUjhqTXFrYmdoNHFXZkJEWjgwc24ifQ.Hr6CIGaeG42nQmZZp8DQlDJEqyxK_ICleM7c19xYhR4');
INSERT INTO `sys_user` VALUES (47, 'bwwwgg', '$2a$10$QQ2DkXQDxWtbvtyV2G9fiufMppqs8AyWm5bDjd7F9ROUXLIpeqCeu', '系统用户od1cgg', '河北保定', '11577386@qq.com', 'f01a9f5518b94aa6ba6923893be08ba9', 0, '2023-04-18 12:26:31', '2023-04-18 12:26:31', '');
INSERT INTO `sys_user` VALUES (48, 'qqqqqqqq', '$2a$10$vWA4k9wDZe8Xwpii2UF0Oe95WIyuQmwgggF6E0Mnsc5TBVaVU.3de', '系统用户23u3al', '河北保定', '15112316545@qq.com', '2ad8ecc27d87420a8ff33f4bf7c168c4', 48, '2023-04-18 12:26:31', '2023-04-18 12:26:31', '');
INSERT INTO `sys_user` VALUES (49, 'agasg', '$2a$10$vxcyJMn1xuvaeWsddr.yf.9D41ZD7Lvdv/zhVAls21i.egcIZ1Zz2', '系统用户hrslea', '河北保定', '13123123@qq.com', '14c09e7a307f4620a721776256681049', 49, '2023-04-18 12:27:06', '2023-04-18 12:27:06', '');
INSERT INTO `sys_user` VALUES (50, 'agaag', '$2a$10$5yJPrEAZK3i3Nly1.s0wj.UZC/XtnsjWl4w9OPvYrEIZK5xxkjh1i', '系统用户apj0p7', '河北唐山', '1221321312@qq.com', '8c1790c20ab746dfae9a4044d68e2a61', 50, '2023-04-18 12:27:06', '2023-04-18 12:27:06', '');

SET FOREIGN_KEY_CHECKS = 1;
