package com.tpw.p2pdemo.controller;


import com.tpw.p2pdemo.pojo.Banner;
import com.tpw.p2pdemo.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/banner")
@Api(description = "图片操作")
public class BannerController {

    @Autowired
    BannerService bannerService;

    @ApiOperation(value = "添加图片")
    @PostMapping("/add")
    public Object addBanner(@RequestParam("file") MultipartFile file, Banner banner){
        if (file.isEmpty()){
            return "文件为空";
        }
        String lastName = file.getOriginalFilename();
        String suffix = lastName.substring(lastName.lastIndexOf(".")).toLowerCase();
        String fileName = UUID.randomUUID()+suffix;
        String path = File.separator + "uploadImg" + File.separator + fileName;
        File destFile = new File(System.getProperty("user.dir") + path);
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(destFile);
            banner.setBannerimg(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        bannerService.addBanner(banner);

        return banner;
    }

}
