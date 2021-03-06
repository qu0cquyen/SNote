package com.snote.Models;

public class Notes {
    private String noteid;
    private String content;
    private String contentImage;
    private String description;
    private String title;
    private int view;

    public Notes(){}

    public Notes(String note_id, String c, String content_image, String des, String t, int v){
        this.noteid = note_id;
        this.content = c;
        this.contentImage = content_image;
        this.description = des;
        this.title = t;
        this.view = v;
    }

    public String getNoteid() {
        return noteid;
    }

    public void setNoteid(String noteid) {
        this.noteid = noteid;
    }

    public String getContentImage() {
        return contentImage;
    }

    public void setContentImage(String contentImage) {
        this.contentImage = contentImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }


}
