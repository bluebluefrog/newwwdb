package com.wjb.newwwdb.service;

import com.wjb.newwwdb.pojo.Evaluation;

import java.util.List;

public interface EvaluationService {

    public List<Evaluation> selectByPersondataId(Integer shopId);

    public List<Evaluation> selectAll();
}
