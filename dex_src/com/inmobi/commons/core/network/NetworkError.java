package com.inmobi.commons.core.network;

public class NetworkError {
    private ErrorCode a;
    private String b;

    public enum ErrorCode {
        NETWORK_UNAVAILABLE_ERROR(0),
        UNKNOWN_ERROR(-1),
        NETWORK_IO_ERROR(-2),
        OUT_OF_MEMORY_ERROR(-3),
        INVALID_ENCRYPTED_RESPONSE_RECEIVED(-4),
        RESPONSE_EXCEEDS_SPECIFIED_SIZE_LIMIT(-5),
        GZIP_DECOMPRESSION_FAILED(-6),
        HTTP_NO_CONTENT(204),
        HTTP_NOT_MODIFIED(304),
        HTTP_BAD_REQUEST(400),
        HTTP_SEE_OTHER(303),
        HTTP_SERVER_NOT_FOUND(404),
        HTTP_MOVED_TEMP(302),
        HTTP_INTERNAL_SERVER_ERROR(500),
        HTTP_NOT_IMPLEMENTED(501),
        HTTP_BAD_GATEWAY(502),
        HTTP_SERVER_NOT_AVAILABLE(503),
        HTTP_GATEWAY_TIMEOUT(504),
        HTTP_VERSION_NOT_SUPPORTED(505);
        private int a;

        private ErrorCode(int i) {
            this.a = i;
        }

        public final int getValue() {
            return this.a;
        }

        public static com.inmobi.commons.core.network.NetworkError.ErrorCode fromValue(int i) {
            com.inmobi.commons.core.network.NetworkError.ErrorCode[] values = values();
            int length = values.length;
            for (int i2 = 0; i2 < length; i2++) {
                com.inmobi.commons.core.network.NetworkError.ErrorCode errorCode = values[i2];
                if (errorCode.a == i) {
                    return errorCode;
                }
            }
            return null;
        }
    }

    public NetworkError(ErrorCode errorCode, String str) {
        this.a = errorCode;
        this.b = str;
    }

    public ErrorCode a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }
}
