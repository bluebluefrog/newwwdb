package com.wjb.newwwdb.controller.management;

import com.wjb.newwwdb.exception.BusinessException;
import com.wjb.newwwdb.pojo.Wolfuser;
import com.wjb.newwwdb.service.EvaluationService;
import com.wjb.newwwdb.service.WolfuserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/management/admin")
public class MUserController {

    @Resource
    WolfuserService wolfuserService;

    @Resource
    EvaluationService evaluationService;

    @GetMapping("/adminlogin.html")
    public ModelAndView login() {
        return new ModelAndView("/management/userlogin");
    }

    @GetMapping("/evaluation.html")
    public ModelAndView evaluation() {
        return new ModelAndView("/management/common");
    }

    @PostMapping("/check_login")
    @ResponseBody
    public Map checkLogin(String username, String password, HttpSession session){
        Map result = new HashMap();
        try {
            Wolfuser admin = wolfuserService.adminCheckLogin(username,password);
            session.setAttribute("loginAdmin", admin);
            result.put("code", "0");
            result.put("msg", "success");
            result.put("redirect_url","/management/index.html");
        } catch (BusinessException e) {
            result.put("code", e.getCode());
            result.put("msg", e.getMsg());
        }
        return result;
    }

    @GetMapping("/logout")
    @ResponseBody
    public ModelAndView logout(HttpSession session){
        session.invalidate();
        return new ModelAndView("/management/userlogin");
    }

//    @GetMapping("/management/evaluation/list")
//    @ResponseBody
//    public Map list(){
//
//    }
}
