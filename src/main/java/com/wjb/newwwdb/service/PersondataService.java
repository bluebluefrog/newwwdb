package com.wjb.newwwdb.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjb.newwwdb.pojo.Persondata;
import com.wjb.newwwdb.pojo.Wolfuser;

import javax.servlet.http.HttpSession;

public interface PersondataService {
    IPage<Persondata> paging(String order, Integer page, Integer rows);

    Persondata selectById(Integer persondataId);

    Persondata selectByUsername(String username);

    Persondata createPersondata(Persondata persondata);

    Persondata updatePersondata(Persondata persondata);

    void deletePersondata(Long persondataId);

    Boolean checkUserByName(Wolfuser wolfuser, String namecheck);
}
