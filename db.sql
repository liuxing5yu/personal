----------------------------------------------2018��1��14��------------------------------------------------------------------------
-- �����ѯ�ղغ��ղصı�ǩ
select
    t.id,t.collect_title,t.collect_url,t.status,t.last_modify_time
		from collect t 
		where 1=1
		order by t.status,t.last_modify_time desc;


-- ��ѯ�ղغ��ղر�ǩ������
select t.* from collect_collect_tag t;

-- Oracle ��֧�����ֲ�����е�д��
/*
insert into collect_collect_tag (collect_id,collect_tag_id) values         
('88065CAE318C4FDDA911DA577F3A5FF9', 'BFC769164FC6422696EC0EBD9BD49D39'),  
('88065CAE318C4FDDA911DA577F3A5FF9', '576786DBCECA4802BED67B91749BC734')
*/

-- ��ѯ�ղر�ǩ������
select * from collect_tag t
where 1=1
and t.isvalid = 1
order by upper(t.name);



--truncate table collect_tag;

-- ��ѯ���б�
select * from user_tables t;

-- ����
select * from todo t 
order by t.status,t.last_modify_time desc;

-- �ղ�
select
    t.id,t.collect_title,t.collect_url,t.status,t.last_modify_time
		from collect t
		where 1=1
		order by t.status,t.last_modify_time desc;
    
--insert into collect t(t.id,t.collect_title,t.collect_url,t.status,t.last_modify_time)
--               values(sys_guid(),'���Ա���','http://baidu.com','0',sysdate);

--truncate table collect;

