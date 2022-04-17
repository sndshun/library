/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : library

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 17/04/2022 22:09:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attachment
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment`  (
  `id` varbinary(32) NOT NULL COMMENT 'id',
  `img_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图片名称',
  `suffix` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '后缀',
  `size` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '默认单位byte',
  `module` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件来自那个模块',
  `img_title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `img_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '访问路径',
  `absolute_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '真实路径',
  `data_href` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '跳转链接',
  `is_delete` tinyint NULL DEFAULT 1 COMMENT '1:正常0:删除',
  `delete_time` datetime NULL DEFAULT NULL COMMENT '默认在回收站30天删除\r\n通过定时任务每天根据这个字段查询到删除时间的图片',
  `delete_by` int NULL DEFAULT NULL COMMENT '查看是谁放回收站的',
  `create_by` int NULL DEFAULT NULL COMMENT '上传人',
  `update_by` int NULL DEFAULT NULL COMMENT '修改人',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attachment
-- ----------------------------
INSERT INTO `attachment` VALUES (0x3230323230343135323231313431393839373938, '20220415221141989798.jpg', 'jpg', '13058', 'book_cover', NULL, '这是一个图书封面', 'http://localhost:8081/file/2022/20220415221141989798.jpg', 'E:/workSpaceidea/img/2022/', 'http://localhost:8081/file/2022/20220415221141989798.jpg', 1, NULL, NULL, 1, NULL, '2022-04-15 22:11:41', '2022-04-16 13:07:32');
INSERT INTO `attachment` VALUES (0x3230323230343135323232313435303533353538, '20220415222145053558.jpg', 'jpg', '8893', 'book_cover', NULL, '这是一个图书封面', 'http://localhost:8081/file/2022/20220415222145053558.jpg', 'E:/workSpaceidea/img/2022/', 'http://localhost:8081/file/2022/20220415222145053558.jpg', 1, NULL, NULL, 1, NULL, '2022-04-15 22:21:45', '2022-04-16 13:07:34');
INSERT INTO `attachment` VALUES (0x3230323230343135323233323037303634373131, '20220415223207064711.jpg', 'jpg', '16884', 'book_cover', NULL, '这是一个图书封面', 'http://localhost:8081/file/2022/20220415223207064711.jpg', 'E:/workSpaceidea/img/2022/', 'http://localhost:8081/file/2022/20220415223207064711.jpg', 1, NULL, NULL, 1, NULL, '2022-04-15 22:32:07', '2022-04-16 13:07:37');
INSERT INTO `attachment` VALUES (0x3230323230343135323233333430383238343435, '20220415223340828445.jpg', 'jpg', '13642', 'book_cover', NULL, '这是一个图书封面', 'http://localhost:8081/file/2022/20220415223340828445.jpg', 'E:/workSpaceidea/img/2022/', 'http://localhost:8081/file/2022/20220415223340828445.jpg', 1, NULL, NULL, 2, NULL, '2022-04-15 22:33:40', '2022-04-16 13:07:39');
INSERT INTO `attachment` VALUES (0x3230323230343135323233343239303630363836, '20220415223429060686.jpg', 'jpg', '7118', 'book_cover', NULL, '这是一个图书封面', 'http://localhost:8081/file/2022/20220415223429060686.jpg', 'E:/workSpaceidea/img/2022/', 'http://localhost:8081/file/2022/20220415223429060686.jpg', 1, NULL, NULL, 2, NULL, '2022-04-15 22:34:29', '2022-04-16 13:07:41');
INSERT INTO `attachment` VALUES (0x3230323230343135323233373531343939343332, '20220415223751499432.jpg', 'jpg', '14352', 'book_cover', NULL, '这是一个图书封面', 'http://localhost:8081/file/2022/20220415223751499432.jpg', 'E:/workSpaceidea/img/2022/', 'http://localhost:8081/file/2022/20220415223751499432.jpg', 1, NULL, NULL, 2, NULL, '2022-04-15 22:37:51', '2022-04-16 13:07:44');
INSERT INTO `attachment` VALUES (0x3230323230343135323233393539373238303831, '20220415223959728081.jpg', 'jpg', '23937', 'book_cover', NULL, '这是一个图书封面', 'http://localhost:8081/file/2022/20220415223959728081.jpg', 'E:/workSpaceidea/img/2022/', 'http://localhost:8081/file/2022/20220415223959728081.jpg', 1, NULL, NULL, 2, NULL, '2022-04-15 22:39:59', '2022-04-16 13:07:46');
INSERT INTO `attachment` VALUES (0x3230323230343135323330363137383735393034, '20220415230617875904.jpg', 'jpg', '17670', 'book_cover', NULL, '这是一个图书封面', 'http://localhost:8081/file/2022/20220415230617875904.jpg', 'E:/workSpaceidea/img/2022/', 'http://localhost:8081/file/2022/20220415230617875904.jpg', 1, NULL, NULL, 2, NULL, '2022-04-15 23:06:17', '2022-04-16 13:07:49');
INSERT INTO `attachment` VALUES (0x3230323230343135323330373235343236373838, '20220415230725426788.jpg', 'jpg', '11428', 'book_cover', NULL, '这是一个图书封面', 'http://localhost:8081/file/2022/20220415230725426788.jpg', 'E:/workSpaceidea/img/2022/', 'http://localhost:8081/file/2022/20220415230725426788.jpg', 1, NULL, NULL, 2, NULL, '2022-04-15 23:07:25', '2022-04-16 13:07:51');
INSERT INTO `attachment` VALUES (0x3230323230343135323332353036373130393134, '20220415232506710914.jpg', 'jpg', '24250', 'book_cover', NULL, '这是一个图书封面', 'http://localhost:8081/file/2022/20220415232506710914.jpg', 'E:/workSpaceidea/img/2022/', 'http://localhost:8081/file/2022/20220415232506710914.jpg', 1, NULL, NULL, 2, NULL, '2022-04-15 23:25:06', '2022-04-16 13:07:53');
INSERT INTO `attachment` VALUES (0x3230323230343135323332363137353939383331, '20220415232617599831.jpg', 'jpg', '31537', 'book_cover', NULL, '这是一个图书封面', 'http://localhost:8081/file/2022/20220415232617599831.jpg', 'E:/workSpaceidea/img/2022/', 'http://localhost:8081/file/2022/20220415232617599831.jpg', 1, NULL, NULL, 2, NULL, '2022-04-15 23:26:17', '2022-04-16 13:07:56');
INSERT INTO `attachment` VALUES (0x3230323230343135323333303537313738303634, '20220415233057178064.jpg', 'jpg', '20367', 'book_cover', NULL, '这是一个图书封面', 'http://localhost:8081/file/2022/20220415233057178064.jpg', 'E:/workSpaceidea/img/2022/', 'http://localhost:8081/file/2022/20220415233057178064.jpg', 1, NULL, NULL, 2, NULL, '2022-04-15 23:30:57', '2022-04-16 13:07:58');
INSERT INTO `attachment` VALUES (0x3230323230343135323333343030383230363330, '20220415233400820630.jpg', 'jpg', '40261', 'book_cover', NULL, '这是一个图书封面', 'http://localhost:8081/file/2022/20220415233400820630.jpg', 'E:/workSpaceidea/img/2022/', 'http://localhost:8081/file/2022/20220415233400820630.jpg', 1, NULL, NULL, 2, NULL, '2022-04-15 23:34:00', '2022-04-16 13:08:00');
INSERT INTO `attachment` VALUES (0x3230323230343135323333353034393031343939, '20220415233504901499.jpg', 'jpg', '41938', 'book_cover', NULL, '这是一个图书封面', 'http://localhost:8081/file/2022/20220415233504901499.jpg', 'E:/workSpaceidea/img/2022/', 'http://localhost:8081/file/2022/20220415233504901499.jpg', 1, NULL, NULL, 2, NULL, '2022-04-15 23:35:04', '2022-04-16 13:08:03');
INSERT INTO `attachment` VALUES (0x3230323230343135323334383232383933393634, '20220415234822893964.jpg', 'jpg', '16608', 'book_cover', NULL, '这是一个图书封面', 'http://localhost:8081/file/2022/20220415234822893964.jpg', 'E:/workSpaceidea/img/2022/', 'http://localhost:8081/file/2022/20220415234822893964.jpg', 1, NULL, NULL, 2, NULL, '2022-04-15 23:48:22', '2022-04-16 13:08:09');
INSERT INTO `attachment` VALUES (0x3230323230343135323335323435313031313230, '20220415235245101120.jpg', 'jpg', '25506', 'book_cover', NULL, '这是一个图书封面', 'http://localhost:8081/file/2022/20220415235245101120.jpg', 'E:/workSpaceidea/img/2022/', 'http://localhost:8081/file/2022/20220415235245101120.jpg', 1, NULL, NULL, 2, NULL, '2022-04-15 23:52:45', '2022-04-16 13:08:12');
INSERT INTO `attachment` VALUES (0x3230323230343136313233313537373130383332, '20220416123157710832.jpg', 'jpg', '40663', 'book_cover', NULL, '这是一个图书封面', 'http://localhost:8081/file/2022/20220416123157710832.jpg', 'E:/workSpaceidea/img/2022/', 'http://localhost:8081/file/2022/20220416123157710832.jpg', 1, NULL, NULL, 1, NULL, '2022-04-16 12:31:57', '2022-04-16 13:08:15');
INSERT INTO `attachment` VALUES (0x3230323230343136313430373232333133343131, '20220416140722313411.jpg', 'jpg', '37499', 'book_cover', NULL, '这是一个图书封面', 'http://localhost:8081/file/2022/20220416140722313411.jpg', 'E:/workSpaceidea/img/2022/', 'http://localhost:8081/file/2022/20220416140722313411.jpg', 1, NULL, NULL, 1, NULL, '2022-04-16 14:07:22', NULL);
INSERT INTO `attachment` VALUES (0x3230323230343136313435383138353032393836, '20220416145818502986.jpg', 'jpg', '15458', 'user', NULL, '这是一个头像', 'http://localhost:8081/file/2022/20220416145818502986.jpg', 'E:/workSpaceidea/img/2022/', 'http://localhost:8081/file/2022/20220416145818502986.jpg', 1, NULL, NULL, 1, NULL, '2022-04-16 14:58:18', '2022-04-16 14:58:43');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '书籍id',
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '书籍名称',
  `sub_title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '副标题',
  `origin_title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '源标题(国外源标题)',
  `author` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作者',
  `book_type` int NULL DEFAULT NULL COMMENT '书籍分类',
  `bindng` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '装订方式',
  `pages` int NULL DEFAULT NULL COMMENT '总页数',
  `attachment_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面id',
  `brand` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '丛书',
  `publisher` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '出版社名称',
  `pubdate` date NULL DEFAULT NULL COMMENT '发版日期',
  `isbn10` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '10位ISNB码',
  `isbn13` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '13为ISNB码',
  `summary` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '书籍简介',
  `original_texts` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '原文摘录',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '销售价格',
  `total` int NOT NULL COMMENT '书籍总量',
  `current_number` int NOT NULL COMMENT '现存数量（可借阅数量）',
  `likes` int NULL DEFAULT 0 COMMENT '推荐人数（点赞数）',
  `borrows` int NULL DEFAULT 0 COMMENT '借阅人数',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '书籍信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (42, '计算之魂', '计算科学品位和认知进阶', NULL, '吴军', 6, '平装', 263, '20220415221141989798', NULL, '人民邮电出版社', '1970-01-01', NULL, '9787115576170', '<p>对计算机科学的掌握程度，决定了一个计算机行业从业者能走多远。在本书中，作者将人文历史与计算机科学相结合，通过一些具体的例题，分10个主题系统地讲解了计算机科学的精髓。这些例题是作者面试求职者时用到的考题，或是头部计算机公司和金融企业的面试题。<br>作者在书中结合自己对计算机工程师的五级分级标准，详细讲解了每类题目不同层次的解决方法、不同水平的人大约能思考到什么样的深度，深度阐述题目背后的计算机科学精髓。通过对比优化解题思路，读者不仅可以衡量自己的水平，在职业的发展道路上走得更快，更可以深刻理解并掌握计算机科学和计算思维，逐渐把握计算机科学这门艺术，获得重复性成功。<br>对于所有有志于了解或学习科技，特别是计算机科学的人来讲，本书不仅有助于了解计算机科学，更有助于了解&nbsp;IT&nbsp;产业的技术特点、培养一些特殊的思维方式、掌握信息时代特殊的做事方法，通过具体的例子，从“术”的层面获得“道”的层面的提升。</p><p><br></p><p><br></p><p>吴军：学者，投资人，人工智能、语音识别和互联网搜索专家。毕业于清华大学和美国约翰·霍普金斯大学，现任丰元资本创始合伙人、上海交通大学客座教授、约翰·霍普金斯大学工学院董事等职。<br>吴军博士曾作为资深研究员和副总裁分别任职于Google&nbsp;公司和腾讯公司。在Google公司，他和同事一同开创了搜索反作弊研究领域，成立了中、日、韩文产品部门，设计了Google&nbsp;中、日、韩文搜索算法，领导了Google自然语言处理和自动问答等研究型项目，拥有近20项美国发明专利。在腾讯公司，他负责了搜索、搜索广告和街景地图等项目。作为风险投资人，他成功地投资了150家硅谷和中国的高科技企业。吴军博士对科技产业有深入的研究，是当今硅谷地区解读&nbsp;IT&nbsp;产业的专家。<br>吴军博士著有《数学之美》《浪潮之巅》《大学之路》《文明之光》《智能时代》《见识》《态度》《全球科技通史》和《信息传》等多部畅销书，并多次获得包括文津图书奖、中国好书奖、中华优秀出版物奖在内的图书大奖。</p>', '<p><br></p>', 109.00, 3, 3, 0, 0, NULL, '1', NULL, '2022-04-15 22:12:50');
