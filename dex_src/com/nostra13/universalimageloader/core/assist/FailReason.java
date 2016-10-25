package com.nostra13.universalimageloader.core.assist;

public final class FailReason {
    private final FailType a;
    private final Throwable b;

    public enum FailType {
        IO_ERROR,
        DECODING_ERROR,
        NETWORK_DENIED,
        OUT_OF_MEMORY,
        UNKNOWN;

        static {
            IO_ERROR = new com.nostra13.universalimageloader.core.assist.FailReason.FailType("IO_ERROR", 0);
            DECODING_ERROR = new com.nostra13.universalimageloader.core.assist.FailReason.FailType("DECODING_ERROR", 1);
            NETWORK_DENIED = new com.nostra13.universalimageloader.core.assist.FailReason.FailType("NETWORK_DENIED", 2);
            OUT_OF_MEMORY = new com.nostra13.universalimageloader.core.assist.FailReason.FailType("OUT_OF_MEMORY", 3);
            UNKNOWN = new com.nostra13.universalimageloader.core.assist.FailReason.FailType("UNKNOWN", 4);
            a = new com.nostra13.universalimageloader.core.assist.FailReason.FailType[]{IO_ERROR, DECODING_ERROR, NETWORK_DENIED, OUT_OF_MEMORY, UNKNOWN};
        }
    }

    public FailReason(FailType failType, Throwable th) {
        this.a = failType;
        this.b = th;
    }
}
