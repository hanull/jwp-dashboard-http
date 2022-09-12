package org.apache.coyote.http11.request;

import java.util.stream.Stream;
import org.apache.coyote.http11.exception.UnsupportedHttpMethodException;

public enum HttpMethod {
    GET("get"),
    POST("post");

    private final String value;

    HttpMethod(final String value) {
        this.value = value;
    }

    public static HttpMethod from(final String value) {
        return Stream.of(values())
                .filter(it -> it.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new UnsupportedHttpMethodException(value));
    }

    public boolean isPost() {
        return this == POST;
    }

    public String getValue() {
        return value;
    }
}
