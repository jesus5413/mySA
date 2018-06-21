package com.group.mysa.Model;

public class FeedInfo {
    private String date;
    private String description;
    private String imgUrl;
    private String score;
    private String title;

    public FeedInfo() {
//        this.date = date;
//        this.description = description;
//        this.imgUrl = imgUrl;
//        this.score = score;
//        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
