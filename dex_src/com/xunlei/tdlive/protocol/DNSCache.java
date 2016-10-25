package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.IDNSCache;
import java.util.HashMap;

public class DNSCache {
    private static IDNSCache[] sDnsCaches;

    public static class DevDNSCache implements IDNSCache {
        private HashMap<String, String> mDNSCache;

        public DevDNSCache() {
            this.mDNSCache = new HashMap();
            this.mDNSCache.put("upload.live.xunlei.com", "10.10.29.33");
            this.mDNSCache.put("h5.live.xunlei.com", "10.10.29.33");
            this.mDNSCache.put("misc.live.xunlei.com", "10.10.29.33");
            this.mDNSCache.put("doc.live.xunlei.com", "10.10.29.33");
            this.mDNSCache.put("biz.live.xunlei.com", "10.10.29.33");
            this.mDNSCache.put("list.live.xunlei.com", "10.10.29.33");
            this.mDNSCache.put("msg.live.xunlei.com", "10.10.160.65");
        }

        public String hit(String str) {
            return (String) this.mDNSCache.get(str);
        }

        public String disc() {
            return "\u5f00\u53d1\u73af\u5883";
        }

        public String did() {
            return com.xunlei.tdlive.protocol.DNSCache.DevDNSCache.class.getSimpleName();
        }
    }

    public static class TestDNSCache implements IDNSCache {
        private HashMap<String, String> mDNSCache;

        public TestDNSCache() {
            this.mDNSCache = new HashMap();
            this.mDNSCache.put("upload.live.xunlei.com", "10.10.142.33");
            this.mDNSCache.put("h5.live.xunlei.com", "10.10.142.33");
            this.mDNSCache.put("misc.live.xunlei.com", "10.10.142.33");
            this.mDNSCache.put("doc.live.xunlei.com", "10.10.142.33");
            this.mDNSCache.put("biz.live.xunlei.com", "10.10.142.33");
            this.mDNSCache.put("list.live.xunlei.com", "10.10.142.33");
            this.mDNSCache.put("msg.live.xunlei.com", "10.10.160.64");
        }

        public String hit(String str) {
            return (String) this.mDNSCache.get(str);
        }

        public String disc() {
            return "\u6d4b\u8bd5\u73af\u5883";
        }

        public String did() {
            return com.xunlei.tdlive.protocol.DNSCache.TestDNSCache.class.getSimpleName();
        }
    }

    static {
        sDnsCaches = new IDNSCache[]{new DevDNSCache(), new TestDNSCache()};
    }

    public static IDNSCache getDevDNS() {
        return sDnsCaches[0];
    }

    public static IDNSCache getTestDNS() {
        return sDnsCaches[1];
    }

    public static IDNSCache[] getDNSCaches() {
        return sDnsCaches;
    }

    public static IDNSCache getDNSCache(String str) {
        IDNSCache[] iDNSCacheArr = sDnsCaches;
        int length = iDNSCacheArr.length;
        for (int i = 0; i < length; i++) {
            IDNSCache iDNSCache = iDNSCacheArr[i];
            if (iDNSCache.did().equals(str)) {
                return iDNSCache;
            }
        }
        return null;
    }
}
