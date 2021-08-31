package com.wjb.newwwdb.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjb.newwwdb.pojo.Persondata;

public interface PersondataMapper extends BaseMapper<Persondata> {

    int deleteByPrimaryKey(Long persondataId);

    int updateByPrimaryKeySelective(Persondata record);
}
