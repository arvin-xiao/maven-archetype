SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) NOT NULL COMMENT '用户姓名',
  `account` varchar(50) NOT NULL COMMENT '用户账号',
  `password` varchar(50) NOT NULL COMMENT '用户密码',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除状态(0:未删除，1:已删除)',
  `version` int(11) DEFAULT '1' COMMENT '版本号',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `created_name` varchar(40) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `updated_name` varchar(40) DEFAULT NULL COMMENT '更新人名称',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

SET FOREIGN_KEY_CHECKS = 1;
