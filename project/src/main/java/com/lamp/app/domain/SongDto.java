package com.lamp.app.domain;

// 크롤링 용 songDto
public class SongDto {
    int rank;
    String title;
    String music;
    String artist;
    String album;
    int year;
    String imgSrc;
    // 0:국내, 1:해외
    int category;

    public SongDto() {}

    public SongDto(int rank, String title, String artist, String album, int year) {
        this.rank = rank;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
    }

    public SongDto(int rank, String title, String music, String artist, String album, int year, String imgSrc, int category) {
        this.rank = rank;
        this.title = title;
        this.music = music;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.imgSrc = imgSrc;
        this.category = category;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "SongDto{" +
                "rank=" + rank +
                ", title='" + title + '\'' +
                ", music='" + music + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", year=" + year +
                ", imgSrc='" + imgSrc + '\'' +
                ", category=" + category +
                '}';
    }
}
