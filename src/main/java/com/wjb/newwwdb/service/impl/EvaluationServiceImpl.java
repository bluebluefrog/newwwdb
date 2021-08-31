package com.wjb.newwwdb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wjb.newwwdb.dao.EvaluationMapper;
import com.wjb.newwwdb.dao.PersondataMapper;
import com.wjb.newwwdb.dao.WolfuserMapper;
import com.wjb.newwwdb.pojo.Evaluation;
import com.wjb.newwwdb.pojo.Persondata;
import com.wjb.newwwdb.pojo.Wolfuser;
import com.wjb.newwwdb.service.EvaluationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Member;
import java.util.List;
@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Resource
    private EvaluationMapper evaluationMapper;

    @Resource
    private PersondataMapper persondataMapper;

    @Resource
    private WolfuserMapper wolfuserMapper;

    public List<Evaluation> selectByPersondataId(Integer persondataId) {
        QueryWrapper<Persondata>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("persondata_id", persondataId);
        Persondata persondata = persondataMapper.selectOne(queryWrapper);
        QueryWrapper<Evaluation> evaluationQueryWrapper = new QueryWrapper<Evaluation>();
        evaluationQueryWrapper.eq("persondata_id", persondataId);
        evaluationQueryWrapper.eq("state", "enable");
        evaluationQueryWrapper.orderByDesc("create_time");
        List<Evaluation> evaluations = evaluationMapper.selectList(evaluationQueryWrapper);
        for (Evaluation e:evaluations) {
            QueryWrapper<Wolfuser>queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("user_id", e.getUserId());
            Wolfuser wolfuser = wolfuserMapper.selectOne(queryWrapper2);
            e.setPersondata(persondata);
            e.setWolfuser(wolfuser);
        }
        return evaluations;
    }

    public List<Evaluation> selectAll() {
        List<Evaluation> evaluationList = evaluationMapper.selectList(new QueryWrapper<Evaluation>());
        return evaluationList;
    }
}
