package com.lamp.app.domain;

public class CommentDto {
    Integer cc_no;
    String cc_writer;
    Integer c_no;
    String cc_content;
    String cc_created;
    String cc_modified;
    Integer cc_comment;

    public Integer getCc_no() {
        return cc_no;
    }

    public void setCc_no(Integer cc_no) {
        this.cc_no = cc_no;
    }

    public String getCc_writer() {
        return cc_writer;
    }

    public void setCc_writer(String cc_writer) {
        this.cc_writer = cc_writer;
    }

    public Integer getC_no() {
        return c_no;
    }

    public void setC_no(Integer c_no) {
        this.c_no = c_no;
    }

    public String getCc_content() {
        return cc_content;
    }

    public void setCc_content(String cc_content) {
        this.cc_content = cc_content;
    }

    public String getCc_created() {
        return cc_created;
    }

    public void setCc_created(String cc_created) {
        this.cc_created = cc_created;
    }

    public String getCc_modified() {
        return cc_modified;
    }

    public void setCc_modified(String cc_modified) {
        this.cc_modified = cc_modified;
    }

    public Integer getCc_comment() {
        return cc_comment;
    }

    public void setCc_comment(Integer cc_comment) {
        this.cc_comment = cc_comment;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "cc_no=" + cc_no +
                ", cc_writer='" + cc_writer + '\'' +
                ", c_no=" + c_no +
                ", cc_content='" + cc_content + '\'' +
                ", cc_created='" + cc_created + '\'' +
                ", cc_modified='" + cc_modified + '\'' +
                ", cc_comment=" + cc_comment +
                '}';
    }
}
