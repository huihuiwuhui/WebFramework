create table customer(
 id BIGINT(20) not null AUTO_INCREMENT COMMENT '�ͻ�ID',
name VARCHAR(255) not null COMMENT '�ͻ�����',
contact VARCHAR(255) COMMENT '��ϵ��',
telephone VARCHAR(11) COMMENT '�绰����',
email VARCHAR(50) COMMENT '�����ַ',
remark TEXT COMMENT '��ע',
PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�ͻ���';

INSERT into customer VALUES('1','customer1','Jack','13512345678','jack@gmail.com','�ܿ�');
INSERT into customer VALUES('2','customer2','Rose','13612345678','rose@gmail.com','��˿');
