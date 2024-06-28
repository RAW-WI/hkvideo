/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : 127.0.0.1:3306
 Source Schema         : hkvideo

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 28/06/2024 17:38:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '密码',
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `status` int NULL DEFAULT 1 COMMENT '状态：0-正常  1-禁用',
  `salt` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '盐值,加密用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '管理员/审核员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123', NULL, NULL, 1, NULL);
INSERT INTO `admin` VALUES (2, 'root', '123456', NULL, NULL, 1, NULL);
INSERT INTO `admin` VALUES (3, 'ruandy', '123', NULL, NULL, 1, NULL);
INSERT INTO `admin` VALUES (4, 'manager', '666888', NULL, NULL, 1, NULL);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '频道分类名',
  `summary` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '频道分类介绍',
  `img` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '封面图地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '频道分类表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (2, '生活', '艺术源于生活', 'http://www.softeem.com/img/2.jpg');
INSERT INTO `category` VALUES (3, '游戏', '精彩世界尽在游戏', 'http://www.softeem.com/img/3.jpg');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `commtime` datetime NULL DEFAULT NULL COMMENT '评论时间',
  `uid` int NULL DEFAULT NULL COMMENT '用户id',
  `vid` int NULL DEFAULT NULL COMMENT '视频id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_comment_user_1`(`uid` ASC) USING BTREE,
  INDEX `fk_comment_video_1`(`vid` ASC) USING BTREE,
  CONSTRAINT `fk_comment_user_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_comment_video_1` FOREIGN KEY (`vid`) REFERENCES `video` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `uid` int NULL DEFAULT NULL COMMENT '用户id',
  `vid` int NULL DEFAULT NULL COMMENT '视频id',
  `favtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_favorite_user_1`(`uid` ASC) USING BTREE,
  INDEX `fk_favorite_video_1`(`vid` ASC) USING BTREE,
  CONSTRAINT `fk_favorite_user_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_favorite_video_1` FOREIGN KEY (`vid`) REFERENCES `video` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '收藏表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of favorite
-- ----------------------------
INSERT INTO `favorite` VALUES (1, 1, 2, '2024-06-01 13:42:52');
INSERT INTO `favorite` VALUES (2, 1, 3, '2024-06-01 13:42:55');
INSERT INTO `favorite` VALUES (3, 2, 1, '2024-06-01 13:43:10');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '标识列(PK)，自动生成，且唯一',
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机号 唯一',
  `regtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间 精确到时分秒  yyyy-MM-dd HH:mm:ss',
  `img` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '头像图片存储地址(oss技术)',
  `birth` date NULL DEFAULT NULL COMMENT '生日格式yyyy-MM-dd',
  `status` int NULL DEFAULT 0 COMMENT '账号状态 0-正常    1-禁用',
  `salt` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '盐值，加密用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户表\r\n' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'softeem', 'asdfasfafwer', NULL, '2024-05-28 14:13:12', NULL, '2024-06-05', 0, NULL);
INSERT INTO `user` VALUES (2, 'kobe', '123', NULL, '2024-05-28 14:15:04', NULL, '2024-06-20', 0, NULL);

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '标题',
  `summary` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '详情',
  `coverimg` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '封面图片地址',
  `url` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '视频文件地址',
  `releasetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `viewcount` int NULL DEFAULT NULL COMMENT '观看数',
  `lovecount` int NULL DEFAULT NULL COMMENT '点赞数',
  `favcount` int NULL DEFAULT NULL COMMENT '收藏数',
  `forward` int NULL DEFAULT NULL COMMENT '转发数',
  `status` int NULL DEFAULT NULL COMMENT '视频状态：0-审核中  1-禁用   2-正常  ',
  `uid` int NULL DEFAULT NULL COMMENT '创作者',
  `aid` int NULL DEFAULT NULL COMMENT '审核员',
  `tags` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '标签',
  `cid` int NULL DEFAULT NULL COMMENT '频道分类',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_video_user_1`(`uid` ASC) USING BTREE,
  INDEX `fk_video_admin_1`(`aid` ASC) USING BTREE,
  INDEX `fk_video_category_1`(`cid` ASC) USING BTREE,
  CONSTRAINT `fk_video_admin_1` FOREIGN KEY (`aid`) REFERENCES `admin` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_video_category_1` FOREIGN KEY (`cid`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_video_user_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '视频表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES (1, '51旅游随拍', '一起感受大自然的气息吧', 'https://gimg3.baidu.com/search/src=http%3A%2F%2Fpics0.baidu.com%2Ffeed%2F7e3e6709c93d70cf6e75f38f1aeecd0ebba12b61.jpeg%40f_auto%3Ftoken%3Dd0690dad663f251a5c5ad210d45f4a16&refer=http%3A%2F%2Fwww.baidu.com&app=2021&size=f360,240&n=0&g=0n&q=75&fmt=auto?sec=1717606800&t=228257b513db284aeb7b0fc50ee4e234', 'http://www.softeem.com/video/4.mp4', '2024-05-29 19:18:36', 10673, 1685455, 12, 44, 0, 1, 4, '搞笑,二次元', 3);
INSERT INTO `video` VALUES (2, '清明节随拍', '清明时节雨纷纷，路上行人欲断魂', 'https://gimg3.baidu.com/search/src=http%3A%2F%2Fpics0.baidu.com%2Ffeed%2F7e3e6709c93d70cf6e75f38f1aeecd0ebba12b61.jpeg%40f_auto%3Ftoken%3Dd0690dad663f251a5c5ad210d45f4a16&refer=http%3A%2F%2Fwww.baidu.com&app=2021&size=f360,240&n=0&g=0n&q=75&fmt=auto?sec=1717606800&t=228257b513db284aeb7b0fc50ee4e234', 'http://www.softeem.com/video/5.mp4', '2024-05-29 19:19:24', 45123, 345654, 33, 55, 2, 2, 4, '动漫,游戏', 2);
INSERT INTO `video` VALUES (3, '端午节划龙舟', '路漫漫其修远兮，吾将上下而求索', 'https://gimg3.baidu.com/search/src=http%3A%2F%2Fpics0.baidu.com%2Ffeed%2F7e3e6709c93d70cf6e75f38f1aeecd0ebba12b61.jpeg%40f_auto%3Ftoken%3Dd0690dad663f251a5c5ad210d45f4a16&refer=http%3A%2F%2Fwww.baidu.com&app=2021&size=f360,240&n=0&g=0n&q=75&fmt=auto?sec=1717606800&t=228257b513db284aeb7b0fc50ee4e234', 'http://www.softeem.com/video/6.mp4', '2024-05-29 19:20:22', 4532, 653545, 12, 66, 1, 2, 4, '生活,娱乐', 2);

SET FOREIGN_KEY_CHECKS = 1;