INSERT INTO `book` VALUES (43, '程序员的数学（第2版）', NULL, NULL, '[日]结城浩-[译]管杰 / 卢晓南', 6, '平装', 262, '20220415222145053558', '图灵程序设计丛书·程序员的数学', '人民邮电出版社', '1970-01-01', NULL, '9787115504906', '<p>《程序员的数学&nbsp;第2版》面向程序员介绍了编程中常用的数学知识，借以培养初级程序员的数学思维。读者无须精通编程，也无须精通数学，只要具备四则运算和乘方等基础知识，即可阅读本书。<br>《程序员的数学&nbsp;第2版》讲解了二进制计数法、逻辑、余数、排列组合、递归、指数爆炸、不可解问题等许多与编程密切相关的数学方法，分析了哥尼斯堡七桥问题、高斯求和、汉诺塔、斐波那契数列等经典问题和算法。引导读者深入理解编程中的数学方法和思路。<br>《程序员的数学&nbsp;第2版》新增一个附录来介绍机器学习的基础知识，内容涉及感知器、损失函数、梯度下降法和神经网络，旨在带领读者走进机器学习的世界。</p><p><br></p><p><br></p><p>结城浩（作者）<br>生于1963年,日本知名技术作家和程序员。在编程语言、设计模式、数学、加密技术等领域,编写了很多深受欢迎的入门书。代表作有《数学女孩》系列、《程序员的数学》《图解密码技术》等。<br>管杰（译者）<br>毕业于复旦大学日语系，现为对日软件工程师，具有多年日语技术文档编写经验。爱好日汉翻译和日本文化史，译有《明解C语言：入门篇》等。<br>卢晓南（译者）<br>本科就读于西安交通大学少年班、数学系。名古屋大学博士（信息科学）。现于山梨大学计算机系任助理教授。主要研究方向包括组合数学（离散数学）及其在信息科学、计算机科学、统计学中的应用。译著有《程序员的数学3：线性代数》。</p>', NULL, 59.00, 2, 2, 0, 0, NULL, '1', NULL, '2022-04-15 22:22:05');
INSERT INTO `book` VALUES (44, '用户体验的要素', '以用户为中心的Web设计', NULL, 'Jesse James Garrett-[译]范晓燕', 6, '平装', 167, '20220415223207064711', NULL, '机械工业出版社', '2007-10-01', NULL, '9787111223108', '<p>这不是一本关于“怎样做（How-to）”的书。有很多很多讨论如何建设网站的书，这本不是。<br>这不是一本关于技术的书。在这里你找不到一行代码。<br>这不是一本有答案的书。相反，这本书说的是“如何提出正确的问题”。<br>这本书将告诉你，在你阅读其他书籍的之前，你需要提前了解什么。如果你需要一个大的概念，如果你需要了解用户体验设计师所做出的决策的环境，这本书很适合你。<br>这本书经过精心设计，使你可以在一两个小时之内读完。如果你是一个刚刚进入用户体验领域的新手─可能你是一个负责组建用户体验团队的管理人员，或者你是一个碰巧进入这个领域的作家或设计师─那么这本书将给你一些基础的概念。如果你已经对这些方法和用户体验领域的关注点很熟悉了，那么这本书将帮助你更有效地把这些概念传达给与你合作的人们。<br>本书是AJAX之父的经典之作。本书用简洁的语言系统化地诠释了设计、技术和商业融合是最重要的发展趋势。全书共8章，包括关于用户体验以及为什么它如此重要、认识这些要素、战略层、范围层、结构层、框架层、表现层以及要素的应用。..<br>全书用清晰的说明和生动的图形分析了以用户为中心的设计方法（UCD）来进行网站设计的复杂内涵，并关注于思路而不是工具或技术，从而使你的网站具备高质量体验的流程。<br>聪明的企业意识到网页设计远远比创建条理清晰的代码和鲜明的图形重要多得多。一个网站在满足您的战略目标的同时，还应该满足您的用户需求。如果没有一个“有凝聚力、统一的用户体验”来支持的话，即使最好的内容和最精密的技术也不能帮助您平衡这些目标。<br>但是创建用户体验看上去是出乎意料的复杂。有很多方面都要考虑到──可用性、品牌识别、信息架构、交互设计──这使得“建立一个成功的网站的唯一方法”似乎就是花大价钱请那些完全了解这些细节的专家来做。<br>本书用清晰的说明和生动的图形分析了“以用户为中心的设计方法（UCD）”来进行网站设计的复杂内涵，并关注于思路而不是工具或技术。Jesse&nbsp;James&nbsp;Garrett给了读者一个关于“网站用户体验开发的总体概念”，从战略和信息架构需求到视觉设计。这种简单易学的介绍方法有助于任何一个网站开发团队，大的或小的，去创建一个成功的用户体验。</p><p><br></p><p><br></p><p>Jesse&nbsp;James&nbsp;Garrett&nbsp;是用户体验咨询公司Adaptive&nbsp;Path的创始人之一。从用户体验要素“在2000年3月初次发布到网上以来，Jesse的所绘制的这个模型已经被下载了2万多次。Jesse的互联网从业经验包括AT&T、Intel、Boeing、Motorola、Hewlett-packard以及和美国国家公众广播等。他在用户体验领域的贡献包括”视觉词典（the&nbsp;Visual&nbsp;Vocabulary）“，一个为规范信息架构文档而建立的开放符号系统，现在这个系统在全球各个企业中得到广泛的应用。他的个人网站&nbsp;www.jjg.net&nbsp;是提供信息架构资源的网站中最受欢迎的一个.<br>译者：范晓燕。UCDChina发起人,从1997年开始从事互联网相关工作,拥有超过10年的互联网从业经验,现从事用户体验研究.分析,以及互联网产品的设计和管理工作.推祟“以用户为中心（UCD）”的设计思想,是用户体验设计的积极推广者和实践者.个人博客：http://ucdchina.com/angela</p>', '<p><br></p>', 25.00, 2, 2, 0, 0, NULL, '1', NULL, '2022-04-15 22:32:51');
INSERT INTO `book` VALUES (45, 'Web表单设计', '点石成金的艺术', NULL, '[美] Luke Wroblewski-[译]卢颐 / 高韵蓓', 6, '平装', 220, '20220415223340828445', '优秀设计师典藏·UCD经典译丛', '清华大学出版社', '1970-01-01', NULL, '9787302225157', '<p>精心设计的表单，能让用户感到心情舒畅，愉快地注册、付款和进行内容创建和管理，这是促成网上商业成功的秘密武器。本书通过独到、深邃的见解，丰富、真实的实例，道出了表单设计的真谛。新手设计师通过阅读本书，可广泛接触到优秀表单设计的所有构成要素。经验丰富的资深设计师，可深入地了解以前没有注意到的问题及解决方案。<br>本书专为表单设计或开发人员准备，但同时也适合可用性工程师、网站开发人员、产品经理、视觉设计师、交互设计师、信息架构人员以及任何一个涉及表单与调查问卷设计的人员阅读和参考。</p><p><br></p><p><br></p><p>Luke&nbsp;Wroblewski：一切都始于大学一年级。那时候，我下午上艺术课，深夜“猫”在机房写代码，完成计算机作业。<br>从那时起，我就萌发了平衡表单与功能的想法。抱着这种想法，我去了国家超级计算机应用中心(National&nbsp;Center&nbsp;for&nbsp;Supercomputing&nbsp;Application，NCSA)工作，那里诞生了第一代大众图形界面的网络浏览器NCSA&nbsp;Mosaic。互联网通过设计和技术产生用户体验。我那时满脑子想的都这些。</p>', NULL, 49.00, 2, 2, 0, 0, NULL, '2', NULL, '2022-04-15 22:33:50');
INSERT INTO `book` VALUES (46, '一目了然', 'Web软件显性设计之路', NULL, 'ROBERT HOEKMAN-[译]何潇', 6, '平装', 187, '20220415223429060686', NULL, '机械工业出版社', '1970-01-01', NULL, '9787111223627', '<p>《一目了然Web软件显性设计之路》通过深入浅出地讲解与丰富的实际案例，帮助读者理解、掌握显性设计的精髓，可轻松熟练地设计出简单易用的基于Web的软件。.<br>我们的用户在众多的竞争中选择了我们基于Web的软件，并投入了他们的金钱和宝贵的时间。作为回报，他们希望能满足需求并推动工作而不感到乏味。事实上，用户应该在离开电脑时仍能感到系统会有效、自觉、敏捷地运转!..<br>幸运的是，这一切都是可能的，而且并不复杂。我们只需要从显性设计起步，做到以下几点：<br>·提供用户所需。<br>·让初级用户迅速转变为中级用户。<br>·尽可能地防止出错，如出错也应友善处理。<br>·精简和优化交互与任务流程，让最复杂的软件也能变得清晰可理解。<br>·为支持特定行为而设计。<br>·持续优化我们的流程与程序。</p>', NULL, 39.00, 1, 1, 0, 0, NULL, '2', NULL, '2022-04-15 22:34:44');
INSERT INTO `book` VALUES (47, '重构（第2版）', '改善既有代码的设计', NULL, '[美] Martin Fowler-[译]熊节 / 林从羽', 6, '精装', 422, '20220415223751499432', '异步图书-程序员必读经典系列', '人民邮电出版社', '1970-01-01', NULL, '9787115508645', '<p>本书是经典著作《重构》出版20年后的更新版。书中清晰揭示了重构的过程，解释了重构的原理和实践方式，并给出了何时以及何地应该开始挖掘代码以求改善。书中给出了60多个可行的重构，每个重构都介绍了一种经过验证的代码变换手法的动机和技术。本书提出的重构准则将帮助开发人员一次一小步地修改代码，从而减少了开发过程中的风险。<br>本书适合软件开发人员、项目管理人员等阅读，也可作为高等院校计算机及相关专业师生的参考读物。</p><p><br></p><p><br></p><p>作者&nbsp;|&nbsp;Martin&nbsp;Fowler<br>世界软件开发大师，ThoughtWorks的首席科学家。他是一位作家、演说者、咨询师和泛软件开发领域的意见领袖。他致力于改善企业级的软件设计，对优秀的设计以及支撑优秀设计的工程实践孜孜以求。他在重构、面向对象分析设计、模式、XP和UML等领域都有卓越贡献。著有《重构》《分析模式》《领域特定语言》等经典著作。<br>译者&nbsp;|&nbsp;熊节<br>在IT行业已经打拼了18年，在金融、零售、政府、电信、制造业等行业的信息化建设方面有着丰富经验，是中国IT业敏捷浪潮的领军人物。熊节拥有利物浦大学MBA学位。<br>译者&nbsp;|&nbsp;林从羽<br>ThoughtWorks软件开发工程师，曾服务于国内外多家大型企业，致力于为团队更快更好地交付可工作的软件。拥抱敏捷精神，TDD爱好者，纯键盘工作者。</p>', NULL, 168.00, 2, 2, 0, 0, NULL, '2', NULL, '2022-04-15 22:38:00');
INSERT INTO `book` VALUES (48, 'Python编程', '从入门到实践（第2版）', NULL, '[美]埃里克·马瑟斯（Eric Matthes）-[译]袁国忠', 6, '平装', 476, '20220415223959728081', '图灵程序设计丛书·Python系列', '人民邮电出版社', '1970-01-01', NULL, '9787115546081', '<p>本书是针对所有层次Python读者而作的Python入门书。全书分两部分：第一部分介绍用Python编程所必须了解的基本概念，包括Matplotlib等强大的Python库和工具，以及列表、字典、if语句、类、文件与异常、代码测试等内容；第二部分将理论付诸实践，讲解如何开发三个项目，包括简单的2D游戏、利用数据生成交互式的信息图以及创建和定制简单的Web应用，并帮助读者解决常见编程问题和困惑。第2版进行了全面修订，简化了Python安装流程，新增了f字符串、get()方法等内容，并且在项目中使用了Plotly库以及新版本的Django和Bootstrap，等等。</p><p><br></p><p><br></p><p>埃里克·马瑟斯（Eric&nbsp;Matthes）<br>高中科学和数学老师，现居住在阿拉斯加，在当地讲授Python入门课程。他从5岁开始就一直在编写程序。</p>', NULL, 89.00, 2, 2, 0, 0, NULL, '2', NULL, '2022-04-15 22:40:08');
INSERT INTO `book` VALUES (49, '数据密集型应用系统设计', NULL, NULL, 'Martin Kleppmann-[译]赵军平 / 李三平 / 吕云松 / 耿煜', 6, '平装', 519, '20220415230617875904', 'O\'Reilly动物系列（中译本）', '中国电力出版社', '1970-01-01', NULL, '9787519821968', '<p>全书分为三大部分：<br>第一部分，主要讨论有关增强数据密集型应用系统所需的若干基本原则。首先开篇第1章即瞄准目标：可靠性、可扩展性与可维护性，如何认识这些问题以及如何达成目标。第2章我们比较了多种不同的数据模型和查询语言，讨论各自的适用场景。接下来第3章主要针对存储引擎，即数据库是如何安排磁盘结构从而提高检索效率。第4章转向数据编码（序列化）方面，包括常见模式的演化历程。<br>第二部分，我们将从单机的数据存储转向跨机器的分布式系统，这是扩展性的重要一步，但随之而来的是各种挑战。所以将依次讨论数据远程复制（第5章）、数据分区（第6章）以及事务（第7章）。接下来的第8章包括分布式系统的更多细节，以及分布式环境如何达成一致性与共识（第9章）。<br>第三部分，主要针对产生派生数据的系统，所谓派生数据主要指在异构系统中，如果无法用一个数据源来解决所有问题，那么一种自然的方式就是集成多个不同的数据库、缓存模块以及索引模块等。首先第10章以批处理开始来处理派生数据，紧接着第11章采用流式处理。第12章总结之前介绍的多种技术，并分析讨论未来构建可靠、可扩展和可维护应用系统可能的新方向或方法。</p><p><br></p><p><br></p><p>作者简介<br>Martin&nbsp;Kleppmann是英国剑桥大学分布式系统方向的研究员。此前，他曾是LinkedIn和Rapportive等互联网公司的软件工程师，负责大规模数据基础设施建设。在此过程中他遇到过一些困难，因此他希望这本书能够帮助读者避免重蹈覆辙。Martin还是一位活跃的会议演讲者、博主和开源贡献者。他认为，每个人都应该学习深刻的技术理念，对技术的深入理解能帮助我们开发出更好的软件。<br>译者简介<br>赵军平,&nbsp;大数据存储与分析资深开发者与推广者（EMC&nbsp;10余年），GPU异构计算的亲历者。中国计算机协会专家委员，DELL&nbsp;EMC资深架构师。12年系统研发、创新与团队管理经验，擅长数据存储与保护,&nbsp;云计算与大数据实时分析，GPU异构加速优化等。相关领域已申请中、美技术专利100余项，并多次在SNIA，LinuxConf，Hadoop&nbsp;Summit,&nbsp;Nvidia&nbsp;GPU&nbsp;Tech&nbsp;Conf等做技术分享，持续关注数据密集和计算密集相关技术的演进、融合与赋能推广。<br>吕云松，北京大学计算机硕士，硕士及DELL&nbsp;EMC中国研究院实习期间专注于大数据实时流式处理相关的研究。现就职于华为2012中软院黎曼实验室，主要从事深度学习的研发。<br>耿煜，DELL&nbsp;EMC架构师兼GTM负责人，致力于推广企业级数字化转型方案。深耕分布式架构以及云计算12年，先后任职于ChinaCache，Sun&nbsp;Microsystems以及EMC等公司。<br>李三平，美国麻省大学计算机工程专业博士，DELL&nbsp;EMC中国研究院首席科学家，研究方向为机器学习、深度学习、智能运维、遥感影像等。已在IEEE&nbsp;Transactions期刊和会议上发表论文数十篇，申请美国专利20余项。推崇简约，热衷机器学习。</p>', NULL, 128.00, 4, 4, 0, 0, NULL, '2', NULL, '2022-04-15 23:06:39');
INSERT INTO `book` VALUES (50, '活着', NULL, NULL, '余华', 1, '平装', 191, '20220415230725426788', '余华作品（2012版）', '作家出版社', '1970-01-01', NULL, '9787506365437', '<p>《活着(新版)》讲述了农村人福贵悲惨的人生遭遇。福贵本是个阔少爷，可他嗜赌如命，终于赌光了家业，一贫如洗。他的父亲被他活活气死，母亲则在穷困中患了重病，福贵前去求药，却在途中被国民党抓去当壮丁。经过几番波折回到家里，才知道母亲早已去世，妻子家珍含辛茹苦地养大两个儿女。此后更加悲惨的命运一次又一次降临到福贵身上，他的妻子、儿女和孙子相继死去，最后只剩福贵和一头老牛相依为命，但老人依旧活着，仿佛比往日更加洒脱与坚强。<br>《活着(新版)》荣获意大利格林扎纳•卡佛文学奖最高奖项（1998年）、台湾《中国时报》10本好书奖（1994年）、香港“博益”15本好书奖（1994年）、第三届世界华文“冰心文学奖”（2002年），入选香港《亚洲周刊》评选的“20世纪中文小说百年百强”、中国百位批评家和文学编辑评选的“20世纪90年代最有影响的10部作品”。</p><p><br></p><p><br></p><p>余华，1960年出生，1983年开始写作。至今已经出版长篇小说4部，中短篇小说集6部，随笔集4部。主要作品有《兄弟》《活着》《许三观卖血记》《在细雨中呼喊》等。其作品已被翻译成20多种语言在美国、英国、法国、德国、意大利、西班牙、荷兰、瑞典、挪威、希腊、俄罗斯、保加利亚、匈牙利、捷克、塞尔维亚、斯洛伐克、波兰、巴西、以色列、日本、韩国、越南、泰国和印度等国出版。曾获意大利格林扎纳·卡佛文学奖（1998年）、法国文学和艺术骑士勋章（2004年）、中华图书特殊贡献奖（2005年）、法国国际信使外国小说奖（2008年）等。</p>', NULL, 20.00, 3, 3, 0, 0, NULL, '2', NULL, '2022-04-15 23:07:37');
INSERT INTO `book` VALUES (51, '秋园', NULL, NULL, '杨本芬', 1, '平装', 280, '20220415232506710914', '看见女性三重奏', '北京联合出版公司', '1970-01-01', NULL, '9787559640666', '<p>听八旬奶奶讲述她和妈妈的故事。<br>1914年，世上有了“秋园”这个人。1918年，汉语有了“她”这个字。秋园，她来过，挣扎过，绝望过，幸福过。今天，她80岁的女儿，把普普通通的她，讲给世界听。<br>“我写了一位普通中国女性一生的故事，写了我们一家人如何像水中的浮木般挣扎求生，写了中南腹地那些乡间人物的生生死死。我知道自己写出的故事如同一滴水，最终将汇入人类历史的长河。”</p><p><br></p><p><br></p><p>杨本芬，1940年出生于湖南湘阴，17岁考入湘阴工业学校，后进入江西共大分校，未及毕业即下放江西农村。此后数十年为生计奔忙，相夫教子，后从某汽车运输公司退休。花甲之年开始写作，在《红豆》《滇池》等刊物上发表过短篇小说。</p>', NULL, 38.00, 6, 6, 0, 0, NULL, '2', NULL, '2022-04-15 23:25:17');
INSERT INTO `book` VALUES (52, '文学课', NULL, NULL, '[阿根廷] 胡里奥·科塔萨尔-[译]林叶青', 1, '精装', 345, '20220415232617599831', '新经典文库：胡里奥·科塔萨尔作品', '南海出版公司', '1970-01-01', NULL, '9787544269155', '<p>★拉美文学巨匠胡里奥·科塔萨尔&nbsp;打破常规的大师文学课&nbsp;简体中文首度引进<br>★加西亚·马尔克斯、聂鲁达、萨拉马戈、略萨、莫言&nbsp;5位诺贝尔文学奖得主&nbsp;齐声推崇的作家<br>★适合所有爱故事的人，写作者必读之书！<br>★这是一个邀请，邀请读者参与伟大的文学游戏。<br>★一本书了解天马行空的科塔萨尔和拉美文学，清晰明了，有料有趣。<br>忠实刻录&nbsp;8堂大师文学课&nbsp;+&nbsp;2场公开讲座，干货满满<br>细致解读+名篇实例分析+疑难解答+创作指导，写给所有阅读者和创作者，在《文学课》找到创作与故事的答案！<br>-----------------------------------------------------------------------<br>一九八〇年，科塔萨尔受邀前往加州大学伯克利分校，教授一门为期两个月的文学课。<br>本书由当时作家八场讲座的录音转写而成。天性顽皮的他和学生“合伙”做起了游戏，要将文学讲得趣味十足、与生活现实息息相关。十三个小时的座谈内容揭示了科塔萨尔的写作秘密：对幻想故事的解读，文学中的音乐性、幽默感、游戏性，幻想与现实主义，对自己作品和创作之路的分析……科塔萨尔再一次施展魔力，将文学课也变成一场郑重其事的游戏。<br>《文学课》是一个邀请，伟大的作家科塔萨尔郑重地邀请您参与其中。</p><p><br></p><p><br></p><p>胡里奥·科塔萨尔（Julio&nbsp;Cortázar）<br>阿根廷著名作家，拉丁美洲“文学爆炸”代表人物。1914年生于比利时，在阿根廷长大，1951年移居法国巴黎。著有长篇小说《跳房子》，短篇小说集《动物寓言集》《游戏的终结》《万火归一》《我们如此热爱格伦达》等。1984年在巴黎病逝。<br>科塔萨尔被认为是20世纪最具实验精神的伟大作家。《西语美洲文学史》的作者奥维耶多说：“每当想到科塔萨尔的名字，人们脑海中浮现的第一个词是：迷人。”</p>', NULL, 69.00, 10, 10, 0, 0, NULL, '2', NULL, '2022-04-15 23:26:35');
INSERT INTO `book` VALUES (53, '卡拉马佐夫兄弟', NULL, NULL, '陀思妥耶夫斯基 (Ф.М.Достоевский)-[译]荣如德', 1, '平装', 985, '20220415233057178064', '陀思妥耶夫斯基文集', '上海译文出版社', '1970-01-01', NULL, '9787532763696', '<p>本书系俄国大作家陀思妥耶夫斯基代表作之一。小说通过一桩真实的弑父案，描写老卡拉马佐夫同三个儿子即两代人之间的尖锐冲突。老卡拉马佐夫贪婪好色，独占妻子留给儿子们的遗产，并与长子德米特里为一个风流女子争风吃醋。一天黑夜，德米特里疑心自己的情人去跟老头儿幽会，便闯入家园，一怒之下，差点儿把老头儿砸死。他仓皇逃离后，躲在暗中装病的老卡拉马佐夫的私生子斯乜尔加科夫，悄然杀死老爷，造成了一桩震惊全俄的扑朔迷离的血案，从而引发了一连串惊心动魄的事件。作品展示一个错综复杂的社会家庭、道德和人性的悲剧主题，体现了作家一生的最高艺术成就。</p><p><br></p><p><br></p><p>陀思妥耶夫斯基(1821-1881)，与大文豪列夫·托尔斯泰、屠格涅夫等人齐名，是俄国文学的卓越代表，他走的是一条极为艰辛、复杂的生活与创作道路，是俄国文学史上最复杂、最矛盾的，作家之一。他的创作独具特色，在群星灿烂的19世纪俄国文坛上独树一帜，占有着十分特殊的地位。主要作品有《穷人》、《被侮辱与被损害的》、《死屋手记》、《罪与罚》、《白痴》、《群魔》、《卡拉马佐夫兄弟》等。</p>', NULL, 0.00, 5, 5, 0, 0, NULL, '2', NULL, '2022-04-15 23:31:16');
INSERT INTO `book` VALUES (54, '万千微尘纷坠心田', '文学阅读的生命化', NULL, '张秋子', 1, '平装', 199, '20220415233400820630', NULL, '新星出版社', '1970-01-01', NULL, '9787513344791', '<p><br></p><p><br></p><p><br></p><p>张秋子，云南昆明人，南开大学比较文学与世界文学博士，现为云南师范大学文学院教师。比起“教外国文学的”或“专业读者”，更想做“向绝大多数人打开文本的人”。</p>', NULL, 42.00, 10, 10, 0, 0, NULL, '2', NULL, '2022-04-15 23:34:14');
INSERT INTO `book` VALUES (55, '局外人', NULL, NULL, '[法] 阿尔贝·加缪-[译]柳鸣九', 1, '精装', 128, '20220415233504901499', '译文经典（精装本）', '上海译文出版社', '1970-01-01', NULL, '9787532751471', '<p>《局外人》是法国作家加缪的成名作，同时也是存在主义文学的杰出作品之一，该书以一种客观记录式的“零度风格”，粗线条地描述了主人公默尔索在荒谬的世界中经历的种种荒谬的事，以及自身的荒诞体验。从参加母亲的葬礼到偶然成了杀人犯，再到被判处死刑，默尔索似乎对一切都无动于衷，他像一个象征性的符号，代表了一种普遍的存在，又像是一个血红色的灯塔，具有高度的警示性。然而，局外人现象的产生无疑是由这个世界本身所孕育的，默尔索的存在有其深刻的外部原因。</p><p><br></p><p><br></p><p>阿尔贝·加缪（Albert&nbsp;Camus，1913—1960），法国声名卓著的小说家、散文家和剧作家，“存在主义”文学的大师。1957年因“热情而冷静地阐明了当代向人类良知提出的种种问题”而获诺贝尔文学奖，是有史以来最年轻的诺奖获奖作家之一。加缪在他的小说、戏剧、随笔和论著中深刻地揭示出人在异己的世界中的孤独、个人与自身的日益异化，以及罪恶和死亡的不可避免，但他在揭示出世界的荒诞的同时却并不绝望和颓丧，他主张要在荒诞中奋起反抗，在绝望中坚持真理和正义，他为世人指出了一条基督教和马克思主义以外的自由人道主义道路。他直面惨淡人生的勇气，他“知其不可而为之”的大无畏精神使他在第二次世界大战之后不仅在法国，而且在欧洲并最终在全世界成为他那一代人的代言人和下一代人的精神导师。</p>', NULL, 22.00, 10, 10, 0, 0, NULL, '2', NULL, '2022-04-15 23:35:13');
INSERT INTO `book` VALUES (56, '霍乱时期的爱情', NULL, NULL, '[哥伦比亚] 加西亚·马尔克斯-[译]杨玲', 1, '精装', 401, '20220416140722313411', '新经典文库：加西亚·马尔克斯作品', '南海出版公司', '1970-01-01', NULL, '9787544258975', '<p>★马尔克斯唯一正式授权，首次完整翻译<br>★《霍乱时期的爱情》是我最好的作品，是我发自内心的创作。——加西亚•马尔克斯<br>★这部光芒闪耀、令人心碎的作品是人类有史以来最伟大的爱情小说。——《纽约时报》<br>《霍乱时期的爱情》是加西亚•马尔克斯获得诺贝尔文学奖之后完成的第一部小说。讲述了一段跨越半个多世纪的爱情史诗，穷尽了所有爱情的可能性：忠贞的、隐秘的、粗暴的、羞怯的、柏拉图式的、放荡的、转瞬即逝的、生死相依的……再现了时光的无情流逝，被誉为“人类有史以来最伟大的爱情小说”，是20世纪最重要的经典文学巨著之一。</p><p><br></p><p><br></p><p>加西亚•马尔克斯（Gabriel&nbsp;García&nbsp;Márquez）<br>1927年出生于哥伦比亚马格达莱纳海滨小镇阿拉卡塔卡。童年与外祖父母一起生活。1936年随父母迁居苏克雷。1947年考入波哥大国立大学。1948年因内战辍学，进入报界。五十年代开始出版文学作品。六十年代初移居墨西哥。1967年《百年孤独》问世。1982年获诺贝尔文学奖。1985年出版《霍乱时期的爱情》。<br>加西亚•马尔克斯豆瓣小站：http://site.douban.com/marquez/</p>', NULL, 39.50, 20, 20, 0, 0, '1', '2', '2022-04-16 14:07:26', '2022-04-15 23:46:30');
INSERT INTO `book` VALUES (57, '1984', NULL, NULL, '[英] 乔治·奥威尔-[译]刘绍铭', 1, '精装', 304, '20220415234822893964', '新经典文库：桂冠文丛', '北京十月文艺出版社', '1970-01-01', NULL, '9787530210291', '<p>★村上春树以《1Q84》向本书致敬<br>★著名学者刘绍铭经典译本内地首次出版<br>★62种文字风靡110个国家，全球销量超过5000万册<br>★《时代周刊》“最好的100本英语小说”<br>★兰登书屋“100本20世纪最佳英语小说”<br>★入选英美中学生必读书书目<br>1936年以来，我所写的每&nbsp;一部严肃作品，都是直接或间接地反对极权主义，支持我所理解的民主社会主义。&nbsp;——乔治•奥威尔（《我为何写作》）<br>《1984》是一部杰出的政治寓言小说，也是一部幻想小说。作品刻画了人类在极权主义社会的生存状态，有若一个永不褪色的警示标签，警醒世人提防这种预想中的黑暗成为现实。历经几十年，其生命力益显强大，被誉为20世纪影响最为深远的文学经典之一。</p><p><br></p><p><br></p><p>乔治•奥威尔（1903-1950），&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;英国著名作家。主要作品有《动物农场》、《1984》等。<br>1903年出生于印度，1907年举家迁回英国，后因经济原因无力深造，被迫远走缅甸，参加帝国警察部队。终因厌倦殖民行径、痴迷写作而辞去公职，辗转回到欧洲，流亡伦敦、巴黎等地，一边深刻体验下层民众生活，一遍从事文学创作，有多部作品出版。1936年西班牙内战爆发，参加左翼组织，因重伤返回英国，但最终树立社会主义信念。1939年，二战爆发，积极参加反纳粹的活动。由于西班牙内战与二战的苦痛经历，对战争与和平、极权与民主、社会关怀与人类理想进行了深刻的思考。<br>1945年，著名的政治寓言小说《动物农场》出版。1949年，《1984》问世，在全球产生深远影响。作品入选多国中学生必读书目，被翻译成62种文字，总销量超过5000万册。<br>1950年，因肺病去世，年仅47岁。</p>', NULL, 28.00, 10, 10, 0, 0, NULL, '2', NULL, '2022-04-15 23:48:30');
INSERT INTO `book` VALUES (58, '如何成为优秀的大脑饲养员', '让我们精力更足、记性更好、情绪更佳的健脑饮食全书', NULL, '[意] 丽莎·莫斯考尼-[译]康洁', 1, '平装', 368, '20220415235245101120', NULL, '九州出版社', '1970-01-01', NULL, '9787522506449', '<p>13种语言版本席卷全球，4国亚马逊高分推荐<br>80项全面自测+24道健脑美餐+11层大脑膳食金字塔<br>挑选食物时，比起琢磨肌肉、脂肪的偏好，我们更该问问大脑爱吃什么<br>◎&nbsp;编辑推荐<br>☆&nbsp;蜚声国际的作者，广受认可的巨作<br>作者被誉为神经科学界的蒙娜丽莎，在世最具影响力的17位女科学家之一，于领域内深耕十五年，将故乡意大利的饮食模式与自身科研成果融为一体，此外，她还曾出镜NBS、TED，与观众分享前沿知识。<br>本书授权15种语言版本，美亚、英亚、澳亚等多国图书平台评分4.6分以上，《泰晤士报》《卫报》《洛杉矶时报》《华尔街日报》等多家媒体击节赞叹。<br>★&nbsp;易被忽视的角度，特别简单的贴心内容<br>提到饮食，人们会考虑哪些食物能让自己皮肤更好、身材更棒，但却很少思考食物与我们的精力和脑力有何关联，本书立足七大营养素，从零开始一一介绍，帮助读者吃得更聪明。除了浅显易懂的讲解，作者还代画重点，将富含各类营养素的几大食物制成图表，让人一目了然。<br>☆&nbsp;大量能够立刻上手的测试和菜谱<br>除了获取知识，读者还可以马上动起手来，通过80道选择题，测出自身当下状态，然后再按照对应的级别规划自己的一日三餐。在书中，作者还分享了她日常所做的快手菜，帮助我们更好地坚持下来。<br>◎&nbsp;内容简介<br>大脑，这个令我们体味到酸甜苦辣、酥麻咸鲜的黑匣子，其实也会饿会渴。和身体的其他部位一样，它也期待着大自然的珍馐的呵护，但除此之外，它还有自己独特的需求。我们虽然理智上认可大脑比肌肉和脂肪更重要，却很少考虑为了大脑我们应该吃什么。<br>丽莎·莫斯考尼博士从脑科学、微生物组和营养基因组学等多种深奥的专业领域出发，整合了数百名科学家的研究结果和自己的独家成果，用一种浅显有趣的方式为我们提供了完整的食物计划建议，还带来了各种你一定想知道的知识，比如关于麸质和菌群，科学界又有哪些新的见解？世界各地的饮食模式有何可借鉴之处，她的家乡意大利又有哪些独特的健脑餐？以及如何在周末简单快速地准备好下一周的健脑美食。<br>除了深入浅出的前沿研究成果，本书也提供了能立刻上手的操作指南，包括全面的测试、富含各项营养的食物榜单、每周食谱和详细的菜谱。就让我们从下一餐、下一口食物开始，守护大脑的健康，做个优秀的大脑饲养员吧！</p><p><br></p><p><br></p><p>丽莎·莫斯考尼（Lisa&nbsp;Mosconi）博士<br>“神经科学界的蒙娜丽莎”“在世蕞具影响力的17位女科学家之一”<br>纽约大学营养与脑部健康实验室创始人及负责人，威尔康奈尔医学院阿尔茨海默病预防中心副主任，专注神经科学/脑科学研究15年，CNN、&nbsp;CBS、&nbsp;NBC、美联社、路透社、《纽约时报》、《华盛顿邮报》、《福布斯》、《华尔街日报》等80多家国际媒体都对她做过报道，她的ted演讲也深受观众喜爱，拥有近300万次播放量。</p>', NULL, 62.00, 10, 10, 0, 0, NULL, '2', NULL, '2022-04-15 23:53:00');
INSERT INTO `book` VALUES (59, '走出帝国', '王清福的故事', NULL, '[美] 苏思纲-[译]卢欣渝', 1, '精装', 424, '20220416123157710832', '汗青堂', '上海文化出版社', '1970-01-01', NULL, '9787553522999', '<p>汹涌的排华浪潮中，他孤身鏖战，誓死捍卫中国人的尊严。<br>★编辑推荐<br>◎&nbsp;哪有什么与生俱来的权利，沉默换不到公平，唯有抗争才能赢得尊重！<br>◎&nbsp;在美国汹涌的排华浪潮中，中国人在美国社会被排挤到了人下人的地步。绝大多数人听天由命，王清福却誓言抗争到底。他写文章，办报纸，宣扬中华文化；他在&nbsp;各地巡回演讲，与反华分子辩论，捍卫华人尊严；他在美国国会作证，成立民间组织，争取华人权益。他是我们不应忘记的华人平权运动先驱，是当之无愧的“黄皮肤的马丁·路德·金”。<br>★学者推荐<br>◎王清福是美国早期引人注目的华人权益倡导者，但现在知之者甚少。苏思纲将王清福的生平呈现在世人眼前，并将其置于更大的背景之下，包括近代中国的经济、美国的排华立法和新闻媒体。苏思纲是一位细致的研究者和出色的作家，曾旅居中国多年，通晓中文。他查阅了大量此前未被人重视的史料，为我们献上了这本生动有趣的著作。它将大大丰富我们对19世纪末20世纪初美国华人历史的认识。<br>——陈国维，纽约大学亚太裔美国人研究中心主任<br>◎我们应该感谢苏思纲，他使王清福不再被人遗忘。他从历史的故纸堆中翻捡出大量材料，写出了一本内容翔实、可读性强、鼓舞人心的传记。通过本书，我们终于听到了美国华人的真实声音。它不仅是给华裔的礼物，也是给所有美国人的礼物。<br>——王灵智，加利福尼亚大学伯克利分校荣休教授<br>◎苏思纲在对中英文资料详尽研究的基础之上，讲述了这个精力充沛、意志坚定、不屈不挠的人的精彩故事，以及他入籍美国的历史背景。苏思纲是一位技艺精湛的历史学家和作家，他很好地说明了王清福对美国建国原则的信念、对华人身份的认同，以及在非常困难的条件下为公平和平等进行的顽强斗争。这是一本引人入胜、值得一读的书。<br>——于仁秋，纽约州立大学帕切斯学院教授<br>★内容简介<br>本书讲述了美国华人民权运动家王清福为同胞争取平等权利而不懈奋斗的精彩一生。19世纪末，美国经济下滑，失业率高企，部分美国人将此归咎于华人劳工，排华势力逐渐抬头，美国国会最终通过了《排华法案》。王清福为了捍卫华人权益，在美国各大报刊发表文章，四处演讲，同反华人士公开辩论，积极推介中国传统文化，纠正美国社会对华人的偏见。他是美国华人平权运动的先驱，被誉为“华人的马丁·路德·金”。</p><p><br></p><p><br></p><p>苏思纲（Scott&nbsp;D.&nbsp;Seligman），美国作家、历史学家，本科就读于普林斯顿大学历史系，获哈佛大学硕士学位。他能讲流利的汉语普通话和粤语，曾旅居中国八年。主要著作有《堂斗》《梅氏三雄》《中国商业礼仪》等。<br>★译者简介<br>卢欣渝，资深英美文学、社科图书翻译，已出版的主要译作有&nbsp;《美国公子》（Less&nbsp;Than&nbsp;Zero）、《英雄与败类》（One&nbsp;Police&nbsp;Plaza）、《鲁滨逊飘流记》（Robinson&nbsp;Crusoe）、《重新出山》（Licence&nbsp;Renewed）、《移情无悔》（Under&nbsp;the&nbsp;Covers）、《临界地带》（Borderlands）、《战争的果实》（The&nbsp;Fruits&nbsp;of&nbsp;War）、《智商测试》（IQ）、《美国阴谋》（American&nbsp;Conspiracies）、《追寻美之音符》（Notes&nbsp;on&nbsp;the&nbsp;Need&nbsp;for&nbsp;Beauty）、《凯恩与阿贝尔》（Kane&nbsp;and&nbsp;Abel）、《旅人迟归》（A&nbsp;Native\'s&nbsp;Return）、《图说古今帝王》（5000&nbsp;Years&nbsp;of&nbsp;Royalty）、《度量世界》（World&nbsp;in&nbsp;the&nbsp;Balance）等。</p>', NULL, 86.00, 10, 10, 0, 0, NULL, '1', NULL, '2022-04-16 12:32:16');

