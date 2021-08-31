package com.wjb.newwwdb.pojo;

public class Persondata {
    private Integer persondataId;

    private String username;

    private String personName;

    private String subTitle;

    private String author;

    private String cover;

    private String description;

    private Float evaluationScore;

    private Integer evaluationQuantity;

    public Integer getPersondataId() {
        return persondataId;
    }

    public void setPersondataId(Integer persondataId) {
        this.persondataId = persondataId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Float getEvaluationScore() {
        return evaluationScore;
    }

    public void setEvaluationScore(Float evaluationScore) {
        this.evaluationScore = evaluationScore;
    }

    public Integer getEvaluationQuantity() {
        return evaluationQuantity;
    }

    public void setEvaluationQuantity(Integer evaluationQuantity) {
        this.evaluationQuantity = evaluationQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}