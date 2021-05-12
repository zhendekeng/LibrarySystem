/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.7.33 : Database - library
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`library` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `library`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `accountId` int(11) NOT NULL AUTO_INCREMENT,
  `accountName` varchar(22) NOT NULL,
  `accountPass` varchar(22) NOT NULL,
  `accountRole` int(11) NOT NULL,
  PRIMARY KEY (`accountId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `account` */

insert  into `account`(`accountId`,`accountName`,`accountPass`,`accountRole`) values (1,'123456','123456',1),(2,'12345','123456',2),(3,'1234567','123456',2),(4,'12334567','123456',2),(5,'369258','123456',2),(6,'123698745','123456',2),(7,'654123','123456',1);

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `adminId` int(11) NOT NULL AUTO_INCREMENT,
  `adminEmail` varchar(22) DEFAULT NULL,
  `accountId` int(11) NOT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`adminId`,`adminEmail`,`accountId`) values (1,'1677698157@qq.com',1),(2,'1677@qq.com',7);

/*Table structure for table `bookInfo` */

DROP TABLE IF EXISTS `bookInfo`;

CREATE TABLE `bookInfo` (
  `bookid` int(11) NOT NULL AUTO_INCREMENT,
  `bookname` varchar(60) DEFAULT NULL,
  `bookauthor` varchar(45) DEFAULT NULL,
  `bookpublish` varchar(60) DEFAULT NULL,
  `bookISBN` varchar(45) DEFAULT NULL,
  `bookintroduction` blob,
  `booklanguage` varchar(12) DEFAULT NULL,
  `bookprice` decimal(12,0) DEFAULT NULL,
  `bookpubDate` date DEFAULT NULL,
  `booktypeId` int(11) DEFAULT NULL,
  `booknumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`bookid`),
  KEY `bookty` (`booktypeId`),
  CONSTRAINT `bookInfo_ibfk_1` FOREIGN KEY (`booktypeId`) REFERENCES `bookType` (`typeid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `bookInfo` */

insert  into `bookInfo`(`bookid`,`bookname`,`bookauthor`,`bookpublish`,`bookISBN`,`bookintroduction`,`booklanguage`,`bookprice`,`bookpubDate`,`booktypeId`,`booknumber`) values (3,'大雪中的山庄','东野圭吾','北京十月文艺出版社','978-7-5302-1683-5','东野圭吾长篇小说杰作，中文简体首次出版。 一出没有剧本的舞台剧，为什么能让七个演员赌上全部人生.东野圭吾就是有这样过人的本领，能从充满悬念的案子写出荡气回肠的情感，在极其周密曲折的同时写出人性的黑暗与美丽。 一家与外界隔绝的民宿里，七个演员被要求住满四天，接受导演的考验，但不断有人失踪。难道这并非正常排练，而是有人布下陷阱要杀他们。 那时候我开始喜欢上戏剧和音乐，《大雪中的山庄》一书的灵感就来源于此。我相信这次的诡计肯定会让人大吃一惊。——东野圭吾','德文','35','2017-06-01',9,1),(4,'三生三世 十里桃花','唐七公子 ','沈阳出版社','978-7-5441-3800-0','三生三世，她和他，是否注定背负一段纠缠的姻缘？\n三生三世，她和他，是否终能互许一个生生世世的承诺？','中文','27','2009-01-06',7,2),(6,'11处特工皇妃','潇湘冬儿','江苏文艺出版社','978-7-5399-4389-3','《11处特工皇妃(套装上中下册)》内容简介：她是国安局军情十一处惊才绝艳的王牌军师——收集情报、策划部署、进不友好国家布置暗杀任务……她运筹帷幄之中，决胜于千里之外，堪称军情局大厦的定海神针。','中文','75','2012-05-05',7,3),(7,'人类简史','[以色列] 尤瓦尔·赫拉利 ','中信出版社','978-7-5086-4735-7','十万年前，地球上至少有六种不同的人\n但今日，世界舞台为什么只剩下了我们自己？\n从只能啃食虎狼吃剩的残骨的猿人，到跃居食物链顶端的智人，\n从雪维洞穴壁上的原始人手印，到阿姆斯壮踩上月球的脚印，\n从认知革命、农业革命，到科学革命、生物科技革命，\n我们如何登上世界舞台成为万物之灵的？\n从公元前1776年的《汉摩拉比法典》，到1776年的美国独立宣言，\n从帝国主义、资本主义，到自由主义、消费主义，\n从兽欲，到物欲，从兽性、人性，到神性，\n我们了解自己吗？我们过得更快乐吗？\n我们究竟希望自己得到什么、变成什么？','英文','68','2014-11-01',11,3),(8,'明朝那些事儿（1-9）','当年明月 ','中国海关出版社','978-7-8016-5608-7','《明朝那些事儿》讲述从1344年到1644年，明朝三百年间的历史。作品以史料为基础，以年代和具体人物为主线，运用小说的笔法，对明朝十七帝和其他王公权贵和小人物的命运进行全景展示，尤其对官场政治、战争、帝王心术着墨最多。作品也是一部明朝政治经济制度、人伦道德的演义。','英文','68','2009-04-06',11,4),(9,'经济学原理（上下)','[美] 曼昆','机械工业出版社','978-7-1111-2676-8','此《经济学原理》的第3版把较多篇幅用于应用与政策，较少篇幅用于正规的经济理论。书中主要从供给与需求、企业行为与消费者选择理论、长期经济增长与短期经济波动以及宏观经济政策等角度深入浅出地剖析了经济学家们的世界观。','英文','68','2003-08-05',6,3),(10,'方向','马克-安托万·马修 ','后浪丨北京联合出版公司','978-7-0201-2526-5','《方向》即便在马修的作品中也算得最独特的：不着一字，尽得风流。原作本无一字，标题只是一个→，出版时才加了个书名Sens——既可以指“方向”，也可以指“意义”。 《方向》没有“字”，但有自己的语言——请读者在尽情释放想象力和独立思考之余，破解作者的密码，听听作者对荒诞的看法。','英文','100','2017-04-01',9,4),(11,'画的秘密','马克-安托万·马修 ','北京联合出版公司·后浪出版公司','978-7-5502-6560-8','一本关于友情的疗伤图像小说，直击人内心最为隐秘的情感。 一部追寻艺术的纸上悬疑电影，揭示命运宇宙中奇诡的真相。 ★ 《画的秘密》荣获欧洲第二大漫画节“瑞士谢尔漫画节最佳作品奖”。 作者曾两度夺得安古兰国际漫画节重要奖项。 ★ 《画的秘密》是一部罕见的、结合了拼贴、镜像、3D等叙事手法的实验型漫画作品。作者巧妙地调度光线、纬度、时间、记忆，在一个悬念重重又温情治愈的故事中，注入了一个有关命运的哲学议题。','英文','60','2016-01-01',9,4),(12,'造彩虹的人','东野圭吾 ','北京十月文艺出版社','978-7-5302-1685-9','其实每个人身上都会发光，但只有纯粹渴求光芒的人才能看到。 从那一刻起，人生会发生奇妙的转折。功一高中退学后无所事事，加入暴走族消极度日；政史备战高考却无法集中精神，几近崩溃；辉美因家庭不和对生活失去勇气，决定自杀。面对糟糕的人生，他们无所适从，直到一天夜里，一道如同彩虹的光闯进视野。 凝视着那道光，原本几乎要耗尽的气力，源源不断地涌了出来。一切又开始充满希望。打起精神来，不能输。到这儿来呀，快来呀——那道光低声呼唤着。 他们追逐着呼唤，到达一座楼顶，看到一个人正用七彩绚烂的光束演奏出奇妙的旋律。 他们没想到，这一晚看到的彩虹，会让自己的人生彻底转...','中文','40','2017-06-01',9,4),(13,'控方证人','阿加莎·克里斯蒂','新星出版社','978-7-5133-2574-5','经典同名话剧六十年常演不衰； 比利•怀尔德执导同名电影，获奥斯卡金像奖六项提名！ 阿加莎对神秘事物的向往大约来自于一种女性祖先的遗传，在足不出户的生活里，生出对世界又好奇又恐惧的幻想。 ——王安忆 伦纳德•沃尔被控谋杀富婆艾米丽以图染指其巨额遗产，他却一再表明自己的无辜。伦纳德的妻子是唯一能够证明他无罪的证人，却以控方证人的身份出庭指证其确实犯有谋杀罪。伦纳德几乎陷入绝境，直到一个神秘女人的出现…… 墙上的犬形图案；召唤死亡的收音机；蓝色瓷罐的秘密；一只疯狂的灰猫……十一篇神秘的怪谈，最可怕的不是“幽灵”，而是你心中的魔鬼。','中文','35','2017-05-01',9,4),(14,'少有人走的路','M·斯科特·派克','吉林文史出版社','978-7-8070-2377-7','这本书处处透露出沟通与理解的意味，它跨越时代限制，帮助我们探索爱的本质，引导我们过上崭新，宁静而丰富的生活；它帮助我们学习爱，也学习独立；它教诲我们成为更称职的、更有理解心的父母。归根到底，它告诉我们怎样找到真正的自我。 正如开篇所言：人生苦难重重。M·斯科特·派克让我们更加清楚：人生是一场艰辛之旅，心智成熟的旅程相当漫长。但是，他没有让我们感到恐惧，相反，他带领我们去经历一系列艰难乃至痛苦的转变，最终达到自我认知的更高境界。','中文','26','2007-01-01',9,4),(15,'追寻生命的意义','[奥] 维克多·弗兰克','新华出版社','978-7-5011-6273-4','《追寻生命的意义》是一个人面对巨大的苦难时，用来拯救自己的内在世界，同时也是一个关于每个人存在的价值和能者多劳们生存的社会所应担负职责的思考。本书对于每一个想要了解我们这个时代的人来说，都是一部必不可少的读物。这是一部令人鼓舞的杰作……他是一个不可思议的人，任何人都可以从他无比痛苦的经历中，获得拯救自己的经验……高度推荐。','中文','12','2003-01-01',9,4),(16,'秘密花园','乔汉娜·贝斯福','北京联合出版公司','978-7-5502-5258-5','欢迎来到秘密花园！ 在这个笔墨编织出的美丽世界中漫步吧 涂上你喜爱的颜色，为花园带来生机和活力 发现隐藏其中的各类小生物，与它们共舞 激活创造力，描绘那些未完成的仙踪秘境 各个年龄段的艺术家和“园丁”都可以来尝试喔！','中文','42','2015-06-01',9,4),(17,'了不起的盖茨比　',' (美)菲茨杰拉德','四川文艺出版社','978-7-5411-5167-5','《了不起的盖茨比》的问世，奠定了弗·司各特·菲茨杰拉德在现代美国文学史上的地位，成了20年代“爵士时代”的发言人和“迷惘的一代”的代表作家之一。20世纪末，美国学术界权威在百年英语文学长河中选出一百部最优秀的小说，《了不起的盖茨比》高居第二位，并被多次搬上银幕和舞台。','英文','40','2018-11-01',9,3),(18,'简·爱','[英] 夏洛蒂·勃朗特','人民出版社','978-7-0102-1466-5','《简·爱》是英国女作家夏洛蒂·勃朗特创作的一部长篇小说，是一部具有自传色彩的作品。作品讲述一位从小便成孤儿的英国女子在各种磨难中不断追求自由与尊严，坚持自我，最终获得幸福的故事。小说展示了男女主人公曲折起伏的爱情经历，成功塑造了一个敢于反抗一切旧习俗和偏见，敢于争取自由和平等地位的妇女形象。','英文','10','2020-04-01',9,1),(19,'野性的呼唤 ','[美]杰克·伦敦','三秦出版社','978-7-5518-1883-4','《野性的呼唤》讲述了一条名叫“巴克”的家犬被迫变成野狼的故事。巴克是米勒法官家中一条养尊处优的宠物狗，过着无忧无虑的生活。因为错信狡猾的花匠，被拐卖到荒蛮严寒的北方。面对人类的棍棒，以及残酷的生存环境，狼的野性在他身上逐渐恢复，它设法克服一切难以想象的困难，在极恶的险境中求生。最终巴克重归森林，成为狼群之首。','英文','45','2018-09-01',9,1);

/*Table structure for table `bookType` */

DROP TABLE IF EXISTS `bookType`;

CREATE TABLE `bookType` (
  `typeid` int(11) NOT NULL,
  `typename` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bookType` */

insert  into `bookType`(`typeid`,`typename`) values (1,'马克思主义'),(2,'哲学'),(3,'社会科学总论'),(4,'政治法律'),(5,'军事'),(6,'经济'),(7,'文化'),(8,'语言'),(9,'文学'),(10,'艺术'),(11,'历史地理'),(12,'自然科学总论'),(13,'数理科学和化学'),(14,'天文学、地球科学'),(15,'生物科学'),(16,'医药、卫生'),(17,'农业科学'),(18,'工业技术'),(19,'交通运输'),(20,'航空、航天'),(21,'环境科学'),(22,'综合');

/*Table structure for table `lendList` */

DROP TABLE IF EXISTS `lendList`;

CREATE TABLE `lendList` (
  `L_id` int(11) NOT NULL AUTO_INCREMENT,
  `L_bookId` int(11) NOT NULL,
  `L_userId` int(11) NOT NULL,
  `L_lendDate` datetime DEFAULT NULL,
  `L_backDate` datetime DEFAULT NULL,
  PRIMARY KEY (`L_id`),
  KEY `bookidkey` (`L_bookId`),
  KEY `useridkey` (`L_userId`),
  CONSTRAINT `lendList_ibfk_1` FOREIGN KEY (`L_bookId`) REFERENCES `bookInfo` (`bookid`),
  CONSTRAINT `lendList_ibfk_2` FOREIGN KEY (`L_userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

/*Data for the table `lendList` */

insert  into `lendList`(`L_id`,`L_bookId`,`L_userId`,`L_lendDate`,`L_backDate`) values (6,3,2,'2021-05-07 20:48:53','2021-05-11 18:53:12'),(8,10,2,'2021-05-07 20:49:30','2021-05-07 20:49:48'),(12,3,2,'2021-05-11 18:52:13','2021-05-11 18:53:12'),(13,4,2,'2021-05-11 18:52:17','2021-05-11 18:53:13'),(14,6,2,'2021-05-11 18:53:15','2021-05-11 18:55:00'),(15,9,2,'2021-05-11 18:53:16',NULL),(16,13,2,'2021-05-11 18:53:18','2021-05-11 18:55:03'),(21,3,1,'2021-05-11 19:24:12','2021-05-12 19:19:28'),(26,3,5,'2021-05-11 00:00:00','2021-05-12 19:13:55'),(32,6,5,'2021-05-11 19:44:06','2021-05-12 19:10:52'),(33,4,5,'2021-05-11 19:50:40','2021-05-12 19:09:25'),(34,6,5,'2021-05-11 19:50:42','2021-05-12 19:10:52'),(35,4,1,'2021-05-12 19:09:04','2021-05-12 19:09:29'),(36,4,5,'2021-05-12 19:09:10','2021-05-12 19:09:25'),(37,3,5,'2021-05-12 19:11:21','2021-05-12 19:13:55'),(38,3,5,'2021-05-12 19:11:39','2021-05-12 19:13:55'),(39,3,1,'2021-05-12 19:11:44','2021-05-12 19:19:28'),(40,3,1,'2021-05-12 19:19:18','2021-05-12 19:19:28');

/*Table structure for table `storeLog` */

DROP TABLE IF EXISTS `storeLog`;

CREATE TABLE `storeLog` (
  `putId` int(11) NOT NULL AUTO_INCREMENT,
  `putDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `putAdminId` int(11) NOT NULL,
  `putBookId` int(11) NOT NULL,
  PRIMARY KEY (`putId`),
  KEY `fk_admin` (`putAdminId`),
  KEY `fk_book` (`putBookId`),
  CONSTRAINT `fk_admin` FOREIGN KEY (`putAdminId`) REFERENCES `admin` (`adminId`),
  CONSTRAINT `fk_book` FOREIGN KEY (`putBookId`) REFERENCES `bookInfo` (`bookid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `storeLog` */

insert  into `storeLog`(`putId`,`putDate`,`putAdminId`,`putBookId`) values (3,'2021-05-07 19:55:59',1,3),(4,'2021-05-07 20:00:07',1,4),(6,'2021-05-07 20:01:00',1,6),(7,'2021-05-07 20:02:44',1,7),(8,'2021-05-07 20:03:28',1,8),(9,'2021-05-07 20:04:23',1,9),(10,'2021-05-07 20:05:09',1,10),(11,'2021-05-07 20:05:48',1,11),(12,'2021-05-07 20:08:00',1,12),(13,'2021-05-07 20:08:38',1,13),(14,'2021-05-07 20:09:21',1,14),(15,'2021-05-07 20:09:53',1,15),(16,'2021-05-07 20:10:27',1,16),(17,'2021-05-08 20:11:35',1,17),(18,'2021-05-11 20:33:08',1,18),(19,'2021-05-12 15:32:52',2,19);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userFullName` varchar(11) DEFAULT NULL,
  `userEmail` varchar(20) DEFAULT NULL,
  `userRegistertime` datetime DEFAULT NULL,
  `accountId` int(11) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`userId`,`userFullName`,`userEmail`,`userRegistertime`,`accountId`) values (1,'番薯头二号','1677698157@qq.com','2021-05-05 00:00:00',2),(2,'番薯头1','1677698157@qq.com','2021-05-07 11:11:28',3),(3,'番薯头1','1677698157@qq.com','2021-05-07 11:16:29',4),(4,'番薯头二号','1254785@qq.com','2021-05-08 19:39:22',5),(5,'测试号码','1677698157@qq.com','2021-05-11 19:18:00',6);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
