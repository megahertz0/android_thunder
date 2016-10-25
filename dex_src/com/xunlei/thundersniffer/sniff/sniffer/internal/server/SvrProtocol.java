package com.xunlei.thundersniffer.sniff.sniffer.internal.server;

abstract class SvrProtocol {

    public static interface SHubBatchQueryKeys {
        public static final String cid = "cid";
        public static final String decode_url = "decode_url";
        public static final String filename = "filename";
        public static final String filesize = "filesize";
        public static final String format = "format";
        public static final String gcid = "gcid";
        public static final String hash = "hash";
        public static final String res = "res";
        public static final String type = "type";
        public static final String url = "url";
        public static final String urllist = "urllist";
    }

    public static interface SVodBatchQueryKeys {
        public static final String trans_list = "trans_list";
        public static final String urllist = "urllist";
    }

    public static interface SVodCode {
        public static final int E_ERROR = 7;
        public static final int E_NOT_FOUND = 4;
        public static final int E_NOT_TRANSCODE = 5;
        public static final int E_OK = 0;
        public static final int E_TIME_OUT = 3;
        public static final int E_UNKNOWN = -1;
        public static final int E_URL_ILLEGAL = 6;
        public static final int E_URL_NOT_VIDEO = 8;
    }
}
