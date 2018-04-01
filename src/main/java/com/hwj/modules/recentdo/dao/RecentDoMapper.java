package com.hwj.modules.recentdo.dao;

import java.util.List;

import com.hwj.modules.base.util.MyBatisRepository;
import com.hwj.modules.recentdo.model.RecentDo;

@MyBatisRepository
public interface RecentDoMapper {
    int deleteByPrimaryKey(String id);

    int insert(RecentDo record);

    int insertSelective(RecentDo record);

    RecentDo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RecentDo record);

    int updateByPrimaryKey(RecentDo record);

	List<RecentDo> selectAll();
}