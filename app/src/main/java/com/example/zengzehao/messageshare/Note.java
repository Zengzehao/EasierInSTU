package com.example.zengzehao.messageshare;

/**
 * Created by zengzehao on 16-11-18.
 */

public class Note {
    private String username;
    private String createTime;
    private String description;
    private String clicks;
    private int mIcon;

    public Note(String username, String createTime, String description, String clicks, int mIcon) {
        this.username = username;
        this.createTime = createTime;
        this.description = description;
        this.clicks = clicks;
        this.mIcon = mIcon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClicks() {
        return clicks;
    }

    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    public int getmIcon() {
        return mIcon;
    }

    public void setmIcon(int mIcon) {
        this.mIcon = mIcon;
    }
}
