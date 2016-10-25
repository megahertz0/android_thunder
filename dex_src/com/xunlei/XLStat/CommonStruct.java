package com.xunlei.XLStat;

import java.util.ArrayList;

public class CommonStruct {

    public static class NetTypeStruct {
        public static final int NETWORK_3G = 2;
        public static final int NETWORK_NONE = 0;
        public static final int NETWORK_UNKNOWN = 3;
        public static final int NETWORK_WIFI = 1;
    }

    public static class XLStatInitStruct {
        public String configPath;
        public String dataTag;
        public ArrayList<String> extData;
        public int heartbeatInterval;
        public String installchannel;
        public String peerID;
        public String productKey;
        public String productName;
        public String productVersion;
        public String serviceKey;
        public String serviceName;
        public String serviceVersion;
        public String startupchannel;
        public long userID;
    }
}
