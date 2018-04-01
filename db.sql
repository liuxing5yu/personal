----------------------------------------------2018��4��1��------------------------------------------------------------------------
-- ���ְ�ƴ������
select *
  from collect_tag t
 where 1 = 1
 order by upper(NLSSORT(t.name,'NLS_SORT=SCHINESE_PINYIN_M'));


-- ���������ղ�
select
    t.id,t.collect_title,t.collect_url,t.status,t.last_modify_time,t.create_time,t.is_series,
    ct.id tag_id,
    ct.last_modify_time
    tag_last_modify_time,
    ct.name tag_name,
    ct.isvalid tag_isvalid
    from
    collect t left join collect_collect_tag cct
    on (t.id =
    cct.collect_id)
    left join collect_tag ct on
    (cct.collect_tag_id =
    ct.id)
    where 1=1
    and
    (
    t.collect_title like '%С%' or t.collect_url like '%С%' or ct.name like '%С%'
    )
    order
    by
    t.create_time desc,t.status,
    upper(nlssort(ct.name,'NLS_SORT=SCHINESE_PINYIN_M'))
    ;

----------------------------------------------2018��1��26��------------------------------------------------------------------------

-- ��ȡ�Զ���ı���Ϣ
select * from user_tables t;
select * from codelist t;
select * from keys;
select * from todo;
select * from user_menu;
select * from collect;
select * from collect_tag;
select * from collect_collect_tag;
select * from recent_do;

----------------------------------------------2018��1��19��------------------------------------------------------------------------
select * from user_menu;

----------------------------------------------2018��1��18��------------------------------------------------------------------------
select * from collect_tag;
select * from todo;

----------------------------------------------2018��1��17��------------------------------------------------------------------------
select * from collect;

select * from collect_collect_tag t where t.collect_id = '141d32a5-4804-4e4c-b3ad-1d4ded3e5f11';

----------------------------------------------2018��1��14��------------------------------------------------------------------------
-- �����ѯ�ղغ��ղصı�ǩ
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

