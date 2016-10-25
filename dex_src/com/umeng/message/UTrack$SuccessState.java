package com.umeng.message;

enum UTrack$SuccessState {
    SUCCESS_CACHE,
    SUCCESS,
    FAIL_REQUEST,
    FAIL_PARAM;

    static {
        a = new UTrack$SuccessState("SUCCESS_CACHE", 0);
        b = new UTrack$SuccessState("SUCCESS", 1);
        c = new UTrack$SuccessState("FAIL_REQUEST", 2);
        d = new UTrack$SuccessState("FAIL_PARAM", 3);
        e = new UTrack$SuccessState[]{a, b, c, d};
    }
}
