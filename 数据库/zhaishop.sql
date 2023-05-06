/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50509
Source Host           : localhost:3306
Source Database       : zhaishop

Target Server Type    : MYSQL
Target Server Version : 50509
File Encoding         : 65001

Date: 2017-12-08 15:15:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_address`
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address` (
  `id` int(11) DEFAULT NULL,
  `province` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `area` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `msg` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_address
-- ----------------------------
INSERT INTO `t_address` VALUES ('1', '未填写', null, null, null, null, null, null, null);
INSERT INTO `t_address` VALUES ('2', '河南省', '驻马店市', '确山县', '卧龙家园一号楼', '15638377962', '刘振兴', '1', '10006');
INSERT INTO `t_address` VALUES ('3', '河南省', '漯河市', '源汇区', '大学路', '15638377962', '刘振兴', '0', '10026');
INSERT INTO `t_address` VALUES ('5', '北京市', '北京市', '西城区', '臭水沟哈哈', '110', '孟恒', '1', '10024');
INSERT INTO `t_address` VALUES ('9', '河南省', '驻马店市', '确山县', 'undefined', 'undefined', 'undefined', '0', '0');
INSERT INTO `t_address` VALUES ('10', '河南省', '驻马店市', '确山县', 'undefined', 'undefined', 'undefined', '0', '0');
INSERT INTO `t_address` VALUES ('11', '河南省', '驻马店市', '确山县', 'undefined', 'undefined', 'undefined', '0', '0');
INSERT INTO `t_address` VALUES ('12', '北京市', '北京市', '西城区', '111', '1', '1', '0', '0');
INSERT INTO `t_address` VALUES ('13', '北京市', '北京市', '西城区', '1', '1', '1', '1', '0');
INSERT INTO `t_address` VALUES ('14', '天津市', '天津市', '和平区', '1', '1', '1', '0', '0');
INSERT INTO `t_address` VALUES ('17', '河南省', '驻马店市', '确山县', '卧龙家园', '15638377962', '刘振兴', '1', '10026');

-- ----------------------------
-- Table structure for `t_bigtype`
-- ----------------------------
DROP TABLE IF EXISTS `t_bigtype`;
CREATE TABLE `t_bigtype` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `remarks` varchar(50) DEFAULT NULL,
  `proPic` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bigtype
-- ----------------------------
INSERT INTO `t_bigtype` VALUES ('1', '食品饮料、酒类、生鲜', '666666', 'images/bigTypeImg/1.jpg');
INSERT INTO `t_bigtype` VALUES ('2', '手机、数码、通信', '', 'images/bigTypeImg/2.jpg');
INSERT INTO `t_bigtype` VALUES ('3', '男装、女装、鞋包', '衣服', 'images/bigTypeImg/3.jpg');
INSERT INTO `t_bigtype` VALUES ('4', '图书、音像、数字商品', '娱乐', 'images/bigTypeImg/4.jpg');
INSERT INTO `t_bigtype` VALUES ('5', '家居、家具、家装', '饰品', 'images/bigTypeImg/5.jpg');
INSERT INTO `t_bigtype` VALUES ('6', '母婴、玩具、乐器', '', 'images/bigTypeImg/6.jpg');
INSERT INTO `t_bigtype` VALUES ('7', '整车、汽车用品', '', 'images/bigTypeImg/7.jpg');
INSERT INTO `t_bigtype` VALUES ('8', '珠宝、钟表', '饰品', 'images/bigTypeImg/8.jpg');
INSERT INTO `t_bigtype` VALUES ('9', '个护化妆', '化妆', 'images/bigTypeImg/9.jpg');

-- ----------------------------
-- Table structure for `t_collect`
-- ----------------------------
DROP TABLE IF EXISTS `t_collect`;
CREATE TABLE `t_collect` (
  `id` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `goodsId` int(11) DEFAULT NULL,
  `goodsName` varchar(100) DEFAULT NULL,
  `goodsProPic` varchar(50) DEFAULT NULL,
  `goodsPrice` double DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_collect
-- ----------------------------
INSERT INTO `t_collect` VALUES ('1', '1', '1', '1', '1', '0', '2016-02-20 18:24:08');
INSERT INTO `t_collect` VALUES ('39', '10024', '18', '红柚', 'images/goodsImg/2016_01_04 15-23-02.jpg', '1.8', '2016-02-23 13:39:21');
INSERT INTO `t_collect` VALUES ('40', '10026', '24', '扫地机器人', 'images/goodsImg/2016_01_04 15-57-40.png', '1099', '2016-02-23 13:46:08');
INSERT INTO `t_collect` VALUES ('18', '10026', '28', '时尚女裙', 'images/goodsImg/2016_01_04 16-38-48.jpg', '129', '2016-02-22 12:18:31');
INSERT INTO `t_collect` VALUES ('22', '10026', '22', 'iPhone 6s plus', 'images/goodsImg/2016_01_04 15-49-05.jpg', '4888', '2016-02-22 12:18:52');
INSERT INTO `t_collect` VALUES ('23', '10026', '30', '韩版新款轻薄羽绒服', 'images/goodsImg/2016_01_04 16-45-57.JPG', '248', '2016-02-22 12:18:57');
INSERT INTO `t_collect` VALUES ('24', '10026', '1', '红富士苹果', 'images/bigTypeImg/2016_01_03 21-33-26.jpg', '1', '2016-02-22 12:19:04');
INSERT INTO `t_collect` VALUES ('25', '10026', '62', '伊利金领冠奶粉', 'images/goodsImg/2016_01_04 17-48-50.JPG', '120', '2016-02-22 12:19:15');
INSERT INTO `t_collect` VALUES ('27', '10026', '88', '儿童滑板车', 'images/goodsImg/2016_01_07 11-01-53.PNG', '398', '2016-02-22 12:19:21');
INSERT INTO `t_collect` VALUES ('43', '10026', '18', '红柚', 'images/goodsImg/2016_01_04 15-23-02.jpg', '1.8', '2016-02-24 10:42:57');

-- ----------------------------
-- Table structure for `t_goods`
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `proPic` varchar(50) DEFAULT NULL,
  `brand` varchar(50) DEFAULT NULL,
  `sales` int(11) DEFAULT NULL,
  `views` int(11) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `contents` longtext,
  `bigTypeId` int(11) DEFAULT NULL,
  `smallTypeId` int(11) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES ('1', '红富士苹果', '1', 'images/bigTypeImg/2016_01_03 21-33-26.jpg', '红富士', '10', '124', '100', '<p style=\"color:red;\">\r\n	这是红富士苹果\r\n</p>', '1', '4', '正常');
INSERT INTO `t_goods` VALUES ('2', '森马牛仔裤', '88', 'images/goodsImg/2016_01_04 15-27-43.png', '森马', '20', '228', '500', '<p>\r\n	这是森马牛仔裤的描述\r\n</p>', '3', '3', '正常');
INSERT INTO `t_goods` VALUES ('3', '高软二班大西瓜', '16000', 'images/bigTypeImg/2016_01_03 21-32-32.jpg', '瓦岗西瓜', '200', '2052', '500', '<p>\r\n	这是西瓜的描述\r\n</p>', '1', '4', '正常');
INSERT INTO `t_goods` VALUES ('5', '西瓜', '1.2', 'images/bigTypeImg/2016_01_03 21-24-41.jpg', '瓦岗西瓜', '200', '2049', '500', '<p>\r\n	这是西瓜的描述\r\n</p>', '1', '4', '正常');
INSERT INTO `t_goods` VALUES ('7', '南瓜', '0.8', 'images/bigTypeImg/2016_01_03 21-20-01.jpg', '瓦岗南瓜', '20', '244', '50', '<h1>\r\n	<span style=\"background-color:#E53333;color:#FFFFFF;\">大南瓜真不错</span> \r\n</h1>', '1', '5', '正常');
INSERT INTO `t_goods` VALUES ('9', '金龙鱼油', '39.9', 'images/bigTypeImg/2016_01_03 21-19-51.png', '金龙鱼', '0', '25', '100', '<p>\r\n	好吃不贵\r\n</p>\r\n<p>\r\n	<img src=\"/zhaiShop/images/attached/image/20160103/20160103091446_971.png\" alt=\"\" /> \r\n</p>\r\n<br />', '1', '6', '正常');
INSERT INTO `t_goods` VALUES ('11', '2件包邮*立顿柠檬茶清新柠檬冰红茶', '24', 'images/goodsImg/2016_01_04 08-52-50.png', 'Lipton/立顿', '0', '108', '1000', '<p>\r\n	<span style=\"color:#ff0000;\"><span style=\"font-family:microsoft yahei;\"><span><span style=\"font-size:14.0px;\"><span><strong>【生产日期】随时更新</strong></span></span></span></span></span>\r\n</p>\r\n<p>\r\n	<span style=\"color:#ff0000;\"><span style=\"font-family:microsoft yahei;\"><span><span style=\"font-size:14.0px;\"><span><strong>【保质期】2年</strong></span></span></span></span></span>\r\n</p>\r\n<p>\r\n	<span style=\"color:#ff0000;\"><span style=\"font-family:microsoft yahei;\"><span><span style=\"font-size:14.0px;\"><span><strong>【净含量】1000克</strong></span></span></span></span></span>\r\n</p>\r\n<p>\r\n	<span style=\"color:#ff0000;\"><span style=\"font-family:microsoft yahei;\"><span><span style=\"font-size:14.0px;\"><span><strong>【配料】白砂糖、食品添加剂、（柠檬酸、食用香精、维生素C）麦芽糊精、红茶粉、柠檬粉</strong></span></span></span></span></span>\r\n</p>\r\n<p>\r\n	<span style=\"font-family:microsoft yahei;\"><span style=\"font-size:24.0px;\"><span style=\"color:#000000;\"><span style=\"font-style:normal;font-weight:normal;\"><span style=\"font-style:normal;font-weight:normal;\"><span>经济商用装是在餐饮行业中流通的产品，在超市商场不售卖这个包装的产品。本产品的口味和质量与超市商场售卖是一样的。</span></span></span></span></span></span>\r\n</p>\r\n<p>\r\n	<span style=\"font-family:microsoft yahei;\"><span style=\"font-size:24.0px;\">&nbsp;<span style=\"color:#bf9000;\"><span style=\"font-style:normal;font-weight:normal;\"><span style=\"color:#000000;\"><span style=\"font-style:normal;font-weight:normal;\"><span>大包装实在是实惠的不得了！按克数算算再和超市的小包装一比较就知道了！冲调十分方便，只需热水一冲，美味即可享用，无论热饮、冷饮，一样美味可口！</span></span></span></span></span></span></span>\r\n</p>\r\n<p align=\"center\">\r\n	<span style=\"font-family:microsoft yahei;\"><span style=\"font-size:24.0px;\">&nbsp;<span style=\"color:#bf9000;\"><span style=\"font-style:normal;font-weight:normal;\"><span style=\"color:#000000;\"><span style=\"font-style:normal;font-weight:normal;\"><span>立顿清新冰红茶(柠檬味)商用装(1000克)到货了,最新鲜的生产日期,带给您最清爽舒畅的感受,可做冰红茶,1000G好大一包,适合公司,家庭和一些饮品店使用,真的很划算哦.</span></span></span></span></span></span></span>\r\n</p>\r\n<p align=\"left\">\r\n	<span style=\"font-family:microsoft yahei;\"><span style=\"font-size:24.0px;\">&nbsp;<span style=\"color:#bf9000;\"><span style=\"font-style:normal;font-weight:normal;\"><span style=\"color:#000000;\"><span style=\"font-style:normal;font-weight:normal;\"><span>立\r\n顿清新柠檬茶商用装(Catering \r\nPack)，是立顿上等即溶红茶和天然柠檬口味的美妙结合，兼含柠檬的清新果香和红茶的甘爽口感，清新爽口，天然健康。立顿柠檬茶让您在清爽舒畅的感受中\r\n焕发神采，振作精神。 立顿世界领先的生产工艺带给您方便的速溶包装，可用冷、热水直接冲调.</span></span></span></span></span></span></span>\r\n</p>', '1', '8', '正常');
INSERT INTO `t_goods` VALUES ('12', '包邮！意大利费列罗巧克力水晶礼盒装 T30粒装 散装喜糖正品 批发', '58', 'images/goodsImg/2016_01_04 09-10-01.jpg', '费列罗/FERRERO ROCHER', '0', '19', '100', '<p style=\"text-align:center;\">\r\n	<span style=\"font-size:36.0px;\"><strong><span style=\"color:#660000;\">过期时间：2016年07月份</span></strong></span> \r\n</p>\r\n<p style=\"text-align:center;\">\r\n	<strong>水晶盒比较脆和薄，本店全部用气泡隔膜包装好的。</strong> \r\n</p>\r\n<p style=\"text-align:center;\">\r\n	<b><span style=\"font-size:36.0px;\"><span style=\"color:#ff0000;\"><span><span style=\"font-size:18.0px;\">但是</span></span></span></span></b><b><span style=\"font-size:36.0px;\"><span style=\"color:#ff0000;\"><span><span style=\"font-size:18.0px;\">快递运输途中暴力分拣，不能保证一定不破损。</span></span></span></span></b> \r\n</p>\r\n<p style=\"text-align:center;\">\r\n	<b><span style=\"font-size:36.0px;\"><span style=\"color:#ff0000;\"><span><span style=\"font-size:18.0px;\">本店尽力包装，请顾客手下留情。饶小的一回。。.</span></span></span></span></b> \r\n</p>\r\n<p style=\"text-align:center;\">\r\n	<b><span style=\"font-size:36.0px;\"><span style=\"color:#ff0000;\"><span><span style=\"font-size:18.0px;\"></span></span></span></span></b><strong><span style=\"color:#ff0000;\"><span style=\"font-family:微软雅黑;font-size:19.0px;line-height:27.0px;background-color:#fbfcfc;\">亲们注意：进口食品因批次不同，包装有所不同，随机发的奥，都是原装意大利进口的！</span></span></strong> \r\n</p>\r\n<p>\r\n	<span style=\"color:#ff0000;font-family:microsoft yahei;font-size:18.0px;\">包邮活动说明</span><span style=\"font-family:microsoft yahei;font-size:18.0px;\">：</span> \r\n</p>\r\n<p>\r\n	&nbsp;\r\n</p>\r\n<p style=\"text-align:center;color:#000000;font-size:14.0px;\">\r\n	<span style=\"line-height:21.0px;font-size:14.0px;\">&nbsp;</span><span style=\"line-height:1.5;font-family:microsoft yahei;font-size:14.0px;\"><span style=\"font-size:18.0px;\">内蒙，海南，甘肃，青海，宁夏，新疆，西藏，港澳台，不参加包邮活动，</span></span><span style=\"line-height:21.0px;font-family:microsoft yahei;font-size:18.0px;\">其他地区均可参加包邮活动。</span> \r\n</p>', '1', '8', '正常');
INSERT INTO `t_goods` VALUES ('13', '【世果汇】越南青芒果8斤 进口新鲜水果 青皮芒果 热带水果 包邮 ', '59', 'images/goodsImg/2016_01_04 09-38-29.jpg', '世果汇', '0', '46', '100', '<ul id=\"J_AttrUL\">\r\n	<li>\r\n		厂名：世果汇水果旗舰店\r\n	</li>\r\n	<li>\r\n		厂址：广东佛山南海里水\r\n	</li>\r\n	<li>\r\n		厂家联系方式：0757-63318917\r\n	</li>\r\n	<li>\r\n		配料表：芒果\r\n	</li>\r\n	<li>\r\n		储藏方法：干燥阴凉处\r\n	</li>\r\n	<li>\r\n		保质期：7 天\r\n	</li>\r\n	<li>\r\n		食品添加剂：无\r\n	</li>\r\n	<li>\r\n		净含量:&nbsp;4000g\r\n	</li>\r\n	<li>\r\n		包装方式:&nbsp;包装\r\n	</li>\r\n	<li id=\"J_attrBrandName\">\r\n		品牌:&nbsp;Fruitone/世果汇\r\n	</li>\r\n	<li>\r\n		售卖方式:&nbsp;单品\r\n	</li>\r\n	<li>\r\n		热卖时间:&nbsp;1月&nbsp;2月&nbsp;11月&nbsp;12月\r\n	</li>\r\n	<li>\r\n		产地:&nbsp;越南\r\n	</li>\r\n	<li>\r\n		套餐份量:&nbsp;1人份\r\n	</li>\r\n	<li>\r\n		套餐周期:&nbsp;1周\r\n	</li>\r\n	<li>\r\n		配送频次:&nbsp;1周2次\r\n	</li>\r\n	<li>\r\n		净含量:&nbsp;2.5kg(含）-5kg(不含)\r\n	</li>\r\n	<li>\r\n		价格:&nbsp;51-100元\r\n	</li>\r\n</ul>', '1', '4', '正常');
INSERT INTO `t_goods` VALUES ('14', 'oppo  R7 plus', '2999', 'images/bigTypeImg/2016_01_04 15-18-15.jpg', 'oppo', '0', '2', '100', '', '2', '17', '正常');
INSERT INTO `t_goods` VALUES ('15', '魅族 5', '1599', 'images/goodsImg/2016_01_04 15-19-46.png', '魅族', '20', '43', '100', '', '2', '17', '正常');
INSERT INTO `t_goods` VALUES ('16', '雷神911黄金版', '6999', 'images/bigTypeImg/2016_01_04 15-14-37.png', '雷神', '352', '591', '500', '', '2', '13', '正常');
INSERT INTO `t_goods` VALUES ('17', '小米音响', '599', 'images/goodsImg/2016_01_04 15-25-53.png', '小米', '211', '329', '300', '', '4', '24', '');
INSERT INTO `t_goods` VALUES ('18', '红柚', '1.8', 'images/goodsImg/2016_01_04 15-23-02.jpg', '红柚', '235', '493', '49995', '', '1', '4', '正常');
INSERT INTO `t_goods` VALUES ('19', '索尼DSC-HX300', '2189', 'images/goodsImg/2016_01_04 15-31-15.jpg', '索尼', '351', '563', '500', '<p>\r\n	这是森马牛仔裤的描述\r\n</p>', '2', '43', '正常');
INSERT INTO `t_goods` VALUES ('20', '海南芝麻蕉', '1.5', 'images/goodsImg/2016_01_04 15-34-40.jpg', '新疆芝麻蕉', '271', '629', '383', '<img src=\"/zhaiShop/images/attached/image/20160106/20160106110219_698.png\" alt=\"\" /><br />', '1', '4', '正常');
INSERT INTO `t_goods` VALUES ('21', '苹果ipad', '1930', 'images/goodsImg/2016_01_04 15-41-51.jpg', '苹果', '120', '362', '200', '<p>\r\n	这是森马牛仔裤的描述\r\n</p>', '2', '17', '');
INSERT INTO `t_goods` VALUES ('22', 'iPhone 6s plus', '4888', 'images/goodsImg/2016_01_04 15-49-05.jpg', '苹果', '6', '545', '20', '<p>\r\n	这是森马牛仔裤的描述\r\n</p>', '2', '17', '正常');
INSERT INTO `t_goods` VALUES ('23', '九阳榨汁机', '1599', 'images/goodsImg/2016_01_04 15-54-27.jpg', '九阳', '132', '360', '300', '<p>\r\n	这是森马牛仔裤的描述\r\n</p>', '2', '44', '');
INSERT INTO `t_goods` VALUES ('24', '扫地机器人', '1099', 'images/goodsImg/2016_01_04 15-57-40.png', '海尔', '366', '635', '3362', '<p>\r\n	这是森马牛仔裤的描述\r\n</p>', '2', '44', '正常');
INSERT INTO `t_goods` VALUES ('25', '战神G50', '3399', 'images/goodsImg/2016_01_04 16-12-32.png', '神舟', '189', '880', '324', '<p>\r\n	这是森马牛仔裤的描述\r\n</p>', '2', '13', '正常');
INSERT INTO `t_goods` VALUES ('27', '水舞喷水音响', '178', 'images/goodsImg/2016_01_04 16-38-33.jpg', '水舞', '0', '13', '100', '水舞喷水音响', '2', '15', '正常');
INSERT INTO `t_goods` VALUES ('28', '时尚女裙', '129', 'images/goodsImg/2016_01_04 16-38-48.jpg', '韩式', '142', '326', '200', '<p>\r\n	这是森马牛仔裤的描述\r\n</p>', '3', '21', '正常');
INSERT INTO `t_goods` VALUES ('29', '森马羽绒服', '298', 'images/goodsImg/2016_01_04 16-43-21.jpg', '森马', '20', '503', '100', '<p>\r\n	这是森马牛仔裤的描述\r\n</p>', '3', '21', '');
INSERT INTO `t_goods` VALUES ('30', '韩版新款轻薄羽绒服', '248', 'images/goodsImg/2016_01_04 16-45-57.JPG', 'AFS JEEP', '0', '13', '1000', '韩版羽绒服', '3', '20', '正常');
INSERT INTO `t_goods` VALUES ('31', '森马学生西装', '198', 'images/goodsImg/2016_01_04 16-46-04.jpg', '森马', '10', '15', '400', '<p>\r\n	这是森马牛仔裤的描述\r\n</p>', '3', '20', '');
INSERT INTO `t_goods` VALUES ('32', '日着游侠套装', '798', 'images/goodsImg/2016_01_04 16-50-25.jpg', '日着工作室', '0', '3', '100', '', '3', '21', '');
INSERT INTO `t_goods` VALUES ('34', '欧美时尚手提包', '598', 'images/goodsImg/2016_01_04 16-54-54.jpg', 'LV', '0', '2', '200', '', '3', '45', '正常');
INSERT INTO `t_goods` VALUES ('35', '冬季新款单肩斜挎女士包包韩版潮流', '47.9', 'images/goodsImg/2016_01_04 16-57-22.JPG', '戴微琪', '0', '6', '400', '', '3', '45', '正常');
INSERT INTO `t_goods` VALUES ('36', '游侠鞋', '969', 'images/goodsImg/2016_01_04 16-57-39.jpg', '日着工作室', '0', '2', '200', '', '3', '45', '正常');
INSERT INTO `t_goods` VALUES ('37', '索尼DSCX50 DVD', '899', 'images/goodsImg/2016_01_04 17-00-45.png', '索尼', '0', '3', '500', '', '4', '23', '正常');
INSERT INTO `t_goods` VALUES ('38', '圣蜜莱雅水感3件套 秋冬补水保湿护肤品爽肤水套装', '79', 'images/goodsImg/2016_01_04 17-01-15.JPG', '圣蜜莱雅', '0', '11', '200', '秋冬补水保湿护肤品', '9', '40', '正常');
INSERT INTO `t_goods` VALUES ('39', '当下的力量 精装新版珍藏版 心灵经典读物', '20', 'images/goodsImg/2016_01_04 17-06-46.JPG', '天猫', '0', '6', '20', '心灵经典读物', '4', '22', '正常');
INSERT INTO `t_goods` VALUES ('40', '催眠大师', '38', 'images/goodsImg/2016_01_04 17-11-53.png', '新华书店', '0', '4', '50', '', '4', '22', '正常');
INSERT INTO `t_goods` VALUES ('41', '自营座垫跨年狂欢 京东自营 座垫满减', '560', 'images/goodsImg/2016_01_04 17-16-09.jpg', '樱木', '20', '143', '30', '<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	樱木 汽车坐垫 冬季汽车坐垫套 汽车座垫新款冬季棉垫羊毛坐垫 锦程盛世系列 羊毛冬季坐垫 驼色\r\n</h1>\r\n<div id=\"p-ad\" class=\"p-ad J-ad-1242668\" style=\"margin:0px;padding:0px;font-family:arial, \'microsoft yahei\';color:#E3393C;font-size:14px;background-color:#FFFFFF;\">\r\n	【京东自营】汽车坐垫冬季坐垫，直降回馈，温暖冬天11月21日至30日低价促销中，快来抢购！\r\n</div>', '7', '35', '正常');
INSERT INTO `t_goods` VALUES ('42', '家用简约转角台式电脑桌书桌书柜书架组合', '256', 'images/goodsImg/2016_01_04 17-16-15.JPG', '优缘雅居', '0', '2', '200', '电脑桌书桌书柜', '5', '27', '正常');
INSERT INTO `t_goods` VALUES ('43', '壳牌（Shell）喜力 汽车机油 润滑油 4L装 5W-40半合成蓝壳HX7 【立减10元满', '175', 'images/goodsImg/2016_01_04 17-19-01.jpg', '壳牌', '30', '374', '17', '<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	<a target=\"_blank\" href=\"http://item.jd.com/1058809369.html\"><span style=\"line-height:20px;\">壳牌（Shell）喜力&nbsp;<span class=\"skcolor_ljg\">汽车机油</span>&nbsp;润滑油 4L装 5W-40半合成蓝壳HX7</span>&nbsp;<span class=\"promo-words\" id=\"J_AD_1058809369\" style=\"line-height:20px;color:#FFAA71;\">【立减10元满</span></a>\r\n</h1>\r\n<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	<span style=\"color:#E3393C;font-size:14px;font-weight:normal;line-height:1.5;\">11月21日至30日低价促销中，快来抢购！</span>\r\n</h1>', '7', '36', '正常');
INSERT INTO `t_goods` VALUES ('44', '道达尔(TOTAL) 快驰QUARTZ全/半合成汽车机油润滑油 SN 5000 4L 5W-30 绿色', '135', 'images/goodsImg/2016_01_04 17-20-37.jpg', '道达尔', '56', '547', '343', '<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	<a target=\"_blank\" href=\"http://item.jd.com/1058809369.html\"><span style=\"line-height:20px;\">道达尔(TOTAL) 快驰QUARTZ全/半合成汽车机油润滑油 SN 5000 4L 5W-30 绿色</span></a>\r\n</h1>\r\n<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	<span style=\"color:#E3393C;font-size:14px;font-weight:normal;line-height:1.5;\"></span> \r\n</h1>', '7', '36', '正常');
INSERT INTO `t_goods` VALUES ('45', '法拉丹顿 1.5 1.8米 欧式床双人真皮公主储物床', '4470', 'images/goodsImg/2016_01_04 17-21-51.JPG', '法拉丹顿 ', '0', '1', '200', '欧式床双人床', '5', '27', '正常');
INSERT INTO `t_goods` VALUES ('50', '宝马 X3订金400000元 2014款 惠买车-车型9 迎新钜惠，买车不贵，购车享新车专属大礼包！', '400000', 'images/goodsImg/2016_01_04 17-23-53.jpg', '宝马', '1', '63', '6', '<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	<a target=\"_blank\" href=\"http://item.jd.com/1058809369.html\"><span style=\"line-height:20px;\">【惠买车】宝马 X3订金400000元 2014款 惠买车-车型9 迎新钜惠，买车不贵，购车享新车专属大礼包！点击查看详情</span></a> \r\n</h1>\r\n<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	<span style=\"color:#E3393C;font-size:14px;font-weight:normal;line-height:1.5;\"></span> \r\n</h1>', '7', '35', '正常');
INSERT INTO `t_goods` VALUES ('51', '宏宝刀具套装厨房套刀厨房套装厨具十件菜刀组合水果刀斩骨刀', '150', 'images/goodsImg/2016_01_04 17-25-02.JPG', '宏宝', '0', '4', '400', '刀具套装', '5', '26', '正常');
INSERT INTO `t_goods` VALUES ('53', '【惠买车】', '23009500', 'images/goodsImg/2016_01_04 17-28-04.jpg', '法拉利', '2', '807', '5', '<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	<a target=\"_blank\" href=\"http://item.jd.com/1058809369.html\"><span style=\"line-height:20px;\">【惠买车】法拉利订金5百万元 2016款 惠买车-车型 迎新钜惠，购车享新车专属大礼包！点击查看详情</span></a> \r\n</h1>\r\n<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	<span style=\"color:#E3393C;font-size:14px;font-weight:normal;line-height:1.5;\"></span> \r\n</h1>', '7', '35', '');
INSERT INTO `t_goods` VALUES ('55', '儿童架子鼓玩具 仿真爵士鼓音乐打击乐器早教益智', '100', 'images/goodsImg/2016_01_04 17-31-46.JPG', '乐于子', '0', '0', '50', '儿童玩具', '6', '31', '正常');
INSERT INTO `t_goods` VALUES ('56', '洗车拖把伸缩杆强吸水', '29', 'images/goodsImg/2016_01_04 17-31-59.jpg', '轩之梦 ', '58', '1937', '600', '<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	<a target=\"_blank\" href=\"http://item.jd.com/1058809369.html\"><span style=\"line-height:20px;\">轩之梦 洗车刷长杆汽车掸子 洗车拖把汽车用品洗车配件 牛奶丝长杆刷 不锈钢伸缩杆 硅胶包边 牛奶丝刷头 强吸水</span></a> \r\n</h1>\r\n<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	<span style=\"color:#E3393C;font-size:14px;font-weight:normal;line-height:1.5;\"></span> \r\n</h1>', '7', '34', '正常');
INSERT INTO `t_goods` VALUES ('58', '汽车贴膜', '200', 'images/goodsImg/2016_01_04 17-35-34.jpg', 'KDX窗膜', '0', '4', '2000', '', '7', '34', '正常');
INSERT INTO `t_goods` VALUES ('59', '米其林轮胎 ', '645', 'images/goodsImg/2016_01_04 17-35-44.jpg', '米其林轮胎', '15', '202', '42', '<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	<a target=\"_blank\" href=\"http://item.jd.com/1058809369.html\"><span style=\"line-height:20px;\">米其林轮胎 ENERGY MXV8 205/55R16 91V 【途虎养车 元旦让利】满999立减30元 全国2万家门店包邮包安装 激情让利点</span></a> \r\n</h1>\r\n<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	<span style=\"color:#E3393C;font-size:14px;font-weight:normal;line-height:1.5;\"></span> \r\n</h1>', '7', '34', '正常');
INSERT INTO `t_goods` VALUES ('60', '影舞者卫衣', '268', 'images/goodsImg/2016_01_04 17-35-49.JPG', '穿越火线', '0', '7', '20', '卫衣', '3', '20', '正常');
INSERT INTO `t_goods` VALUES ('61', '新YAMAHA摩托车大跑车150-250cc送本田贴花铃木趴赛越野助力机车', '5880', 'images/goodsImg/2016_01_04 17-44-13.JPG', '车一斤', '0', '3', '20', '机车', '7', '35', '正常');
INSERT INTO `t_goods` VALUES ('62', '伊利金领冠奶粉', '120', 'images/goodsImg/2016_01_04 17-48-50.JPG', '伊利', '0', '3', '500', '奶粉', '6', '30', '正常');
INSERT INTO `t_goods` VALUES ('63', '包邮正品个性摩托车头盔男女 610钢铁侠复古高端全盔越野机车亮橙', '888', 'images/goodsImg/2016_01_04 17-57-51.JPG', 'FASHION', '0', '4', '200', '头盔', '7', '34', '正常');
INSERT INTO `t_goods` VALUES ('64', 'cf手表穿越火线男cf指挥官二2', '156', 'images/goodsImg/2016_01_04 18-00-37.JPG', '网游胜利者', '0', '0', '30', '指挥官二代', '8', '37', '');
INSERT INTO `t_goods` VALUES ('65', 'CF热火燃情戒指', '69', 'images/goodsImg/2016_01_04 18-08-05.JPG', 'crossfire', '0', '1', '10', '燃情戒指', '8', '37', '正常');
INSERT INTO `t_goods` VALUES ('66', 'D11迷你音响', '74', 'images/goodsImg/2016_01_05 17-04-04.PNG', 'SANSUI', '0', '3', '500', '音响', '4', '24', '正常');
INSERT INTO `t_goods` VALUES ('67', '汽车载CD音乐光盘碟片', '39', 'images/goodsImg/2016_01_05 17-07-04.PNG', '缘星图书音像', '0', '3', '500', 'CD', '4', '23', '正常');
INSERT INTO `t_goods` VALUES ('68', '中国第四季好声音车载CD', '28', 'images/goodsImg/2016_01_05 17-11-14.PNG', '魔杰音乐', '0', '2', '400', 'CD', '4', '23', '正常');
INSERT INTO `t_goods` VALUES ('69', '天才在左 疯子在右(', '20', 'images/goodsImg/2016_01_05 17-13-22.PNG', '湖北中途文轩', '0', '5', '300', '图书', '4', '25', '正常');
INSERT INTO `t_goods` VALUES ('70', '迪士尼神奇英语', '57', 'images/goodsImg/2016_01_05 17-16-54.PNG', '光电音像出版社', '0', '3', '200', '英语', '4', '25', '正常');
INSERT INTO `t_goods` VALUES ('71', '欧利时 陶瓷手表女时尚腕表石英手表潮流女表 ', '398', 'images/goodsImg/2016_01_05 17-20-37.png', '欧利时', '0', '0', '360', '', '8', '38', '正常');
INSERT INTO `t_goods` VALUES ('72', '创意电子钟表', '25', 'images/goodsImg/2016_01_05 17-22-20.PNG', '大帝电子', '0', '56', '50000', '闹钟', '4', '24', '正常');
INSERT INTO `t_goods` VALUES ('73', 'EYAS 水钻合金婚纱发箍头饰', '98', 'images/goodsImg/2016_01_05 17-23-19.png', 'EYAS', '0', '0', '500', '', '8', '39', '正常');
INSERT INTO `t_goods` VALUES ('74', '天王表  正品蓝宝石石英表', '699', 'images/goodsImg/2016_01_05 17-25-47.jpg', '天王表', '0', '1', '400', '', '8', '37', '正常');
INSERT INTO `t_goods` VALUES ('75', '大象换鞋凳', '248', 'images/goodsImg/2016_01_05 17-26-01.PNG', '若然', '0', '1', '4000', '凳子', '5', '27', '正常');
INSERT INTO `t_goods` VALUES ('76', '金兄弟 儿童手镯', '298', 'images/goodsImg/2016_01_05 17-27-46.jpg', '金兄弟', '0', '3', '300', '', '8', '39', '正常');
INSERT INTO `t_goods` VALUES ('77', '家居生活小用品', '3.8', 'images/goodsImg/2016_01_05 17-39-38.png', '三毛小百货', '0', '19', '10000', '', '5', '26', '正常');
INSERT INTO `t_goods` VALUES ('78', '火影表', '99', 'images/goodsImg/2016_01_05 17-40-10.jpg', '动漫手表', '0', '1', '100', '', '8', '37', '正常');
INSERT INTO `t_goods` VALUES ('79', '饰品摆件', '169', 'images/goodsImg/2016_01_05 17-44-46.jpg', '饰品', '0', '0', '200', '', '8', '46', '正常');
INSERT INTO `t_goods` VALUES ('80', '创意懒人沙发床', '298', 'images/goodsImg/2016_01_05 17-49-21.jpg', '佳嘉优', '0', '1', '200', '', '5', '27', '正常');
INSERT INTO `t_goods` VALUES ('81', '懒人沙发', '199', 'images/goodsImg/2016_01_05 17-51-48.jpg', '佳嘉优', '0', '2', '300', '', '5', '27', '正常');
INSERT INTO `t_goods` VALUES ('82', '双人床卧室', '3066', 'images/goodsImg/2016_01_05 17-55-55.jpg', '域图', '0', '1', '500', '', '5', '27', '正常');
INSERT INTO `t_goods` VALUES ('83', '墙纸壁画', '59', 'images/goodsImg/2016_01_05 18-00-08.jpg', '米素', '0', '1', '5000', '', '5', '29', '正常');
INSERT INTO `t_goods` VALUES ('84', '产后收腹带束腰', '80', 'images/goodsImg/2016_01_05 18-06-42.PNG', '喜孕母婴', '0', '5', '400', '', '6', '32', '正常');
INSERT INTO `t_goods` VALUES ('85', '月子牙刷', '15.6', 'images/goodsImg/2016_01_05 18-08-11.PNG', '好奇贝贝', '0', '5', '300', '<h1>\r\n	<span style=\"color:#E56600;\">hkkjhnjkhnkj</span>\r\n</h1>', '6', '32', '正常');
INSERT INTO `t_goods` VALUES ('86', '卧室地毯', '56', 'images/goodsImg/2016_01_07 10-59-52.png', '富兴', '0', '8', '700', '<h1>\r\n	asdds\r\n</h1>', '5', '27', '正常');
INSERT INTO `t_goods` VALUES ('87', '便携婴儿推车', '499', 'images/goodsImg/2016_01_07 10-59-31.PNG', 'VOVO', '0', '3', '500', '', '6', '33', '正常');
INSERT INTO `t_goods` VALUES ('88', '儿童滑板车', '398', 'images/goodsImg/2016_01_07 11-01-53.PNG', 'SUKE速客', '0', '3', '300', '', '6', '31', '正常');
INSERT INTO `t_goods` VALUES ('89', '原装进口幼儿成长奶粉', '135', 'images/goodsImg/2016_01_07 11-04-00.PNG', '乐贝尔', '0', '3', '1000', '', '6', '30', '正常');
INSERT INTO `t_goods` VALUES ('90', '男童冬装外套', '199', 'images/goodsImg/2016_01_07 11-06-55.PNG', '十点伴', '0', '1', '300', '', '6', '31', '正常');
INSERT INTO `t_goods` VALUES ('91', '儿童装女童冬季连衣裙', '36', 'images/goodsImg/2016_01_07 11-08-36.PNG', '伊诺', '0', '3', '500', '', '6', '31', '正常');
INSERT INTO `t_goods` VALUES ('92', '孕妇待产包', '118', 'images/goodsImg/2016_01_07 11-10-24.PNG', '十月结晶', '0', '3', '200', '', '6', '32', '正常');
INSERT INTO `t_goods` VALUES ('93', 'Hellokitty高低床', '2299', 'images/goodsImg/2016_01_07 11-12-26.PNG', '酷漫居', '0', '4', '400', '', '8', '39', '正常');
INSERT INTO `t_goods` VALUES ('94', '创意灯光旋转木马八音盒', '29', 'images/goodsImg/2016_01_07 11-14-27.PNG', '陌上的杂货铺子', '0', '1', '490', '', '8', '39', '正常');
INSERT INTO `t_goods` VALUES ('95', '瑜然美马来西亚纯甘油', '27', 'images/goodsImg/2016_01_07 11-23-43.PNG', '瑜然美', '0', '1', '2000', '', '9', '40', '正常');
INSERT INTO `t_goods` VALUES ('96', '相宜本草洗面奶', '23', 'images/goodsImg/2016_01_07 11-27-45.PNG', '相宜本草', '0', '0', '500', '', '9', '41', '正常');
INSERT INTO `t_goods` VALUES ('97', '丹姿他能量男士洗面奶', '38', 'images/goodsImg/2016_01_07 11-29-47.PNG', '他能量', '0', '2', '100', '', '9', '40', '正常');
INSERT INTO `t_goods` VALUES ('98', '欧诗漫卸妆水乳液 ', '39', 'images/goodsImg/2016_01_07 11-32-18.PNG', '欧诗漫', '0', '0', '2000', '', '9', '40', '正常');
INSERT INTO `t_goods` VALUES ('99', '黛丽丹男士洗面奶', '79', 'images/goodsImg/2016_01_07 11-35-21.PNG', '戴丽丹', '0', '1', '400', '', '9', '40', '正常');
INSERT INTO `t_goods` VALUES ('100', '雅丽洁 ', '185', 'images/goodsImg/2016_01_07 11-36-38.PNG', '优质汇', '0', '1', '300', '', '9', '40', '正常');
INSERT INTO `t_goods` VALUES ('101', '隆力奇果木肌密润护肤', '88', 'images/goodsImg/2016_01_07 11-39-05.PNG', '肌言堂', '0', '3', '700', '<h1>\r\n	<span style=\"color:#E53333;\">jkasdhfjkdhjsk</span>\r\n</h1>\r\n<p>\r\n	<img src=\"/zhaiShop/images/attached/image/20160108/20160108153112_907.png\" alt=\"\" height=\"725\" width=\"725\" />\r\n</p>', '9', '40', '正常');

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` varchar(20) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `addressId` int(11) DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `state` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('2016011307460639', '10006', '119.7', '1', '', '2016-01-13 07:46:06', '1');
INSERT INTO `t_order` VALUES ('2016011307485276', '10006', '119.7', '2', '', '2016-01-13 07:48:52', '6');
INSERT INTO `t_order` VALUES ('2016011307513246', '10006', '1.5', '1', '', '2016-01-13 07:51:32', '1');
INSERT INTO `t_order` VALUES ('201601130815549', '10006', '7.5', '2', '', '2016-01-13 08:15:54', '6');
INSERT INTO `t_order` VALUES ('2016011308164232', '10006', '7.5', '2', '', '2016-01-13 08:16:42', '6');
INSERT INTO `t_order` VALUES ('2016011308172058', '10006', '16.5', '2', '', '2016-01-13 08:17:20', '6');
INSERT INTO `t_order` VALUES ('2016011308345570', '10006', '1.5', '2', '', '2016-01-13 08:34:55', '6');
INSERT INTO `t_order` VALUES ('2016011310462349', '10026', '1.5', '1', '', '2016-01-13 10:46:23', '4');
INSERT INTO `t_order` VALUES ('2016011318543413', '10026', '9.6', '3', '速度发货', '2016-01-13 18:54:34', '6');
INSERT INTO `t_order` VALUES ('2016011318583217', '10026', '88', '1', '', '2016-01-13 18:58:32', '4');
INSERT INTO `t_order` VALUES ('2016011319105352', '10026', '13.8', '4', '统一规划', '2016-01-13 19:10:53', '5');
INSERT INTO `t_order` VALUES ('2016011516103677', '10026', '132.8', '3', '', '2016-01-15 16:10:36', '6');
INSERT INTO `t_order` VALUES ('2016012215144383', '10026', '20', '4', '', '2016-01-22 15:14:43', '6');
INSERT INTO `t_order` VALUES ('2016012719250813', '10026', '59', '1', '', '2016-01-27 19:25:08', '4');
INSERT INTO `t_order` VALUES ('2016012721014417', '10026', '1.8', '4', '', '2016-01-27 21:01:44', '5');
INSERT INTO `t_order` VALUES ('2016020716541516', '10026', '6999', '4', '', '2016-02-07 16:54:15', '6');
INSERT INTO `t_order` VALUES ('2016020912435057', '10026', '7.5', '1', '', '2016-02-09 12:43:50', '4');
INSERT INTO `t_order` VALUES ('2016021711453647', '10026', '47.9', '4', '', '2016-02-17 11:45:36', '6');
INSERT INTO `t_order` VALUES ('2016021821231691', '10026', '1.8', '1', '', '2016-02-18 21:23:16', '4');
INSERT INTO `t_order` VALUES ('2016022017201783', '10026', '233.8', '1', '', '2016-02-20 17:20:17', '4');
INSERT INTO `t_order` VALUES ('2016022017210141', '10026', '59', '1', '', '2016-02-20 17:21:01', '4');
INSERT INTO `t_order` VALUES ('2016022017211980', '10026', '1.8', '4', '', '2016-02-20 17:21:19', '6');
INSERT INTO `t_order` VALUES ('201602201723459', '10026', '7.5', '1', '', '2016-02-20 17:23:45', '4');
INSERT INTO `t_order` VALUES ('2016022117443744', '10026', '1.5', '3', '今天天气挺好', '2016-02-21 17:44:37', '6');
INSERT INTO `t_order` VALUES ('2016022118013895', '10026', '100.7', '1', '', '2016-02-21 18:01:38', '4');
INSERT INTO `t_order` VALUES ('2016022208160023', '10026', '1.5', '1', '', '2016-02-22 08:16:00', '1');
INSERT INTO `t_order` VALUES ('2016022208284819', '10026', '1.5', '1', '', '2016-02-22 08:28:48', '4');
INSERT INTO `t_order` VALUES ('2016022208290032', '10026', '1.5', '1', '', '2016-02-22 08:29:00', '4');
INSERT INTO `t_order` VALUES ('2016022208442354', '10026', '1.8', '1', '', '2016-02-22 08:44:23', '4');
INSERT INTO `t_order` VALUES ('2016022212494765', '10026', '5502.5', '1', '', '2016-02-22 12:49:47', '4');
INSERT INTO `t_order` VALUES ('2016022309514842', '10026', '1.5', '3', '速度发货申通快递', '2016-02-23 09:51:48', '6');
INSERT INTO `t_order` VALUES ('2016022310121696', '10026', '6999', '4', '', '2016-02-23 10:12:16', '5');
INSERT INTO `t_order` VALUES ('2016022313380372', '10024', '1.5', '5', '66666', '2016-02-23 13:38:03', '6');
INSERT INTO `t_order` VALUES ('2016022313451343', '10026', '1.8', '1', '', '2016-02-23 13:45:13', '4');
INSERT INTO `t_order` VALUES ('2016022314320423', '10026', '1.5', '1', '', '2016-02-23 14:32:04', '1');
INSERT INTO `t_order` VALUES ('2016022314524674', '10026', '1.5', '1', '', '2016-02-23 14:52:46', '1');
INSERT INTO `t_order` VALUES ('2016022409403348', '10026', '1.5', '7', '', '2016-02-24 09:40:33', '6');
INSERT INTO `t_order` VALUES ('2016022410184959', '10026', '59', '1', '', '2016-02-24 10:18:49', '1');
INSERT INTO `t_order` VALUES ('2016022411011772', '10026', '1.5', '3', '', '2016-02-24 11:01:17', '6');
INSERT INTO `t_order` VALUES ('2016022412232273', '10026', '1.5', '1', '', '2016-02-24 12:23:22', '1');
INSERT INTO `t_order` VALUES ('2016022413585874', '10026', '59', '1', '', '2016-02-24 13:58:58', '4');
INSERT INTO `t_order` VALUES ('2016022509325562', '10026', '59', '17', '', '2016-02-25 09:32:55', '6');
INSERT INTO `t_order` VALUES ('2016022509355766', '10026', '59', '1', '', '2016-02-25 09:35:57', '1');
INSERT INTO `t_order` VALUES ('2016022509412440', '10026', '1.5', '17', '', '2016-02-25 09:41:24', '6');
INSERT INTO `t_order` VALUES ('2016022509420785', '10026', '9', '17', '', '2016-02-25 09:42:07', '6');
INSERT INTO `t_order` VALUES ('2016022509425623', '10026', '3', '17', '', '2016-02-25 09:42:56', '6');
INSERT INTO `t_order` VALUES ('2016022509431821', '10026', '1.5', '17', '', '2016-02-25 09:43:18', '6');
INSERT INTO `t_order` VALUES ('2016022509454971', '10026', '1.5', '17', '', '2016-02-25 09:45:49', '6');
INSERT INTO `t_order` VALUES ('2016022509475725', '10026', '1.5', '17', '', '2016-02-25 09:47:57', '6');
INSERT INTO `t_order` VALUES ('2016022509482025', '10026', '1.5', '1', '', '2016-02-25 09:48:20', '1');
INSERT INTO `t_order` VALUES ('2016022509493161', '10026', '1.5', '1', '', '2016-02-25 09:49:31', '1');
INSERT INTO `t_order` VALUES ('2016022509494048', '0', '1.5', '1', '', '2016-02-25 09:49:40', '1');
INSERT INTO `t_order` VALUES ('2016022509494583', '0', '1.5', '13', '', '2016-02-25 09:49:45', '6');

-- ----------------------------
-- Table structure for `t_orderitem`
-- ----------------------------
DROP TABLE IF EXISTS `t_orderitem`;
CREATE TABLE `t_orderitem` (
  `id` int(11) DEFAULT NULL,
  `goodsId` int(11) DEFAULT NULL,
  `goodsName` varchar(100) DEFAULT NULL,
  `proPic` varchar(50) DEFAULT NULL,
  `goodsPrice` double DEFAULT NULL,
  `sum` int(11) DEFAULT NULL,
  `subTotal` double DEFAULT NULL,
  `orderId` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_orderitem
-- ----------------------------
INSERT INTO `t_orderitem` VALUES ('1001', '9', '金龙鱼油', 'images/bigTypeImg/2016_01_03 21-19-51.png', '39.9', '3', '119.7', '2016011307460639');
INSERT INTO `t_orderitem` VALUES ('1002', '9', '金龙鱼油', 'images/bigTypeImg/2016_01_03 21-19-51.png', '39.9', '3', '119.7', '2016011307485276');
INSERT INTO `t_orderitem` VALUES ('1003', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016011307513246');
INSERT INTO `t_orderitem` VALUES ('1004', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '5', '7.5', '201601130815549');
INSERT INTO `t_orderitem` VALUES ('1005', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '5', '7.5', '2016011308164232');
INSERT INTO `t_orderitem` VALUES ('1006', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '5', '7.5', '2016011308172058');
INSERT INTO `t_orderitem` VALUES ('1007', '18', '红柚', 'images/goodsImg/2016_01_04 15-23-02.jpg', '1.8', '5', '9', '2016011308172058');
INSERT INTO `t_orderitem` VALUES ('1008', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016011308345570');
INSERT INTO `t_orderitem` VALUES ('1009', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016011310462349');
INSERT INTO `t_orderitem` VALUES ('1010', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '4', '6', '2016011318543413');
INSERT INTO `t_orderitem` VALUES ('1011', '18', '红柚', 'images/goodsImg/2016_01_04 15-23-02.jpg', '1.8', '2', '3.6', '2016011318543413');
INSERT INTO `t_orderitem` VALUES ('1012', '2', '森马牛仔裤', 'images/goodsImg/2016_01_04 15-27-43.png', '88', '1', '88', '2016011318583217');
INSERT INTO `t_orderitem` VALUES ('1013', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '8', '12', '2016011319105352');
INSERT INTO `t_orderitem` VALUES ('1014', '18', '红柚', 'images/goodsImg/2016_01_04 15-23-02.jpg', '1.8', '1', '1.8', '2016011319105352');
INSERT INTO `t_orderitem` VALUES ('1015', '77', '家居生活小用品', 'images/goodsImg/2016_01_05 17-39-38.png', '3.8', '1', '3.8', '2016011516103677');
INSERT INTO `t_orderitem` VALUES ('1016', '28', '时尚女裙', 'images/goodsImg/2016_01_04 16-38-48.jpg', '129', '1', '129', '2016011516103677');
INSERT INTO `t_orderitem` VALUES ('1017', '39', '当下的力量 精装新版珍藏版 心灵经典读物', 'images/goodsImg/2016_01_04 17-06-46.JPG', '20', '1', '20', '2016012215144383');
INSERT INTO `t_orderitem` VALUES ('1018', '13', '【世果汇】越南青芒果8斤 进口新鲜水果 青皮芒果 热带水果 包邮 ', 'images/goodsImg/2016_01_04 09-38-29.jpg', '59', '1', '59', '2016012719250813');
INSERT INTO `t_orderitem` VALUES ('1019', '18', '红柚', 'images/goodsImg/2016_01_04 15-23-02.jpg', '1.8', '1', '1.8', '2016012721014417');
INSERT INTO `t_orderitem` VALUES ('1020', '16', '雷神911黄金版', 'images/bigTypeImg/2016_01_04 15-14-37.png', '6999', '1', '6999', '2016020716541516');
INSERT INTO `t_orderitem` VALUES ('1021', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '5', '7.5', '2016020912435057');
INSERT INTO `t_orderitem` VALUES ('1022', '35', '冬季新款单肩斜挎女士包包韩版潮流', 'images/goodsImg/2016_01_04 16-57-22.JPG', '47.9', '1', '47.9', '2016021711453647');
INSERT INTO `t_orderitem` VALUES ('1023', '18', '红柚', 'images/goodsImg/2016_01_04 15-23-02.jpg', '1.8', '1', '1.8', '2016021821231691');
INSERT INTO `t_orderitem` VALUES ('1024', '18', '红柚', 'images/goodsImg/2016_01_04 15-23-02.jpg', '1.8', '1', '1.8', '2016022017201783');
INSERT INTO `t_orderitem` VALUES ('1025', '12', '包邮！意大利费列罗巧克力水晶礼盒装 T30粒装 散装喜糖正品 批发', 'images/goodsImg/2016_01_04 09-10-01.jpg', '58', '4', '232', '2016022017201783');
INSERT INTO `t_orderitem` VALUES ('1026', '13', '【世果汇】越南青芒果8斤 进口新鲜水果 青皮芒果 热带水果 包邮 ', 'images/goodsImg/2016_01_04 09-38-29.jpg', '59', '1', '59', '2016022017210141');
INSERT INTO `t_orderitem` VALUES ('1027', '18', '红柚', 'images/goodsImg/2016_01_04 15-23-02.jpg', '1.8', '1', '1.8', '2016022017211980');
INSERT INTO `t_orderitem` VALUES ('1028', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '5', '7.5', '201602201723459');
INSERT INTO `t_orderitem` VALUES ('1029', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016022117443744');
INSERT INTO `t_orderitem` VALUES ('1030', '13', '【世果汇】越南青芒果8斤 进口新鲜水果 青皮芒果 热带水果 包邮 ', 'images/goodsImg/2016_01_04 09-38-29.jpg', '59', '1', '59', '2016022118013895');
INSERT INTO `t_orderitem` VALUES ('1031', '18', '红柚', 'images/goodsImg/2016_01_04 15-23-02.jpg', '1.8', '1', '1.8', '2016022118013895');
INSERT INTO `t_orderitem` VALUES ('1032', '9', '金龙鱼油', 'images/bigTypeImg/2016_01_03 21-19-51.png', '39.9', '1', '39.9', '2016022118013895');
INSERT INTO `t_orderitem` VALUES ('1033', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016022208160023');
INSERT INTO `t_orderitem` VALUES ('1034', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016022208284819');
INSERT INTO `t_orderitem` VALUES ('1035', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016022208290032');
INSERT INTO `t_orderitem` VALUES ('1036', '18', '红柚', 'images/goodsImg/2016_01_04 15-23-02.jpg', '1.8', '1', '1.8', '2016022208442354');
INSERT INTO `t_orderitem` VALUES ('1037', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '5', '7.5', '2016022212494765');
INSERT INTO `t_orderitem` VALUES ('1038', '24', '扫地机器人', 'images/goodsImg/2016_01_04 15-57-40.png', '1099', '5', '5495', '2016022212494765');
INSERT INTO `t_orderitem` VALUES ('1039', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016022309514842');
INSERT INTO `t_orderitem` VALUES ('1040', '16', '雷神911黄金版', 'images/bigTypeImg/2016_01_04 15-14-37.png', '6999', '1', '6999', '2016022310121696');
INSERT INTO `t_orderitem` VALUES ('1041', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016022313380372');
INSERT INTO `t_orderitem` VALUES ('1042', '18', '红柚', 'images/goodsImg/2016_01_04 15-23-02.jpg', '1.8', '1', '1.8', '2016022313451343');
INSERT INTO `t_orderitem` VALUES ('1043', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016022314320423');
INSERT INTO `t_orderitem` VALUES ('1044', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016022314524674');
INSERT INTO `t_orderitem` VALUES ('1045', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016022409403348');
INSERT INTO `t_orderitem` VALUES ('1046', '13', '【世果汇】越南青芒果8斤 进口新鲜水果 青皮芒果 热带水果 包邮 ', 'images/goodsImg/2016_01_04 09-38-29.jpg', '59', '1', '59', '2016022410184959');
INSERT INTO `t_orderitem` VALUES ('1047', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016022411011772');
INSERT INTO `t_orderitem` VALUES ('1048', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016022412232273');
INSERT INTO `t_orderitem` VALUES ('1049', '13', '【世果汇】越南青芒果8斤 进口新鲜水果 青皮芒果 热带水果 包邮 ', 'images/goodsImg/2016_01_04 09-38-29.jpg', '59', '1', '59', '2016022413585874');
INSERT INTO `t_orderitem` VALUES ('1050', '13', '【世果汇】越南青芒果8斤 进口新鲜水果 青皮芒果 热带水果 包邮 ', 'images/goodsImg/2016_01_04 09-38-29.jpg', '59', '1', '59', '2016022509325562');
INSERT INTO `t_orderitem` VALUES ('1051', '13', '【世果汇】越南青芒果8斤 进口新鲜水果 青皮芒果 热带水果 包邮 ', 'images/goodsImg/2016_01_04 09-38-29.jpg', '59', '1', '59', '2016022509355766');
INSERT INTO `t_orderitem` VALUES ('1052', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016022509412440');
INSERT INTO `t_orderitem` VALUES ('1053', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '6', '9', '2016022509420785');
INSERT INTO `t_orderitem` VALUES ('1054', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '2', '3', '2016022509425623');
INSERT INTO `t_orderitem` VALUES ('1055', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016022509431821');
INSERT INTO `t_orderitem` VALUES ('1056', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016022509454971');
INSERT INTO `t_orderitem` VALUES ('1057', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016022509475725');
INSERT INTO `t_orderitem` VALUES ('1058', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016022509482025');
INSERT INTO `t_orderitem` VALUES ('1059', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016022509493161');
INSERT INTO `t_orderitem` VALUES ('1060', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016022509494048');
INSERT INTO `t_orderitem` VALUES ('1061', '20', '海南芝麻蕉', 'images/goodsImg/2016_01_04 15-34-40.jpg', '1.5', '1', '1.5', '2016022509494583');

-- ----------------------------
-- Table structure for `t_shoppingcart`
-- ----------------------------
DROP TABLE IF EXISTS `t_shoppingcart`;
CREATE TABLE `t_shoppingcart` (
  `userId` int(11) DEFAULT NULL,
  `goodsId` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `goodsPrice` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_shoppingcart
-- ----------------------------
INSERT INTO `t_shoppingcart` VALUES ('10026', '18', '1', '1.8');
INSERT INTO `t_shoppingcart` VALUES ('10026', '7', '1', '0.8');

-- ----------------------------
-- Table structure for `t_slide`
-- ----------------------------
DROP TABLE IF EXISTS `t_slide`;
CREATE TABLE `t_slide` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `proPic` varchar(50) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_slide
-- ----------------------------
INSERT INTO `t_slide` VALUES ('1', '幻灯片1', 'images/slide/1.jpg', 'http://www.baidu.com');
INSERT INTO `t_slide` VALUES ('2', '幻灯片2', 'images/slide/2.jpg', 'http://ilt.me');
INSERT INTO `t_slide` VALUES ('3', '幻灯片3', 'images/slide/3.jpg', '这是幻灯片三');
INSERT INTO `t_slide` VALUES ('4', '幻灯片4', 'images/slide/4.jpg', '这是幻灯片四');
INSERT INTO `t_slide` VALUES ('5', '幻灯片5', 'images/slide/5.jpg', '这是幻灯片五');

-- ----------------------------
-- Table structure for `t_smalltype`
-- ----------------------------
DROP TABLE IF EXISTS `t_smalltype`;
CREATE TABLE `t_smalltype` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `bigTypeId` int(11) DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_smalltype
-- ----------------------------
INSERT INTO `t_smalltype` VALUES ('3', '森马裤子', '3', '很不错的裤子');
INSERT INTO `t_smalltype` VALUES ('4', '国内水果', '1', '');
INSERT INTO `t_smalltype` VALUES ('5', '蔬菜', '1', '');
INSERT INTO `t_smalltype` VALUES ('6', '粮油生鲜', '1', '');
INSERT INTO `t_smalltype` VALUES ('7', '鼎极名酒', '1', '');
INSERT INTO `t_smalltype` VALUES ('8', '休闲食品', '1', '');
INSERT INTO `t_smalltype` VALUES ('9', '饮料牛奶', '1', '');
INSERT INTO `t_smalltype` VALUES ('10', '粮油生鲜', '1', '');
INSERT INTO `t_smalltype` VALUES ('11', '进口水果', '1', '');
INSERT INTO `t_smalltype` VALUES ('12', '办公网络', '2', '');
INSERT INTO `t_smalltype` VALUES ('13', '电竞游戏', '2', '');
INSERT INTO `t_smalltype` VALUES ('14', 'DIV硬件', '2', '');
INSERT INTO `t_smalltype` VALUES ('15', '潮流影音', '2', '');
INSERT INTO `t_smalltype` VALUES ('16', '手机配件', '2', '');
INSERT INTO `t_smalltype` VALUES ('17', '手机通讯', '2', '');
INSERT INTO `t_smalltype` VALUES ('18', '珠宝首饰', '3', '');
INSERT INTO `t_smalltype` VALUES ('19', '服饰配件', '3', '');
INSERT INTO `t_smalltype` VALUES ('20', '男装', '3', '');
INSERT INTO `t_smalltype` VALUES ('21', '女装', '3', '');
INSERT INTO `t_smalltype` VALUES ('22', '人文社科', '4', '');
INSERT INTO `t_smalltype` VALUES ('23', '影视DVD', '4', '');
INSERT INTO `t_smalltype` VALUES ('24', '数字音乐', '4', '');
INSERT INTO `t_smalltype` VALUES ('25', '电子图书', '4', '');
INSERT INTO `t_smalltype` VALUES ('26', '家用厨具', '5', '');
INSERT INTO `t_smalltype` VALUES ('27', '家居家具', '5', '');
INSERT INTO `t_smalltype` VALUES ('28', '家用百货', '5', '');
INSERT INTO `t_smalltype` VALUES ('29', '家饰', '5', '');
INSERT INTO `t_smalltype` VALUES ('30', '奶粉', '6', '');
INSERT INTO `t_smalltype` VALUES ('31', '童装玩具', '6', '');
INSERT INTO `t_smalltype` VALUES ('32', '孕产必备', '6', '');
INSERT INTO `t_smalltype` VALUES ('33', '大牌推车', '6', '');
INSERT INTO `t_smalltype` VALUES ('34', '维修保养', '7', '');
INSERT INTO `t_smalltype` VALUES ('35', '全部整车', '7', '');
INSERT INTO `t_smalltype` VALUES ('36', '机油', '7', '');
INSERT INTO `t_smalltype` VALUES ('37', '男士手表', '8', '');
INSERT INTO `t_smalltype` VALUES ('38', '女士手表', '8', '');
INSERT INTO `t_smalltype` VALUES ('39', '儿童金品', '8', '');
INSERT INTO `t_smalltype` VALUES ('40', '面部护肤', '9', '');
INSERT INTO `t_smalltype` VALUES ('41', '祛痘清洁', '9', '');
INSERT INTO `t_smalltype` VALUES ('42', '电脑 div', '2', '');
INSERT INTO `t_smalltype` VALUES ('43', '相机、摄影、投影', '2', '');
INSERT INTO `t_smalltype` VALUES ('44', '生活电器', '2', '');
INSERT INTO `t_smalltype` VALUES ('45', '鞋包', '3', '');
INSERT INTO `t_smalltype` VALUES ('46', '男女士饰品', '8', '');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) DEFAULT NULL,
  `userName` varchar(50) DEFAULT NULL,
  `trueName` varchar(50) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `birthday` varchar(10) DEFAULT NULL,
  `statusID` varchar(50) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `userType` int(11) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('10006', 'zuidaima', '刘振兴', '男', '1995-11-24', '412821199602066838', '15638377962', '河南省漯河市源汇区大学路123号', 'zuidaima@qq.com', '0', '111111');
INSERT INTO `t_user` VALUES ('10019', 'dabo', '王胜博', '男', '2015-12-01', '1111', '15638377961', '许昌', '123@qq.com', '1', '123');
INSERT INTO `t_user` VALUES ('10023', 'bian  bian', '周志芳', '女', '2015-12-01', '412821199602066839', '18790908921', '地狱', '81999686@qq.com', '1', '3838438');
INSERT INTO `t_user` VALUES ('10024', 'heng', '孟恒6', '男', '1995-06-08', '4114211994022312345', '15729351792', '商丘', '81999686@qq.com', '1', '10010');
INSERT INTO `t_user` VALUES ('10025', 'zx', null, null, null, null, null, null, null, null, '123');
INSERT INTO `t_user` VALUES ('10026', 'lzx6', '刘振兴', '男', '1995-11-01', '412821199602066838', '15638377962', null, '8589561@qq.com', null, 'lzx19951124');
INSERT INTO `t_user` VALUES ('10027', 'liuchao', '刘超', '男', '2016-01-06', '412821', '15638377962', '', '1@qq.com', '1', '654321');
INSERT INTO `t_user` VALUES ('10029', 'lzx666', null, null, null, null, null, null, null, null, 'lzx19951124');
