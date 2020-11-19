package com.tpw.p2pdemo.mapper;

import com.tpw.p2pdemo.pojo.Banner;
import org.springframework.stereotype.Component;

@Component
public interface BannerMapper {

    int addBanner(Banner banner);

}
