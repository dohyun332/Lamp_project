package com.lamp.app.test;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TestController {

    public static final Path path1 =
            Paths.get(System.getProperty("user.dir"));

    @RequestMapping("/image")
    public String imgupload1() {

        return "test";
    }

//    @RequestMapping("/image2")
//    public String imgupload2(MultipartHttpServletRequest image) {
//        System.out.println(image);
//
//        return "test";
//    }

    @RequestMapping("/image2")
    public String imgupload2(MultipartFile image22) {
        System.out.println(image22.getOriginalFilename());


        String path2 = System.getProperty("user.dir");
        File file = new File("");
        String path3 = file.getAbsolutePath();

        System.out.println("path1 = " + path1);
        System.out.println("path2 = " + path2);
        System.out.println("path3 = " + path3);
        return "test";
    }
}
