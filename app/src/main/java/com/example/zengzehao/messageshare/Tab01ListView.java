package com.example.zengzehao.messageshare;

/**
 * Created by zengzehao on 16-12-7.
 */

public class Tab01ListView {
    private String username;
    private String time;
    private String title;
    private String objectId;
    private String type;
    private String contact;
    private String url;
    private int clicks_number;

    public Tab01ListView(){

    }

    public Tab01ListView(String username, String time, String title, String objectId, String type, String contact, String url, int clicks_number) {
        this.username = username;
        this.time = time;
        this.title = title;
        this.objectId = objectId;
        this.type = type;
        this.contact = contact;
        this.url = url;
        this.clicks_number = clicks_number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getClicks_number() {
        return clicks_number;
    }

    public void setClicks_number(int clicks_number) {
        this.clicks_number = clicks_number;
    }
}
