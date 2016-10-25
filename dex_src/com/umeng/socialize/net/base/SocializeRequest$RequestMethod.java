package com.umeng.socialize.net.base;

protected enum SocializeRequest$RequestMethod {
    GET {
        public final String toString() {
            return SocializeRequest.access$100();
        }
    },
    POST {
        public final String toString() {
            return SocializeRequest.access$200();
        }
    };
}
