package com.alterrae.spark.extractors;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mockito.Mockito;
import spark.Request;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestMock {
    public static RequestMockBuilder builder() {
        return new RequestMockBuilder();
    }

    public static class RequestMockBuilder {
        private Request mock;
        private String body;
        private String method;
        private Map<String,String> headers;

        public RequestMockBuilder() {
            this.mock = Mockito.mock(Request.class);
            this.body = "";
            this.method = "GET";
            this.headers = newHashMap();
        }

        public RequestMockBuilder body(String body) {
            this.body = body;
            return this;
        }

        public RequestMockBuilder addHeader(String key, String value) {
            this.headers.put(key, value);
            return this;
        }

        public RequestMockBuilder method(String method) {
            this.method = method;
            return this;
        }

        public Request build() {
            when(mock.body()).thenReturn(body);
            when(mock.requestMethod()).thenReturn(method);
            if(headers.isEmpty()) {
                when(mock.headers(anyString())).thenReturn(null);
            } else {
                buildHeaders();
            }

            return mock;
        }

        private void buildHeaders() {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                when(mock.headers(header.getKey())).thenReturn(header.getValue());
            }
        }
    }
}
