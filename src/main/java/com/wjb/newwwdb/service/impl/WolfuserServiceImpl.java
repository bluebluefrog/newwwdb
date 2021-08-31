package com.wjb.newwwdb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wjb.newwwdb.dao.EvaluationMapper;
import com.wjb.newwwdb.dao.WolfuserMapper;
import com.wjb.newwwdb.emuns.ResponseEnum;
import com.wjb.newwwdb.exception.BusinessException;
import com.wjb.newwwdb.pojo.Evaluation;
import com.wjb.newwwdb.pojo.Wolfuser;
import com.wjb.newwwdb.service.WolfuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class WolfuserServiceImpl implements WolfuserService{

    @Autowired
    private WolfuserMapper wolfUserMapper;

    @Resource
    private EvaluationMapper evaluationMapper;

    @Transactional
    public Wolfuser userRegister(Wolfuser user) {
        QueryWrapper<Wolfuser> queryWrapper = new QueryWrapper<Wolfuser>();
        queryWrapper.eq("username",user.getUsername());
        Wolfuser oldWolfUser = wolfUserMapper.selectOne(queryWrapper);
        if(oldWolfUser!= null){
            throw new BusinessException(ResponseEnum.USERNAME_EXIST.getCode().toString(), ResponseEnum.USERNAME_EXIST.getMsg());
        }

        String afterMd5 = DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8));
        user.setPassword(afterMd5);
        user.setRole(1);
        user.setPersonalizedSignature("Hello 大家好我是"+user.getUsername());
        user.setCreateTime(new Date());

        //写入数据库
        int count = wolfUserMapper.insert(user);
        if (count == 0) {
            throw new BusinessException(ResponseEnum.ERROR.getCode().toString(), ResponseEnum.ERROR.getMsg());
        }
        user.setPassword("");
        return user;
    }

    public Wolfuser uerLogin(String userName, String password) {
        QueryWrapper<Wolfuser> queryWrapper = new QueryWrapper<Wolfuser>();
        queryWrapper.eq("username",userName);
        Wolfuser user = wolfUserMapper.selectOne(queryWrapper);
        if (user == null) {
            //用户不存在
            throw new BusinessException(ResponseEnum.USERNAME_OR_PASSWORD_ERROR.getCode().toString(), ResponseEnum.USERNAME_OR_PASSWORD_ERROR.getMsg());
        }
        if(!user.getPassword().equalsIgnoreCase(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets
                .UTF_8)))){
            //密码错误
            throw new BusinessException(ResponseEnum.PASSWORD_ERROR.getCode().toString(), ResponseEnum.PASSWORD_ERROR.getMsg());
        }
        user.setPassword("");
        return user;
    }


    public Wolfuser adminCheckLogin(String userName, String password) {
        QueryWrapper<Wolfuser> queryWrapper = new QueryWrapper<Wolfuser>();
        queryWrapper.eq("username",userName);
        Wolfuser user = wolfUserMapper.selectOne(queryWrapper);
        if (user == null) {
            //用户不存在
            throw new BusinessException(ResponseEnum.USERNAME_OR_PASSWORD_ERROR.getCode().toString(), ResponseEnum.USERNAME_OR_PASSWORD_ERROR.getMsg());
        }
        if(!user.getPassword().equalsIgnoreCase(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets
                .UTF_8)))){
            //密码错误
            throw new BusinessException(ResponseEnum.PASSWORD_ERROR.getCode().toString(), ResponseEnum.PASSWORD_ERROR.getMsg());
        }
        if (user.getRole() != 2) {
            throw new BusinessException(ResponseEnum.NEED_ADMIN.getCode().toString(), ResponseEnum.NEED_ADMIN.getMsg());
        }
        user.setPassword("");
        return user;
    }

    public Evaluation evaluate(Integer wolfuserId, Integer persondataId, Integer score, String content) {
        Evaluation evaluation = new Evaluation();
        evaluation.setPersondataId(persondataId);
        evaluation.setUserId(wolfuserId);
        evaluation.setScore(score);
        evaluation.setContent(content);
        evaluation.setCreateTime(new Date());
        evaluation.setState("enable");
        evaluation.setEnjoy(0);
        evaluationMapper.insert(evaluation);
        return evaluation;
    }

    public Evaluation enjoy(Integer evaluationId) {
        Evaluation evaluation = evaluationMapper.selectByEvaluationId(evaluationId.longValue());
        evaluation.setEnjoy(evaluation.getEnjoy() + 1);
        evaluationMapper.updateEvaluation(evaluation);
        return evaluation;
    }

    public Wolfuser selectByUsername(String username) {
        QueryWrapper<Wolfuser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return wolfUserMapper.selectOne(queryWrapper);
    }
}
