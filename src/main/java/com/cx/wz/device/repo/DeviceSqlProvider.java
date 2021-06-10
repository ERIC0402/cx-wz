package com.cx.wz.device.repo;

import com.cx.wz.device.entity.ChannelDeviceEntity;
import com.cx.wz.device.entity.DeviceEntity;
import com.cx.wz.uitls.sql.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

public class DeviceSqlProvider {



    public String findChannelByDeviceLog(Map<String, Object> params) {
        SQL sql = new SQL() {{
            SELECT("*");
            FROM("T_CHANNEL_DEVICE_LOG");
            WHERE("1=1");
            WHERE_IF("DEVICE_ID=#{deviceId}", params.get("deviceId") != null);
            WHERE_IF("SOURCE=#{source}",  !StringUtils.isEmpty(params.get("source")));
        }};

        return sql.toString();
    }

    public String findChannelByDeviceLogByReffer(Map<String, Object> params) {
        SQL sql = new SQL() {{
            SELECT("*");
            FROM("T_CHANNEL_DEVICE_LOG");
            WHERE("1=1");
            WHERE_IF("DEVICE_ID=#{deviceId}", params.get("deviceId") != null);
            WHERE_IF("referrer=#{reffer} ",  !StringUtils.isEmpty(params.get("reffer")));
        }};

        return sql.toString();
    }

    public String attributionChannel(ChannelDeviceEntity channelDeviceEntity){
        return new SQL() {{
            INSERT_INTO("T_CHANNEL_DEVICE");
            VALUES("DEVICE_ID", "#{deviceId}");
            VALUES("referrer", "#{referrer}");
            VALUES_IF("source", "#{source}", !StringUtils.isEmpty(channelDeviceEntity.getSource()));
            VALUES_IF("app_package_name", "#{appPackageName}", !StringUtils.isEmpty(channelDeviceEntity.getAppPackageName()));
        }}.toString();
    }

    public String attributionChannelLog(ChannelDeviceEntity channelDeviceEntity){
        return new SQL() {{
            INSERT_INTO("T_CHANNEL_DEVICE_LOG");
            VALUES("DEVICE_ID", "#{deviceId}");
            VALUES("referrer", "#{referrer}");
            VALUES_IF("source", "#{source}", !StringUtils.isEmpty(channelDeviceEntity.getSource()));
            VALUES_IF("app_package_name", "#{appPackageName}", !StringUtils.isEmpty(channelDeviceEntity.getAppPackageName()));
        }}.toString();
    }

    public String insertDevice(DeviceEntity deviceEntity){
        return new SQL() {{
            INSERT_INTO("T_DEVICE");
            VALUES_IF("ANDROID_ID", "#{androidId}", !StringUtils.isEmpty(deviceEntity.getAndroidId()));
            VALUES_IF("G_ID", "#{gId}", !StringUtils.isEmpty(deviceEntity.getGId()));
            VALUES_IF("IP", "#{ip}", !StringUtils.isEmpty(deviceEntity.getIp()));
            VALUES_IF("mac_address", "#{macAddress}", !StringUtils.isEmpty(deviceEntity.getMacAddress()));
        }}.toString();
    }
}
