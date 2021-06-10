package com.starwin.hawkeye;

import com.cx.wz.crawler.CrawlerResultEntity;
import com.cx.wz.merchant.entity.PackageEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RunWith(SpringRunner.class)
public class PackageEntityTest {

    @Test
    public void errorResult() {
        PackageEntity packageEntity1 = new PackageEntity().setStatus(PackageEntity.PackageStatus.CREATED);

        CrawlerResultEntity crawlerResult1 = new CrawlerResultEntity()
                .setStatus(CrawlerResultEntity.CrawlerStatus.RESPONSE_ERROR);
        PackageEntity newPackageEntity1 = packageEntity1.handleCrawlerResult(crawlerResult1);
        Assert.assertEquals(PackageEntity.PackageStatus.CREATED, newPackageEntity1.getStatus());


        PackageEntity packageEntity2 = new PackageEntity().setStatus(PackageEntity.PackageStatus.ONLINE);
        CrawlerResultEntity crawlerResult2 = new CrawlerResultEntity()
                .setStatus(CrawlerResultEntity.CrawlerStatus.PARSE_ERROR);
        PackageEntity newPackageEntity2 = packageEntity2.handleCrawlerResult(crawlerResult2);
        Assert.assertEquals(PackageEntity.PackageStatus.ONLINE, newPackageEntity2.getStatus());
    }

    @Test
    public void createdPackage() throws ParseException {
        PackageEntity packageEntity = new PackageEntity().setStatus(PackageEntity.PackageStatus.CREATED);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


        Date checkTime = dateFormat.parse("2019-05-02 17:00");

        CrawlerResultEntity offlineResult = new CrawlerResultEntity()
                .setStatus(CrawlerResultEntity.CrawlerStatus.PARSE_SUCCEED)
                .setOnline(false)
                .setCreateTime(checkTime);

        PackageEntity newPackageEntity1 = packageEntity.handleCrawlerResult(offlineResult);
        Assert.assertEquals(PackageEntity.PackageStatus.CREATED, newPackageEntity1.getStatus());


        Date updated = dateFormat.parse("2019-05-01 00:00");
        CrawlerResultEntity onlineResult = new CrawlerResultEntity()
                .setStatus(CrawlerResultEntity.CrawlerStatus.PARSE_SUCCEED)
                .setOnline(true)
                .setUpdated(updated)
                .setCreateTime(checkTime);

        PackageEntity newPackageEntity2 = packageEntity.handleCrawlerResult(onlineResult);
        Assert.assertEquals(PackageEntity.PackageStatus.ONLINE, newPackageEntity2.getStatus());
        Assert.assertEquals(updated, newPackageEntity2.getFirstOnlineTime());
        Assert.assertEquals(Long.valueOf(1L), newPackageEntity2.getOnlineDays());
        Assert.assertEquals(checkTime, newPackageEntity2.getOnlineBaseTime());
    }

    @Test
    public void onlinePackage() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date date = dateFormat.parse("2019-05-01 14:00");

        PackageEntity packageEntity = new PackageEntity()
                .setStatus(PackageEntity.PackageStatus.ONLINE)
                .setFirstOnlineTime(date)
                .setLastOnlineTime(date)
                .setOnlineDays(0L)
                .setOnlineBaseTime(date);

        Date date1 = dateFormat.parse("2019-05-01 19:00");
        CrawlerResultEntity crawlerResult1 = new CrawlerResultEntity()
                .setStatus(CrawlerResultEntity.CrawlerStatus.PARSE_SUCCEED)
                .setOnline(true)
                .setCreateTime(date1);
        PackageEntity newPackage1 = packageEntity.handleCrawlerResult(crawlerResult1);

        Assert.assertEquals(PackageEntity.PackageStatus.ONLINE, newPackage1.getStatus());
        Assert.assertEquals(date1, newPackage1.getLastCheckTime());
        Assert.assertEquals(Long.valueOf(0L), newPackage1.getOnlineDays());
        Assert.assertEquals(packageEntity.getLastOnlineTime(), newPackage1.getLastOnlineTime());


