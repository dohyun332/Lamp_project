package com.lamp.app.dao;

import com.lamp.app.domain.SongDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class genieCrawlingDao {

    public static void main(String[] args) throws Exception {
        List<SongDto> cl = new ArrayList<SongDto>();
        for(int i = 1990; i <= 2022; i++) {
            for(int j = 0; j<=1; j++) {
                cl = crawlingTxt(i, j);
                FileWriter fw = new FileWriter("E:/musicInsert.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                for(int k = 0 ; k < cl.size(); k++) {
                    SongDto sd = cl.get(k);
                    bw.write("insert into music(m_rank, m_title, m_music, m_musician, m_album, m_year, m_albumimg, m_category) values("+ sd.getRank()+ ", " +'"'+ sd.getTitle() +'"'+ ", " +'"'+ sd.getMusic()  +'"'+ ", " +'"'+ sd.getArtist() +'"'+ ", " +'"'+ sd.getAlbum() +'"'+ ", " + sd.getYear() + ", " +'"'+ sd.getImgSrc() + ".jpg"+ '"' + ", " + sd.getCategory() + ");");
                    bw.newLine();
                }
                bw.flush();
                bw.close();
            }
        }
    }

    static List<SongDto> crawlingTxt(int year, int cate) throws IOException {
        List<SongDto> sl = new ArrayList<SongDto>();
        String url = "https://www.genie.co.kr/chart/musicHistory?year=" + year + "&category="+cate+"&pg=1";
        Document doc = null;
        Elements tmp;
        String title_tmp = null;
        String music_tmp = null;
        String artist_tmp = null;
        String album_tmp = null;
        int rank = 0;
        String imgSrc = null;

        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 1~50위
        Elements element = doc.select("tr.list");
        for(int i=0; i<element.size(); i++) {
            Element el = element.get(i);

            // 노래순위
            tmp = el.select("td.number");
            rank = Integer.parseInt(tmp.text());

            //타이틀
            tmp = el.select("span.icon-title");
            title_tmp = tmp.text();
            if(title_tmp.equals("")) title_tmp = "SIDE";

            // 노래제목
            tmp = el.select("td.info").select("a.title");
            music_tmp = tmp.text().replace("TITLE ", "");

            // 가수
            tmp = el.select("td.info").select("a.artist");
            artist_tmp = tmp.text();

            // 앨범
            tmp = el.select("td.info").select("a.albumtitle");
            album_tmp = tmp.text();

            // 이미지 소스
            tmp = el.select("a.cover").select("img");
            imgSrc = tmp.attr("src");

            URL urlImg = new URL("https:" + imgSrc);
            InputStream is = urlImg.openStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            File folder = new File("E:/album/" + year + "/");
            if(!folder.exists()) {
                folder.mkdirs();
            }


            FileOutputStream fos;

            // indexno와 사진순서 맞추기 위해 i에 +1적용
            if(i < 9) {
                fos = new FileOutputStream("E:/album/" + year + "/" + year + "_" + cate + "_00" + (i+1) + ".jpg");
                //arrayList  삽입
                sl.add(new SongDto(rank, title_tmp, music_tmp, artist_tmp, album_tmp, year, year + "/" + year + "_" + cate +"_00"+(i+1), cate));

            } else {
                fos = new FileOutputStream("E:/album/" + year + "/" + year +  "_" + cate + "_0" +(i+1) + ".jpg");

                //arrayList  삽입
                sl.add(new SongDto(rank, title_tmp, music_tmp, artist_tmp, album_tmp, year, year + "/" + year + "_" + cate + "_0"+(i+1), cate));
            }
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            int b;
            while((b=bis.read()) != -1) {
                bos.write(b);
            }
            bos.flush();
            bos.close();
        }

        url = "https://www.genie.co.kr/chart/musicHistory?year=" + year + "&category="+cate+"&pg=2";
        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 50위~100위
        element = doc.select("tr.list");
        for(int i=0; i<element.size(); i++) {
            Element el = element.get(i);

            // 노래순위
            tmp = el.select("td.number");
            rank = Integer.parseInt(tmp.text());

            //타이틀
            tmp = el.select("span.icon-title");
            title_tmp = tmp.text();
            if(title_tmp.equals("")) title_tmp = "SIDE";

            // 노래제목
            tmp = el.select("td.info").select("a.title");
            music_tmp = tmp.text().replace("TITLE ", "");

            // 가수
            tmp = el.select("td.info").select("a.artist");
            artist_tmp = tmp.text();

            // 앨범
            tmp = el.select("td.info").select("a.albumtitle");
            album_tmp = tmp.text();

            // 이미지 소스
            tmp = el.select("a.cover").select("img");
            imgSrc = tmp.attr("src");


            URL urlImg = new URL("https:" + imgSrc);
            InputStream is = urlImg.openStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            FileOutputStream fos;
            // indexno와 사진순서 맞추기 위해 i에 +1적용
            if(i < 49) {

                fos = new FileOutputStream("E:/album/" + year + "/" + year +  "_" + cate + "_0" + ((i+1)+50) + ".jpg");

                //arrayList  삽입
                sl.add(new SongDto(rank, title_tmp, music_tmp, artist_tmp, album_tmp, year, year + "/" + year + "_" + cate + "_0"+((i+1)+50), cate));
            } else {
                fos = new FileOutputStream("E:/album/" + year + "/" + year +  "_" + cate + "_"+ + ((i+1)+50) + ".jpg");

                //arrayList  삽입
                sl.add(new SongDto(rank, title_tmp, music_tmp, artist_tmp, album_tmp, year, year + "/" + year + "_" + cate + "_"+((i+1)+50), cate));
            }
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            int b;
            while((b=bis.read()) != -1) {
                bos.write(b);
            }
            bos.flush();
            bos.close();
        }
        return sl;
    }
}


