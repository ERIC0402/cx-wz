package com.cx.wz.event.service;

import com.cx.wz.event.entity.EventEntity;
import com.cx.wz.event.repo.EventMapper;
import com.cx.wz.uitls.device.GA;
import com.cx.wz.uitls.security.RestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class EventService {

    @Autowired
    private EventMapper eventMapper;

    public void saveGpEvent(String androidId, String referrer, String packageName, Long productId, EventEntity.Event event){
        EventEntity eventEntity = new EventEntity();
        eventEntity.setAndroidId(androidId);
        if(!StringUtils.isEmpty(referrer)) {
            GA ga = RestUtils.assembleGA(referrer);
            if (ga != null) {
                eventEntity.setSource(ga.getSource());
            }
        }
        eventEntity.setPackageName(packageName);
        eventEntity.setProductId(productId);
        eventEntity.setEvent(event);
        eventMapper.insert(eventEntity);
    }

}
