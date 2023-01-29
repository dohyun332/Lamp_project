package com.lamp.app.domain;

import java.util.List;

public class EditorChoiceDto {

    int e_no;
    String e_theme;
    String e_editor;
    Integer e_totalCnt;
    List m_albumimg;

    public int getE_no() {
        return e_no;
    }

    public void setE_no(int e_no) {
        this.e_no = e_no;
    }

    public String getE_theme() {
        return e_theme;
    }

    public void setE_theme(String e_theme) {
        this.e_theme = e_theme;
    }

    public String getE_editor() {
        return e_editor;
    }

    public void setE_editor(String e_editor) {
        this.e_editor = e_editor;
    }

    public Integer getE_totalCnt() {
        return e_totalCnt;
    }

    public void setE_totalCnt(Integer e_totalCnt) {
        this.e_totalCnt = e_totalCnt;
    }

    public List getM_albumimg() {
        return m_albumimg;
    }

    public void setM_albumimg(List m_albumimg) {
        this.m_albumimg = m_albumimg;
    }

    @Override
    public String toString() {
        return "EditorChoiceDto{" +
                "e_no=" + e_no +
                ", e_theme='" + e_theme + '\'' +
                ", e_editor='" + e_editor + '\'' +
                ", e_totalCnt=" + e_totalCnt +
                ", m_albumimg=" + m_albumimg +
                '}';
    }
}
