package com.songareeit.fcmpush.service;

import com.songareeit.fcmpush.common.Const;
import com.songareeit.fcmpush.common.RequestHeaderInterceptor;
import com.songareeit.fcmpush.payload.request.RequestPushMessage;
import com.songareeit.fcmpush.payload.response.ResponseFcmPush;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * @author 이동엽(Dongyeop, Lee)
 * @date 2023. 11. 28
 * @description 푸시 알림을 보내는 Service
 * @history <pre>
 *  -----------------------------------------------------------------
 *      변경일          작성자                    변경내용
 *  --------------- ---------- --------------------------------------
 *   2023. 11. 28     이동엽                    최초 작성
 *
 *
 *  </pre>
 */
@Service
@RequiredArgsConstructor
public class PushNotificationService {

    @Value("${fcm.url}")
    private String FCM_URL;

    @Value("${fcm.api-key}")
    private String FCM_API_KEY;

    private final FcmOpenFeign fcmOpenFeign;

    /**
     * RestTemplate를 이용해 푸시 알람 보내기
     *
     * @param notification : 송신할 알림 정보
     * @return : FCM 결과
     */
    public ResponseFcmPush pushFCMWithRestTemplate(final HttpEntity<RequestPushMessage> notification) {

        final RestTemplate restTemplate = new RestTemplate();
        final ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

        /* Header에 필요한 정보 삽입 */
        interceptors.add(new RequestHeaderInterceptor(Const.AUTHORIZATION, "key=" + FCM_API_KEY));
        interceptors.add(new RequestHeaderInterceptor(Const.CONTENT_TYPE_KEY, Const.CONTENT_TYPE_JSON_VALUE));
        restTemplate.setInterceptors(interceptors);

        return restTemplate.postForObject(FCM_URL, notification, ResponseFcmPush.class);

        /*
         * FCM 라이브러리를 사용하지 않고 직접 고려할 경우 retry, timeout를 고려해야 한다.
         */
    }

    /**
     * Feign을 이용해 푸시 알람 보내기
     *
     * @param pushMessage : FCM 송신 정보
     * @return : FCM 결과
     */
    public ResponseFcmPush pushFCMWithFeign(final RequestPushMessage pushMessage) {
        return fcmOpenFeign.pushFcm("key=" + FCM_API_KEY, pushMessage);
    }
}
