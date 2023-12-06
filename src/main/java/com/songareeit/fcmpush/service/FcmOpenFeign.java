package com.songareeit.fcmpush.service;

import com.songareeit.fcmpush.common.Const;
import com.songareeit.fcmpush.payload.request.RequestPushMessage;
import com.songareeit.fcmpush.payload.response.ResponseFcmPush;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "FcmOpenFeign", url = "${fcm.url}")
public interface FcmOpenFeign {

    @PostMapping(consumes = Const.APPLICATION_JSON)
    ResponseFcmPush pushFcm(
            @RequestHeader(value = Const.AUTHORIZATION) String fcmApiKey,
            @RequestBody RequestPushMessage pushMessage);
}