-- ----------------------------
-- Table structure for book_tag
-- ----------------------------
DROP TABLE IF EXISTS `book_tag`;
CREATE TABLE `book_tag`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `tag_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签名字',
  `tag_state` tinyint NULL DEFAULT 1 COMMENT '标签状态：\r\n1（待审核状态）\r\n2（正常状态）',
  `update_by` int NULL DEFAULT NULL COMMENT '更新者',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '书籍标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_tag
-- ----------------------------
INSERT INTO `book_tag` VALUES (1, '文学', 1, 2, 2, '2022-04-07 16:38:33', '2022-04-07 13:54:08');
INSERT INTO `book_tag` VALUES (2, '小说', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 13:54:22');
INSERT INTO `book_tag` VALUES (3, '中国文学', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 13:54:36');
INSERT INTO `book_tag` VALUES (4, '儿童文学', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 13:54:51');
INSERT INTO `book_tag` VALUES (5, '当代文学', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 13:55:00');
INSERT INTO `book_tag` VALUES (6, '外国文学', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 13:55:21');
INSERT INTO `book_tag` VALUES (7, '日本文学', 1, NULL, 2, NULL, '2022-04-14 23:41:14');
INSERT INTO `book_tag` VALUES (8, '经典', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 13:55:32');
INSERT INTO `book_tag` VALUES (9, '散文', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 13:55:38');
INSERT INTO `book_tag` VALUES (10, '外国名著', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 13:56:02');
INSERT INTO `book_tag` VALUES (11, '诗词', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 13:56:27');
INSERT INTO `book_tag` VALUES (12, '鲁迅', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 13:56:45');
INSERT INTO `book_tag` VALUES (13, '港台', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 13:57:51');
INSERT INTO `book_tag` VALUES (14, '张爱玲', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 13:57:57');
INSERT INTO `book_tag` VALUES (15, '杂文', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 13:58:03');
INSERT INTO `book_tag` VALUES (16, '钱钟书', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 13:58:08');
INSERT INTO `book_tag` VALUES (17, '王小波', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 13:58:18');
INSERT INTO `book_tag` VALUES (18, '余华', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 13:58:25');
INSERT INTO `book_tag` VALUES (19, '茨威格', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 13:58:32');
INSERT INTO `book_tag` VALUES (20, '米兰·昆德拉', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 13:58:44');
INSERT INTO `book_tag` VALUES (21, '童话', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 13:58:59');
INSERT INTO `book_tag` VALUES (22, '名著', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 14:00:06');
INSERT INTO `book_tag` VALUES (23, '古典文学', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 14:00:17');
INSERT INTO `book_tag` VALUES (24, '树上春树', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 14:03:04');
INSERT INTO `book_tag` VALUES (25, '经济学', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:12:40');
INSERT INTO `book_tag` VALUES (26, '管理', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:12:47');
INSERT INTO `book_tag` VALUES (27, '经济', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:13:05');
INSERT INTO `book_tag` VALUES (28, '商业', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:13:09');
INSERT INTO `book_tag` VALUES (29, '金融', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:13:13');
INSERT INTO `book_tag` VALUES (30, '投资', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:13:21');
INSERT INTO `book_tag` VALUES (31, '营销', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:13:32');
INSERT INTO `book_tag` VALUES (32, '理财', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:13:37');
INSERT INTO `book_tag` VALUES (33, '创业', 1, 2, 2, '2022-04-07 16:38:28', '2022-04-07 15:13:41');
INSERT INTO `book_tag` VALUES (34, '股票', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:13:48');
INSERT INTO `book_tag` VALUES (35, '广告', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:13:56');
INSERT INTO `book_tag` VALUES (36, '企业史', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:14:06');
INSERT INTO `book_tag` VALUES (37, '策划', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:14:13');
INSERT INTO `book_tag` VALUES (38, '科普', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:14:16');
INSERT INTO `book_tag` VALUES (39, '互联网', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:14:23');
INSERT INTO `book_tag` VALUES (40, '科学', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:14:31');
INSERT INTO `book_tag` VALUES (41, '编程', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:14:37');
INSERT INTO `book_tag` VALUES (42, '交互设计', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:14:48');
INSERT INTO `book_tag` VALUES (43, '算法', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:16:28');
INSERT INTO `book_tag` VALUES (44, '用户体验', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:16:42');
INSERT INTO `book_tag` VALUES (45, '科技', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:17:00');
INSERT INTO `book_tag` VALUES (46, 'web', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:17:05');
INSERT INTO `book_tag` VALUES (47, '交互', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:17:11');
INSERT INTO `book_tag` VALUES (48, '通信', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:17:15');
INSERT INTO `book_tag` VALUES (49, 'UE', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:17:20');
INSERT INTO `book_tag` VALUES (50, '神经网络', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:17:32');
INSERT INTO `book_tag` VALUES (51, 'UCD', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:18:29');
INSERT INTO `book_tag` VALUES (52, '程序', 1, NULL, 2, '2022-04-07 16:32:53', '2022-04-07 15:19:47');

-- ----------------------------
-- Table structure for book_tags
-- ----------------------------
DROP TABLE IF EXISTS `book_tags`;
CREATE TABLE `book_tags`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `book_id` int NULL DEFAULT NULL COMMENT '书籍id',
  `tag_id` int NULL DEFAULT NULL COMMENT '标签id',
  `likes` int NULL DEFAULT 0 COMMENT '推荐数',
  `update_by` int NULL DEFAULT NULL COMMENT '最后修改人',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_tags
-- ----------------------------

-- ----------------------------
-- Table structure for book_type
-- ----------------------------
DROP TABLE IF EXISTS `book_type`;
CREATE TABLE `book_type`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '书籍类型id',
  `type_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '书籍类型',
  `update_by` int NULL DEFAULT NULL COMMENT '更新者',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '书籍类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_type
