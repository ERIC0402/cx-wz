package com.cx.wz.banner;

import com.cx.wz.banner.dto.BannerDto;
import com.cx.wz.uitls.BaseInfoUtil;
import com.cx.wz.uitls.CustomizedHttpHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BaseInfoUtil baseInfoUtil;

    @GetMapping
    public List<BannerDto> banner(@RequestHeader(value = CustomizedHttpHeader.APP_PACKAGE_NAME, required = false) String packageName) {
        String bannerUrl = baseInfoUtil.getBannerUrl(packageName);
        return Collections.singletonList(new BannerDto(bannerUrl, null));
    }

}
