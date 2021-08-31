package com.wjb.newwwdb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjb.newwwdb.pojo.Evaluation;

public interface EvaluationMapper extends BaseMapper<Evaluation> {

    int deleteByPrimaryKey(Long persondataId);

    Evaluation selectByEvaluationId(Long evaluationId);

    void updateEvaluation(Evaluation evaluation);
}
