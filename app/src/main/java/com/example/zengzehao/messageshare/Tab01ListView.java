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
    private int clicks_number;

    public Tab01ListView(String username, String time, String title, String objectId, String type, String contact, int clicks_number) {
        this.username = username;
        this.time = time;
        this.title = title;
        this.objectId = objectId;
        this.type = type;
        this.contact = contact;
        this.clicks_number = clicks_number;
    }

    public String getUsername() {
        return username;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getType() {
        return type;
    }

    public String getContact() {
        return contact;
    }

    public int getClicks_number() {
        return clicks_number;
    }
}
