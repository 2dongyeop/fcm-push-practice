package com.songareeit.fcmpush.endpoint;

import com.songareeit.fcmpush.payload.request.RequestPushMessage;
import com.songareeit.fcmpush.payload.response.ResponseFcmPush;
import com.songareeit.fcmpush.service.PushNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 이동엽(Dongyeop, Lee)
 * @date 2023. 11. 28
 * @description 푸시 알림 요청을 받는 Endpoint
 * @history <pre>
 *  -----------------------------------------------------------------
 *      변경일          작성자                    변경내용
 *  --------------- ---------- --------------------------------------
 *   2023. 11. 28     이동엽                    최초 작성
 *
 *
 *  </pre>
 */
@Slf4j
@RestController
@RequestMapping("/push")
@RequiredArgsConstructor
public class PushNotificationEndpoint {

    private final PushNotificationService pushNotificationService;

    /**
     * 푸시 알림 보내기 with RestTemplate
     *
     * @param pushMessage : 송신할 푸시 메시지 포맷
     * @return : FCM 결과 값
     */
    @PostMapping("/v1")
    public ResponseEntity<ResponseFcmPush> postPushMessageWithRestTemplate(@RequestBody final RequestPushMessage pushMessage) {

        log.info("pushMessage[{}]", pushMessage);

        final HttpEntity<RequestPushMessage> request = new HttpEntity<>(pushMessage);
        return ResponseEntity.ok(pushNotificationService.pushFCMWithRestTemplate(request));
    }

    /**
     * 푸시 알림 보내기 with OpenFeign
     *
     * @param pushMessage : 송신할 푸시 메시지 포맷
     * @return : FCM 결과 값
     */
    @PostMapping("/v2")
    public ResponseEntity<ResponseFcmPush> postPushMessageWithOpenFeign(@RequestBody final RequestPushMessage pushMessage) {

        log.info("pushMessage[{}]", pushMessage);
        return ResponseEntity.ok(pushNotificationService.pushFCMWithFeign(pushMessage));
    }
}