-- ----------------------------
INSERT INTO `book_type` VALUES (1, '文学', 2, 2, '2022-04-06 19:09:19', '2022-03-25 23:54:05');
INSERT INTO `book_type` VALUES (2, '流行', NULL, 2, '2022-04-06 19:09:20', '2022-03-25 23:54:11');
INSERT INTO `book_type` VALUES (3, '文化', NULL, 2, '2022-04-06 19:09:21', '2022-03-25 23:54:30');
INSERT INTO `book_type` VALUES (4, '生活', NULL, 2, '2022-04-06 19:09:21', '2022-03-25 23:54:36');
INSERT INTO `book_type` VALUES (5, '经营', NULL, 2, '2022-04-06 19:09:22', '2022-03-25 23:54:41');
INSERT INTO `book_type` VALUES (6, '科技', NULL, 2, '2022-04-06 19:09:23', '2022-03-25 23:54:44');

-- ----------------------------
-- Table structure for borrowing
-- ----------------------------
DROP TABLE IF EXISTS `borrowing`;
CREATE TABLE `borrowing`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '借阅记录id',
  `book_id` int NOT NULL COMMENT '借阅书籍id',
  `user_id` int NOT NULL COMMENT '借阅用户id',
  `start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '借阅时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '归还时间',
  `state` tinyint NOT NULL DEFAULT 0 COMMENT '状态0（未归还）1（已归还）',
  `update_by` int NULL DEFAULT NULL COMMENT '修改者',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '借阅记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrowing
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int NOT NULL,
  `menu_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单，前端判断是否显示',
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单释义',
  `permission_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限代码',
  `permission_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限释义',
  `required_permission` tinyint(1) NOT NULL DEFAULT 2 COMMENT '是否本菜单必选权限, 1.必选 2非必选 通常是\"列表\"权限是必选',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (101, 'book', '图书管理', 'book:list', '图书列表', 1, '2022-03-26 15:54:15', '2021-04-20 15:57:50');
