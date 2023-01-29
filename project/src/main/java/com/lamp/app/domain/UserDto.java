package com.lamp.app.domain;

public class UserDto {
    Integer u_idx;
    String u_img;
    String u_id;
    String u_pw;
    String u_name;
    String u_cell;
    String u_email;
    Integer u_gender;
    String u_birthday;
    String u_joindate;
    String u_lastlogin;

    public Integer getU_idx() {
        return u_idx;
    }

    public void setU_idx(Integer u_idx) {
        this.u_idx = u_idx;
    }

    public String getU_img() {
        return u_img;
    }

    public void setU_img(String u_img) {
        this.u_img = u_img;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_pw() {
        return u_pw;
    }

    public void setU_pw(String u_pw) {
        this.u_pw = u_pw;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_cell() {
        return u_cell;
    }

    public void setU_cell(String u_cell) {
        this.u_cell = u_cell;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public Integer getU_gender() {
        return u_gender;
    }

    public void setU_gender(Integer u_gender) {
        this.u_gender = u_gender;
    }

    public String getU_birthday() {
        return u_birthday;
    }

    public void setU_birthday(String u_birthday) {
        this.u_birthday = u_birthday;
    }

    public String getU_joindate() {
        return u_joindate;
    }

    public void setU_joindate(String u_joindate) {
        this.u_joindate = u_joindate;
    }

    public String getU_lastlogin() {
        return u_lastlogin;
    }

    public void setU_lastlogin(String u_lastlogin) {
        this.u_lastlogin = u_lastlogin;
    }


    @Override
    public String toString() {
        return "UserDto{" +
                "u_idx=" + u_idx +
                ", u_img='" + u_img + '\'' +
                ", u_id='" + u_id + '\'' +
                ", u_pw='" + u_pw + '\'' +
                ", u_name='" + u_name + '\'' +
                ", u_cell='" + u_cell + '\'' +
                ", u_email='" + u_email + '\'' +
                ", u_gender=" + u_gender +
                ", u_birthday='" + u_birthday + '\'' +
                ", u_joindate='" + u_joindate + '\'' +
                ", u_lastlogin='" + u_lastlogin + '\'' + "}";
    }
}
