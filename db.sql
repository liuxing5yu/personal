----------------------------------------------2018年1月17日------------------------------------------------------------------------
select * from collect;

select * from collect_collect_tag t where t.collect_id = '141d32a5-4804-4e4c-b3ad-1d4ded3e5f11';

----------------------------------------------2018年1月14日------------------------------------------------------------------------
-- 联表查询收藏和收藏的标签
select
    t.id,t.collect_title,t.collect_url,t.status,t.last_modify_time,
    cct.collect_tag_id,
    ct.id,
    ct.last_modify_time,
    ct.name,
    ct.isvalid
		from collect t left join collect_collect_tag cct on (t.id = cct.collect_id) left join collect_tag ct on (cct.collect_tag_id = ct.id)
		where 1=1
		order by t.status,t.last_modify_time desc;
    
delete from collect t where t.id = '3952aa4f-4639-4656-a7c6-d65475942eb7';


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

