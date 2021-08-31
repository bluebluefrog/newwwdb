package com.wjb.newwwdb.pojo;

import com.baomidou.mybatisplus.annotation.TableField;

import java.util.Date;

public class Evaluation {
    private Integer evaluationId;
    private String content;
    private Integer score;
    private Integer userId;
    private Integer persondataId;
    private Integer enjoy;
    private String state;
    private String disableReason;
    private Date disableTime;
    private Date createTime;
    @TableField(exist = false)
    private Wolfuser wolfuser;
    @TableField(exist = false)
    private Persondata persondata;

    public Integer getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPersondataId() {
        return persondataId;
    }

    public void setPersondataId(Integer persondataId) {
        this.persondataId = persondataId;
    }

    public Integer getEnjoy() {
        return enjoy;
    }

    public void setEnjoy(Integer enjoy) {
        this.enjoy = enjoy;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDisableReason() {
        return disableReason;
    }

    public void setDisableReason(String diaableReason) {
        this.disableReason = diaableReason;
    }

    public Date getDisableTime() {
        return disableTime;
    }

    public void setDisableTime(Date disableTime) {
        this.disableTime = disableTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Wolfuser getWolfuser() {
        return wolfuser;
    }

    public void setWolfuser(Wolfuser wolfuser) {
        this.wolfuser = wolfuser;
    }

    public Persondata getPersondata() {
        return persondata;
    }

    public void setPersondata(Persondata persondata) {
        this.persondata = persondata;
    }

}