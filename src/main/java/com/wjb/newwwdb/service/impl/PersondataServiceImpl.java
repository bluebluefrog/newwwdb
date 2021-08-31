package com.wjb.newwwdb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjb.newwwdb.dao.EvaluationMapper;
import com.wjb.newwwdb.dao.PersondataMapper;
import com.wjb.newwwdb.emuns.ResponseEnum;
import com.wjb.newwwdb.exception.BusinessException;
import com.wjb.newwwdb.pojo.Persondata;
import com.wjb.newwwdb.pojo.Wolfuser;
import com.wjb.newwwdb.service.PersondataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class PersondataServiceImpl implements PersondataService {

    @Resource
    public PersondataMapper persondataMapper;

    @Resource
    public EvaluationMapper evaluationMapper;

    public IPage<Persondata> paging(String order, Integer page, Integer rows) {
        Page<Persondata> p = new Page<Persondata>(page, rows);
        QueryWrapper<Persondata> queryWrapper = new QueryWrapper<Persondata>();
        if (order != null) {
            if(order.equals("quantity")){
                queryWrapper.orderByDesc("evaluation_quantity");
            }else if(order.equals("score")){
                queryWrapper.orderByDesc("evaluation_score");
            }
        }
        Page<Persondata> PersondataPage = persondataMapper.selectPage(p, queryWrapper);
        return PersondataPage;
    }

    public Persondata selectById(Integer persondataId) {
        QueryWrapper<Persondata>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("persondata_id", persondataId);
        Persondata persondata = persondataMapper.selectOne(queryWrapper);
        return persondata;
    }

    public Persondata selectByUsername(String username) {
        QueryWrapper<Persondata> queryWrapper = new QueryWrapper<Persondata>();
        queryWrapper.eq("username", username);
        Persondata persondata = persondataMapper.selectOne(queryWrapper);
        return persondata;
    }

    @Transactional
    public Persondata createPersondata(Persondata persondata) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("person_name", persondata.getPersonName());
        Persondata oldPersonData = persondataMapper.selectOne(queryWrapper);
        if (oldPersonData != null) {
            throw new BusinessException(ResponseEnum.PERSON_DATA_EXIST.getCode().toString(), ResponseEnum.PERSON_DATA_EXIST.getMsg());
        }
        persondataMapper.insert(persondata);
        return persondata;
    }

    @Transactional
    public Persondata updatePersondata(Persondata persondata) {
        persondataMapper.updateByPrimaryKeySelective(persondata);
        return persondata;
    }

    @Transactional
    public void deletePersondata(Long persondataId) {
        persondataMapper.deleteByPrimaryKey(persondataId);
        evaluationMapper.deleteByPrimaryKey(persondataId);
    }

    public Boolean checkUserByName(Wolfuser wolfuser, String namecheck) {
        if (namecheck == null) {
            throw new BusinessException(ResponseEnum.NEED_LOGIN.getCode().toString(), ResponseEnum.NEED_LOGIN.getMsg());
        }
        if (wolfuser.getUsername() != namecheck && wolfuser.getRole() != 2) {
            throw new BusinessException(ResponseEnum.NEED_RIGHT_NAME.getCode().toString(), ResponseEnum.NEED_RIGHT_NAME.getMsg());
        }
        return true;

    }
}
