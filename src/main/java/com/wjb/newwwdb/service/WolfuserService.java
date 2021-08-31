package com.wjb.newwwdb.service;

import com.wjb.newwwdb.pojo.Evaluation;
import com.wjb.newwwdb.pojo.Wolfuser;

public interface WolfuserService {

    Wolfuser userRegister(Wolfuser WolfUser);

    Wolfuser uerLogin(String userName, String password);

    Wolfuser adminCheckLogin(String userName, String password);

    Evaluation evaluate(Integer wolfuserId, Integer persondataId, Integer score, String content);

    Evaluation enjoy(Integer evaluationId);

    Wolfuser selectByUsername(String username);
}
