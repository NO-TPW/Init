package com.tpw.p2pdemo.service;

import com.tpw.p2pdemo.mapper.BannerMapper;
import com.tpw.p2pdemo.pojo.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerServiceImp implements BannerService{

    @Autowired
    BannerMapper bannerMapper;

    public int addBanner(Banner banner) {
        return bannerMapper.addBanner(banner);
    }
}
