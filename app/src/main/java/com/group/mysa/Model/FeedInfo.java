package com.group.mysa.Model;

import java.io.Serializable;
import java.sql.Timestamp;

public class FeedInfo implements Serializable{
    private String address;
    private String zip;
    private int counter;
    private String timestamp;
    private String date;
    private String description;
    private String imgUrl;
    private String score;
    private String title;
    private String link;
    private String postId;
    private String surveyUrl;
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSurveyUrl() {
        return surveyUrl;
    }

    public void setSurveyUrl(String surveyUrl) {
        this.surveyUrl = surveyUrl;
    }

    public String getPostid() {
        return postId;

    }

    public void setPostid(String postid) {
        this.postId = postid;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public FeedInfo() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
