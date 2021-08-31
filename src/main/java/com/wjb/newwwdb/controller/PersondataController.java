package com.wjb.newwwdb.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjb.newwwdb.exception.BusinessException;
import com.wjb.newwwdb.pojo.Evaluation;
import com.wjb.newwwdb.pojo.Persondata;
import com.wjb.newwwdb.pojo.Wolfuser;
import com.wjb.newwwdb.service.EvaluationService;
import com.wjb.newwwdb.service.PersondataService;
import com.wjb.newwwdb.service.WolfuserService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PersondataController {

    @Resource
    private PersondataService persondataService;

    @Resource
    private EvaluationService evaluationService;

    @Resource
    private WolfuserService wolfuserService;

//    @Resource
//    private EvaluationService evaluationService;


    @GetMapping("/persondata.html")
    public ModelAndView showPersondata() {
        return new ModelAndView("/persondata");
    }

    @GetMapping("/search.html")
    public ModelAndView showSearch() {
        return new ModelAndView("/search");
    }


    @GetMapping("/")
    public ModelAndView showIndex() {
        ModelAndView mav = new ModelAndView("/index");
        return mav;
    }

    @GetMapping("/persondatas")
    @ResponseBody
    public IPage<Persondata> selectPersondata(String order, Integer p) {
        if (p == null) {
            p = 1;
        }
        IPage<Persondata> pageObject = persondataService.paging(order, p, 10);
        return pageObject;
    }

    @GetMapping("/persondata/{id}")
    public ModelAndView showDetail(@PathVariable("id") Integer id, HttpSession session) {
        Persondata persondata = persondataService.selectById(id);
        List<Evaluation> evaluations = evaluationService.selectByPersondataId(id);
        Wolfuser wolfuser = (Wolfuser) session.getAttribute("loginMember");
        ModelAndView mav = new ModelAndView("/detail");

        mav.addObject("persondata", persondata);
        mav.addObject("evaluationList", evaluations);
        return mav;
    }

    @GetMapping("/id/{id}")
    @ResponseBody
    public Map selectById(@PathVariable("id") Integer persondataId) {
        Persondata persondata =persondataService.selectById(persondataId);
        Map result = new HashMap();
        result.put("code", "0");
        result.put("msg", "success");
        result.put("data", persondata);
        return result;
    }

    @PostMapping("/persondata/username/")
    @ResponseBody
    public ModelAndView selectByUsername(String username,HttpSession session){
        Persondata persondata = persondataService.selectByUsername(username);
        ModelAndView modelAndView = showDetail(persondata.getPersondataId(), session);
        return modelAndView;
    }

    @PostMapping("/upload")
    @ResponseBody
    public Map upload(@RequestParam("img") MultipartFile file, HttpServletRequest request) throws IOException {
        String uploadFilePath = request.getServletContext().getResource("/").getPath() + "/upload/";
        String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        file.transferTo(new File(uploadFilePath + fileName + suffix));
        Map result = new HashMap();
        result.put("errno", 0);
        result.put("data", new String[]{"/upload/" + fileName + suffix});
        return result;
    }

    @PostMapping("/create")
    @ResponseBody
    public Map createPersondata(Persondata persondata, HttpSession session) {
        Map result = new HashMap();
        try {
            checkUserByName(persondata.getPersonName(),session);
            Wolfuser wolfuser=(Wolfuser)session.getAttribute("loginMember");
            persondata.setUsername(wolfuser.getUsername());
            persondata.setEvaluationQuantity(0);
            persondata.setEvaluationScore(0f);
            Document doc = Jsoup.parse(persondata.getDescription());
            Element img = doc.select("img").first();
            String cover = img.attr("src");
            persondata.setCover(cover);
            persondataService.createPersondata(persondata);
            result.put("code", "0");
            result.put("msg", "success");
        } catch (BusinessException e) {
            e.printStackTrace();
            result.put("code", e.getCode());
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @PostMapping("/update")
    @ResponseBody
    public Map update(Persondata persondata,HttpSession session) {
        Map result = new HashMap();
        Wolfuser wolfuser = (Wolfuser) session.getAttribute("loginMember");
        try {
           checkUserByName(persondata.getPersonName(),session);
           Persondata updatePersondata = persondataService.selectById(persondata.getPersondataId());
           updatePersondata.setPersonName(persondata.getPersonName());
           updatePersondata.setSubTitle(persondata.getSubTitle());
           updatePersondata.setAuthor(persondata.getAuthor());
           updatePersondata.setDescription(persondata.getDescription());
           Document doc = Jsoup.parse(persondata.getDescription());
           String cover = doc.select("img").first().attr("src");
           persondata.setCover(cover);
           persondata.setUsername(wolfuser.getUsername());
            persondata.setEvaluationQuantity(0);
            persondata.setEvaluationScore(0f);
            persondataService.updatePersondata(persondata);
            result.put("code", "0");
            result.put("msg", "success");
        } catch (BusinessException e) {
            e.printStackTrace();
            result.put("code", e.getCode());
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public Map deletePersondata(@PathVariable("id") Integer persondataId,HttpSession session) {
        Map result = new HashMap();
        try {
            Persondata persondata = persondataService.selectById(persondataId);
            Wolfuser wolfuser = wolfuserService.selectByUsername(persondata.getUsername());
            checkUserByName(persondata.getPersonName(), session);
            persondataService.deletePersondata(persondataId.longValue());
            result.put("code", "0");
            result.put("msg", "success");
        } catch (BusinessException e) {
            e.printStackTrace();
            result.put("code", e.getCode());
            result.put("msg", e.getMessage());
        }
        return result;
    }



    private Boolean checkUserByName(String username, HttpSession session) {
        Wolfuser wolfuser = (Wolfuser) session.getAttribute("loginMember");
        if (wolfuser == null) {
            persondataService.checkUserByName(wolfuser,null);
        }
        return persondataService.checkUserByName(wolfuser,username);
    }
}
