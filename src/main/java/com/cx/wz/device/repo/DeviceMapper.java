package com.cx.wz.device.repo;

import com.cx.wz.device.entity.ChannelDeviceEntity;
import com.cx.wz.device.entity.DeviceEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface DeviceMapper {

    @Select("SELECT * FROM T_CHANNEL_DEVICE WHERE DEVICE_ID=#{deviceId}")
    List<ChannelDeviceEntity> findChannelByDevice(@Param("deviceId") long deviceId);

    @Select("SELECT * FROM t_channel_device_log WHERE DEVICE_ID=#{deviceId} and create_time> now() - interval '1 hour' * #{limitDay}  ")
    List<ChannelDeviceEntity> findChannelByDeviceLogList(@Param("deviceId") long deviceId, @Param("limitDay") Integer limitDay);

    @Select("select * from t_channel_device_log where app_package_name=#{appPackageName} and device_id= #{deviceId} and (source in ('adsplay','google-play') or referrer like 'fb=%'   ) and create_time - interval '15 min' < #{createTime} and create_time >= #{createTime}  order by id asc  limit 1;")
    ChannelDeviceEntity findChannelDeviceLogByFb(@Param("deviceId") long deviceId, @Param("createTime") Date createTime, @Param("appPackageName") String appPackageName);

    @Select("select * from t_channel_device where device_id= #{deviceId} and source in ('(not set)','(not%20set)') and create_time + interval '1 min' >= #{createTime}  and create_time< #{createTime} limit 1")
    ChannelDeviceEntity findChannelDeviceByFb(@Param("deviceId") long deviceId, @Param("createTime") Date createTime);


    @Update("update t_channel_device set source=#{source} ,referrer=#{referrer},medium='1' where id=#{id}")
    int updateFBChannelSource(@Param("source") String source, @Param("referrer") String referrer, @Param("id") long id);

    @Select("SELECT * FROM T_CHANNEL_DEVICE WHERE DEVICE_ID=#{deviceId} order by create_time desc limit 1")
    ChannelDeviceEntity findFirstChannelByDevice(@Param("deviceId") Long deviceId);

    @Select("SELECT * FROM T_CHANNEL_DEVICE WHERE DEVICE_ID=#{deviceId} and sid_type != 'H5' order by create_time desc limit 1")
    ChannelDeviceEntity findLastAppChannelByDevice(@Param("deviceId") Long deviceId);

    @Select("SELECT * FROM t_channel_device_log WHERE DEVICE_ID=#{deviceId} ORDER BY id DESC LIMIT 1")
    ChannelDeviceEntity findChannelByDeviceLog(@Param("deviceId") long deviceId);


    @SelectProvider(type = DeviceSqlProvider.class, method = "findChannelByDeviceLog")
    List<ChannelDeviceEntity> findChannelByDeviceAndSourceLog(@Param("deviceId") long deviceId, @Param("source") String source);

    @SelectProvider(type = DeviceSqlProvider.class, method = "findChannelByDeviceLogByReffer")
    List<ChannelDeviceEntity> findChannelByDeviceLogByReffer(@Param("deviceId") long deviceId, @Param("reffer") String reffer);

    @InsertProvider(type = DeviceSqlProvider.class, method = "attributionChannel")
    int attributionChannel(ChannelDeviceEntity channelDeviceEntity);

    @InsertProvider(type = DeviceSqlProvider.class, method = "attributionChannelLog")
    int attributionChannelLog(ChannelDeviceEntity channelDeviceEntity);

    @InsertProvider(type = DeviceSqlProvider.class, method = "insertDevice")
    @Options(useGeneratedKeys = true)
    int insertDevice(DeviceEntity deviceEntity);

    @Select("SELECT * FROM T_DEVICE WHERE AF_ID=#{afId} ORDER BY ID DESC LIMIT 1")
    DeviceEntity findDeviceByAF(String afId);

    @Update("UPDATE t_device SET af_id=#{afId} WHERE id=#{id} ")
    int updateDeviceAfId(@Param("afId") String afId, @Param("id") long id);

    @Update("UPDATE t_device SET g_id=#{gaId} WHERE id=#{id} ")
    int updateDeviceGaId(@Param("gaId") String gaId, @Param("id") long id);

    @Update("UPDATE t_device SET fish_uuid=#{uuid} WHERE id=#{id} ")
    int updateDeviceFishUUID(@Param("uuid") String uuid, @Param("id") long id);

    @Update("UPDATE t_device SET st_uuid=#{uuid} WHERE id=#{id} ")
    int updateDeviceStUUID(@Param("uuid") String uuid, @Param("id") long id);

    @Update("UPDATE t_device SET country=#{country}, province=#{province} WHERE id=#{id} ")
    int updateDeviceLocation(@Param("country") String country, @Param("province") String province, @Param("id") long id);

    @Select("SELECT * FROM T_DEVICE WHERE AD_ID=#{adId} ORDER BY ID DESC LIMIT 1")
    DeviceEntity findDeviceByAD(String adId);

    @Select("SELECT * FROM T_DEVICE WHERE ANDROID_ID=#{androidId} ORDER BY ID DESC LIMIT 1")
    DeviceEntity findDeviceByAndroidId(String androidId);

    @Select("SELECT * FROM T_DEVICE WHERE ANDROID_ID=#{androidId} ORDER BY ID LIMIT 1")
    DeviceEntity findFirstDeviceByAndroidId(String androidId);

    @Select("SELECT * FROM T_DEVICE WHERE ID=#{id} ORDER BY ID DESC LIMIT 1")
    DeviceEntity findDeviceById(long id);

    @Select("SELECT * FROM T_DEVICE WHERE G_ID=#{gid} ORDER BY ID DESC LIMIT 1")
    DeviceEntity findDeviceByG(String gid);

    @Delete("delete from t_channel_device_log where id=#{id}")
    int deleteChannelDeviceLog(@Param("id") long id);

    @Select("SELECT * FROM T_DEVICE WHERE PARTNER_CLICK_ID=#{partnerClickId} AND PARTNER_ID=#{partnerId}")
    DeviceEntity findDeviceByPartner(@Param("partnerClickId") String partnerClickId, @Param("partnerId") Long partnerId);

    @Cacheable(cacheNames = "appsource")
    @Select("SELECT distinct SOURCE FROM T_CHANNEL_DEVICE_LOG WHERE SOURCE IS NOT NULL and create_time>current_date - interval '60 days'")
    List<String> getAllChannel();

    @Cacheable(cacheNames = "uuangCount")
    @Select("select count(1)\n" +
            "from t_channel_device\n" +
            "where create_time >= now() - INTERVAL '1 days' and source='uuang' and (medium is null or medium <>'smd')")
    Integer getUUangCounts();

    @Cacheable(cacheNames = "adsplayCount")
    @Select("select count(1)\n" +
            "from t_channel_device\n" +
            "where create_time >= now() - INTERVAL '1 days' and source='adsplay' and (medium is null or medium <>'smd')")
    Integer getAdsplayCounts();

    @Select("select device.* from t_loan_app loan\n" +
            "left join t_device_source source on source.customer_id = loan.customer_id\n" +
            "left join t_device device on device.id = source.device_id\n" +
            "where loan.id = #{loanAppId}\n" +
            "order by source.id desc\n" +
            "limit 1")
    DeviceEntity findDeviceByLoanAppId(@Param("loanAppId") Long loanAppId);

    @Update("update t_device set device_no=#{deviceNo} where id=#{id}")
    void updateDeviceNo(@Param("id") Long id, @Param("deviceNo") String deviceNo);


    @Select("select *\n" +
            "from t_device\n" +
            "where g_id=#{gaid}")
    List<DeviceEntity> findDeviceByGaid(@Param("gaid") String gaid);

    @Select("select *\n" +
            "from t_device\n" +
            "where ip=#{ip}")
    List<DeviceEntity> findDeviceByIp(@Param("ip") String ip);
}
