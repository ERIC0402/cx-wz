package com.starwin.hawkeye;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.wz.config.RestConfig;
import com.cx.wz.crawler.CrawlerResultEntity;
import com.cx.wz.crawler.CrawlerService;
import com.cx.wz.merchant.entity.PackageEntity;
import com.cx.wz.merchant.repo.PackageMapper;
import com.cx.wz.merchant.service.PackageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestConfig.class})
public class PackageServiceTest {
    @Autowired
    private PackageMapper packageMapper;
    @Autowired
    private PackageService packageService;

    @Autowired
    private CrawlerService crawlerService;

    @Test
    public void testMonitor() {
        String packageName = "com.manteiv.longlich";

        PackageEntity packageEntity = packageMapper.selectOne(new QueryWrapper<PackageEntity>().lambda().eq(PackageEntity::getPackageName, packageName));
        CrawlerResultEntity result2 = crawlerService.crawlerPackage(packageName);
        packageService.handleResultAndEvent(packageEntity, result2);
    }
}
