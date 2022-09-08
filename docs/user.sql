drop table if exists `user` ;
create table `user`
(
    `id` bigint(20) not null comment '主键',
    `name` varchar(20) not null comment '用户名',
    `password` varchar(20) not null comment  '密码',
    `salt` varchar(20) not null comment  '盐',
    `phone` varchar(20) not null comment '手机号',
    `email` varchar(20) not null comment '邮箱',
    `state` int(11) not null default 1 comment '状态，0：禁用，1：启用',
    `deleted` int(11) not null default 0 comment '逻辑删除，0：未删除，1：已删除',
    `create_time`   timestamp   NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp   NULL     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='user';