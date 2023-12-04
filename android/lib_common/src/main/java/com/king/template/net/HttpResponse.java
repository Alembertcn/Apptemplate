package com.king.template.net;

/**
 * 接口返回的统一结构
 * Created by Yin Shudi on 17/7/13.
 */

public class HttpResponse<T> {

    private ResponseHeader header;
    private T body;

    public ResponseHeader getHeader() {
        return header;
    }

    public void setHeader(ResponseHeader header) {
        this.header = header;
    }

    public long getTimestamp() {
        return header != null ? header.getTimestamp() : 0;
    }

    public int getCode() {
        return header != null ? header.getCode() : HttpCode.UNKNOWN_ERROR;
    }

    public String getMessage() {
        return header != null ? header.getMessage() : "";
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    private static class ResponseHeader {
        private long timestamp;
        private int code;
        private String message;

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
