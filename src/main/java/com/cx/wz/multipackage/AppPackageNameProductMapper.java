package com.cx.wz.multipackage;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.postgresql.util.PSQLException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AppPackageNameProductMapper {


    @Cacheable(cacheNames = "packageproduct")
    @Select("SELECT * FROM t_appname_packagename_config WHERE app_package_name = #{appPackageName} ORDER BY ID DESC LIMIT 1 ")
    AppPackageNameProductEntity findOneByPackageName(@Param("appPackageName") String appPackageName);

    @Insert("insert into t_appname_packagename_config(app_package_name, app_name, valid_request_sign) values (#{appPackageName}, #{appName}, #{validSign})")
    int createAppNamePackageNameConf(@Param("appPackageName") String appPackageName, @Param("appName") String appName, @Param("validSign") boolean validSign) throws PSQLException;

    @Cacheable(cacheNames = "packageproduct")
    @Select("select app_package_name from t_appname_packagename_config")
    List<String> findAllPackageName();

}
