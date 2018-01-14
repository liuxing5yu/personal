----------------------------------------------2018年1月14日------------------------------------------------------------------------
-- 联表查询收藏和收藏的标签
select
    t.id,t.collect_title,t.collect_url,t.status,t.last_modify_time
		from collect t 
		where 1=1
		order by t.status,t.last_modify_time desc;


-- 查询收藏和收藏标签关联表
select t.* from collect_collect_tag t;

-- Oracle 不支持这种插入多行的写法
/*
insert into collect_collect_tag (collect_id,collect_tag_id) values         
('88065CAE318C4FDDA911DA577F3A5FF9', 'BFC769164FC6422696EC0EBD9BD49D39'),  
('88065CAE318C4FDDA911DA577F3A5FF9', '576786DBCECA4802BED67B91749BC734')
*/

-- 查询收藏标签关联表
select * from collect_tag t
where 1=1
and t.isvalid = 1
order by upper(t.name);



--truncate table collect_tag;

-- 查询所有表
select * from user_tables t;

-- 待做
select * from todo t 
order by t.status,t.last_modify_time desc;

-- 收藏
select
    t.id,t.collect_title,t.collect_url,t.status,t.last_modify_time
		from collect t
		where 1=1
		order by t.status,t.last_modify_time desc;
    
--insert into collect t(t.id,t.collect_title,t.collect_url,t.status,t.last_modify_time)
--               values(sys_guid(),'测试标题','http://baidu.com','0',sysdate);

--truncate table collect;

