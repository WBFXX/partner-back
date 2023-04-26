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

 Date: 26/04/2023 20:46:05
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
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '简介',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `uid` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户标识',
  `deleted` int(255) DEFAULT 0 COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '动态' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dynamic
-- ----------------------------
INSERT INTO `dynamic` VALUES (1, '测试', '测试内容', 'http://localhost:9090/file/download/56ea6f90963143829e86f6b652d0b236.jpg', '测试', '2022-11-29 21:37:24', '2022-11-29 21:37:26', 'efb74e2cdc5b4ae680959d25b7cd101c', 0);
INSERT INTO `dynamic` VALUES (2, '123', '123', 'http://localhost:9090/file/download/56ea6f90963143829e86f6b652d0b236.jpg', '123', '2023-04-26 16:31:08', '2023-04-26 16:31:08', 'efb74e2cdc5b4ae680959d25b7cd101c', 0);

-- ----------------------------
-- Table structure for im
-- ----------------------------
DROP TABLE IF EXISTS `im`;
CREATE TABLE `im`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户编号',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户姓名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `sign` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '个性签名',
  `text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '消息文字',
  `create_time` datetime(0) DEFAULT NULL COMMENT '发送时间',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of im
-- ----------------------------
INSERT INTO `im` VALUES (1, '5a4f2a4963c148d1a27cb8bdb712792c', '管理员', 'http://localhost:9090/file/download/846c0d3b444845189ef1fd6643ec8f24.jpg', NULL, '1', '2023-04-25 20:38:46', NULL);
INSERT INTO `im` VALUES (2, '5a4f2a4963c148d1a27cb8bdb712792c', '管理员', 'http://localhost:9090/file/download/846c0d3b444845189ef1fd6643ec8f24.jpg', NULL, '1', '2023-04-25 20:38:55', NULL);
INSERT INTO `im` VALUES (3, '5a4f2a4963c148d1a27cb8bdb712792c', '管理员', 'http://localhost:9090/file/download/846c0d3b444845189ef1fd6643ec8f24.jpg', NULL, 'a', '2023-04-25 20:45:44', NULL);
INSERT INTO `im` VALUES (4, '5a4f2a4963c148d1a27cb8bdb712792c', '管理员', 'http://localhost:9090/file/download/846c0d3b444845189ef1fd6643ec8f24.jpg', NULL, 'a', '2023-04-25 20:52:02', NULL);
INSERT INTO `im` VALUES (5, '5a4f2a4963c148d1a27cb8bdb712792c', '管理员', 'http://localhost:9090/file/download/846c0d3b444845189ef1fd6643ec8f24.jpg', NULL, '1', '2023-04-25 20:58:57', NULL);
INSERT INTO `im` VALUES (6, '5a4f2a4963c148d1a27cb8bdb712792c', '管理员', 'http://localhost:9090/file/download/846c0d3b444845189ef1fd6643ec8f24.jpg', NULL, '1\n', '2023-04-25 21:11:23', NULL);
INSERT INTO `im` VALUES (7, '5a4f2a4963c148d1a27cb8bdb712792c', '管理员', 'http://localhost:9090/file/download/846c0d3b444845189ef1fd6643ec8f24.jpg', NULL, '😃', '2023-04-25 21:11:46', NULL);
INSERT INTO `im` VALUES (8, '5a4f2a4963c148d1a27cb8bdb712792c', '管理员', 'http://localhost:9090/file/download/846c0d3b444845189ef1fd6643ec8f24.jpg', NULL, 'a', '2023-04-25 21:12:02', NULL);
INSERT INTO `im` VALUES (9, '5a4f2a4963c148d1a27cb8bdb712792c', '管理员', 'http://localhost:9090/file/download/846c0d3b444845189ef1fd6643ec8f24.jpg', NULL, 'a', '2023-04-25 21:12:32', NULL);
INSERT INTO `im` VALUES (10, '5a4f2a4963c148d1a27cb8bdb712792c', '管理员', 'http://localhost:9090/file/download/846c0d3b444845189ef1fd6643ec8f24.jpg', NULL, 'sasa', '2023-04-25 21:15:56', NULL);
INSERT INTO `im` VALUES (11, 'b4f3422e6c82439ea3e112d69091584e', 'abb', 'http://localhost:9090/file/download/846c0d3b444845189ef1fd6643ec8f24.jpg', NULL, '22', '2023-04-25 21:30:36', NULL);
INSERT INTO `im` VALUES (12, 'b4f3422e6c82439ea3e112d69091584e', 'abb', 'http://localhost:9090/file/download/846c0d3b444845189ef1fd6643ec8f24.jpg', NULL, '😉😇🥰😋🤨', '2023-04-25 21:35:14', NULL);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编码',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '内容',
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类型',
  `deleted` int(11) DEFAULT 0 COMMENT '删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `c_d`(`code`, `deleted`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 'user', 'user', 'icon', 0);
INSERT INTO `sys_dict` VALUES (2, 'message', 'message', 'icon', 0);
INSERT INTO `sys_dict` VALUES (3, 'menu', 'menu', 'icon', 0);
INSERT INTO `sys_dict` VALUES (4, 'grid', 'grid', 'icon', 0);
INSERT INTO `sys_dict` VALUES (5, 'star', 'star', 'icon', 0);
INSERT INTO `sys_dict` VALUES (6, 'house', 'house', 'icon', 0);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '路径',
  `orders` int(11) DEFAULT 1 COMMENT '顺序',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'grid' COMMENT '图标',
  `page` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '页面路径',
  `auth` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限',
  `pid` int(11) DEFAULT NULL COMMENT '父级id',
  `deleted` int(1) DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `type` int(1) DEFAULT NULL COMMENT '类型，1目录  2菜单 3按钮',
  `hide` tinyint(1) DEFAULT 0 COMMENT '是否隐藏',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `a_d_index`(`auth`, `deleted`) USING BTREE,
  UNIQUE INDEX `p_p_d_index`(`path`, `page`, `deleted`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '系统管理', '', 2, 'menu', NULL, NULL, NULL, 0, '2023-01-16 20:45:51', '2023-01-16 20:45:51', 1, 0);
INSERT INTO `sys_permission` VALUES (3, '用户管理', 'user', 1, 'grid', 'User', 'user.list', 1, 0, '2023-01-16 20:45:51', '2023-01-16 20:45:51', 2, 0);
INSERT INTO `sys_permission` VALUES (4, '用户新增', '', 1, 'grid', '', 'user.add', 3, 0, '2023-01-16 20:45:51', '2023-01-16 20:45:51', 3, 0);
INSERT INTO `sys_permission` VALUES (8, '用户编辑', '', 1, 'grid', NULL, 'user.edit', 3, 0, NULL, '2023-01-28 11:45:21', 3, 0);
INSERT INTO `sys_permission` VALUES (9, '用户删除', NULL, 1, 'grid', NULL, 'user.delete', 3, 0, '2023-01-29 11:04:15', '2023-01-29 11:04:15', 3, 0);
INSERT INTO `sys_permission` VALUES (10, '角色管理', 'role', 1, 'grid', 'Role', 'role', 1, 0, '2023-04-23 10:30:59', NULL, 2, 0);
INSERT INTO `sys_permission` VALUES (11, '权限管理', 'permission', 1, 'grid', 'Permission', 'permission.list', 1, 0, '2023-04-23 10:31:37', NULL, 2, 0);
INSERT INTO `sys_permission` VALUES (12, '首页', 'home', 1, 'house', 'Home', '', NULL, 0, '2023-04-23 10:49:36', NULL, 2, 0);
INSERT INTO `sys_permission` VALUES (13, '数据字典管理', 'dict', 1, 'grid', 'Dict', 'dict.list', 1, 0, '2023-04-23 20:43:01', NULL, 2, 0);
INSERT INTO `sys_permission` VALUES (14, '批量删除', NULL, 1, 'grid', NULL, 'user.deleteBatch', 3, 0, '2023-04-24 12:34:13', NULL, 3, 0);
INSERT INTO `sys_permission` VALUES (15, '用户导入', NULL, 1, 'grid', NULL, 'user.import', 3, 0, '2023-04-24 12:34:49', NULL, 3, 0);
INSERT INTO `sys_permission` VALUES (16, '用户导出', NULL, 1, 'grid', NULL, 'user.export', 3, 0, '2023-04-24 12:35:03', NULL, 3, 0);
INSERT INTO `sys_permission` VALUES (17, '聊天管理', 'im', 1, 'grid', 'Im', 'im.list', NULL, 0, '2023-04-25 20:09:08', NULL, 2, 0);
INSERT INTO `sys_permission` VALUES (18, '动态管理', 'dynamic', 1, 'grid', 'Dynamic', NULL, NULL, 0, NULL, NULL, 2, 0);
INSERT INTO `sys_permission` VALUES (19, '动态查询', NULL, 1, 'grid', NULL, 'dynamic.list', 18, 0, NULL, NULL, 3, 0);
INSERT INTO `sys_permission` VALUES (20, '动态新增', NULL, 1, 'grid', NULL, 'dynamic.add', 18, 0, NULL, NULL, 3, 0);
INSERT INTO `sys_permission` VALUES (21, '动态导入', NULL, 1, 'grid', NULL, 'dynamic.import', 18, 0, NULL, NULL, 3, 0);
INSERT INTO `sys_permission` VALUES (22, '动态导出', NULL, 1, 'grid', NULL, 'dynamic.export', 18, 0, NULL, NULL, 3, 0);
INSERT INTO `sys_permission` VALUES (23, '批量删除', NULL, 1, 'grid', NULL, 'dynamic.deleteBatch', 18, 0, NULL, NULL, 3, 0);
INSERT INTO `sys_permission` VALUES (24, '动态编辑', NULL, 1, 'grid', NULL, 'dynamic.edit', 18, 0, NULL, NULL, 3, 0);
INSERT INTO `sys_permission` VALUES (25, '动态删除', NULL, 1, 'grid', NULL, 'dynamic.delete', 18, 0, NULL, NULL, 3, 0);
INSERT INTO `sys_permission` VALUES (26, '热门动态', NULL, 1, 'grid', NULL, 'dynamic.list.hot', 18, 0, '2023-04-26 17:24:59', NULL, 3, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `flag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '唯一标识',
  `deleted` int(1) DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `flag_deleted_index`(`flag`, `deleted`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (2, '管理员', 'ADMIN', 0, '2023-04-19 22:07:50', NULL);
INSERT INTO `sys_role` VALUES (3, '普通用户', 'USER', 0, '2023-04-19 22:08:15', NULL);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_id`(`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 506 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (492, 2, 1);
INSERT INTO `sys_role_permission` VALUES (493, 2, 3);
INSERT INTO `sys_role_permission` VALUES (494, 2, 4);
INSERT INTO `sys_role_permission` VALUES (495, 2, 8);
INSERT INTO `sys_role_permission` VALUES (496, 2, 9);
INSERT INTO `sys_role_permission` VALUES (500, 2, 10);
INSERT INTO `sys_role_permission` VALUES (501, 2, 11);
INSERT INTO `sys_role_permission` VALUES (482, 2, 12);
INSERT INTO `sys_role_permission` VALUES (502, 2, 13);
INSERT INTO `sys_role_permission` VALUES (497, 2, 14);
INSERT INTO `sys_role_permission` VALUES (498, 2, 15);
INSERT INTO `sys_role_permission` VALUES (499, 2, 16);
INSERT INTO `sys_role_permission` VALUES (483, 2, 17);
INSERT INTO `sys_role_permission` VALUES (484, 2, 18);
INSERT INTO `sys_role_permission` VALUES (485, 2, 19);
INSERT INTO `sys_role_permission` VALUES (486, 2, 20);
INSERT INTO `sys_role_permission` VALUES (487, 2, 21);
INSERT INTO `sys_role_permission` VALUES (488, 2, 22);
INSERT INTO `sys_role_permission` VALUES (489, 2, 23);
INSERT INTO `sys_role_permission` VALUES (490, 2, 24);
INSERT INTO `sys_role_permission` VALUES (491, 2, 25);
INSERT INTO `sys_role_permission` VALUES (504, 3, 12);
INSERT INTO `sys_role_permission` VALUES (503, 3, 18);
INSERT INTO `sys_role_permission` VALUES (505, 3, 26);

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
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'ADMIN' COMMENT '角色',
  `sign` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '个性签名',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uid_index`(`uid`) USING BTREE,
  UNIQUE INDEX `username_index`(`username`, `deleted`) USING BTREE,
  UNIQUE INDEX `email_index`(`email`, `deleted`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'bgg', '$2a$10$KYJgiVBuZIWmZni93GBmC.g2fMTVhja8tzZ1LtToGOf44q1E2Z8Cq', 'Me', '河北保定', '115771236@qq.com', 'efb74e2cdc5b4ae680959d25b7cd101c', 0, '2023-03-01 16:06:50', '2023-03-01 16:06:50', 'http://localhost:9090/file/download/3b0159d59eab4edbb07dcc21776acf99.jpg', 'USER', '1');
INSERT INTO `sys_user` VALUES (2, 'admin', '$2a$10$N6Yd/ifpjDgvw8yJ3x2hOOM/qIU6RoM2kmSlzRKfdS2fY5gQgz.E2', '管理员', '河北保定', '151622545@qq.com', '5a4f2a4963c148d1a27cb8bdb712792c', 0, '2023-04-09 10:57:12', '2023-04-09 10:57:12', 'http://localhost:9090/file/download/3b0159d59eab4edbb07dcc21776acf99.jpg', 'ADMIN', '1');
INSERT INTO `sys_user` VALUES (47, 'bwwwg', '$2a$10$QQ2DkXQDxWtbvtyV2G9fiufMppqs8AyWm5bDjd7F9ROUXLIpeqCeu', '系统用户od1cgg', '河北保定', '11577386@qq.com', 'f01a9f5518b94aa6ba6923893be08ba9', 0, '2023-04-18 12:26:31', '2023-04-18 12:26:31', 'http://localhost:9090/file/download/3b0159d59eab4edbb07dcc21776acf99.jpg', 'ADMIN', '1');
INSERT INTO `sys_user` VALUES (48, 'qqqqqqqq', '$2a$10$vWA4k9wDZe8Xwpii2UF0Oe95WIyuQmwgggF6E0Mnsc5TBVaVU.3de', '系统用户23u3al', '河北保定', '15112316545@qq.com', '2ad8ecc27d87420a8ff33f4bf7c168c4', 48, '2023-04-18 12:26:31', '2023-04-18 12:26:31', 'http://localhost:9090/file/download/3b0159d59eab4edbb07dcc21776acf99.jpg', 'ADMIN', '1');
INSERT INTO `sys_user` VALUES (49, 'agasg', '$2a$10$vxcyJMn1xuvaeWsddr.yf.9D41ZD7Lvdv/zhVAls21i.egcIZ1Zz2', '系统用户hrslea', '河北保定', '13123123@qq.com', '14c09e7a307f4620a721776256681049', 49, '2023-04-18 12:27:06', '2023-04-18 12:27:06', 'http://localhost:9090/file/download/3b0159d59eab4edbb07dcc21776acf99.jpg', 'ADMIN', '1');
INSERT INTO `sys_user` VALUES (50, 'agaag', '$2a$10$5yJPrEAZK3i3Nly1.s0wj.UZC/XtnsjWl4w9OPvYrEIZK5xxkjh1i', '系统用户apj0p7', '河北唐山', '1221321312@qq.com', '8c1790c20ab746dfae9a4044d68e2a61', 50, '2023-04-18 12:27:06', '2023-04-18 12:27:06', 'http://localhost:9090/file/download/3b0159d59eab4edbb07dcc21776acf99.jpg', 'ADMIN', '1');
INSERT INTO `sys_user` VALUES (51, 'agg', '$2a$10$j69nAm8jRNlYrbwycJXIBecpXMkREEUtT3MzNhR2tstyv0JF6AWyi', '系统用户bc8rxq', '河北邯郸', '115771@qq.com', '3369f5e084574870bd6b109639c31f15', 0, '2023-04-22 08:34:50', '2023-04-22 08:34:50', 'http://localhost:9090/file/download/3b0159d59eab4edbb07dcc21776acf99.jpg', 'ADMIN', '1');
INSERT INTO `sys_user` VALUES (52, 'abb', '$2a$10$SnsG9m8zAFTqqYz9A00HSOmsnBMxGqChgcD.IWt4Hmlle89jI9rSm', 'abb', '河北邯郸', '12312312@166.com', 'b4f3422e6c82439ea3e112d69091584e', 0, '2023-04-25 20:24:07', '2023-04-25 20:24:07', 'http://localhost:9090/file/download/846c0d3b444845189ef1fd6643ec8f24.jpg', 'ADMIN', '1');

SET FOREIGN_KEY_CHECKS = 1;
