package com.cx.wz.device.service;


import com.cx.wz.device.repo.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceChannelService {

    @Autowired
    private DeviceMapper deviceMapper;


    public List<String> getAllChannel() {
        return deviceMapper.getAllChannel();
    }

    public Integer getUUangCounts() {
        return deviceMapper.getUUangCounts();
    }

    public Integer getAdsplayCounts() {
        return deviceMapper.getAdsplayCounts();
    }
}
