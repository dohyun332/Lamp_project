package com.lamp.app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.*;

@Service
public class ProfileImgService {

    public void profileImgService(MultipartHttpServletRequest multiRequest) {

        // 파라미터 이름을 키로 파라미터에 해당하는 파일정보를 값으로하는 Map 가져옴
        Map<String, MultipartFile> files = multiRequest.getFileMap();

        // files.entrySet()요소 읽기
        Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();

        MultipartFile mFile;

        // 업로드될 경우 지정
        String filePath = "F:\\220530 자바본강의\\221215 개인포트폴리오\\작업\\2301151611_project\\src\\main\\webapp\\resources\\img\\user_profile";

        // 파일명이 중복될 경우 사용할 스트링 객체
        String saveFileName = "", saveFilePath = "";

        // 읽어 올 요소가 있으면 true 없으면 false
        while(itr.hasNext()) {
            Entry<String, MultipartFile> entry = itr.next();
            mFile = entry.getValue();
            System.out.println("mFile = " + mFile);
        }
    }
}
