/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : boom

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 26/10/2020 01:41:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for leiguan_info
-- ----------------------------
DROP TABLE IF EXISTS `leiguan_info`;
CREATE TABLE `leiguan_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fix_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '固定码',
  `child_code` int(20) NOT NULL COMMENT '发码',
  `store_time` bigint(20) NOT NULL  COMMENT '入库时间',
  `send_time` bigint(20) NULL DEFAULT NULL COMMENT '发出时间',
  `back_time` bigint(20) NULL DEFAULT NULL COMMENT '退回时间',
  `use_time` bigint(20) NULL DEFAULT NULL COMMENT '使用时间',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '当前状态',
  `keeper` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '保管人',
  `consumer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '领退人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `key1`(`fix_code`, `child_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for leiguan_log
-- ----------------------------
DROP TABLE IF EXISTS `leiguan_log`;
CREATE TABLE `leiguan_log`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `date` bigint(20) NOT NULL COMMENT '日期',
  `operation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作 1入库 2发出 3退回 4使用',
  `fix_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '固定码',
  `child_from` int(20) NOT NULL COMMENT '发码起始值',
  `child_to` int(20) NOT NULL COMMENT '发码结束值',
  `keeper` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '保管人',
  `consumer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '领退人',
  `operator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '系统录入人员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `key1`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `group_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '队伍信息',
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职位',
  `phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `role` tinyint(4) NOT NULL COMMENT 'admin(1),work(2),guest(3)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `key1`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for zhayao_info
-- ----------------------------
DROP TABLE IF EXISTS `zhayao_info`;
CREATE TABLE `zhayao_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `batch_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '批次号',
  `box_num` int(20) NOT NULL COMMENT '箱号',
  `col_num` tinyint(4) NOT NULL COMMENT '柱号',
  `unit` float(20, 1) NOT NULL COMMENT '单位（kg）',
  `store_time` bigint(20) NOT NULL COMMENT '入库时间',
  `send_time` bigint(20) NULL DEFAULT NULL COMMENT '发出时间',
  `back_time` bigint(20) NULL DEFAULT NULL COMMENT '退还时间',
  `use_time` bigint(20) NULL DEFAULT NULL COMMENT '使用时间',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `keeper` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '保管员',
  `consumer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '领退人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `key1`(`batch_num`, `box_num`, `col_num`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for zhayao_log
-- ----------------------------
DROP TABLE IF EXISTS `zhayao_log`;
CREATE TABLE `zhayao_log`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `date` bigint(20) NOT NULL COMMENT '日期',
  `operation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作 1入库 2发出 3退回 4使用',
  `batch_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '批次号',
  `box_from` int(20) NOT NULL COMMENT '箱号起始值',
  `box_to` int(20) NOT NULL COMMENT '箱号结束值',
  `col_from` tinyint(4) NULL DEFAULT NULL COMMENT '柱号起始值',
  `col_to` tinyint(4) NULL DEFAULT NULL COMMENT '柱号结束值',
  `count` float(20, 0) NOT NULL COMMENT '操作数值（kg）',
  `keeper` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '保管人',
  `consumer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '领退人',
  `operator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '系统录入人员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `boom`.`boom_detail`  (
  `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `date` bigint(20) NOT NULL COMMENT '日期',
  `line_num` int(20) NOT NULL COMMENT '线束号',
  `stake_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '炮排号/桩号',
  `height` int(20) NOT NULL COMMENT '井深',
  `fix_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '雷管固定码',
  `child_code` int(20) NOT NULL COMMENT '雷管子码',
  `batch_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '炸药批次号',
  `box_num` int(20) NOT NULL COMMENT '炸药箱号',
  `col_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '炸药柱码',
  `count` float(20, 1) NOT NULL COMMENT '炸药总重量',
  `down` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '下药工',
  `packager` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '包药工',
  `mark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `videos` json NULL COMMENT '视频链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
SET FOREIGN_KEY_CHECKS = 1;



INSERT INTO `boom`.`boom_detail`(`id`, `date`, `line_num`, `stake_num`, `height`, `fix_code`, `child_code`, `batch_num`, `box_num`, `col_num`, `count`, `down`, `packager`, `mark`, `videos`) VALUES (1, 1604172246915, 0, '580021', 3, '152010', 1, 'batch1', 1, '1-3', 2.0, '下药工姓名', '包药工姓名', '备注', '{\"badVideo\": \"废盲炮视频url\", \"useVideo\": \"下药视频url\", \"sendVideo\": \"交接发放视频url\", \"guardVideo\": \"警卫班视频url\", \"packageVideo\": \"包药视频url\"}');
INSERT INTO `boom`.`boom_detail`(`id`, `date`, `line_num`, `stake_num`, `height`, `fix_code`, `child_code`, `batch_num`, `box_num`, `col_num`, `count`, `down`, `packager`, `mark`, `videos`) VALUES (2, 1604172246915, 1, '580022', 3, '152011', 2, 'batch1', 2, '2-3', 2.0, '下药工姓名', '包药工姓名', '备注', '{\"badVideo\": \"测试地址5\", \"useVideo\": null, \"sendVideo\": \"测试地址2\", \"guardVideo\": \"测试地址\", \"packageVideo\": null}');
INSERT INTO `boom`.`boom_detail`(`id`, `date`, `line_num`, `stake_num`, `height`, `fix_code`, `child_code`, `batch_num`, `box_num`, `col_num`, `count`, `down`, `packager`, `mark`, `videos`) VALUES (3, 1604172246915, 2, '580023', 3, '152012', 3, 'batch1', 3, '2-3', 2.0, '下药工姓名', '包药工姓名', '备注', '{\"badVideo\": \"废盲炮视频url\", \"useVideo\": \"下药视频url\", \"sendVideo\": \"交接发放视频url\", \"guardVideo\": \"警卫班视频url\", \"packageVideo\": \"包药视频url\"}');
