package org.apache.coyote.http11.exception;

public class HttpMethodNotSupportException extends RuntimeException {

    public HttpMethodNotSupportException(final String value) {
        super(String.format("지원하지 않는 http 메서드입니다. (%s)", value));
    }
}