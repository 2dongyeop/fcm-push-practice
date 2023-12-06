package com.songareeit.fcmpush.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.songareeit.fcmpush.payload.request.RequestNotification;
import com.songareeit.fcmpush.payload.request.RequestPushData;
import com.songareeit.fcmpush.payload.request.RequestPushMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author 이동엽(Dongyeop, Lee)
 * @date 2023. 11. 28
 * @description
 * @history <pre>
 *  -----------------------------------------------------------------
 *      변경일          작성자                    변경내용
 *  --------------- ---------- --------------------------------------
 *   2023. 11. 28     이동엽                    최초 작성
 *
 *
 *  </pre>
 */
@SpringBootTest
@AutoConfigureMockMvc
class PushNotificationEndpointTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void pushMessage() throws Exception {

        // given
        final String pushToken = "AAAAyEaSwBI:APA91bFw-6N55ZaZ2NOOmf3t4Ipazqe30V-Bxtu0dfb5ki-x2mIjI_tMW_o4vN6-39XBoHWQAiiTliJ61h905r-ydtZbiWwDWTU1WvI1Gvqmcoyw4lnW3XZYMUV0Fxv8N7lg5F0rsnxakQRRBPOr7o6h-rKR3tFqzQ";
        final String priority = "high";

        final RequestPushMessage request = new RequestPushMessage(
                new RequestNotification("title", "body", "default", "FCM_PLUGIN_ACTIVITY"),
                new RequestPushData("3", "title", "body"),
                pushToken,
                priority
        );

        // when
        final var result = mockMvc.perform(post("/push")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result.andExpect(status().isOk());
    }
}