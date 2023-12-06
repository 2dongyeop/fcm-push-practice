package com.songareeit.fcmpush.payload.response;

import java.util.List;

/**
 * @author 이동엽(Dongyeop, Lee)
 * @date 2023. 11. 28
 * @description FCM 푸시 알람 응답
 * @history <pre>
 *  -----------------------------------------------------------------
 *      변경일          작성자                    변경내용
 *  --------------- ---------- --------------------------------------
 *   2023. 11. 28     이동엽                    최초 작성
 *
 *
 *  </pre>
 */
public record ResponseFcmPush(
        Long multicast_id,
        int success,
        int failure,
        int canonical_ids,
        List<ResponseMessageId> results
) {
}
