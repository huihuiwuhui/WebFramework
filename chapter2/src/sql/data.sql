create table customer(
 id BIGINT(20) not null AUTO_INCREMENT COMMENT '客户ID',
name VARCHAR(255) not null COMMENT '客户名称',
contact VARCHAR(255) COMMENT '联系人',
telephone VARCHAR(11) COMMENT '电话号码',
email VARCHAR(50) COMMENT '邮箱地址',
remark TEXT COMMENT '备注',
PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户表';

INSERT into customer VALUES('1','customer1','Jack','13512345678','jack@gmail.com','杰克');
INSERT into customer VALUES('2','customer2','Rose','13612345678','rose@gmail.com','肉丝');
