package com.cx.wz.config;

import com.cx.wz.device.entity.ChannelDeviceEntity;
import com.cx.wz.device.entity.DeviceEntity;
import com.cx.wz.device.repo.DeviceMapper;
import com.cx.wz.uitls.BaseInfoUtil;
import com.cx.wz.uitls.CustomizedHttpHeader;
import com.cx.wz.uitls.IPFetcher;
import com.cx.wz.uitls.date.DateUtil;
import com.cx.wz.uitls.device.GA;
import com.cx.wz.uitls.security.RestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

@Component
public class DeviceInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(DeviceInterceptor.class);

    @Value("${channel.re-attribute.windows}")
    private int reAttributeWindows;

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private BaseInfoUtil baseInfoUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String appVersion = request.getHeader(CustomizedHttpHeader.APP_VERSION);
        String appPackageName = request.getHeader(CustomizedHttpHeader.APP_PACKAGE_NAME);
        String appName = request.getHeader(CustomizedHttpHeader.APP_NAME);
        String gaId = request.getHeader(CustomizedHttpHeader.GA_ID);
        String referrer = request.getHeader(CustomizedHttpHeader.REFERRER);
        String token = request.getHeader(CustomizedHttpHeader.AUTH_TOKEN);
        String androidId = request.getHeader(CustomizedHttpHeader.ANDROID_ID);
        String macAddress = request.getHeader(CustomizedHttpHeader.MAC_ADDRESS);

        try {
            baseInfoUtil.updateAppNamePackageNameConf(appPackageName, appName);

            //1. 获取链接信息
            String ip = IPFetcher.getIpAddr(request);

            //2. 查找android id是否存在;如果存在，则设置device为现有的;如果不存在，则创建新的device
            if(StringUtils.isEmpty(androidId)) {
                return true;
            }

            DeviceEntity deviceEntity = deviceMapper.findDeviceByAndroidId(androidId);
            if (deviceEntity == null) {
                deviceEntity = new DeviceEntity();
                deviceEntity.setAndroidId(androidId);
                deviceEntity.setIp(ip);
                deviceEntity.setCreateTime(new Date());
                deviceEntity.setGId(gaId);
                deviceEntity.setMacAddress(macAddress);
                deviceMapper.insertDevice(deviceEntity);
            }else {
                if(!StringUtils.isEmpty(gaId) && !gaId.equals(deviceEntity.getGId())) {
                    logger.info("update device {} gaId {} ",deviceEntity.getId(),gaId);
                    deviceMapper.updateDeviceGaId(gaId,deviceEntity.getId());
                }
            }
            //3. 绑定设备渠道关系;查找设备是否绑定过渠道，如果未绑定，则进行绑定
            List<ChannelDeviceEntity> channelList = deviceMapper.findChannelByDevice(deviceEntity.getId());
            logger.info("【设备与渠道绑定】获取到REFERRER:{}，appVersion：{}，appPackageName：{}, deviceId:{}, ip:{}, token {} ", referrer, appVersion, appPackageName, androidId,ip,token);

            ChannelDeviceEntity channel = null;
            if(CollectionUtils.isEmpty(channelList)) {
                logger.info("设备第一次绑定渠道 {} ",deviceEntity.getId());
            }else {
                channel = channelList.get(0);
            }
            if (StringUtils.isEmpty(referrer)) {
                logger.warn("未找到设备[{}]的渠道, 版本[{}]", androidId, appVersion);
            }else {
                if (channel == null) {
                    channel = generateChannel(referrer,deviceEntity.getId());
                    channel.setAppPackageName(appPackageName);
                    deviceMapper.attributionChannel(channel);
                    deviceMapper.attributionChannelLog(channel);
                }else {
                    channel = generateChannel(referrer,deviceEntity.getId());
                    channel.setAppPackageName(appPackageName);
                    // source为null的数据 同一条 refer插入一个
                    List<ChannelDeviceEntity> dbList = deviceMapper.findChannelByDeviceLogByReffer(deviceEntity.getId(),channel.getReferrer());
                    saveChannelDeviceLog(dbList,channel,deviceEntity.getId(),appPackageName);
                }
            }

        } catch (Exception e) {
            logger.error("渠道解析报错,版本[{}] {}：", appVersion, e.toString());
        }
        return true;
    }

    public void saveChannelDeviceLog(List<ChannelDeviceEntity> dbList, ChannelDeviceEntity channel ,long deviceId,String appPackageName) {
        boolean needLog = true;
        for(ChannelDeviceEntity channelDeviceEntity : dbList) {
            if(null == channelDeviceEntity.getAppPackageName()) {
                logger.info("LOG老客户数据 不再统计渠道 device {}  source {}",deviceId,channel.getSource());
                needLog = false;
                break;
            }
            if(!StringUtils.isEmpty(channel.getSource()) && null != appPackageName) {
                if(appPackageName.equals(channelDeviceEntity.getAppPackageName()) && channel.getSource().equals(channelDeviceEntity.getSource()) &&
                        DateUtil.daysBetween(channelDeviceEntity.getCreateTime(), new Date()) <= reAttributeWindows ) {
                    logger.info("LOG该渠道对应的包名已记录 device {} packagename {} source {}",deviceId,appPackageName, channel.getSource());
                    needLog = false;
                    break;
                }
            }
            if(StringUtils.isEmpty(channel.getSource()) && null != appPackageName) {
                // reffer
                if(appPackageName.equals(channelDeviceEntity.getAppPackageName()) && channel.getReferrer().equals(channelDeviceEntity.getReferrer()) &&
                        DateUtil.daysBetween(channelDeviceEntity.getCreateTime(), new Date()) <= reAttributeWindows ) {
                    logger.info("LOG该渠道对应的包名已记录 reffer device {} packagename {} referrer {}",deviceId,appPackageName, channel.getReferrer());
                    needLog = false;
                    break;
                }
            }
        }

        if(needLog ) {
            logger.warn("LOG增加渠道日志 device id {} source {}  appPackageName {} ",channel.getDeviceId(),channel.getSource() ,appPackageName);
            deviceMapper.attributionChannelLog(channel);
        }
    }

    public void saveChannelDevice(List<ChannelDeviceEntity> dbList, ChannelDeviceEntity channel ,long deviceId,String appPackageName) {
        boolean needLog = true;
        for(ChannelDeviceEntity channelDeviceEntity : dbList) {
            if(null == channelDeviceEntity.getAppPackageName()) {
                logger.info("老客户数据 不再统计渠道 device {}  source {}",deviceId,channel.getSource());
                needLog = false;
                break;
            }
            if(appPackageName.equals(channelDeviceEntity.getAppPackageName()) && !"uuang".equals(channel.getSource()) &&  !"adsplay".equals(channel.getSource())) {
                logger.info("该设备对应的包名已记录 device {} packagename {}",deviceId,appPackageName);
                needLog = false;
                break;
            }
            if(appPackageName.equals(channelDeviceEntity.getAppPackageName()) && channel.getSource().equals(channelDeviceEntity.getSource())) {
                logger.info("该渠道对应的包名已记录 device {} packagename {}",deviceId,appPackageName);
                needLog = false;
                break;
            }
        }

        if(needLog ) {
            logger.warn("增加uuang adsplay 渠道包名首次渠道来源 device id {} source {}  appPackageName {} ",channel.getDeviceId(),channel.getSource() ,appPackageName);
            deviceMapper.attributionChannel(channel);
        }
    }

    public ChannelDeviceEntity generateChannel(String referrer, long deviceId) throws UnsupportedEncodingException {
        if(!StringUtils.isEmpty(referrer)){
            referrer = URLDecoder.decode(referrer, "UTF-8");

            referrer = referrer.replaceAll("%20"," ");
        }

        ChannelDeviceEntity channel = new ChannelDeviceEntity();
        channel.setDeviceId(deviceId);
        channel.setReferrer(referrer);
        GA ga = RestUtils.assembleGA(referrer);

        if (ga != null) {
            channel.setReferrer(ga.getReferrer());
            if(!StringUtils.isEmpty(ga.getSource())){
                channel.setSource(ga.getSource());
            }
        }
        return channel;
    }


}