INSERT INTO `sys_permission` VALUES (102, 'book', '图书管理', 'book:add', '添加图书', 2, '2022-03-26 15:54:17', '2021-08-10 11:57:44');
INSERT INTO `sys_permission` VALUES (103, 'book', '图书管理', 'book:upd', '修改图书', 2, '2022-03-26 15:54:19', '2021-08-10 11:58:27');
INSERT INTO `sys_permission` VALUES (104, 'book', '图书管理', 'book:del', '删除图书', 2, '2022-03-26 15:54:22', '2021-08-10 11:58:49');
INSERT INTO `sys_permission` VALUES (201, 'bookType', '图书类型管理', 'bookType:list', '图书类型列表', 1, '2022-03-26 15:54:35', '2021-04-20 15:58:31');
INSERT INTO `sys_permission` VALUES (202, 'bookType', '图书类型管理', 'bookType:add', '添加图书类型', 2, '2022-03-26 15:54:40', '2021-08-10 12:03:05');
INSERT INTO `sys_permission` VALUES (203, 'bookType', '图书类型管理', 'bookType:upd', '修改图书类型', 2, '2022-03-26 15:54:42', '2021-08-10 12:11:18');
INSERT INTO `sys_permission` VALUES (204, 'bookType', '图书类型管理', 'bookType:del', '删除图书类型', 2, '2022-03-26 15:54:47', '2021-08-10 12:12:20');
INSERT INTO `sys_permission` VALUES (301, 'bookTag', '图书标签管理', 'bookTag:list', '图书标签列表', 1, '2022-03-26 15:54:56', '2021-08-10 12:13:21');
INSERT INTO `sys_permission` VALUES (302, 'bookTag', '图书标签管理', 'bookTag:add', '添加图书标签', 2, '2022-03-26 15:54:58', '2021-08-10 12:13:40');
INSERT INTO `sys_permission` VALUES (303, 'bookTag', '图书标签管理', 'bookTag:upd', '修改图书标签', 2, '2022-03-26 15:54:59', '2021-08-10 12:13:59');
INSERT INTO `sys_permission` VALUES (304, 'bookTag', '图书标签管理', 'bookTag:del', '删除图书标签', 2, '2022-03-26 15:55:06', '2021-08-10 12:14:39');
INSERT INTO `sys_permission` VALUES (401, 'borrow', '借阅记录管理', 'borrow:list', '借阅记录查看', 1, '2022-03-26 15:56:30', '2022-03-26 15:54:07');
INSERT INTO `sys_permission` VALUES (402, 'borrow', '借阅记录管理', 'borrow:add', '借阅图书', 1, '2022-03-26 15:57:57', '2022-03-26 15:56:12');
INSERT INTO `sys_permission` VALUES (403, 'borrow', '借阅记录管理', 'borrow:upd', '借阅记录修改', 2, '2022-03-26 15:59:31', '2022-03-26 15:57:52');
INSERT INTO `sys_permission` VALUES (404, 'borrow', '借阅记录管理', 'borrow:del', '借阅记录删除', 2, NULL, '2022-03-26 15:59:23');
INSERT INTO `sys_permission` VALUES (80001, 'user', '用户管理', 'user:list', '用户列表', 1, NULL, '2022-03-26 16:21:17');
INSERT INTO `sys_permission` VALUES (80002, 'user', '用户管理', 'user:add', '添加用户', 2, NULL, '2022-03-26 16:21:17');
INSERT INTO `sys_permission` VALUES (80003, 'user', '用户管理', 'user:upd', '修改用户', 2, NULL, '2022-03-26 16:21:17');
INSERT INTO `sys_permission` VALUES (80004, 'user', '用户管理', 'user:del', '删除用户', 2, NULL, '2022-03-26 16:21:17');
INSERT INTO `sys_permission` VALUES (80101, 'dashboard', '控制台', 'dashboard:list', '控制台菜单', 2, NULL, '2022-03-29 14:35:14');
INSERT INTO `sys_permission` VALUES (90001, 'system', '系统管理', 'system:list', '系统管理', 1, NULL, '2022-03-26 16:06:46');
INSERT INTO `sys_permission` VALUES (90002, 'permiss', '角色管理', 'role:list', '角色列表', 1, '2022-03-29 15:58:10', '2022-03-26 16:21:17');
INSERT INTO `sys_permission` VALUES (90003, 'permiss', '角色管理', 'role:add', '添加角色', 2, '2022-03-29 15:58:07', '2022-03-26 16:21:17');
INSERT INTO `sys_permission` VALUES (90004, 'permiss', '角色管理', 'role:upd', '修改角色', 2, '2022-03-29 15:58:07', '2022-03-26 16:21:17');
INSERT INTO `sys_permission` VALUES (90005, 'permiss', '角色管理', 'role:del', '删除角色', 2, '2022-03-29 15:58:05', '2022-03-26 16:21:17');
INSERT INTO `sys_permission` VALUES (90006, 'permiss', '权限管理', 'permiss:list', '角色权限列表', 1, NULL, '2022-03-26 16:21:17');
INSERT INTO `sys_permission` VALUES (90007, 'permiss', '权限管理', 'permiss:add', '添加角色权限', 2, '2022-03-26 16:30:57', '2022-03-26 16:21:17');
INSERT INTO `sys_permission` VALUES (90008, 'permiss', '权限管理', 'permiss:upd', '修改角色权限', 2, '2022-03-26 16:30:58', '2022-03-26 16:21:17');
INSERT INTO `sys_permission` VALUES (90009, 'permiss', '权限管理', 'permiss:del', '删除角色权限', 2, '2022-03-26 16:30:59', '2022-03-26 16:21:17');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '描述',
  `update_by` int NULL DEFAULT NULL COMMENT '修改者',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'admin', '网站管理员', 1, NULL, '2022-04-05 21:35:18', '2021-04-20 15:47:44');
