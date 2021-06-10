package com.cx.wz.event;

import com.cx.wz.event.entity.EventEntity;
import com.cx.wz.event.service.EventService;
import com.cx.wz.uitls.CustomizedHttpHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
@CrossOrigin
public class EventController {

    protected static Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @GetMapping("/{event}")
    public ResponseEntity login(@PathVariable("event") EventEntity.Event event,
                                @RequestHeader(value = CustomizedHttpHeader.REFERRER) String source,
                                @RequestHeader(value = CustomizedHttpHeader.ANDROID_ID) String androidId,
                                @RequestHeader(value = CustomizedHttpHeader.APP_PACKAGE_NAME) String packageName,
                                @RequestParam("productId") Long productId) {
        eventService.saveGpEvent(androidId, source, packageName, productId, event);
        return new ResponseEntity(HttpStatus.OK);
    }

}
