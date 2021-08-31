package com.wjb.newwwdb.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjb.newwwdb.exception.BusinessException;
import com.wjb.newwwdb.pojo.Evaluation;
import com.wjb.newwwdb.pojo.Persondata;
import com.wjb.newwwdb.pojo.Wolfuser;
import com.wjb.newwwdb.service.PersondataService;
import com.wjb.newwwdb.service.WolfuserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WolfuserController {

    @Resource
    private WolfuserService wolfUserService;

    @Resource
    private PersondataService persondataService;

    @GetMapping("/register.html")
    public ModelAndView showRegister() {
        return new ModelAndView("/register");
    }

    @GetMapping("/login.html")
    public ModelAndView showLogin() {
        return new ModelAndView("/login");
    }

    @PostMapping("/registe")
    @ResponseBody
    public Map register(String username, String password, String wechatId, HttpServletRequest request){
//        //获取正确验证码
//        String verifyCode = (String)request.getSession().getAttribute("kaptchaVerifyCode");
        //验证码进行比对
        Map result = new HashMap();
//        if (vc == null || verifyCode == null || !vc.equals(verifyCode)&&1!=1) {
//            result.put("code", "VC01");
//            result.put("msg", "验证码错误");
//        }else{
            try{
                Wolfuser user = new Wolfuser();
                user.setUsername(username);
                user.setPassword(password);
                user.setWechat(wechatId);

                wolfUserService.userRegister(user);
                result.put("code", "0");
                result.put("msg", "success");
            } catch(BusinessException e){
                result.put("code", e.getCode());
                result.put("msg", e.getMsg());
            }
//        }
        return result;
    }

    @PostMapping("/check_login")
    @ResponseBody
    public Map memberLogin(String username, String password, String nickname, HttpSession session){

//        //获取正确验证码
//        String verifyCode = (String)session.getAttribute("kaptchaVerifyCode");
//        //验证码进行比对
        Map result = new HashMap();
//        if (vc == null || verifyCode == null || !vc.equals(verifyCode)) {
//            result.put("code", "VC01");
//            result.put("msg", "验证码错误");
//        }else{
            try {
                Wolfuser user = wolfUserService.uerLogin(username, password);
                session.setAttribute("loginMember", user);
                result.put("code", "0");
                result.put("msg", "success");
            } catch (BusinessException e) {
                result.put("code", e.getCode());
                result.put("msg", e.getMsg());
            }
//        }
        return result;
    }

    @PostMapping("/evaluate")
    @ResponseBody
    public Map evaluate(Integer userId, Integer persondataId, Integer score, String content, HttpSession session) {
        Map result = new HashMap();
        try {
            if (score == null) {
                score = 1;
            }
            wolfUserService.evaluate(userId, persondataId,score,content);
            result.put("code", "0");
            result.put("msg", "success");
        } catch (BusinessException e) {
            result.put("code", e.getCode());
            result.put("msg", e.getMsg());
        }
        return result;
    }

    @PostMapping("/enjoy")
    @ResponseBody
    public Map enjoy(Integer evaluationId) {
        Map result = new HashMap();
        try {
            Evaluation enjoy = wolfUserService.enjoy(evaluationId);
            result.put("code", "0");
            result.put("msg", "success");
            result.put("evaluation", enjoy);
        } catch (BusinessException e) {
            result.put("code", e.getCode());
            result.put("msg", e.getMsg());
        }
        return result;
    }

    @GetMapping("/user/logout")
    @ResponseBody
    public ModelAndView logout(HttpSession session){
        session.invalidate();
        return new ModelAndView("/login");
    }

    @GetMapping("/list")
    @ResponseBody
    public Map shopList(Integer page, Integer limit) {
        if (page == null) {
            page = 1;
        }
        if (limit == 0) {
            limit = 10;
        }
        IPage<Persondata> pageObject = persondataService.paging(null, page, limit);
        Map result = new HashMap();
        result.put("code", "0");
        result.put("msg", "success");
        result.put("data", pageObject.getRecords());//当前页面记录数
        result.put("count", pageObject.getTotal());//未分页总记录数
        return result;
    }
}
