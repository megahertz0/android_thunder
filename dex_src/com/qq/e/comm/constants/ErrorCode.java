package com.qq.e.comm.constants;

public class ErrorCode {

    public static final class AdError {
        public static final int DETAIl_URL_ERROR = 503;
        public static final int JSON_PARSE_ERROR = 502;
        public static final int NO_FILL_ERROR = 501;
        public static final int PLACEMENT_ERROR = 500;
        public static final int RETRY_LOAD_SUCCESS = 506;
        public static final int RETRY_NO_FILL_ERROR = 505;
    }

    public static final class InitError {
        public static final int GET_INTERFACE_ERROR = 303;
        public static final int INIT_ADMANGER_ERROR = 301;
        public static final int INIT_AD_ERROR = 300;
        public static final int INIT_PLUGIN_ERROR = 302;
        public static final int INVALID_REQUEST_ERROR = 304;
    }

    public static final class NetWorkError {
        public static final int HTTP_STATUS_ERROR = 403;
        public static final int IMG_LOAD_ERROR = 406;
        public static final int QUEUE_FULL_ERROR = 401;
        public static final int RESOURCE_LOAD_FAIL_ERROR = 405;
        public static final int RETRY_TIME_JS_ERROR = 407;
        public static final int RETRY_TIME_NATIVE_ERROR = 402;
        public static final int STUB_NETWORK_ERROR = 400;
        public static final int TIME_OUT_ERROR = 404;
    }

    public static final class OtherError {
        public static final int ANDROID_PERMMISON_ERROR = 602;
        public static final int CONTAINER_HEIGHT_ERROR = 606;
        public static final int CONTAINER_INVISIBLE_ERROR = 600;
        public static final int GET_PARAS_FROM_JS_ERROR = 603;
        public static final int GET_PARAS_FROM_NATIVE_ERROR = 604;
        public static final int NETWORK_TYPE_ERROR = 601;
        public static final int UNKNOWN_ERROR = 605;
    }
}
