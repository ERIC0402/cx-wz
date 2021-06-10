package com.cx.wz.setting.repo;


import com.cx.wz.setting.entity.SettingEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SettingMapper {

    @Select("SELECT * FROM T_SETTING WHERE ID = #{id}")
    SettingEntity findOne(@Param("id") Long id);

    @Select("SELECT * FROM T_SETTING WHERE key in ('api.market.protocol.day') ")
    List<SettingEntity> findApiMarketSettingList();

    @Cacheable(cacheNames = "restsetting")
    @Select("SELECT * FROM T_SETTING WHERE NAMESPACE = #{namespace} AND KEY = #{key}")
    SettingEntity findOneByKey(@Param("namespace") String namespace, @Param("key") String key);

    @Select("SELECT * FROM T_SETTING WHERE NAMESPACE = #{namespace} AND KEY = #{key}")
    SettingEntity findOneByKeyNotCache(@Param("namespace") String namespace, @Param("key") String key);

    @Update("UPDATE T_SETTING SET VALUE = #{value}, UPDATE_TIME = NOW() WHERE KEY = #{key}")
    int updateSettingValueByKey(@Param("key") String key, @Param("value") String value);

    @Update("UPDATE T_SETTING SET reapply_value = #{value}, UPDATE_TIME = NOW() WHERE KEY = #{key} and namespace='risk' ")
    int updateSettingReapplyValueByKey(@Param("key") String key, @Param("value") String value);

    @Update("UPDATE T_SETTING SET reapply_value = value, UPDATE_TIME = NOW() WHERE KEY = #{key} and namespace='risk' and rule is not null ")
    int updateSingleSettingReapplyValue(@Param("key") String key);

    @Update("UPDATE T_SETTING SET visibility = #{visibility}, UPDATE_TIME = NOW() WHERE KEY = #{key}  ")
    int updateSettingVisibity(@Param("key") String key, @Param("visibility") Boolean visibility);

    @Update("UPDATE T_SETTING SET VALUE = #{value}, UPDATE_TIME = NOW() WHERE ID = #{id}")
    int updateSettingValueById(@Param("id") long id, @Param("value") String value);

    @Update("UPDATE T_SETTING SET VALUE = #{value}, UPDATE_TIME = NOW() WHERE ID = #{id}")
    int updateSettingFirstValueById(@Param("id") long id, @Param("value") String value);

    @Update("UPDATE T_SETTING SET reapply_value = #{value}, UPDATE_TIME = NOW() WHERE ID = #{id}")
    int updateSettingReapplyValueById(@Param("id") long id, @Param("value") String value);

    @Update("UPDATE t_setting SET value = #{value}, status = #{status}, update_time = NOW() WHERE id = #{id}")
    int updateSettingValueAndStatusById(@Param("id") long id, @Param("value") String value, @Param("status") SettingEntity.Status status);

    @Update("UPDATE t_setting SET status = #{status}, update_time = NOW() WHERE KEY = #{key} and namespace= #{namespace}")
    int updateSettingStatusById(@Param("key") String key, @Param("namespace") String namespace, @Param("status") SettingEntity.Status status);

    @Select("select DISTINCT namespace from t_setting where visibility = true")
    List<String> getSettingNamespaceList();

    @Select("select * from t_setting where namespace in ('loan_application', 'loan_app_client', 'company') or key='platform_locale' ")
    List<SettingEntity> findApplicationSettingList();

    @Select("select * from t_setting where namespace = 'loan_app_client' and key like 'main_banner_%'")
    List<SettingEntity> findMainBannerSetting();

    @Select("select * from t_setting where namespace = #{namespace}")
    List<SettingEntity> findByNamespace(@Param("namespace") String namespace);

    @Select("select rule from t_setting where namespace = 'risk' and rule is not null and status = 'ACTIVE' and (value is not null or reapply_value is not null) and (trim(value) != '' or trim(reapply_value) != '') group by rule")
    List<String> findActiveRule();

    @Select("select * from t_setting where rule = any(#{rulesStr}::varchar[]) order by key")
    List<SettingEntity> fetchByRules(@Param("rulesStr") String rulesStr);

    @Update("update t_setting set value = #{value}, update_time = now() where namespace = #{namespace} and key = #{key}")
    int updateValueByNSAndKey(@Param("namespace") String namespace, @Param("key") String key, @Param("value") String value);

    @Select("SELECT * FROM T_SETTING WHERE namespace = #{namespace} order by key")
    List<SettingEntity> findValueByNamespace(@Param("namespace") String namespace);

    @Insert("INSERT INTO t_setting(namespace, key, value, comments) " +
            " VALUES(#{namespace},#{key}, #{value}, #{comments})")
    @Options(useGeneratedKeys = true)
    int insertDomain(@Param("namespace") String namespace, @Param("key") String key, @Param("value") String value, @Param("comments") String comments);

    @Delete("delete from t_setting where namespace = #{namespace} and key = #{key}")
    int deleteByNamespaceAndKey(@Param("namespace") String namespace, @Param("key") String key);

}
