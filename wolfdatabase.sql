/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : wolfdatabase

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 08/07/2021 02:05:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for evaluation
-- ----------------------------
DROP TABLE IF EXISTS `evaluation`;
CREATE TABLE `evaluation` (
  `evaluation_id` bigint NOT NULL AUTO_INCREMENT COMMENT '评价编号',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '短评内容',
  `score` int NOT NULL COMMENT '对该人评分-5分制',
  `user_id` int NOT NULL COMMENT '用户编号',
  `persondata_id` bigint NOT NULL COMMENT '个人信息编号',
  `enjoy` int NOT NULL DEFAULT '0' COMMENT '点赞数量',
  `state` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'enable' COMMENT '审核状态 enable-有效 disable-已禁用',
  `disable_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '禁用理由',
  `disable_time` datetime DEFAULT NULL COMMENT '禁用时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`evaluation_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of evaluation
-- ----------------------------
BEGIN;
INSERT INTO `evaluation` VALUES (1, 'e12', 3, 4, 6, 0, 'enable', NULL, NULL, '2021-07-03 22:31:31');
INSERT INTO `evaluation` VALUES (2, '拿狼：“哎我不知道谁是预言家”', 5, 4, 7, 0, 'enable', NULL, NULL, '2021-07-04 01:06:20');
INSERT INTO `evaluation` VALUES (3, '拿狼：“你们怎么能忍受az活到最后”', 5, 4, 8, 0, 'enable', NULL, NULL, '2021-07-04 01:09:48');
INSERT INTO `evaluation` VALUES (4, '滑水大师', 5, 5, 7, 10, 'enable', NULL, NULL, '2021-07-04 01:18:09');
INSERT INTO `evaluation` VALUES (5, '玩扇子?也能输？', 5, 5, 8, 0, 'enable', NULL, NULL, '2021-07-04 01:21:42');
INSERT INTO `evaluation` VALUES (6, '“拿狼把把悍跳预言家”', 5, 4, 10, 0, 'enable', NULL, NULL, '2021-07-04 01:34:37');
INSERT INTO `evaluation` VALUES (8, '最好不要直视这人', 5, 4, 12, 0, 'enable', NULL, NULL, '2021-07-04 01:38:57');
INSERT INTO `evaluation` VALUES (9, '当你搞不懂他的身份时不要慌，他只是在乱玩罢了', 5, 4, 11, 0, 'enable', NULL, NULL, '2021-07-04 01:39:38');
INSERT INTO `evaluation` VALUES (10, '吉米哥还是你吉米哥，拿守卫先冲狼', 5, 4, 9, 0, 'enable', NULL, NULL, '2021-07-04 01:40:20');
INSERT INTO `evaluation` VALUES (11, '状态狼', 1, 4, 23, 0, 'enable', NULL, NULL, '2021-07-07 23:11:14');
INSERT INTO `evaluation` VALUES (12, '拿跳预言家起状态素质极差', 1, 7, 23, 0, 'enable', NULL, NULL, '2021-07-07 23:53:31');
INSERT INTO `evaluation` VALUES (13, '是好人还行，拿狼我体验极差', 2, 7, 12, 0, 'enable', NULL, NULL, '2021-07-07 23:54:30');
INSERT INTO `evaluation` VALUES (14, '接到查杀乱跳神', 1, 7, 11, 0, 'enable', NULL, NULL, '2021-07-07 23:55:17');
INSERT INTO `evaluation` VALUES (15, '不能看他投票水很深', 1, 7, 9, 0, 'enable', NULL, NULL, '2021-07-07 23:55:49');
INSERT INTO `evaluation` VALUES (16, '亚当老师偶尔蒙对狼人', 5, 7, 23, 0, 'enable', NULL, NULL, '2021-07-08 00:01:17');
INSERT INTO `evaluation` VALUES (17, '亚当学生', 1, 7, 23, 0, 'enable', NULL, NULL, '2021-07-08 00:01:25');
COMMIT;

-- ----------------------------
-- Table structure for persondata
-- ----------------------------
DROP TABLE IF EXISTS `persondata`;
CREATE TABLE `persondata` (
  `persondata_id` int NOT NULL AUTO_INCREMENT COMMENT '个人信息编号',
  `username` varchar(64) NOT NULL COMMENT '用户id',
  `person_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '人名',
  `sub_title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '外号',
  `author` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作者',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '个人图片',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '个人信息详情',
  `evaluation_score` float(255,1) NOT NULL DEFAULT '0.0' COMMENT '评分',
  `evaluation_quantity` int NOT NULL DEFAULT '0' COMMENT '评价数量',
  PRIMARY KEY (`persondata_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of persondata
-- ----------------------------
BEGIN;
INSERT INTO `persondata` VALUES (7, 'Christyty', 'Christyty', 'hello我是christy', 'warren', '/upload/20210704030509860.jpeg', '<p><img src=\"/upload/20210704030509860.jpeg\" style=\"max-width:100%;\"><br></p>', 0.0, 0);
INSERT INTO `persondata` VALUES (8, 'Esther', 'Esther', '我是艾老板esther', 'warren', '/upload/20210704030852060.jpeg', '<p><img src=\"/upload/20210704030852060.jpeg\" style=\"max-width:100%;\"><br></p>', 0.0, 0);
INSERT INTO `persondata` VALUES (9, 'warren', 'Jimmy', '吉米哥带飞你', 'warren', '/upload/20210704031426908.jpeg', '<p><img src=\"/upload/20210704031426908.jpeg\" style=\"max-width:100%;\"><br></p>', 0.0, 0);
INSERT INTO `persondata` VALUES (10, '小鸟发誓', '小鸟发誓', '鸟师大姐', 'warren', '/upload/20210704031809935.jpeg', '<p><img src=\"/upload/20210704031809935.jpeg\" style=\"max-width:100%;\"><img src=\"/upload/20210704031841026.jpeg\" style=\"max-width: 100%;\"><br></p>', 0.0, 0);
INSERT INTO `persondata` VALUES (11, '小婶婶', '小婶婶', '你小婶', 'warren', '/upload/20210704032130018.jpeg', '<p><img src=\"/upload/20210704032130018.jpeg\" style=\"max-width:100%;\"><br></p>', 0.0, 0);
INSERT INTO `persondata` VALUES (12, 'Kevin', 'Kevin', '猪油', 'warren', '/upload/20210704032205212.jpeg', '<p><img src=\"/upload/20210704032205212.jpeg\" style=\"max-width:100%;\"><br></p>', 0.0, 0);
INSERT INTO `persondata` VALUES (23, 'warren', '太君', '大大滴狼', 'warren', '/upload/20210708010546097.jpeg', '<p><img src=\"/upload/20210708010546097.jpeg\" style=\"max-width:100%;\"><br></p>', 0.0, 0);
COMMIT;

-- ----------------------------
-- Table structure for wolfuser
-- ----------------------------
DROP TABLE IF EXISTS `wolfuser`;
CREATE TABLE `wolfuser` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '用户密码，MD5加密',
  `personalized_signature` varchar(50) NOT NULL DEFAULT '' COMMENT '个性签名',
  `wechat` varchar(50) NOT NULL DEFAULT '' COMMENT '微信号码',
  `role` int NOT NULL DEFAULT '1' COMMENT '角色，1-普通用户，2-管理员 0-禁用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of wolfuser
-- ----------------------------
BEGIN;
INSERT INTO `wolfuser` VALUES (4, 'warren', '98007299789c3265705d7f9d381d1779', 'Hello 大家好我是warren', '', 2, '2021-07-03 21:31:47', '2021-07-07 22:50:28');
INSERT INTO `wolfuser` VALUES (5, '勇敢鸡鸡不怕困难', 'd432eb18017c004fd305969713a17aa8', 'Hello 大家好我是勇敢鸡鸡不怕困难', '', 2, '2021-07-04 01:11:28', '2021-07-04 03:18:24');
INSERT INTO `wolfuser` VALUES (6, 'wjb123', '98007299789c3265705d7f9d381d1779', 'Hello 大家好我是wjb123', 'wjb1498314886', 1, '2021-07-07 23:28:29', '2021-07-08 01:28:28');
INSERT INTO `wolfuser` VALUES (7, '路人0011', '98007299789c3265705d7f9d381d1779', 'Hello 大家好我是路人0011', '？？', 1, '2021-07-07 23:52:45', '2021-07-08 01:52:45');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
