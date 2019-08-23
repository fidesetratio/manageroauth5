DROP TABLE IF EXISTS `oauth_access_token`;
DROP TABLE IF EXISTS `oauth_approvals`;
DROP TABLE IF EXISTS `oauth_client_details`;
DROP TABLE IF EXISTS `oauth_client_token`;
DROP TABLE IF EXISTS `oauth_code`;
DROP TABLE IF EXISTS `oauth_permissions`;
DROP TABLE IF EXISTS `oauth_users`;
DROP TABLE IF EXISTS `oauth_refresh_token`;

CREATE TABLE `oauth_access_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` mediumblob,
  `authentication_id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `authentication` mediumblob,
  `refresh_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `oauth_approvals` (
  `userId` varchar(255) DEFAULT NULL,
  `clientId` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `oauth_client_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` mediumblob,
  `authentication_id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `oauth_code` (
  `code` varchar(255) DEFAULT NULL,
  `authentication` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `oauth_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` varchar(100) NOT NULL,
  `user_role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` mediumblob,
  `authentication` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `oauth_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `oauth_client_details`(`client_id`,`resource_ids`,`client_secret`,`scope`,`authorized_grant_types`,`web_server_redirect_uri`,`authorities`,`access_token_validity`,`refresh_token_validity`,`additional_information`,`autoapprove`) values ('clientapp','belajar','123456','read,write','password,refresh_token,authorization_code','','',100,100,null,'1');
insert into `oauth_client_details`(`client_id`,`resource_ids`,`client_secret`,`scope`,`authorized_grant_types`,`web_server_redirect_uri`,`authorities`,`access_token_validity`,`refresh_token_validity`,`additional_information`,`autoapprove`) values ('clientauthcode','belajar','123456','read,write','authorization_code, refresh_token',null,'CLIENT',100,100,null,'1');
insert into `oauth_permissions`(`id`,`id_user`,`user_role`) values (2,'1','ROLE_USER');
insert into `oauth_users`(`id`,`username`,`password`,`active`) values (1,'patartimotius','evievi123',1);

