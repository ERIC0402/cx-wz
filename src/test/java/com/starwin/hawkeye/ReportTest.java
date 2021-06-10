package com.starwin.hawkeye;

import com.google.common.collect.Lists;
import com.cx.wz.config.RestConfig;
import com.cx.wz.app.job.ReportDailyJob;
import com.cx.wz.app.job.ReportMonitorPackageJob;
import com.cx.wz.merchant.service.MerchantService;
import com.cx.wz.report.EmailService;
import com.cx.wz.report.ReportBean;
import com.cx.wz.report.ReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestConfig.class})
public class ReportTest {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportMonitorPackageJob reportMonitorPackageJob;

    @Autowired
    private ReportDailyJob reportDailyJob;

    @Autowired
    private MerchantService merchantService;

    @Test
    public void testSend() {
        emailService.send(Lists.newArrayList("zheng.wang@starwin.com"),
                "subject", new Date().toString());
    }

    @Test
    public void testDailyReport() {
        reportDailyJob.reportDaily();
    }

    @Test
    public void testMonitorReport() {
        reportMonitorPackageJob.reportMonitorPackage();
    }

    @Test
    public void testMerchantStatics() {
        merchantService.calculateOfflineStatics("2010");
    }

    @Test
    public void testChangeLogReport() {
        ReportBean reportBean = reportService.changeLogReport(new Date());
        emailService.send(Lists.newArrayList("jianlei.jiang@starwin.com"),
                reportBean.getSubject(), reportBean.getContent());
    }
}
