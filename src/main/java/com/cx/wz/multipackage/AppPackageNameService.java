package com.cx.wz.multipackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppPackageNameService {

    @Autowired
    private AppPackageNameProductMapper appPackageNameProductMapper;

    public String getAppName(String packageName) {
        AppPackageNameProductEntity entity = appPackageNameProductMapper.findOneByPackageName(packageName);
        return entity == null ? null : entity.getAppName();
    }
}