INSERT INTO `sys_role` VALUES (2, 'user', '普通用户', 1, NULL, '2022-04-03 01:39:51', '2021-04-20 15:49:04');
INSERT INTO `sys_role` VALUES (3, 'worker', '职工', 1, NULL, '2022-04-03 01:38:05', '2021-04-20 15:49:16');
INSERT INTO `sys_role` VALUES (5, 'vip_user', '借阅用户', 1, NULL, '2022-04-03 01:37:47', '2021-04-20 15:48:53');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `role_id` int NOT NULL COMMENT '角色id',
  `perms_id` int NOT NULL COMMENT '权限id',
  `update_by` int NULL DEFAULT NULL COMMENT '更新者',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`role_id`, `perms_id`) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE,
  INDEX `perms_id`(`perms_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限映射表\r\n\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 101, 1, 1, NULL, '2022-04-07 19:05:27');
INSERT INTO `sys_role_permission` VALUES (1, 102, 1, 1, NULL, '2022-04-07 19:05:27');
INSERT INTO `sys_role_permission` VALUES (1, 103, 1, 1, NULL, '2022-04-07 19:05:27');
INSERT INTO `sys_role_permission` VALUES (1, 104, 1, 1, NULL, '2022-04-07 19:05:27');
INSERT INTO `sys_role_permission` VALUES (1, 201, 1, 1, NULL, '2022-04-07 19:05:27');
INSERT INTO `sys_role_permission` VALUES (1, 202, 1, 1, NULL, '2022-04-07 19:05:27');
INSERT INTO `sys_role_permission` VALUES (1, 203, 1, 1, NULL, '2022-04-07 19:05:27');
INSERT INTO `sys_role_permission` VALUES (1, 204, 1, 1, NULL, '2022-04-07 19:05:27');
INSERT INTO `sys_role_permission` VALUES (1, 301, 1, 1, NULL, '2022-04-07 19:05:27');
INSERT INTO `sys_role_permission` VALUES (1, 302, 1, 1, NULL, '2022-04-07 19:05:27');
INSERT INTO `sys_role_permission` VALUES (1, 303, 1, 1, NULL, '2022-04-07 19:05:27');
INSERT INTO `sys_role_permission` VALUES (1, 304, 1, 1, NULL, '2022-04-07 19:05:27');
INSERT INTO `sys_role_permission` VALUES (1, 401, 1, 1, NULL, '2022-04-07 19:05:27');
INSERT INTO `sys_role_permission` VALUES (1, 402, 1, 1, NULL, '2022-04-07 19:05:27');
INSERT INTO `sys_role_permission` VALUES (1, 403, 1, 1, NULL, '2022-04-07 19:05:27');
INSERT INTO `sys_role_permission` VALUES (1, 404, 1, 1, NULL, '2022-04-07 19:05:27');
INSERT INTO `sys_role_permission` VALUES (1, 80001, 1, 1, NULL, '2022-04-07 19:05:27');
INSERT INTO `sys_role_permission` VALUES (1, 80002, 1, 1, NULL, '2022-04-07 19:05:27');
INSERT INTO `sys_role_permission` VALUES (1, 80003, 1, 1, NULL, '2022-04-07 19:05:27');
INSERT INTO `sys_role_permission` VALUES (1, 80004, 1, 1, NULL, '2022-04-07 19:05:27');
INSERT INTO `sys_role_permission` VALUES (2, 101, 1, 1, NULL, '2022-04-03 01:41:31');
INSERT INTO `sys_role_permission` VALUES (2, 201, 1, 1, NULL, '2022-04-03 01:41:31');
INSERT INTO `sys_role_permission` VALUES (2, 301, 1, 1, NULL, '2022-04-03 01:41:31');
INSERT INTO `sys_role_permission` VALUES (2, 401, 1, 1, NULL, '2022-04-03 01:41:31');
INSERT INTO `sys_role_permission` VALUES (2, 404, 1, 1, NULL, '2022-04-03 01:41:31');
INSERT INTO `sys_role_permission` VALUES (3, 101, 1, 1, NULL, '2022-04-03 01:40:56');
INSERT INTO `sys_role_permission` VALUES (3, 102, 1, 1, NULL, '2022-04-03 01:40:56');
INSERT INTO `sys_role_permission` VALUES (3, 103, 1, 1, NULL, '2022-04-03 01:40:56');
INSERT INTO `sys_role_permission` VALUES (3, 104, 1, 1, NULL, '2022-04-03 01:40:56');
INSERT INTO `sys_role_permission` VALUES (3, 201, 1, 1, NULL, '2022-04-03 01:40:56');
INSERT INTO `sys_role_permission` VALUES (3, 202, 1, 1, NULL, '2022-04-03 01:40:56');
INSERT INTO `sys_role_permission` VALUES (3, 203, 1, 1, NULL, '2022-04-03 01:40:56');
INSERT INTO `sys_role_permission` VALUES (3, 204, 1, 1, NULL, '2022-04-03 01:40:56');
INSERT INTO `sys_role_permission` VALUES (3, 301, 1, 1, NULL, '2022-04-03 01:40:56');
INSERT INTO `sys_role_permission` VALUES (3, 302, 1, 1, NULL, '2022-04-03 01:40:56');
INSERT INTO `sys_role_permission` VALUES (3, 303, 1, 1, NULL, '2022-04-03 01:40:56');
INSERT INTO `sys_role_permission` VALUES (3, 304, 1, 1, NULL, '2022-04-03 01:40:56');
INSERT INTO `sys_role_permission` VALUES (3, 401, 1, 1, NULL, '2022-04-03 01:40:56');
INSERT INTO `sys_role_permission` VALUES (3, 402, 1, 1, NULL, '2022-04-03 01:40:56');
INSERT INTO `sys_role_permission` VALUES (3, 403, 1, 1, NULL, '2022-04-03 01:40:56');
INSERT INTO `sys_role_permission` VALUES (3, 404, 1, 1, NULL, '2022-04-03 01:40:56');
INSERT INTO `sys_role_permission` VALUES (5, 101, 1, 1, NULL, '2022-04-03 01:41:53');
INSERT INTO `sys_role_permission` VALUES (5, 201, 1, 1, NULL, '2022-04-03 01:41:53');
INSERT INTO `sys_role_permission` VALUES (5, 301, 1, 1, NULL, '2022-04-03 01:41:53');
INSERT INTO `sys_role_permission` VALUES (5, 401, 1, 1, NULL, '2022-04-03 01:41:53');
INSERT INTO `sys_role_permission` VALUES (5, 402, 1, 1, NULL, '2022-04-03 01:41:53');
INSERT INTO `sys_role_permission` VALUES (5, 404, 1, 1, NULL, '2022-04-03 01:41:53');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `sex` int NULL DEFAULT NULL COMMENT '用户性别(0：未知，1：男，2：女)',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系地址',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'root', '1234', 1, '保密', '2022-03-28 20:01:24', '2022-03-27 21:15:41');
INSERT INTO `sys_user` VALUES (2, '测试用户', '123456', 2, '11111', '2022-03-27 21:16:03', '2022-03-27 21:15:54');

-- ----------------------------
-- Table structure for sys_user_auths
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_auths`;
CREATE TABLE `sys_user_auths`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户id',
  `identity_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录类型（手机号 邮箱 用户名）或第三方应用名称（微信 微博等）',
  `identifier` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标识（手机号 邮箱 用户名或第三方应用的唯一标识）',
  `credential` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码凭证（站内的保存密码，站外的不保存或保存token）',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户授权表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_auths
-- ----------------------------
INSERT INTO `sys_user_auths` VALUES (1, 1, 'ID', 'admin', '123456', '2021-08-09 21:27:35', '2021-04-20 15:53:15');
INSERT INTO `sys_user_auths` VALUES (2, 2, 'ID', 'usertest', '123456', '2021-08-09 21:27:40', '2021-04-20 16:03:07');
INSERT INTO `sys_user_auths` VALUES (3, 1, 'EMAIL', '33256', '123456', NULL, '2022-03-27 21:14:35');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int NOT NULL COMMENT '用户id',
  `role_id` int NOT NULL COMMENT '角色id',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` int NULL DEFAULT NULL COMMENT '更新者',
  `create_by` int NULL DEFAULT NULL COMMENT '创建者',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1, NULL, '2022-03-20 14:15:20', NULL, NULL);
INSERT INTO `sys_user_role` VALUES (2, 2, 2, '2022-03-20 14:16:12', '2022-03-20 14:15:20', NULL, NULL);
INSERT INTO `sys_user_role` VALUES (3, 2, 3, '2022-03-28 21:13:46', '2022-03-20 14:15:20', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