        Date date2 = dateFormat.parse("2019-05-03 19:00");
        CrawlerResultEntity crawlerResult2 = new CrawlerResultEntity()
                .setStatus(CrawlerResultEntity.CrawlerStatus.PARSE_SUCCEED)
                .setOnline(true)
                .setCreateTime(date2);
        PackageEntity newPackage2 = packageEntity.handleCrawlerResult(crawlerResult2);
        Assert.assertEquals(PackageEntity.PackageStatus.ONLINE, newPackage2.getStatus());
        Assert.assertEquals(Long.valueOf(2L), newPackage2.getOnlineDays());
        Assert.assertEquals(Date.from(packageEntity.getOnlineBaseTime().toInstant().plus(2, ChronoUnit.DAYS))
                , newPackage2.getOnlineBaseTime());


        Date date3 = dateFormat.parse("2019-05-02 19:00");
        CrawlerResultEntity crawlerResult3 = new CrawlerResultEntity()
                .setStatus(CrawlerResultEntity.CrawlerStatus.PARSE_SUCCEED)
                .setOnline(false)
                .setCreateTime(date3);
        PackageEntity newPackage3 = packageEntity.handleCrawlerResult(crawlerResult3);
        Assert.assertEquals(PackageEntity.PackageStatus.OFFLINE, newPackage3.getStatus());
        Assert.assertEquals(Long.valueOf(1L), newPackage3.getOnlineDays());
        Assert.assertNull(newPackage3.getOnlineBaseTime());
        Assert.assertEquals(date3, newPackage3.getLastOfflineTime());
        Assert.assertEquals(date3, newPackage3.getOfflineBaseTime());
        Assert.assertEquals(Long.valueOf(0L), newPackage3.getOfflineDays());
    }

    @Test
    public void offlinePackage() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date date = dateFormat.parse("2019-05-01 14:00");

        PackageEntity packageEntity = new PackageEntity()
                .setStatus(PackageEntity.PackageStatus.OFFLINE)
                .setOfflineDays(3L)
                .setOfflineBaseTime(date);

        Date date1 = dateFormat.parse("2019-05-01 19:00");
        CrawlerResultEntity crawlerResult1 = new CrawlerResultEntity()
                .setStatus(CrawlerResultEntity.CrawlerStatus.PARSE_SUCCEED)
                .setOnline(false)
                .setCreateTime(date1);
        PackageEntity newPackage1 = packageEntity.handleCrawlerResult(crawlerResult1);

        Assert.assertEquals(PackageEntity.PackageStatus.OFFLINE, newPackage1.getStatus());
        Assert.assertEquals(Long.valueOf(3L), newPackage1.getOfflineDays());
        Assert.assertEquals(packageEntity.getOfflineBaseTime(), newPackage1.getOfflineBaseTime());

        Date date2 = dateFormat.parse("2019-05-03 19:00");
        CrawlerResultEntity crawlerResult2 = new CrawlerResultEntity()
                .setStatus(CrawlerResultEntity.CrawlerStatus.PARSE_SUCCEED)
                .setOnline(false)
                .setCreateTime(date2);
        PackageEntity newPackage2 = packageEntity.handleCrawlerResult(crawlerResult2);

        Assert.assertEquals(PackageEntity.PackageStatus.OFFLINE, newPackage2.getStatus());
        Assert.assertEquals(Long.valueOf(5L), newPackage2.getOfflineDays());
        Assert.assertEquals(Date.from(packageEntity.getOfflineBaseTime().toInstant().plus(2, ChronoUnit.DAYS))
                , newPackage2.getOfflineBaseTime());

        Date date3 = dateFormat.parse("2019-05-02 19:00");
        CrawlerResultEntity crawlerResult3 = new CrawlerResultEntity()
                .setStatus(CrawlerResultEntity.CrawlerStatus.PARSE_SUCCEED)
                .setOnline(true)
                .setCreateTime(date3);
        PackageEntity newPackage3 = packageEntity.handleCrawlerResult(crawlerResult3);

        Assert.assertEquals(PackageEntity.PackageStatus.ONLINE, newPackage3.getStatus());
        Assert.assertEquals(Long.valueOf(4L), newPackage3.getOfflineDays());
        Assert.assertNull(newPackage3.getOfflineBaseTime());
        Assert.assertEquals(date3, newPackage3.getLastOnlineTime());
        Assert.assertEquals(date3, newPackage3.getOnlineBaseTime());
    }
}
