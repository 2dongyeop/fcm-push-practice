package com.songareeit.fcmpush.payload.request;

/**
 * @author 이동엽(Dongyeop, Lee)
 * @date 2023. 11. 28
 * @description 푸시 메시지 포맷
 * @history <pre>
 *  -----------------------------------------------------------------
 *      변경일          작성자                    변경내용
 *  --------------- ---------- --------------------------------------
 *   2023. 11. 28     이동엽                    최초 작성
 *
 *
 *  </pre>
 */
public record RequestPushMessage(
        RequestNotification notification,
        RequestPushData data,
        String to,
        String priority
) {
}
