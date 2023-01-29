package com.lamp.app.domain;

public class TodayMusicDto {
    Integer t_no;
    String t_youtubelink;
    Integer m_no;
    String m_title;
    Integer m_rank;
    String m_music;
    String m_musician;
    String m_album;
    String m_albumimg;
    String m_year;
    int m_category;

    public int getT_no() {
        return t_no;
    }

    public void setT_no(int t_no) {
        this.t_no = t_no;
    }

    public String getT_youtubelink() {
        return t_youtubelink;
    }

    public void setT_youtubelink(String t_youtubelink) {
        this.t_youtubelink = t_youtubelink;
    }

    public int getM_no() {
        return m_no;
    }

    public void setM_no(int m_no) {
        this.m_no = m_no;
    }

    public String getM_title() {
        return m_title;
    }

    public void setM_title(String m_title) {
        this.m_title = m_title;
    }

    public int getM_rank() {
        return m_rank;
    }

    public void setM_rank(int m_rank) {
        this.m_rank = m_rank;
    }

    public String getM_music() {
        return m_music;
    }

    public void setM_music(String m_music) {
        this.m_music = m_music;
    }

    public String getM_musician() {
        return m_musician;
    }

    public void setM_musician(String m_musician) {
        this.m_musician = m_musician;
    }

    public String getM_album() {
        return m_album;
    }

    public void setM_album(String m_album) {
        this.m_album = m_album;
    }

    public String getM_albumimg() {
        return m_albumimg;
    }

    public void setM_albumimg(String m_albumimg) {
        this.m_albumimg = m_albumimg;
    }

    public String getM_year() {
        return m_year;
    }

    public void setM_year(String m_year) {
        this.m_year = m_year;
    }

    public int getM_category() {
        return m_category;
    }

    public void setM_category(int m_category) {
        this.m_category = m_category;
    }

    @Override
    public String toString() {
        return "TodayMusicDto{" +
                "t_no=" + t_no +
                ", t_youtubelink='" + t_youtubelink + '\'' +
                ", m_no=" + m_no +
                ", m_title='" + m_title + '\'' +
                ", m_rank=" + m_rank +
                ", m_music='" + m_music + '\'' +
                ", m_musician='" + m_musician + '\'' +
                ", m_album='" + m_album + '\'' +
                ", m_albumimg='" + m_albumimg + '\'' +
                ", m_year='" + m_year + '\'' +
                ", m_category=" + m_category +
                '}';
    }
}
