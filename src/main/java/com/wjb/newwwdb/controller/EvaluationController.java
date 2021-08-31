package com.wjb.newwwdb.controller;

import com.wjb.newwwdb.exception.BusinessException;
import com.wjb.newwwdb.pojo.Evaluation;
import com.wjb.newwwdb.service.EvaluationService;
import com.wjb.newwwdb.service.WolfuserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class EvaluationController{

    @Resource
    public EvaluationService evaluationService;

    @Resource
    public WolfuserService wolfuserService;



}
