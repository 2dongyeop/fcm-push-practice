package com.songareeit.fcmpush.common;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.io.IOException;

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
public class RequestHeaderInterceptor implements ClientHttpRequestInterceptor {

    private final String headerName;
    private final String headerValue;

    public RequestHeaderInterceptor(final String headerName, final String headerValue) {
        this.headerName = headerName;
        this.headerValue = headerValue;
    }

    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution) throws IOException {

        final HttpRequest wrapper = new HttpRequestWrapper(request);
        wrapper.getHeaders().set(headerName, headerValue);

        return execution.execute(wrapper, body);
    }
}
