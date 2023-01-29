package com.lamp.app.domain;

public class CommunityDto {
    Integer c_no;
    String c_writer;
    String c_title;
    String c_content;
    String c_created;
    String c_modified;
    Integer c_hits;
    String c_music;

    public Integer getC_no() {
        return c_no;
    }

    public void setC_no(Integer c_no) {
        this.c_no = c_no;
    }

    public String getC_writer() {
        return c_writer;
    }

    public void setC_writer(String c_writer) {
        this.c_writer = c_writer;
    }

    public String getC_title() {
        return c_title;
    }

    public void setC_title(String c_title) {
        this.c_title = c_title;
    }

    public String getC_content() {
        return c_content;
    }

    public void setC_content(String c_content) {
        this.c_content = c_content;
    }

    public String getC_created() {
        return c_created;
    }

    public void setC_created(String c_created) {
        this.c_created = c_created;
    }

    public String getC_modified() {
        return c_modified;
    }

    public void setC_modified(String c_modified) {
        this.c_modified = c_modified;
    }

    public Integer getC_hits() {
        return c_hits;
    }

    public void setC_hits(Integer c_hits) {
        this.c_hits = c_hits;
    }

    public String getC_music() {
        return c_music;
    }

    public void setC_music(String c_music) {
        this.c_music = c_music;
    }

    @Override
    public String toString() {
        return "CommunityDto{" +
                "c_no=" + c_no +
                ", c_writer='" + c_writer + '\'' +
                ", c_title='" + c_title + '\'' +
                ", c_content='" + c_content + '\'' +
                ", c_created='" + c_created + '\'' +
                ", c_modified='" + c_modified + '\'' +
                ", c_hits=" + c_hits +
                ", c_music='" + c_music + '\'' +
                '}';
    }
}
