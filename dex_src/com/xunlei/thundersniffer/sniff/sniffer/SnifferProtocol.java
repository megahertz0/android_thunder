package com.xunlei.thundersniffer.sniff.sniffer;

final class SnifferProtocol {

    public static interface CacheType {
        public static final int MANUAL = 1;
        public static final int NSRC_CACHE = 2;
        public static final int REFURL_CACHE = 3;
        public static final int TITLE_CACHE = 4;
    }

    public static interface GetKey {
        public static final String CID = "cid";
        public static final String DECODE_URL = "decode_url";
        public static final String FILENAME = "filename";
        public static final String FILESIZE = "filesize";
        public static final String FORMAT = "format";
        public static final String GCID = "gcid";
        public static final String HASH = "hash";
        public static final String HEAD = "head";
        public static final String INBLACKLIST = "inblacklist";
        public static final String ORIGINAL_DOWNLOAD_URL = "original_url";
        public static final String TYPE = "type";
        public static final String URL = "url";
        public static final String VODPLAY = "vodplay";
    }

    public static interface SetKey {
        public static final String ETAG = "etag";
        public static final String FILEINFO = "fileinfo";
        public static final String HEAD = "head";
        public static final String KEYWORD = "keyword";
        public static final String LAST_MODIFIED = "last_modified";
        public static final String NSRC = "nsrc";
        public static final String ORIGINAL_FILEINFO = "original_fileinfo";
        public static final String PAGE = "page";
        public static final String REFURL = "refurl";
        public static final String REFURLINFO = "refurlinfo";
        public static final String TITLE = "title";
    }

    public static interface SnifferGetReturn {
        public static final int E_INBLACKLIST = 1;
        public static final int NO_ERROR = 0;
    }
}
